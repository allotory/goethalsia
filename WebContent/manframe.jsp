<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GB18030" />
		<link href="css/globle_v1.css" rel="stylesheet" type="text/css">
		<link href="css/welcome2.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" href="css/common.css" type="text/css" />
		<link href="css/skin_blue.css" rel="stylesheet" type="text/css"
			id="lnkSkin" />
		<title>管理区域</title>
	</head>

	<body>
		<div id="man_zone">
			<div class="ContentWp">

				<div class="ContentThemeWp">
					<div class="F2Img w2_HeadWp">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="w2_HeadTb">
							<tr>
								<td class="User_tdWp">
									<div class="w2_U_Wp">
										<h1>
											您好，欢迎登陆MyMail邮箱系统！
										</h1>
										<b class="IcoImg IcoNwMail"></b>
										<h2>
											您有&nbsp;
											<a href="box.jsp" title="阅读未读邮件" class="fnt_Red"><b>${unreadcount
													}</b>
											</a>&nbsp;封&nbsp;
											<a href="goreceive.do" title="阅读未读邮件">未读邮件</a>
										</h2>
										<div class="w2_Rong">
											<div class="rng_DcrTxt">
												邮箱容量：
												<logic:present name="emailspace">
													<c:out value="${emailspace.espace/1024}" />
												</logic:present>
												G，已使用
												<logic:present name="emailspace">
													<fmt:formatNumber
														value="${(emailspace.espace-emailspace.spaceleft)}"
														pattern="####.###" />
												</logic:present>
												M
											</div>
											<div class="WelImg rng_PcsWp">
												<div class="WelImg rng_Pcs"
													style="width: ${(( emailspace.espace-emailspace.spaceleft )/ emailspace.espace ) * 100}"></div>
												<div class="rng_PcsTxt">
													<fmt:formatNumber
														value="${((emailspace.espace-emailspace.spaceleft)/emailspace.espace)*100}"
														pattern="##.###" />
													%
												</div>
											</div>
										</div>
									</div>
								</td>
								<td class="F2Img Mid_LineWp"
									style="background-repeat: no-repeat;"></td>
								<td class="Weather_tdWp"></td>
							</tr>
						</table>
					</div>
					<div class="w2_MidWp">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="w2_MidTb">
							<tr>
								<td class="Mid_JJ"></td>
								<td class="Mid_lWp">
									<div class="w2cbx cbx_yxFW">
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											class="F2Img w2cbx_Top">
											<tr>
												<td class="WelImg C_L"></td>
												<td class="F2Img M_Rp">
													&nbsp;
												</td>
												<td class="WelImg C_R"></td>
											</tr>
										</table>
										<div class="cbx_TabWp">
											<ul class="cbx_TabIn">
												<li class="WelImg On">
													<a href="javascript:void(0);" title="">邮箱服务</a>
												</li>
											</ul>
										</div>
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											class="w2cbx_Mid">
											<tr>
												<td class="WelImg L_Rp"></td>
												<td class="M_C">
													<div class="M_C_Inr">
														<div class="F2Img w2ft_Bnr bTj_sdy">
															<table border="0" cellspacing="0" cellpadding="0"
																width="99%" class="BnrTj_Tb">
																<tr>
																	<td class="BnrTj_ImgWp">
																		<div class="BnrTj_Img WelImg"></div>
																	</td>
																	<td class="BnrTj_TtWp" nowrap>
																		<h3>
																			欢迎登陆MyMail邮箱系统！
																		</h3>
																		<div class="BnrTj_DcrWp" nowrap>
																			更安全……
																		</div>
																		<div class="BnrTj_ExLink" nowrap>
																			<a href="about.html" target="_blank" title="">更多了解&gt;&gt;</a>
																		</div>
																	</td>
																</tr>
															</table>
														</div>
														<table width="100%" border="0" cellspacing="0"
															cellpadding="0" class="sTj_WpTb">
															<tr>
																<td class="Bx Bx_Huangfu">
																	<table border="0" cellspacing="0" cellpadding="0"
																		width="100%" class="sTj_Tb">
																		<tr>
																			<td rowspan="2" class="sTj_ImgWp">
																				<div class="sTj_Img WelImg"></div>
																			</td>
																			<td class="sTj_TtWp" nowrap>
																				<h4>
																					<a href="gonotebook.do" title="记事本">记事本</a>
																				</h4>
																			</td>
																		</tr>
																		<tr>
																			<td class="sTj_DcrWp" nowrap>
																				记录想法、文章、心情
																			</td>
																		</tr>
																	</table>
																</td>
																<td class="Bx Bx_Laixinfenlei">
																	<table border="0" cellspacing="0" cellpadding="0"
																		width="100%" class="sTj_Tb">
																		<tr>
																			<td rowspan="2" class="sTj_ImgWp">
																				<div class="sTj_Img WelImg"></div>
																			</td>
																			<td class="sTj_TtWp" nowrap>
																				<h4>
																					<a href="gocontactbox.do" title="通讯录">通讯录</a>
																				</h4>
																			</td>
																		</tr>
																		<tr>
																			<td class="sTj_DcrWp" nowrap>
																				通讯录
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td class="Bx Bx_Qianxin">
																	<table border="0" cellspacing="0" cellpadding="0"
																		width="100%" class="sTj_Tb">
																		<tr>
																			<td rowspan="2" class="sTj_ImgWp">
																				<div class="sTj_Img WelImg"></div>
																			</td>
																			<td class="sTj_TtWp" nowrap>
																				<h4>
																					<a href="goothermailbox.do" title="邮箱搬家">邮箱搬家</a>
																				</h4>
																			</td>
																		</tr>
																		<tr>
																			<td class="sTj_DcrWp" nowrap>
																				加入您的其他邮箱
																			</td>
																		</tr>
																	</table>
																</td>
																<td class="Bx Bx_Daohang">
																	<table border="0" cellspacing="0" cellpadding="0"
																		width="100%" class="sTj_Tb">
																		<tr>
																			<td rowspan="2" class="sTj_ImgWp">
																				<div class="sTj_Img WelImg"></div>
																			</td>
																			<td class="sTj_TtWp" nowrap>
																				<h4>
																					<a href="gowebdiskfolder.do" title="记事本">网络硬盘</a>
																				</h4>
																			</td>
																		</tr>
																		<tr>
																			<td class="sTj_DcrWp" nowrap>
																				保存文档、图片、软件
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
													</div>
												</td>
												<td class="WelImg R_Rp"></td>
											</tr>
										</table>
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											class="w2cbx_Btm">
											<tr>
												<td class="WelImg C_L"></td>
												<td class="F2Img M_Rp">
													&nbsp;
												</td>
												<td class="WelImg C_R"></td>
											</tr>
										</table>
									</div>
								</td>
								<td class="Mid_JJ"></td>
								<td class="Mid_rWp">
									<div class="w2cbx cbx_yxBJ F2Img" style="margin-bottom: 14px;">
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											class="F2Img w2cbx_Top"
											style="height: 6px; font-size: 1px; line-height: 1px;">
											<tr>
												<td class="WelImg C_L"></td>
												<td class="F2Img M_Rp">
													&nbsp;
												</td>
												<td class="WelImg C_R"></td>
											</tr>
										</table>
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											class="w2cbx_Mid">
											<tr>
												<td class="WelImg L_Rp"></td>
												<td class="M_C">
													<div class="M_C_Inr">
														<div class="C_Tt">
															<a href="#" title="邮箱搬家">邮箱搬家</a>
														</div>
														<div class="C_Dcr">
															让易邮邮箱收取、管理您的其它邮箱邮件
														</div>
														<form method="post" action="addothermailbox.do">
															<div class="C_Ipt">
																<input type="text" name="uname" class="Ipt IptNml"
																	value="例：user@sina.com"
																	onClick="if(this.value=='例：user@sina.com')this.value=''" />
																<input type="submit" class="Btn BtnNml" value="下一步" />
															</div>
														</form>
													</div>
												<td class="WelImg R_Rp"></td>
											</tr>
										</table>
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											class="w2cbx_Btm">
											<tr>
												<td class="WelImg C_L"></td>
												<td class="F2Img M_Rp">
													&nbsp;
												</td>
												<td class="WelImg C_R"></td>
											</tr>
										</table>
									</div>
									<div class="w2cbx cbx_yxTJ">
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											class="F2Img w2cbx_Top">
											<tr>
												<td class="WelImg C_L"></td>
												<td class="F2Img M_Rp">
													信息公告
												</td>
												<td class="WelImg C_R"></td>
											</tr>
										</table>
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											class="w2cbx_Mid">
											<tr>
												<td class="WelImg L_Rp"></td>
												<td class="M_C">
													<div class="M_C_Inr">
														<div class=""></div>
													</div>
												</td>
												<td class="WelImg R_Rp"></td>
											</tr>
										</table>
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											class="w2cbx_Btm">
											<tr>
												<td class="WelImg C_L"></td>
												<td class="F2Img M_Rp">
													&nbsp;
												</td>
												<td class="WelImg C_R"></td>
											</tr>
										</table>
									</div>
								</td>
								<td class="Mid_JJ"></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
