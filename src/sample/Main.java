package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.models.Client;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main extends Application {
    private static Database database = new Database();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Samples/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        ArrayList<Client> clients = database.getClients();
        for (Client client : clients) {
            System.out.println(client.getId() + "\t" +
                    client.getFio() + "\t" +
                    client.getEmail() + "\t" +
                    client.getPhoneNumber());
        }
    }


    public static void main(String[] args) throws SQLException {
        launch(args);


    }
}
