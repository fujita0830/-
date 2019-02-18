package com.internousdev.cyan.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.cyan.dao.CartInfoDAO;
import com.internousdev.cyan.dao.PurchaseHistoryInfoDAO;
import com.internousdev.cyan.dto.CartInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class SettlementCompleteAction extends ActionSupport implements SessionAware{

	private String id;
	private Map<String, Object> session;

	public String execute() {

		String result = SUCCESS;

		if(!session.containsKey("mCategoryDTOList")) {
			return "timeout";
		}

		@SuppressWarnings("unchecked")
		ArrayList<CartInfoDTO> cartInfoDTOList = (ArrayList<CartInfoDTO>)session.get("cartInfoDTOList");

		for(int i=0;i<cartInfoDTOList.size();i++) {
			cartInfoDTOList.get(i).setDestinationId(Integer.parseInt(getId()));
		}

		PurchaseHistoryInfoDAO purchaseHistoryInfoDAO = new PurchaseHistoryInfoDAO();
		int count = 0;
		for(int i=0; i<cartInfoDTOList.size();i++) {
			count += purchaseHistoryInfoDAO.regist(
					String.valueOf(session.get("loginId")),
					cartInfoDTOList.get(i).getProductId(),
					cartInfoDTOList.get(i).getProductCount(),
					cartInfoDTOList.get(i).getDestinationId(),
					cartInfoDTOList.get(i).getSubtotal()
					);
		}
		if(count > 0) {
			CartInfoDAO cartInfoDAO = new CartInfoDAO();
			count = cartInfoDAO.deleteAll(String.valueOf(session.get("loginId")));
			if(count > 0) {
				List<CartInfoDTO> cartInfoDTOLists = new ArrayList<CartInfoDTO>();
				cartInfoDTOLists = cartInfoDAO.getCartInfoDTOList(String.valueOf(session.get("loginId")));
				Iterator<CartInfoDTO> iterator = cartInfoDTOLists.iterator();
				if(!(iterator.hasNext())) {
					cartInfoDTOList = null;

				}
				session.put("cartInfoDTOList", cartInfoDTOList);

				int totalPrice = Integer.parseInt(String.valueOf(cartInfoDAO.getTotalPrice(String.valueOf(session.get("loginId")))));
				session.put("totalPrice", totalPrice);
				session.remove("purchaseHistoryInfoDTOList");
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

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
