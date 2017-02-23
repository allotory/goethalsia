<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>

		<style type="text/css">
<!--
a:link {
	color: blue;
	text-decoration: none;
}

a:visited {
	color: blue;
	text-decoration: none;
}

a:hover {
	color: #FF9900;
	text-decoration: none;
}

.color01 {
	color: #3399CC;
}

.login_table_text1_input {
	border: darkgray 1px solid;
	background-color: white;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 14px;
	padding: 1px 3px
}
-->
</style>
		<script type="text/javascript" src="js/jquery.js"></script>
		<link href="images/zh-cn/globle_v1.css" rel="stylesheet"
			type="text/css">
		<link href="images/zh-cn/read.css" rel="stylesheet" type="text/css">
		<link href="images/zh-cn/skin_blue.css" rel="stylesheet"
			type="text/css" id="lnkSkin" />

		<!-- 页面编辑器-->
		<script type="text/javascript" src="fckeditor/fckeditor.js"></script>
		<script type="text/javascript">
  	window.onload = function(){
  		var oFCKeditor = new FCKeditor('content');
  		oFCKeditor.BasePath="/WellMail/fckeditor/";
  		oFCKeditor.Width="76%";
  		oFCKeditor.Height="330";
  		
  		oFCKeditor.Config["CustomConfigurationsPath"]="/WellMail/js/myconfig.js";
		oFCKeditor.ReplaceTextarea() ;
		
  	}
  </script>

		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/addcc.js"></script>
		<script type="text/javascript" src="js/addbcc.js"></script>
		<script type="text/javascript" src="js/addqf.js"></script>
		<script type="text/javascript" src="js/jquery-1.2.6.js"></script>
		<script type="text/javascript" src="js/jquery.funkyUI.js"></script>

		<script type="text/javascript">
var i = 1;     
function addAttachmentToList(){   
 if (findAttachment(event.srcElement.value)){
  return;
  }           
    var span = document.createElement('span');   
    span.id = '_attachment' + i;   
	//alert(event.srcElement.value);
	
	var path = new String(event.srcElement.value);
	
    span.innerHTML = event.srcElement.value 
    + '&nbsp;<a href="javascript:delAttachment(' + (i++) +')"><img src="images/action_delete.gif"/></a>'
   + '<input type="hidden" name="attach" value="'+ extractFileName(event.srcElement.value) +'"><br/>';   
   
    
    $.get("UploadServlet?path=" + path ,null ,callback);
   
    span.title = event.srcElement.value;   
    G('attachmentList').appendChild(span);   
       
    if (G('attachmentList').style.display == 'none')   
    {   
        G('btnAdd').value = '继续添加';   
        G('attachmentList').style.display = '';   
        G('btnClear').style.display = '';   
    }   
  
    G('total').innerText = '当前选择上传'+ G('attachmentList').childNodes.length + '个附件';   
}   

function callback(data) {
   // alert("服务器端的数据回来了！");
    alert(data);
    //Alert();
    //var resultObj = $("#result");
   // resultObj.html(data);
}
  
function selectAttachment()   
{   
    cleanInvalidUpfile();   
       
    var upfile = '<input type="file" style="display:none" onchange="addAttachmentToList();" id="_upfile'+i+'">';   
    document.body.insertAdjacentHTML('beforeEnd', upfile);   
    G('_upfile'+i).click();   
}   
  
function extractFileName(fn)   
{   
    return fn.substr(fn.lastIndexOf("\\") + 1);   
}   
  
function findAttachment(fn)   
{   
    var o = G('attachmentList').getElementsByTagName('span');   
    for(var i=0;i<o.length;i++)   
        if (o[i].title == fn) return true;   
    return false;   
}   
  
function delAttachment(id)   
{   // var path = event.srcElement.value;
	// var path2 = new String();
	// path2 = path;
    // alert("path1"+path2);
     var up = G('_upfile'+id);
    // alert("up="+up.value);
    $.get("DeleteServlet?path=" + up.value ,null ,callback);
    
    
    G('attachmentList').removeChild(G('_attachment'+id));   
    document.body.removeChild(G('_upfile'+id));   
    
    
    // 当附件列表为空则不显示并且变化添加附件按钮文本   
    if (G('attachmentList').childNodes.length == 0)   
    {   
        G('btnAdd').value = '添加附件';   
        G('attachmentList').style.display = 'none';   
        G('btnClear').style.display = 'none';   
    }   
   
    G('total').innerText = '当前选择上传'+ G('attachmentList').childNodes.length + '个附件';    
    
    
    //alert("path2"+ G('attachmentList').childNode());
    
    
          
}   
  
function cleanInvalidUpfile()   
{   
    var o = document.body.getElementsByTagName('input');   
    for(var i=o.length-1;i>=0;i--)   
      if (o[i].type == 'file' && o[i].id.indexOf('_upfile') == 0)   
     {   
          if (!G('_attachment'+o[i].id.substr(7)))   
              document.body.removeChild(o[i]);   
      }   
}   
  
function clearAttachment()   
{   
    var o = G('attachmentList').childNodes;   
    for(var i=o.length-1;i>=0;i--)   
        G('attachmentList').removeChild(o[i]);   
  
    o = document.body.getElementsByTagName('input');   
    for(var i=o.length-1;i>=0;i--)   
      if (o[i].type == 'file' && o[i].id.indexOf('_upfile') == 0)   
      {   
          document.body.removeChild(o[i]);   
      }       
  
    G('btnAdd').value = '添加附件';   
    G('attachmentList').style.display = 'none';   
    G('btnClear').style.display = 'none';   
       
    G('total').innerText = '当前选择上传0个附件';       
         
}   
  
