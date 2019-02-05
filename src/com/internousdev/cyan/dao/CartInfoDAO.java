package com.internousdev.cyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.cyan.dto.CartInfoDTO;
import com.internousdev.cyan.util.DBConnector;

public class CartInfoDAO {

	public List<CartInfoDTO> getCartInfoDtoList(String loginId) {
        DBConnector dbConnector = new DBConnector();
        Connection connection = dbConnector.getConnection();
        List<CartInfoDTO> cartInfoDtoList = new ArrayList<CartInfoDTO>();
        String sql="select"
                + " ci.id as id,"
                + " ci.user_id as user_id,"
                + " ci.temp_user_id as temp_user_id,"
                + " ci.product_id as product_id,"
                + " ci.product_count as product_count,"
                + " pi.price as price,"
                + " pi.product_name as product_name,"
                + " pi.product_name_kana as product_name_kana,"
                + " pi.image_file_path as image_file_path, "
                + " pi.image_file_name as image_file_name, "
                + " pi.release_date as release_date,"
                + " pi.release_company as release_company,"
                + " pi.status as status,"
                + " (ci.product_count * ci.price) as subtotal,"
                + " ci.regist_date as regist_date,"
                + " ci.update_date as update_date"
                + " FROM cart_info as ci"
                + " LEFT JOIN product_info as pi"
                + " ON ci.product_id = pi.product_id"
                + " WHERE ci.user_id = ?"
                + " order by update_date desc, regist_date desc";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            System.out.println("cartinfodao-getcartinfodtolist:"+loginId);
            preparedStatement.setString(1, loginId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                CartInfoDTO cartInfoDTO = new CartInfoDTO();
                cartInfoDTO.setId(resultSet.getInt("id"));
                cartInfoDTO.setUserId(resultSet.getString("user_id"));
                cartInfoDTO.setTempUserId(resultSet.getString("temp_user_id"));
                cartInfoDTO.setProductId(resultSet.getInt("product_id"));
                cartInfoDTO.setProductCount(resultSet.getInt("product_count"));
                cartInfoDTO.setPrice(resultSet.getInt("price"));
                cartInfoDTO.setProductName(resultSet.getString("product_name"));
                cartInfoDTO.setProductNameKana(resultSet.getString("product_name_kana"));
                cartInfoDTO.setImageFilePath(resultSet.getString("image_file_path"));
                cartInfoDTO.setImageFileName(resultSet.getString("image_file_name"));
                cartInfoDTO.setReleaseDate(resultSet.getDate("release_date"));
                cartInfoDTO.setReleaseCompany(resultSet.getString("release_company"));
                cartInfoDTO.setStatus(resultSet.getString("status"));
                cartInfoDTO.setSubtotal(resultSet.getInt("subtotal"));
                cartInfoDtoList.add(cartInfoDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartInfoDtoList;
    }

    public int getTotalPrice(String userId) {
        int totalPrice = 0;
        DBConnector dbConnector = new DBConnector();
        Connection connection = dbConnector.getConnection();
        String sql = "select sum(product_count * price) as total_price from cart_info where user_id=? group by user_id";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                totalPrice = resultSet.getInt("total_price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalPrice;
    }

    public int regist(String userId, String tempUserId, int productId, int productCount, int price) {
        DBConnector dbConnector = new DBConnector();
        Connection connection = dbConnector.getConnection();
        int count = 0;
        String sql = "insert into cart_info(user_id, temp_user_id, product_id, product_count, price, regist_date)"
                + " values (?, ?, ?, ?, ?, now())";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, tempUserId);
            preparedStatement.setInt(3, productId);
            preparedStatement.setInt(4, productCount);
            preparedStatement.setInt(5, price);
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int update(String userId, int productId, int productCount){
        DBConnector db = new DBConnector();
        Connection con = db.getConnection();
        String sql = "UPDATE cart_info SET product_count=(product_count + ?), update_date = now() WHERE user_id=? AND product_id=?";
        int result = 0;
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,productCount);
            ps.setString(2,userId);
            ps.setInt(3,productId);
            result = ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        try{
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    public int delete(String productId, String userId) {
        DBConnector dbConnector = new DBConnector();
        Connection connection = dbConnector.getConnection();
        int count = 0;
        String sql = "delete from cart_info where product_id=? and user_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productId);
            preparedStatement.setString(2, userId);
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public boolean isExistsCartInfo (String loginId, int productId) {
        DBConnector db = new DBConnector();
        Connection con = db.getConnection();
        String sql = "SELECT COUNT(id) AS COUNT FROM cart_info WHERE user_id = ? and product_id=?";
        boolean result = false;
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, loginId);
            ps.setInt(2, productId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                if(rs.getInt("COUNT") > 0) {
                    result = true;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        try{
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public int linkToLoginId(String tempUserId, String loginId) {
        DBConnector dbConnector = new DBConnector();
        Connection connection = dbConnector.getConnection();
        int count = 0;
        String sql = "update cart_info set user_id=?, temp_user_id = null where temp_user_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, loginId);
            preparedStatement.setString(2, tempUserId);
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

}
