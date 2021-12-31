package controller;

import dao.CountryDAO;
import dao.CustomerDAO;
import dao.DivisionDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.Division;
import utilities.CurrentUser;
import utilities.Local;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

public class CustomerEntryController implements Initializable {
    public Button save;
    public TextField streetNameTextBox;
    public TextField cityTextBox;
    public ComboBox<Country> countryComboBox;
    public Label divisionLabel;
    public ComboBox<Division> divisionComboBox;
    public Button cancel;
    public TextField zipCodeTextBox;
    public TextField phoneNumberTextBox;
    public Label nowDatetime;
    public Label localLocation;
    public TextField customerNameTextBox;
    public Label errorMessage;
    public Label userID;
    private Customer selectedCustomer;
    public TextField customerID;

    public void onSave(ActionEvent actionEvent) throws IOException {

        if(validateSave()) {
            setCustomer();
            Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerScreen.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setTitle("Appointment Scheduler");
            window.setScene(scene);
            window.show();
        }
    }

    public void onCancel(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerScreen.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Appointment Scheduler");
        window.setScene(scene);
        window.show();
    }

    public void onCountrySelected(ActionEvent actionEvent) {
        Country selectedCountry = countryComboBox.getSelectionModel().getSelectedItem();
        divisionComboBox.setItems(DivisionDAO.getDivisionsOfCountry(selectedCountry.getCountryId()));
        divisionComboBox.setValue(null);
    }

    public boolean validateSave(){
        boolean valid = false;
        String style = "-fx-border-color: red;";
        String normalTextBox = "-fx-background-radius: 5;";
        String normal = "";
        HashMap<TextField,Boolean> textBoxMap = new HashMap<>();
        textBoxMap.put(customerNameTextBox,true);
        textBoxMap.put(streetNameTextBox,true);
        textBoxMap.put(zipCodeTextBox,true);
        textBoxMap.put(phoneNumberTextBox,true);
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
        }

        HashMap<ComboBox,Boolean> comboBoxMap = new HashMap<>();
        comboBoxMap.put(countryComboBox,true);
        comboBoxMap.put(divisionComboBox,true);
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
        }

        if(!textBoxMap.containsValue(false) && !comboBoxMap.containsValue(false))
            valid = true;
        else
            errorMessage.setText("*Required Fields");

        return valid;
    }


    public void setCustomer() {
        if (null != selectedCustomer) {
            selectedCustomer.setCustomerName(customerNameTextBox.getText());
            selectedCustomer.setAddress(streetNameTextBox.getText());
            selectedCustomer.setPostalCode(zipCodeTextBox.getText());
            selectedCustomer.setDivisionId(divisionComboBox.getValue().getDivisionId());
            selectedCustomer.setPhone(phoneNumberTextBox.getText());
            CustomerDAO.updateCustomer(selectedCustomer);
        }
        else {
            Customer newCustomer = new Customer(0,
                    customerNameTextBox.getText(),
                    streetNameTextBox.getText(),
                    zipCodeTextBox.getText(),
                    phoneNumberTextBox.getText(),
                    null,
                    String.valueOf(CurrentUser.getCurrentUserID()),
                    null,
                    String.valueOf(CurrentUser.getCurrentUserID()),
                    divisionComboBox.getValue().getDivisionId(),
                    divisionComboBox.getValue().getDivisionName()
                    );
            CustomerDAO.addCustomer(newCustomer);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(selectedCustomer);
        userID.setText(String.valueOf(CurrentUser.getCurrentUserID()));
        nowDatetime.setText(Local.getNowDateTime());
        localLocation.setText(Local.getLocation());
        countryComboBox.setItems(CountryDAO.getAllCountries());
        selectedCustomer = CustomerScreenController.getSelectedCustomer();
        if (null != selectedCustomer){
            System.out.println(selectedCustomer);
            customerNameTextBox.setText(selectedCustomer.getCustomerName());
            customerID.setText(String.valueOf(selectedCustomer.getCustomerId()));
            streetNameTextBox.setText(selectedCustomer.getAddress());
            zipCodeTextBox.setText(selectedCustomer.getPostalCode());
            Division selectDivision = DivisionDAO.getDivision(selectedCustomer.getDivisionId());
            Country selectCountry = CountryDAO.getCountry(selectDivision.getCountryId());
            countryComboBox.setValue(selectCountry);
            divisionComboBox.setItems(DivisionDAO.getDivisionsOfCountry(selectCountry.getCountryId()));
            divisionComboBox.setValue(selectDivision);
            phoneNumberTextBox.setText(selectedCustomer.getPhone());
        }
    }
}
