package sample;

import sample.models.Accessories;
import sample.models.Client;
import sample.models.Order;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static Connection dbConnection;
    private static String username = "postgres";
    private static String password = "root";
    private static String dbURI = "jdbc:postgresql://localhost:5432/trpo";

    public Database(){
    }

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        dbConnection = DriverManager.getConnection(dbURI, username, password);
        return dbConnection;
    }
    public int addClient(Client client){
        String insert = "insert into \"client\"" +
                "(fio,phone_number,email) " +
                "values(?,?,?)";
        String selectId = "SELECT id FROM \"client\"\n" +
                "ORDER BY \"id\" DESC\n" +
                "LIMIT 1";


        try {

            PreparedStatement prSt = this.getDbConnection().prepareStatement(insert);
            prSt.setString(1,client.getFio());
            prSt.setString(2,client.getPhoneNumber());
            prSt.setString(3,client.getEmail());
            prSt.executeUpdate();
            ResultSet resultSet =getResultSetForExecuteQuery(selectId);
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException | ClassNotFoundException var5) {
            var5.printStackTrace();
            return 0;
        }
    }
    public ArrayList<Client> getClients() throws SQLException {
        String select =  "SELECT * FROM client";
        ResultSet resultSet =getResultSetForExecuteQuery(select);
        ArrayList<Client> clients = new ArrayList<>();
        try{
            while (resultSet.next()){
                Client client = new Client(resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4));
                client.setId(resultSet.getInt(1));
                clients.add(client);
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }
        return clients;
    }
    public ArrayList<Accessories> getAccessories(Integer type) throws SQLException {
        String select =  "SELECT * FROM accessories WHERE type = '"+ type.toString() +"'";
        ResultSet resultSet =getResultSetForExecuteQuery(select);
        ArrayList<Accessories> accessories = new ArrayList<>();
        try{
            while (resultSet.next()){
                Accessories item = new Accessories(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getInt(5));
                accessories.add(item);
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }
        return accessories;
    }
    public void updateOrderStatus(int orderId){
        String updateStatus = "UPDATE \"order\" SET status = true WHERE id = " + orderId;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(updateStatus);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public ArrayList<Order> getOrders(Boolean isComplete) throws SQLException {
        String select = "SELECT * FROM \"order\" WHERE status = '" + isComplete + "'";
        ResultSet resultSet =getResultSetForExecuteQuery(select);
        ArrayList<Order> orders = new ArrayList<>();
        try{
            while (resultSet.next()){
                Order item = new Order(
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4),
                        resultSet.getLong(5),
                        resultSet.getLong(6),
                        resultSet.getBoolean(7));
                item.setId(resultSet.getInt(1));
                orders.add(item);
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }
        return orders;

    }
    public void addOrder(Order order,ArrayList<Accessories> accessories){
        ResultSet resultSet = null;

        String orderInsert = "INSERT INTO public.\"order\" " +
                "(client_id,manager_id,cost,reg_date,compl_date,status) VALUES (?,?,?,?,?,?)";
        String lastOrderIdSelect = "SELECT id FROM \"order\"\n" +
                "ORDER BY \"id\" DESC\n" +
                "LIMIT 1";

        try {

            PreparedStatement prSt = this.getDbConnection().prepareStatement(orderInsert);
            prSt.setInt(1,order.getClient_id());
            prSt.setInt(2,order.getManager_id());
            prSt.setInt(3, order.getCost());
            prSt.setLong(4, order.getReg_date());
            prSt.setLong(5,order.getCompl_date());
            prSt.setBoolean(6,order.isStatus());
            prSt.executeUpdate();

            PreparedStatement prStForId = this.getDbConnection().prepareStatement(lastOrderIdSelect);
            resultSet = prStForId.executeQuery();
            int orderId = 0;
            while (resultSet.next()) {
                orderId = resultSet.getInt(1);
            }

            for (Accessories accessory : accessories) {
                String orderToAccessoriesInsert = "INSERT INTO \"order_accessories\" " +
                        "VALUES (?,?)";
                PreparedStatement prStForOrderToAccessories = this.getDbConnection().prepareStatement(orderToAccessoriesInsert);
                prStForOrderToAccessories.setInt(1, orderId);
                prStForOrderToAccessories.setInt(2, accessory.getId());
                prStForOrderToAccessories.executeUpdate();
            }

        } catch (SQLException var5) {
            var5.printStackTrace();
        } catch (ClassNotFoundException var6) {
            var6.printStackTrace();
        }
    }


    public ResultSet getResultSetForExecuteQuery(String SQLRequest) {
        ResultSet resultSet = null;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(SQLRequest);
            resultSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }


}
