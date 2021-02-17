package com.swingy.controller;

import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

import com.swingy.model.Map;
import com.swingy.model.characters.*;
import com.swingy.view.ConsoleView;

public class VillainEncounterController {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    Scanner userInput = new Scanner(System.in);
    Random random = new Random();
    Map newMap;
    private int run;

    public VillainEncounterController(Champion champion, Villain villain, Map map) throws  IOException {
        System.out.println("You've had an encounter with a " + villain.villainName + ".");
        System.out.println("HitPoints: " + villain.hitPoints);
        System.out.println("Make a choice: run / fight");
        String selection = userInput.nextLine();

        run = random.nextInt(2);
        if (selection.equalsIgnoreCase("run") && run == 1) {

            champion.y = champion.saveY;
            champion.x = champion.saveX;
            map.resetMap();
            map.updateMap(champion);
            map.printMap(champion);

            System.out.println("You managed to escape!");
        }
        else if (selection.equalsIgnoreCase("run") && run == 0) {
            System.out.println("You could not get away fast enough. Time for battle!");
            while (champion.hitPoints > 0 && villain.hitPoints > 0) {
                championAttack(champion, villain);
                villainAttack(champion, villain);
            }
            if (champion.hitPoints <= 0) {
                System.out.println("You have been defeated! Better luck next time.");
            }
            else if ( villain.hitPoints <= 0) {
                System.out.println("Well done! Villain defeated!");
                champion.experience += villain.experienceDrop;
            }
            if (champion.experience >= champion.level * 1000 + ((champion.level - 1) * (champion.level - 1)) * 450) {
                champion.level += 1;
            }
            if (villain.drop == 1) {
                System.out.println("Villain: " + villain.villainName + " dropped artifact: " + villain.artifact);
                if (villain.artifact.equalsIgnoreCase("Upgraded Armour")) {
                    champion.defense = champion.defense + villain.armour.defenseBoost;
                    System.out.println("You gained: "+ villain.armour.defenseBoost + " defense points.");

                } else if (villain.artifact.equalsIgnoreCase("Upgraded Helm")) {
                    champion.hitPoints = champion.hitPoints + villain.helm.hitPointsBoost;
                    System.out.println("You gained: "+ villain.helm.hitPointsBoost + " hit points.");

                } else if (villain.artifact.equalsIgnoreCase("Upgraded Weapon")) {
                    champion.attack = champion.attack + villain.weapon.attackBoost;
                    System.out.println("You gained: "+ villain.weapon.attackBoost + " attack points.");
                }
            }
        }
        else if (selection.equalsIgnoreCase("fight")) {
            while (champion.hitPoints > 0 && villain.hitPoints > 0) {
                championAttack(champion, villain);
                villainAttack(champion, villain);
                if (champion.hitPoints <= 0) {
                    System.out.println("You lost. Better luck next time!");
                }
                else if (villain.hitPoints <= 0) {
                    System.out.println("Congratulations! You have defeated the villain!");
                    champion.experience += villain.experienceDrop;
                    if (champion.experience >= champion.level * 1000 + ((champion.level - 1) * (champion.level - 1)) * 450) {
                        champion.level += 1;
                    }
                    if (villain.drop == 1) {
                        System.out.println("Villain: " + villain.villainName + " dropped artifact: " + villain.artifact);
                        if (villain.artifact.equalsIgnoreCase("Upgraded Armour")) {
                            champion.defense = champion.defense + villain.armour.defenseBoost;
                            System.out.println("You gained: "+ villain.armour.defenseBoost + " defense points.");

                        } else if (villain.artifact.equalsIgnoreCase("Upgraded Helm")) {
                            champion.hitPoints = champion.hitPoints + villain.helm.hitPointsBoost;
                            System.out.println("You gained: "+ villain.helm.hitPointsBoost + " hit points.");

                        } else if (villain.artifact.equalsIgnoreCase("Upgraded Weapon")) {
                            champion.attack = champion.attack + villain.weapon.attackBoost;
                            System.out.println("You gained: "+ villain.weapon.attackBoost + " attack points.");
                        }
                    }
                }
            }
        }
        else {
            System.out.println(RED + "Please enter a valid action: fight/run" + RESET);
            newMap = map;
            map.encounter(champion, map);
            map.resetMap();
            map.updateMap(champion);
            map.printMap(champion);

            while (champion.hitPoints > 0) {
                ConsoleView.championMove(champion, map);
            }
            if (champion.hitPoints <= 0) {
                System.out.println("--- GAME OVER ---");
            }
        }
    }

    public void championAttack(Champion champion, Villain villain) {
        int champDamage = random.nextInt(champion.attack);
        villain.hitPoints = villain.hitPoints - champDamage;
    }

    public void villainAttack(Champion champion, Villain villain) {
        int villainDamage = random.nextInt(villain.attack);
        if (villainDamage > champion.defense) {
            villainDamage = villainDamage - champion.defense;
        }
        else  {
            villainDamage = 0;
        }
        champion.hitPoints = champion.hitPoints - villainDamage;
    }
}
