package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Division;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DivisionDAO {
    private static final String tableName = "first_level_divisions";

    public static ObservableList<Division> getAllDivisions() {
        ObservableList<Division> allDivisions = FXCollections.observableArrayList();
        try {
            String fetchStatement = "SELECT * FROM " + tableName;
            Connection connection = DBConnection.getConnection();
            DBQuery.setPreparedStatement(connection, fetchStatement);
            PreparedStatement statement = DBQuery.getPreparedStatement();
            statement.execute();
            ResultSet results  = statement.getResultSet();
            while (results.next()){
                Division division = new Division(
                        results.getInt("Division_ID"),
                        results.getString("Division"),
                        results.getString("Create_Date"),
                        results.getString("Created_By"),
                        results.getString("Last_Update"),
                        results.getString("Last_Updated_By"),
                        results.getInt("Country_ID"));
                allDivisions.add(division);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return allDivisions;
    }

    public static HashMap<Integer,String> getDivisionMap() {
        HashMap<Integer,String> divisionMap = new HashMap<>();
        try {
            int key = 0;
            String value = null;
            String fetchStatement = "SELECT * FROM " + tableName;
            Connection connection = DBConnection.getConnection();
            DBQuery.setPreparedStatement(connection, fetchStatement);
            PreparedStatement statement = DBQuery.getPreparedStatement();
            statement.execute();
            ResultSet results  = statement.getResultSet();
            while (results.next()){
                       key = results.getInt("Division_ID");
                       value = results.getString("Division");

                divisionMap.put(key,value);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return divisionMap;
    }


    public static Division getDivision(int selectDivisionID) {
        Division division = null;
        try {
            String fetchStatement = "SELECT * FROM " + tableName + " WHERE Division_ID = ?";
            System.out.println(fetchStatement);
            Connection connection = DBConnection.getConnection();
            DBQuery.setPreparedStatement(connection, fetchStatement);
            PreparedStatement statement = DBQuery.getPreparedStatement();
            statement.setInt(1,selectDivisionID);
            statement.execute();
            ResultSet results  = statement.getResultSet();
            while (results.next()){
                division = new Division(
                        results.getInt("Division_ID"),
                        results.getString("Division"),
                        results.getString("Create_Date"),
                        results.getString("Created_By"),
                        results.getString("Last_Update"),
                        results.getString("Last_Updated_By"),
                        results.getInt("Country_ID"));
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return division;
    }
}
