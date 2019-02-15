package com.internousdev.cyan.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.cyan.dao.CartInfoDAO;
import com.internousdev.cyan.dao.UserInfoDAO;
import com.internousdev.cyan.dto.CartInfoDTO;
import com.internousdev.cyan.dto.UserInfoDTO;
import com.internousdev.cyan.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;


public class LoginAction extends ActionSupport implements SessionAware {
	private String loginId;
	private String password;
	private boolean savedLoginId;

	private Map<String, Object> session;

	@SuppressWarnings("unchecked")
	public String execute() {
		String result = ERROR;
		if(!session.containsKey("mCategoryDTOList")){
			return "timeout";
			}
		session.remove("loginIdErrorMessageList");
		session.remove("passwordErrorMessageList");
		session.remove("loginIdPasswordErrorMessageList");

		if(savedLoginId==true) {
			session.put("savedLoginId", true);
			session.put("loginId", loginId);
		} else {
			session.put("savedLoginId", false);
			session.remove("loginId", loginId);
		}

		List<String> loginIdErrorMessageList = new ArrayList<String>();
		List<String> passwordErrorMessageList = new ArrayList<String>();
		List<String> loginIdPasswordErrorMessageList = new ArrayList<String>();

		InputChecker inputChecker = new InputChecker();
		loginIdErrorMessageList = inputChecker.doCheck("ユーザーID", loginId, 1, 8, true, false, false, true, false, false, false, false, false);
		passwordErrorMessageList = inputChecker.doCheck("パスワード", password, 1, 16, true, false, false, true, false, false, false, false, false);

		if(loginIdErrorMessageList.size()!=0
		|| passwordErrorMessageList.size()!=0) {
			session.put("loginIdErrorMessageList", loginIdErrorMessageList);
			session.put("passwordErrorMessageList", passwordErrorMessageList);
			session.put("logined", 0);
			return result;
		}

		UserInfoDAO userInfoDAO = new UserInfoDAO();
		if(userInfoDAO.isExistsUserInfo(loginId, password)) {
			if(userInfoDAO.login(loginId, password) > 0) {


				// カートの情報をユーザーに紐づける。
				List<CartInfoDTO> cartInfoDTOList  = new ArrayList<CartInfoDTO>();
				//sessionからカート情報を取得
				cartInfoDTOList = (List<CartInfoDTO>) session.get("cartInfoDTOList");
				if (cartInfoDTOList != null) {
					changeCartInfo(cartInfoDTOList);
				}
				// 次の遷移先を設定
				if (session.containsKey("cartFlag")) {
					session.remove("cartFlag");
					result = "cart";
				} else {
					result = SUCCESS;
				}
			}
			// ユーザー情報をsessionに登録する
			UserInfoDTO userInfoDTO = userInfoDAO.getUserInfo(loginId, password);
			session.put("loginId", userInfoDTO.getUserId());
			session.put("logined", 1);
		} else {
			loginIdPasswordErrorMessageList.add("ユーザーIDまたはパスワードが異なります。");
			session.put("loginIdPasswordErrorMessageList", loginIdPasswordErrorMessageList);
		}
		return result;
	}

	private void changeCartInfo(List<CartInfoDTO> cartInfoDTOList) {
		CartInfoDAO cartInfoDAO = new CartInfoDAO();
		int count = 0;
     	String tempUserId = session.get("tempUserId").toString();

		for (CartInfoDTO dto : cartInfoDTOList) {
			if (cartInfoDAO.isExistsCartInfo(loginId, dto.getProductId())) {
				count += cartInfoDAO.updateProductCount(loginId, dto.getProductId(), dto.getProductCount());
				cartInfoDAO.delete(String.valueOf(dto.getProductId()), tempUserId);
			} else {
				count += cartInfoDAO.linkToLoginId(tempUserId,loginId);
			}
		}

		if (count == cartInfoDTOList.size()) {
			List<CartInfoDTO> newCartInfoDTOList = cartInfoDAO.getCartInfoDTOList(loginId);
			Iterator<CartInfoDTO> iterator = newCartInfoDTOList.iterator();
			if(!(iterator.hasNext())) {
				newCartInfoDTOList = null;
			}
			session.put("cartInfoDTOList", newCartInfoDTOList);
		}
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

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
