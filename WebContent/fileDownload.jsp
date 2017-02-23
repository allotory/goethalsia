<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030" import="java.io.*,java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
		<title>Insert title here</title>
	</head>
	<body>
		<%
			int fileid = Integer.parseInt(request.getParameter("fileid"));

			String driverName = "com.mysql.jdbc.Driver";
			String dbURL = "jdbc:mysql://localhost/wellmail?autoReconnect=true";
			String userName = "root";
			String userPwd = "root";

			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;

			String filename = "";

			try {
				Class.forName(driverName);
				conn = DriverManager.getConnection(dbURL, userName, userPwd);
				String sql = "select * from webfile where fileid=" + fileid;
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					filename = rs.getString("filename");
				}

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

			try {
				File file = new File(filename);
				String fileName = file.getName();
				InputStream is = new FileInputStream(file);
				OutputStream os = response.getOutputStream();
				BufferedInputStream bis = new BufferedInputStream(is);
				BufferedOutputStream bos = new BufferedOutputStream(os);

				fileName = java.net.URLEncoder.encode(fileName, "UTF-8");// 处理中文文件名的问题
				fileName = new String(fileName.getBytes("UTF-8"), "GBK");// 处理中文文件名的问题
				response.reset();
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/x-msdownload");// 不同类型的文件对应不同的MIME类型
				response.setHeader("Content-Disposition",
						"attachment; filename=" + fileName);
				int bytesRead = 0;
				byte[] buffer = new byte[1024];
				while ((bytesRead = bis.read(buffer)) != -1) {
					bos.write(buffer, 0, bytesRead);// 将文件发送到客户端
				}

				out.clear();
				out = pageContext.pushBody();

				bos.flush();
				bis.close();
				bos.close();
				is.close();
				os.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		%>
	</body>
</html>