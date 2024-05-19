package Mystic_Mayhem;

import java.io.Serializable;

public class Archer extends Character implements Serializable {

//    String shoot  = " ";

    public Archer(int n) {
        super("Archer", Shop.championDetails[0][n]);
    }

}
