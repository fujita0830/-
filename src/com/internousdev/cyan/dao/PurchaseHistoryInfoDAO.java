package com.internousdev.cyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.cyan.dto.purchaseHistoryInfoDTO;
import com.internousdev.cyan.util.DBConnector;


public class PurchaseHistoryInfoDAO {

	public List<PurchaseHistoryInfoDTO> getPurchaseHistoryList(String loginId) {

		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		List<PurchaseHistoryInfoDTO> purchaseHistoryInfoDTOList = new ArrayList<PurchaseHistoryInfoDTO>();

		String sql = "select"
				+ " phi.id as id," /* ID */
				+ " phi.user_id as user_id," /*ユーザーID*/
				+ " phi.product_count as product_count," /*個数*/
				+ " pi.product_id as product_id," /*商品ID*/
				+ " pi.product_name as product_name," /*商品名*/
				+ " pi.product_name_kana as product_name_kana," /*商品名かな*/
				+ " pi.product_description as product_description," /*商品詳細*/
				+ " pi.category_id as category_id," /*カテゴリID*/
				+ " pi.price," /*価格*/
				+ " pi.image_file_name as image_file_name,"/*画像ファイルパス*/
				+ " pi.image_file_path as image_file_path,"/*画像ファイル名*/
				+ " pi.release_company," /*発売会社名*/
				+ " phi.release_date," /*発売年月日*/
				+ " phi.price as price,"/*値段*/
				+ " phi.regist_date as regist_date," /*登録日*/
				+ " phi.update_date as update_date," /*更新日*/
				+ " di.family_name as family_name," /*姓*/
				+ " di.first_name as first_name," /*姓*/
				+ " di.family_name_kana as family_name_kana," /*姓かな*/
				+ " di.first_name_kana as first_name_kana," /*名かな*/
				+ " di.email as email," /*メールアドレス*/
				+ " di.tel_number as tel_number," /*電話番号*/
				+ " di user_address as user_address"/*住所*/
				+ " FROM purchase_history_info as phi"
				+ " LEFT JOIN product_info as pi"
				+ " ON phi.product_id= pi.product_id"
				+ " LEFT JOIN destination_info as di"
				+ " ON phi.destination_id=di.id"
				+ " WHERE phi.user_id=?"
				+ " ORDER BY regist_date DESC";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginId);
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				purchaseHistoryInfoDTO purchaseHistoryInfoDto = new PurchaseHistoryInfoDTO();
				purchaseHistoryInfoDto.setId(resultSet.getInt("id"));
				purchaseHistoryInfoDto.setUserId(resultSet.getString("user_id"));
				purchaseHistoryInfoDto.setProductId(resultSet.getInt("product_id"));
				purchaseHistoryInfoDto.setProductCount(resultSet.getInt("product_count"));
				purchaseHistoryInfoDto.setPrice(resultSet.getInt("price"));
				purchaseHistoryInfoDto.setRegistDate(resultSet.getDate("regist_date"));
				purchaseHistoryInfoDto.setUpdateDate(resultSet.getDate("update_date"));
				purchaseHistoryInfoDto.setProductName(resultSet.getString("product_name"));
				purchaseHistoryInfoDto.setProductNameKana(resultSet.getString("product_name_Kana"));
				purchaseHistoryInfoDto.setProductDescription(resultSet.getString("product_description"));
				purchaseHistoryInfoDto.setCategoryId(resultSet.getInt("category_id"));
				purchaseHistoryInfoDto.setImageFileName(resultSet.getString("image_file_name"));
				purchaseHistoryInfoDto.setImageFilePath(resultSet.getString("image_file_path"));
				purchaseHistoryInfoDto.setReleaseCompany(resultSet.getString("release_company"));
				purchaseHistoryInfoDto.setReleaseDate(resultSet.getDate("release_date"));
				purchaseHistoryInfoDto.setFamilyName(resultSet.getString("family_name"));
				purchaseHistoryInfoDto.setFirstName(resultSet.getString("first_name"));
				purchaseHistoryInfoDto.setFamilyNameKana(resultSet.getString("family_name_Kana"));
				purchaseHistoryInfoDto.setFirstNameKana(resultSet.getString("first_name_Kana"));
				purchaseHistoryInfoDto.setEmail(resultSet.getString("email"));
				purchaseHistoryInfoDto.setTelNumber(resultSet.getString("tel_number"));
				purchaseHistoryInfoDto.setUserAddress(resultSet.getString("user_address"));
				purchaseHistoryInfoDTOList.add(purchaseHistoryInfoDto);


		}
	} catch (SQLException e){
		e.printStackTrace();
	}
	try {
		connection.close();

	} catch (SQLException e){
		e.printStackTrace();
	}
	return purchaseHistoryInfoDTOList;
}

public int regist(String longId,int productId, int productCount, int destinationId, int price){

	com.internousdev.cyan.util.DBConnector dbConnector = new DBConnector();
	Connection connection = dbConnector.getConnection();
	String sql ="insert int purchase_history_info where user_id=?";
	int count=0;
	try{
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, loginId);
		preparedStatement.setint(2, productId);
		preparedStatement.setint(3, productCount);
		preparedStatement.setint(4, price);
		preparedStatement.setint(5, destinationId);
		count = preparedStatement.executeUpdate();

	} catch (SQLException e){
		e.printStackTrace();
	}
	try {
		connection.close();
	} catch (SQLExceptionc e){
		e.printStackTrace();
	}
	return count;
	}

	}








