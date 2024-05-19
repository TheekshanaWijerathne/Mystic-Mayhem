package Mystic_Mayhem;

import java.io.Serializable;

public class Healers extends Character implements Serializable {
    public Healers(int n) {
        super("Healer", Shop.championDetails[3][n]);
    }



}


