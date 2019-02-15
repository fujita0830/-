package com.internousdev.cyan.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.cyan.dao.UserInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordCompleteAction extends ActionSupport implements SessionAware{

	private Map<String,Object> session;

	public String execute() {
		String result = ERROR;
		if(!session.containsKey("mCategoryDTOList")){
			return"timeout";
		}
			UserInfoDAO userInfoDAO = new UserInfoDAO();
			int count = userInfoDAO.resetPassword(String.valueOf(session.get("loginId")),String.valueOf(session.get("newPassword")));
			if(count > 0) {
				result = SUCCESS;
			}else {
				result = ERROR;
			}
		return result;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
