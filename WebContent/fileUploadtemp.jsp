<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<link rel="stylesheet" href="css/common.css" type="text/css" />
		<title>��������</title>
	</head>

	<body>
		<%
			int filetypeid = Integer.parseInt(request
					.getParameter("filetypeid"));
			//System.out.println("filetypeid(fileuploadtemp):"+filetypeid);
		%>
		<div id="man_zone">
			<iframe name="content_frame" marginwidth=0 marginheight=0 width=100%
				height=100% src="fileUpload.jsp?filetypeid=<%=filetypeid%>"
				frameborder=0></iframe>
		</div>
	</body>
</html>

