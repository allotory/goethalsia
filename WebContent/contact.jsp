<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>收件箱</title>
		<link href="images/zh-cn/globle_v1.css" rel="stylesheet"
			type="text/css">
		<link href="images/zh-cn/inbox.css" rel="stylesheet" type="text/css">
		<link href="images/zh-cn/skin_blue.css" rel="stylesheet"
			type="text/css" id="lnkSkin" />
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
	</head>
	<%
		int groupid = Integer.parseInt(request.getParameter("groupid"));
	%>
	<c:set scope="page" value="<%=groupid%>" var="groupid"></c:set>

	<script language="javascript"> 
	function action1() 
	{ 
	   document.messageForm.action="createcontact.jsp?groupid=<%=groupid%>"; 
	   document.messageForm.submit(); 
	} 
	function action2() 
	{ 
	   document.messageForm.action="deletecontact.do?groupid=<%=groupid%>"; 
	   document.messageForm.submit(); 
	} 
	function action3() 
	{ 
	   document.messageForm.action="contactchangegroup.do?groupid=<%=groupid%>"; 
	   document.messageForm.submit(); 
	} 
</script>
	<body class="All_C_Page Inbox">
		<form name="messageForm" id="oFormMessage" method="post" action="">
			<div class="ContentWp">
				<div class="ContentThemeWp">


					<div class="gToolbar gTbrTop">
						<input type="button" class="Btn BtnNml ImpBtn" onclick="action1()"
							value="新建联系人" />
						<input type="button" class="Btn BtnNml" onclick="action2()"
							value="删除联系人" />

						<logic:notPresent name="contactBeanList">
        	查询分类错误，请稍候重试！
        </logic:notPresent>
						<logic:present name="contactBeanList">
							<logic:empty name="contactBeanList">
        		当前不存在分类！
        	</logic:empty>
							<logic:notEmpty name="contactBeanList">
								<select class="Sel" name="groupid2" onchange="action3()">
									<option value="none" selected="selected">
										复制到组
									</option>
									<c:forEach items="${contactBeanList}" var="t">
										<option value="${t.contactgroup.groupid }">
											${t.contactgroup.groupname }
										</option>
									</c:forEach>
								</select>
							</logic:notEmpty>
						</logic:present>

						<div class="Extra">
							<span class="Txt"><span class="Unable">首页</span>&nbsp;&nbsp;
								<span class="Unable">上页</span>&nbsp;&nbsp; <span class="Unable">下页</span>&nbsp;&nbsp;
								<span class="Unable">末页</span> </span>
							<select class="SelA" style="margin-right: 0px">
								<option value="1" selected>
									1/1
								</option>
							</select>
						</div>
					</div>

					<div class="Ibx_Main_Wp">
						<table id="oTableHead" class="Ibx_gTable F2Img Ibx_gTable_TT">
							<tbody>
								<tr>
									<th class="Ibx_Th_F"></th>
									<th class="Ibx_Th_Chkbx">
										<input id="oFormCheckAll" onclick="checkall()" type="checkbox"
											title="全选/不选　本页所有邮件" />
									</th>
									<th class="Ibx_Th_From" width="20%">
										<a href="#" title="点击可按此排序">联系人姓名</a>
									</th>
									<th class="Ibx_Th_Subject" width="30%">
										<a href="#" title="点击可按此排序">邮箱地址</a>
									</th>
									<th class="Ibx_Th_Size">
										<a href="#" title="点击可按此排序">操作</a>
									</th>
								</tr>
							<tbody>
						</table>
						<div class="Ibx_Lst_dWp dWpOpen">

							<table class="Ibx_gTable Ibx_gTable_Con" id="oTableCOUNT0">
								<tbody>

									<logic:notPresent name="contactBeanList">
										<tr class="I_Mark0">
											<td>
												&nbsp;对不起，查询错误，请稍候重试！
											</td>
										</tr>
									</logic:notPresent>
									<logic:present name="contactBeanList">
										<logic:empty name="contactBeanList">
											<tr class="I_Mark0">
												<td>
													&nbsp;对不起，list为空，查询错误，请稍候重试！
												</td>
											</tr>
										</logic:empty>
										<logic:notEmpty name="contactBeanList">
											<c:forEach items="${contactBeanList}" var="t">

												<c:if test="${t.contactgroup.groupid eq groupid}">

													<c:choose>
														<c:when test="${empty t.contactList}">
															<tr class="I_Mark0">
																<td>
																	&nbsp;对不起，该分组中没有联系人！
																</td>
															</tr>
														</c:when>
														<c:otherwise>
															<c:forEach items="${t.contactList}" var="c">
																<tr class="I_Mark0">
																	<td class="Ibx_Td_F">
																		&nbsp;
																	</td>
																	<td class="Ibx_Td_Chkbx">
																		<input name="mid" type="checkbox" title="选择/不选"
																			value="${c.contactid }" />
																	</td>
																	<td class="Ibx_Td_From" width="20%">
																		<a href="#">${c.contactname }</a>
																	</td>
																	<td class="Ibx_Td_Subject" width="30%">
																		<a href="#">${c.contactemail }</a>
																	</td>
																	<td class="Ibx_Td_Size">
																		<span title="5637 字节"><a
																			href="contactwrite.jsp?contactemail=${c.contactemail }">写信</a>
																			/ <a
																			href="deleteonecontact.do?groupid=${groupid }&contactid=${c.contactid }">删除</a>
																		</span>
																	</td>
																</tr>
															</c:forEach>
														</c:otherwise>
													</c:choose>
												</c:if>
											</c:forEach>
										</logic:notEmpty>
									</logic:present>
								<tbody>
							</table>
						</div>
						<h4 class="Ibx_Lst_bExtra" id="oH4Check">
							选择：
							<a href="javascript:void(0)" type="all">全部</a>&nbsp;-&nbsp;
							<a href="javascript:void(0)" type="unread">未读</a>&nbsp;-&nbsp;
							<a href="
