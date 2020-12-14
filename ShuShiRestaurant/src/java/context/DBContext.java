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
/*
connect to database
*/
public class DBContext {
    private static String serverName;
    private static String dbName;
    private static String portNumber;
    private static  String userID;
    private static String password;
     /**
      * get data context.xml
      * @param servername is name of server
      * @param dbnam is name of database
      * @param portNumber is port of SqLServer
      */
    static {
        try {
            InitialContext initialContext = new InitialContext();
            Context environmentContext = (Context) initialContext.lookup("java:comp/env");
            serverName = (String) environmentContext.lookup("serverName");
            dbName = (String) environmentContext.lookup("dbName");
            portNumber = (String) environmentContext.lookup("portNumber");
            userID = (String) environmentContext.lookup("userID");
            password = (String) environmentContext.lookup("password");
        } catch (NamingException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * get path of image folder
     * @return imagePath
     */
    public String getImagePath() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            InitialContext context = new InitialContext();
            Context xmlNode = (Context) context.lookup("java:comp/env");
            String imagePath = (String) xmlNode.lookup("imagePath");
            return imagePath;
        } catch (ClassNotFoundException | NamingException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    /**
     * get connection to sql server
     * @return connection
     * @throws Exception 
     */
    public static Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }
    /**
     * 
     * @param rs Save the results of the database query statement
     * @param ps PreparedStatement is used to execute dynamic or parameterized SQL queries.
     * @param con Connection is the session between net_bean and database.
     * @throws SQLException 
     */
    public static void closeConnection(ResultSet rs, PreparedStatement ps, Connection con) throws SQLException { // đóng kết nối
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

}
