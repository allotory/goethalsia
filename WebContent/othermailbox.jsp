<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>其他邮箱</title>
		<link href="images/zh-cn/globle_v1.css" rel="stylesheet"
			type="text/css">
		<link href="images/zh-cn/inbox.css" rel="stylesheet" type="text/css">
		<link href="images/zh-cn/skin_blue.css" rel="stylesheet"
			type="text/css" id="lnkSkin" />
	</head>

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
	   document.messageForm.action="addothermailbox2.do"; 
	   document.messageForm.submit(); 
	} 
	function action2() 
	{ if(confirm("是否确认删除?（删除不能恢复！）")){
		   document.messageForm.action="deleteothermailbox.do"; 
		   document.messageForm.submit();
		}
	}
	
	
	function changepage(s){
		
	   document.messageForm.action="goothermailbox2.do?pageNo="+s; 
	   document.messageForm.submit();
	}
	
</script>
	<body class="All_C_Page Inbox">
		<form name="messageForm" id="oFormMessage" method="post">
			<div class="ContentWp">
				<div class="ContentThemeWp">
					<div class="gTitle">
						<b class="mTT"> </b> &nbsp;&nbsp;(共&nbsp;
						<b id="oTotal">${fn:length(otherMailBoxBeanList)}</b>&nbsp;个)
					</div>
					<div class="gToolbar gTbrTop">
						<input type="button" onclick="action2()" class="Btn BtnNml ImpBtn"
							value="删 除" />
						<input type="button" onclick="action1()" class="Btn BtnNml ImpBtn"
							value="添 加" />
						<div class="Extra">
							<span class="Txt"><span class="Unable"><a
									href="goothermailbox2.do?pageNo=1">首页</a>
							</span>&nbsp;&nbsp; <span class="Unable"><a
									href="goothermailbox2.do?pageNo=${othermailPageNo - 1 }">上页</a>
							</span> &nbsp;&nbsp; <span class="Unable"> <a
									href="goothermailbox2.do?pageNo=${othermailPageNo + 1 }">下页</a>
							</span>&nbsp;&nbsp; <span class="Unable"><a
									href="goothermailbox2.do?pageNo=${othermailTotalPages }">末页</a>
							</span> </span>
							<select class="SelA" style="margin-right: 0px" name="pageNo"
								onchange="changepage(this.value)">
								<c:forEach var="t" begin="1" end="${othermailTotalPages}">
									<option value="${t }">
										${t }/${othermailTotalPages }
									</option>
								</c:forEach>
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
									<th class="Ibx_Th_From" width="40%">
										<a href="#" title="点击可按此排序">用户名</a>
									</th>
									<th class="Ibx_Th_Subject">
										<a href="#" title="点击可按此排序">邮箱类型</a>
									</th>
									<th class="Ibx_Th_Date">
										<a href="#" title="点击可按此排序">操作<b class="icoIbx icoDown"></b>
										</a>
									</th>
								</tr>
							<tbody>
						</table>
						<div class="Ibx_Lst_dWp dWpOpen">
							<h4 class="Ibx_Lst_dTT">
								<a href="javascript:void(0)" title="展开/隐藏" class="icoIbx"></a><span
									class="Txt">日期：今天&nbsp;<span style="display: none">(<a
										href="javascript:void(0)" title="
选中该组所有邮件"
										key="oTableCOUNT0"><span id="oSpanCOUNT0">${fn:length(otherMailBoxBeanList)}</span>个</a>)</span>
								</span>
							</h4>
							<table class="Ibx_gTable Ibx_gTable_Con" id="oTableCOUNT0">
								<tbody>

									<logic:notPresent name="otherMailBoxBeanList">
										<tr class="I_Mark0">
											<td>
												对不起，查询错误，请稍候重试！
											</td>
										</tr>
									</logic:notPresent>
									<logic:present name="otherMailBoxBeanList">
										<logic:empty name="otherMailBoxBeanList">
											<tr class="I_Mark0">
												<td>
													对不起，当前没有其他邮箱！
												</td>
											</tr>
										</logic:empty>
										<logic:notEmpty name="otherMailBoxBeanList">
											<c:forEach items="${otherMailBoxBeanList}" var="t">
												<script type="text/javascript">
							            				function action3() 
														{ 
															if(confirm("是否确认删除?（删除不能恢复！）")){
															   document.messageForm.action="deleteoneotherreceive.do?uid=${t.othermailbox.uid }"; 
															   document.messageForm.submit();
															 }
														}
							            			</script>
												<tr class="I_Mark0">
													<td class="Ibx_Td_F">
														&nbsp;
													</td>
													<td class="Ibx_Td_Chkbx">
														<input name="mid" type="checkbox" title="选择/不选"
															value="${t.othermailbox.uid }" />
													</td>
													<td class="Ibx_Td_From" width="40%">
														<a href="gootherreceive.do?uid=${t.othermailbox.uid }">${t.othermailbox.uname
															}</a>
													</td>
													<td class="Ibx_Td_Subject">
														<a href="gootherreceive.do?uid=${t.othermailbox.uid }">${t.othermailboxtype.tname
															}</a>
													</td>
													<td class="Ibx_Td_Date" title="">
														<a href="#" onclick="action3()">删除</a>
													</td>
												</tr>
											</c:forEach>
										</logic:notEmpty>
									</logic:present>
								<tbody>
							</table>
						</div>
					</div>

					<div class="gToolbar gTbrBtm">

						<input type="button" onclick="action1()" class="Btn BtnNml ImpBtn"
							value="删 除" />
						<input type="button" onclick="action1()" class="Btn BtnNml ImpBtn"
							value="添 加" />
						<div class="Extra">
							<span class="Txt"><span class="Unable"><a
									href="goothermailbox2.do?pageNo=1">首页</a>
							</span>&nbsp;&nbsp; <span class="Unable"><a
									href="goothermailbox2.do?pageNo=${othermailPageNo - 1 }">上页</a>
							</span> &nbsp;&nbsp; <span class="Unable"> <a
									href="goothermailbox2.do?pageNo=${othermailPageNo + 1 }">下页</a>
							</span>&nbsp;&nbsp; <span class="Unable"><a
									href="goothermailbox2.do?pageNo=${othermailTotalPages }">末页</a>
							</span> </span>
							<select class="SelA" style="margin-right: 0px" name="pageNo"
								onchange="changepage(this.value)">
								<c:forEach var="t" begin="1" end="${othermailTotalPages}">
									<option value="${t }">
										${t }/${othermailTotalPages }
									</option>
								</c:forEach>
							</select>
						</div>
					</div>

				</div>
			</div>
		</form>
	</body>
</html>
