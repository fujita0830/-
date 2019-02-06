package com.internousdev.cyan.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.cyan.dao.UserInfoDAO;
import com.internousdev.cyan.dto.MCategoryDTO;
import com.internousdev.cyan.dto.UserInfoDTO;
import com.opensymphony.xwork2.ActionSupport;


public class MyPageAction extends ActionSupport implements SessionAware{
	private String categoryId;
	private String keywords;
	private List<MCategoryDTO> mCategoryDTOList = new ArrayList<MCategoryDTO>();

	private Map<String,Object> session;
	public String execute(){
		String result = ERROR;
		System.out.println(categoryId);
		System.out.println(keywords);
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

			System.out.println(session.get("familyName"));

			result = SUCCESS;
		}
			return result;
		}

		public List<MCategoryDTO> getmCategoryDTOList() {
			return mCategoryDTOList;
		}


		public void setmCategoryDTOList(List<MCategoryDTO> mCategoryDTOList) {
			this.mCategoryDTOList = mCategoryDTOList;
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