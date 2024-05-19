package Mystic_Mayhem;

import java.io.*;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Player implements Serializable {
        //Static variables belongs to class...

        private static int numOfUsers;
        private static HashMap<String,String> user_map=new HashMap<>();
        //Variables belongs to specific object....
        private String name;
        private String username;
        private int id;
        private int exp;
        private float gold;
        private Character[] characters = new Character[5] ;
        private String homeGround;




        /**
         * Allows a new player to sign up by entering a unique username and password.
         * If the username is already taken, prompts the user to enter a different username.
         * Increments the number of users in the system.
         */
        public void signUp() {
                Scanner scanner = new Scanner(System.in);
                String username;
                System.out.println("Welcome Warrior\nEnter the user name!!!");
                System.out.println("Press \"1\" to back");
                username = scanner.nextLine();
                if(username.equals("1")){
                        new GameMenu().start();
                }
                if (user_map == null) {
                        user_map.put(username, setPassword());
                        numOfUsers++;
                } else {
                        while (true) {
                                if (!user_map.containsKey(username)) {
                                        user_map.put(username, setPassword());
                                        numOfUsers++;
                                        break;
                                }
                                System.out.println("This username is already taken!!!\nTry another one!!!");
                                username = scanner.nextLine();
                        }
                }
                this.newPlayer(username);
        }

        /**
         * Initializes a new player with the given username, allowing them to set their name, choose a home-ground,
         * and initializes their experience, gold, and id.
         * @param username The unique username of the player.
         */
        private void newPlayer(String username) {

                Scanner scn = new Scanner(System.in);
                this.username = username;
                System.out.println("Enter name");
                this.name= scn.nextLine();
                System.out.println("Choose Your home-ground :");
                System.out.println("1. Hillcrest");
                System.out.println("2. Marshland");
                System.out.println("3. Desert");
                System.out.println("4. Arcane");


                int choice;
                do {
                        System.out.println("Enter Your Choice");
                        try {
                                choice = scn.nextInt();
                        }
                        catch(java.util.InputMismatchException e){
                                scn.nextLine();
                                choice = 6;
                        }
                        switch (choice) {
                                case 1: homeGround="Hillcrest"; break;
                                case 2: homeGround="Marshland"; break;
                                case 3: homeGround="Desert"; break;
                                case 4: homeGround="Arcane"; break;
                        default:
                                System.out.println("Invalid choice Warrior. Please try again.");
                        }

                } while ( (0 > choice)  || (choice > 5));

                //this.homeGround =String.valueOf(choice);
                this.exp=0;
                this.gold=500;
                this.id = numOfUsers;
        }



        /**
         * Loads the user map and the number of users from serialized files.
         * Reads the user map from "userMap.ser" and the number of users from "numOfUsers.ser".
         * Updates the user_map and numOfUsers variables accordingly.
         */
        public static void loadUserMapAndNumOfUsers() {

                try {
                        FileInputStream fileIn = new FileInputStream("userMap.ser");
                        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                        user_map = (HashMap<String, String>) objectIn.readObject();
                        objectIn.close();
                        fileIn.close();

                        FileInputStream numOfUsersIn = new FileInputStream("numOfUsers.ser");
                        ObjectInputStream numOfUsersObjectIn = new ObjectInputStream(numOfUsersIn);
                        numOfUsers = numOfUsersObjectIn.readInt();
                        numOfUsersObjectIn.close();
                        numOfUsersIn.close();
                } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                }
        }



        /**
         * Gets a valid password from the user
         */
        private String setPassword(){

                Scanner scanner = new Scanner(System.in);
                String password;
                while(true) {
                        System.out.println("Input your password.\n It should be more than 8 characters.");
                        password = (String)scanner.nextLine();
                        if(password.length() >= 8){
                                System.out.println("Congratulations!!! Your password is successfully created!!!");
                                break;
                        }
                        System.out.println("Your Password is too short...Try another one");
                }
                return password;
        }



        /**
         * Logs in a player by asking for their username and password.
         * If the username is not found or the password is incorrect, allows the user to try again.
         * if the username is found then deserialize the object
         * Returns the logged-in player.
         * @return The logged-in player.
         */
        //log in to exist player's account
        public Player logIn(){


                Scanner scanner = new Scanner(System.in);
                Player player = new Player();
                String inputname;
                do {
                        System.out.println("Enter your username Warrior !!!");
                        System.out.println("Press \"1\" to back");
                        inputname = scanner.nextLine();
                        if(inputname.equals("1")){
                                new GameMenu().start();
                        }
                        String filename = inputname + ".ser";
                        if(user_map.containsKey(inputname)){
                                while(true){
                                        System.out.println("Enter your password !!!");
                                        String password = scanner.nextLine();
                                        if(user_map.get(inputname).equals(password)){
                                                player = (Player)loadPlayer(filename);
                                                System.out.println("Welcome back " + player.name);
                                                break;
                                        }
                                        System.out.println("Wrong password. Try again.");
                                }
                                break;
                        }
                        System.out.println("Wrong username . Try again.");
                }while(true);
                return player;
        }


        /**
         * Saves the player's data to a serialized file named their username.
         * If the file already exists, it will be overwritten.
         */

        public void savePlayer() {
                try
                {
                        //Saving of object in a file
                        String filename = this.username + ".ser";
                        FileOutputStream file = new FileOutputStream(filename);
                        ObjectOutputStream out = new ObjectOutputStream(file);


                        out.writeObject(this);
                        out.close();
                        file.close();

                }
                catch(IOException ex)
                {
                        System.out.println("IOException is caught");
                }
        }


        /**
         * Loads a player's data from a serialized file.
         * @param filename The name of the file containing the player's data.
         * @return The loaded player object, or null if the file cannot be loaded.
         */

        public Player loadPlayer(String filename){
                Player player = null;
                try {
                        FileInputStream fileIn = new FileInputStream(filename);
                        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                        player = (Player) objectIn.readObject();
                        objectIn.close();
                        fileIn.close();
                } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                }
                return player;
        }


        /**
         * Saves the user map and the number of users to serialized files.
         * The user map is saved to "userMap.ser" and the number of users is saved to "numOfUsers.ser".
         */

        public static void save_UserMap_And_NumOfUsers() {
                try {
                        FileOutputStream fileOut = new FileOutputStream("userMap.ser");
                        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                        objectOut.writeObject(user_map);
                        objectOut.close();
                        fileOut.close();

                        FileOutputStream numOfUsersOut = new FileOutputStream("numOfUsers.ser");
                        ObjectOutputStream numOfUsersObjectOut = new ObjectOutputStream(numOfUsersOut);
                        numOfUsersObjectOut.writeInt(numOfUsers);
                        numOfUsersObjectOut.close();
                        numOfUsersOut.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }



        /**
         * Displays the player's profile statistics, including name, username, home ground, gold, experience, and ID.
         *Allows the User to change the name and save the changes
         * @param player The name player object that we need to get the status.
         */

        public void playerStats(Player player) {
                System.out.println("--------------------------------------------------");
                System.out.println("\t\t\t\t\t\tPROFILE");
                System.out.println("--------------------------------------------------");
                System.out.println("Name : " + name);
                System.out.println("Username : " + username);
                System.out.println("Home Ground : " + homeGround);
                System.out.println("Gold : " + gold);
                System.out.println("Exp : " + exp);
                System.out.println("Id : " + id);

                System.out.println("--------------------------------------------------");

                System.out.println("Do you want change Your Name");
                System.out.println("1. Change Name");
                System.out.println("2. Back to Main Menu");

                int select;
                Scanner scanner = new Scanner(System.in);
                try {
                        select = scanner.nextInt();
                }
                catch(java.util.InputMismatchException e){
                        select = 3;
                }
                if (select == 1) {
                        System.out.println("Enter Your New Name :");
                        String NAME = scanner.next();
                        this.name=NAME;
                        System.out.println("Name has been changed to "+ name);
                        savePlayer();
                        new GameMenu(player).MainMenu();
                }
                else if (select == 2) {
                        System.out.println("Back to Main Menu");
                        new GameMenu(player).MainMenu();
                }
                else{
                        System.out.println("Wrong Input.");
                        System.out.println("you are back to Main Menu...");
                        new GameMenu(player).MainMenu();
                }
        }



        /**
         * Checks if the player can buy a new character based on their current gold and the cost of characters.
         * @return True if the player can buy a character, false otherwise.
         */

        public boolean canBuyCharacter() {
                int cost = 0;
                for(int i = 0 ; i < 5 ; i ++){
                        if(characters[i] == null){
                                switch(i){
                                        case 0:
                                                cost += 80;
                                                break;
                                        case 1:
                                                cost += 85;
                                                break;
                                        case 2:
                                                cost += 100;
                                                break;
                                        case 3:
                                                cost += 95;
                                                break;
                                        case 4:
                                                cost += 120;
                                                break;
                                }
                        }
                }
                if(cost > this.gold){
                        return false;
                }
                else{
                        return true;
                }
        }



        /**
         * Buys a character for the player and deducts the cost from their gold.
         * @param i The index of the character array to place the new character.
         * @param n The subtype's index of the character
         * @return True if the character was successfully bought, false otherwise.
         */

        public boolean buyCharactor(int i, int n) {
                Character newcha;
                if (characters[i] == null) {
                        switch (i) {
                                case 0:
                                        newcha = new Archer(n);
                                        break;
                                case 1:
                                        newcha = new Knights(n);
                                        break;
                                case 2:
                                        newcha = new Mages(n);
                                        break;
                                case 3:
                                        newcha = new Healers(n);
                                        break;
                                case 4:
                                        newcha = new MythicalCreatures(n);
                                        break;
                                default:
                                        return false; // Invalid index
                        }
                        this.gold -= newcha.getprice(); // Reduce gold
                        characters[i] = newcha;
                        if (!canBuyCharacter()) {
                                this.gold += newcha.getprice(); // Revert gold
                                System.out.println("==================== Warning!!!====================");
                                System.out.println("You cannot buy other characters if you buy this !!!");
                                System.out.println("===================================================");
                                characters[i] = null;
                                return false;
                        } else {
                                return true;
                        }
                }
                return false;
        }



        /**
         * Displays the opponent's profile statistics, including name, home ground, gold, and experience.
         * Also displays the opponent's champions.
         * @param opponent The opponent player whose stats are being displayed.
         */

        private void opponentStats(Player opponent) {
                System.out.println("--------------------------------------------------");
                System.out.println("--------------------------------------------------");
                System.out.println("\t\t\t\tOPPONENT'S PROFILE");
                System.out.println("--------------------------------------------------");
                System.out.println("Name : " + opponent.getName());
                System.out.println("Home Ground : " + opponent.getGround());
                System.out.println("Gold : " + opponent.getGold());
                System.out.println("Exp : " + opponent.getExp());
                System.out.println("------------Opponent's Champions-----------------");
                for(Character char1 : opponent.getCharacters()){
                        System.out.println(char1.getCategory() + ": " + char1.getName());
                }
                System.out.println("--------------------------------------------------");
                System.out.println("--------------------------------------------------");
        }
        /**
         * Initiates a battle with a randomly selected opponent.
         * Allows the player to choose to attack, skip the battle, or exit.
         * Saves the player and opponent data after the battle.
         */
        public void findBattle() {
                System.out.println("You are going to have fun.....");
                System.out.println("Good Luck For the Game.....");
                Scanner scanner = new Scanner(System.in);
                int choice;
                Random random = new Random();
                Player opponent = null;
                do {
                        int opponentIndex = random.nextInt(numOfUsers);
                        String[] keys = user_map.keySet().toArray(new String[0]);
                        if(keys[opponentIndex].equals(username)) {
                                choice = 2;
                                continue;
                        }
                        else {
                                opponent = loadPlayer(keys[opponentIndex]+".ser");
                                opponentStats(opponent);
                        }
                        System.out.println("1. Attack");
                        System.out.println("2. Skip");
                        System.out.println("3. Exit");
                        System.out.println("Enter Your Choice :");
                        choice = scanner.nextInt();
                        switch(choice) {
                                case 1:
                                        Battle match = new Battle(this,opponent);
                                        this.savePlayer();
                                        opponent.savePlayer();
                                        break;
                                case 2:
                                        continue;
                                case 3:
                                        System.out.println("Back To the Main Menu");
                                        break;

                                default :
                                        System.out.println("Invalid choice!");
                                        continue;
                        };


                } while (choice != 3 && choice != 1);
                new GameMenu(this).MainMenu();
        }


        /**
         * Checks if the player already has a character of the given type.
         * If so, prompts the player to sell the existing character before buying a new one.
         * @param type The type of character to check for.
         * @return True if the player can proceed with buying a new character, false otherwise.
         */

        public boolean characterExist(int type){
                Scanner scanner = new Scanner(System.in);
                int choice ;
                if(characters[type] != null){
                        System.out.println("You already have character from this class.You have to sell it in order to buy new !!! \n  1.sell or 2.back");
                        do{
                                try {
                                        choice = scanner.nextInt();
                                }
                                catch(java.util.InputMismatchException e){
                                        System.out.println("Invalid input !!!");
                                        scanner.nextLine();
                                        choice = 5;
                                }

                        }while(choice != 1 && choice != 2);
                        if(choice == 1){
                                if(sellCharacter(type)){
                                        return true;
                                }
                                else{
                                        return false;
                                }
                        } else if (choice==2) {new Shop().championsMenu(this);}



                }
                return true;
        }


        /**
         * Sells a character of the given type, adding 90% of its price to the player's gold.
         * @param type The type of character to sell.
         * @return True if the character was successfully sold, false otherwise.
         */

        private boolean sellCharacter(int type){
                int cost = 0;
                System.out.println("type = " + type  );
                switch(type){
                        case 0 :
                                cost = 80;
                                break;
                        case 1 :
                                cost = 85;
                                break;
                        case 2 :
                                cost = 100;
                                break;
                        case 3 :
                                cost = 95;
                                break;
                        case 4 :
                                cost = 120;
                                break;
                }
                if((gold + ((characters[type].getprice() * 90)/100)) < cost){
                        System.out.println("You can't sell this .If you sell this, you will not have enough money to buy new!!!");
                        return false;
                }
                else{
                        gold = gold + (int)((characters[type].getprice() * 90)/100) ;
                        characters[type] = null ;
                        System.out.println("Successfully Sold");
                        return true;
                }
        }







        public String getGround() {
                return homeGround;
        }




        public String getName() {
                return name;
        }


        public int getExp() {
                return exp;
        }

        public void setExp(int exp) {
                this.exp = exp;
        }

        public float getGold() {
                return gold;
        }

        public void setGold(float gold) {
                this.gold = gold;
        }

        public Character[] getCharacters() {
                return characters;
        }



        public boolean characterHaveArmour(int typeC) {
                return characters[typeC].HaveArmour();
        }
        public void sellCharacter_Armour(int typeC){
                gold += (int)characters[typeC].getArmour().getPrice();
                characters[typeC].SellArmour();

        }
        public void sellCharacter_Artifact(int typeC) {
                gold += (int)characters[typeC].getArtifact().getPrice();
                characters[typeC].SellArtifact();

        }
        public boolean character_HaveArtifact(int typeC) {
                return characters[typeC].HaveArtifact();
        }
        public boolean hasEnoughMoneyToBuyEquipment(int price){
                if(this.gold >= price){
                        return true;
                }
                return false;
        }
        public void setCharacter_Equipment(int type , int type_C, Equipment equipment){
                if(type == 0){
                        this.characters[type_C].setArmour(equipment);
                }
                else if(type == 1){
                        this.characters[type_C].setArtifact(equipment);
                }
                gold -= (int)equipment.getPrice();

        }
}