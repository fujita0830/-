package com.internousdev.cyan.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.RequestingUserName;

import org.apache.struts2.interceptor.SessionAware;
import org.omg.CORBA.PRIVATE_MEMBER;

import com.internousdev.sampleweb.dao.PurchaseHistoryInfoDAO;
import com.internousdev.sampleweb.dto.PurchaseHistoryInfoDTO;
import com.opensymphony.xwork2.ActionSupport;


public class DeletePurchaseHistoryAction exetends ActionSupport implements SessionAware{

	private String categoryId;
	private String sex;
	private List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDtoList;
	private Map<String, Object> session;


	public String execute(){

		String result = ERROR;

		PurchaseHistoryInfoDAO purchaseHistoryInfoDAO = new PurchaseHistoryInfoDAO();

		int count = purchaseHistoryInfoDAO.deleteAll(String.valueOf(session.get("loginId")));

		if(count > 0){

			purchaseHistoryInfoDtoList = purchaseHistoryInfoDAO.getPurchaseHistoryList(String.valueOf(session.get("loginId")));

			Iterator<PurchaseHistoryInfoDTO> iterator = purchaseHistoryInfoDtoList.iterator();

			if(!(iterator.hasNext())){
				purchaseHistoryInfoDtoList = null;
			}

			result=SUCCESS;
			}

		return result;
		}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public List<PurchaseHistoryInfoDTO> getPurchaseHistoryInfoDtoList() {
		return purchaseHistoryInfoDtoList;
	}

	public void setPurchaseHistoryInfoDtoList(List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDtoList) {
		this.purchaseHistoryInfoDtoList = purchaseHistoryInfoDtoList;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}

