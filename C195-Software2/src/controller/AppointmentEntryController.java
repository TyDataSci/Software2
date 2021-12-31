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

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;
import utilities.CurrentUser;
import utilities.Local;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.lang.Math.abs;

public class AppointmentEntryController implements Initializable {

    public Button save;
    public Button cancel;
    public Label localLocation;
    public Label nowDatetime;
    public Label dateAvailable;
    public Label userID;
    private static Appointment selectedAppointment;
    public ComboBox<Contact> contactComboBox;
    public ComboBox<Customer> customerComboBox;
    public TextField titleTextBox;
    public TextField descriptionTextBox;
    public ComboBox<Country> countryComboBox;
    public ComboBox<Division>  divisionComboBox;
    public DatePicker startDatePicker;
    public DatePicker endDatePicker;
    public ComboBox<String> startHourComboBox;
    public ComboBox<String>  endHourComboBox;
    public ComboBox<String>  startMinuteComboBox;
    public ComboBox<String>  endMinuteComboBox;
    public ComboBox<String>  startAmPmComboBox;
    public ComboBox<String>  endAmPmComboBox;
    public TextField  appointmentIdTextBox;
    public TextField typeTextBox;
    public TableView availabilityTable;
    public TableColumn startColumn;
    public TableColumn endColumn;
    public TableColumn statusColumn;
    public Label errorMessage;
    ObservableList<String> allHours = FXCollections.observableArrayList("01","02","03","04","05","06","07","08","09","10","11","12");
    ObservableList<String> allAMHours = FXCollections.observableArrayList("01","02","03","04","05","06","07","08","09","10","11","00");
    ObservableList<String> allMinutes = FXCollections.observableArrayList("00","15","30","45");
    ObservableList<String> AMPMList = FXCollections.observableArrayList("AM","PM");

    public void OnSave(ActionEvent actionEvent) throws IOException {

        if(validateSave()) {
            setAppointment();
            Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setTitle("Appointment Scheduler");
            window.setScene(scene);
            window.show();
        }

    }

    public void onCancel(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Appointment Scheduler");
        window.setScene(scene);
        window.show();
    }
    public boolean withinBusinessHours() {
        int selectedStartHour = Integer.parseInt(AMPMHourConverter(startHourComboBox,startAmPmComboBox));
        int selectedStartMinute = Integer.parseInt(startMinuteComboBox.getValue());
        LocalTime selectedStartTime = LocalTime.of(selectedStartHour,selectedStartMinute);
        int selectedEndHour = Integer.parseInt(AMPMHourConverter(endHourComboBox,endAmPmComboBox));
        int selectedEndMinute = Integer.parseInt(endMinuteComboBox.getValue());
        LocalTime selectedEndTime = LocalTime.of(selectedEndHour,selectedEndMinute);
        LocalTime beginBusinessTime = LocalTime.of(8,0);
        LocalTime closeBusinessTime = LocalTime.of(22,0);
        if (startDatePicker.getValue().toString().equals(endDatePicker.getValue().toString())){
            if(selectedStartTime.isAfter(beginBusinessTime) || selectedStartTime == beginBusinessTime) {
                if (selectedEndTime.isBefore(closeBusinessTime) || selectedEndTime == closeBusinessTime)
                    return true;
            }
        }
        errorMessage.setText("Appointment is outside of business hours.");
        return false;
    }

    public boolean appointmentStartBeforeEnd() {
        int selectedStartHour = Integer.parseInt(AMPMHourConverter(startHourComboBox,startAmPmComboBox));
        int selectedStartMinute = Integer.parseInt(startMinuteComboBox.getValue());
        LocalTime selectedStartTime = LocalTime.of(selectedStartHour,selectedStartMinute);
        int selectedEndHour = Integer.parseInt(AMPMHourConverter(endHourComboBox,endAmPmComboBox));
        int selectedEndMinute = Integer.parseInt(endMinuteComboBox.getValue());
        LocalTime selectedEndTime = LocalTime.of(selectedEndHour,selectedEndMinute);
       if(selectedStartTime.isBefore(selectedEndTime)) {
           return true;
       }
        errorMessage.setText("Appointment Start Time must come before End Time.");
       return false;
    }

