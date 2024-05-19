package Mystic_Mayhem;

public class Battle{
    private  String ground;
    private Player player;
    private Player opponent;
    private int attackCount = 0;
    private Character[] playerChampions;
    private Character[] opponentChampions;
    private Character[] playerSpeed;
    private Character[] opponentSpeed;


    /**
     * Initiates a battle between two players by initializing their respective champions
     * and conducting a series of attacks until one player wins or the maximum number of rounds is reached.
     *
     * @param player The player initiating the battle.
     * @param opponent The opponent player.
     */

    public Battle(Player player , Player opponent ){
        this.player = player;
        this.opponent = opponent;
        this.ground = opponent.getGround();
        playerChampions = player.getCharacters().clone();
        opponentChampions = opponent.getCharacters().clone();

        for(Character playChamp : playerChampions){
            playChamp.groundeffect(ground);
        }
        for(Character oppChamp : playerChampions){
            oppChamp.groundeffect(ground);
        }

        int p = 0;
        int o = 0;
        int round = 1;
        while (attackCount < 18){
            System.out.println();
            System.out.println("===================");
            System.out.printf("\t Round %d \n" , round);
            System.out.println("===================");
            System.out.println();
            sortToSpeed(playerChampions);
            playerSpeed = playerChampions;
            while (playerSpeed[p].getHealth() <= 0 ) {
                p++;
                if(p>4)p=0;
            }
            attackModified(playerSpeed[p],"Attack");
            attackCount++;
            sortToHealth(opponentChampions);
            if (opponentChampions[4].getHealth() <= 0){
//                System.out.println("WIN CALLED FOR THE PLAYER");
                win(player,opponent);
                break;
            }
            if(p==4)p=0;
            else p++;

            sortToSpeed(opponentChampions);
            opponentSpeed = opponentChampions;
            while (opponentSpeed[o].getHealth() <= 0 ) {
                o++;
                if(o>4)o=0;
            }
            attackModified(opponentSpeed[o],"Defend");
            attackCount++;
            sortToHealth(playerChampions);
            if (playerChampions[4].getHealth() <= 0){
//                System.out.println("WIN CALLED FOR THE OPPONENT");
                win(opponent,player);
                attackCount--;
                break;
            }
            if(o==4)o=0;
            else o++;
            round++;
        }
        if(attackCount == 18){
            System.out.println("=======================================");
            System.out.println("\tThe battle was draw! in round " + round);
            System.out.println("=======================================");
            System.out.printf("%s's XP: %d Gold coins: %d\n", player.getName(), player.getExp(), (int)player.getGold());
            System.out.printf("%s's XP: %d Gold coins: %d\n",opponent.getName(), opponent.getExp(), (int)opponent.getGold());
            recover();
        }
    }


    /**
     * Conducts a normal attack between two characters without considering ground effects.
     *
     * @param char1 The character initiating the attack.
     * @param char2 The character defending against the attack.
     * @param turn  A string indicating whose turn it is to attack ("Attack" for player's turn, "Defend" for opponent's turn).
     */
    private void attacksNormal(Character char1, Character char2, String turn){
//        System.out.println("attackNormal called");
        if(!char1.getCategory().equals("Healer")) {
//            char2.setHealth(char2.getHealth() - char1.getAttack());
            if(turn.equals("Attack")) {
                System.out.printf("%s's %s attacks %s's %s \n", player.getName(), char1.getName(), opponent.getName(), char2.getName());
            }
            else{
                System.out.printf("%s's %s attacks %s's %s \n", opponent.getName(), char1.getName(), player.getName(), char2.getName());
            }
            float damage = (char1.getAttack()*0.5f - char2.getDefence()*0.1f);

            if(turn.equals("Attack")) {
                System.out.printf("%s's %s got %.1f damage!\n", opponent.getName(), char2.getName(), damage);

            }
            else {
                System.out.printf("%s's %s got %.2f damage!\n", player.getName(), char2.getName(), damage);
            }
            //########################
            if(damage >= char2.getHealth()){
                System.out.printf("Defender %s's health : %.1f\n", char2.getName(), 0.0);
            }
            else {
                System.out.printf("Defender %s's health : %.2f\n", char2.getName(), char2.getHealth());
            }
            System.out.printf("Attacker %s's health : %.1f\n", char1.getName() ,char1.getHealth());
            char2.setHealth(char2.getHealth() - damage);
            //########################
            if (char2.getHealth() <= 0){
                if(turn.equals("Attack")) {
                    System.out.println("----------------------------------------");
                    System.out.printf("%s's %s Died!\n", opponent.getName(), char2.getName());
                    System.out.println("----------------------------------------");
                }
                else {
                    System.out.println("----------------------------------------");
                    System.out.printf("%s's %s Died!\n", player.getName(), char2.getName());
                    System.out.println("----------------------------------------");
                }
            }
        }


        else{
            Character[] healthSort;
            if(turn.equals("Attack")) healthSort = playerChampions;
            else healthSort = opponentChampions;
            sortToHealth(healthSort);
            int j = 0;
            while (healthSort[j].getHealth() <= 0) j++;
            if(turn.equals("Attack")) {
                System.out.printf("%s's %s healed %s's %s \n", player.getName(), char1.getName(), player.getName(), healthSort[j].getName());
                float heal = char1.getAttack() * 0.1f;
//                if((healthSort[j].getHealth() + heal) >= player.getCharacters()[linearSearch(player.getCharacters(),healthSort[j])].getHealth()){
//                    heal = player.getCharacters()[linearSearch(player.getCharacters(),healthSort[j])].getHealth() - healthSort[j].getHealth();
//                }
                healthSort[j].setHealth(healthSort[j].getHealth() + heal);
                System.out.printf("%s's %s got %.1f health increase!\n", player.getName(), healthSort[j].getName(), heal);
            }
            else {
                System.out.printf("%s's %s healed %s's %s \n", opponent.getName(), char1.getName(), opponent.getName(), healthSort[j].getName());
                float heal = char1.getAttack() * 0.1f;
//                if((healthSort[j].getHealth() + heal) >= opponent.getCharacters()[linearSearch(opponent.getCharacters(),healthSort[j])].getHealth()){
//                    heal = opponent.getCharacters()[linearSearch(opponent.getCharacters(),healthSort[j])].getHealth() - healthSort[j].getHealth();
//                }
                healthSort[j].setHealth(healthSort[j].getHealth() + heal);
                System.out.printf("%s's %s got %.1f health increase!\n", opponent.getName(), healthSort[j].getName(), heal);
                System.out.printf("Defender %s's health : %.2f\n", char2.getName(), char2.getHealth());
                System.out.printf("Attacker %s's health : %.1f\n", char1.getName() ,char1.getHealth());

            }
        }
    }


