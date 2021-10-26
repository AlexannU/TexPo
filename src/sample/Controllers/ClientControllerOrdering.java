package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Main;

public class ClientControllerOrdering {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonBackOnStart;

    @FXML
    private Button buttonSaveInformation;

    @FXML
    private TextField textfieldEmail;

    @FXML
    private TextField textfieldName;

    @FXML
    private TextField textfieldPatronymic;

    @FXML
    private TextField textfieldPhoneNumber;

    @FXML
    private TextField textfieldSurname;

    @FXML
    void backToStart(MouseEvent event)  {


    }

    @FXML
    void saveClientInformation(MouseEvent event) {
        String name, surname, patronymic, phone, email;
        name = textfieldName.getText();
        surname = textfieldSurname.getText();
        patronymic = textfieldPatronymic.getText();
        phone = textfieldPhoneNumber.getText();
        email = textfieldEmail.getText();
        // потом нужно закинуть полученные данные в базу
        textfieldName.setText(null);
        textfieldSurname.setText(null);
        textfieldPatronymic.setText(null);
        textfieldPhoneNumber.setText(null);
        textfieldEmail.setText(null);
    }

    @FXML
    void initialize() {



    }

}
