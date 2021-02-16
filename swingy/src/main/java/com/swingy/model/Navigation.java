package com.swingy.model;

import com.swingy.model.characters.PrintStats;
import com.swingy.model.characters.Villain;
import com.swingy.model.Combat;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Navigation {

    private int x = 0;
    private int y = 0;

    int selection = 1;
    Scanner userInput = new Scanner(System.in);
    Random getRandom = new Random();

    public Navigation() {}

    public void moveDirection(int mapSize, String name) throws IOException {
        Villain villain = new Villain(name);
        PrintStats hero = new PrintStats(name);
        Combat fight = new Combat(hero, villain);

        String[] villains = {null, villain.type, null, null};

        System.out.println("Select an option below to move: ");
        System.out.println("1. Press 1 to move North.");
        System.out.println("2. Press 2 to move East.");
        System.out.println("3. Press 3 to move West.");
        System.out.println("4. Press 4 to move South.");
        System.out.println("5. Press 5 for status or 0 to quit.");
        System.out.println("Make a selection: ");

        while (selection != 0) {
            selection = userInput.nextInt();

            switch (selection) {
                case 0:
                    System.exit(0);
                    break;

                case 1:
                    while (y++ > 0) {
                        String villainName = villains[getRandom.nextInt(villains.length)];
                        if (villainName != null) {
                            System.out.println("You encountered Villain " + villainName + " HP: " + villain.HP + " Attack: " + villain.attack);
                            fight.battle(name);
                            break;
                        }
                        if (y == mapSize) {
                            System.out.println("You have reached the border - levelling up!");
                            setVictoryMessage(name);
                            y = 1;
                            break;
                        }
                        break;
                    }
                case 2:
                    while (--y < 0) {

                        String villainName = villains[getRandom.nextInt(villains.length)];
                        if (villainName != null) {
                            System.out.println("You encountered Villain " + villainName + " HP: " + villain.HP + " Attack: " + villain.attack);
                            fight.battle(name);
                            break;
                        }
                        if (y == (-mapSize)) {
                            System.out.println("You have reached the border - levelling up!");
                            setVictoryMessage(name);
                            y = 1;
                            break;
                        }
                        break;
                    }
                    break;
                case 3:
                    while (--x < 0) {
                        String villainName = villains[getRandom.nextInt(villains.length)];
                        if (villainName != null) {
                            System.out.println("You encountered Villain " + villainName + " HP: " + villain.HP + " Attack: " + villain.attack);
                            fight.battle(name);
                            break;
                        }
                        if (x == (-mapSize)) {
                            System.out.println("You have reached the border - levelling up!");
                            setVictoryMessage(name);
                            x = 1;
                            break;
                        }
                        break;
                    }
                    break;
                case 4:
                    while (x++ > 0) {
                        String villainName = villains[getRandom.nextInt(villains.length)];
                        if (villainName != null) {
                            System.out.println("You encountered Villain " + villainName + " HP: " + villain.HP + " Attack: " + villain.attack);
                            fight.battle(name);
                            break;
                        }
                        if (x == mapSize) {
                            System.out.println("You have reached the border - levelling up!");
                            setVictoryMessage(name);
                            x = 1;
                            break;
                        }
                        break;
                    }
                    break;
                case 5:
                    hero.displayStats(name);
                    break;
                default:
                    System.out.println("Make a selection from 0 to 5: ");
                    selection = userInput.nextInt();
                    break;
            }
        }
        selection = userInput.nextInt();
    }

    public boolean setVictoryMessage(String name) throws IOException {
        String selection = userInput.nextLine();
        System.out.println("Well done! Make a selection: \n");
        System.out.println("-----------------------------\n");
        System.out.println("\tPress 1 to continue\n");
        System.out.println("\tPress 2 to quit\n");

        selection = userInput.nextLine();
        if (selection.equals("1")) {
            new Navigation().moveDirection(PrintStats.getCoordinates(), name);
        }
        else if (selection.equals("2")) {
            System.exit(1);
        }
        else if (!selection.equals("1") && !selection.equals("2")) {
            System.out.println("Invalid option!");
            System.out.println("\tPress 1 to continue\n");
            System.out.println("\tPress 2 to quit\n");
        }
        return true;
    }
}
