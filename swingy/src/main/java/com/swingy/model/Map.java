package com.swingy.model;

import com.swingy.controller.VillainEncounterController;
import com.swingy.model.characters.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Map {

    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_RESET = "\u001B[0m";

    static public int size;
    static public int [][] map;
    static Villain villain;
    static VillainEncounterController encounter;
    ArrayList<Villain> villains = new ArrayList<Villain>();
    ArrayList<Villain> toRemove = new ArrayList<Villain>();

    public Map(Champion champion) {
        size = (champion.level - 1) * 5 + 10 - (champion.level % 2);
        map = new int[size][size];
        champion.x = (size / 2);
        champion.y = (size / 2);
        villainPosition(champion);
    }

    public void villainPosition(Champion champion) {
        Random random = new Random();
        int villainCount = 3 * size;
        int counter = 0;
        int typeCounter = 0;
        int x;
        int y;

        while (counter < villainCount) {
            x = random.nextInt(size);
            y = random.nextInt(size);
            while ( (x == champion.x && y == champion.y)) {
                x = random.nextInt(size);
                y = random.nextInt(size);
            }
            for (Villain vil : villains) {
                while ( x == vil.x && y == vil.y) {
                    x = random.nextInt(size);
                    y = random.nextInt(size);
                    while (x == champion.x && y == champion.y) {
                        x = random.nextInt(size);
                        y = random.nextInt(size);
                    }
                }
            }
            if ((typeCounter < villainCount / 3)) {
                villain = new VillainDemon();
                villain.x = x;
                villain.y = y;
                villains.add(villain);
                typeCounter++;
            }
            else if ((typeCounter >= villainCount / 3) && (typeCounter < (villainCount / 3 + villainCount /3))) {
                villain = new VillainOrc();
                villain.x = x;
                villain.y = y;
                villains.add(villain);
                typeCounter++;
            }
            else if ((typeCounter >= (villainCount / 3 + villainCount / 3)) && (typeCounter < villainCount)) {
                villain = new VillainWitch();
                villain.x = x;
                villain.y = y;
                villains.add(villain);
                typeCounter++;
            }
            counter++;
        }
        updateMap(champion);
        printMap(champion);
    }

    public void updateMap(Champion champion) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == champion.y && j == champion.x) {
                    map[i][j] = 2;
                }
                for (Villain vil : villains) {
                    if (i == vil.y && j == vil.x) {
                        if (map[i][j] == 2) {

                        } else {
                            map[i][j] = 1;
                        }
                    }
                    else if (map[i][j] == 1 || map[i][j] == 2) {

                    }
                    else
                        {
                            map[i][j] = 0;
                        }
                    }
                }
            }
        }

        public void printMap(Champion champion) {
            System.out.println("\n");
            System.out.println(champion.champName + "[" + champion.champClass + "] " + "\t" + "HitPoints: " + champion.hitPoints);
            System.out.println("Level: " + champion.level + "\t" + "Defense: " + champion.defense);
            System.out.println("Experience: " + champion.experience + "\t" + "Attack: " + champion.attack);
            System.out.println("\n");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (map[i][j] == 1) {
                        System.out.print(TEXT_RED + map[i][j] + TEXT_RESET + " ");
                    }
                    else if (map[i][j] == 2) {
                        System.out.print(TEXT_GREEN + map[i][j] + TEXT_RESET + " ");
                    }
                    else {
                        System.out.print(map[i][j] + " ");
                    }
                }
                System.out.println("\n");
            }
        }

        public void resetMap() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = 0;
            }
        }
    }

    public void encounter(Champion champion, Map runMap) throws IOException {
        if (map[champion.y][champion.x] == 1) {
            for (Villain vil: villains) {
                if (vil.y == champion.y && vil.x == champion.x) {
                    encounter = new VillainEncounterController(champion, vil, runMap);
                    if (vil.hitPoints <= 0) {
                        toRemove.add(vil);
                    }
                }
            }
            villains.removeAll(toRemove);
        }

    }

}

