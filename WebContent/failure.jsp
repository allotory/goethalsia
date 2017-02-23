<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
	<HEAD>
		<TITLE>Sorry!</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=gb2312">
		<STYLE type=text/css>
TD {
	FONT-SIZE: 9pt;
	FONT-FAMILY: "Arial", "Helvetica, " sans-serif "
}

BODY {
	FONT-SIZE: 9pt;
	FONT-FAMILY: "Arial", "Helvetica", "sans-serif"
}

.tbl1 {
	BORDER-RIGHT: #3f5294 1px solid;
	BORDER-TOP: #3f5294 1px solid;
	FONT-SIZE: 9pt;
	BORDER-LEFT: #3f5294 1px solid;
	BORDER-BOTTOM: #3f5294 1px solid
}

.td1 {
	BORDER-RIGHT: #ffffff 0px solid;
	BORDER-TOP: #ffffff 1px solid;
	BORDER-LEFT: #ffffff 1px solid;
	BORDER-BOTTOM: #ffffff 0px solid
}
</STYLE>

		<STYLE type=text/css>
A {
	COLOR: #000000;
	TEXT-DECORATION: none
}

A:hover {
	COLOR: #ff0000;
	TEXT-DECORATION: none
}
</STYLE>

		<STYLE type=text/css>
.style6 {
	FONT-FAMILY: "Courier New", Courier, mono
}
</STYLE>

	</HEAD>
	<BODY bgColor=#ffffff>
		<P>
			&nbsp;
		</P>
		<TABLE height=382 cellSpacing=0 cellPadding=0 width=470 align=center
			border=0>
			<TBODY>
				<TR>
					<TD vAlign=top background="images/bg.gif">
						<BR>
						<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
							<TBODY>
								<TR>
									<TD width="14%">
										&nbsp;
									</TD>
									<TD width="86%">
										<TABLE style="FILTER: glow(color =   #ffffff, strength =   5)"
											width="100%" align=center>
											<TBODY>
												<TR>
													<TD align=center height=14>
														<SPAN class=style6><FONT color=#ff0000 size=6>Sorry!</FONT>
														</SPAN>
													</TD>
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
								<TR>
									<TD>
										&nbsp;
									</TD>
									<TD>
										<DIV align=center>
											<TABLE cellSpacing=2 cellPadding=0 width="100%" align=center
												border=0>
												<TBODY>
													<TR>
														<TD>
															<logic:present name="flag">

																<c:set value="passwordnotequal" var="passwordnotequal" />
																<c:set value="checkcodeerror" var="checkcodeerror" />
																<c:set value="nameorpasserror" var="nameorpasserror" />
																<c:set value="delfoldererror" var="delfoldererror" />
																<c:set value="deletenotetypeerror"
																	var="deletenotetypeerror" />
																<c:set value="checkboxmustchose" var="checkboxmustchose" />
																<c:set value="iscontaincontact" var="iscontaincontact" />
																<c:set value="answererror" var="answererror" />
																<c:set value="contactalreadyexist"
																	var="contactalreadyexist" />

																<c:if test="${answererror eq flag}">
																	<center>
																		对不起！密码提示问题错误，请确认！
																	</center>
																</c:if>
																
																<c:if test="${passwordnotequal eq flag}">
																	<center>
																		对不起！两次密码不一致，注册失败！
																	</center>
																</c:if>

																<c:if test="${checkcodeerror eq flag}">
																	<center>
																		对不起！验证码不正确，请重新确认！
																	</center>
																</c:if>

																<c:if test="${nameorpasserror eq flag}">
																	<center>
																		对不起！用户名或密码错误！
																	</center>
																</c:if>

																<c:if test="${delfoldererror eq flag}">
																	<center>
																		对不起！您所要删除的文件夹包括我的文档（图片、音乐、多媒体、软件）等系统设定内容，不能被删除！
																	</center>
																</c:if>

																<c:if test="${deletenotetypeerror eq flag}">
																	<center>
																		对不起！您所要删除的记事本类型包含记事本，请删除该类型记事本后，再删除该记事本类型！
																	</center>
																</c:if>

																<c:if test="${checkboxmustchose eq flag}">
																	<center>
																		对不起！你必须至少选择一个！
																	</center>
																</c:if>

																<c:if test="${iscontaincontact eq flag}">
																	<center>
																		对不起！你选择的联系人组包含联系人，请确认联系人已删除！
																	</center>
																</c:if>

																<c:if test="${contactalreadyexist eq flag}">
																	<center>
																		对不起！你添加的联系人已经存在！
																	</center>
																</c:if>
															</logic:present>
														</TD>
													</TR>
													<TR>
														<TD align="left">
														</TD>
													</TR>
												</TBODY>
											</TABLE>
											<BR>
										</DIV>
									</TD>
								</TR>
							</TBODY>
						</TABLE>
						<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
							<TBODY>
								<TR>
									<TD width="38%">
										&nbsp;
									</TD>
									<TD width="17%">
										<DIV align=right>
											<A href="#"><IMG height=38 src="images/jt.gif" width=56
													border=0> </A>
										</DIV>
									</TD>
									<TD width="45%">
										<DIV align=center>
											<A href="#" onclick="history.back()"><FONT color=#ff0000>[由此返回上页]</FONT>
											</A>
										</DIV>
									</TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		<P>
			&nbsp;
		</P>
	</body>
</html>
</BODY>
</HTML>