package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import util.Local;
import util.ControllerTabState;

import java.io.IOException;
import java.net.URL;
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
    private String entryScreen = "/view/AppointmentEntryScreen.fxml";
    private String titleOfEntryScreen = "New Appointment Entry";


    public void onCustomerTab(ActionEvent actionEvent) throws IOException {
        ControllerTabState.setState("isCustomersTab");
        setTabProperties();
    }

    public void onReportsTab(ActionEvent actionEvent) throws IOException {
        ControllerTabState.setState("isReportsTab");
        setTabProperties();
    }

    public void onDailyTab(ActionEvent actionEvent) throws IOException {
        ControllerTabState.setState("isDailyTab");
        setTabProperties();

    }

    public void onWeeklyTab(ActionEvent actionEvent) throws IOException {
        ControllerTabState.setState("isWeeklyTab");
        setTabProperties();
    }

    public void onMonthlyTab(ActionEvent actionEvent) throws IOException {
        ControllerTabState.setState("isMonthlyTab");
        setTabProperties();
    }

    public void onAllAppointmentsTab(ActionEvent actionEvent) throws IOException {
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

    public void setTabProperties() {
        nowDatetime.setText(Local.getNowDateTime());

        if (ControllerTabState.getState() == "isDailyTab") {
            tabNameLabel.setText("Daily Appointments");
            accentLine.setStroke(Paint.valueOf("#8a2be2"));
            addNewEntry.setText("Add Appointment");
            entryScreen = "/view/AppointmentEntryScreen.fxml";
            titleOfEntryScreen = "New Appointment Entry";

        }
        else if (ControllerTabState.getState() == "isWeeklyTab") {
            tabNameLabel.setText("Weekly Appointments");
            accentLine.setStroke(Paint.valueOf("#3cb371"));
            addNewEntry.setText("Add Appointment");
            entryScreen = "/view/AppointmentEntryScreen.fxml";
            titleOfEntryScreen = "New Appointment Entry";
        }
        else if (ControllerTabState.getState() == "isMonthlyTab") {
            tabNameLabel.setText("Monthly Appointments");
            accentLine.setStroke(Paint.valueOf("#dc143c"));
            addNewEntry.setText("Add Appointment");
            entryScreen = "/view/AppointmentEntryScreen.fxml";
            titleOfEntryScreen = "New Appointment Entry";
        }
        else if (ControllerTabState.getState() == "isAllTab") {
            tabNameLabel.setText("All Appointments");
            accentLine.setStroke(Paint.valueOf("CORAL"));
            addNewEntry.setText("Add Appointment");
            entryScreen = "/view/AppointmentEntryScreen.fxml";
            titleOfEntryScreen = "New Appointment Entry";
        }
        else if (ControllerTabState.getState() == "isCustomersTab"){
            tabNameLabel.setText("Customers");
            accentLine.setStroke(Paint.valueOf("#00bfff"));
            addNewEntry.setText("Add Customer");
            entryScreen = "/view/CustomerEntryScreen.fxml";
            titleOfEntryScreen = "New Customer Entry";
        }
        else if (ControllerTabState.getState() == "isReportsTab"){
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
        setTabProperties();
        localLocation.setText(Local.getLocation());
    }
}
