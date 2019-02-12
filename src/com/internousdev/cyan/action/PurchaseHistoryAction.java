package com.internousdev.cyan.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.cyan.dao.PurchaseHistoryInfoDAO;
import com.internousdev.cyan.dto.PurchaseHistoryInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class PurchaseHistoryAction extends ActionSupport implements SessionAware{

	private String categoryId;
	private List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList;
	private Map<String, Object> session;

	public String execute(){

		String result=SUCCESS;

		PurchaseHistoryInfoDAO purchaseHistoryInfoDAO = new PurchaseHistoryInfoDAO();
		purchaseHistoryInfoDTOList = purchaseHistoryInfoDAO.getPurchaseHistoryList(String.valueOf(session.get("loginId")));
		Iterator<PurchaseHistoryInfoDTO> iterator=purchaseHistoryInfoDTOList.iterator();

		if(!(iterator.hasNext())){
			purchaseHistoryInfoDTOList =null;
		}


		if(!session.containsKey("mCategoryDTOList")){

			result="timeout";
			}

		return result;
		}


public List<PurchaseHistoryInfoDTO> getPurchaseHistoryInfoDTOList(){
	return purchaseHistoryInfoDTOList;
}

public void setPurchaseHistoryInfoDTOList(List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList){
	this.purchaseHistoryInfoDTOList = purchaseHistoryInfoDTOList;
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