    public boolean availabilityCheck() {
        if (!appointmentStartBeforeEnd())
            return false;
        if (!withinBusinessHours())
            return false;
        int selectedStartHour = Integer.parseInt(AMPMHourConverter(startHourComboBox,startAmPmComboBox));
        int selectedStartMinute = Integer.parseInt(startMinuteComboBox.getValue());
        LocalTime selectedStartTime = LocalTime.of(selectedStartHour,selectedStartMinute);
        int selectedEndHour = Integer.parseInt(AMPMHourConverter(endHourComboBox,endAmPmComboBox));
        int selectedEndMinute = Integer.parseInt(endMinuteComboBox.getValue());
        LocalTime selectedEndTime = LocalTime.of(selectedEndHour,selectedEndMinute);
        String[]dateArray = startDatePicker.getValue().toString().split("-");
        int day = Integer.parseInt(dateArray[2]);
        int month = Integer.parseInt(dateArray[1]);
        int year = Integer.parseInt(dateArray[0]);
        ObservableList<Appointment> bookedTimes = AppointmentDAO.getAppointmentsBySelectDate(day,month,year);
        String alreadyBookedMessage = "Time is booked. Choose a new time.";
        for (Appointment bookedTime: bookedTimes){
            if (!String.valueOf(bookedTime.getAppointmentId()).equals(appointmentIdTextBox.getText())) {
                String[] startArray = bookedTime.getStart().split(" ");
                String[] startTimeArray = startArray[1].split(":");
                int startHour = Integer.parseInt(startTimeArray[0]);
                int startMinute = Integer.parseInt(startTimeArray[1]);
                LocalTime startTime = LocalTime.of(startHour,startMinute);
                String[] endArray = bookedTime.getEnd().split(" ");
                String[] endTimeArray = endArray[1].split(":");
                int endHour = Integer.parseInt(endTimeArray[0]);
                int endMinute = Integer.parseInt(endTimeArray[1]);
                LocalTime endTime = LocalTime.of(endHour,endMinute);
                if (selectedStartTime.isBefore(startTime) && selectedEndTime.isAfter(startTime)) {
                    errorMessage.setText(alreadyBookedMessage);
                    return false;
                }
                else if (selectedStartTime.isBefore(endTime) && selectedEndTime.isAfter(endTime)) {
                    errorMessage.setText(alreadyBookedMessage);
                    return false;
                }
                else if (selectedStartTime == startTime && selectedEndTime.isAfter(startTime)) {
                    errorMessage.setText(alreadyBookedMessage);
                    return false;
                }
                else if (selectedStartTime.isBefore(endTime) && selectedEndTime == endTime) {
                    errorMessage.setText(alreadyBookedMessage);
                    return false;
                }

            }
        }
        return true;
    }

    public boolean validateSave(){
        boolean valid = false;
        String style = "-fx-border-color: red;";
        String normalTextBox = "-fx-background-radius: 5;";
        String normal = "";
        HashMap<TextField,Boolean> textBoxMap = new HashMap<>();
        textBoxMap.put(titleTextBox,true);
        textBoxMap.put(typeTextBox,true);
        textBoxMap.put(descriptionTextBox,true);
        Iterator textIterator = textBoxMap.entrySet().iterator();
        while (textIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry) textIterator.next();
            TextField textBox = (TextField) mapElement.getKey();
            if (textBox.getText().isBlank()){
                mapElement.setValue(false);
                textBox.setStyle(style);
            }
            else
                textBox.setStyle(normalTextBox);

           // System.out.println(textBox.getText() + " " + mapElement.getValue());
        }

        HashMap<ComboBox,Boolean> comboBoxMap = new HashMap<>();
        comboBoxMap.put(contactComboBox,true);
        comboBoxMap.put(customerComboBox,true);
        comboBoxMap.put(countryComboBox,true);
        comboBoxMap.put(divisionComboBox,true);
        comboBoxMap.put(startHourComboBox,true);
        comboBoxMap.put(startMinuteComboBox,true);
        comboBoxMap.put(startAmPmComboBox,true);
        comboBoxMap.put(endHourComboBox,true);
        comboBoxMap.put(endMinuteComboBox,true);
        comboBoxMap.put(endAmPmComboBox,true);
        Iterator comboIterator = comboBoxMap.entrySet().iterator();
        while (comboIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry) comboIterator.next();
            ComboBox comboBox = (ComboBox) mapElement.getKey();
            if (null == comboBox.getValue()){
                mapElement.setValue(false);
                comboBox.setStyle(style);
            }
            else
                comboBox.setStyle(normal);

