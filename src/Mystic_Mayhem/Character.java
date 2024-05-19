package Mystic_Mayhem;

import java.io.Serializable;

public abstract class Character implements Serializable {
    private String name;
    private String category;// Player catagery(Archer,knight,....)
    private float price;
    private float attack;
    private int defence;
    private float health;
    private int speed;

    private float  fullHealth;
    private int originalDefence;
    private int originalSpeed;

    private Equipment armour = null;
    private Equipment artifact = null;
    private String cast;// Charactor's ground

    Character(String category, Object[] data){
        this.category = category;
        this.name = (String)data[0];
        this.price = (int)data[1];
        this.attack = (int)data[2];
        this.defence = (int)data[3];
        this.health = (int)data[4];
        this.speed = (int)data[5];
        this.cast = (String)data[6];
        this.fullHealth = health;
        this.originalDefence = defence;
        this.originalSpeed = speed;
    }






    public  void attack(String battleground , Character opponentChar) {
        if(battleground.equals("Hillcrest") && cast.equals("Highlander")){
            opponentChar.setHealth(opponentChar.getHealth()-attack);
            System.out.printf("%s attacked %s!");
        }
    };



    public void groundeffect(String Battleground) {
        switch (Battleground) {
            case "Hillcrest":
                if (cast.equals("Highlander")) {
                    attack++;
                    defence++;
                }
                else if (cast.equals("Marshlander") || cast.equals("Sunchildren")) speed--;
                break;
            case "Marshland":
                if (cast.equals("Marshlander")) defence += 2;
                else if(cast.equals("Sunchlidren")) attack--;
                else if(cast.equals("Mystic")) speed--;
                break;
            case "Desert":
                if(cast.equals("Marshlander")) health--;
                else if(cast.equals("Sunchildren")) attack++;
                break;
            case "Arcane":
                if(cast.equals("Mystic")) attack += 2;
                else if(cast.equals("Highlander" ) || cast.equals("Marshlander")){
                    speed--;
                    defence--;
                }
                break;
        }
    }


    public float getprice() {
        return price;
    }
    public int getSpeed() {
        return speed;
    }
    public int getDefence() {
        return defence;
    }
    public float getHealth() {
        return health;
    }
    public String getCast() {
        return cast;
    }
    public float getAttack() {
        return attack;
    }
    public String getName() {
        return name;
    }
    public float getFullHealth() {
        return fullHealth;
    }
    public int getOriginalDefence() {
        return originalDefence;
    }
    public int getOriginalSpeed() {return originalSpeed;}
    public String getCategory() {
        return category;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public void setHealth(float health) {
        this.health = health;
    }
    public void setDefence(int defence) {
        this.defence = defence;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public boolean HaveArmour() {
        if (armour == null) {
            return false;
        }
        return true;
    }
    public boolean HaveArtifact() {
        if (artifact == null) {
            return false;
        }
        return true;
    }
    public void SellArmour(){
        armour = null;
    }
    public void SellArtifact(){
        artifact = null;
    }

    public void setArmour(Equipment equipment){
        this.armour = equipment;
        StatusUp(equipment);
    }

    public void setArtifact(Equipment equipment){
        this.artifact = equipment;
        StatusUp(equipment);
    }
    private void StatusUp(Equipment equipment){
        this.price += equipment.getPrice();
        this.attack += equipment.getAttack();
        this.defence += equipment.getDefence();
        this.health += equipment.getHealth();
        this.speed += equipment.getSpeed();
        this.fullHealth = health;
        this.originalDefence = defence;
        this.originalSpeed =speed;
    }

    public Equipment getArmour() {
        return armour;
    }

    public Equipment getArtifact() {
        return artifact;
    }

    public String ArmourName(){
        if(armour == null){
            return "None";
        }
        return armour.getName();
    }

    public String ArtifactName(){
        if(artifact == null){
            return "None";
        }
        return artifact.getName();
    }
}

