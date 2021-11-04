package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Database;
import sample.models.Accessories;
import sample.models.Order;

public class ClientControllerChoosing {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonMakeOrder;

    @FXML
    private ChoiceBox<String> choiceboxBB;

    @FXML
    private ChoiceBox<String> choiceboxCDROM;

    @FXML
    private ChoiceBox<String> choiceboxCP;

    @FXML
    private ChoiceBox<String> choiceboxGraphicsCard;

    @FXML
    private ChoiceBox<String> choiceboxHDD;

    @FXML
    private ChoiceBox<String> choiceboxMotherboard;

    @FXML
    private ChoiceBox<String> choiceboxSoundCard;

    @FXML
    private Label labelNameCost;

    @FXML
    private Label labelTotalCost;

    private final Database database= new Database();

    private final ArrayList<Accessories> databaseBodyAndBlock = database.getAccessories(Accessories.BODY_AND_BLOCK);
    private final ArrayList<Accessories> databaseCdrom = database.getAccessories(Accessories.CD_ROM);
    private final ArrayList<Accessories> databaseCp = database.getAccessories(Accessories.CP);
    private final ArrayList<Accessories> databaseGraphicsCard = database.getAccessories(Accessories.GRAPHIC_CARD);
    private final ArrayList<Accessories> databaseHdd = database.getAccessories(Accessories.HDD);
    private final ArrayList<Accessories> databaseMotherBoard = database.getAccessories(Accessories.MOTHER_BOARD);
    private final ArrayList<Accessories> databaseSoundCard = database.getAccessories(Accessories.SOUND_CARD);






    public ClientControllerChoosing() throws SQLException {
    }


    @FXML
    void makeClientOrder(ActionEvent event) {



        ArrayList<Accessories> accessoriesForDatabase = new ArrayList<>();
        accessoriesForDatabase.add(databaseBodyAndBlock.get(choiceboxBB.getSelectionModel().getSelectedIndex()));
        accessoriesForDatabase.add(databaseCdrom.get(choiceboxCDROM.getSelectionModel().getSelectedIndex()));
        accessoriesForDatabase.add(databaseCp.get(choiceboxCP.getSelectionModel().getSelectedIndex()));
        accessoriesForDatabase.add(databaseGraphicsCard.get(choiceboxGraphicsCard.getSelectionModel().getSelectedIndex()));
        accessoriesForDatabase.add(databaseHdd.get(choiceboxHDD.getSelectionModel().getSelectedIndex()));
        accessoriesForDatabase.add(databaseMotherBoard.get(choiceboxMotherboard.getSelectionModel().getSelectedIndex()));
        accessoriesForDatabase.add(databaseSoundCard.get(choiceboxSoundCard.getSelectionModel().getSelectedIndex()));
        Order order = new Order(1,1,5600,1635294633000L, 1635294633000L,false);
        //database.addOrder(order,accessoriesForDatabase);

        buttonMakeOrder.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/Samples/clientPageOrdering.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }



        ClientControllerOrdering cco = loader.getController();
        cco.sendOrderList(accessoriesForDatabase,Integer.parseInt(labelTotalCost.getText()));


        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();


    }
    
    @FXML
    void initialize() {
        addItemsAndSetOnAction(choiceboxBB,databaseBodyAndBlock);
        addItemsAndSetOnAction(choiceboxCDROM,databaseCdrom);
        addItemsAndSetOnAction(choiceboxCP,databaseCp);
        addItemsAndSetOnAction(choiceboxGraphicsCard,databaseGraphicsCard);
        addItemsAndSetOnAction(choiceboxHDD,databaseHdd);
        addItemsAndSetOnAction(choiceboxMotherboard,databaseMotherBoard);
        addItemsAndSetOnAction(choiceboxSoundCard,databaseSoundCard);

    }
    private int [] temp= {0,0,0,0,0,0,0};
    private void getTotalCost(javafx.event.ActionEvent actionEvent) {

        int sum=0;


        //System.out.println(databaseBodyAndBlock.get(choiceboxBB.getSelectionModel().getSelectedIndex()).getCost());
        switch (((ChoiceBox) actionEvent.getSource()).getId()){
            case "choiceboxBB":
                temp[0]=databaseBodyAndBlock.get(choiceboxBB.getSelectionModel().getSelectedIndex()).getCost();
                break;
            case "choiceboxCDROM":
                temp[1]=databaseCdrom.get(choiceboxCDROM.getSelectionModel().getSelectedIndex()).getCost();
                break;
            case "choiceboxCP":
                temp[2]=databaseCp.get(choiceboxCP.getSelectionModel().getSelectedIndex()).getCost();
                break;
            case "choiceboxGraphicsCard":
                temp[3]=databaseGraphicsCard.get(choiceboxGraphicsCard.getSelectionModel().getSelectedIndex()).getCost();
                break;
            case "choiceboxHDD":
                temp[4]=databaseHdd.get(choiceboxHDD.getSelectionModel().getSelectedIndex()).getCost();
                break;
            case "choiceboxMotherboard":
                temp[5]=databaseMotherBoard.get(choiceboxMotherboard.getSelectionModel().getSelectedIndex()).getCost();
                break;
            case "choiceboxSoundCard":

                temp[6]=databaseSoundCard.get(choiceboxSoundCard.getSelectionModel().getSelectedIndex()).getCost();
                break;
            default:
                break;
        }



        for (int i = 0; i<6;i++){
            sum+=temp[i];
        }

        labelTotalCost.setText(Integer.toString(sum));
    }
    private ArrayList<String> getListOfAccessoriesName(ArrayList<Accessories> accessories){
        ArrayList<String> accessoriesNames = new ArrayList<>();
        for (Accessories accessory : accessories) {
            accessoriesNames.add(accessory.getName());
        }
        return accessoriesNames;
    }
    private void addItemsAndSetOnAction(ChoiceBox<String> choiceBox,ArrayList<Accessories> accessories){
        choiceBox.getItems().addAll(getListOfAccessoriesName(accessories));
        choiceBox.setOnAction(this::getTotalCost);
    }



}
