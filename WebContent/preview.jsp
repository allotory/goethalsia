<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030" import="java.util.*"%>
<%@page import="com.wellmail.bean.EmailBean"%>
<%@page import="com.wellmail.model.*"%>
<%@page import="java.sql.*"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>mymail邮件登录系统演示</title>
		<link href="images/zh-cn/globle_v1.css" rel="stylesheet"
			type="text/css">
		<link href="images/zh-cn/read.css" rel="stylesheet" type="text/css">
		<link href="images/zh-cn/skin_blue.css" rel="stylesheet"
			type="text/css" id="lnkSkin" />

		<style>
pre {
	white-space: pre-wrap;
	white-space: -moz-pre-wrap;
	white-space: -pre-wrap;
	white-space: -o-pre-wrap;
	word-wrap: break-word;
	font-family: '';
}

.rm_line {
	border-top: 2px solid #F1F1F1;
	font-size: 0;
	margin: 15px 0
}

.atchImg img {
	border: 2px solid #c3d9ff;
}

.lnkTxt {
	color: #0066CC;
	font-size: 12px
}

.rm_PicArea * {
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: 700;
}

.fbk3 {
	color: #333;
	line-height: 160%
}

.fTip {
	font-size: 11px;
	font-weight: normal
}
</style>

	</head>
	<body class="All_C_Page read">
		<form name="messageForm" method="post">
			<div class="ContentWp">
				<div class="ContentThemeWp">
					<div class="gToolbar gTbrTop">
						<input type="button" value="返 回" onclick="history.back()"
							class="Btn BtnNml ImpBtn"
							onMouseDown="this.className='Btn BtnHv BtnDw ImpBtn'"
							hidefocus="ture" onMouseOver="this.className='Btn BtnHv ImpBtn';"
							onMouseOut="this.className='Btn BtnNml ImpBtn';">
						<div class="Extra">
							[
							<a href="#">上一封</a>]
						</div>
					</div>

					<%
						String username = request.getParameter("username");
						String cc = request.getParameter("cc");
						if (cc == null || cc == "") {
							cc = "";
						}

						String bcc = request.getParameter("bcc");
						if (bcc == null || bcc == "") {
							bcc = "";
						}

						String recipients = request.getParameter("recipients");
						if (recipients == null || recipients == "") {
							recipients = "";
						}

						String qf = request.getParameter("qf");
						if (qf == null || qf == "") {
							qf = "";
						}
						String subject = request.getParameter("subject");
						if (subject == null || subject == "") {
							subject = "";
						} else {
							subject = new String(subject.getBytes("ISO8859-1"), "GB18030");
						}

						String content = request.getParameter("content");
						if (content == null || content == "") {
							content = "";
						} else {
							content = new String(content.getBytes("ISO8859-1"), "GB18030");
						}
						String attach[] = request.getParameterValues("attach");
					%>


					<div class="gCBgWp">
						<div class="Read_T">
							<table class="rTable">
								<tr>
									<th>
										主 题：
									</th>
									<td>
										<b class="Read_f1"><%=subject%></b>&nbsp; &nbsp;[
										<a href="javascript:void(0)" title="">举报垃圾邮件</a>]
									</td>
								</tr>
								<tr>
									<th>
										发件人：
									</th>
									<td>
										<b>"焜安信息"</b>&nbsp;&lt;
										<a href="#" class="Read_a" title="给他写信"><%=username%></a>&gt;
										&nbsp;&nbsp;[
										<a href="#" title="添加到通讯录">添加到通讯录</a>] &nbsp;[
										<a href="#" title="">邮件往来</a>] &nbsp;[
										<a href="javascript:void(0)" title="拒收">拒收</a>]
									</td>
								</tr>
								<tr>
									<th>
										收件人：
									</th>
									<td>
										&lt;<%=recipients%>&gt;
									</td>
								</tr>

								<%
									if (qf != "") {
								%>
								<tr>
									<th>
										群发人：
									</th>
									<td>
										&lt;<%=qf%>&gt;
									</td>
								</tr>
								<%
									}
								%>

								<%
									if (cc != "") {
								%>
								<tr>
									<th>
										抄送人：
									</th>
									<td>
										&lt;<%=cc%>&gt;
									</td>
								</tr>
								<%
									}
								%>


								<%
									if (bcc != "") {
								%>
								<tr>
									<th>
										抄送人：
									</th>
									<td>
										&lt;<%=bcc%>&gt;
									</td>
								</tr>
								<%
									}
								%>

								<%
									if (attach != null) {
										if (attach.length > 0) {
								%>

								<tr>
									<th>
										附 件：
									</th>
									<td>
										<%
											for (int i = 0; i < attach.length; i++) {
														out.println(attach + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
													}
										%>
									</td>
								</tr>
								<%
									}
									}
								%>

							</table>
						</div>
					</div>
					<div class="Read_D">
						<div class="Read_see" style="margin: 0px; width: 100%;">
							<center>
								<table width="95%" height="250" border="0" cellpadding="0"
									cellspacing="0">
									<tr>
										<td align="left" valign="top">
											<div>
												<%=content%>
											</div>
										</td>
									</tr>
								</table>
							</center>
						</div>
						<div class="Hr">
							<hr />
						</div>

						<div class="gToolbar gTbrBtm">
							<input type="button" value="返 回"
								onClick="location.href='box.html';" class="Btn BtnNml ImpBtn"
								onMouseDown="this.className='Btn BtnHv BtnDw ImpBtn'"
								onMouseOver="this.className='Btn BtnHv ImpBtn';"
								onMouseOut="this.className='Btn BtnNml ImpBtn';">
							<div class="Extra">
								[
								<a href="#">上一封</a>]
							</div>

						</div>
					</div>
				</div>
		</form>
	</body>
</html>
