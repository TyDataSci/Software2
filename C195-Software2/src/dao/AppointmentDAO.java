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
                        results.getInt("Contact_ID"),
                        CustomerDAO.getCustomerMap().get(results.getInt("Customer_ID")).toString(),
                        ContactDAO.getContactMap().get(results.getInt("Contact_ID")));
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
                        results.getInt("Contact_ID"),
                        CustomerDAO.getCustomerMap().get(results.getInt("Customer_ID")).toString(),
                        ContactDAO.getContactMap().get(results.getInt("Contact_ID")));
            }

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return appointment;
    }

    public static ObservableList<Appointment> getDailyAppointments() {
        ObservableList<Appointment> dailyAppointments = FXCollections.observableArrayList();
        try {
            Connection connection = DBConnection.getConnection();
            String fetchStatement = "SELECT * FROM " + tableName;
            fetchStatement += "\n" + "WHERE extract(DAY FROM start) = extract(DAY FROM NOW())";
            fetchStatement += "\n" + "AND extract(MONTH FROM start) = extract(MONTH  FROM NOW())";
            fetchStatement += "\n" + "AND extract(YEAR FROM start) = extract(YEAR  FROM NOW())";
            DBQuery.setPreparedStatement(connection,fetchStatement);
            PreparedStatement statement = DBQuery.getPreparedStatement();
            //System.out.println(statement.toString());
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
                        results.getInt("Contact_ID"),
                        CustomerDAO.getCustomerMap().get(results.getInt("Customer_ID")).toString(),
                        ContactDAO.getContactMap().get(results.getInt("Contact_ID")));
                dailyAppointments.add(appointment);}
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return dailyAppointments;
    }
    public static ObservableList<Appointment> getWeeklyAppointments() {
        ObservableList<Appointment> weeklyAppointments = FXCollections.observableArrayList();
        try {
            Connection connection = DBConnection.getConnection();
            String fetchStatement = "\n" + "SELECT * FROM " + tableName;
            fetchStatement += "\n" + "WHERE extract(WEEK FROM start) = extract(WEEK FROM NOW())";
            fetchStatement += "\n" + "AND extract(YEAR FROM start) = extract(YEAR FROM NOW())";
            DBQuery.setPreparedStatement(connection,fetchStatement);
            PreparedStatement statement = DBQuery.getPreparedStatement();
            //System.out.println(statement.toString());
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
                        results.getInt("Contact_ID"),
                        CustomerDAO.getCustomerMap().get(results.getInt("Customer_ID")).toString(),
                        ContactDAO.getContactMap().get(results.getInt("Contact_ID")));
                weeklyAppointments.add(appointment);}
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return weeklyAppointments;
    }
    public static ObservableList<Appointment> getMonthlyAppointments() {
        ObservableList<Appointment> monthlyAppointments = FXCollections.observableArrayList();
        try {
            Connection connection = DBConnection.getConnection();
            String fetchStatement = "\n" + "SELECT * FROM " + tableName;
            fetchStatement += "\n" + "WHERE extract(MONTH FROM start) = extract(MONTH FROM NOW())";
            fetchStatement += "\n" + "AND extract(YEAR FROM start) = extract(YEAR FROM NOW())";
            DBQuery.setPreparedStatement(connection,fetchStatement);
            PreparedStatement statement = DBQuery.getPreparedStatement();
            //System.out.println(statement.toString());
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
                        results.getInt("Contact_ID"),
                        CustomerDAO.getCustomerMap().get(results.getInt("Customer_ID")).toString(),
                        ContactDAO.getContactMap().get(results.getInt("Contact_ID")));
                monthlyAppointments.add(appointment);}
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return monthlyAppointments;
    }

    public static ObservableList<Appointment> getAppointmentsBySelectDate(int dayOf, int monthOf, int yearOf) {
        ObservableList<Appointment> dailyAppointments = FXCollections.observableArrayList();
        try {
            Connection connection = DBConnection.getConnection();
            String fetchStatement = "\n" + "SELECT * FROM " + tableName;
            fetchStatement += "\n" + "WHERE extract(DAY FROM start) = ?";
            fetchStatement += "\n" + "AND extract(MONTH FROM start) = ?";
            fetchStatement += "\n" + "AND extract(YEAR FROM start) = ?";
            DBQuery.setPreparedStatement(connection,fetchStatement);
            PreparedStatement statement = DBQuery.getPreparedStatement();
            statement.setInt(1, dayOf);
            statement.setInt(2, monthOf);
            statement.setInt(3, yearOf);
            //System.out.println(statement.toString());
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
                        results.getInt("Contact_ID"),
                        CustomerDAO.getCustomerMap().get(results.getInt("Customer_ID")).toString(),
                        ContactDAO.getContactMap().get(results.getInt("Contact_ID")));
                dailyAppointments.add(appointment);}
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return dailyAppointments;
    }

    public static void addAppointment(String title, String description,String location,String type,String start,String end,
                                     String createdBy,String lastUpdatedBy, int customerID, int userID, int contactID) {
        try {
            Connection connection = DBConnection.getConnection();
            String fetchStatement = "\n" + "INSERT INTO " + tableName + " ";
            fetchStatement += "\n" + "(Title,Description,Location,Type,Start,End,Create_Date,\n" +
                    "Created_By,Last_Update,Last_Updated_By,Customer_ID,User_ID,Contact_ID)";
            fetchStatement += "\n" + "values (?,?,?,?,?,?,NOW(),?,NOW(),?,?,?,?)";
            DBQuery.setPreparedStatement(connection, fetchStatement);
            PreparedStatement statement = DBQuery.getPreparedStatement();
            statement.setString(1, title);
            statement.setString(2, description);
            statement.setString(3, location);
            statement.setString(4, type);
            statement.setString(5, start);
            statement.setString(6, end);
            statement.setString(7, createdBy);
            statement.setString(8, lastUpdatedBy);
            statement.setInt(9, customerID);
            statement.setInt(10, userID);
            statement.setInt(11, contactID);
            //System.out.println(statement.toString());
            statement.execute();
            ResultSet results = statement.getResultSet();

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void updateAppointment(Appointment selectAppointment) {
        try {
            Connection connection = DBConnection.getConnection();
            String fetchStatement = "\n" + "UPDATE " + tableName;
            fetchStatement += "\n" + "SET Title=?,Description=?,Location=?,Type=?,Start=?,End=?,\n" +
                    "Last_Update=NOW(),Last_Updated_By=?,Customer_ID=?,User_ID=?,Contact_ID=?";
            fetchStatement += "\n" + "WHERE Appointment_ID=?";
                    DBQuery.setPreparedStatement(connection, fetchStatement);
            PreparedStatement statement = DBQuery.getPreparedStatement();
            statement.setString(1, selectAppointment.getTitle());
            statement.setString(2, selectAppointment.getDescription());
            statement.setString(3, selectAppointment.getLocation());
            statement.setString(4, selectAppointment.getType());
            statement.setString(5, selectAppointment.getStart());
            statement.setString(6, selectAppointment.getEnd());
            statement.setString(7, selectAppointment.getLastUpdatedBy());
            statement.setInt(8, selectAppointment.getCustomerId());
            statement.setInt(9, selectAppointment.getUserId());
            statement.setInt(10, selectAppointment.getContactId());
            statement.setInt(11, selectAppointment.getAppointmentId());
            System.out.println(statement.toString());
            statement.execute();
            ResultSet results = statement.getResultSet();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
