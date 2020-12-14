package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DBContext {

    InitialContext initial;
    Context context;
    String Dbname, dbusername, dbpass, localhost, portnumber, img;

    public DBContext() throws NamingException {
        initial = new InitialContext();
        Object obj = initial.lookup("java:comp/env");
        context = (Context) obj;
        Dbname = context.lookup("dbName").toString();
        dbusername = context.lookup("user").toString();
        dbpass = context.lookup("pass").toString();
        localhost = context.lookup("serverName").toString();
        portnumber = context.lookup("portNumber").toString();
        img = context.lookup("image").toString();
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://"
                + localhost + ":"
                + portnumber + ";databaseName="
                + Dbname, dbusername, dbpass);
        return conn;
    }

    public void closeConnection(ResultSet rs, PreparedStatement ps, Connection con) throws SQLException {
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
        if (ps != null && !ps.isClosed()) {
            ps.close();
        }
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

    public String getImagePath() throws Exception {
        return img;
    }
}