function G(id)   
{   
    return document.getElementById(id);   
} 
</script>
		<script language="javascript"> 
	function action1() 
	{ 
	   document.form1.action="senting.do"; 
	   document.form1.submit(); 
	} 
	function action2() 
	{ 
	   document.form1.action="savescript.do"; 
	   document.form1.submit(); 
	} 
</script>

	</head>

	<body style="margin-left: 0pt; margin-top: 0pt">

		<form name="form1" method="post">

			<table style="margin-left: 0pt; margin-top: 0pt">
				<tr bgcolor="#f7fdfe" height="35px">
					<td width="100%">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input border="0" TYPE="BUTTON" ONCLICK="action1()"
							class="Btn BtnNml" value="发 送"
							onMouseDown="this.className='Btn BtnHv BtnDw'"
							onMouseOver="this.className='Btn BtnHv';"
							onMouseOut="this.className='Btn BtnNml';" name="reply"
							onFocus="this.blur()" />
						&nbsp;&nbsp;
						<input TYPE="BUTTON" ONCLICK="action2()" class="Btn BtnNml"
							value="保存草稿" onMouseDown="this.className='Btn BtnHv BtnDw'"
							onMouseOver="this.className='Btn BtnHv';"
							onMouseOut="this.className='Btn BtnNml';" name="replyall"
							onFocus="this.blur()" />
						&nbsp;&nbsp;
						<input TYPE="BUTTON" ONCLICK="action3" class="Btn BtnNml"
							value="预 览" onMouseDown="this.className='Btn BtnHv BtnDw'"
							onMouseOver="this.className='Btn BtnHv';"
							onMouseOut="this.className='Btn BtnNml';" name="forward"
							onFocus="this.blur()" />
						&nbsp;&nbsp;
						<input TYPE="BUTTON" ONCLICK="action4" class="Btn BtnNml"
							value="取 消" onMouseDown="this.className='Btn BtnHv BtnDw'"
							onMouseOver="this.className='Btn BtnHv';"
							onMouseOut="this.className='Btn BtnNml';" name="delete"
							onFocus="this.blur()" />
					</td>
				</tr>
			</table>

			<%
				String contactemail = request.getParameter("contactemail");
			%>

			<div class="gCBgWp" style="margin-left: 5pt; margin-top: 5pt">
				<div class="Read_T">
					<table class="rTable"
						style="margin-left: 25px; padding-left: 98px; padding-top: 2px">
						<tr valign="top">
							<td width="10%" align="right">
								<input type="button" value="发件人">
							</td>
							<td>
								<input type="text" disabled="disabled"
									class="login_table_text1_input"
									style="font-weight: bold; font-family: Verdana, Arial, Helvetica, sans-serif"
									value="${user.username }" size="25" />
							</td>
							<td width="45%">
								<font size="2"><a id="cca" href="#" onclick="changeCC()">添加抄送</a>&nbsp;|&nbsp;<a
									href="#" id="bcca" onclick="changeBCC()">添加密送</a>&nbsp;|&nbsp;<a
									id="qfa" href="#" onclick="changeQF()">使用群发单显</a> </font>
							</td>
						</tr>
						<tr valign="top">
							<td align="right" style="padding-top: 5px">
								<input type="button" value="收件人">
							</td>
							<td colspan="2" style="padding-top: 5px">
								<input type="text" class="login_table_text1_input"
									style="font-weight: normal; font-family: Verdana, Arial, Helvetica, sans-serif"
									value="<%=contactemail%>" name="recipients" size="100" />
							</td>
						</tr>
					</table>
					<div style="padding-left: 58px; padding-top: 2px">
						<span id="asd"></span>

						<span id="def"></span>

						<span id="fig"></span>

						<table class="rTable">
							<tr valign="top">
								<td align="right" style="padding-top: 5px">
									<input type="button" value="主&nbsp;&nbsp;&nbsp;题">
								</td>
								<td colspan="2" style="padding-top: 5px">
									<input type="text" class="login_table_text1_input"
										style="font-weight: normal; font-family: Verdana, Arial, Helvetica, sans-serif"
										value="" name="subject" size="100" />
								</td>
							</tr>
							<tr valign="top">
								<td align="left" colspan="3" style="padding-left: 4px">
									<fieldset
										style="width: 750px; border: 1px solid darkgray; text-align: left; COLOR: darkgray; FONT-SIZE: 12px; font-family: Verdana; padding: 5px;">
										<legend>
											添加附件
										</legend>
										<input type="button" value="添加附件" id="btnAdd"
											onclick="selectAttachment();">
										&nbsp;
										<input type="button" value="清空附件" id="btnClear"
											style="display: none" onclick="clearAttachment();">
										<div id="attachmentList"
											style="margin: 3px 0px 0px 0px; padding: 4px 3px 4px 3px; background-color: white; display: none; border: 1px solid darkgray;">
										</div>
										<div id="total" style="margin: 3px 0px 0px 0px;">
											当前选择上传0个附件
										</div>
									</fieldset>
							</tr>
						</table>
					</div>
					<br />

					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<textarea name="content"></textarea>
		</form>
		&nbsp;&nbsp;&nbsp;
	</body>
</html>
