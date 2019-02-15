package com.internousdev.cyan.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.cyan.dao.UserInfoDAO;
import com.internousdev.cyan.dto.UserInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class MyPageAction extends ActionSupport implements SessionAware{

	private Map<String,Object> session;
	public String execute(){

		String result = SUCCESS;

		if(!session.containsKey("mCategoryDTOList")){
					return "timeout";
		}

		UserInfoDAO userInfoDAO = new UserInfoDAO();
		UserInfoDTO userInfoDTO = new UserInfoDTO();
		userInfoDTO = userInfoDAO.getUserInfo(String.valueOf(session.get("loginId")));
		if(userInfoDTO!=null){
			session.put("familyName",userInfoDTO.getFamilyName());
			session.put("firstName",userInfoDTO.getFirstName());
			session.put("familyNameKana",userInfoDTO.getFamilyNameKana());
			session.put("firstNameKana",userInfoDTO.getFirstNameKana());
			session.put("sex",userInfoDTO.getSex());
			session.put("email",userInfoDTO.getEmail());
		}
		return result;
	}

	public Map<String,Object> getSession(){
		return session;
	}

	public void setSession(Map<String,Object> session){
		this.session = session;
	}

}