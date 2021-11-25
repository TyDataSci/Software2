package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utilities.Local;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentEntryController implements Initializable {


    public Button save;
    public Button cancel;
    public Label localLocation;
    public Label nowDatetime;
    public TextField customerID;
    public Label dateAvailable;
    public Label userID;
    public ComboBox typeComboBox;
    public ComboBox contactNameComboBox;


    public void OnSave(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Appointment Scheduler");
        window.setScene(scene);
        window.show();
    }

    public void onCancel(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Appointment Scheduler");
        window.setScene(scene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nowDatetime.setText(Local.getNowDateTime());
        localLocation.setText(Local.getLocation());
    }
}
