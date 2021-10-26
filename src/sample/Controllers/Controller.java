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
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonAdmin;

    @FXML
    private Button buttonBuyer;



    @FXML
    void initialize()  { // переход на страницу Админа
        buttonAdmin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                buttonAdmin.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/Samples/adminPage.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
            }
        });
        buttonBuyer.setOnAction(new EventHandler<ActionEvent>() { // переход на страницу Покупателя
            @Override
            public void handle(ActionEvent actionEvent) {
                buttonBuyer.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/Samples/clientPageChoosingItems.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }
        });


    }

}


