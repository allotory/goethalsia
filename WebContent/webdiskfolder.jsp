<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"
	import="java.sql.*,com.wellmail.model.*,java.text.DecimalFormat,java.util.*,com.wellmail.bean.*"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
		<title>Insert title here</title>
		<script type="text/javascript" src="js/theme_output.js"></script>
		<link href="css/style2.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/1004271438/tools.js"></script>
		<link href="css/inbox2.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="js/wp_v7.js"></script>

		<link href="css/global[2].css" rel="stylesheet" type="text/css" />
		<link href="css/skin_126green[1].css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
	function changeframe(item, sortname, src) { 
		if(item != "" && sortname != "") { 
			window.top.frames['mainFrame'].getObject('show_text').innerHTML = sortname + "  <img src=images/slide.gif broder=0 />  " + item 
		} 
		if(src != "") { 
			window.top.frames['manFrame'].location = src 
		} 
	} 
</script>

		<script type="text/javascript">   
        function checkall(){   
            var all = document.getElementById("oFormCheckAll");   
            var sub = document.getElementsByName("mid");   
            if(all.checked==true){   
                for(var i=0;i<sub.length;i++){   
                    sub[i].checked=true;   
                }   
            }else{   
                for(var i=0;i<sub.length;i++){   
                    sub[i].checked=false;   
                }   
            }   
        }   
    </script>

		<script language="javascript"> 
	function action1() 
	{ 
	   document.messageForm.action="deletefolder.do"; 
	   document.messageForm.submit(); 
	} 
	function changepage(s){
		
	   document.messageForm.action="webdiskfolder.jsp?pageNo="+s; 
	   document.messageForm.submit();
	}
