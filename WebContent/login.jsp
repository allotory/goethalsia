<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>WellMail邮件登录系统</title>
		<style type="text/css">
<!--
body {
	margin: 0;
	padding: 0;
	font-size: 12px;
	font-family: Arial, Helvetica, sans-serif;
	color: #333333;
	background: #FFFFFF;
}

div ul,div ul li {
	margin: 0px;
	padding: 0px;
	list-style: none;
}

img {
	border: 0px;
}

a:link {
	color: #333333;
	text-decoration: none;
}

a:visited {
	color: #333333;
	text-decoration: none;
}

a:hover {
	color: #FF9900;
	text-decoration: none;
}

.color01 {
	color: #3399CC;
}

.container {
	width: 809px;
	height: 600px;
	overflow: hidden;
	margin: 0 auto;
}

.logo {
	position: relative;
	top: 20px;
	height: 47px;
}

.logo ul li {
	float: left;
}

.right {
	width: 460px;
	text-align: right;
	position: relative;
	right: 20px;
}

.main {
	clear: both;
}

.main div {
	float: left;
}

.main_left {
	width: 809px;
}

.main_left01 {
	height: 76px;
}

.main_left02 {
	width: 809px;
	height: 223px;
	background: url(images/bg.jpg);
}

.main_left03 {
	height: 7px;
}

.main_left04 {
	height: 121px;
	background: url(images/login_25.png) no-repeat left;
}

.main_left05 {
	padding: 20px 0 0 120px;
}

.main_left05 ul li {
	height: 40px;
	line-height: 40px;
}

.main_center {
	width: 373px;
	z-index: 50;
	position: relative;
	left: 417px;
	top: -440px;
}

.main_center_top {
	height: 71px;
	width: 100%;
	background: url(images/login_01.gif) no-repeat bottom;
	text-indent: 280px;
}

.main_center_main {
	height: 341px;
	width: 100%;
	background: url(images/login_02.jpg);
}

.main_center_main01 {
	padding: 10px 0 0 30px;
}

.Login {
	padding-top: 20px;
}

* html .Login {
	padding-top: 22px;
}

.main_center_bott {
	height: 24px;
	width: 100%;
	background: url(images/login_03.gif);
}

.main_right {
	width: 19px;
}

.main_rightbg {
	height: 223px;
	background: url(images/bg01.jpg);
}

.login_table_text1_input {
	width: 172px;
	border: #0a8fda 1px solid;
	background-color: #E8F9FF;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 14px;
	padding: 5px 3px
}

.login_table_select {
	width: 180px;
	height: 22px;
	border: 1px solid #84a1bd;
	float: left
}

.cop {
	clear: both;
	height: 8px;
	background: lightblue;
	position: relative;
	top: -400px;
}

.CopyRight {
	text-align: center;
	line-height: 20px;
	position: relative;
	top: -380px;
}
-->
</style>
		<script type="text/javascript"> 
<!--
if(top.location !== self.location) {
    top.location = self.location;
}
function $(id) {
	return document.getElementById(id);
}
window.onload=function (){
    // $("user").focus();
}
function loginCheckUser() {
    if ($('user').value == "") {
        //alert("邮件帐号不能为空！");
        
        alert("邮件帐号不能为空！");
        
        // $('user').focus();
        return false;
    }
    var patrn=/^[a-zA-Z]{6,15}$/;

	if (!patrn.exec($('user').value)) {
		alert("帐号格式不正确！必须为6～15个字母！");
		return false;
	}else {
		var qff=document.getElementById("usernametrue");
		qff.innerHTML="<img src='images/002.gif'>";
		//alert("aaaa");
		return true;
    }
}

function loginCheckPass() {
	 if ($('password').value == "") {
        alert("登录密码不能为空！");
        $('password').focus();
        return false;
    }
    
    var patrn=/^(\w){6,20}$/;

	if (!patrn.exec($('password').value)) {
		alert("密码格式正确！只能输入6-20个字母、数字、下划线!");
		return false;
	}else {
		var qfpass=document.getElementById("passtrue");
		qfpass.innerHTML="<img src='images/002.gif'>";
		return true;
	}
	return true;
}

function loginCheckCheckCode() {
	
	if ($('dpassword').value == "") {
        alert("请输入验证码！");
        $('dpassword').focus();
        return false;
    }else {
    	var qfcode=document.getElementById("codetrue");
		qfcode.innerHTML="<img src='images/002.gif'>";
		return true;
    }
	return true;
}
//-->
</script>
		<script language="javascript">
  function loadimage(){
    document.getElementById("vcode_img").src = "image.jsp?"+Math.random();
  }
</script>
	</head>
	
<%

	Cookie[] cookies = request.getCookies(); //得到Cookie的值
	//System.out.println(cookies.length);
	String username = "";
	String password = "";
	if(cookies!= null && cookies.length > 0){
	
	        for(int i=0; i<cookies.length; i++){
	
	                if(cookies[i].getName().equals("username")){
	
	                        //request.setAttribute("username", cookies[i].getValue()); //设置属性值
	
	                        //System.out.println(cookies[i].getValue());
	                        username = cookies[i].getValue();
	                }else if(cookies[i].getName().equals("pwdHash")){
	
	                        //request.setAttribute("password",cookies[i].getValue());
	                       // System.out.println(cookies[i].getValue());
	                       password = cookies[i].getValue();
	                       
	
	                }                
	
	        }                
	
	}     

