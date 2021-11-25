package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentDAO {
    private static final String tableName ="appointments";
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
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

            return allAppointments;
    }
    public static Appointment getAppointment(int selectId) throws SQLException {
        Appointment appointment = null;
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

        return appointment;
    }
}
