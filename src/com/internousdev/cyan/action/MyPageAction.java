package com.internousdev.cyan.action;

import java.util.AllayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.sampleweb.dao.UserInfoDAO;
import com.internousdev.sampleweb.dto.MCategoryDTO;
import com.internousdev.sampleweb.dto.UserInfoDTO;
import com.internousdev.xwork.ActionSupport;


public MyPageAction extends ActionSuport implements SessionAware{
	private Stirng categoryId;
	private String keywords;
	private List<MCategoryDTO> mCategoryDtoLis = new ArrayList<MCategoryDTO>();

	private Map<String,Object>session;
	public String execute(){
		String result = ERROR;
		System.out.println(categoryId);
		System.out.println(keywords);
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		UserInfoDTO userInfoDTO = new UserInfoDTO();
		userInfoDTO = userInfoDAO.getUserInfo(Stirng.valueOf(session.get("loginId")));

		if(userInfoDTO!=null){
			session.put("familyName",userInfoDTO.getFamilyName());
			session.put("firstName",userIndoDTO.getFirstName());
			session.put("familyNameKana",userIndoDTO.getFamilyNameKana());
			session.put("firstNameKana",userInfoDTO.getFirstNameKana());
			session.put("sex",userInfoDTO.getSex());
			session.put("email",userInfoDTO.getEmaik());

			System.out.println(session.get("familyNmae"));

			result = SUCCESS;
		}
			return result;
		}

		public List<MCategoryDTO> getmCategoryDtoList() {
			return mCategoryDtoList;
		}


		public void setmCategoryDtoList(List<MCategoryDTO> mCategoryDtoList) {
			this.mCategoryDtoList = mCategoryDtoList;
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