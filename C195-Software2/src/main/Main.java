package main;

/**
 *
 * @author Tyler Sanders
 */

import util.Local;
import util.DBQuery;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.DBConnection;

import java.sql.*;

public class Main extends Application{
    @Override
    public void start(Stage stage) throws Exception {


        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
        stage.setTitle("Main Screen");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) throws SQLException {
        DBConnection.openConnection();
        System.out.println(Local.getLanguage());
        String fetchStatement = "SELECT * FROM countries";
        DBQuery.setPreparedStatement(DBConnection.getConnection(),fetchStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();

        // "INSERT INTO countries (country, createDate, createdBy, lastUpdateBy)
        // VALUES('US', '2020-02-22 00:00:00', 'admin', 'admin";
        statement.execute();
        ResultSet results  = statement.getResultSet();
        while (results.next())
            System.out.println(results.getString("Country"));

        System.out.println(Local.getLocation());
        System.out.println(Local.getTimeZoneId() + "\n");

        launch(args);
        DBConnection.closeConnection();
    }



}
