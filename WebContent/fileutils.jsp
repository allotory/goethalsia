<%@page contentType="text/html"%>
<%@page pageEncoding="GB2312"%>
<%@page import="java.io.*"%>
<%@page import="java.sql.*"%>
<%@page import="java.text.*"%>
<%@page import="com.wellmail.model.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
	request.setCharacterEncoding("GBK");
	response.setContentType("text/html;charset=GB2312");
	char[] hchl = { 13, 10 };
	String boundary = request.getContentType().substring(30);
	String field_boundary = "--" + boundary + new String(hchl);
	String last_boundary = "--" + boundary + "--" + new String(hchl);
	String _msg = "";
	ServletInputStream getdata = request.getInputStream();
	ByteArrayOutputStream temp = new ByteArrayOutputStream();
	byte[] data_line = new byte[8192];
	int line_byte_count = 0;
	boolean found_boundary = false;
	while ((line_byte_count = getdata.readLine(data_line, 0,
			data_line.length)) != -1) {
		if (!found_boundary) {
			line_byte_count = getdata.readLine(data_line, 0,
					data_line.length);
		}
		String temp_str = new String(data_line, 0, line_byte_count);
		if (temp_str.indexOf("filename") != -1) {
			if (temp_str.substring(temp_str.indexOf("filename=") + 9,
					temp_str.lastIndexOf("\"") + 1).length() > 2) {
				String file_name = temp_str.substring(temp_str
						.lastIndexOf("\\") + 1, temp_str
						.lastIndexOf("\""));
				line_byte_count = getdata.readLine(data_line, 0,
						data_line.length);
				line_byte_count = getdata.readLine(data_line, 0,
						data_line.length);
				FileOutputStream myfile = new FileOutputStream(
						"../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"
								+ file_name, false); //文件存放目录
				boolean test = true;
				while (test) {
					line_byte_count = getdata.readLine(data_line, 0,
							data_line.length);
					if (line_byte_count == -1) {
						test = false;
						break;
					}
					if (temp.size() == 0) {
						temp.write(data_line, 0, line_byte_count);
					} else {
						if (new String(data_line, 0, line_byte_count)
								.equals(field_boundary)
								|| new String(data_line, 0,
										line_byte_count)
										.equals(last_boundary)) {
							myfile.write(temp.toByteArray(), 0, temp
									.toByteArray().length - 2);
							temp.reset();
							myfile.close();
							//out.println(file_name+"上传成功了 <br>");
							_msg = _msg + file_name + "上传成功<br>";
							test = false;
							found_boundary = true;
						} else {
							temp.writeTo(myfile);
							temp.reset();
							temp.write(data_line, 0, line_byte_count);
						}
					}
				}

				//----------------------------------------

				Users users = (Users) request.getSession()
						.getAttribute("user");

				File f = new File(
						"../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"
								+ file_name);
				double filelength = f.length();
				filelength = filelength / 1024 / 1024;
				DecimalFormat df = new DecimalFormat(".###");
				String st = df.format(filelength);
				double flength = Double.parseDouble(st);

				java.util.Date d = new java.util.Date();
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String uploadtime = sdf.format(d);

				Integer filetypeidtemp = (Integer) request.getSession()
						.getAttribute("sessionfiletypeid");
				System.out.println(filetypeidtemp.intValue());
				int filetypeid = 0;
				if (filetypeidtemp != null) {
					filetypeid = filetypeidtemp.intValue();
				} else {
					// 连接数据库   查询filetypeid
					String driverName = "com.mysql.jdbc.Driver";
					String dbURL = "jdbc:mysql://localhost/wellmail?autoReconnect=true";
					String userName = "root";
					String userPwd = "root";

					Connection conn = null;
					Statement stmt = null;
					ResultSet rs = null;

					try {
						Class.forName(driverName);
						conn = DriverManager.getConnection(dbURL,
								userName, userPwd);
						String sql = "select * from filetype where filetypename='我的文档' and username='"
								+ users.getUsername() + "'";
						stmt = conn.createStatement();
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							filetypeid = rs.getInt("filetypeid");
						}

						System.out.println("查询成功" + filetypeid);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						if (rs != null) {
							try {
								rs.close();
								rs = null;
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}

						if (stmt != null) {
							try {
								stmt.close();
								stmt = null;
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}

						if (conn != null) {
							try {
								conn.close();
								conn = null;
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				}

				// 连接数据库   添加webfile
				String driverName = "com.mysql.jdbc.Driver";
				String dbURL = "jdbc:mysql://localhost/wellmail?autoReconnect=true";
				String userName = "root";
				String userPwd = "root";

				Connection conn = null;
				Statement stmt = null;
				ResultSet rs = null;

				try {
					Class.forName(driverName);
					conn = DriverManager.getConnection(dbURL, userName,
							userPwd);

					String sql = "insert into webfile values(null,'../../james-2.3.2-db/apps/james/SAR-INF/lib/userfile/upload/"
							+ file_name
							+ "',"
							+ flength
							+ ",'"
							+ uploadtime
							+ "','"
							+ users.getUsername()
							+ "'," + filetypeid + ")";
					stmt = conn.createStatement();
					stmt.executeUpdate(sql);
					System.out.println("插入成功");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (rs != null) {
						try {
							rs.close();
							rs = null;
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}

					if (stmt != null) {
						try {
							stmt.close();
							stmt = null;
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}

					if (conn != null) {
						try {
							conn.close();
							conn = null;
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}

				// 修改filetype
				try {
					Class.forName(driverName);
					conn = DriverManager.getConnection(dbURL, userName,
							userPwd);
					String sql = "select * from filetype where filetypeid="
							+ filetypeid;
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					int containfilecount = 0;
					double foldersize = 0;
					while (rs.next()) {
						containfilecount = rs
								.getInt("containfilecount");
						foldersize = rs.getDouble("foldersize");
					}

					String sqlupdateft = "update filetype set containfilecount="
							+ (containfilecount + 1)
							+ ",foldersize="
							+ (foldersize + flength)
							+ " where filetypeid=" + filetypeid;
					Statement stmt2 = conn.createStatement();
					stmt2.executeUpdate(sqlupdateft);

					System.out.println("filetype更新完成！");
					stmt2.close();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (rs != null) {
						try {
							rs.close();
							rs = null;
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}

					if (conn != null) {
						try {
							conn.close();
							conn = null;
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}

				// 修改webfile
				try {
					Class.forName(driverName);
					conn = DriverManager.getConnection(dbURL, userName,
							userPwd);
					String sql = "select * from webdisk where username='"
							+ users.getUsername() + "'";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					int filecount = 0;
					double wsleft = 0;
					while (rs.next()) {
						filecount = rs.getInt("filecount");
						wsleft = rs.getDouble("wsleft");
					}

					String sqlupdateft = "update webdisk set filecount="
							+ (filecount + 1)
							+ ",wsleft="
							+ (wsleft - flength);
					Statement stmt2 = conn.createStatement();
					stmt2.executeUpdate(sqlupdateft);

					System.out.println("webdisk更新完成！");
					stmt2.close();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (rs != null) {
						try {
							rs.close();
							rs = null;
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}

					if (conn != null) {
						try {
							conn.close();
							conn = null;
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}

				//-----------------------------------------

			} else {
				String field_name = temp_str.substring(temp_str
						.indexOf("name") + 6,
						temp_str.lastIndexOf(";") - 1);
				line_byte_count = getdata.readLine(data_line, 0,
						data_line.length);
				line_byte_count = getdata.readLine(data_line, 0,
						data_line.length);
				line_byte_count = getdata.readLine(data_line, 0,
						data_line.length);
				line_byte_count = getdata.readLine(data_line, 0,
						data_line.length);
				found_boundary = true;
				// out.println(field_name+"没有选择上传文件！ <br>"); 
				// _msg=_msg+field_name+"没有选择上传文件<br>";
			}
		} else {
			String field_name = temp_str.substring(temp_str
					.indexOf("name") + 6, temp_str.lastIndexOf("\""));
			line_byte_count = getdata.readLine(data_line, 0,
					data_line.length);
			temp.reset();
			boolean test = true;
			while (test) {
				line_byte_count = getdata.readLine(data_line, 0,
						data_line.length);
				if (line_byte_count == -1) {
					test = false;
					break;
				}
				if (new String(data_line, 0, line_byte_count)
						.equals(field_boundary)
						|| new String(data_line, 0, line_byte_count)
								.equals(last_boundary)) {
					test = false;
					found_boundary = true;
					if (temp.size() > 2) {
						// out.println(field_name+":"+new String(temp.toByteArray())+" <br>"); 
						_msg = _msg + field_name + ":"
								+ new String(temp.toByteArray())
								+ "<br>";
					} else {
						// out.println(field_name+"没有内容！ <br>"); 
						_msg = _msg + field_name + "没有内容！<br>";
					}
					temp.reset();
				} else {
					temp.write(data_line, 0, line_byte_count);
				}
			}
		}

	}
	getdata.close();
	//_msg = _msg.substring(_msg.indexOf(">")+1);
	out.println("<script>window.parent.Finish('" + _msg
			+ "');</script>");
%>