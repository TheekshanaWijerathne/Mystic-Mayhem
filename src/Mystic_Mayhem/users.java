package Mystic_Mayhem;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

public class


users implements Serializable {
    private static int numOfUsers;
    private static HashMap<String,String> user_map=new HashMap<>();



    public void logIn() {

    }





//    public Player signUp() {
//        Scanner scanner = new Scanner(System.in);
//        String username;
//        System.out.println("Enter the user name bitch !!!");
//        username = scanner.nextLine();
//        if(user_map==null) {
//            user_map.put(username, password());
//            numOfUsers++;
//        }
//        else{
//            while(true) {
//                if (!user_map.containsKey(username)) {
//                    user_map.put(username, password());
//                    numOfUsers++;
//                    break;
//                }
//                System.out.println("Some son of bitch already taken your username!!!\nTry another one!!!");
//                username = scanner.nextLine();
//            }
//        }
//        System.out.println("");
//        //return Player(name,username,);
//    }




    private String password(){
        Scanner scanner = new Scanner(System.in);
        String password;
        while(true) {
            System.out.println("Input your damn password.\nIt should be more than 8 characters.");
            password = (String)scanner.nextLine();
            if(password.length() >= 8){
                System.out.println("Congratulations bitch you know Math.your password was successfully created!!!");
                break;
            }
            System.out.println("Just go and drink Ancher pedia pro.Don't you know number 8");
        }
        return password;
    }


}
