package com.internousdev.cyan.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.cyan.dao.MCategoryDAO;
import com.internousdev.cyan.dto.MCategoryDTO;
import com.internousdev.cyan.util.CommonUtility;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport implements SessionAware{

	private Map<String, Object> session;

	public String execute() {

		if(!session.containsKey("logined")) {
			session.put("logined", 0);
		}

		if(session.get("logined").equals(0) && !(session.containsKey("tempUserId"))){
			CommonUtility commonUtility = new CommonUtility();
			session.put("tempUserId", commonUtility.getRamdomValue());
		}

		List<MCategoryDTO> mCategoryDTOList = new ArrayList<MCategoryDTO>();
		if(!session.containsKey("mCategoryDTOList")){
			MCategoryDAO mCategoryDAO = new MCategoryDAO();
			mCategoryDTOList = mCategoryDAO.getMCategoryList();
			session.put("mCategoryDTOList", mCategoryDTOList);
		}
		return SUCCESS;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
