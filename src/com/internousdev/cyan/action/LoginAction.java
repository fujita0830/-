package com.internousdev.cyan.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.cyan.dao.CartInfoDAO;
import com.internousdev.cyan.dao.DestinationInfoDAO;
import com.internousdev.cyan.dao.UserInfoDAO;
import com.internousdev.cyan.dto.DestinationInfoDTO;
import com.internousdev.cyan.dto.UserInfoDTO;
import com.internousdev.cyan.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware {
	private String categoryId;
	private String loginId;
	private String password;
	private boolean savedLoginId;

	private List<String> loginIdErrorMessageList = new ArrayList<String>();
	private List<String> passwordErrorMessageList = new ArrayList<String>();
	private List<String> loginIdPasswordErrorMessageList = new ArrayList<String>();

	private Map<String, Object> session;

	public String execute() {
		String result = ERROR;

		session.put("loginIdErrorMessageList", "");
		session.put("passwordErrorMessageList", "");

		if(savedLoginId==true) {
			session.put("savedLoginId", true);
			session.put("loginId", loginId);
		} else {
			session.put("savedLoginId", false);
			session.remove("loginId", loginId);
		}

		InputChecker inputChecker = new InputChecker();
		loginIdErrorMessageList = inputChecker.doCheck("ユーザーID", loginId, 1, 8, true, false, false, true, false, false, false, false, false);
		passwordErrorMessageList = inputChecker.doCheck("パスワード", password, 1, 16, true, false, false, true, false, false, false, false, false);

		if(loginIdErrorMessageList.size()!=0
		|| passwordErrorMessageList.size()!=0) {
			session.put("loginIdErrorMessageList", loginIdErrorMessageList);
			session.put("passwordErrorMessageList", passwordErrorMessageList);
			session.put("logined", 0);
		}

		if(!session.containsKey("mCategoryDTOList")){
			result="timeout";
			}

		UserInfoDAO userInfoDAO = new UserInfoDAO();
		if(userInfoDAO.isExistsUserInfo(loginId, password)) {
			if(userInfoDAO.login(loginId, password) > 0) {
				UserInfoDTO userInfoDTO = userInfoDAO.getUserInfo(loginId, password);
				session.put("loginId", userInfoDTO.getUserId());
				int count=0;
				CartInfoDAO cartInfoDAO = new CartInfoDAO();

				count = cartInfoDAO.linkToLoginId(String.valueOf(session.get("tempUserId")), loginId);
				if(count > 0) {
					DestinationInfoDAO destinationInfoDAO = new DestinationInfoDAO();
					try {
						List<DestinationInfoDTO> destinationInfoDTOList = new ArrayList<DestinationInfoDTO>();
						destinationInfoDTOList = destinationInfoDAO.getDestinationInfo(loginId);
						Iterator<DestinationInfoDTO> iterator = destinationInfoDTOList.iterator();
						if(!(iterator.hasNext())) {
							destinationInfoDTOList = null;
						}
						session.put("destinationInfoDTOList", destinationInfoDTOList);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					result = "cart";
				} else {
					result = SUCCESS;
				}
			}
			session.put("logined", 1);
		} else {
			loginIdPasswordErrorMessageList.add("ユーザーIDまたはパスワードが異なります。");
			session.put("loginIdPasswordErrorMessageList", loginIdPasswordErrorMessageList);
		}

		return result;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
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

	public boolean isSavedLoginId() {
		return savedLoginId;
	}

	public void setSavedLoginId(boolean savedLoginId) {
		this.savedLoginId = savedLoginId;
	}

	public List<String> getLoginIdErrorMessageList() {
		return loginIdErrorMessageList;
	}

	public void setLoginIdErrorMessageList(List<String> loginIdErrorMessageList) {
		this.loginIdErrorMessageList = loginIdErrorMessageList;
	}

	public List<String> getPasswordErrorMessageList() {
		return passwordErrorMessageList;
	}

	public void setPasswordErrorMessageList(List<String> passwordErrorMessageList) {
		this.passwordErrorMessageList = passwordErrorMessageList;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
