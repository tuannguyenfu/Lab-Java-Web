/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import DBContext.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Digital;

/**
 *
 * @author admin
 */
public class DigitalDAO {

    public Digital getTheMostRecent() throws Exception {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = "SELECT top 1 * from digital order by timePost desc";
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Digital d = new Digital();
                d.setId(rs.getInt("ID"));
                d.setTitile(rs.getString("title"));
                d.setDescription(rs.getString("description"));
                d.setImage(rs.getString("image"));
                d.setAuthor(rs.getString("author"));
                d.setTimePost(rs.getTimestamp("timePost"));
                d.setShortDes(rs.getString("shortDes"));
                return d;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
        return null;
    }

    public ArrayList<Digital> getFiveRecent() throws Exception {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ArrayList<Digital> digitals = new ArrayList<>();
            String query = "Select top 5 * from digital "
                    + "where timePost not in "
                    + "(select max(timePost) from digital )\n"
                    + "order by timePost desc";
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Digital d = new Digital();
                d.setId(rs.getInt("ID"));
                d.setTitile(rs.getString("title"));
                d.setDescription(rs.getString("description"));
                d.setImage(rs.getString("image"));
                d.setAuthor(rs.getString("author"));
                d.setTimePost(rs.getTimestamp("timePost"));
                d.setShortDes(rs.getString("shortDes"));
                digitals.add(d);
            }
            return digitals;
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
    }

    public Digital getDigitalById(int id) throws Exception {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "Select * from digital where id = ?";
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Digital d = new Digital();
                d.setId(rs.getInt("ID"));
                d.setTitile(rs.getString("title"));
                d.setDescription(rs.getString("description"));
                d.setImage(rs.getString("image"));
                d.setAuthor(rs.getString("author"));
                d.setTimePost(rs.getTimestamp("timePost"));
                d.setShortDes(rs.getString("shortDes"));
                return d;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
        return null;
    }

    public ArrayList<Digital> searchDigitals(int pageIndex, int pageSize, String txtSearch) throws Exception {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ArrayList<Digital> list = new ArrayList<>();
            String query = "Select * FROM "
                    + "(Select *,ROW_NUMBER() over (order by ID ASC)\n"
                    + "as row_num FROM digital WHERE title like ?)\n"
                    + "as a WHERE row_num between ?*?-2 AND ?*?";
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            ps.setInt(2, pageIndex);
            ps.setInt(3, pageSize);
            ps.setInt(4, pageIndex);
            ps.setInt(5, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                Digital d = new Digital();
                d.setId(rs.getInt("ID"));
                d.setTitile(rs.getString("title"));
                d.setDescription(rs.getString("description"));
                d.setImage(rs.getString("image"));
                d.setAuthor(rs.getString("author"));
                d.setTimePost(rs.getTimestamp("timePost"));
                d.setShortDes(rs.getString("shortDes"));
                list.add(d);
            }
            return list;
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
    }

    public int count(String txt) throws Exception {
        DBContext db = new DBContext();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select count(*) as rowNum from digital \n"
                    + "where title like ?";
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txt + "%");
            rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt("rowNum");
            }
            return count;
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
    }
}
