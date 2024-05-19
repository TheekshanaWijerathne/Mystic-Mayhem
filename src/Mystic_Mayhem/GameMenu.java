
package Mystic_Mayhem;

import com.sun.tools.javac.Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Scanner;

public class GameMenu {
    Scanner scanner = new Scanner(System.in);
    Player player = new Player();

    GameMenu(Player player){
        this.player = player;
    };
    public GameMenu(){
        player = new Player();
    }

    public void start() {
        System.out.println("╭─────────────────────────────────────────────╮");
        System.out.println("│ New here?                                   │");
        System.out.println("│    1.Then Sign up start training!!!         │");
        System.out.println("│                                             │");
        System.out.println("│ Have an existing account?                   │");
        System.out.println("│    2.Then login and conquer the world!!     │");
        System.out.println("│ Done?                                       │");
        System.out.println("│    3. Exit                                  │");
        System.out.println("╰─────────────────────────────────────────────╯");
        System.out.println();
        System.out.println(" >>>>>>>Enter Your Choice : ");

        int choice;
        Player.loadUserMapAndNumOfUsers();
        try {
            choice = scanner.nextInt();
        }catch (Exception e){
            scanner.nextLine();
            choice = 0;
        }


        do{
            switch (choice) {
                case 1:
                    player.signUp();
                    player.savePlayer();
                    Player.save_UserMap_And_NumOfUsers();
                    Shop.shoppingForFirstTime(player);
                    player.savePlayer();
                    MainMenu();

                    break;
                case 2:
                    player = player.logIn();
                    player.setGold(999999999);
                    player.setExp(99999999);
                    player.savePlayer();
                    MainMenu();
                    break;
                case 3:
                    System.out.println("Warrior, the enemies are conquering the world !!! Come back soon.");
                    System.exit(0);
                    break;
                default:
                    System.out.println(" Your input is invalid Warrior !!!!. Please try again.");
                    scanner.nextLine();
                    start();
                    break;
            }
        } while(false);

    }


    public void MainMenu() {
        System.out.println("------------------------------------------");
        System.out.println("------------------------------------------");
        System.out.println("------------------------------------------");
        System.out.println("1. Find Battle");
        System.out.println("2. Inventory");
        System.out.println("3. Shop");
        System.out.println("4. Profile");
        System.out.println("5. Back to Previous Menu");
        System.out.println("6. Exit");

        System.out.println(">>>>>>>Enter Your Choice : ");
        int select;
        do {

            try{
                select = scanner.nextInt();
            }catch (Exception e){
                scanner.nextLine();
                select = 0;
            }
            switch (select) {
                case 1:
                    System.out.println("Find A Battle");
                    player.findBattle();
                    break;
                case 2:
                    Inventory();
                    break;
                case 3:
                    var shop = new Shop();
                    shop.shopNavigation(player);// 1st edit................/////////////
                    player.savePlayer();
                    MainMenu();
                    break;
                case 4:
                    player.playerStats(player);
                    player.savePlayer();
                    break;
                case 5:
                    System.out.println("------------------------------------------");
                    System.out.println("Backing Up to previous menu");
                    System.out.println("------------------------------------------");
                    player = new Player();
                    start();
                    //start();
                    break;
                case 6:
                    System.out.println("------------------------------------------");
                    System.out.println("-----------------Exiting------------------");
                    System.out.println("------------------------------------------");
                    System. exit(0);
                    break;
                default:
                    System.out.println("Your Choice is incorrect. Please Enter a correct choice");
                    break;
            }

        } while (select != 6);
        System.out.println("\t\t\t\t\t\t------------------------------------------");

    }

    public void Inventory(){
        System.out.println("----------------------------------------------------------------");
        System.out.println("-----------------------PLAYER'S CHAMPIONS-----------------------");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Archer            = " + player.getCharacters()[0].getName() +  "  ("+"Armour = " + player.getCharacters()[0].ArmourName() + "  Artifact = " + player.getCharacters()[0].ArtifactName() + ")");
        System.out.println("Knight            = " + player.getCharacters()[1].getName()+   "  ("+"Armour = " + player.getCharacters()[1].ArmourName() + "  Artifact = " + player.getCharacters()[1].ArtifactName() + ")");
        System.out.println("Mage              = " + player.getCharacters()[2].getName()+   "  ("+"Armour = " + player.getCharacters()[2].ArmourName() + "  Artifact = " + player.getCharacters()[2].ArtifactName() + ")");
        System.out.println("Healer            = " + player.getCharacters()[3].getName() + "  ("+"Armour = " + player.getCharacters()[3].ArmourName() + "  Artifact = " + player.getCharacters()[3].ArtifactName() + ")");
        System.out.println("Mythical Creature = " + player.getCharacters()[4].getName() +  "  ("+"Armour = " + player.getCharacters()[4].ArmourName() + " Artifact = " + player.getCharacters()[4].ArtifactName() + ")" );
        System.out.println("----------------------------------------------------------------");

        int choice=0;
        while(choice!=1) {
            System.out.println("Press 1 to exit Inventory");

            try {
                choice = scanner.nextInt();
            }
            catch(java.util.InputMismatchException e){
                scanner.nextLine();
                choice = 5;
            }
            if (choice == 1)
                MainMenu();
        }
    }





    public static void playAudio(String filePath) {
        new Thread(() -> {
            try {
                // Get audio input stream
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

                // Get clip
                Clip clip = AudioSystem.getClip();

                // Open audio clip and start playing
                clip.open(audioInputStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);

                // Wait for the program to end
                synchronized (Main.class) {
                    try {
                        Main.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Close resources
                clip.close();
                audioInputStream.close();
            } catch (Exception e) {
                System.err.println("Error playing audio: " + e.getMessage());
            }
        }).start();
    }

}
