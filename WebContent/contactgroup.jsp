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

		<script language="javascript"> 
	function action1() 
	{ 
	   document.messageForm.action="createcontactgroup.jsp"; 
	   document.messageForm.submit(); 
	} 
	function action2() 
	{ 
	   document.messageForm.action="deletecontactgroup.do"; 
	   document.messageForm.submit(); 
	} 
</script>
	</head>

	<body class="All_C_Page Inbox">
		<form name="messageForm" id="oFormMessage" method="post" action="">
			<div class="ContentWp">
				<div class="ContentThemeWp">


					<div class="gToolbar gTbrTop">
						<input type="button" class="Btn BtnNml ImpBtn" onclick="action1()"
							value="新建联系人组" />
						<input type="button" class="Btn BtnNml" onclick="action2()"
							value="删除联系人组" />
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
										<input id="oFormCheckAll" type="checkbox" title="全选/不选　本页所有邮件" />
									</th>
									<th class="Ibx_Th_From">
										<a href="#" title="点击可按此排序">联系组名</a>
									</th>
									<th class="Ibx_Th_Subject" width="50%">
										<a href="#" title="点击可按此排序">人数</a>
									</th>
									<th class="Ibx_Th_Size">
										<a href="#" title="点击可按此排序">操作</a>
									</th>
								</tr>
							<tbody>
						</table>
						<div class="Ibx_Lst_dWp dWpOpen">

							<table class="Ibx_gTable Ibx_gTable_Con" id="oTableCOUNT0"
								action="list">
								<tbody>
									<c:forEach items="${contactBeanList}" var="t">
										<tr class="I_Mark0">
											<td class="Ibx_Td_F">
												&nbsp;
											</td>
											<td class="Ibx_Td_Chkbx">
												<input name="mid" type="checkbox" title="选择/不选"
													value="${t.contactgroup.groupid }" />
											</td>
											<td class="Ibx_Td_From">
												<a href="">${t.contactgroup.groupname }</a>
											</td>
											<td class="Ibx_Td_Subject" width="50%">
												<a href="mail.html">${t.contactgroup.containusercount }</a>
											</td>
											<td class="Ibx_Td_Size">
												<span title="5637 字节"><a
													href="deleteonecontactgroup.do?groupid=${t.contactgroup.groupid }">删除</a>
												</span>
											</td>
										</tr>
									</c:forEach>
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
