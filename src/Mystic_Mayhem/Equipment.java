package Mystic_Mayhem;

import java.io.Serializable;

public class Equipment implements Serializable {
    private String catogary;
    public String name;
    public int price;
    public int attack;
    public int defence;
    public int health;
    public int speed;

    Equipment(String Catogary){
        this.catogary = Catogary;
    }

    public void setAll(Object[] objects) {
        this.name = (String) objects[0];
        this.price = (int) objects[1];
        this.attack = (int) objects[2];
        this.defence = (int) objects[3];
        this.health = (int) objects[4];
        this.speed = (int) objects[5];
    }

    public float getPrice() {
        return price;
    }

    public float getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public float getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }
    public String getName() {
        return name;
    }
}
