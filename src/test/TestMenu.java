package src.test;

import java.util.Scanner;

import src.utils.Menu;
import src.utils.UserInteraction;

public class TestMenu {
    static Scanner scanner = new Scanner(System.in);
    static Menu menu = new Menu("option1", "option2", "option3");
    
    public static void main(String[] args) {

        menu.displayOptions();
        String[] userChoice = new String[1];
        try {
            UserInteraction.limitAttempts(() -> {
                userChoice[0] = menu.getUserChoice(scanner);
            }, 3);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }

        menu.proceedToOption(userChoice[0], method1, method2, method3);
    }

    static Runnable method1 = () -> {
        System.out.println("method1 runs.");
    };

    static Runnable method2 = () -> {
        System.out.println("method2 runs.");
    };

    static Runnable method3 = () -> {
        System.out.println("method3 runs.");
    };
}
