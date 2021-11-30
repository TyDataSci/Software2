package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactDAO {
    private static final String tableName = "contacts";

    public static ObservableList<Contact> getAllContacts() {
        ObservableList<Contact> allContacts = FXCollections.observableArrayList();
        try {
            String fetchStatement = "SELECT * FROM " + tableName;
            Connection connection = DBConnection.getConnection();
            DBQuery.setPreparedStatement(connection, fetchStatement);
            PreparedStatement statement = DBQuery.getPreparedStatement();
            statement.execute();
            ResultSet results  = statement.getResultSet();
            while (results.next()){
                Contact contact = new Contact(
                        results.getInt("Contact_ID"),
                        results.getString("Contact_Name"),
                        results.getString("Email"));
                allContacts.add(contact);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return allContacts;
    }

    public static Contact getContact(int selectContactID) {
        Contact contact = null;
        try {
            String fetchStatement = "SELECT * FROM " + tableName + " WHERE Contact_ID = ?";
            System.out.println(fetchStatement);
            Connection connection = DBConnection.getConnection();
            DBQuery.setPreparedStatement(connection, fetchStatement);
            PreparedStatement statement = DBQuery.getPreparedStatement();
            statement.setInt(1,selectContactID);
            statement.execute();
            ResultSet results  = statement.getResultSet();
            while (results.next()) {
                contact = new Contact(
                        results.getInt("Contact_ID"),
                        results.getString("Contact_Name"),
                        results.getString("Email"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return contact;
    }
}