</script>
	</head>
	<body>
		<form name="messageForm">
			<div class="gNetfolder">
				<!-- 顶栏 Start -->
				<div class="g-title-1">
					<h2>
						网盘
					</h2>
					<%
						WebDisk webdisk = (WebDisk) request.getSession().getAttribute(
								"webdisk");

						double webspace = webdisk.getWebspace();
						double wsleft = webdisk.getWsleft();

						int filecount = webdisk.getFilecount();

						double probar = ((webspace - wsleft) / webspace) * 100;

						DecimalFormat df = new DecimalFormat("##.###");
						String st = df.format(probar);
						probar = Double.parseDouble(st);
					%>
					<span class="txt-info"> 共${fn:length(webfilebeanlist)}个文件夹 <%=filecount%>个文件
						| 容量： <span class="g-probar" title="已使用<%=probar%>%"><span
							style="width: <%=probar%>%"></span> </span> <span class="maxsize"><script
								language="JavaScript"></script> </span>&nbsp; </span>
					<div class="ext">

						<span class="srch"> <span class="g-srch"><!--  <span
								class="ipt-t ipt-t-dft"><input id="minikeyword"
										type="text" class="txt-info" value="搜索网盘"
										onfocus="if(this.value =='搜索网盘'){this.value='';}"
										onblur="if(this.value ==''){this.value='搜索网盘';}"
										onkeyup="if(event.keyCode == 13){fWpMiniSearch();}" /><a
									href="javascript:fGoto()" onclick="fWpMiniSearch();"
									class="btn btn-srch"></a><span class="btn btn-advsrch"
									onclick="fWpSetSearch(true)"></span>--> </span> </span>
					</div>

				</div>
				<!-- 顶栏 End -->

				<%
					Users users = (Users) request.getSession().getAttribute("user");

					// 连接数据库   查询filetypeid
					String driverName = "com.mysql.jdbc.Driver";
					String dbURL = "jdbc:mysql://localhost/wellmail?autoReconnect=true";
					String userName = "root";
					String userPwd = "root";

					Connection conn = null;
					Statement stmt = null;
					ResultSet rs = null;

					int filetypeid = 0;

					try {
						Class.forName(driverName);
						conn = DriverManager.getConnection(dbURL, userName, userPwd);
						String sql = "select * from filetype where filetypename='我的文档' and username='"
								+ users.getUsername() + "'";
						stmt = conn.createStatement();
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							filetypeid = rs.getInt("filetypeid");
						}

						// System.out.println("查询成功"+filetypeid); 
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
				%>

				<%
					//分页
					List<WebFileBean> webfilebeanlist = (List<WebFileBean>) request
							.getSession().getAttribute("webfilebeanlist");

					int pageSize = 7;
					String temp = request.getParameter("pageNo");
					int pageNo = 0;
					if (temp != null) {
						pageNo = Integer.parseInt(temp);
					}
					if (pageNo <= 0) {
						pageNo = 1;
					}

					int totalRecords = webfilebeanlist.size();
					//System.out.println(totalRecords+"____totalRecords");

					int totalPages = totalRecords % pageSize == 0 ? totalRecords
							/ pageSize : totalRecords / pageSize + 1;

					if (pageNo > totalPages) {
						pageNo = totalPages;
					}

					int startPos = (pageNo - 1) * pageSize;
					//System.out.println(startPos+"____startPos");

					List<WebFileBean> webfilebeanlistTemp = new ArrayList<WebFileBean>();

					//System.out.println(pageNo+"***pageno**");
					for (int i = 0; i < pageNo - 1; i++) {
						pageSize += 7;
					}
					//System.out.println(pageSize+"***pagesize*");

					if (startPos >= totalRecords - totalRecords % pageSize) {
						pageSize = totalRecords % pageSize;
					}
					if (webfilebeanlist.size() > 0) {
						for (int i = startPos; i < pageSize; i++) {
							webfilebeanlistTemp.add(webfilebeanlist.get(i));
							//System.out.println(articles.get(i)+"--------");
						}
					}

					request.getSession().setAttribute("webFolderPageNo", pageNo);
					request.getSession()
							.setAttribute("webFolderTotalPages", totalPages);
					request.getSession().setAttribute("webfilebeanlistTemp",
							webfilebeanlistTemp);
				%>


				<!-- 工具栏1 -->
				<div class="g-toolbar">
					<div class="btngrp">
						<a href="createfolder.jsp" class="btn btn-dft"><span>新建文件夹</span>
						</a>
					</div>
					<div class="btngrp">
						<a href="#" onclick="action1()" class="btn btn-dft btn-dft-gc"><span>删
								除</span> </a>
					</div>
					<div class="btngrp btngrp-ext">
						<a href="webdiskfolder.jsp?pageNo=1" class="txt-disabd">首页</a><a
							href="webdiskfolder.jsp?pageNo=${webFolderPageNo - 1 }"
							class="txt-disabd">上页</a><a
							href="webdiskfolder.jsp?pageNo=${webFolderPageNo + 1 }"
							class="txt-disabd">下页</a><a
							href="webdiskfolder.jsp?pageNo=${webFolderTotalPages }"
							class="txt-disabd">末页</a>
						<select name="pageNo" onchange="changepage(this.value)">
							<c:forEach var="t" begin="1" end="${webFolderTotalPages}">
								<option value="${t }">
									${t }/${webFolderTotalPages }
								</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<table width="96%" align="center">
					<tr>
						<td>
							<div class="ContentWp">
								<div class="ContentThemeWp">

									<div class="Ibx_Main_Wp">
										<table id="oTableHead" class="Ibx_gTable F2Img Ibx_gTable_TT">
											<tbody>
												<tr>

													<th class="Ibx_Th_Chkbx">
														<input type="checkbox" id="oFormCheckAll"
															onclick="checkall()" title="全选/不选　本页所有邮件" />
													</th>
													<th class="Ibx_Th_From">
														<a href="#" title="点击可按此排序">文件夹名</a>
													</th>
													<th class="Ibx_Th_Date">
														<a href="#" title="点击可按此排序">大小<b
															class="icoIbx icoDown"></b>
														</a>
													</th>
													<th class="Ibx_Th_Size">
														<a href="#" title="点击可按此排序">文件数</a>
													</th>
												</tr>
											<tbody>
										</table>

										<div class="Ibx_Lst_dWp dWpOpen">
											<table class="Ibx_gTable Ibx_gTable_Con" id="oTableCOUNT0">
												<tbody>

													<logic:notPresent name="webfilebeanlistTemp">
														<tr>
															<td>
																对不起，网盘查询错误，稍候重试1
															</td>
														</tr>
													</logic:notPresent>
													<logic:present name="webfilebeanlistTemp">
														<logic:empty name="webfilebeanlistTemp">
															<tr>
																<td>
																	对不起，当前不存在文件夹！
																</td>
															</tr>
														</logic:empty>
														<logic:notEmpty name="webfilebeanlistTemp">
															<c:forEach items="${webfilebeanlistTemp}" var="t">
																<tr class="I_Mark0">

																	<td class="Ibx_Td_Chkbx">
																		<input name="mid" type="checkbox" title="选择/不选"
																			value="${t.filetype.filetypeid }" />
																	</td>

																	<td class="Ibx_Td_From">
																		<img src="images/folder.gif">
																		<a href="#"
																			onclick="javascript:changeframe('', '', 'webdisktemp.jsp?filetypeid=${t.filetype.filetypeid }'); return false;">${t.filetype.filetypename
																			} </a>
																	</td>

																	<td class="Ibx_Td_Date" title="" width="20%">
																		${t.filetype.foldersize }M
																	</td>

																	<td class="Ibx_Td_Size" width="20%">
																		<span title="5637 字节">${t.filetype.containfilecount
																			}</span>
																	</td>
																</tr>
															</c:forEach>
														</logic:notEmpty>
													</logic:present>
												<tbody>
											</table>
										</div>

									</div>
								</div>
							</div>
						</td>
					</tr>
				</table>

				<!-- 工具栏2 -->
				<div class="g-toolbar">
					<div class="btngrp">
						<a href="createfolder.jsp" class="btn btn-dft"><span>新建文件夹</span>
						</a>
					</div>
					<div class="btngrp">
						<a href="#" onclick="action1()" class="btn btn-dft btn-dft-gc"><span>删
								除</span> </a>
					</div>
					<div class="btngrp btngrp-ext">
						<a href="webdiskfolder.jsp?pageNo=1" class="txt-disabd">首页</a><a
							href="webdiskfolder.jsp?pageNo=${webFolderPageNo - 1 }"
							class="txt-disabd">上页</a><a
							href="webdiskfolder.jsp?pageNo=${webFolderPageNo + 1 }"
							class="txt-disabd">下页</a><a
							href="webdiskfolder.jsp?pageNo=${webFolderTotalPages }"
							class="txt-disabd">末页</a>
						<select name="page_no">
							<option value="1" selected>
								1/1
							</option>
						</select>
					</div>
				</div>


				<div class="ln-thin ln-c-mid"></div>
			</div>

			<div id="divSort" class="g-menu g-menu-hasico"
				style="width: 120px; display: none">
				<div class="g-menu-inner">
					<ul>
						<li>
							<a href="#"><b class='ico ico-slct ico-slct-radio'></b>时间从新到旧</a>
						</li>
						<li>
							<a href="#">时间从旧到新</a>
						</li>
						<li>
							<a href="#">文件从大到小</a>
						</li>
						<li>
							<a href="#">文件从小到大</a>
						</li>
						<li>
							<a href="#">按文件类型</a>
						</li>
						<li>
							<a href="#">按名称</a>
						</li>
					</ul>
				</div>
			</div>
		</form>
	</body>
</html>