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
		<%
			//查询email相关信息
			int emailid = Integer.parseInt(request.getParameter("emailid"));
			List<EmailBean> emailbeanList = (List<EmailBean>) request
					.getSession().getAttribute("emailBeanList");

			Email email = new Email();
			List<Attachment> attachmentList = new ArrayList<Attachment>();
			boolean containAttachment = false;
			List<CC> ccList = new ArrayList<CC>();
			List<BCC> bccList = new ArrayList<BCC>();

			for (Iterator<EmailBean> i = emailbeanList.iterator(); i.hasNext();) {
				EmailBean eb = i.next();
				if (eb.getEmail().getEmailid() == emailid) {
					email = eb.getEmail();
					attachmentList = eb.getAttachmentList();
					containAttachment = eb.isContainAttachment();
					ccList = eb.getCcList();
					bccList = eb.getBccList();
				}
			}

			//查询contact相关信息

			List<Contact> contactList = (List<Contact>) request.getSession()
					.getAttribute("contactList");

			Contact contact2 = null;

			if (contactList != null) {

				for (Iterator<Contact> i = contactList.iterator(); i.hasNext();) {

					Contact contact = i.next();

					if (contact.getContactemail().equals(email.getSender())) {

						contact2 = contact;
						break;
					}
				}
			}

			//更改已读，未读标签

			Connection conn = null;
			Statement stmt = null;

			// 连接数据库   
			String driverName = "com.mysql.jdbc.Driver";
			String dbURL = "jdbc:mysql://localhost/wellmail?autoReconnect=true";
			String userName = "root";
			String userPwd = "root";

			try {
				Class.forName(driverName);
				conn = DriverManager.getConnection(dbURL, userName, userPwd);

				stmt = conn.createStatement();
				String sql = "update email set unread=0 where emailid="
						+ emailid;
				stmt.executeUpdate(sql);

				//System.out.println("邮件插入成功"); 
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
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
		<script language="javascript"> 
	function action1() 
	{ 
	   document.messageForm.action="receivemaildeleting.do"; 
	   document.messageForm.submit(); 
	} 
	function action2() 
	{ 
	   document.messageForm.action="savescript.do"; 
	   document.messageForm.submit(); 
	} 
	function action3() 
	{ 
	   document.messageForm.action="reply.jsp?emailid=<%=emailid%>"; 
	   document.messageForm.submit(); 
	} 
	function action4() 
	{ 
	   document.messageForm.action="transmit.jsp?emailid=<%=emailid%>"; 
	   document.messageForm.submit(); 
	}
</script>

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

						<input type="button" class="Btn BtnNml" onclick="action3()"
							value="回 复" onMouseDown="this.className='Btn BtnHv BtnDw'"
							onMouseOver="this.className='Btn BtnHv';"
							onMouseOut="this.className='Btn BtnNml';" name="reply" />
						<input type="button" class="Btn BtnNml" onclick="action4()"
							value="转 发" onMouseDown="this.className='Btn BtnHv BtnDw'"
							onMouseOver="this.className='Btn BtnHv';"
							onMouseOut="this.className='Btn BtnNml';" name="forward" />
						<input type="button" onclick="action1()" class="Btn BtnNml"
							value="删　除" onMouseDown="this.className='Btn BtnHv BtnDw'"
							onMouseOver="this.className='Btn BtnHv';"
							onMouseOut="this.className='Btn BtnNml';" name="delete" />
						<div class="Extra">
							[
							<a href="#">上一封</a>]
						</div>
					</div>
					<logic:notPresent name="emailBeanList" scope="session">
						<tr class="I_Mark0">
							<td align="center">
								对不起，当前没有邮件！
							</td>
						</tr>
					</logic:notPresent>
					<logic:present name="emailBeanList" scope="session">

						<logic:empty name="emailBeanList">
							<tr class="I_Mark0">
								<td align="center">
									对不起，查询错误，List为空！
								</td>
							</tr>
						</logic:empty>
						<logic:notEmpty name="emailBeanList">

							<div class="gCBgWp">
								<div class="Read_T">
									<input type="hidden" name="emailid"
										value="<%=email.getEmailid()%>">
									<table class="rTable">
										<tr>
											<th>
												主 题：
											</th>
											<td>
												<b class="Read_f1"><%=email.getSubject()%></b>&nbsp; &nbsp;
											</td>
										</tr>
										<%
											if (contact2 != null) {
										%>
										<tr>
											<th>
												发件人：
											</th>
											<td>
												<b>"<%=contact2.getContactname()%>"</b>&nbsp;&lt;
												<a href="#" class="Read_a" title="给他写信"><%=email.getSender()%></a>&gt;
												&nbsp;&nbsp;
											</td>
										</tr>
										<%
											} else {
										%>
										<tr>
											<th>
												发件人：
											</th>
											<td>
												&lt;
												<a href="#" class="Read_a" title="给他写信"><%=email.getSender()%></a>&gt;
												&nbsp;&nbsp;[
												<a
													href="addtocontact.do?contactemail=<%=email.getSender()%>"
													title="添加到通讯录">添加到通讯录</a>] &nbsp;
											</td>
										</tr>
										<%
											}
										%>
										<tr>
											<th>
												收件人：
											</th>
											<td>
												&lt;<%=email.getRecipients()%>&gt;
											</td>
										</tr>

										<%
											if (ccList != null && ccList.size() > 0) {
										%>
										<tr>
											<th>
												抄送人：
											</th>
											<td>
												<%
													for (Iterator<CC> i = ccList.iterator(); i.hasNext();) {
																	CC cc = i.next();
												%>
												&lt;<%=cc.getCcname()%>&gt;
												<%
													}
												%>
											</td>
										</tr>
										<%
											}
										%>


										<%
											if (bccList != null && bccList.size() > 0) {
										%>
										<tr>
											<th>
												抄送人：
											</th>
											<td>
												<%
													for (Iterator<BCC> i = bccList.iterator(); i.hasNext();) {
																	BCC bcc = i.next();
												%>
												&lt;<%=bcc.getBccname()%>&gt;
												<%
													}
												%>
											</td>
										</tr>
										<%
											}
										%>

										<tr>
											<th>
												日 期：
											</th>
											<td><%=email.getSenttime()%></td>
										</tr>

										<%
											if (containAttachment) {
										%>

										<tr>
											<th>
												附 件：
											</th>
											<td>
												<%
													for (Iterator<Attachment> it = attachmentList
																		.iterator(); it.hasNext();) {
																	Attachment attachment = it.next();
																	out
																			.println(attachment.getAttachmentname()
																					+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
																					+ attachment.getAttachmentsize()
																					* 1024
																					+ "KB &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='attachDownload.jsp?attachmentid="
																					+ attachment.getAttachmentid()
																					+ "'>下载</a>");
																}
												%>
											</td>
										</tr>
										<%
											}
										%>

									</table>
								</div>
							</div>
							<div class="Read_D">
								<div class="Read_see" style="margin: 0px; width: 100%;">
									<!--
					        <iframe name="mail_content" id="mail_content" width="100%" height="300" name="" src="mailcontent.jsp" frameborder=0>
					        </iframe>
					      -->
									<center>
										<table width="95%" height="250" border="0" cellpadding="0"
											cellspacing="0">
											<tr>
												<td align="left" valign="top">
													<div>
														<%=email.getContent()%>
													</div>
												</td>
											</tr>
										</table>
									</center>
								</div>
								<div class="Hr">
									<hr />
								</div>
						</logic:notEmpty>
					</logic:present>

					<div class="gToolbar gTbrBtm">
						<input type="button" value="返 回" onclick="history.back()"
							class="Btn BtnNml ImpBtn"
							onMouseDown="this.className='Btn BtnHv BtnDw ImpBtn'"
							hidefocus="ture" onMouseOver="this.className='Btn BtnHv ImpBtn';"
							onMouseOut="this.className='Btn BtnNml ImpBtn';">

						<input type="button" class="Btn BtnNml" onclick="action3()"
							value="回 复" onMouseDown="this.className='Btn BtnHv BtnDw'"
							onMouseOver="this.className='Btn BtnHv';"
							onMouseOut="this.className='Btn BtnNml';" name="reply" />
						<input type="button" class="Btn BtnNml" onclick="action4()"
							value="转 发" onMouseDown="this.className='Btn BtnHv BtnDw'"
							onMouseOver="this.className='Btn BtnHv';"
							onMouseOut="this.className='Btn BtnNml';" name="forward" />
						<input type="button" onclick="action1()" class="Btn BtnNml"
							value="删　除" onMouseDown="this.className='Btn BtnHv BtnDw'"
							onMouseOver="this.className='Btn BtnHv';"
							onMouseOut="this.className='Btn BtnNml';" name="delete" />
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
