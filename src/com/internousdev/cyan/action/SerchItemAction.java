package com.internousdev.cyan.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.internousdev.cyan.dto.MCategoryDTO;
import com.internousdev.cyan.dto.ProductInfoDTO;
import com.internousdev.cyan.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;
public class SerchItemAction extends ActionSupport {


	private String categoryId;
	private String keywords;
	private List<MCategoryDTO> mCategoryDTOList = new ArrayList<MCategoryDTO>();
	private List<String> keywordsErrorMessageList = new ArrayList<String>();
	private List<ProductInfoDTO> productInfoDtoList = new ArrayList<ProductInfoDTO>();
	private Map<String, Object> session;
	private String tempKeywords;

	public String execute(){

		String reslut = ERROR;
		InputChecker inputChecker = new InputChecker();

		if (StringUtils.isBlank(keywords)){

			tempKeywords = "";


		}else{
			keywordsErrorMessageList = inputChecker.doCheck("検索ワード", keywords, 0, 16, true, true, true, true, false, false, false, true, true);


		}








		return SUCCESS;

	}



}
