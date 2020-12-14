/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import context.DBContext;
import entity.AboutShop;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * get information of sushi store
 */
public class AboutShopDAO {
    /**
     * getInformation method
     * 
     * get Information from database table 
     * 
     * Process
     * 
     * Get connection to SQLServer database
     * 
     * if get connection fail, throw Exception 
     * 
     * query get properties of information Shop 
     * 
     * new object AboutShop to save properties of information Shop
     * 
     * return object
     * 
     * Close connection
     * @param connection is session between net_bean and database
     * @param ps is used to execute dynamic or parameterized SQL queries
     * @param rs is save result of the database query statement
     * closeConnection meaing close Close Connection after connection database
     */
    public AboutShop getInformation() throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM AboutShop;";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                AboutShop aboutShop = new AboutShop(rs.getInt("Id"),
                        rs.getString("Address"),
                        rs.getString("Tel"),
                        rs.getString("Email"),
                        rs.getString("OpeningHours"),
                        rs.getString("PhotoPath"));
                return aboutShop;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            DBContext.closeConnection(rs, ps, connection);
        }
        return null;
    }
}