    /**
     * Performs a modified attack considering additional upgrades to normal attacks, such as ground effects and special abilities.
     *
     * @param attackChar The character initiating the attack.
     * @param turn       A string indicating whose turn it is to attack ("Attack" for player's turn, "Defend" for opponent's turn).
     */
    private void attackModified(Character attackChar, String turn){
//        System.out.println("attackMod called");
        Character[] defenceList;
        if(turn.equals("Attack")) {
            sortToDefence(opponentChampions);
            defenceList = opponentChampions;
        }
        else {
            sortToDefence(playerChampions);
            defenceList = playerChampions;
        }
        int defenceIndex = 0;
        if(ground.equals("Hillcrest") && attackChar.getCast().equals("Highlander")) {
            while (defenceList[defenceIndex].getHealth() <= 0) defenceIndex++;
            attacksNormal(attackChar,defenceList[defenceIndex],turn);

            if (defenceList[defenceIndex].getHealth() <= 0 && defenceIndex < 4){
                defenceIndex++;
                if(!attackChar.getCategory().equals("Healer")) {
                    float damage = attackChar.getAttack() * 0.1f - defenceList[defenceIndex].getDefence() * 0.5f;
                    defenceList[defenceIndex].setHealth(defenceList[defenceIndex].getHealth() - damage);
                    System.out.printf("%s's %s attacked %s's %s again with a bonus turn! \n", player.getName(), attackChar.getName(), opponent.getName(), defenceList[defenceIndex].getName());
                    System.out.printf("%s's %s got %.1f damage!\n", opponent.getName(), defenceList[defenceIndex].getName(), damage);
                    if(defenceList[defenceIndex].getHealth() <= 0){
                        if (turn.equals("Attack")){
                            System.out.println("----------------------------------------");
                            System.out.printf("%s's %s died!\n", opponent.getName(),defenceList[defenceIndex].getName());
                            System.out.println("----------------------------------------");
                        }
                        else {
                            System.out.println("----------------------------------------");
                            System.out.printf("%s's %s died!\n", player.getName(),defenceList[defenceIndex].getName());
                            System.out.println("----------------------------------------");
                        }
                    }
                }
                else {
                    Character[] healthSort;
                    if(turn.equals("Attack")) healthSort = playerChampions;
                    else healthSort = opponentChampions;
                    sortToHealth(healthSort);
                    int k = 0;
                    while (healthSort[k].getHealth() <= 0) k++;
                    if(turn.equals("Attack")) {
                        System.out.printf("%s's %s healed %s's %s \n", player.getName(), attackChar.getName(), player.getName(), healthSort[k].getName());
                        float bonusHeal = attackChar.getAttack() * 0.02f;
                        if((healthSort[k].getHealth() + bonusHeal) >= player.getCharacters()[linearSearch(player.getCharacters(),healthSort[k])].getHealth()){
                            bonusHeal = player.getCharacters()[linearSearch(player.getCharacters(),healthSort[k])].getHealth() - healthSort[k].getHealth();
                        }

                        healthSort[k].setHealth(healthSort[k].getHealth() + bonusHeal);
                        System.out.printf("%s's %s got %.2f health increase!\n", player.getName(), healthSort[k].getName(), bonusHeal);
                    }
                    else {
                        System.out.printf("%s's %s healed %s's %s \n", opponent.getName(), attackChar.getName(), opponent.getName(), healthSort[k].getName());
                        float bonusHeal = attackChar.getAttack() * 0.02f;
                        if((healthSort[k].getHealth() + bonusHeal) >= opponent.getCharacters()[linearSearch(opponent.getCharacters(),healthSort[k])].getHealth()){
                            bonusHeal = opponent.getCharacters()[linearSearch(opponent.getCharacters(),healthSort[k])].getHealth() - healthSort[k].getHealth();
                        }
                        healthSort[k].setHealth(healthSort[k].getHealth() + bonusHeal);
                        System.out.printf("%s's %s got %2f health increase!\n", opponent.getName(), healthSort[k].getName(), bonusHeal);
                    }
                }
            }
        }
        else if(ground.equals("Arcane") && attackChar.getCast().equals("Mystic")) {
            while (defenceList[defenceIndex].getHealth() <= 0) defenceIndex++;
            attacksNormal(attackChar, defenceList[defenceIndex], turn);
            attackChar.setHealth(attackChar.getHealth() * 1.1f);
        }
        else {
            while (defenceList[defenceIndex].getHealth() <= 0) defenceIndex++;
            attacksNormal(attackChar, defenceList[defenceIndex], turn);
        }
    }


