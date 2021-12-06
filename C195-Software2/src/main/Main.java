package main;

/**
 *
 * @author Tyler Sanders
 */

import dao.*;
import model.Appointment;
import model.Contact;
import utilities.Local;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        System.out.println(DivisionDAO.getDivisionMap().get(1));
        System.out.println(DivisionDAO.getDivisionMap().get(2));
        System.out.println(DivisionDAO.getDivisionMap().get(3));
        System.out.println(AppointmentDAO.getDailyAppointments());
        System.out.println(AppointmentDAO.getWeeklyAppointments());
        System.out.println("test: " + AppointmentDAO.getAppointmentsBySelectDate(28,5,2020).get(0).getStart());
        Appointment newApp = AppointmentDAO.getAppointment(3);
        newApp.setTitle("Long Meeting");
        AppointmentDAO.updateAppointment(newApp);
        System.out.println("Test Update: " + AppointmentDAO.getAppointment(3).getTitle());
        System.out.println("Test Contact Map: " + ContactDAO.getContactMap().get(1));
        System.out.println("Test Contact Map: " + ContactDAO.getContactMap().get(2));
        System.out.println("Test Customer Map: " + CustomerDAO.getCustomerMap().get(2));
        System.out.println("Test Customer Map: " + CustomerDAO.getCustomerMap().get(1));
        //String fetchStatement = "SELECT * FROM countries";
       // DBQuery.setPreparedStatement(fetchStatement);
        //PreparedStatement statement = DBQuery.getPreparedStatement();

        // "INSERT INTO countries (country, createDate, createdBy, lastUpdateBy)
        // VALUES('US', '2020-02-22 00:00:00', 'admin', 'admin";
        //statement.execute();
        //ResultSet results  = statement.getResultSet();
        //while (results.next())
        //    System.out.println(results.getString("Country"));
       // Country country = CountryDAO.getCountry("UK");
        //System.out.println("test: " + country.getCountryName());



        //System.out.println(AppointmentDAO.getAllAppointments().get(1).getAppointmentId());
        //System.out.println(Local.getLocation());
        //System.out.println(Local.getTimeZoneId() + "\n");

        launch(args);
        DBConnection.closeConnection();
    }



}
