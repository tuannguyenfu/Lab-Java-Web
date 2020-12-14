/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import context.DBContext;
import entity.Sushi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * get properties of total sushi
 */
public class SushiDAO {

    DBContext bContext = new DBContext();

    /**
     * getOne method
     *
     * get properties sushi from database table
     *
     * Process
     *
     * Get connection to SQLServer database
     *
     * if get connection fail, throw Exception
     *
     * Query get properties of SUSHI from database table
     * 
     * new object sushi to save properties when query statement executed
     * 
     * return sushi
     *
     * close connection
     *
     * @param id is id of sushi
     * @return properties of sushi
     */
    public Sushi getOne(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM Sushi WHERE Id = ?;";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Sushi sushi = new Sushi(
                        rs.getInt("id"),
                        rs.getString("Name"),
                        bContext.getImagePath() + rs.getString("PhotoPath"),
                        rs.getString("Description"),
                        rs.getString("FullDescription"),
                        rs.getString("author"),
                        rs.getDate("dateCreated")
                );
                return sushi;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    /**
     * /**
     * getTotalSushi method
     *
     * get count total sushi from database table
     *
     * Process
     *
     * Get connection to SQLServer database
     *
     * if get connection fail, throw Exception
     *
     * Query get count total SUSHI from database table and return
     *
     * close connection
     *
     * @return count
     * @throws SQLException
     */
    public int getTotalSushi() throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String query = "SELECT COUNT(Id) FROM Sushi";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            DBContext.closeConnection(rs, ps, connection);
        }
        return -1;
    }

    /**
     * 
     * ListSuShiPagging method
     *
     * get properties sushi from database table
     *
     * Process
     *
     * Get connection to SQLServer database
     *
     * if get connection fail, throw Exception
     *
     * Query get SUSHI from database table by pageIndex and PageSize
     *
     * new object sushi to save properties of sushi
     * 
     * new list sushi and add sushi to list
     * 
     * return list
     * 
     * close connection
     *
     * @param pageIndex is index of page
     * @param pageSize is size of page
     * @return list
     * @throws SQLException
     */
    public List<Sushi> ListSuShiPagging(int pageIndex, int pageSize) throws SQLException {
        ArrayList<Sushi> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * from("
                    + "select ROW_NUMBER() over (order by name ASC) as rn, *\n"
                    + "from Sushi \n"
                    + ")as x\n"
                    + "where rn between (?-1)*?+1"
                    + "and ?*?";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, pageIndex);
            ps.setInt(2, pageSize);
            ps.setInt(3, pageIndex);
            ps.setInt(4, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                Sushi sushi = new Sushi(
                        rs.getInt("Id"),
                        rs.getString("Name"),
                        bContext.getImagePath() + rs.getString("PhotoPath"),
                        rs.getString("Description"),
                        rs.getString("FullDescription"));
                list.add(sushi);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            DBContext.closeConnection(rs, ps, connection);
        }
        return null;
    }

}
