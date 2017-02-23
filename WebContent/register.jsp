<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GB18030" />
		<title>MyMail邮箱 - 注册新用户</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script language="javascript">
  function loadimage(){
    document.getElementById("vcode_img").src = "image.jsp?"+Math.random();
  }
</script>
		<script language="javascript">
	function fsubmit(obj){
	
		var n = 0; 
		var objs = document.getElementById("servItems"); 
		 
		if(objs.checked) {
			n=n+1; 
		}
		// alert(n);
		if( n == 0 ) {
			// alert("至少选中一个");
			var qf=document.getElementById("serviceitem");
			qf.innerHTML="<img src='images/178.gif'> 请接受服务条款！";
			return false;
		} 
		
		var objs2=document.getElementsByName("sex");
		var temp= 0;
		for (var i=0;i<objs2.length;i++){
		  if(objs2[i].checked){
		  	temp++;
		  }
		 }
		 
		 if(temp == 0) {
		 	var qfa=document.getElementById("sexresult");
			qfa.innerHTML="<img src='images/178.gif'> 性别必须选择！";
			return false;
		 }
	
		obj.submit();
		// alert("ok");
	}
	
	function registeCheckUser(s) {
    if (s == "") {
        //alert("邮件帐号不能为空！");
        
        alert("邮件帐号不能为空！");
        
        // $('user').focus();
        return false;
    }
    var patrn=/^[a-zA-Z]{6,15}$/;

	if (!patrn.exec(s)) {
		alert("帐号格式不正确！必须为6～15个字母！");
		return false;
	}
    return true;
}


	// 24.校验密码：只能输入6-20个字母、数字、下划线

	function isPasswd(s)
	{
	
		if (s == "") {
	       	var qf=document.getElementById("p1result");
			qf.innerHTML="<img src='images/178.gif'> 密码不能为空！";
			return false;
    	}
		var patrn=/^(\w){6,20}$/;
	
		if (!patrn.exec(s)) {
			var qf=document.getElementById("p1result");
			qf.innerHTML="<img src='images/178.gif'> 密码格式不正确，只能输入6-20个字母、数字、下划线！";
			return false;
		}
		var qf=document.getElementById("p1result");
		qf.innerHTML="<img src='images/002.gif'> 密码格式正确！";
		return true;
	
	} 
	
	function passwordEqual(s){
		var password = document.getElementById("password");
		///alert(s);
		// alert(password.value);
		if(password.value != "") {
			if(s != password.value) {
				var qf=document.getElementById("p2result");
				qf.innerHTML="<img src='images/178.gif'> 两次密码不相同，请确认！";
				return false;
			}else {
				var qf=document.getElementById("p2result");
				qf.innerHTML="<img src='images/002.gif'> 两次密码正确！";
				return true;
			}
		}else {
			var qf=document.getElementById("p2result");
			qf.innerHTML="<img src='images/178.gif'> 确认密码不能为空！";
			return false;
		}
	}
	
	function changeQuestion(s){
		if(s == "请选择密码提示问题") {
			var qf=document.getElementById("quesresult");
			qf.innerHTML="<img src='images/178.gif'> 请选择密码提示问题！";
			return false;
		}else {
			var qf=document.getElementById("quesresult");
			qf.innerHTML="";
		}
	}
	
	function answerCheck(s){
		if(s == "") {
			var qfa=document.getElementById("ansresult");
			qfa.innerHTML="<img src='images/178.gif'> 密码提示问题必须填写！";
			return false;
		}else {
			var qfa=document.getElementById("ansresult");
			qfa.innerHTML="<img src='images/002.gif'> 密码提示问题完成！";
			return true;
		}
	}
	
	function checkRadioValue(){
	 var obj=document.getElementsByName("sex");
	 var t = 0;
	 for (var i=0;i<obj.length;i++)
	 {
	  if(obj[i].checked){
	  	t++;
	  }
	 }
	 if(t > 0) {
	 	var qfa=document.getElementById("sexresult");
	 	qfa.innerHTML="<img src='images/002.gif'> 性别选择完成！";
	  	return false;
	 }
	 var qfa=document.getElementById("sexresult");
	 qfa.innerHTML="<img src='images/178.gif'> 性别必须选择！";
	 return true;
	}
	
	 function CheckChinaMobileID(mobile){
        if(mobile != "") {
	        if(isNaN(mobile)||(mobile.length!=11)){
	            var qfa=document.getElementById("telresult");
		 		qfa.innerHTML="<img src='images/178.gif'> 手机号码为11位数字！请正确填写！";
	            return false;
	        }
	        var reg =/^0{0,1}(13[4-9]|15[7-9]|15[0-2]|18[7-8])[0-9]{8}$/;
	        if(!reg.test(mobile))
	        {
	            var qfa=document.getElementById("telresult");
		 		qfa.innerHTML="<img src='images/178.gif'> 您的手机号码不是中国现有号码，请重新输入！";
	            return false;
	        }
	        
	        var qfa=document.getElementById("telresult");
		 	qfa.innerHTML="<img src='images/002.gif'> 您的手机号码正确！";
	        return true;
        }
    }
    
    function checkCode(a) {
    	if(a == "") {
    		var qfa=document.getElementById("ccoderesult");
		 	qfa.innerHTML="<img src='images/178.gif'> 对不起，验证码不能为空！";
		 	 return false;
    	}
    	
    	if(isNaN(a)||(a.length!=4)) {
    		var qfa=document.getElementById("ccoderesult");
		 	qfa.innerHTML="<img src='images/178.gif'> 对不起，验证码为数字且长度为4！";
		 	return false;
    	}
    	
    	var qfa=document.getElementById("ccoderesult");
	 	qfa.innerHTML="<img src='images/002.gif'> 验证码格式正确！";
        return true;
    }
    
    function serviceitem() 
	{ 
		var n = 0; 
		var objs = document.all.servItems; 
		for(var i=0;i <objs.length;i++)  
		{ 
		if(objs[i].checked) 
		n=n+1; 
		} 
		if( n == 0 ) 
		alert("至少选中一个"); 
	} 
