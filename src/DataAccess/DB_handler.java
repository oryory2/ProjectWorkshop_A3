package DataAccess;

import DataAccess.DB_connector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_handler {

    public static Connection conn;

    public static void connect_DB(){
        conn = DB_connector.connect();
    }

    public static void disconnect_DB() {
        DB_connector.disconnect(DB_connector.getInstance());
    }

    public static void add_row(String table, String[] column_names, String[] value_names){
        try{
            String sql;
            StringBuilder sql_builder = new StringBuilder("INSERT INTO " + table + " (");

//          Write the '(column1, column2, ...)'
            for (String col_name : column_names)
                sql_builder.append(col_name).append(", ");

//          Substrings to remove last comma and space -> casts it back from string to stringbuilder
            sql = sql_builder.substring(0, sql_builder.length() -2);
            sql_builder = new StringBuilder(sql);
            sql_builder.append(") VALUES (");

//          Write the '(value1, value2, ...)'
            for (String val_name : value_names)
                sql_builder.append("'").append(val_name).append("', ");
            sql = sql_builder.substring(0, sql_builder.length() -2);
            sql_builder = new StringBuilder(sql);
            sql_builder.append(")");

            sql = sql_builder.toString();
            System.out.println(sql);

            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


}
