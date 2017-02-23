<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030" import="java.util.*"%>
<%@ page import="com.wellmail.model.*"%>
<%@ page import="com.wellmail.bean.NoteBookBean;"%>
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
			int noteid = Integer.parseInt(request.getParameter("noteid"));

			List<NoteBookBean> noteBookBeanList = (List) request.getSession()
					.getAttribute("noteBookBeanList");

			NoteType notetype = null;
			NoteBook notebook = null;

			for (Iterator<NoteBookBean> i = noteBookBeanList.iterator(); i
					.hasNext();) {

				NoteBookBean nbb = i.next();

				notetype = nbb.getNotetype();
				notebook = nbb.getNotebook();

				if (notebook.getNoteid() == noteid) {
					break;
				}

			}
		%>
		<script language="javascript"> 
	function action1() 
	{ 
	   document.messageForm.action="notemodify.jsp?noteid=<%=notebook.getNoteid()%>"; 
	   document.messageForm.submit(); 
	} 
	
	function action2() 
	{ 
	   document.messageForm.action="deleteonenote.do?noteid=<%=notebook.getNoteid()%>"; 
	   document.messageForm.submit(); 
	} 
	
	function action3() 
	{ 
	  document.messageForm.action="onenotechangtype.do?noteid=<%=notebook.getNoteid()%>"; 
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

						<input type="button" class="Btn BtnNml" onclick="action1()"
							value="编辑" onclick="action1()"
							onMouseDown="this.className='Btn BtnHv BtnDw'"
							onMouseOver="this.className='Btn BtnHv';"
							onMouseOut="this.className='Btn BtnNml';" name="reply" />
						<input type="button" class="Btn BtnNml" onclick="action2()"
							value="删除" onMouseDown="this.className='Btn BtnHv BtnDw'"
							onMouseOver="this.className='Btn BtnHv';"
							onMouseOut="this.className='Btn BtnNml';" name="replyall" />
						<logic:notPresent name="noteTypeList">
		  		分类查询错误，请稍候重试！
		</logic:notPresent>
						<logic:present name="noteTypeList">
							<logic:empty name="noteTypeList">
	  			当前没有分类！
	  		</logic:empty>
							<logic:notEmpty name="noteTypeList">
								<select name="notetypeid" class="Sel" onchange="action3()">
									<option>
										更改分类到...
									</option>
									<c:forEach items="${noteTypeList}" var="t">
										<option value="${t.notetypeid }">
											${t.notetypename }
										</option>
									</c:forEach>
								</select>
							</logic:notEmpty>
						</logic:present>
					</div>
					<div class="gCBgWp">
						<div class="Read_T">
							<table class="rTable">
								<tr>
									<th>
										主 题：
									</th>
									<td><%=notebook.getNotetitle()%></td>
								</tr>
								<tr>
									<th>
										分 类：
									</th>
									<td><%=notetype.getNotetypename()%></td>
								</tr>
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
												<%=notebook.getNotecontent()%>
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
							<input type="button" value="返 回" onclick="history.back()"
								class="Btn BtnNml ImpBtn"
								onMouseDown="this.className='Btn BtnHv BtnDw ImpBtn'"
								hidefocus="ture"
								onMouseOver="this.className='Btn BtnHv ImpBtn';"
								onMouseOut="this.className='Btn BtnNml ImpBtn';">

							<input type="button" class="Btn BtnNml" value="编辑"
								onMouseDown="this.className='Btn BtnHv BtnDw'"
								onMouseOver="this.className='Btn BtnHv';"
								onMouseOut="this.className='Btn BtnNml';" name="reply" />
							<input type="button" class="Btn BtnNml" value="删除"
								onMouseDown="this.className='Btn BtnHv BtnDw'"
								onMouseOver="this.className='Btn BtnHv';"
								onMouseOut="this.className='Btn BtnNml';" name="replyall" />
							<logic:notPresent name="noteTypeList">
		  		分类查询错误，请稍候重试！
		</logic:notPresent>
							<logic:present name="noteTypeList">
								<logic:empty name="noteTypeList">
	  			当前没有分类！
	  		</logic:empty>
								<logic:notEmpty name="noteTypeList">
									<select name="notetypeid" class="Sel" onchange="action3()">
										<option>
											更改分类到...
										</option>
										<c:forEach items="${noteTypeList}" var="t">
											<option value="${t.notetypeid }">
												${t.notetypename }
											</option>
										</c:forEach>
									</select>
								</logic:notEmpty>
							</logic:present>

						</div>
					</div>
				</div>
		</form>
	</body>
</html>
