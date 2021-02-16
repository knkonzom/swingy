package com.swingy.view;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.swingy.*;

public class ConsoleView {
    Scanner input = new Scanner(System.in);

    public ConsoleView() throws IOException {
        System.out.println("Welcome to Adventure Game");
        System.out.println("Press '1' to create new game");
        System.out.println("Press '2' to select hero");

        try {
            int choice = input.nextInt();
            if (Integer.toString(choice).isEmpty()) {
                System.out.println("No INPUT!");
            }
            String name = input.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter new name: \n");
                    name = input.nextLine();
                    if (name.isEmpty()) {
                        System.out.println("No name entered!");
                        System.exit(0);
                    }
                    if (name.length() > 15) {
                        System.out.println("Too many characters in name!");
                        System.exit(1);
                    }
                    DataController dataControl = new DataController(name);
                    dataControl.getStorage().saveData(name);

                    System.out.println("Success: new game created!");
                    System.out.println("\tType 'START' to begin game.");
                    System.out.println("\tType 'QUIT' to exit game.");
                    String userInput = input.nextLine();

                    if (userInput.equalsIgnoreCase("START")) {
                        dataControl.getStorage().loadData(name);
                        dataControl.getStats().displayStats(name);
                        dataControl.getNavigate().moveDirection(PrintStats.getCoordinates(), name);
                    }
                    else if (userInput.equalsIgnoreCase("QUIT")) {
                        System.exit(1);
                    }
                    break;

                case 2:
                    System.out.println("Please enter hero name: \n");
                    name = input.nextLine();
                    DataController dataControl2 = new DataController(name);
                    dataControl2.getStorage().loadData(name);
                    dataControl2.getStats().displayStats(name);
                    dataControl2.getNavigate().moveDirection(PrintStats.getCoordinates(), name);

                    break;

                default:
                    System.out.println("Invalid selection!");
            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Invalid input!");
        }
    }
}
