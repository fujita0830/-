package com.internousdev.cyan.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordAction extends ActionSupport implements SessionAware{

	private Map<String,Object> session;
	int resetPasswordFlg;

	public String execute() {

		String result = SUCCESS;

		if(!session.containsKey("mCategoryDTOList")){
			return "timeout";
		}

		if(resetPasswordFlg==0) {
			session.remove("resetPasswordLoginId");
		}
			session.remove("loginIdErrorMessageList");
			session.remove("passwordErrorMessageList");
			session.remove("passwordIncorrectErrorMessageList");
			session.remove("newPasswordErrorMessageList");
			session.remove("reConfirmationNewPasswordErrorMessageList");
			session.remove("newPasswordIncorrectErrorMessageList");

		return result;

	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public int getResetPasswordFlg() {
		return resetPasswordFlg;
	}

	public void setResetPasswordFlg(int resetPasswordFlg) {
		this.resetPasswordFlg = resetPasswordFlg;
	}

}
