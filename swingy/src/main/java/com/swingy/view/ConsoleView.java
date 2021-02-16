package com.swingy.view;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.swingy.model.storage.Storage;
import com.swingy.controller.DataController;
import com.swingy.model.characters.PrintStats;

public class ConsoleView {

    Scanner userInput = new Scanner(System.in);

    public ConsoleView() throws IOException {
        System.out.println("Welcome to the Game!");
        System.out.println("To start: \n");
        System.out.println("Enter [1] to start a new game.");
        System.out.println("Enter [2] load a hero");

        try {

            int input = userInput.nextInt();

            if (Integer.toString(input).isEmpty()) {
                System.out.println("No input found!");
            }
            String name = userInput.nextLine();

            switch (input) {
                case 1:
                    System.out.println("Enter your name to begin: \n");
                    System.out.println("----------------------------");

                    name = userInput.nextLine();
                    if (name.isEmpty()) {
                        System.out.println("No name entered!");
                        System.exit(0);
                    }
                    if (name.length() > 20) {
                        System.out.println("Name is too long!");
                        System.exit(1);
                    }
                    DataController control = new DataController(name);
                    control.getStorage().storeData(name);

                    System.out.println("Game started! Please make a selection below: ");
                    System.out.println("\t\tType START to begin");
                    System.out.println("\t\tType QUIT to exit game.");
                    String selection = userInput.nextLine();
                    if (selection.equalsIgnoreCase("START")) {
                        control.getStorage().loadData(name);
                        control.getStats().displayStats(name);
                        control.getNavigation().moveDirection(PrintStats.getCoordinates(), name);
                    }
                    else if (selection.equalsIgnoreCase("QUIT")) {
                        System.exit(1);
                    }
                    break;
                case 2:
                    System.out.println("Enter name of previous hero: ");
                    name = userInput.nextLine();
                    DataController control1 = new DataController(name);
                    control1.getStorage().loadData(name);
                    control1.getStats().displayStats(name);
                    control1.getNavigation().moveDirection(PrintStats.getCoordinates(), name);
                    break;
                default:
                    System.out.println("Invalid selection!");

            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Wrong input!");
        }
    }
}
