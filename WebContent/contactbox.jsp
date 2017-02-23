<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Free Menu Designs - e-lusion.com</title>
		<meta http-equiv="Content-Type" content="text/html; charset=Gb18030" />
		<style type="text/css">
body {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	margin: 0;
	font-size: 80%;
	font-weight: bold;
	background: white;
}

a:link {
	color: #76B0D5;
	text-decoration: none;
}

a:visited {
	color: #7B878F;
	text-decoration: none;
}

a:hover,a:active {
	color: #2399E5;
}

#header {
	color: #76B0D5;
	background: white;
	border-bottom: 3px solid #76B0D5;
	font-family: Georgia, "Times New Roman", Times, serif;
	font-size: 2em;
	padding: 10px;
}

#content {
	margin: 10px;
	color: #555;
}

ul {
	list-style: none;
	margin: 0;
	padding: 0;
}

p {
	font-size: .9em;
}

#firstcol {
	float: left;
}

/* =-=-=-=-=-=-=-[Menu Three]-=-=-=-=-=-=-=- */
#menu3 {
	width: 200px;
	border: 1px solid #ccc;
	margin: 10px;
}

#menu3 li a {
	height: 32px;
	voice-family: "\"}\"";
	voice-family: inherit;
	height: 24px;
	text-decoration: none;
}

#menu3 li a:link,#menu3 li a:visited {
	color: #888;
	display: block;
	background: url(images/menu3.gif);
	padding: 8px 0 0 30px;
}

#menu3 li a:hover,#menu3 li a:active {
	color: #283A50;
	background: url(images/menu3.gif) 0 -32px;
	padding: 8px 0 0 30px;
}

#aaa {
	width: 3px;
	height: 440px;
	background: #76B0D5;
}
</style>

	</head>

	<body>
		<div id="header">
			联系人
		</div>
		<table>
			<tr valign="top">
				<td>
					<table
						style="margin-left: 15px; margin-right: 9px; margin-top: 8px">
						<logic:notPresent name="contactBeanList">
							<tr style="padding-bottom: 5px;">
								<td>
									对不起，通讯录查询错误，
									<br />
									请稍候重试！
								</td>
							</tr>
						</logic:notPresent>
						<logic:present name="contactBeanList">
							<logic:empty name="contactBeanList">
								<tr style="padding-bottom: 5px;">
									<td>
										对不起，通讯录组为空，
										<br />
										请稍候重试！
									</td>
								</tr>
							</logic:empty>
							<logic:notEmpty name="contactBeanList">
								<tr style="padding-bottom: 5px;">
									<td>
										<img src="images/direct_blue.gif">
										<a href="contact.jsp" target="a">全部联系人[${fn:length(contactList)}]</a>
									</td>
								</tr>
								<c:forEach items="${contactBeanList}" var="t">
									<tr style="padding-bottom: 5px;">
										<td>
											<img src="images/direct_blue.gif">
											<a href="contact.jsp?groupid=${t.contactgroup.groupid }"
												target="a">${t.contactgroup.groupname
												}[${t.contactgroup.containusercount }人]</a>
										</td>
									</tr>
								</c:forEach>
							</logic:notEmpty>
						</logic:present>
						<tr style="padding-bottom: 5px;">
							<td>
								<font color="white">aa</font>
							</td>
						</tr>
						<tr style="padding-bottom: 5px;">
							<td>
								<font color="white">aa</font>
							</td>
						</tr>
						<tr style="padding-bottom: 5px;">
							<td>
								<img src="images/direct_blue.gif">
								<a href="createcontactgroup.jsp">添加联系人组</a>
							</td>
						</tr>
						<tr style="padding-bottom: 5px;">
							<td>
								<img src="images/direct_blue.gif">
								<a href="contactgroup.jsp">查看联系人组</a>
							</td>
						</tr>
					</table>
				</td>

				<td>
					<div id="aaa"></div>
				</td>


				<td valign="top" align="center">
					<table>
						<tr align="left">
							<td>
								&nbsp;&nbsp;&nbsp;通讯录[
								<a href="contactindex.jsp" target="a">首页</a>]
							</td>
						</tr>
						<tr align="center">
							<td>
								<iframe name="a" src="contactindex.jsp" frameborder="0"
									marginwidth="0" marginheight="0" width="850px" height="400px"></iframe>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
