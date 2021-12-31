package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class UserDAO {
    private static final String tableName = "users";
    private static final HashMap<Integer,String> userMap = new HashMap<>();

    public static ObservableList<User> getAllUsers() {
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        try {
            String fetchStatement = "SELECT * FROM " + tableName;
            Connection connection = DBConnection.getConnection();
            DBQuery.setPreparedStatement(connection, fetchStatement);
            PreparedStatement statement = DBQuery.getPreparedStatement();
            statement.execute();
            ResultSet results  = statement.getResultSet();
            while (results.next()){
                User user = new User(
                        results.getInt("User_ID"),
                        results.getString("User_Name"),
                        results.getString("Password"),
                        results.getString("Create_Date"),
                        results.getString("Created_By"),
                        results.getString("Last_Update"),
                        results.getString("Last_Updated_By"));
                allUsers.add(user);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return allUsers;
    }

    public static User getUser(int selectUserID) {
        User user = null;
        try {
            String fetchStatement = "SELECT * FROM " + tableName + " WHERE User_ID = ?";
            System.out.println(fetchStatement);
            Connection connection = DBConnection.getConnection();
            DBQuery.setPreparedStatement(connection, fetchStatement);
            PreparedStatement statement = DBQuery.getPreparedStatement();
            statement.setInt(1,selectUserID);
            statement.execute();
            ResultSet results  = statement.getResultSet();
            while (results.next()) {
                user = new User(
                        results.getInt("User_ID"),
                        results.getString("User_Name"),
                        results.getString("Password"),
                        results.getString("Create_Date"),
                        results.getString("Created_By"),
                        results.getString("Last_Update"),
                        results.getString("Last_Updated_By"));
            }

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return user;
    }
    public static User getUser(String selectUserName) {
        User user = null;
        try {
            String fetchStatement = "SELECT * FROM " + tableName + " WHERE User_Name = ?";
            System.out.println(fetchStatement);
            Connection connection = DBConnection.getConnection();
            DBQuery.setPreparedStatement(connection, fetchStatement);
            PreparedStatement statement = DBQuery.getPreparedStatement();
            statement.setString(1,selectUserName);
            statement.execute();
            ResultSet results  = statement.getResultSet();
            while (results.next()) {
                user = new User(
                        results.getInt("User_ID"),
                        results.getString("User_Name"),
                        results.getString("Password"),
                        results.getString("Create_Date"),
                        results.getString("Created_By"),
                        results.getString("Last_Update"),
                        results.getString("Last_Updated_By"));
            }

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return user;
    }

    public static HashMap<Integer,String> getUserMap() {
        if (userMap.isEmpty()){
            //System.out.println("Updating Division HashMap");
            updateUserMap();
        }
        return userMap;
    }

    public static void updateUserMap() {
        try {
            userMap.clear();
            int key = 0;
            String value = null;
            String fetchStatement = "SELECT * FROM " + tableName;
            Connection connection = DBConnection.getConnection();
            DBQuery.setPreparedStatement(connection, fetchStatement);
            PreparedStatement statement = DBQuery.getPreparedStatement();
            statement.execute();
            ResultSet results  = statement.getResultSet();
            while (results.next()){
                key = results.getInt("User_ID");
                value = results.getString("User_Name");

                userMap.put(key,value);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
