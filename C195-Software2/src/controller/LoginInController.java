package controller;

import dao.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.User;
import utilities.ControllerTabState;
import utilities.CurrentUser;
import utilities.Local;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginInController implements Initializable {
    public Label nowDatetime;
    public TextField usernameTextbox;
    public PasswordField passwordTextbox;
    public Button signIn;
    public ToggleButton languageEN;
    public ToggleButton languageFR;
    public ToggleGroup language;
    public Label welcomeMessage;
    public Label username;
    public Label password;
    public Label languageLabel;
    public Label localLocation;
    public Label loginErrorMessage;

    public void onSignIn(ActionEvent actionEvent) throws IOException {
        if (!usernameTextbox.getText().isBlank() && !passwordTextbox.getText().isBlank()){
            if (authenticateUser()) {
                Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
                Scene scene = new Scene(root);
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setTitle("Appointment Scheduler");
                window.setScene(scene);
                window.show();
            }
        }
    }

    public void onEnter(ActionEvent actionEvent) throws IOException {
        System.out.println("Enter Pressed");
        if (!usernameTextbox.getText().isBlank() && !passwordTextbox.getText().isBlank()){
            if (authenticateUser()) {
                Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
                Scene scene = new Scene(root);
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setTitle("Appointment Scheduler");
                window.setScene(scene);
                window.show();
            }
        }
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
    public void logLoginActivity(String results) {
        try {
            String loginTime = Local.getNowDateTime();
            String username = usernameTextbox.getText();
            String password = passwordTextbox.getText();
            String loginActivity = "Time: " + loginTime + "\n" + "Username: " + username + "\n" + "Password: " + password + "\n";
            BufferedWriter buffWriter = new BufferedWriter(new FileWriter("./" + "login_activity.txt.",true));
            buffWriter.write(loginActivity+ results + "\n");
            buffWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (Exception e) {
            System.out.println("An error occurred.\n" + e.getMessage());
        }
    }

    public boolean authenticateUser() {
        boolean authorize = false;
        String username = usernameTextbox.getText();
        String password = passwordTextbox.getText();
        User currentUser = UserDAO.getUser(username);
        if (null != currentUser) {
            if (password.equals(currentUser.getPassword())) {
                CurrentUser.setCurrentUserID(currentUser.getUserId());
                logLoginActivity( "Successfully logged in.");
                return true;
            }
        }
        logLoginActivity("Failed login attempt.");
        loginErrorMessage.setText("Incorrect username or password");
        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized.");
        ResourceBundle rb = ResourceBundle.getBundle("assets/languages/rb");
        if (Local.getLanguage() == "fr") {
            setFrenchProperties();
        }
        nowDatetime.setText(Local.getNowDateTime());
        localLocation.setText(Local.getLocation());
        ControllerTabState.setState("isAllTab");

    }
}
