package com.internousdev.cyan.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.cyan.dao.MCategoryDAO;
import com.internousdev.cyan.dao.PurchaseHistoryInfoDAO;
import com.internousdev.cyan.dto.MCategoryDTO;
import com.internousdev.cyan.dto.PurchaseHistoryInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class PurchaseHistoryAction extends ActionSupport implements SessionAware{

	private String categoryId;
	private List<MCategoryDTO> mCategoryDtoList = new ArrayList<MCategoryDTO>();
	private List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDtoList;
	private Map<String, Object> session;

	public String execute(){

		PurchaseHistoryInfoDAO purchaseHistoryInfoDao = new PurchaseHistoryInfoDAO();
		purchaseHistoryInfoDtoList = purchaseHistoryInfoDao.getPurchaseHistoryList(String.valueOf(session.get("loginId")));
		Iterator<PurchaseHistoryInfoDTO> iterator=purchaseHistoryInfoDtoList.iterator();

		if(!(iterator.hasNext())){
			purchaseHistoryInfoDtoList =null;
		}

		if(!session.containsKey("mCategoryList")){
			MCategoryDAO mCategoryDao = new MCategoryDAO();
			mCategoryDtoList = mCategoryDao.getMCategoryList();
			session.put("mCategoryDtoList", mCategoryDtoList);
		}
		return SUCCESS;
		}

public List<MCategoryDTO> getmCategoryDtoList() {
	return mCategoryDtoList;
}

public void setmCategoryDtoList(List<MCategoryDTO> mCategoryDtoList) {
	this.mCategoryDtoList = mCategoryDtoList;
}

public List<PurchaseHistoryInfoDTO> getPurchaseHistoryInfoDtoList(){
	return purchaseHistoryInfoDtoList;
}

public void setPurchaseHistoryInfoDtoList(List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDtoList){
	this.purchaseHistoryInfoDtoList = purchaseHistoryInfoDtoList;
}

public String getCategoryId() {
	return categoryId;
}

public void setCategoryId(String categoryId) {
	this.categoryId = categoryId;
}

public Map<String, Object> getSession() {
	return session;
}

public void setSession(Map<String, Object> session){
	this.session = session;

}
}
