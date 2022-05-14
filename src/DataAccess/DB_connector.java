package DataAccess;
import java.sql.*;


// Class that only creates instance to connect/disconnect the SQLITE DB
public class DB_connector {
    public static final String URL ="jdbc:sqlite:db_hw3.db";
    public static Connection conn;

    protected static Connection getInstance(){
        return conn;
    }

    // Creates and returns a connection
    public static Connection connect() {
        try {
            conn = DriverManager.getConnection  (URL);
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    // Closes the connection
    protected static void disconnect(Connection conn) {
        if (conn != null){
            try{
                conn.close();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }


}
