package airport;

import java.util.Scanner;

public class Airport {
    private int inputConsole;



    public static void menu () {

            Scanner inputConsole = new Scanner(System.in);
//            Minden azonos változó kijelölése ALT +J
            while (!inputConsole.hasNextInt()) {
//                System.out.println("Input is not a number.");
                inputConsole.nextLine();
            }
            int number = inputConsole.nextInt();

        switch (inputConsole)
        case 1: addPlane();
    }

    public static void addPlane (String newPlane) {
        //TODO
    }
    public static void findPlane (String findPlane) {
        //TODO
    }
    public static void listAllPlane (String listAllPlane) {
        //TODO
    }
    public static void addNewCity (String addNewCity) {
        //TODO
    }
    public static void findId (String addNewCity) {
        //TODO
    }
}