javascript:void(0)" type="read">已读</a>&nbsp;-&nbsp;
							<a href="javascript:void(0)" type="reverse">反选</a>&nbsp;-&nbsp;
							<a href="javascript:void(0)" type="no">不选</a>
						</h4>
					</div>

					<div class="gToolbar gTbrBtm">
						<input type="button" class="Btn BtnNml ImpBtn" value="新建联系人" />
						<input type="button" class="Btn BtnNml" value="删除联系人" />
						<select class="Sel">
							<option value="none" selected="true">
								复制到组
							</option>
							<option value="read:true">
								&nbsp;&nbsp;已读邮件
							</option>
							<option value="read:false">
								&nbsp;&nbsp;未读邮件
							</option>
							<option value="none" class="SelOptLine">
								-------------
							</option>
							<option value="priority:1">
								&nbsp;&nbsp;紧急邮件
							</option>
							<option value="priority:3">
								&nbsp;&nbsp;普通邮件
							</option>
							<option value="priority:5">
								&nbsp;&nbsp;缓慢邮件
							</option>
							<option value="none" class="SelOptLine">
								-------------
							</option>
							<option value="label0:1" class="fnt_Mark1">
								&nbsp;&nbsp;重要邮件
							</option>
							<option value="label0:2" class="fnt_Mark2">
								&nbsp;&nbsp;公司邮件
							</option>
							<option value="label0:3" class="fnt_Mark3">
								&nbsp;&nbsp;业务邮件
							</option>
							<option value="label0:4" class="fnt_Mark4">
								&nbsp;&nbsp;资讯邮件
							</option>
							<option value="label0:5" class="fnt_Mark5">
								&nbsp;&nbsp;亲友邮件
							</option>
							<option value="label0:6" class="fnt_Mark6">
								&nbsp;&nbsp;同学邮件
							</option>
							<option value="label0:7" class="fnt_Mark7">
								&nbsp;&nbsp;休闲邮件
							</option>
							<option value="label0:8" class="fnt_Mark8">
								&nbsp;&nbsp;趣闻邮件
							</option>
							<option value="label0:15" class="fnt_Mark15">
								&nbsp;&nbsp;杂项邮件
							</option>
							<option value="none" class="SelOptLine">
								-------------
							</option>
							<option value="label0:0">
								&nbsp;&nbsp;取消标签
							</option>
						</select>
						<div class="Extra">
							<span class="Txt"><span class="Unable">首页</span>&nbsp;&nbsp;
								<span class="Unable">上页</span>&nbsp;&nbsp; <span class="Unable">下页</span>&nbsp;&nbsp;
								<span class="Unable">末页</span> </span>
							<select action="page" class="SelA" style="margin-right: 0px">
								<option value="1" selected>
									1/1
								</option>
							</select>
						</div>
					</div>

				</div>
			</div>
		</form>
	</body>
</html>