    /**
     * Sorts an array of characters based on their speed in descending order,
     * with priority given to character categories in case of equal speeds to determine the order of characters to perform the attack.
     *
     * @param arr The array of characters to be sorted.
     */
    private void sortToSpeed(Character[] arr){
        String[] speedPriority = {"Healer", "Mage", "Mythical Creature", "Knight", "Archer"};
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].getSpeed() < arr[j + 1].getSpeed()) {
                    // Swap arr[j] and arr[j+1]
                    Character temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
                else if (arr[j].getSpeed() == arr[j + 1].getSpeed()){
                    if (linearSearch(speedPriority,arr[j].getCategory()) < linearSearch(speedPriority,arr[j + 1].getCategory())) {
                        // Swap arr[j] and arr[j+1]
                        Character temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        }
    }


    /**
     * Sorts an array of characters based on their defence in ascending order,
     * with priority given to character categories in case of equal defence values.
     *
     * @param arr The array of characters to be sorted.
     */
    private void sortToDefence(Character[] arr){
        String[] defencePriority = {"Mage", "Knight", "Archer", "Mythical Creature", "Healer"};
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < (n - i - 1); j++) {
                if (arr[j].getDefence() > arr[j + 1].getDefence()) {
                    // Swap arr[j] and arr[j+1]
                    Character temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
                else if (arr[j].getDefence() == arr[j + 1].getDefence()){
                    if (linearSearch(defencePriority,arr[j].getCategory()) < linearSearch(defencePriority,arr[j + 1].getCategory())){
                        // Swap arr[j] and arr[j+1]
                        Character temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        }
    }


    /**
     * Sorts an array of characters based on their current health in ascending order,
     * to get the character with the lowest health for healer to heal.
     * and to determine whether all the characters are dead to call off the battle.
     *
     * @param arr The array of characters to be sorted.
     */
    private void sortToHealth(Character[] arr){
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < (n - i - 1); j++) {
                if (arr[j].getHealth() > arr[j + 1].getHealth()) {
                    // Swap arr[j] and arr[j+1]
                    Character temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }


    /**
     * Performs a linear search in an array to find the index of the first occurrence of the target object.
     *
     * @param arr    The array to be searched.
     * @param target The target object to be found.
     */
    private int linearSearch(Object[] arr, Object target) {
        int r = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(target)) {
                r = i;
                break;
            }
        }
        return r;
    }



    /**
     * Handles the victory of one player over another in the battle, updating their statistics accordingly.
     *
     * @param win  The player who won the battle.
     * @param lose The player who lost the battle.
     */
    private void win(Player win, Player lose){
        System.out.printf("%s loose the battle!\n",lose.getName());
        System.out.printf("%s rewarded with %f gold coins and %d XP!\n", win.getName(), lose.getGold()*0.1f, 10);
        win.setGold(win.getGold() + lose.getGold()*0.1f);
        win.setExp(win.getExp() + 10);
        lose.setGold(lose.getGold()*0.9f);
        System.out.printf("%s's XP: %d Gold coins: %d\n", win.getName(), win.getExp(), (int)win.getGold());
        System.out.printf("%s's XP: %d Gold coins: %d\n",win.getName(), lose.getExp(), (int)lose.getGold());
        recover();
    }


    /**
     * Resets the health, defence, and speed of all characters belonging to both the player and the opponent to their original values.
     * This method is called after a battle to prepare for the next battle.
     */
    private void recover(){
        for(Character plyChar: player.getCharacters()){
            plyChar.setHealth(plyChar.getFullHealth());
            plyChar.setDefence(plyChar.getOriginalDefence());
            plyChar.setSpeed(plyChar.getOriginalSpeed());
        }
        for(Character oppChar: opponent.getCharacters()){
            oppChar.setHealth(oppChar.getFullHealth());
            oppChar.setDefence(oppChar.getOriginalDefence());
            oppChar.setSpeed(oppChar.getOriginalSpeed());
        }
    }
}