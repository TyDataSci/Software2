package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Division;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DivisionDAO {
    private static final String tableName = "first_level_divisions";

    public static ObservableList<Division> getAllDivisions() throws SQLException {
        ObservableList<Division> allDivisions = FXCollections.observableArrayList();
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
        return allDivisions;
    }

    public static Division getDivision(int selectDivisionID) throws SQLException {
        Division division = null;
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

        return division;
    }
}
