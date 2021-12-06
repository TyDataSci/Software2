package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class CustomerDAO {
    private static final String tableName ="customers";
    private static final HashMap<Integer, Customer> customerMap = new HashMap<Integer, Customer>();

    public static ObservableList<Customer> getAllCustomers() {
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        try {
            String fetchStatement = "SELECT * FROM " + tableName;
            Connection connection = DBConnection.getConnection();
            DBQuery.setPreparedStatement(connection, fetchStatement);
            PreparedStatement statement = DBQuery.getPreparedStatement();
            statement.execute();
            ResultSet results  = statement.getResultSet();
            while (results.next()){
                Customer customer = new Customer(
                        results.getInt("Customer_ID"),
                        results.getString("Customer_Name"),
                        results.getString("Address"),
                        results.getString("Postal_Code"),
                        results.getString("Phone"),
                        results.getString("Create_Date"),
                        results.getString("Created_By"),
                        results.getString("Last_Update"),
                        results.getString("Last_Updated_By"),
                        results.getInt("Division_ID"),
                DivisionDAO.getDivisionMap().get(results.getInt("Division_ID")));
                allCustomers.add(customer);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return allCustomers;
    }
    public static Customer getCustomer(int selectId) {
        Customer customer = null;
        try {
            String fetchStatement = "SELECT * FROM " + tableName + " WHERE Customer_ID = ?";
            Connection connection = DBConnection.getConnection();
            DBQuery.setPreparedStatement(connection, fetchStatement);
            PreparedStatement statement = DBQuery.getPreparedStatement();
            statement.setInt(1,selectId);
            statement.execute();
            ResultSet results  = statement.getResultSet();
            while (results.next()){
                customer = new Customer(
                        results.getInt("Customer_ID"),
                        results.getString("Customer_Name"),
                        results.getString("Address"),
                        results.getString("Postal_Code"),
                        results.getString("Phone"),
                        results.getString("Create_Date"),
                        results.getString("Created_By"),
                        results.getString("Last_Update"),
                        results.getString("Last_Updated_By"),
                        results.getInt("Division_ID"),
                        DivisionDAO.getDivisionMap().get(results.getInt("Division_ID")));}
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return customer;
    }

    public static HashMap<Integer, Customer> getCustomerMap() {
        if (customerMap.isEmpty()){
            System.out.println("Updating Customer HashMap");
            updateCustomerMap();
        }
        return customerMap;
    }

    public static void updateCustomerMap() {
        try {
            customerMap.clear();
            int key = 0;
            Customer value = null;
            String fetchStatement = "SELECT * FROM " + tableName;
            Connection connection = DBConnection.getConnection();
            DBQuery.setPreparedStatement(connection, fetchStatement);
            PreparedStatement statement = DBQuery.getPreparedStatement();
            statement.execute();
            ResultSet results  = statement.getResultSet();
            while (results.next()){
                key = results.getInt("Customer_ID");
                value = getCustomer(key);

                customerMap.put(key,value);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
