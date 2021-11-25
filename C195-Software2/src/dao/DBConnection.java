package dao;
import  java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String dbURL = protocol + vendorName + ipAddress + databaseName ;
    private static final String MYSQLJBCDriver = "com.mysql.cj.jdbc.Driver";
    private static final String username = "sqlUser";
    private static final String password = "Passw0rd!";
    private static Connection connection;

    public  static Connection openConnection() {
        try {
            Class.forName(MYSQLJBCDriver);
            connection = DriverManager.getConnection(dbURL, username, password);

            System.out.println("Connection Successful");

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }


    public static Connection getConnection(){
        return connection;
    }

    public static  void closeConnection() {
        try{
            connection.close();
            System.out.println("Disconnection Successful");
        }
        catch (Exception e){
            //Do nothing;
        }
    }

    public static String getDatabaseName() {
        return databaseName;
    }


}
