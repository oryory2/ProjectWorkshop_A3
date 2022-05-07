package DataAccess;
import java.sql.*;

public class DB_connector {

    /**
     * Connect to a sample database
     */
    public static Connection connect() {
        Connection conn = null;
        String url = "jdbc:sqlite:db_hw3.db";
        try {
            conn = DriverManager.getConnection(url);
            System.out.println(conn);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void disconnect(Connection conn) {
        if (conn != null){
            try{
                conn.close();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

}
