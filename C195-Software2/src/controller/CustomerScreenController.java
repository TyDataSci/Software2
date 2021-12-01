package controller;

import dao.CustomerDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import model.Customer;
import utilities.ControllerTabState;
import utilities.Local;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerScreenController implements Initializable {
    public Button weeklyTab;
    public Button customerTab;
    public Button reportsTab;
    public Button dailyTab;
    public Button monthlyTab;
    public Button allAppointmentsTab;
    public Label nowDatetime;
    public Label welcomeUsername;
    public TextField searchBox;
    public ToggleButton languageEN;
    public ToggleButton languageFR;
    public Button editExistingEntry;
    public Button addNewEntry;
    public ComboBox dropdownComboBox;
    public Line accentLine;
    public Button logOut;
    public Button searchButton;
    public Label tabNameLabel;
    public Button deleteEntry;
    public Label localLocation;
    public TableView<Customer> mainTable;
    public TableColumn<Customer, String> col1;
    public TableColumn<Customer, String> col2;
    public TableColumn<Customer, String> col3;
    public TableColumn<Customer, String> col4;
    public TableColumn<Customer, String> col5;
    public TableColumn<Customer, String> col6;
    private String entryScreen = "/view/AppointmentEntryScreen.fxml";
    private String titleOfEntryScreen = "New Appointment Entry";


    public void onCustomerTab(ActionEvent actionEvent) {
        ControllerTabState.setState("isCustomersTab");
        setTabProperties();
    }

    public void onReportsTab(ActionEvent actionEvent) throws IOException {
        ControllerTabState.setState("isReportsTab");
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Appointment Scheduler");
        window.setScene(scene);
        window.show();
    }

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

    public void onLanguageEN(ActionEvent actionEvent) {
    }

    public void onLanguageFR(ActionEvent actionEvent) {
    }

    public void onSearchButton(ActionEvent mouseEvent) {
    }

    public void onAddNewEntry(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(entryScreen));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle(titleOfEntryScreen);
        window.setScene(scene);
        window.show();
    }

    public void onDropdownComboBox(ActionEvent actionEvent) {
    }
    public void setCustomerTable(){
        mainTable.setItems(CustomerDAO.getAllCustomers());
        col1.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        col2.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        col3.setCellValueFactory(new PropertyValueFactory<>("phone"));
        col4.setCellValueFactory(new PropertyValueFactory<>("address"));
        col5.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        col6.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
    }

    public void setTabProperties() {
        nowDatetime.setText(Local.getNowDateTime());
        tabNameLabel.setText("Customers");
        accentLine.setStroke(Paint.valueOf("#00bfff"));
        addNewEntry.setText("Add Customer");
        entryScreen = "/view/CustomerEntryScreen.fxml";
        titleOfEntryScreen = "New Customer Entry";
        setCustomerTable();


    }

    public void onLogOut(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Log In");
        window.setScene(scene);
        window.show();
    }

    public void onDeleteEntry(ActionEvent actionEvent) {
    }

    public void onEditExistingEntry(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(ControllerTabState.getState());
        setTabProperties();
        localLocation.setText(Local.getLocation());
    }
}
