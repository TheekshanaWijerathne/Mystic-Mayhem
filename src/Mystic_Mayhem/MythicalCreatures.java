package Mystic_Mayhem;

import java.io.Serializable;

public class MythicalCreatures extends Character implements Serializable {

//    String shoot  = " ";

    public MythicalCreatures(int n) {
        super("MythicalCreatures", Shop.championDetails[4][n]);
    }


}
