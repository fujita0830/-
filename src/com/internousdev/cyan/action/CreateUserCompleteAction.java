package com.internousdev.cyan.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.cyan.dao.UserInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class CreateUserCompleteAction extends ActionSupport implements SessionAware{

	private String familyName;
	private String firstName;
	private String familyNameKana;
	private String firstNameKana;
	private String sex;
	private String email;
	private String loginId;
	private String password;
	private Map<String, Object> session;
	public String execute() {
		String result = ERROR;
		if(!session.containsKey("mCategoryDTOList")){
			return "timeout";
		}
			UserInfoDAO UserInfoDAO = new UserInfoDAO();
			int count = UserInfoDAO.createUser(String.valueOf(session.get("createUserFamilyName")),
					String.valueOf(session.get("createUserFirstName")),
					String.valueOf(session.get("createUserFamilyNameKana")),
					String.valueOf(session.get("createUserFirstNameKana")),
					String.valueOf(session.get("createUserSexValue")),
					String.valueOf(session.get("createUserEmail")),
					String.valueOf(session.get("createUserLoginId")),
					String.valueOf(session.get("createUserPassword")));
			if(count > 0) {
				result = SUCCESS;
				session.put("loginId", session.get("createUserLoginId"));
				session.put("password", session.get("createUserPassword"));
				session.remove("createUserFamilyName");
				session.remove("createUserFirstName");
				session.remove("createUserFamilyNameKana");
				session.remove("createUserFirstNameKana");
				session.remove("createUserSexValue");
				session.remove("createUserEmail");
				session.remove("createUserLoginId");
				session.remove("createUserPassword");
		}
		return result;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFamilyNameKana() {
		return familyNameKana;
	}

	public void setFamilyNameKana(String familyNameKana) {
		this.familyNameKana = familyNameKana;
	}

	public String getFirstNameKana() {
		return firstNameKana;
	}

	public void setFirstNameKana(String firstNameKana) {
		this.firstNameKana = firstNameKana;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
