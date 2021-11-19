package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import util.Local;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginInController implements Initializable {
    public Label nowDatetime;
    public TextField usernameTextbox;
    public TextField passwordTextbox;
    public Button signIn;
    public ToggleButton languageEN;
    public ToggleButton languageFR;
    public ToggleGroup language;
    public Label welcomeMessage;
    public Label username;
    public Label password;
    public Label languageLabel;
    public Label localLocation;

    public void onSignIn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Appointment Scheduler");
        window.setScene(scene);
        window.show();
    }

    public void onLanguageEN(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        window.setTitle("Appointment Scheduler");
        window.setScene(scene);
        window.show();
    }

    public void onLanguageFR(ActionEvent actionEvent) {
        setFrenchProperties();

    }

    public void setFrenchProperties() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("assets/languages/rb");
        welcomeMessage.setText(resourceBundle.getString("welcome"));
        username.setText(resourceBundle.getString("username"));
        usernameTextbox.setPromptText(resourceBundle.getString("usernamePrompt"));
        password.setText(resourceBundle.getString("password"));
        passwordTextbox.setPromptText(resourceBundle.getString("passwordPrompt"));
        signIn.setText(resourceBundle.getString("signInButton"));
        languageLabel.setText(resourceBundle.getString("language"));
        languageFR.setStyle("-fx-background-radius: 50;-fx-background-color:#F4A460");
        languageEN.setStyle("-fx-background-radius: 50;-fx-background-color:#FFEFD5");
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized.");
        ResourceBundle rb = ResourceBundle.getBundle("assets/languages/rb");
        if (Local.getLanguage() == "fr") {
            setFrenchProperties();
        }
        nowDatetime.setText(Local.getNowDateTime());
        localLocation.setText(Local.getLocation());


    }

}
