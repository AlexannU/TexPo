package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
import sample.Database;
import sample.Main;
import sample.models.Accessories;
import sample.models.Client;
import sample.models.Order;

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
    private Database database = new Database();
    private ArrayList<Accessories> accessoriesOnScene = new ArrayList<>();
    private int cost;
    @FXML
    void saveClientInformation(MouseEvent event) {
        String name, surname, patronymic, phone, email;
        name = textfieldName.getText();
        surname = textfieldSurname.getText();
        patronymic = textfieldPatronymic.getText();
        phone = textfieldPhoneNumber.getText();
        email = textfieldEmail.getText();
        Client client = new Client(surname + " " +
                name + " " +
                patronymic,phone,email);
        int clientId =  database.addClient(client);
        database.addOrder(new Order(clientId,
                1,
                cost,
                new Date().getTime(),
                new Date().getTime() + 604800000L,
                false), accessoriesOnScene);

        // потом нужно закинуть полученные данные в базу

        textfieldName.setText(null);
        textfieldSurname.setText(null);
        textfieldPatronymic.setText(null);
        textfieldPhoneNumber.setText(null);
        textfieldEmail.setText(null);

    }

    @FXML
    void initialize() {
        System.out.println("Scene open");



    }
    public void sendOrderList(ArrayList<Accessories> accessories,int cost) { // вот это метод, который забирает данные из Контроллера с выбором комплектующих
        this.cost = cost;
        this.accessoriesOnScene = accessories;
        // Здесь все что угодно может быть
        //textfieldName.setText(String.valueOf(Order.get(0).getId()));// проверил, что данные успешно передались

    }

}
