package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentDAO {
    private static final String tableName ="appointments";

    public static ObservableList<Appointment> getAllAppointments() {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        try {
            Connection connection = DBConnection.getConnection();
            String fetchStatement = "SELECT * FROM " + tableName;
            DBQuery.setPreparedStatement(connection,fetchStatement);
            PreparedStatement statement = DBQuery.getPreparedStatement();
            statement.execute();
            ResultSet results = statement.getResultSet();
            while (results.next()) {
                Appointment appointment = new Appointment(
                        results.getInt("Appointment_ID"),
                        results.getString("Title"),
                        results.getString("Description"),
                        results.getString("Location"),
                        results.getString("Type"),
                        results.getString("Start"),
                        results.getString("End"),
                        results.getString("Create_Date"),
                        results.getString("Created_By"),
                        results.getString("Last_Update"),
                        results.getString("Last_Updated_By"),
                        results.getInt("Customer_ID"),
                        results.getInt("User_ID"),
                        results.getInt("Contact_ID"));
                allAppointments.add(appointment);}
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

            return allAppointments;
    }
    public static Appointment getAppointment(int selectId) {
        Appointment appointment = null;
        try {
            Connection connection = DBConnection.getConnection();
            String fetchStatement = "SELECT * FROM " + tableName + " WHERE Appointment_ID = ?";
            DBQuery.setPreparedStatement(connection, fetchStatement);
            PreparedStatement statement = DBQuery.getPreparedStatement();
            statement.setInt(1, selectId);
            statement.execute();
            ResultSet results = statement.getResultSet();
            while (results.next()) {
                appointment = new Appointment(
                        results.getInt("Appointment_ID"),
                        results.getString("Title"),
                        results.getString("Description"),
                        results.getString("Location"),
                        results.getString("Type"),
                        results.getString("Start"),
                        results.getString("End"),
                        results.getString("Create_Date"),
                        results.getString("Created_By"),
                        results.getString("Last_Update"),
                        results.getString("Last_Updated_By"),
                        results.getInt("Customer_ID"),
                        results.getInt("User_ID"),
                        results.getInt("Contact_ID"));
            }

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return appointment;
    }


    public static ObservableList<Appointment> getDailyAppointments(int dayOfYear) {
        ObservableList<Appointment> dailyAppointments = FXCollections.observableArrayList();
        try {
            Connection connection = DBConnection.getConnection();
            String fetchStatement = "SELECT * FROM " + tableName;
            fetchStatement += "\n" + "WHERE extract(DAY FROM start) = ?";
            fetchStatement += "\n" + "AND extract(MONTH FROM start) = extract(MONTH FROM '2020-05-28')";
            fetchStatement += "\n" + "AND extract(YEAR FROM start) = extract(YEAR FROM '2020-05-28')";
            DBQuery.setPreparedStatement(connection,fetchStatement);
            PreparedStatement statement = DBQuery.getPreparedStatement();
            statement.setInt(1, dayOfYear);
            System.out.println(statement.toString());
            statement.execute();
            ResultSet results = statement.getResultSet();
            while (results.next()) {
                Appointment appointment = new Appointment(
                        results.getInt("Appointment_ID"),
                        results.getString("Title"),
                        results.getString("Description"),
                        results.getString("Location"),
                        results.getString("Type"),
                        results.getString("Start"),
                        results.getString("End"),
                        results.getString("Create_Date"),
                        results.getString("Created_By"),
                        results.getString("Last_Update"),
                        results.getString("Last_Updated_By"),
                        results.getInt("Customer_ID"),
                        results.getInt("User_ID"),
                        results.getInt("Contact_ID"));
                dailyAppointments.add(appointment);}
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return dailyAppointments;
    }
    public static ObservableList<Appointment> getWeeklyAppointments(int weekOfYear) {
        ObservableList<Appointment> weeklyAppointments = FXCollections.observableArrayList();
        try {
            Connection connection = DBConnection.getConnection();
            String fetchStatement = "SELECT * FROM " + tableName;
            fetchStatement += "\n" + "WHERE extract(WEEK FROM start) = ?";
            fetchStatement += "\n" + "AND extract(YEAR FROM start) = extract(YEAR FROM '2020-05-28')";
            DBQuery.setPreparedStatement(connection,fetchStatement);
            PreparedStatement statement = DBQuery.getPreparedStatement();
            statement.setInt(1, weekOfYear);
            System.out.println(statement.toString());
            statement.execute();
            ResultSet results = statement.getResultSet();
            while (results.next()) {
                Appointment appointment = new Appointment(
                        results.getInt("Appointment_ID"),
                        results.getString("Title"),
                        results.getString("Description"),
                        results.getString("Location"),
                        results.getString("Type"),
                        results.getString("Start"),
                        results.getString("End"),
                        results.getString("Create_Date"),
                        results.getString("Created_By"),
                        results.getString("Last_Update"),
                        results.getString("Last_Updated_By"),
                        results.getInt("Customer_ID"),
                        results.getInt("User_ID"),
                        results.getInt("Contact_ID"));
                weeklyAppointments.add(appointment);}
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return weeklyAppointments;
    }
    public static ObservableList<Appointment> getMonthlyAppointments(int monthOfYear) {
        ObservableList<Appointment> monthlyAppointments = FXCollections.observableArrayList();
        try {
            Connection connection = DBConnection.getConnection();
            String fetchStatement = "SELECT * FROM " + tableName;
            fetchStatement += "\n" + "WHERE extract(MONTH FROM start) = ?";
            fetchStatement += "\n" + "AND extract(YEAR FROM start) = extract(YEAR FROM '2020-05-28')";
            DBQuery.setPreparedStatement(connection,fetchStatement);
            PreparedStatement statement = DBQuery.getPreparedStatement();
            statement.setInt(1, monthOfYear);
            System.out.println(statement.toString());
            statement.execute();
            ResultSet results = statement.getResultSet();
            while (results.next()) {
                Appointment appointment = new Appointment(
                        results.getInt("Appointment_ID"),
                        results.getString("Title"),
                        results.getString("Description"),
                        results.getString("Location"),
                        results.getString("Type"),
                        results.getString("Start"),
                        results.getString("End"),
                        results.getString("Create_Date"),
                        results.getString("Created_By"),
                        results.getString("Last_Update"),
                        results.getString("Last_Updated_By"),
                        results.getInt("Customer_ID"),
                        results.getInt("User_ID"),
                        results.getInt("Contact_ID"));
                monthlyAppointments.add(appointment);}
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return monthlyAppointments;
    }

}
