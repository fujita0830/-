package com.internousdev.cyan.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordAction extends ActionSupport implements SessionAware{
	private String categoryId;
	private String loginId;
	private Map<String,Object> session;
	public String execute() {
		String result = SUCCESS;
		if(!session.containsKey("mCategoryDTOList")){
			result="timeout";
		}else{
			session.remove("loginIdErrorMessageList");
			session.remove("passwordErrorMessageList");
			session.remove("passwordIncorrectErrorMessageList");
			session.remove("newPasswordErrorMessageList");
			session.remove("reConfirmationNewPasswordErrorMessageList");
			session.remove("newPasswordIncorrectErrorMessageList");
		}
		return result;

	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
}
