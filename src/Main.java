import Mystic_Mayhem.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {


        GameMenu.playAudio("videoplayback.wav");
        System.out.println("╔╦╗╦ ╦╔═╗╔╦╗╦╔═╗             ");
        System.out.println("║║║╚╦╝╚═╗ ║ ║║               ");
        System.out.println("╩ ╩ ╩ ╚═╝ ╩ ╩╚═╝             ");
        System.out.println("          ╔╦╗╔═╗╦ ╦╦ ╦╔═╗╔╦╗ ");
        System.out.println("          ║║║╠═╣╚╦╝╠═╣║╣ ║║║ ");
        System.out.println("          ╩ ╩╩ ╩ ╩ ╩ ╩╚═╝╩ ╩ ");
        System.out.println("╔╗ ╦ ╦   ╔═╗┬ ┬┌┐ ┌─┐┬─┐  ┌─┐┬─┐┌─┐┌─┐┌─┐┌─┐┬─┐┌─┐");
        System.out.println("╠╩╗╚╦╝───║  └┬┘├┴┐├┤ ├┬┘  │  ├┬┘├┤ ├┤ ├─┘├┤ ├┬┘└─┐");
        System.out.println("╚═╝ ╩    ╚═╝ ┴ └─┘└─┘┴└─  └─┘┴└─└─┘└─┘┴  └─┘┴└─└─┘");

        GameMenu UI = new GameMenu();
        UI.start();
        }
}



