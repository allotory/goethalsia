package com.wellmail.form;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class UsersForm extends ActionForm {

	private String username;
	
	private String pwdHash;
	
	private String pwdHashConfirm;
	
	private String pwdAlgorithm;
	
	private int useForwarding;
	
	private String forwardDestination;
	
	private int useAlias;
	
	private String alias;
	
	//验证码
	private String checkcode;
	
	//密码提示问题
	private String question;
	
	//密码提示问题答案
	private String answer;
	
	//性别
	private String sex;
	
	//出生年
	private String year;
	
	//初生月
	private String month;
	
	//出生日
	private String day;
	
	//电话
	private String tel;

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwdHash() {
		return pwdHash;
	}

	public void setPwdHash(String pwdHash) {
		this.pwdHash = pwdHash;
	}

	public String getPwdAlgorithm() {
		return pwdAlgorithm;
	}

	public void setPwdAlgorithm(String pwdAlgorithm) {
		this.pwdAlgorithm = pwdAlgorithm;
	}

	public int getUseForwarding() {
		return useForwarding;
	}

	public void setUseForwarding(int useForwarding) {
		this.useForwarding = useForwarding;
	}

	public String getForwardDestination() {
		return forwardDestination;
	}

	public void setForwardDestination(String forwardDestination) {
		this.forwardDestination = forwardDestination;
	}

	public int getUseAlias() {
		return useAlias;
	}

	public void setUseAlias(int useAlias) {
		this.useAlias = useAlias;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public String getPwdHashConfirm() {
		return pwdHashConfirm;
	}

	public void setPwdHashConfirm(String pwdHashConfirm) {
		this.pwdHashConfirm = pwdHashConfirm;
	}
}
