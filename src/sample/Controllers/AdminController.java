package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import sample.Database;

public class AdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonNotifyClient;

    @FXML
    private Button buttonTakeOrder;

    @FXML
    private ListView<String> listOfNotifyClient; // сюда должны грузиться уже принятые заказы, !!!какой тип даных должен быть я хз в этом листе!!!

    @FXML
    private ListView<String> listOrder; // сюда грузяться непринятые еще заказы, !!!какой тип даных должен быть я хз в этом листе!!!

    private final Database database = new Database();

    @FXML
    void acceptOrderName(MouseEvent event) /* метод должен принимать заказ, который находится на listOrder по клику на кнопку, принимается выделенный заказ */{
        int selectedId = listOrder.getSelectionModel().getSelectedIndex();
        listOfNotifyClient.getItems().add(listOrder.getItems().get(selectedId)); // передает выбранный заказ в listOfNotifyClient
        // изменяет статус заказа на принят (должно быть выражение)//
        listOrder.getItems().remove(selectedId); // удаляет выбраыннй заказ с listOrder

    }

    @FXML
    void notifyClient(MouseEvent event) /* метод должен отправлять на почту клиенту сообщение уведомления об изменении статуса заказа, который находится на listOfNotifyClient, при нажатии кнопки и выделение заказа */ {
        int selectedId = listOfNotifyClient.getSelectionModel().getSelectedIndex();
        // изменяет статус заказа на выполнен//
        listOfNotifyClient.getItems().remove(selectedId);// удаляет выбраыннй заказ с listOrder
        //database.updateOrderStatus(selectedId);
    }

    @FXML
    void initialize() {


    }

}
