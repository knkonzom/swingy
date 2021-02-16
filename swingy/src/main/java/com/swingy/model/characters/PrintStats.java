package com.swingy.model.characters;

import java.io.IOException;
import java.util.List;

import com.swingy.model.storage.Storage;

public class PrintStats {

    String name;
    String type;
    int hp;
    int level = 1;
    int experience;
    static int mapSize;
    int attack;
    int defense;
    List<String> list;

    public PrintStats(String name) throws IOException {
        mapSize = (this.level - 1) * 5 + 10 - (this.level % 2);
    }

    public void displayStats(String name) throws IOException {
        Storage data = new Storage(name);

        list = data.loadData(name);
        System.out.println("\t\t\tGame Stats: \t\t\t\n");
        System.out.println(("\t\t\t------------------------"));
        System.out.println("\t\t\tName:\t\t\t" + list.toString().split(",")[0]);
        System.out.println("\t\t\tHP:\t\t\t" + list.toString().split(",")[6]);
        System.out.println("\t\t\tLevel:\t\t\t" + list.toString().split(",")[2]);
        System.out.println("\t\t\tAttack:\t\t\t" + list.toString().split(",")[7]);
        System.out.println("\t\t\tDefense:\t\t\t" + list.toString().replace("]", "").split(",")[8]);
        System.out.println("\t\t\tExperience:\t\t\t" + list.toString().split(",")[3]);
        System.out.println("\t\t\tMap Size:\t\t\t" + mapSize);
    }

    public static int getCoordinates() {
        return mapSize;
    }
}
