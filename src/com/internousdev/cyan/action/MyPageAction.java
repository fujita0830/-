package com.internousdev.cyan.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.cyan.dao.UserInfoDAO;
import com.internousdev.cyan.dto.UserInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class MyPageAction extends ActionSupport implements SessionAware{
	private String categoryId;
	private String keywords;

	private Map<String,Object> session;
	public String execute(){

		String result = SUCCESS;
		System.out.println(categoryId);
		System.out.println(keywords);
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		UserInfoDTO userInfoDTO = new UserInfoDTO();
		userInfoDTO = userInfoDAO.getUserInfo(String.valueOf(session.get("loginId")));

		if(!session.containsKey("mCategoryDTOList")){
			result="timeout";
			}else{
				if(userInfoDTO!=null){
					session.put("familyName",userInfoDTO.getFamilyName());
					session.put("firstName",userInfoDTO.getFirstName());
					session.put("familyNameKana",userInfoDTO.getFamilyNameKana());
					session.put("firstNameKana",userInfoDTO.getFirstNameKana());
					session.put("sex",userInfoDTO.getSex());
					session.put("email",userInfoDTO.getEmail());

					System.out.println(session.get("familyName"));
				}
			}
			return result;
		}

		public String getCategoryId() {
			return categoryId;
		}

		public void setCategoryId(String categoryId) {
			this.categoryId = categoryId;
		}

		public String getKeywords(){
			return keywords;
		}

		public void setKeywords(String keywords){
			this.keywords = keywords;
		}

		public Map<String,Object> getSession(){
			return session;
		}

		public void setSession(Map<String,Object> session){
			this.session = session;
		}
		}