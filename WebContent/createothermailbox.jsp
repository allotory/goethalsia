<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<TITLE>创建新文件夹</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=GB18030">
		<META http-equiv=Content-Language content=GB18030>
		<STYLE type=text/css>
A:link {
	COLOR: #555555;
	TEXT-DECORATION: none
}

A:visited {
	COLOR: #555555;
	TEXT-DECORATION: none
}

A:active {
	COLOR: #555555;
	TEXT-DECORATION: none
}

A:hover {
	COLOR: #6f9822;
	TEXT-DECORATION: none
}

.text {
	FONT-SIZE: 12px;
	COLOR: #555555;
	FONT-FAMILY: "";
	TEXT-DECORATION: none
}

.STYLE1 {
	font-size: 13px
}

.STYLE2 {
	font-size: 12px
}

.STYLE3 {
	font-size: 11px
}
</STYLE>

	</head>
	<BODY>
		<BR>
		<BR>
		<BR>
		<BR>
		<BR>
		<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%"
			align=center border=0>

			<TR>
				<TD vAlign="center" align="middle">
					<TABLE cellSpacing=0 cellPadding=0 width=500 align=center border=0>

						<TR>
							<TD width=17 height=17>
								<IMG height=17 src="images/co_01.gif" width=17>
							</TD>
							<TD width=316 background="bg01.gif"></TD>
							<TD width=17 height=17>
								<IMG height=17 src="images/co_02.gif" width=17>
							</TD>
						</TR>
						<TR>
							<TD background=images/bg02.gif></TD>
							<TD>
								<TABLE class=text cellSpacing=0 cellPadding=10 width="100%"
									align=center border=0>
									<TR>
										<TD>
											<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
												<TR>
													<TD width=20>
													</TD>
													<TD>
														<IMG height=66 src="images/createothermailbox.gif"
															width=400>
													</TD>
												</TR>
											</TABLE>
										</TD>
									</TR>
									<TR>
										<TD>
											<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
												<TR>
													<TD background="images/dot_01.gif" height=1></TD>
												</TR>
											</TABLE>
											<BR>
											<logic:present name="mainuname">
												<logic:empty name="mainuname">
													<TABLE class=text cellSpacing=0 cellPadding=0 width="100%"
														border=0>
														<TR>
															<TD width=20></TD>
															<TD>
																<div>
																	<form action="createothermailbox.do">
																		<logic:notPresent name="otherMailBoxTypeList">
																		对不起，查询其他邮箱类型错误，请稍候重试！
																	</logic:notPresent>
																		<logic:present name="otherMailBoxTypeList">
																			<logic:empty name="otherMailBoxTypeList">
																			对不起，当前没有其他邮箱类型！
																		</logic:empty>
																			<logic:notEmpty name="otherMailBoxTypeList">
																			邮箱类型:
																			<select name="tid">
																					<option>
																						------请选择------
																					</option>
																					<c:forEach items="${otherMailBoxTypeList}" var="t">
																						<option value="${t.tid }">
																							${t.tname }
																						</option>
																					</c:forEach>
																				</select>
																			</logic:notEmpty>
																		</logic:present>
																		<br>
																		用&nbsp;户&nbsp;名：
																		<input type="text" name="uname" />
																		<br>
																		密 &nbsp;&nbsp;&nbsp;码：
																		<input type="password" name="upass" />
																		<br>
																		<input type="submit" value="创建" />
																	</form>
																</div>
															</TD>
														</TR>
													</TABLE>
												</logic:empty>
												<logic:notEmpty name="mainuname">
													<TABLE class=text cellSpacing=0 cellPadding=0 width="100%"
														border=0>
														<TR>
															<TD width=20></TD>
															<TD>
																<div>
																	<form action="createothermailbox.do">
																		<logic:notPresent name="otherMailBoxTypeList">
																		对不起，查询其他邮箱类型错误，请稍候重试！
																	</logic:notPresent>
																		<logic:present name="otherMailBoxTypeList">
																			<logic:empty name="otherMailBoxTypeList">
																			对不起，当前没有其他邮箱类型！
																		</logic:empty>
																			<logic:notEmpty name="otherMailBoxTypeList">
																			邮箱类型:
																			<select name="tid">
																					<option>
																						------请选择------
																					</option>
																					<c:forEach items="${otherMailBoxTypeList}" var="t">
																						<option value="${t.tid }">
																							${t.tname }
																						</option>
																					</c:forEach>
																				</select>
																			</logic:notEmpty>
																		</logic:present>
																		<br>
																		用&nbsp;户&nbsp;名：
																		<input type="text" name="uname" value="${mainuname }" />
																		<br>
																		密 &nbsp;&nbsp;&nbsp;码：
																		<input type="password" name="upass" />
																		<br>
																		<input type="submit" value="创建" />
																	</form>
																</div>
															</TD>
														</TR>
													</TABLE>
												</logic:notEmpty>
											</logic:present>
											<logic:notPresent name="mainuname">
												<TABLE class=text cellSpacing=0 cellPadding=0 width="100%"
													border=0>
													<TR>
														<TD width=20></TD>
														<TD>
															<div>
																<form action="createothermailbox.do">
																	<logic:notPresent name="otherMailBoxTypeList">
																	对不起，查询其他邮箱类型错误，请稍候重试！
																</logic:notPresent>
																	<logic:present name="otherMailBoxTypeList">
																		<logic:empty name="otherMailBoxTypeList">
																		对不起，当前没有其他邮箱类型！
																	</logic:empty>
																		<logic:notEmpty name="otherMailBoxTypeList">
																		邮箱类型:
																		<select name="tid">
																				<option>
																					------请选择------
																				</option>
																				<c:forEach items="${otherMailBoxTypeList}" var="t">
																					<option value="${t.tid }">
																						${t.tname }
																					</option>
																				</c:forEach>
																			</select>
																		</logic:notEmpty>
																	</logic:present>
																	<br>
																	用&nbsp;户&nbsp;名：
																	<input type="text" name="uname" />
																	<br>
																	密 &nbsp;&nbsp;&nbsp;码：
																	<input type="password" name="upass" />
																	<br>
																	<input type="submit" value="创建" />
																</form>
															</div>
														</TD>
													</TR>
												</TABLE>
											</logic:notPresent>

										</TD>
									</TR>
								</TABLE>
							</TD>
							<TD background="images/bg03.gif"></TD>
						</TR>
						<TR>
							<TD width=17 height=17>
								<IMG height=17 src="images/co_03.gif" width=17>
							</TD>
							<TD background="images/bg04.gif" height=17></TD>
							<TD width=17 height=17>
								<IMG height=17 src="images/co_04.gif" width=17>
							</TD>
						</TR>
					</TABLE>
					<TABLE class=text cellSpacing=0 cellPadding=0 width=500
						align=center border=0>
						<TR>
							<TD></TD>
						</TR>
						<TR>
							<TD align="middle"></TD>
						</TR>
					</TABLE>
				</TD>
			</TR>
		</TABLE>
	</BODY>