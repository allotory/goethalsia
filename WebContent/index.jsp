<%@ page language="java" pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<link rel="stylesheet" href="css/common.css" type="text/css" />
		<title>鑫荣企业内部邮件系统</title>
	</head>
	<frameset rows="50,*" cols="*" frameborder="no" border="0"
		framespacing="0" name="all">
		<frame src="topframe.jsp" name="topFrame" frameborder="0"
			scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />
		<frameset name="myFrame" cols="199,7,*" frameborder="no" border="0"
			framespacing="0">
			<frame src="leftframe.jsp" name="leftFrame" frameborder="0"
				scrolling="no" noresize="noresize" id="leftFrame" title="leftFrame" />
			<frame src="switchframe.jsp" name="midFrame" frameborder="0"
				scrolling="no" noresize="noresize" id="midFrame" title="midFrame" />
			<frameset rows="59,*" cols="*" frameborder="no" border="0"
				framespacing="0">
				<frame src="mainframe.jsp" name="mainFrame" frameborder="0"
					scrolling="no" noresize="noresize" id="mainFrame" title="mainFrame" />
				<frame src="manframe.jsp" name="manFrame" frameborder="0"
					id="manFrame" title="manFrame" />
			</frameset>
		</frameset>
	</frameset>
	<noframes>
		<body>
		</body>
	</noframes>
</html>

