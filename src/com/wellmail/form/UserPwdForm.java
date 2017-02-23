package com.wellmail.form;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class UserPwdForm extends ActionForm {

	private String question;
	
	private String answer;

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
	
}
