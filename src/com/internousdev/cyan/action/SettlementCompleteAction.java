package com.internousdev.cyan.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.cyan.dao.CartInfoDAO;
import com.internousdev.cyan.dto.CartInfoDTO;
import com.opensymphony.xwork2.ActionSupport;


public class SettlementCompleteAction extends ActionSupport implements SessionAware{

	private String id;
	private String categoryId;
	private Map<String, Object> session;

	public String execute(){
		String result = ERROR;

		@SuppressWarnings("unchecked")
		ArrayList<PurchaseHistoryInfoDTO> purchaseHistoryInfoDtoList = (ArrayList<PurchaseHistoryInfoDTO>)session.get("purchaseHistoryInfoDtoList");

		@SuppressWarnings("unchecked")
		ArrayList<DestinationInfoDTO>destinationinfoDTOList = (ArrayList<DestinationInfoDTO>)session.get("destinationInfoDTOList");
		for(int i=0;i<purchaseHistoryInfoDtoList.size();i++){
			purchaseHistoryInfoDtoList.get(i).setDestinationId(destinationInfoDtoList.get(0).getId());
		}

		purchaseHistoryInfoDAO purchaseHistoryInfoDAO = new PurchaseHIistoryInfoDAO();
		int count = 0;
		for(int i=0; i<purchaseHistoryInfoDtoList.size();i++){
			count += purchaseHistoryInfoDAO.regist(
					String.valueOf(session.get("loginId")),
					purchaseHistoryInfoDtoList.get(i).getProductId(),
					purchaseHistoryInfoDtoList.get(i).getProductCount(),
					purchaseHistoryInfoDtoList.get(i).getDestinationId(),
					purchaseHistoryInfoDtoList.get(i).getSubtotal()
					);
		}
		if(count>0){
			CartInfoDAO cartinfoDAO = new CartInfoDAO();
			count = cartinfoDAO.deleteAll(String.valueOf(session.get("loginId")));
			if(count > 0){
				List<CartInfoDTO>cartInfoDtoList = new ArrayList<CartInfoDTO>();
				cartInfoDtoList = cartinfoDAO.getCartInfoDtoList(String.valueOf(session.get("loginId")));
				Iterator<CartInfoDTO>iterator = cartInfoDtoList.iterator();
				if(!(iterator.hasNext())){
					cartInfoDtoList = null;
				}
		    session.put("cartInfoDtoList",cartInfoDtoList);

		    int totalPrice = Integer.parseInt(String.vallueOf(cartinfoDAO.getTotalPrice(String.valueOf(session.get("loginId")))));
		    session.put("totalPrice", totalPrice);
		    session.remove("purchaseHistoryInfoDtoList");
		    result = SUCCESS;
			}
		 }
		return result;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}


}
