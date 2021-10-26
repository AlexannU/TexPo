package sample.models;

public class Order {
    private int id;
    private int client_id;
    private int manager_id;
    private int cost;
    private long reg_date;
    private long compl_date;
    private boolean status;

    public Order(int id, int client_id, int manager_id, int cost, long reg_date, long compl_date, boolean status) {
        this.id = id;
        this.client_id = client_id;
        this.manager_id = manager_id;
        this.cost = cost;
        this.reg_date = reg_date;
        this.compl_date = compl_date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public long getReg_date() {
        return reg_date;
    }

    public void setReg_date(long reg_date) {
        this.reg_date = reg_date;
    }

    public long getCompl_date() {
        return compl_date;
    }

    public void setCompl_date(long compl_date) {
        this.compl_date = compl_date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
