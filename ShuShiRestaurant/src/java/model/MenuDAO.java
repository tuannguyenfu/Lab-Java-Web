/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import context.DBContext;
import entity.Menu;
import entity.Sushi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
get properties of total sushi menu
*/
public class MenuDAO {

    /**
     * getTotalMenu method
     * 
     * get total page by count from database table
     * 
     * Process
     * 
     * Get connection to SQLServer database
     * 
     * if get connection fail, throw Exception 
     * 
     * Query count from database table and return 
     * 
     * close connection
     * @return total menu in database
     * @throws Exception 
     */
     public int getTotalMenu() throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String query = "SELECT COUNT(Id) FROM Menu";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
             e.printStackTrace(System.out);
        }
        return count;
     }
     
    /**
     * ListMenuPaggning method
     * 
     * get record by Row_Number by pageIndex and pageSize
     * 
     * Process
     * 
     * Get connection to SQLServer database
     * 
     * If get connection fail, throw SQLException
     * 
     * Query records from database table by id Article
     * 
     * New object Menu save information of menu
     * 
     * Add object Menu to ListMenu
     * 
     * return listMenu
     * 
     * Close connection
     * @param pageIndex is index of page
     * @param pageSize is size of page
     * @return list menu 
     * @throws SQLException 
     */
    public List<Menu> ListMenuPagging(int pageIndex, int pageSize) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Menu> list = new ArrayList<>();
        try {
            String query = "select *from("
                + "select ROW_NUMBER() over (order by ID ASC) as rn, *\n"
                + "from Menu\n"
                + ")as x\n"
                + "where rn between (?-1)*?+1"
                + "and ?*?";
            
            //khi pageindex = 1; pagesize = 3
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, pageIndex);
            ps.setInt(2, pageSize);
            ps.setInt(3, pageIndex);
            ps.setInt(4, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                Menu menu = new Menu(rs.getInt("Id"), 
                        rs.getString("Name"), 
                        rs.getString("Price"), 
                        rs.getString("ShortDescription"), 
                        rs.getString("FullDescription"));
                list.add(menu);
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
