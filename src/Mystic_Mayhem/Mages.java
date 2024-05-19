package Mystic_Mayhem;

import java.io.Serializable;

public class Mages extends Character implements Serializable {

//    String shoot  = " ";

    public Mages(int n) {
        super("Mages", Shop.championDetails[2][n]);
    }
}
