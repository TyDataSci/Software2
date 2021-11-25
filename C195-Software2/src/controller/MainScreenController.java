package controller;

import dao.*;
import model.Contact;
import model.Country;
import utilities.Local;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

import dao.AppointmentDAO;
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
import model.Appointment;
import utilities.Local;
import utilities.ControllerTabState;
import dao.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
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
    public TableView<Appointment> mainTable;
    public TableColumn<Appointment, String> col1;
    public TableColumn<Appointment, String> col2;
    public TableColumn<Appointment, String> col3;
    public TableColumn<Appointment, String> col4;
    public TableColumn<Appointment, String> col5;
    public TableColumn<Appointment, String> col6;
    public TableColumn<Appointment, String> col7;
    public TableColumn<Appointment, String> col8;
    public TableColumn<Appointment, String> col9;
    public TableColumn<Appointment, String> col10;

    private String entryScreen = "/view/AppointmentEntryScreen.fxml";
    private String titleOfEntryScreen = "New Appointment Entry";


    public void onCustomerTab(ActionEvent actionEvent) throws IOException, SQLException {
        ControllerTabState.setState("isCustomersTab");
        setTabProperties();
    }

    public void onReportsTab(ActionEvent actionEvent) throws IOException, SQLException {
        ControllerTabState.setState("isReportsTab");
        setTabProperties();
    }

    public void onDailyTab(ActionEvent actionEvent) throws IOException, SQLException {
        ControllerTabState.setState("isDailyTab");
        setTabProperties();

    }

    public void onWeeklyTab(ActionEvent actionEvent) throws IOException, SQLException {
        ControllerTabState.setState("isWeeklyTab");
        setTabProperties();
    }

    public void onMonthlyTab(ActionEvent actionEvent) throws IOException, SQLException {
        ControllerTabState.setState("isMonthlyTab");
        setTabProperties();
    }

    public void onAllAppointmentsTab(ActionEvent actionEvent) throws IOException, SQLException {
        ControllerTabState.setState("isAllTab");
        setTabProperties();

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

    public void setTabProperties() throws SQLException, IOException {
        nowDatetime.setText(Local.getNowDateTime());

        if (Objects.equals(ControllerTabState.getState(), "isDailyTab")) {
            tabNameLabel.setText("Daily Appointments");
            accentLine.setStroke(Paint.valueOf("#8a2be2"));
            addNewEntry.setText("Add Appointment");
            entryScreen = "/view/AppointmentEntryScreen.fxml";
            titleOfEntryScreen = "New Appointment Entry";
        }
        else if (Objects.equals(ControllerTabState.getState(), "isWeeklyTab")) {
            tabNameLabel.setText("Weekly Appointments");
            accentLine.setStroke(Paint.valueOf("#3cb371"));
            addNewEntry.setText("Add Appointment");
            entryScreen = "/view/AppointmentEntryScreen.fxml";
            titleOfEntryScreen = "New Appointment Entry";
        }
        else if (Objects.equals(ControllerTabState.getState(), "isMonthlyTab")) {
            tabNameLabel.setText("Monthly Appointments");
            accentLine.setStroke(Paint.valueOf("#dc143c"));
            addNewEntry.setText("Add Appointment");
            entryScreen = "/view/AppointmentEntryScreen.fxml";
            titleOfEntryScreen = "New Appointment Entry";
        }
        else if (Objects.equals(ControllerTabState.getState(), "isAllTab")) {
            tabNameLabel.setText("All Appointments");
            accentLine.setStroke(Paint.valueOf("CORAL"));
            addNewEntry.setText("Add Appointment");
            entryScreen = "/view/AppointmentEntryScreen.fxml";
            titleOfEntryScreen = "New Appointment Entry";
        }
        else if (Objects.equals(ControllerTabState.getState(), "isCustomersTab")){
            tabNameLabel.setText("Customers");
            accentLine.setStroke(Paint.valueOf("#00bfff"));
            addNewEntry.setText("Add Customer");
            entryScreen = "/view/CustomerEntryScreen.fxml";
            titleOfEntryScreen = "New Customer Entry";
        }
        else if (Objects.equals(ControllerTabState.getState(), "isReportsTab")){
            tabNameLabel.setText("Reports");
            accentLine.setStroke(Paint.valueOf("#ffd400"));
            addNewEntry.setText("Add Appointment");
            entryScreen = "/view/AppointmentEntryScreen.fxml";
            titleOfEntryScreen = "New Appointment Entry";
        }

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
            //setTabProperties();
        tabNameLabel.setText("Daily Appointments");
        accentLine.setStroke(Paint.valueOf("#8a2be2"));
        addNewEntry.setText("Add Appointment");
        entryScreen = "/view/AppointmentEntryScreen.fxml";
        titleOfEntryScreen = "New Appointment Entry";
        //CustomerDAO.getCustomer(1).getCustomerName();
        //col1.setCellValueFactory(new PropertyValueFactory<>("title"));
       /* for (int i=0; i < AppointmentDAO.getAllAppointments().size(); i++){
            System.out.println(AppointmentDAO.getAllAppointments().get(i).getAppointmentId());
            System.out.println(AppointmentDAO.getAllAppointments().get(i).getTitle());
            System.out.println(AppointmentDAO.getAllAppointments().get(i).getDescription());
            System.out.println(AppointmentDAO.getAllAppointments().get(i).getLocation());
            System.out.println(AppointmentDAO.getAllAppointments().get(i).getType());
            System.out.println(AppointmentDAO.getAllAppointments().get(i).getStart());
            System.out.println(AppointmentDAO.getAllAppointments().get(i).getEnd());
            System.out.println(AppointmentDAO.getAllAppointments().get(i).getCreateDate());
            System.out.println(AppointmentDAO.getAllAppointments().get(i).getCreatedBy());
            System.out.println(AppointmentDAO.getAllAppointments().get(i).getLastUpdate());
            System.out.println(AppointmentDAO.getAllAppointments().get(i).getLastUpdatedBy());
            System.out.println(AppointmentDAO.getAllAppointments().get(i).getCustomerId());
            System.out.println(AppointmentDAO.getAllAppointments().get(i).getUserId());
            System.out.println(AppointmentDAO.getAllAppointments().get(i).getContactId());
        } */

        //col2.setCellValueFactory(new PropertyValueFactory<>("title"));
            //col3.setCellValueFactory(new PropertyValueFactory<>("description"));
            //col4.setCellValueFactory(new PropertyValueFactory<>("location"));
            //col5.setCellValueFactory(new PropertyValueFactory<>("type"));
            //col6.setCellValueFactory(new PropertyValueFactory<>("start"));
            //col7.setCellValueFactory(new PropertyValueFactory<>("end"));
           // col8.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            //col9.setCellValueFactory(new PropertyValueFactory<>("userId"));
            //col10.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        localLocation.setText(Local.getLocation());
    }
}
