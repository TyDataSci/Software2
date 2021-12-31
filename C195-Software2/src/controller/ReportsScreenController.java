package controller;

import dao.AppointmentDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import model.GroupBy;
import utilities.ControllerTabState;
import utilities.CurrentUser;
import utilities.Local;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportsScreenController implements Initializable {

    public Button weeklyTab;
    public Button customerTab;
    public Button reportsTab;
    public Button dailyTab;
    public Button monthlyTab;
    public Button allAppointmentsTab;
    public Label localLocation;
    public Label nowDatetime;
    public Label welcomeUsername;
    public Line accentLine;
    public Button logOut;
    public ToggleButton languageEN;
    public ToggleButton languageFR;
    public ComboBox<GroupBy> monthComboBox;
    public ComboBox<GroupBy> customerComboBox;
    public ComboBox<GroupBy> typeComboBox;
    public TableView typeTable;
    public TableView monthTable;
    public TableView customerTable;

    public TableColumn col1;
    public TableColumn col2;
    public TableColumn col3;
    public TableColumn col4;
    public TableColumn col5;
    public TableColumn col6;
    public TableColumn col7;
    public TableColumn col8;
    public TableColumn col9;

    public TableColumn col11;
    public TableColumn col12;
    public TableColumn col13;
    public TableColumn col14;
    public TableColumn col16;
    public TableColumn col17;
    public TableColumn col18;
    public TableColumn col19;
    public TableColumn col15;

    public TableColumn col21;
    public TableColumn col22;
    public TableColumn col24;
    public TableColumn col25;
    public TableColumn col26;
    public TableColumn col27;
    public TableColumn col28;
    public TableColumn col29;
    public TableColumn col23;
    public Label typeCount;
    public Label monthCount;
    public Label customerCount;

    public void onDailyTab(ActionEvent actionEvent) throws IOException {
        ControllerTabState.setState("isDailyTab");
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Appointment Scheduler");
        window.setScene(scene);
        window.show();

    }

    public void onWeeklyTab(ActionEvent actionEvent) throws IOException {
        ControllerTabState.setState("isWeeklyTab");
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Appointment Scheduler");
        window.setScene(scene);
        window.show();
    }

    public void onMonthlyTab(ActionEvent actionEvent) throws IOException {
        ControllerTabState.setState("isMonthlyTab");
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Appointment Scheduler");
        window.setScene(scene);
        window.show();
    }

    public void onAllAppointmentsTab(ActionEvent actionEvent) throws IOException {
        ControllerTabState.setState("isAllTab");
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Appointment Scheduler");
        window.setScene(scene);
        window.show();

    }
    public void onCustomerTab(ActionEvent actionEvent) throws IOException {
        ControllerTabState.setState("isCustomersTab");
        Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerScreen.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Appointment Scheduler");
        window.setScene(scene);
        window.show();
    }

    public void onLogOut(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Log In");
        window.setScene(scene);
        window.show();
    }

    public void onLanguageEN(ActionEvent actionEvent) {
    }

    public void onLanguageFR(ActionEvent actionEvent) {
    }


    public void setReportsTables() {
        col1.setText("Appointment ID");
        col1.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        col2.setText("Title");
        col2.setCellValueFactory(new PropertyValueFactory<>("title"));
        col3.setText("Description");
        col3.setCellValueFactory(new PropertyValueFactory<>("description"));
        col4.setText("Location");
        col4.setCellValueFactory(new PropertyValueFactory<>("location"));
        col5.setText("Contact");
        col5.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        col6.setText("Type");
        col6.setCellValueFactory(new PropertyValueFactory<>("type"));
        col7.setText("Start");
        col7.setCellValueFactory(new PropertyValueFactory<>("start"));
        col8.setText("End");
        col8.setCellValueFactory(new PropertyValueFactory<>("end"));
        col9.setText("Customer");
        col9.setCellValueFactory(new PropertyValueFactory<>("customerString"));
        col11.setText("Appointment ID");
        col11.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        col12.setText("Title");
        col12.setCellValueFactory(new PropertyValueFactory<>("title"));
        col13.setText("Description");
        col13.setCellValueFactory(new PropertyValueFactory<>("description"));
        col14.setText("Location");
        col14.setCellValueFactory(new PropertyValueFactory<>("location"));
        col15.setText("Contact");
        col15.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        col16.setText("Type");
        col16.setCellValueFactory(new PropertyValueFactory<>("type"));
        col17.setText("Start");
        col17.setCellValueFactory(new PropertyValueFactory<>("start"));
        col18.setText("End");
        col18.setCellValueFactory(new PropertyValueFactory<>("end"));
        col19.setText("Customer");
        col19.setCellValueFactory(new PropertyValueFactory<>("customerString"));
        col21.setText("Appointment ID");
        col21.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        col22.setText("Title");
        col22.setCellValueFactory(new PropertyValueFactory<>("title"));
        col23.setText("Description");
        col23.setCellValueFactory(new PropertyValueFactory<>("description"));
        col24.setText("Location");
        col24.setCellValueFactory(new PropertyValueFactory<>("location"));
        col25.setText("Contact");
        col25.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        col26.setText("Type");
        col26.setCellValueFactory(new PropertyValueFactory<>("type"));
        col27.setText("Start");
        col27.setCellValueFactory(new PropertyValueFactory<>("start"));
        col28.setText("End");
        col28.setCellValueFactory(new PropertyValueFactory<>("end"));
        col29.setText("Customer");
        col29.setCellValueFactory(new PropertyValueFactory<>("customerString"));

    }

    public void onTypeComboBox(ActionEvent actionEvent) {
        typeTable.setItems(AppointmentDAO.getAppointmentsByType(typeComboBox.getSelectionModel().getSelectedItem()));
        typeCount.setText(String.valueOf(typeComboBox.getSelectionModel().getSelectedItem().getCount()));
    }

    public void onMonthComboBox(ActionEvent actionEvent) {
        monthTable.setItems(AppointmentDAO.getAppointmentsByMonth(monthComboBox.getSelectionModel().getSelectedItem()));
        monthCount.setText(String.valueOf(monthComboBox.getSelectionModel().getSelectedItem().getCount()));
    }

    public void onCustomerComboBox(ActionEvent actionEvent) {
        customerTable.setItems(AppointmentDAO.getAppointmentsByCustomer(customerComboBox.getSelectionModel().getSelectedItem()));
        customerCount.setText(String.valueOf(customerComboBox.getSelectionModel().getSelectedItem().getCount()));
    }

    public void onReportsTab(ActionEvent actionEvent) {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setReportsTables();
        welcomeUsername.setText("Welcome, " + CurrentUser.getCurrentUser());
        localLocation.setText(Local.getLocation());
        typeComboBox.setPromptText("Choose a Type");
        typeComboBox.setItems(AppointmentDAO.getTypeAppointmentGroupBy());
        monthComboBox.setPromptText("Choose a Month");
        monthComboBox.setItems(AppointmentDAO.getMonthAppointmentGroupBy());
        customerComboBox.setPromptText("Choose a Customer");
        customerComboBox.setItems(AppointmentDAO.getCustomerAppointmentGroupBy());
    }
}
