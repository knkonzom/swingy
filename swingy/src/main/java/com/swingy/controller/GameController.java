package com.swingy.controller;


import java.io.*;

import com.swingy.model.*;
import com.swingy.model.characters.*;
import com.swingy.view.ConsoleView;

public class GameController {
    public static PrintWriter writer;

    public static void saveGame (Champion champion) throws IOException {
        writer = new PrintWriter(new FileWriter("src/main/java/com/swingy/save.txt"));
        writer.println("Class " + champion.champClass);
        writer.println("Name " + champion.champName);
        writer.println("Level " + champion.level);
        writer.println("Attack " + champion.attack);
        writer.println("Defense " + champion.defense);
        writer.println("Experience " + champion.experience);
        writer.println("Hit Points " + champion.hitPoints);
        writer.println("PositionX " + (champion.x + 1));
        writer.println("PositionY " + (champion.y + 1));
        writer.println("DONE");
        writer.close();
    }

    public static void loadGame () throws IOException {
        BufferedReader bufReader = new BufferedReader(new FileReader("src/main/java/com/swingy/save.txt"));
        String line = bufReader.readLine();
        Champion champion = new Champion();

        while (!line.equalsIgnoreCase("DONE")) {
            if (line.split(" ")[0].equalsIgnoreCase("Class")) {
                String type = line.split(" ")[1];
                if (type.equalsIgnoreCase("Dwarf")) {
                    champion = new ChampionDwarf();
                } else if (type.equalsIgnoreCase("Elf")) {
                    champion = new ChampionElf();
                } else if (type.equalsIgnoreCase("Hobbit")) {
                    champion = new ChampionHobbit();
                }
                champion.champClass = line.split(" ")[1];
            }
            if (line.split(" ")[0].equalsIgnoreCase("Name"))
                champion.champName = line.split(" ")[1];

            if (line.split(" ")[0].equalsIgnoreCase("Level"))
                champion.level = Integer.parseInt(line.split(" ")[1]);

            if (line.split(" ")[0].equalsIgnoreCase("Attack"))
                champion.attack = Integer.parseInt(line.split(" ")[1]);

            if (line.split(" ")[0].equalsIgnoreCase("Defense"))
                champion.defense = Integer.parseInt(line.split(" ")[1]);

            if (line.split(" ")[0].equalsIgnoreCase("Experience"))
                champion.experience = Integer.parseInt(line.split(" ")[1]);

            if (line.split(" ")[0].equalsIgnoreCase("Hit Points"))
                champion.hitPoints = Integer.parseInt(line.split(" ")[1]);

            if (line.split(" ")[0].equalsIgnoreCase("PositionX"))
                champion.x = Integer.parseInt(line.split(" ")[1]);

            if (line.split(" ")[0].equalsIgnoreCase("PositionY"))
                champion.y = Integer.parseInt(line.split(" ")[1]);
            line = bufReader.readLine();
        }

        Map map = new Map(champion);
        while (champion.hitPoints > 0) {
            ConsoleView.championMove(champion, map);
        }
        if (champion.hitPoints <= 0) {
            System.out.println("You lose - GAME OVER!");
        }
    }
}
