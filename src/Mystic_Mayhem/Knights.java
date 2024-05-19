package Mystic_Mayhem;

import java.io.Serializable;

public class Knights extends Character implements Serializable {

    public Knights(int n) {
        super("Knights", Shop.championDetails[1][n]);
    }

}
