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
		<title>收件箱</title>
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
		var n = 0; 
		var objs = document.getElementsByName("mid"); 
		for(var i=0;i <objs.length;i++)  
		{ 
		if(objs[i].checked) 
			n=n+1; 
		} 
		if( n == 0 ) {
			alert("至少选中一个");
		}else { 
		   document.messageForm.action="clearscriptmail.do"; 
		   document.messageForm.submit(); 
	   }
	} 	
	
	function action2() 
	{ 
		var n = 0; 
		var objs = document.getElementsByName("mid"); 
		for(var i=0;i <objs.length;i++)  
		{ 
		if(objs[i].checked) 
			n=n+1; 
		} 
		if( n == 0 ) {
			alert("至少选中一个");
		}else { 
		   document.messageForm.action="scriptchangeFlag.do"; 
		   document.messageForm.submit();
		 }
	 
	}
	function action3() 
	{ 
		var n = 0; 
		var objs = document.getElementsByName("mid"); 
		for(var i=0;i <objs.length;i++)  
		{ 
		if(objs[i].checked) 
			n=n+1; 
		} 
		if( n == 0 ) {
			alert("至少选中一个");
		}else { 
		   document.messageForm.action="scriptmoveto.do"; 
		   document.messageForm.submit();
		}
	}
	function action4() 
	{ 
	   document.messageForm.action="scriptqueryfrom.do"; 
	   document.messageForm.submit();
	}
	
	function changepage(s){
		
	   document.messageForm.action="goscript2.do?pageNo="+s; 
	   document.messageForm.submit();
	}
