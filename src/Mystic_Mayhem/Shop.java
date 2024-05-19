package Mystic_Mayhem;

import java.util.Scanner;

public class Shop {
    static String[] championTypes = {"Archers", "Knights", "Mages", "Healers", "Mythical Creatures"};
    public static Object championDetails[][][] = {
            //name,price,attack,Defence,Health,Speed,HomeGround
            //Archers
            {{"Shooter", 80, 11, 4, 6, 9,"Highlander"}, {"Ranger", 115, 14, 5, 8, 10,"Highlander"}, {"Sunfire", 160, 15, 5, 7, 14, "Sunchildren"}, {"Zing", 200, 16, 9, 11, 14, "Sunchildren"}, {"Saggitarius", 230, 18, 7, 12, 17,"Mystic"}},
            //Knights
            {{"Squire", 85, 8, 9, 7, 8,"Marshlander"}, {"Cavalier", 110, 10, 12, 7, 10,"Highlander"}, {"Templar", 155, 14, 16, 12, 12, "Sunchildren"}, {"Zoro", 180, 17, 16, 13, 14,"Highlander"}, {"Swiftblade", 250, 18, 20, 17, 13,"Marshlander"}},
            //Mages
            {{"Warlock", 100, 12, 7, 10, 12,"Marshlander"}, {"Illusionist", 120, 13, 8, 12, 14,"Mystic"}, {"Enchanter", 160, 16, 10, 13, 16,"Highlander"}, {"Conjurer", 195, 18, 15, 14, 12,"Highlander"}, {"Eldrith", 270, 19, 17, 18, 14,"Mystic"}},
            //Healers
            {{"Soother", 95, 10, 8, 9, 6, "Sunchildren"}, {"Medic", 125, 12, 9, 10, 7,"Highlander"}, {"Alchemist", 150, 13, 13, 13, 13,"Marshlander"}, {"Saint", 200, 16, 14, 17, 9,"Mystic"}, {"Lightbringer", 260, 17, 15, 19, 12,"Sunchildren"}},
            //Mythical creatures
            {{"Dragon", 120, 12, 14, 15, 8, "Sunchildren"}, {"Basilisk", 165, 15, 11, 10, 1,"Marshlander"}, {"Hydra", 205, 12, 16, 15, 11,"Marshlander"}, {"Phoenix", 275, 17, 13, 17, 19, "Sunchildren"}, {"Pegasus", 340, 14, 18, 20, 20,"Mystic"}}
    };


    String equipmentTypes[] = {"Armour", "Artefacts"};
    private static Object equipmentDetails[][][] = {
            //name,price,attack,Defence,Health,Speed
            // Armour
            {{"Chainmail", 70, 0, 1, 0, -1}, {"Regalia", 105, 0, 1, 0, 0}, {"Fleece", 150, 0, 2, 1, 1}},
            // Artefact
            {{"Excalibur", 150, 2, 0, 0, 0}, {"Amulet", 200, 1, -1, 1, 1}, {"Crystal", 210, 2, 1, -1, -1}}
    };


    private void displayEquipmentTypes() {
        for (int i = 0; i < 2; i++) {
            System.out.println(i + 1 + ". " + equipmentTypes[i]);
        }
    }


    private void displayCharactersTypes() {
        for (int i = 0; i < 5; i++)
            System.out.println(i+1 + ". " + championTypes[i]);
    }


    /**
     * Simulates the first time shopping experience for a player, allowing them to buy characters and equipment from the shop.
     * @param player The player for whom the shopping experience is being simulated.
     */

    public static void shoppingForFirstTime(Player player) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean bought;
        System.out.println("Welcome to the shop....");
        System.out.println("You can buy characters and equipment on this shop !!!");
        System.out.println("These are the main categories of characters");
        System.out.println("you need to have one character from this each categories");
        for (int i = 0; i < 5; i++) {
            System.out.println("These are the " + championTypes[i]);
            System.out.println("---------------------------------------------------------------");
            System.out.println("\tName   \t\tPrice\tAttack\tDefence\tHealth\tSpeed\tHomeGround");
            System.out.println("---------------------------------------------------------------");
            do {
                int count = 1;
                for (Object[] item : championDetails[i]) {
                    System.out.printf(count + " :%-15s%-8s%-8s%-8s%-8s%-8s%-8s\n", item[0], item[1], item[2], item[3], item[4], item[5], item[6]);
                    count++;
                }
                System.out.print("Enter your choice: ");
                try {
                    choice = scanner.nextInt();
                }
                catch(java.util.InputMismatchException e){
                    scanner.nextLine();
                    choice = 7;
                }
                if (choice >= 1 && choice <= 5) {
                    bought = player.buyCharactor(i , choice - 1);
                    if (bought == false) {
                        choice = 6;
                    }
                }
                else if(!(choice >= 1 && choice <= 6)){
                    System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5);
        }
    }


    /**
     * Provides navigation options for the player within the game shop, allowing them to buy items, se (feature not implemented yet), or exit the shop.
     *
     * @param player The player navigating the shop.
     */

