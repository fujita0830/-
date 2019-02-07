package com.internousdev.cyan.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.cyan.dto.MCategoryDTO;
import com.opensymphony.xwork2.ActionSupport;

public class GoLoginAction extends ActionSupport implements SessionAware {
	private String categoryId;
	private List<MCategoryDTO> mCategoryDTOList = new ArrayList<MCategoryDTO>();
	private Map<String, Object> session;
	public String execute() {
		String result = SUCCESS;
		if(!session.containsKey("mCategoryDTOList")){
			result="timeout";
			}
		session.put("loginIdErrorMessageList", "");
		session.put("passwordErrorMessageList", "");
		return result;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public List<MCategoryDTO> getmCategoryDTOList() {
		return mCategoryDTOList;
	}

	public void setmCategoryDTOList(List<MCategoryDTO> mCategoryDTOList) {
		this.mCategoryDTOList = mCategoryDTOList;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
