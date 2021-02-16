package com.swingy.model;

import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.io.IOException;

import com.swingy.model.characters.Villain;
import com.swingy.model.characters.PrintStats;
import com.swingy.model.Navigation;
import com.swingy.model.storage.Storage;

public class Combat {

    Villain villain;
    PrintStats hero;

    Random getRandom = new Random();

    public Combat(PrintStats hero, Villain villain) {
        this.hero       = hero;
        this.villain    = villain;
    }

    public void battle(String name) throws IOException {
        Storage storage = new Storage(name);
        List<String> list = storage.loadData(name);
        int hp = storage.getHp();

        while (true) {
            Scanner selection = new Scanner(System.in);

            System.out.println("You've made an encounter. Choose one of three options: 'Run', 'Attack' or 'Quit'.");
            String userInput = selection.nextLine();
            while (true) {
                if (!userInput.equalsIgnoreCase("Run") && !userInput.equalsIgnoreCase("Attack") && !userInput.equalsIgnoreCase("Quit")) {
                    System.out.println("Invalid Selection!");
                    selection = new Scanner(System.in);
                    System.out.println("Do you want to 'Run', 'Attack' or 'Quit'?");
                    userInput = selection.nextLine();
                }
                else
                {
                    if (userInput.equalsIgnoreCase("Run"))
                    {
                        int run = (int)(Math.random() * 100 + 1);
                        if (run >= 50) {
                            System.out.println(run);
                            System.out.println(PrintStats.getCoordinates());
                            System.out.println("Successfully escaped!");
                            Navigation back = new Navigation();
                            back.moveDirection(PrintStats.getCoordinates(), name);
                            break;
                        }
                        else
                        {
                            System.out.println("If you run - survival is less than 50%");
                            System.out.println("Do you want to 'Run', 'Attack' or 'Quit'?");
                            userInput = selection.nextLine();

                            int villainHealth = getRandom.nextInt(villain.HP);

                            while (villainHealth > 0) {
                                int damageInflicted = getRandom.nextInt(storage.getAttack() + storage.getDefense());
                                int damageReceived = getRandom.nextInt(villain.attack);

                                villainHealth -= damageInflicted;
                                hp -= damageReceived;

                                System.out.println("\tYou struck villain: " + villain.type + " with " + damageInflicted + " damage!\n");
                                System.out.println("\tYou got hurt " + damageReceived + " in return.");

                                if (hp < 1) {
                                    System.out.println("You've been injured! Too weak to continue.");
                                    break;
                                }
                            }
                            if (hp < 1) {
                                hp = 0;
                                int oldXP = Integer.parseInt(list.toString().split(",")[3]);
                                int newXP = oldXP + (villain.HP * villain.attack);
                                storage.saveGameProgress(name, newXP, hp);
                                System.out.println("You lost! Try again next time!");
                                System.exit(1);
                            }
                            System.out.println("------------------------------------------------------------");
                            System.out.println("\t# " + villain.type + " was defeated!");
                            System.out.println("\t# HP left:" + hp);
                        }
                    }
                    else if (userInput.equalsIgnoreCase("Attack")) {
                        int villainHealth = getRandom.nextInt(villain.HP);

                        while (villainHealth > 0) {
                            int damageInflicted = getRandom.nextInt(storage.getAttack() + storage.getDefense());
                            int damageReceived = getRandom.nextInt(villain.attack);

                            villainHealth -= damageInflicted;
                            hp -= damageReceived;

                            System.out.println("\tYou struck villain: " + villain.type + " with " + damageInflicted + " damage!\n");
                            System.out.println("\tYou got hurt " + damageReceived + " in return.");

                            if (hp < 1) {
                                hp = 0;
                                System.out.println("You are injured! Too weak to continue.");
                                break;
                            }
                        }
                        if (hp < 1) {
                            hp = 0;

                            int oldXP = Integer.parseInt(list.toString().split(",")[3]);
                            int newXP = oldXP + (villain.HP * villain.attack);

                            storage.saveGameProgress(name, newXP, hp);
                            System.out.println("You lost!\n");
                            System.out.println(name + ", you have lost all HP. Create new game to play again.");
                            System.out.println("Exiting...");
                            System.exit(1);
                        }
                        System.out.println("------------------------------------------------------------");
                        System.out.println("# " + villain.type + " has been defeated. Congratulations!");
                        System.out.println("# You have " + hp + " HP remaining.");

                        int oldXP = Integer.parseInt(list.toString().split(",")[3]);
                        int newXP = oldXP + (villain.HP * villain.attack);
                        storage.saveGameProgress(name, newXP, hp);
                        Navigation back = new Navigation();
                        back.moveDirection(PrintStats.getCoordinates(), name);
                        break;
                    }
                    else if (userInput.equalsIgnoreCase("Quit")){
                        System.exit(1);
                    }
                }
            }
            selection.close();
        }
    }
}