package com.internousdev.cyan.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.cyan.dao.CartInfoDAO;
import com.internousdev.cyan.dto.CartInfoDTO;
import com.internousdev.cyan.dto.MCategoryDTO;
import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport implements SessionAware {

	private String categoryId;
	private String keywords;
	private List<MCategoryDTO> mCategoryDTOList = new ArrayList<MCategoryDTO>();
	private Map<String, Object> session;

	public String execute() {
		String result = ERROR;
		String userId = null;
		CartInfoDAO cartInfoDAO = new CartInfoDAO();
		List<CartInfoDTO> cartInfoDTOList = new ArrayList<CartInfoDTO>();
		session.remove("checkListErrorMessageList");
		if(session.get("logined").equals(1)) {
			userId = String.valueOf(session.get("loginId"));
		}else if (session.containsKey("tempUserId")) {
			userId = String.valueOf(session.get("tempUserId"));
		}
		cartInfoDTOList = cartInfoDAO.getCartInfoDTOList(userId);
		Iterator<CartInfoDTO> iterator = cartInfoDTOList.iterator();
		if(!(iterator.hasNext())) {
			cartInfoDTOList = null;
		}
		session.put("cartInfoDTOList", cartInfoDTOList);
		int totalPrice = Integer.parseInt(String.valueOf(cartInfoDAO.getTotalPrice(userId)));
		session.put("totalPrice", totalPrice);
		result = SUCCESS;
		if(!session.containsKey("mCategoryDTOList")){
			result="timeout";
			}

		return result;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