</script>
	<body class="All_C_Page Inbox">
		<form name="messageForm" id="oFormMessage" method="post">
			<div class="ContentWp">
				<div class="ContentThemeWp">
					<div class="gTitle">
						<b class="mTT"> </b> &nbsp;&nbsp;(共&nbsp;
						<b id="oTotal">${fn:length(scriptEmailBeanList)}</b>&nbsp;封)
					</div>

					<div class="gToolbar gTbrTop">
						<input type="button" onclick="action1()" class="Btn BtnNml ImpBtn"
							value="删 除" />
						<select class="Sel" onchange="action2()" name="changeflag">
							<option value="none" selected="selected">
								标记为...
							</option>
							<option value="readtrue" onclick="action2()">
								&nbsp;&nbsp;已读邮件
							</option>
							<option value="readfalse" onclick="action2()">
								&nbsp;&nbsp;未读邮件
							</option>
							<option value="none" class="SelOptLine">
								-------------
							</option>
							<option value="urgent" onclick="action2()">
								&nbsp;&nbsp;紧急邮件
							</option>
							<option value="commonpriority" onclick="action2()">
								&nbsp;&nbsp;普通邮件
							</option>
							<option value="slow" onclick="action2()">
								&nbsp;&nbsp;缓慢邮件
							</option>
							<option value="none" class="SelOptLine">
								-------------
							</option>
							<option value="important" class="fnt_Mark1" onclick="action2()">
								&nbsp;&nbsp;重要邮件
							</option>
							<option value="company" class="fnt_Mark2" onclick="action2()">
								&nbsp;&nbsp;公司邮件
							</option>
							<option value="business" class="fnt_Mark3" onclick="action2()">
								&nbsp;&nbsp;业务邮件
							</option>
							<option value="information" class="fnt_Mark4" onclick="action2()">
								&nbsp;&nbsp;资讯邮件
							</option>
							<option value="family" class="fnt_Mark5" onclick="action2()">
								&nbsp;&nbsp;亲友邮件
							</option>
							<option value="student" class="fnt_Mark6" onclick="action2()">
								&nbsp;&nbsp;同学邮件
							</option>
							<option value="leisure" class="fnt_Mark7" onclick="action2()">
								&nbsp;&nbsp;休闲邮件
							</option>
							<option value="intresting" class="fnt_Mark8" onclick="action2()">
								&nbsp;&nbsp;趣闻邮件
							</option>
							<option value="commontag" class="fnt_Mark15" onclick="action2()">
								&nbsp;&nbsp;普通邮件
							</option>
							<option value="none" class="SelOptLine">
								-------------
							</option>
							<option value="cancel" onclick="action2()">
								&nbsp;&nbsp;取消标签
							</option>
						</select>
						<select class="Sel" name="moveto" onchange="action3()">
							<option value="none" selected="selected">
								移动到...
							</option>
							<option value="receivebox">
								&nbsp;&nbsp;收件箱
							</option>
							<option value="rubbishbox">
								&nbsp;&nbsp;垃圾邮件
							</option>
							<option value="deletedbox">
								&nbsp;&nbsp;已删除邮件
							</option>
							<option value="scriptbox">
								&nbsp;&nbsp;草稿箱
							</option>
							<option value="sendedbox">
								&nbsp;&nbsp;已发送邮件
							</option>
							<option value="adbox">
								&nbsp;&nbsp;广告邮件
							</option>
							<option value="none" class="SelOptLine">
								-------------
							</option>
						</select>
						<select class="Sel" name="queryfrom" onchange="action4()">
							<option value="none" selected="selected">
								查看...
							</option>
							<option value="all">
								&nbsp;&nbsp;全部邮件
							</option>
							<option value="none" class="SelOptLine">
								-------------
							</option>
							<option value="readtrue" onclick="action2()">
								&nbsp;&nbsp;已读邮件
							</option>
							<option value="readfalse" onclick="action2()">
								&nbsp;&nbsp;未读邮件
							</option>
							<option value="none" class="SelOptLine">
								-------------
							</option>
							<option value="urgent" onclick="action2()">
								&nbsp;&nbsp;紧急邮件
							</option>
							<option value="commonpriority" onclick="action2()">
								&nbsp;&nbsp;普通邮件
							</option>
							<option value="slow" onclick="action2()">
								&nbsp;&nbsp;缓慢邮件
							</option>
							<option value="none" class="SelOptLine">
								-------------
							</option>
							<option value="important" class="fnt_Mark1" onclick="action2()">
								&nbsp;&nbsp;重要邮件
							</option>
							<option value="company" class="fnt_Mark2" onclick="action2()">
								&nbsp;&nbsp;公司邮件
							</option>
							<option value="business" class="fnt_Mark3" onclick="action2()">
								&nbsp;&nbsp;业务邮件
							</option>
							<option value="information" class="fnt_Mark4" onclick="action2()">
								&nbsp;&nbsp;资讯邮件
							</option>
							<option value="family" class="fnt_Mark5" onclick="action2()">
								&nbsp;&nbsp;亲友邮件
							</option>
							<option value="student" class="fnt_Mark6" onclick="action2()">
								&nbsp;&nbsp;同学邮件
							</option>
							<option value="leisure" class="fnt_Mark7" onclick="action2()">
								&nbsp;&nbsp;休闲邮件
							</option>
							<option value="intresting" class="fnt_Mark8" onclick="action2()">
								&nbsp;&nbsp;趣闻邮件
							</option>
							<option value="commontag" class="fnt_Mark15" onclick="action2()">
								&nbsp;&nbsp;普通邮件
							</option>
						</select>
						<div class="Extra">
							<span class="Txt"><span class="Unable"><a
									href="goscript2.do?pageNo=1">首页</a>
							</span>&nbsp;&nbsp; <span class="Unable"><a
									href="goscript2.do?pageNo=${scriptPageNo - 1 }">上页</a>
							</span> &nbsp;&nbsp; <span class="Unable"> <a
									href="goscript2.do?pageNo=${scriptPageNo + 1 }">下页</a>
							</span>&nbsp;&nbsp; <span class="Unable"><a
									href="goscript2.do?pageNo=${scriptTotalPages }">末页</a>
							</span> </span>
							<select class="SelA" style="margin-right: 0px" name="pageNo"
								onchange="changepage(this.value)">
								<c:forEach var="t" begin="1" end="${scriptTotalPages}">
									<option value="${t }">
										${t }/${scriptTotalPages }
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
									<th class="Ibx_Th_icoInfo">
										<b class="icoIbx" title='邮件类型'><img
												src="images/web_icon_013.gif" />
										</b>
									</th>
									<th class="Ibx_Th_From">
										<a href="#" title="点击可按此排序">发件人</a>
									</th>
									<th class="Ibx_Th_icoFlag">
										<b class="icoIbx" title='邮件标签'><img src="images/9.JPG">
										</b>
									</th>
									<th class="Ibx_Th_Subject">
										<a href="#" title="点击可按此排序">主题</a>
									</th>
									<th class="Ibx_Th_Date">
										<a href="#" title="点击可按此排序">日期<b class="icoIbx icoDown"></b>
										</a>
									</th>
									<th class="Ibx_Th_icoATCM">
										<b class="icoIbx" title='附件标识'><img
												src="images/attach.gif" />
										</b>
									</th>
									<th class="Ibx_Th_Size">
										<a href="#" title="点击可按此排序">大小(KB)</a>
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
										key="oTableCOUNT0"><span id="oSpanCOUNT0">10</span>封</a>)</span>
								</span>
							</h4>
							<table class="Ibx_gTable Ibx_gTable_Con" id="oTableCOUNT0">
								<tbody>

									<logic:notPresent name="scriptEmailBeanList" scope="session">
										<tr class="I_Mark0">
											<td align="center">
												对不起，查询错误，请稍候重试！
											</td>
										</tr>
									</logic:notPresent>
									<logic:present name="scriptEmailBeanList" scope="session">

										<logic:empty name="scriptEmailBeanList">
											<tr class="I_Mark0">
												<td align="center">
													对不起，当前没有草稿邮件！
												</td>
											</tr>
										</logic:empty>
										<logic:notEmpty name="scriptEmailBeanList">
											<c:forEach items="${scriptEmailBeanList}" var="t">
												<tr class="I_Mark0">
													<td class="Ibx_Td_F">
														&nbsp;
													</td>
													<td class="Ibx_Td_Chkbx">
														<input name="mid" type="checkbox" title="选择/不选"
															value="${t.email.emailid }" />
													</td>

													<td class="Ibx_Td_icoInfo">
														<b class="icoIbx icoSlow" title="缓慢邮件"> </b>
													</td>

													<td class="Ibx_Td_From">
														<a href="#"
															onclick="javascript:changeframe('', '', 'scriptmailtemp.jsp?emailid=${t.email.emailid }'); return false;">${t.email.sender
															}</a>
													</td>
													<c:choose>
														<c:when test="${t.email.mailtag.tagid eq 1}">
															<td class="Ibx_Td_icoFlag" title="重要邮件">
																<b class="icoIbx"> <img src="images/tag/1.JPG" /> </b>
															</td>
														</c:when>
														<c:when test="${t.email.mailtag.tagid eq 2}">
															<td class="Ibx_Td_icoFlag" title="公司邮件">
																<b class="icoIbx"> <img src="images/tag/3.JPG" /> </b>
															</td>
														</c:when>
														<c:when test="${t.email.mailtag.tagid eq 3}">
															<td class="Ibx_Td_icoFlag" title="业务邮件">
																<b class="icoIbx"> <img src="images/tag/4.JPG" /> </b>
															</td>
														</c:when>
														<c:when test="${t.email.mailtag.tagid eq 4}">
															<td class="Ibx_Td_icoFlag" title="资讯邮件">
																<b class="icoIbx"> <img src="images/tag/6.JPG" /> </b>
															</td>
														</c:when>
														<c:when test="${t.email.mailtag.tagid eq 5}">
															<td class="Ibx_Td_icoFlag" title="亲友邮件">
																<b class="icoIbx"> <img src="images/tag/5.JPG" /> </b>
															</td>
														</c:when>
														<c:when test="${t.email.mailtag.tagid eq 6}">
															<td class="Ibx_Td_icoFlag" title="同学邮件">
																<b class="icoIbx"> <img src="images/tag/2.JPG" /> </b>
															</td>
														</c:when>
														<c:when test="${t.email.mailtag.tagid eq 7}">
															<td class="Ibx_Td_icoFlag" title="休闲邮件">
																<b class="icoIbx"> <img src="images/tag/8.JPG" /> </b>
															</td>
														</c:when>
														<c:when test="${t.email.mailtag.tagid eq 8}">
															<td class="Ibx_Td_icoFlag" title="趣闻邮件">
																<b class="icoIbx"> <img src="images/tag/7.JPG" /> </b>
															</td>
														</c:when>
														<c:otherwise>
															<td class="Ibx_Td_icoFlag" title="未分类">
																<b class="icoIbx"> <img src="images/tag/9.JPG" /> </b>
															</td>
														</c:otherwise>
													</c:choose>
													<td class="Ibx_Td_From" align="left">
														<a
															onclick="javascript:changeframe('', '', 'scriptmailtemp.jsp?emailid=${t.email.emailid }'); return false;"
															href="#">${t.email.subject }</a>
													</td>
													<td class="Ibx_Td_Date" title="">
														${t.email.senttime }
													</td>
													<c:choose>
														<c:when test="${t.containAttachment}">
															<td class="Ibx_Td_icoATCM">
																<img src="images/attach.gif" />
															</td>
														</c:when>
														<c:otherwise>
															<td class="Ibx_Td_icoATCM"></td>
														</c:otherwise>
													</c:choose>
													<td class="Ibx_Td_Size">
														<span title="5637 字节"><fmt:formatNumber
																value="${t.email.msgsize*1024 }" pattern="####.##" />
														</span>
													</td>
												</tr>
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
						<input type="button" onclick="action1()" class="Btn BtnNml ImpBtn"
							value="删 除" />
						<select class="Sel" onchange="action2()" name="changeflag">
							<option value="none" selected="selected">
								标记为...
							</option>
							<option value="readtrue" onclick="action2()">
								&nbsp;&nbsp;已读邮件
							</option>
							<option value="readfalse" onclick="action2()">
								&nbsp;&nbsp;未读邮件
							</option>
							<option value="none" class="SelOptLine">
								-------------
							</option>
							<option value="urgent" onclick="action2()">
								&nbsp;&nbsp;紧急邮件
							</option>
							<option value="commonpriority" onclick="action2()">
								&nbsp;&nbsp;普通邮件
							</option>
							<option value="slow" onclick="action2()">
								&nbsp;&nbsp;缓慢邮件
							</option>
							<option value="none" class="SelOptLine">
								-------------
							</option>
							<option value="important" class="fnt_Mark1" onclick="action2()">
								&nbsp;&nbsp;重要邮件
							</option>
							<option value="company" class="fnt_Mark2" onclick="action2()">
								&nbsp;&nbsp;公司邮件
							</option>
							<option value="business" class="fnt_Mark3" onclick="action2()">
								&nbsp;&nbsp;业务邮件
							</option>
							<option value="information" class="fnt_Mark4" onclick="action2()">
								&nbsp;&nbsp;资讯邮件
							</option>
							<option value="family" class="fnt_Mark5" onclick="action2()">
								&nbsp;&nbsp;亲友邮件
							</option>
							<option value="student" class="fnt_Mark6" onclick="action2()">
								&nbsp;&nbsp;同学邮件
							</option>
							<option value="leisure" class="fnt_Mark7" onclick="action2()">
								&nbsp;&nbsp;休闲邮件
							</option>
							<option value="intresting" class="fnt_Mark8" onclick="action2()">
								&nbsp;&nbsp;趣闻邮件
							</option>
							<option value="commontag" class="fnt_Mark15" onclick="action2()">
								&nbsp;&nbsp;普通邮件
							</option>
							<option value="none" class="SelOptLine">
								-------------
							</option>
							<option value="cancel" onclick="action2()">
								&nbsp;&nbsp;取消标签
							</option>
						</select>
						<select class="Sel" name="moveto" onchange="action3()">
							<option value="none" selected="selected">
								移动到...
							</option>
							<option value="receivebox">
								&nbsp;&nbsp;收件箱
							</option>
							<option value="rubbishbox">
								&nbsp;&nbsp;垃圾邮件
							</option>
							<option value="deletedbox">
								&nbsp;&nbsp;已删除邮件
							</option>
							<option value="scriptbox">
								&nbsp;&nbsp;草稿箱
							</option>
							<option value="sendedbox">
								&nbsp;&nbsp;已发送邮件
							</option>
							<option value="adbox">
								&nbsp;&nbsp;广告邮件
							</option>
							<option value="none" class="SelOptLine">
								-------------
							</option>
						</select>
						<select class="Sel" name="queryfrom" onchange="action4()">
							<option value="none" selected="selected">
								查看...
							</option>
							<option value="all">
								&nbsp;&nbsp;全部邮件
							</option>
							<option value="none" class="SelOptLine">
								-------------
							</option>
							<option value="readtrue" onclick="action2()">
								&nbsp;&nbsp;已读邮件
							</option>
							<option value="readfalse" onclick="action2()">
								&nbsp;&nbsp;未读邮件
							</option>
							<option value="none" class="SelOptLine">
								-------------
							</option>
							<option value="urgent" onclick="action2()">
								&nbsp;&nbsp;紧急邮件
							</option>
							<option value="commonpriority" onclick="action2()">
								&nbsp;&nbsp;普通邮件
							</option>
							<option value="slow" onclick="action2()">
								&nbsp;&nbsp;缓慢邮件
							</option>
							<option value="none" class="SelOptLine">
								-------------
							</option>
							<option value="important" class="fnt_Mark1" onclick="action2()">
								&nbsp;&nbsp;重要邮件
							</option>
							<option value="company" class="fnt_Mark2" onclick="action2()">
								&nbsp;&nbsp;公司邮件
							</option>
							<option value="business" class="fnt_Mark3" onclick="action2()">
								&nbsp;&nbsp;业务邮件
							</option>
							<option value="information" class="fnt_Mark4" onclick="action2()">
								&nbsp;&nbsp;资讯邮件
							</option>
							<option value="family" class="fnt_Mark5" onclick="action2()">
								&nbsp;&nbsp;亲友邮件
							</option>
							<option value="student" class="fnt_Mark6" onclick="action2()">
								&nbsp;&nbsp;同学邮件
							</option>
							<option value="leisure" class="fnt_Mark7" onclick="action2()">
								&nbsp;&nbsp;休闲邮件
							</option>
							<option value="intresting" class="fnt_Mark8" onclick="action2()">
								&nbsp;&nbsp;趣闻邮件
							</option>
							<option value="commontag" class="fnt_Mark15" onclick="action2()">
								&nbsp;&nbsp;普通邮件
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
