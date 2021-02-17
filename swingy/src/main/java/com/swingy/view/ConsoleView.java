package com.swingy.view;

import java.io.*;
import java.util.Scanner;
//import javax.validation.constraints.*;

import com.swingy.model.*;
import com.swingy.model.characters.*;
import com.swingy.controller.GameController;

public class ConsoleView {

    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    static Champion champion;
    static Map map;

    public static void main(String[] args) throws IOException {
        Scanner userInput = new Scanner(System.in);
        System.out.println("--- Welcome to SWINGY ---");
        System.out.println("new game[new] / load game[load]");
        System.out.println("Type 'Quit' to exit game.");

    
        String selection = userInput.nextLine();
        if (selection.equalsIgnoreCase("quit")) {
            System.exit(1);
        }
        if (selection.equalsIgnoreCase("new")) {
            championSelect();
            map = new Map(champion);
            while (champion.hitPoints > 0) {
                championMove(champion, map);
            }
            if (champion.hitPoints <= 0) {
                System.out.println("No more HP.\nGAME OVER");
            }
        } else if (selection.equalsIgnoreCase("load")) {
            selectPreviousChampion();
        }
    }

    public static void championSelect() throws IOException {
        ChampionHobbit hobbit = new ChampionHobbit();
        ChampionElf elf = new ChampionElf();
        ChampionDwarf dwarf = new ChampionDwarf();

        Scanner userInput = new Scanner(System.in);
        System.out.println("Choose 1 Champion Class: Hobbit, Elf or Dwarf");
        System.out.println("Hobbit - Attack: " + hobbit.attack + " Defense: " + hobbit.defense + " HitPoints: " + hobbit.hitPoints);
        System.out.println("Elf - Attack: " + elf.attack + " Defense: " + elf.defense + " HitPoints: " + elf.hitPoints);
        System.out.println("Dwarf - Attack: " + dwarf.attack + " Defense: " + dwarf.defense + " HitPoints: " + dwarf.hitPoints);
        String selection = userInput.nextLine();
        if (selection.equalsIgnoreCase("Hobbit")) {
            champion = new ChampionHobbit();
            System.out.println("Enter name: ");
            String name = userInput.nextLine();
            while (name.equalsIgnoreCase("")) ;
            {
                System.out.println("Enter name: ");
                name = userInput.nextLine();
            }
            champion.champName = name;
            System.out.println("Welcome, " + champion.champName + " to the game!");
        } else if (selection.equalsIgnoreCase("Elf")) {
            champion = new ChampionElf();
            System.out.println("Enter name: ");
            String name = userInput.nextLine();
            // while (name.equalsIgnoreCase("")) ;
            // {
            //     System.out.println("Enter name: ");
            //     name = userInput.nextLine();
            // }
            champion.champName = name;
            System.out.println("Welcome, " + champion.champName + " to the game!");
        }
        else if (selection.equalsIgnoreCase("Dwarf")) {
            champion = new ChampionDwarf();
            System.out.println("Enter name: ");
            String name = userInput.nextLine();
            while (name.equalsIgnoreCase("")); {
                System.out.println("Enter name: ");
                name = userInput.nextLine();
            }
            champion.champName = name;
            System.out.println("Welcome, " + champion.champName + " to the game!");
        }
        else {
            System.out.println(RED + "Enter VALID Champion Class: Hobbit, Elf or Dwarf." + RESET);
            championSelect();
            map = new Map(champion);
            while (champion.hitPoints > 0) {
                championMove(champion, map);
            }
            if (champion.hitPoints <= 0) {
                System.out.println("No more HP.\nGAME OVER");
            }
        }
        GameController.saveGame(champion);
    }
    public static void selectPreviousChampion() throws IOException {

        try {
            Scanner userInput = new Scanner(System.in);
            BufferedReader bufReader = new BufferedReader(new FileReader("src/main/java/com/swingy/save.txt"));
            String line = null;
            try {
                line = bufReader.readLine();
                System.out.println("--- Saved Champions ---");
                while (!line.contains("PositionX")) {
                    String stats = line.split(" ")[0];
                    String value = line.split(" ")[1];
                    System.out.println(stats + ": " + value);
                    line = bufReader.readLine();
                }
                System.out.println("\n");
                System.out.println("Do you want to continue with this Character? [yes/no]");
                String selection = userInput.nextLine();
                if (selection.equalsIgnoreCase("no"))
                    System.exit(0);
                else if (selection.equalsIgnoreCase("yes"))
                    GameController.loadGame();
                else System.exit(0);
            } catch (Exception e) {
                System.out.println("No Save File!");
                championSelect();
                map = new Map(champion);
                while (champion.hitPoints > 0) {
                    championMove(champion, map);
                }
                if (champion.hitPoints <= 0) {
                    System.out.println("No More HP.\nGAME OVER");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No Save File! Start New Game: ");
            championSelect();
            map = new Map(champion);
            while (champion.hitPoints > 0) {
                championMove(champion, map);
            };
            if (champion.hitPoints <= 0) {
                System.out.println("No More HP.\nGAME OVER");
            }
        }
    }

    public static void championMove(Champion champion, Map map) throws  IOException {
        System.out.println("Choose a direction: North, East, West South");
        Scanner userInput = new Scanner(System.in);

        String selection = userInput.nextLine();
        if (selection.equalsIgnoreCase("quit")) {
            System.exit(1);
        }
        if (selection.equalsIgnoreCase("north")) {
            if (champion.y - 1 < 0) {
                map = new Map(champion);
                System.out.println("--- New Map ---");
            }
            else {
                champion.saveX = champion.x;
                champion.saveY = champion.y;
                champion.y =champion.y - 1;
            }
            map.encounter(champion, map);
            map.resetMap();
            map.updateMap(champion);
            map.printMap(champion);
        }
        else if (selection.equalsIgnoreCase("east")) {
            if (champion.x + 1 >= map.size) {
                map = new Map(champion);
                System.out.println("--- New Map ---");
            }
            else {
                champion.saveX = champion.x;
                champion.saveY = champion.y;
                champion.x =champion.x + 1;
            }
            map.encounter(champion, map);
            map.resetMap();
            map.updateMap(champion);
            map.printMap(champion);
        }
        else if (selection.equalsIgnoreCase("west")) {
            if (champion.x - 1 < 0) {
                map = new Map(champion);
                System.out.println("--- New Map ---");
            }
            else {
                champion.saveX = champion.x;
                champion.saveY = champion.y;
                champion.x =champion.x - 1;
            }
            map.encounter(champion, map);
            map.resetMap();
            map.updateMap(champion);
            map.printMap(champion);
        }
        else if (selection.equalsIgnoreCase("south")) {
            if (champion.y + 1 >= map.size) {
                map = new Map(champion);
                System.out.println("--- New Map ---");
            }
            else {
                champion.saveX = champion.x;
                champion.saveY = champion.y;
                champion.y =champion.y + 1;
            }
            map.encounter(champion, map);
            map.resetMap();
            map.updateMap(champion);
            map.printMap(champion);
        }
        else {
            System.out.println(RED + "Please enter valid direction: north, east, west or south." + RESET);
            map.resetMap();
            map.updateMap(champion);
            map.printMap(champion);
        }
        GameController.saveGame(champion);
    }
}