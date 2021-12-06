package controller;

import dao.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.stage.Stage;
import model.Appointment;
import model.Country;
import model.Division;
import utilities.Local;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import static java.lang.Math.abs;

public class AppointmentEntryController implements Initializable {


    public Button save;
    public Button cancel;
    public Label localLocation;
    public Label nowDatetime;
    public Label dateAvailable;
    public Label userID;
    private static Appointment selectedAppointment;
    public ComboBox contactComboBox;
    public ComboBox customerComboBox;
    public TextField titleTextBox;
    public TextField descriptionTextBox;
    public ComboBox<Country> countryComboBox;
    public ComboBox divisionComboBox;
    public DatePicker startDatePicker;
    public DatePicker endDatePicker;
    public ComboBox startHourComboBox;
    public ComboBox endHourComboBox;
    public ComboBox startMinuteComboBox;
    public ComboBox endMinuteComboBox;
    public ComboBox startAmPmComboBox;
    public ComboBox endAmPmComboBox;
    public TextField appointmentIdTextBox;
    public TextField typeTextBox;
    ObservableList<String> allHours = FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12");
    ObservableList<String> allMinutes = FXCollections.observableArrayList("00","15","30","45");
    ObservableList<String> AMPMList = FXCollections.observableArrayList("AM","PM");

    public void OnSave(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Appointment Scheduler");
        window.setScene(scene);
        window.show();
    }

    public void onCancel(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Appointment Scheduler");
        window.setScene(scene);
        window.show();
    }

    public void onCountrySelected(ActionEvent actionEvent) {
        Country selectedCountry = countryComboBox.getSelectionModel().getSelectedItem();
        divisionComboBox.setItems(DivisionDAO.getDivisionsOfCountry(selectedCountry.getCountryId()));
    }
    public String parseDate(String time) {
        String date = null;
        String[] startddMMyyyy = time.split(" ");

        return  startddMMyyyy[0];
    }

    public String parseHour(String time) {
        String hour = null;
        String[] startHHmmArr = time.split(" ");
        String[] startHHmm = startHHmmArr[1].split(":");
        if (allHours.contains(startHHmm[0]))
            hour = startHHmm[0];
        else {
            int value = 0;
            value = Integer.parseInt(startHHmm[0]);
            value = value - 12;
            hour = hour.valueOf(abs(value));
        }
        return hour;
    }
    public String parseMinutes(String time) {
        String minutes = null;
        String[] startHHmmArr = time.split(" ");
        String[] startHHmm = startHHmmArr[1].split(":");
        if (allMinutes.contains(startHHmm[1])) {
            minutes = startHHmm[1];
        }
        return minutes;
    }

    public String parseAMPM(String time) {
        String amPM = null;
        String[] startHHmmArr = time.split(" ");
        String[] startHHmm = startHHmmArr[1].split(":");
        if (allHours.contains(startHHmm[0]))
            amPM = "AM";
        else {
            int value = 0;
            value = Integer.parseInt(startHHmm[0]);
            value = value - 12;
            if (value <= 12 && value >= 1){
                amPM = "PM";
            }
            else {
                amPM="AM";
            }

        }
        return amPM;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nowDatetime.setText(Local.getNowDateTime());
        localLocation.setText(Local.getLocation());
        selectedAppointment = MainScreenController.getSelectedAppointment();
        if (null != selectedAppointment) {

            contactComboBox.setItems(ContactDAO.getAllContacts());
            contactComboBox.setValue(ContactDAO.getContact(selectedAppointment.getContactId()).toString());
            customerComboBox.setItems(CustomerDAO.getAllCustomers());
            customerComboBox.setValue(CustomerDAO.getCustomer(selectedAppointment.getContactId()).toString());
            titleTextBox.setText(selectedAppointment.getTitle());
            appointmentIdTextBox.setText(String.valueOf(selectedAppointment.getAppointmentId()));
            appointmentIdTextBox.setDisable(true);
            typeTextBox.setText(selectedAppointment.getType());
            descriptionTextBox.setText(selectedAppointment.getDescription());
            countryComboBox.setItems(CountryDAO.getAllCountries());
            Country selectedCountry = CountryDAO.countryFromLocation(selectedAppointment.getLocation());
            if (null != selectedCountry)
                countryComboBox.setValue(selectedCountry);
            selectedCountry = countryComboBox.getSelectionModel().getSelectedItem();
            if (null != selectedCountry) {
                divisionComboBox.setItems(DivisionDAO.getDivisionsOfCountry(selectedCountry.getCountryId()));
                Division selectedDivision = DivisionDAO.divisionFromLocation(selectedAppointment.getLocation(), selectedCountry.getCountryId());
                if (null != selectedDivision)
                    divisionComboBox.setValue(selectedDivision.toString());
            }
            startDatePicker.setValue(LocalDate.parse(parseDate(selectedAppointment.getStart())));
            startHourComboBox.setItems(allHours);
            startHourComboBox.setValue(parseHour(selectedAppointment.getStart()));
            startMinuteComboBox.setItems(allMinutes);
            startMinuteComboBox.setValue(parseMinutes(selectedAppointment.getStart()));
            startAmPmComboBox.setItems(AMPMList);
            startAmPmComboBox.setValue(parseAMPM(selectedAppointment.getStart()));
            endDatePicker.setValue(LocalDate.parse(parseDate(selectedAppointment.getEnd())));
            endHourComboBox.setItems(allHours);
            endHourComboBox.setValue(parseHour(selectedAppointment.getEnd()));
            endMinuteComboBox.setItems(allMinutes);
            endMinuteComboBox.setValue(parseMinutes(selectedAppointment.getEnd()));
            endAmPmComboBox.setItems(AMPMList);
            endAmPmComboBox.setValue(parseAMPM(selectedAppointment.getEnd()));
        }
    }
}
