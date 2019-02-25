package com.internousdev.cyan.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class CreateUserAction extends ActionSupport implements SessionAware{
	private String familyName;
	private String firstName;
	private String familyNameKana;
	private String firstNameKana;
	private String sex;
	private List<String> sexList = new ArrayList<String>();
	private String email;
	private String loginId;
	private String password;
	private static final String MALE = "男性";
	private static final String FEMALE = "女性";
	private Map<String, Object> session;
	private int createUserFlg;

	public int getCreateUserFlg() {
		return createUserFlg;
	}
	public void setCreateUserFlg(int createUserFlg) {
		this.createUserFlg = createUserFlg;
	}
	public String execute() {
		String result = SUCCESS;
		if(!session.containsKey("mCategoryDTOList")){
			return "timeout";
		}
			session.remove("familyNameErrorMessageList");
			session.remove("firstNameErrorMessageList");
			session.remove("familyNameKanaErrorMessageList");
			session.remove("firstNameKanaErrorMessageList");
			session.remove("emailErrorMessageList");
			session.remove("userIdErrorMessageList");
			session.remove("passwordErrorMessageList");
			session.remove("loginIdIncorrectErrorMessageList");
			session.remove("loginIdPasswordErrorMessageList");
			session.remove("loginIdErrorMessageList");

			if(createUserFlg==0){
				session.remove("familyName");
				session.remove("firstName");
				session.remove("familyNameKana");
				session.remove("firstNameKana");
				session.remove("sex");
				session.remove("email");
				session.remove("loginId");
				session.remove("password");
			}

			if(session.get("sex") == null) {
				session.put("sex", MALE);
			}else{
				session.put("sex",String.valueOf(session.get("sex")));
			}
			sexList.add(MALE);
			sexList.add(FEMALE);
			session.put("sexList", sexList);

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

	public List<String> getSexList() {
		return sexList;
	}

	public void setSexList(List<String> sexList) {
		this.sexList = sexList;
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