    public void shopNavigation(Player player) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("==== Game Shop Menu ====");
            System.out.println("1. Buy");
            System.out.println("2. Exit");
            System.out.println("========================\n");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
            }
            catch(java.util.InputMismatchException e){
                scanner.nextLine();
                choice = 7;
            }

            switch (choice) {
                case 1:
                    buyMenu(player);
                    player.savePlayer();
                    break;

                case 2:
                    System.out.println("Exiting the shop...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 2);
    }

    /**
     * Displays the buy menu allowing the player to choose between buying champions, equipments, or returning to the main menu.
     * @param player The player for whom the buy menu is displayed.
     */

    private void buyMenu(Player player) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("==== Buy Menu ====");
            System.out.println("1. Champions");
            System.out.println("2. Equipments");
            System.out.println("3. Back to main menu");
            System.out.println("==================\n");
            System.out.print("Enter your choice: ");
            try {
                choice = scanner.nextInt();
            }
            catch(java.util.InputMismatchException e){
                scanner.nextLine();
                choice = 7;
            }

            switch (choice) {
                case 1:
                    championsMenu(player);
                    break;
                case 2:
                    equipmentsMenu(player);
                    break;
                case 3:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
            }
        } while (choice != 3);
    }



    public  void championsMenu(Player player) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("======================================");
            displayCharactersTypes();
            System.out.println("6. Back to buy menu");
            System.out.println("======================================");
            System.out.print("Enter your choice: ");
            try {
                choice = scanner.nextInt();
            }
            catch(java.util.InputMismatchException e){
                scanner.nextLine();
                choice = 7;
            }
            switch (choice) {
                case 1:
                    championSubMenu(0 ,player);
                    break;
                case 2:
                    championSubMenu(1 , player);
                    break;
                case 3:
                    championSubMenu(2 , player);
                    break;
                case 4:
                    championSubMenu(3 , player);
                    break;
                case 5:
                    championSubMenu(4 , player);
                    break;
                case 6:
                    System.out.println("Returning to buy menu...");
                    buyMenu(player);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 6);
        player.savePlayer();
    }



    private void equipmentsMenu(Player player) {
        Equipment equipment;
        Scanner scanner = new Scanner(System.in);
        int choice_C;
        int choice;
        do {
            System.out.println("======================================");
            System.out.println("Which one is to buy your equipment ???");
            int i = 1;
            for(Character character : player.getCharacters()) {
                System.out.println(i + " " + character.getName());
                i++;
            }
            System.out.println("======================================");
            System.out.println("\n6.Back to buy menu");

            try {
                choice_C = scanner.nextInt();
            }
            catch(java.util.InputMismatchException e){
                scanner.nextLine();
                choice_C = 7;
                System.out.println("Wrong input Try Again !!!");
            }
        }while(choice_C != 1 && choice_C != 2 && choice_C != 3 && choice_C != 4 && choice_C != 5 && choice_C != 6);
        if(choice_C == 6){
            buyMenu(player);
            return;
        }
        int sell;
        int type_C = choice_C -1;
        do {
            System.out.println("====Equipments Categories====");
            displayEquipmentTypes();
            System.out.println("=============================");
            System.out.println("3. Back to buy menu");
            System.out.print("Enter your choice: ");
            try {
                choice = scanner.nextInt();
            }
            catch(java.util.InputMismatchException e){
                scanner.nextLine();
                choice = 7;
            }
            switch (choice) {
                case 1:
                    if(player.characterHaveArmour(type_C)){
                        System.out.println("=============================");
                        System.out.println("You already have a Armour on" + player.getCharacters()[type_C].getName());
                        System.out.println("Do you wish to sell it???");
                        System.out.println("=============================");
                        System.out.println("1.Yes\n2.No");
                        do{
                            try {
                                sell = scanner.nextInt();
                            }
                            catch(java.util.InputMismatchException e){
                                scanner.nextLine();
                                sell = 5;
                            }
                            if(sell == 1){
                                player.sellCharacter_Armour(type_C);
                            }
                            else if(sell == 2){
                                return;
                            }
                        }while(sell != 1 && sell != 2);

                    }
                    equipment = new Equipment("Armour");
                    equipmentSubMenu(0,type_C , equipment ,player);
                    break;
                case 2:
                    if(player.character_HaveArtifact(type_C)){
                        System.out.println("=============================");
                        System.out.println("You already have a Artifact on" + player.getCharacters()[type_C].getName());
                        System.out.println("Do you wish to sell it???");
                        System.out.println("=============================");
                        System.out.println("1.Yes\n2.No");
                        do{
                            try {
                                sell = scanner.nextInt();
                            }
                            catch(java.util.InputMismatchException e){
                                scanner.nextLine();
                                sell = 5;
                            }
                            if(sell == 1){
                                player.sellCharacter_Artifact(type_C);
                            }
                            else if(sell == 2){
                                return;
                            }
                        }while(sell != 1 && sell != 2);
                    }
                    equipment = new Equipment("Artefacts");
                    equipmentSubMenu(1,type_C ,equipment,player);
                    break;
                case 3:
                    System.out.println("Returning to buy menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 3);
        player.savePlayer();
    }





    private void championSubMenu(int type ,Player player) {
        if(player.characterExist(type)){
            System.out.println("===============================================================");
            System.out.println("  " + championTypes[type]);
            System.out.println("===============================================================");
            System.out.println("---------------------------------------------------------------");
            System.out.println("\tName   \t\tPrice\tAttack\tDefence\tHealth\tSpeed\tHomeGround");
            System.out.println("---------------------------------------------------------------");
            int count = 1;
            for (Object[] item : championDetails[type]) {
                System.out.printf(count + " :%-15s%-8s%-8s%-8s%-8s%-8s%-8s\n", item[0], item[1], item[2], item[3], item[4],item[5],item[6]);
                count++;
            }
            System.out.println("===============================================================");
            System.out.println("Enter your choice: ");
            System.out.println("Gold :" + player.getGold());

            Scanner scanner = new Scanner(System.in);
            int choice;
            do {
                try {
                    choice = scanner.nextInt();
                }
                catch(java.util.InputMismatchException e){
                    scanner.nextLine();
                    choice = 7;
                }
                switch (choice) {
                    case 1:
                        System.out.println("You successfully brought :" + championDetails[type][choice - 1][0] );
                        if(!player.buyCharactor(type ,0 )){
                            choice = 6;
                        };
                        break;
                    case 2:
                        System.out.println("You successfully brought :" + championDetails[type][choice - 1][0] );
                        if(!player.buyCharactor(type ,1 )){
                            choice = 6;
                        };
                        break;
                    case 3:
                        System.out.println("You successfully brought :" + championDetails[type][choice - 1][0] );
                        if(!player.buyCharactor(type ,2 )){
                            choice = 6;
                        };
                        break;
                    case 4:
                        System.out.println("You successfully brought :" + championDetails[type][choice - 1][0] );
                        if(!player.buyCharactor(type ,3 )){
                            choice = 6;
                        };
                        break;
                    case 5:
                        System.out.println("You successfully brought :" + championDetails[type][choice - 1][0] );
                        if(!player.buyCharactor(type ,4 )){
                            choice = 6;
                        };
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5);
        }
    }




    private void equipmentSubMenu(int type ,int type_C , Equipment equipment ,Player player) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("======================================================");
            System.out.println("------------------------------------------------------");
            System.out.println("\tName   \t\tPrice\tAttack\tDefence\tHealth\tSpeed");
            System.out.println("------------------------------------------------------");
            int count = 1;
            for (Object[] item : equipmentDetails[type]) {
                System.out.printf(count + " :%-15s%-8s%-8s%-8s%-8s%-8s\n", item[0], item[1], item[2], item[3], item[4],item[5]);
                count++;
            }
            System.out.println("\n4 .Press 4 to exit.");
            System.out.println("======================================================");
            System.out.print("Enter your choice: ");
            System.out.println("(Gold = " + player.getGold() + ")");
            try {
                choice = scanner.nextInt();
            }
            catch(java.util.InputMismatchException e){
                scanner.nextLine();
                choice = 7;
            }
            switch (choice) {
                case 1:
                    if(player.hasEnoughMoneyToBuyEquipment((Integer) equipmentDetails[type][choice - 1][1])){
                        System.out.println("You successfully brought :" + equipmentDetails[type][choice - 1][0]);
                        equipment.setAll(equipmentDetails[type][choice - 1]);
                        player.setCharacter_Equipment(type ,type_C , equipment);
                    }
                    else{
                        System.out.println("Insufficient account balance\nTry other one");
                    }
                    break;
                case 2:
                    if(player.hasEnoughMoneyToBuyEquipment((Integer) equipmentDetails[type][choice - 1][1])){
                        System.out.println("You successfully brought :" + equipmentDetails[type][1][0]);
                        equipment.setAll(equipmentDetails[type][choice - 1]);
                        player.setCharacter_Equipment(type ,type_C , equipment);
                    }
                    else{
                        System.out.println("Insufficient account balance\nTry other one");
                    }
                    break;
                case 3:
                    if(player.hasEnoughMoneyToBuyEquipment((Integer) equipmentDetails[type][choice - 1][1])){
                        System.out.println("You successfully brought :" + equipmentDetails[type][2][0]);
                        equipment.setAll(equipmentDetails[type][choice - 1]);
                        player.setCharacter_Equipment(type ,type_C , equipment);
                    }
                    else{
                        System.out.println("Insufficient account balance\nTry other one");
                    }
                    break;
                case 4:
                    System.out.println("You are Exciting");
                    break;
                default:
                    System.out.println("Invalid input.\nTry again!");
                    break;
            }
        } while (choice != 1 && choice != 2 && choice != 3 && choice != 4);
    }
}