%>
	<script type="text/javascript">
	function action() 
	{ 
	   document.form.action="forgetpass.jsp"; 
	   document.form.submit();
	}
		
	</script>
	<body>
		<div class="container">
			<div class="logo">
				<ul>
					<li>
						<img title="企业电子邮件中心" src="images/man_logo.jpg" />
					</li>
					<li class="right">
						<a title="首页" href="#">首页</a>
						<a title="帮助" href="#">帮助</a>
						<a title="注册" href="register.do">注册</a>
					</li>
				</ul>
			</div>
			<div class="main">
				<div class="main_left">
					<ul>
						<li class="main_left01"></li>
						<li class="main_left02"></li>
						<li class="main_left03"></li>
						<li class="main_left04">
							<div class="main_left05">
								<ul>
									<li>
										<img src="images/login_33.jpg" align="middle" />
										&nbsp;&nbsp;精准反垃圾，有效过滤超过98.6%的
										<span class="color01">垃圾邮件</span>；
									</li>
									<li>
										<img src="images/login_37.jpg" align="middle" />
										&nbsp;&nbsp;
										<span class="color01">病毒邮件</span>有效拦截率超过99.9%；
									</li>
								</ul>
							</div>
						</li>
					</ul>
				</div>
				<div class="main_center">
					<div class="main_center_top">
						<img id="month_img" src="images/blank.gif" />
					</div>
					<div class="main_center_main">
						<Div class="main_center_main01">
							<ul>
								<li>
									<img src="images/login_11.gif" title="登录企业邮箱" />
								</li>
								<li class="Login">
									<form name="form" method="post" action="login.do">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td class="color01" width="80" align="right">
													登录帐号
												</td>
												<td>
													&nbsp;&nbsp;
													<input onblur="loginCheckUser(this.value);"
														class="login_table_text1_input" type="text"
														name="username" id="user" maxlength="50"
														style="font-weight: bold; font-family: Verdana, Arial, Helvetica, sans-serif" 
														value="<%=username %>"/>
													<span id="usernametrue"></span>
												</td>
											</tr>
											<Tr>
												<td colspan="2" height="5px"></td>
											</tr>
											<tr>
												<td height="20px"></td>
												<td style="padding-left: 6px;">
													<strong style="letter-spacing: 1px;">&nbsp;&nbsp;@MyMail.Com</strong>
												</td>
											</tr>
											<tr>
												<td class="color01" width="80" align="right" height="45px">
													登录密码
												</td>
												<td>
													&nbsp;&nbsp;
													<input onblur="loginCheckPass(this.value);"
														class="login_table_text1_input" type="password"
														name="pwdHash" id="password" maxlength="50"
														style="font-weight: bold; font-family: Verdana, Arial, Helvetica, sans-serif" 
														value="<%=password %>"/>
													<span id="passtrue"></span>
												</td>
											</tr>
											<tr>
												<td height="20px"></td>
												<td style="padding-left: 6px;" valign="middle">
													&nbsp;&nbsp;
													<img id="vcode_img" src="image.jsp" class="">
													&nbsp;&nbsp;
													<a href="javascript:loadimage();">看不清楚，换一张</a>
												</td>
											</tr>
											<tr>
												<td class="color01" width="80" align="right" height="45px">
													验 证 码
												</td>
												<td>
													&nbsp;&nbsp;
													<input onblur="loginCheckCheckCode(this.value);"
														class="login_table_text1_input" type="text"
														name="checkcode" id="dpassword" maxlength="50"
														style="font-weight: bold; font-family: Verdana, Arial, Helvetica, sans-serif" />
													<span id="codetrue"></span>
												</td>
											</tr>
											<tr>
												<td></td>
												<td height="45px" style="line-height: 20px;">
													<table>
														<tr>
															<td>
																<input name="rember" id="remuser" type="checkbox"
																	style="float: left" align="middle" value="rem"/>
																&nbsp;记住用户名
															</td>
															<td>
																&nbsp;&nbsp;&nbsp;<a href="clearcookie.do">清除Cookie</a>
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td colspan="2" style="padding-left: 60px;">
													<input name="enter" type="hidden" value='true' />
													<input title="登录邮箱" value="" type="submit"
														style="background-image: url(images/login_18.gif); BORDER-bottom: medium none; BORDER-left: medium none; BORDER-right: medium none; BORDER-TOP: medium none; width: 86px; height: 33px; cursor: hand;"
														onFocus="this.blur()" />
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input name="getpass" title="忘记密码" value="" type="button"
														style="background-image: url(images/login_20.gif); BORDER-bottom: medium none; BORDER-left: medium none; BORDER-right: medium none; BORDER-TOP: medium none; width: 86px; height: 33px; cursor: hand;"
														onFocus="this.blur()"
														onclick="window.location.href('forgetpass.jsp')" />
												</td>
											</tr>
											<tr>
												<td colspan="2"
													style="height: 10px; background: url(images/line.gif) no-repeat"></td>
											</tr>
										</table>
									</form>
								</li>
							</ul>
						</div>
					</div>
					<div class="main_center_bott">
						&nbsp;
					</div>
				</div>
			</div>
			<div class="cop"></div>
			<div class="CopyRight">
				Copyright &copy; MyMail 2010-2011 My Infomation Technology, All
				Rights Reserved
			</div>
		</div>
	</body>
</html>
