package com.internousdev.cyan.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.cyan.dao.UserInfoDAO;
import com.internousdev.cyan.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordConfirmAction extends ActionSupport implements SessionAware{

	private String loginId;
	private String password;
	private String newPassword;
	private String reConfirmationPassword;

	private Map<String, Object> session;

	public String execute() {
		String result = ERROR;

		if(!session.containsKey("mCategoryDTOList")){
			return "timeout";
		}
			session.remove("loginIdErrorMessageList");
			session.remove("passwordErrorMessageList");
			session.remove("passwordIncorrectErrorMessageList");
			session.remove("newPasswordErrorMessageList");
			session.remove("reConfirmationNewPasswordErrorMessageList");
			session.remove("newPasswordIncorrectErrorMessageList");

			InputChecker inputChecker = new InputChecker();

			List<String> loginIdErrorMessageList = inputChecker.doCheck("ユーザーID", loginId, 1, 8, true, false, false, true, false, false, false, false, false);
			List<String> passwordErrorMessageList = inputChecker.doCheck("現在のパスワード", password, 1, 16, true, false, false, true, false, false, false, false, false);
			List<String> newPasswordErrorMessageList = inputChecker.doCheck("新しいパスワード", newPassword, 1, 16, true, false, false, true, false, false, false, false, false);
			List<String> reConfirmationNewPasswordErrorMessageList = inputChecker.doCheck("（再確認）", reConfirmationPassword, 1, 16, true, false, false, true, false, false, false, false, false);

			if(loginIdErrorMessageList.size()==0
			&& passwordErrorMessageList.size()==0
			&& newPasswordErrorMessageList.size()==0
			&& reConfirmationNewPasswordErrorMessageList.size()==0) {

				UserInfoDAO userInfoDAO = new UserInfoDAO();
				List<String> passwordIncorrectErrorMessageList = new ArrayList<String>();
				List<String> newPasswordIncorrectErrorMessageList = new ArrayList<String>();

				if(userInfoDAO.isExistsUserInfo(loginId, password)) {
					int beginIndex = 0;
					int endIndex = 1;

					StringBuilder stringBuilder = new StringBuilder("****************");
					String concealedPassword = stringBuilder.replace(beginIndex, endIndex, newPassword.substring(beginIndex,endIndex)).toString();
					session.put("loginId", loginId);
					session.put("newPassword", newPassword);
					session.put("concealedPassword", concealedPassword);
					newPasswordIncorrectErrorMessageList = inputChecker.doPasswordCheck(newPassword, reConfirmationPassword);

					if(newPasswordIncorrectErrorMessageList.size()==0){
						result = SUCCESS;
					}else {
						session.put("newPasswordIncorrectErrorMessageList", newPasswordIncorrectErrorMessageList);
					}
				} else {
					passwordIncorrectErrorMessageList.add("ユーザーIDまたは現在のパスワードが異なります。");
					session.put("passwordIncorrectErrorMessageList", passwordIncorrectErrorMessageList);
				}
			} else {
				session.put("loginIdErrorMessageList", loginIdErrorMessageList);
				session.put("passwordErrorMessageList", passwordErrorMessageList);
				session.put("newPasswordErrorMessageList", newPasswordErrorMessageList);
				session.put("reConfirmationNewPasswordErrorMessageList", reConfirmationNewPasswordErrorMessageList);
			}

		return result;
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

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getReConfirmationPassword() {
		return reConfirmationPassword;
	}

	public void setReConfirmationPassword(String reConfirmationPassword) {
		this.reConfirmationPassword = reConfirmationPassword;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
