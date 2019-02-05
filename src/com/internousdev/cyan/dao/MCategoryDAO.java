package com.internousdev.cyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.cyan.dto.MCategoryDTO;
import com.internousdev.cyan.util.DBConnector;

public class MCategoryDAO {
	public List<MCategoryDTO> getMCategoryList() {
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		String sql = "select * from m_category";
		List<MCategoryDTO> mCategoryDTOList = new ArrayList<MCategoryDTO>();

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				MCategoryDTO mCategoryDTO = new MCategoryDTO();
				mCategoryDTO.setId(resultSet.getString("id"));
				mCategoryDTO.setCategoryId(resultSet.getString("category_id"));
				mCategoryDTO.setCategoryName(resultSet.getString("category_name"));
				mCategoryDTOList.add(mCategoryDTO);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}try{
			connection.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return mCategoryDTOList;
	}
}
