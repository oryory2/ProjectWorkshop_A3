package DataAccess;
import java.sql.*;



public class DB_connector {
    public static final String URL ="jdbc:sqlite:db_hw3.db";
    public static Connection conn;

    protected static Connection getInstance(){
        return conn;
    }


    public static Connection connect() {
        try {
            conn = DriverManager.getConnection(URL);
//            System.out.println(conn);
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

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
