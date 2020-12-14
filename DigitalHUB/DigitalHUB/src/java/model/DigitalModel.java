/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import context.DBContext;
import entity.Digital;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;


public class DigitalModel {
      DBContext db;
      private Connection connection = null;
      private PreparedStatement ps = null;
      private ResultSet rs = null;
      
      public DigitalModel() throws NamingException{
          db = new DBContext();
      }
      
    //Close connection, preparedStatement and resultSet

    public List<Digital> searchTitleAndPagging(int pageIndex, int pageSize, String title) throws Exception{
        ArrayList<Digital> list = new ArrayList<>();
        String sql = "select *from("
                + "select ROW_NUMBER() over (order by ID ASC) as rn, *\n"
                + "from digital where title like ? \n"
                + ")as x\n"
                + "where rn between (?-1)*?+1"
                + "and ?*?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + title + "%");
            ps.setInt(2, pageIndex);
            ps.setInt(3, pageSize);
            ps.setInt(4, pageIndex);
            ps.setInt(5, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                Digital digital = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                list.add(digital);
            }
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, connection);
        }
    }

    public Digital getOne(int id) throws Exception{
        String sql = "Select * from digital where ID = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Digital digital = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                return digital;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, connection);
        }
        return null;
    }

    public Digital getTop1Digital() throws Exception{
        String sql = "Select Top 1* from digital order by timepost desc";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Digital digital = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                return digital;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, connection);
        }
        return null;
    }

    public List<Digital> get5NextDigital() throws Exception{
        ArrayList<Digital> list4Next = new ArrayList<>();
        String sql = "select *from("
                + "select ROW_NUMBER() over (order by timepost ASC) as rn, *\n"
                + "from digital  \n"
                + ")as x\n"
                + "where rn between 2"
                + "and 6";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Digital digital = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                list4Next.add(digital);
            }
            return list4Next;
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, connection);
        }
    }

    public int getCountByTxtSearchTitle(String txtSearch) throws Exception{
        String sql = "Select count(ID) from digital where title like ?";
        int count = 0;
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + txtSearch + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            throw e;
        }
        return count;
    }

    public List<Digital> getAllResultSearch(String tittle) throws Exception{
        ArrayList<Digital> listAllResult = new ArrayList<>();
        try {
            String sql = "Select * from digital where title like ?";
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + tittle + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Digital digital = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                listAllResult.add(digital);
            }
            return listAllResult;
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, connection);
        }
    }
}
