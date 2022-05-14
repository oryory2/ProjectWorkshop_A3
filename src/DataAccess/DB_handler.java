package DataAccess;

import DataAccess.DB_connector;
import org.sqlite.core.DB;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class DB_handler {

    public static Connection conn;

    public static void connect_DB(){
        conn = DB_connector.connect();
    }

    public static void disconnect_DB() {
        DB_connector.disconnect(DB_connector.getInstance());
    }



    public static Boolean checkLogin(String username, String password) throws SQLException {
        String sql = "select username, password from login where username='" + username + "' and password='" + password + "'";
        System.out.println(sql);
        PreparedStatement pst1 = conn.prepareStatement(sql);
        ResultSet rs = pst1.executeQuery();
        if (rs.next()) {
            System.out.println("Username & Password are correct");
            return true;

        } else {
            System.out.println("Username & Password are incorrect");
            return false;
        }

    }

    public static void addReferee(String name, String leagueId){
            String[] column_names = {"referee_name","league_id"};
            String[] value_names = {name,leagueId};
            add_row("referee",column_names,value_names);

        }


    public static void add_league(String season, String league_name, String number_of_teams)
    {
        if (season == null || league_name == null)
        {
            return;
        }

        String[] column_names = {"season", "league_name", "number_of_teams"};
        String[] values = {season, league_name, number_of_teams};
        add_row("leagues", column_names, values);
    }


    public static void addGame(String homeTeam, String visitorTeam, String leagueIdOfTheGame, String dateAndTime, String referee) {
        if (homeTeam == null || visitorTeam == null || leagueIdOfTheGame == null || dateAndTime == null || referee == null ){
            return;
        }
        String[] column_names = {"game_datetime", "home_team_id", "league_id", "visitor_team_id", "referee_id"};
        String[] values = {dateAndTime,homeTeam,leagueIdOfTheGame,visitorTeam,referee};
        add_row("games", column_names, values);

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

            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void remove_row(String table, String column_name, String value_name){
        try{
            String sql = "DELETE FROM " + table + " WHERE " +column_name+ " = " + "'"+value_name+"'";
            System.out.println(sql);
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static boolean existInDB(String table, String[] columns_name, String condition){
        return get_list(table,columns_name,condition).size() >= 1 ;

    }

    //Function to get a list of all the rows from a given table.
    // Parameter condition is the condition that comes after 'WHERE' in sql query. If no condition, enter null.
    //Example: get_list(login, ["username", "password"], "username == 'or'"
    public static ArrayList<ArrayList> get_list(String table, String[] columns_name, String conditions) {

        ArrayList<ArrayList> result = new ArrayList<ArrayList>();
        try {
            String query;
            StringBuilder sql_builder = new StringBuilder("Select ");

//          Write the '(column1, column2, ...)'
            for (String col_name : columns_name)
                sql_builder.append(col_name).append(", ");

//          Substrings to remove last comma and space -> casts it back from string to stringbuilder
            query = sql_builder.substring(0, sql_builder.length() - 2);
            sql_builder = new StringBuilder(query);
            sql_builder.append(" from " + table);

            if (conditions != null) {
                sql_builder.append(" where " + conditions);
            }


            query = sql_builder.toString();
            Statement statement = conn.createStatement();
            ResultSet resultset = statement.executeQuery(query);
            while (resultset.next()) {
                ArrayList<String> curr_row = new ArrayList<String>();
                for (String col_name : columns_name) {
//                    System.out.println(resultset.getString(col_name));
                    curr_row.add(resultset.getString(col_name));
                }
                result.add(curr_row);
            }
            return result;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;

    }


    // Function that gets the last row that was added to a table (if the table contains an autoincrement ID)
    public static String get_max_id_row(String table, String column_name)
    {
        try{
            // Builds and executes the query
            String query = "SELECT max(" + column_name + ") FROM " + table;
            Statement statement = conn.createStatement();
            ResultSet resultset = statement.executeQuery(query);

            //Gets the result
            int max_id = -1;
            if (resultset.next()){
                max_id = resultset.getInt(1);
            }

            return Integer.toString(max_id);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "-1";
        }

    }

}