          //  System.out.println(comboBox.getValue() + " " + mapElement.getValue());
        }

        HashMap<DatePicker,Boolean> datePickerMap = new HashMap<>();
        datePickerMap.put(startDatePicker,true);
        datePickerMap.put(endDatePicker,true);
        Iterator pickerIterator = datePickerMap.entrySet().iterator();
        while (pickerIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry) pickerIterator.next();
            DatePicker datePicker = (DatePicker) mapElement.getKey();
            if (null  == datePicker.getValue()){
                mapElement.setValue(false);
                datePicker.setStyle(style);
            }
            else
                datePicker.setStyle(normal);
         //   System.out.println(datePicker.getValue()+ " " + mapElement.getValue());
        }


            if(!datePickerMap.containsValue(false) && !textBoxMap.containsValue(false) && !comboBoxMap.containsValue(false)) {
                if (availabilityCheck())
                    valid = true;
            }
            else {
                errorMessage.setText("*Required Fields");
            }

            return valid;
    }

    public void setAppointment(){
        Appointment appointmentToSet;
        if (null != selectedAppointment){
            System.out.println("Before Update: " + startDatePicker.getValue() + " " + AMPMHourConverter(startHourComboBox,startAmPmComboBox));
            appointmentToSet = selectedAppointment;
            appointmentToSet.setTitle(titleTextBox.getText());
            appointmentToSet.setType(typeTextBox.getText());
            appointmentToSet.setDescription(descriptionTextBox.getText());
            appointmentToSet.setLocation(divisionComboBox.getValue() + ", " + countryComboBox.getValue());
            appointmentToSet.setStart(startDatePicker.getValue() + " " + AMPMHourConverter(startHourComboBox,startAmPmComboBox)  + ":" + startMinuteComboBox.getValue());
            appointmentToSet.setEnd(endDatePicker.getValue() + " " + AMPMHourConverter(endHourComboBox,endAmPmComboBox) + ":" + endMinuteComboBox.getValue());
            appointmentToSet.setCustomerId(customerComboBox.getSelectionModel().getSelectedItem().getCustomerId());
            AppointmentDAO.updateAppointment(appointmentToSet);
            System.out.println("After Update: " + AppointmentDAO.getAppointment(selectedAppointment.getAppointmentId()).getStart());
        }
        else {
           appointmentToSet = new Appointment(
                    0,
                    titleTextBox.getText(),
                    descriptionTextBox.getText(),
                    divisionComboBox.getValue() + ", " + countryComboBox.getValue(),
                    typeTextBox.getText(),
                   null,
                  null,
                    null,
                    CurrentUser.getCurrentUser(),
                    null,
                    CurrentUser.getCurrentUser(),
                    customerComboBox.getSelectionModel().getSelectedItem().getCustomerId(),
                    CurrentUser.getCurrentUserID(),
                    contactComboBox.getSelectionModel().getSelectedItem().getContactId(),
                    customerComboBox.getSelectionModel().getSelectedItem().toString(),
                    contactComboBox.getSelectionModel().getSelectedItem().toString());
           appointmentToSet.setStart(startDatePicker.getValue() + " " + AMPMHourConverter(startHourComboBox,startAmPmComboBox) + ":" + startMinuteComboBox.getValue());
           appointmentToSet.setEnd(endDatePicker.getValue() + " " + AMPMHourConverter(endHourComboBox,endAmPmComboBox) + ":" + endMinuteComboBox.getValue());
           AppointmentDAO.addAppointment(appointmentToSet);
        }
    }

    public void onCountrySelected(ActionEvent actionEvent) {
        Country selectedCountry = countryComboBox.getSelectionModel().getSelectedItem();
        divisionComboBox.setItems(DivisionDAO.getDivisionsOfCountry(selectedCountry.getCountryId()));
        divisionComboBox.setValue(null);
    }
    public String parseDate(String time) {
        String date = null;
        String[] startddMMyyyy = time.split(" ");

        return  startddMMyyyy[0];
    }

    public String parseHour(String time) {
        String hour = null;
        String[] startHHmmArr = time.split(" ");
        //System.out.println("First split: " + startHHmmArr[1]);
        String[] startHHmm = startHHmmArr[1].split(":");
        //System.out.println("Second split: " +startHHmm[0]);
        if (allHours.contains(startHHmm[0]))
            hour = startHHmm[0];
        else {
            int value = 0;
            value = Integer.parseInt(startHHmm[0]);
            value = value - 12;
            hour = hour.valueOf(abs(value));
            if (abs(value) < 10)
                hour = "0" + hour;
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
        String[] startHHmmArr = time.split(" ");
        String[] startHHmm = startHHmmArr[1].split(":");
        if (allAMHours.contains(startHHmm[0]))
            return "AM";

        else
            return "PM";
    }
    public void onStartDate(ActionEvent actionEvent) {
        setAvailabilityTable();
    }
    public void setAvailabilityTable(){
        try {
            String[]dateArray = startDatePicker.getValue().toString().split("-");
            for (int i=0; i<dateArray.length; i++)
                System.out.println(dateArray[i]);
            int day = Integer.parseInt(dateArray[2]);
            int month = Integer.parseInt(dateArray[1]);
            int year = Integer.parseInt(dateArray[0]);
            LocalDate availableDate = LocalDate.of(year, month,day);
            DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("MMM dd");
            String availableDateString = dateTimeFormat.format(availableDate);
            dateAvailable.setText(availableDateString);
            ObservableList<Appointment> bookedTime = AppointmentDAO.getAppointmentsBySelectDate(day,month,year);
            for (Appointment appointment:bookedTime) {
                String start = appointment.getStart().split(" ")[1];
                String startHour = start.split(":")[0];
                String startMinutes = start.split(":")[1];
                appointment.setStart(startHour + ":" + startMinutes);

                String end = appointment.getEnd().split(" ")[1];
                String endHour = end.split(":")[0];
                String endMinutes = end.split(":")[1];
                appointment.setEnd(endHour + ":" + endMinutes);
            }

            availabilityTable.setItems(bookedTime);
            startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
            endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
            statusColumn.setCellValueFactory(new PropertyValueFactory<>("bookedIndicator"));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        availabilityTable.getSortOrder().add(startColumn);
    }
    public String AMPMHourConverter(ComboBox<String> hourCombo, ComboBox<String> amPM) {
        String hour;
        if (amPM.getValue() == "PM" && hourCombo.getValue() != "12") {
            int val = Integer.parseInt(hourCombo.getValue());
            val += 12;
            hour = String.valueOf(val);
        }
        else if (amPM.getValue() == "AM" && hourCombo.getValue() == "12"){
            int val = Integer.parseInt(hourCombo.getValue());
            val -= 12;
            hour = String.valueOf(val);
        }
        else
           hour = hourCombo.getValue();

        return hour;
    }

        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nowDatetime.setText(Local.getNowDateTime());
        localLocation.setText(Local.getLocation());
        userID.setText(String.valueOf(CurrentUser.getCurrentUserID()));
        selectedAppointment = MainScreenController.getSelectedAppointment();
        contactComboBox.setItems(ContactDAO.getAllContacts());
        customerComboBox.setItems(CustomerDAO.getAllCustomers());
        startHourComboBox.setItems(allHours);
        startMinuteComboBox.setItems(allMinutes);
        startAmPmComboBox.setItems(AMPMList);
        endHourComboBox.setItems(allHours);
        endMinuteComboBox.setItems(allMinutes);
        endAmPmComboBox.setItems(AMPMList);
        appointmentIdTextBox.setText("Auto-generated");
        appointmentIdTextBox.setDisable(true);
        countryComboBox.setItems(CountryDAO.getAllCountries());

        if (null != selectedAppointment) {
            contactComboBox.setValue(ContactDAO.getContact(selectedAppointment.getContactId()));
            customerComboBox.setValue(CustomerDAO.getCustomer(selectedAppointment.getCustomerId()));
            titleTextBox.setText(selectedAppointment.getTitle());
            appointmentIdTextBox.setText(String.valueOf(selectedAppointment.getAppointmentId()));
            typeTextBox.setText(selectedAppointment.getType());
            descriptionTextBox.setText(selectedAppointment.getDescription());
            Country selectedCountry = CountryDAO.countryFromLocation(selectedAppointment.getLocation());
            if (null != selectedCountry)
                countryComboBox.setValue(selectedCountry);
                divisionComboBox.setValue(null);
            selectedCountry = countryComboBox.getSelectionModel().getSelectedItem();
            if (null != selectedCountry) {
                divisionComboBox.setItems(DivisionDAO.getDivisionsOfCountry(selectedCountry.getCountryId()));
                Division selectedDivision = DivisionDAO.divisionFromLocation(selectedAppointment.getLocation(), selectedCountry.getCountryId());
                if (null != selectedDivision)
                    divisionComboBox.setValue(selectedDivision);
            }
            startDatePicker.setValue(LocalDate.parse(parseDate(selectedAppointment.getStart())));
            startHourComboBox.setValue(parseHour(selectedAppointment.getStart()));
            startMinuteComboBox.setValue(parseMinutes(selectedAppointment.getStart()));
            startAmPmComboBox.setValue(parseAMPM(selectedAppointment.getStart()));
            endDatePicker.setValue(LocalDate.parse(parseDate(selectedAppointment.getEnd())));
            endHourComboBox.setValue(parseHour(selectedAppointment.getEnd()));
            endMinuteComboBox.setValue(parseMinutes(selectedAppointment.getEnd()));
            endAmPmComboBox.setValue(parseAMPM(selectedAppointment.getEnd()));
            setAvailabilityTable();
        }
    }
}
