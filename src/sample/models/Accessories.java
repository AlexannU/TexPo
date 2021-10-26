package sample.models;

public class Accessories {
    private int Id;
    private String name;
    private int type;
    private int cost;
    private String characteristic;

    public static final int BODY_AND_BLOCK = 1;
    public static final int CD_ROM = 2;
    public static final int CP = 3;
    public static final int GRAPHIC_CARD = 4;
    public static final int HDD = 5;
    public static final int MOTHER_BOARD = 6;
    public static final int SOUND_CARD = 7;


    public Accessories(int id, String name, Integer cost, String characteristic,int type) {
        Id = id;
        this.name = name;
        this.cost = cost;
        this.characteristic = characteristic;
        this.type = type;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }
}