</script>
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/verify.js"></script>
	</head>


	<body>
		<div class="header">
			<a href="#"><img src="images/man_logo.jpg" alt="MyMail邮箱"
					class="logo" />
			</a>
			<div class="head_links">
				<a href="http://help.163.com/" target="_blank" title="帮助">帮助</a>&nbsp;&nbsp;&nbsp;
			</div>
		</div>

		<div class="content">
			<div class="mhd">
				<div class="tit">
					欢迎注册MyMail免费邮箱！
				</div>
			</div>
			<div class="mcont">
				<!-- 分拆补充资料部分 Start -->
				<div id="sysmessage" class="err-info" style="display: none"></div>
				<!-- registing.do -->
				<form id="regform" name="regform" action="registing.do"
					method="post">
					<div class="main-cont">
						<div class="main-cont-tit">
							<div class="arr"></div>
							<h2>
								创建您的帐号
							</h2>
						</div>

						<table class="cont-tab" cellspacing="0" cellpadding="0"
							style="table-layout: fixed">
							<!--用户名的处理 Start-->
							<tr id="tr_input_username">
								<td class="td1">
									用户名：
									<span class="nes">*</span>
								</td>
								<td class="td2" align="left">
									<div class="fle">
										<input onblur="registeCheckUser(this.value)" type="text"
											name="username" id="inp_uname"
											onFocus="this.className='inp ipt-focus'"
											class="inp ipt-normal"
											style="width: 205px; font-weight: bold" maxlength="18" />
									</div>

									<input id="btn_chk" type="button" value="检测" class="btn-jc fri"
										align="left" onclick="verify()" />
									<br />
									<span id="result"></span>
								</td>
							</tr>

							<tr id="tr_chk_username_result" style="display: none">
								<td class="td1">
									&nbsp;
								</td>
								<td colspan="2" class="td3">
									<div id="div_chose_uname" class="chose-list">

									</div>
								</td>
							</tr>

							<tr>
								<td class="td1">
									密 码：
									<span class="nes">*</span>
								</td>
								<td class="td2" align="left">
									<input onblur="isPasswd(this.value)"
										onFocus="this.className='inp ipt-focus'" type="password"
										name="pwdHash" id="password" class="inp ipt-normal"
										style="width: 235px" maxlength="16" value="" />
									<br />
									<span id="p1result"></span>
								</td>
							</tr>
							<tr>
								<td class="td1">
									再次输入密码：
									<span class="nes">*</span>
								</td>
								<td class="td2" align="left">
									<input onblur="passwordEqual(this.value)" style="width: 235px"
										type="password" name="pwdHashConfirm" id="passwordconfirm"
										class="inp ipt-normal"
										onFocus="this.className='inp ipt-focus'" maxlength="16"
										value="" />
									<br />
									<span id="p2result"></span>
								</td>
							</tr>
						</table>
						<div class="main-cont-tit">
							<div class="arr"></div>
							<h2>
								安全信息设置
							</h2>
							<span class="fle Cgray">（以下信息非常重要，请慎重填写）</span>
						</div>

						<table class="cont-tab" cellspacing="0" cellpadding="0"
							style="table-layout: fixed">
							<tr>
								<td class="td1">
									密码保护问题：
									<span class="nes">*</span>
								</td>
								<td class="td2" align="left">
									<select name="question" id="secproblem" class="sel"
										style="width: 235px" onblur="changeQuestion(this.value)">
										<option style="color: #666" value="请选择密码提示问题">
											请选择密码提示问题
										</option>
										<option value="您母亲的姓名是?">
											您母亲的姓名是?
										</option>
										<option value="您父亲的姓名是?">
											您父亲的姓名是?
										</option>
										<option value="您配偶的姓名是?">
											您配偶的姓名是?
										</option>
										<option value="您母亲的生日是?">
											您母亲的生日是?
										</option>
										<option value="您父亲的生日是?">
											您父亲的生日是?
										</option>
										<option value="您配偶的生日是?">
											您配偶的生日是?
										</option>
										<option value="您的出生地是?">
											您的出生地是?
										</option>
										<option value="您的小学校名是?">
											您的小学校名是?
										</option>
										<option value="您的中学校名是?">
											您的中学校名是?
										</option>
										<option value="您的大学校名是?">
											您的大学校名是?
										</option>
									</select>
									<br />
									<span id="quesresult"></span>
								</td>
							</tr>
							<tr>
								<td class="td1">
									密码保护问题答案：
									<span class="nes">*</span>
								</td>
								<td class="td2" align="left">
									<input style="width: 235px" onblur="answerCheck(this.value)"
										type="text" name="answer" id="secanswer"
										class="inp ipt-normal"
										onFocus="this.className='inp ipt-focus';" maxlength="30" />
									<br />
									<span id="ansresult"></span>
								</td>
							</tr>
							<tr>
								<td class="td1">
									性 别：
									<span class="nes">*</span>
								</td>
								<td class="td2" style="font-size: 14px" align="left">
									<label for="rd1">
										<input type="radio" name="sex" id="rd1" value="男"
											onclick="checkRadioValue()">
										男
									</label>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<label for="rd2">
										<input type="radio" name="sex" id="rd2" value="女"
											onclick="checkRadioValue()">
										女
									</label>
									<br />
									<span id="sexresult"></span>
								</td>

							</tr>
							<tr>
								<td class="td1">
									出生日期：
									<span class="nes">*</span>
								</td>
								<td class="td2" style="font-size: 14px" align="left">
									<input type="text" name="year" id="year" value=""
										class="inp ipt-normal"
										onFocus="this.className='inp ipt-focus'" maxlength="4"
										style="width: 60px"
										onkeyup="this.value=this.value.replace(/\D/g,'')" />
									年
									<input type="text" name="month" id="month" value=""
										class="inp ipt-normal"
										onFocus="this.className='inp ipt-focus'" maxlength="2"
										style="width: 50px"
										onkeyup="this.value=this.value.replace(/\D/g,'')"
										onafterpaste="this.value=this.value.replace(/\D/g,'')" />
									月
									<input type="text" name="day" id="day" value=""
										class="inp ipt-normal"
										onFocus="this.className='inp ipt-focus'" maxlength="2"
										style="width: 50px"
										onkeyup="this.value=this.value.replace(/\D/g,'')"
										onafterpaste="this.value=this.value.replace(/\D/g,'')" />
									日
								</td>

							</tr>
							<tr>
								<td class="td1">
									手机号：
									<span class="unnes">*</span>
								</td>
								<td class="td2" align="left">
									<input onblur="CheckChinaMobileID(this.value)"
										style="width: 235px" type="text" name="tel" id="mobile"
										value="" class="inp ipt-normal"
										onFocus="this.className='inp ipt-focus'" maxlength="16" />
									<br />
									<span id="telresult"></span>
								</td>

								<td class="td3">

								</td>
							</tr>
						</table>
						<div class="main-cont-tit">
							<div class="arr"></div>
							<h2>
								注册验证
							</h2>
						</div>

						<table class="cont-tab" cellspacing="0" cellpadding="0"
							style="table-layout: fixed">
							<tr>
								<td class="td1">
									&nbsp;
								</td>
								<td class="td2 codeImg" align="left">
									<img id="vcode_img" src="image.jsp" class="">
									&nbsp;&nbsp;
									<a href="javascript:loadimage();">看不清楚，换一张</a>
								</td>
								<td class="td3">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td class="td1">
									请输入上边的字符：
									<span class="nes">*</span>
								</td>
								<td class="td2" align="left">
									<input style="width: 235px" type="text" name="checkcode"
										id="authcode" class="inp ipt-normal"
										onFocus="this.className='inp ipt-focus'"
										onBlur="checkCode(this.value)" maxlength="16" />
									<!--输入框3种状态 “ipt-normal”-正常，“ipt-focus”-获取焦点，“ipt-error”-正常， -->
									<br />
									<span id="ccoderesult"></span>
								</td>

							</tr>
						</table>

						<div class="main-cont-tit">
							<div class="arr"></div>
							<h2>
								服务条款
							</h2>
						</div>
						<table class="cont-tab" cellspacing="0" cellpadding="0"
							style="table-layout: fixed">
							<tr>
								<td class="td1">
									&nbsp;
								</td>
								<td class="td2" align="left">
									<label>
										<input type="checkbox" name="servItems" id="servItems"
											checked="checked">
										我已阅读并接受
									</label>
									&ldquo;
									<a href="serviceitem.html">服务条款</a>&rdquo;
									<br />
									<span id="serviceitem"></span>
								</td>
								<td class="td3">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td class="td1">
									&nbsp;
								</td>
								<td class="td2" align="left">
									<input name="" type="button" class="btn-submit"
										onFocus="this.className='btn-submit-act'"
										onBlur="this.className='btn-submit'"
										onClick="javascript:fsubmit(document.regform);" value=""
										title="创建帐号">
								</td>
								<td class="td3">
									&nbsp;
								</td>
							</tr>
						</table>

					</div>
				</form>
				<!-- 分拆补充资料部分   End -->



			</div>
			<div class="mft"></div>
		</div>



		<div class="footer">
			<A href="#" target="_blank">关于Mymail</A>&nbsp;&nbsp;
			<A href="#" target="_blank">邮箱官方博客</A>&nbsp;&nbsp;
			<A href="http://www.188.com/" target="_blank">财富邮</A>&nbsp;&nbsp;
			<A href="http://cards.163.com/" target="_blank">精美贺卡</A>&nbsp;&nbsp;
			<A href="http://safesurf.china.cn/data_form.php" target="_blank">举报违法信息</A>&nbsp;&nbsp;
			<A href="http://help.163.com/" target="_blank">客户服务</A>
			<BR />
			<A href="http://corp.163.com/gb/legal/legal.html" target="_blank">相关法律</A>&nbsp;&nbsp;|&nbsp;&nbsp;版权所有&nbsp;&copy;&nbsp;2009-2010
			<script language="javascript"
				src="http://mimg.163.com/copyright/year.js"></script>
		</div>

		<!-- START NetEase Devilfish 2006 -->
		<script src="http://analytics.163.com/ntes.js" type="text/javascript"></script>
		<script type="text/javascript">
_ntes_nacc = "163mail";
neteaseTracker();
</script>
		<!-- END NetEase Devilfish 2006 -->
	</body>
</html>