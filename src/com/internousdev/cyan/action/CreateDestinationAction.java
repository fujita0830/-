package com.internousdev.cyan.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class CreateDestinationAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;
	int createDestinationFlg;

	public String execute() {
		String result = SUCCESS;

		if(!session.containsKey("mCategoryDTOList")) {
			return "timeout";
		}

		if(createDestinationFlg==0){

			session.remove("createDestinationFamilyName");
			session.remove("createDestinationFirstName");
			session.remove("createDestinationFamilyNameKana");
			session.remove("createDestinationFirstNameKana");
			session.remove("createDestinationUserAddress");
			session.remove("createDestinationTelNumber");
			session.remove("createDestinationEmail");

		}

		session.remove("familyNameErrorMessageList");
		session.remove("firstNameErrorMessageList");
		session.remove("familyNameKanaErrorMessageList");
		session.remove("firstNameKanaErrorMessageList");
		session.remove("emailErrorMessageList");
		session.remove("telNumberErrorMessageList");
		session.remove("userAddressErrorMessageList");

	return result;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public int getCreateDestinationFlg() {
		return createDestinationFlg;
	}

	public void setCreateDestinationFlg(int createDestinationFlg) {
		this.createDestinationFlg = createDestinationFlg;
	}

}
