<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<link rel="stylesheet" href="css/common.css" type="text/css" />
		<title>通用后台管理系统</title>
	</head>

	<body>
		<div class="header_content">
			<div class="logo">
				<img src="images/man_logo.jpg" alt="51EHUO-无忧易货网后台管理" />
			</div>
			<div class="right_nav">
				<div class="text_left">
					<ul class="nav_list">
						<li>
							<img src="images/direct.gif" width="8" height="21" />
							<b><font color="highlight">积分: </font> <font color="red">
									<logic:present name="userscore">
										<c:out value="${userscore.score}" />
									</logic:present> </font> <font color="highlight">分&nbsp;&nbsp;&nbsp;&nbsp;等级：</font> <font
								color="red"> <logic:present name="userscore">
										<c:out value="${userscore.level}" />
									</logic:present> </font> <font color="highlight">级</font>
							</b>
						</li>
					</ul>
				</div>
				<div class="text_right">
					<ul class="nav_return">
						<li>
							<img src="images/return.gif" width="13" height="21" />
							&nbsp;系统操作 
						
						</li>
						<li>
							[
							<a href="modifyuser.jsp" target="all">修改密码</a>]
						</li>
						<li>
							[
							<a href="logout.do">退出</a>]
						</li>
						<li>
							[
							<a href="about.html" target="all">关于</a>]&nbsp;&nbsp;
						</li>
					</ul>
				</div>
			</div>
		</div>
	</body>
</html>