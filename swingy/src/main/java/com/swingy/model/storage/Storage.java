package com.swingy.model.storage;

import com.swingy.model.characters.Hero;
import java.io.IOException;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;

import java.util.ArrayList;
import java.util.List;


public class Storage {

    String name, type, weapon, armour;
    int level, experience, attack, defense, hp;

    public Storage(String name) {
        this.name       =   name;
        this.type       =   new Hero().type;
        this.weapon     =   new Hero().weaponName;
        this.armour     =   new Hero().armourName;
        this.level      =   new Hero().level;
        this.experience =   new Hero().experience;
        this.attack     =   new Hero().attack;
        this.defense    =   new Hero().defense;
    }

    public void storeData(String name) throws IOException {
        File save = new File("save.txt");
        FileWriter writer = new FileWriter(save, true);
        BufferedReader buf = new BufferedReader((new FileReader("save.txt")));
        String line;

        while ((line = buf.readLine()) != null) {
            if (this.name == null) {
                System.out.println("No name entered!");
                System.exit(1);
            }
            if (line.contains(this.name)) {
                System.out.println("The name [" + this.name + "] " + "in use.\nEnter a different name: " );
                System.exit(1);
            }
        }
        writer.write(this.name + ", " + this.type + ", " + this.experience + ", " + this.weapon + ", " + this.armour + ", " + this.hp + ", " + this.attack + ", " + this.defense + ".\n");
        buf.close();
        writer.close();
    }

    public List<String> loadData(String name) throws IOException {
        BufferedReader buf = new BufferedReader(new FileReader("save.txt"));
        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();

        String line;

        while ((line = buf.readLine()) != null) {
            if (line.contains(this.name)) {
                list1.add(line);
                list.add(line.split(",")[0]);
            }
        }
        if (!list.contains(this.name)) {
            System.out.println("The name [" + this.name + "] " + "does not exists. Check your spelling or start new game.");
            System.exit(0);
        }
        buf.close();
        return list1;
    }

    private void levelUp() {
        int totalLevel = this.level * 1000 + ((this.level - 1) * (this.level - 1)) * 450;
        totalLevel =+ this.experience;

        if (this.level == 1 && totalLevel > 2450) {
            if (totalLevel > 2450 && this.hp != 0) {
                this.level      = this.level + 1;
                this.defense    = this.defense + 10;
                this.attack     = this.attack + 10;
            }
        }
        else if (this.level == 2 && totalLevel > 4800) {
            if (totalLevel >4800 && this.hp != 0) {
                this.level      = this.level + 1;
                this.defense    = this.defense + 10;
                this.attack     = this.attack + 10;
            }
        }
        else if (this.level == 3 && totalLevel > 8050) {
            if (totalLevel > 8050 && this.hp != 0) {
                this.level      = this.level + 1;
                this.defense    = this.defense + 10;
                this.attack     = this.attack + 10;
            }
        }
    }

    public void saveGameProgress(String name, int experience1, int hp) throws IOException {
        BufferedReader buf = new BufferedReader(new FileReader("save.txt"));
        List<String> list = new ArrayList<>();

        this.experience = experience1;
        this.hp = hp;

        String line;

        levelUp();
        while((line = buf.readLine()) != null) {
            if (line.contains(this.name)) {
                line = line.replaceAll(line, this.name + ", " + this.type + ", " + this.experience + ", " + this.weapon + ", " + this.armour + ", " + this.hp + ", " + this.attack + ", " + this.defense + ".\n");
            }
            list.add(line);
        }
        buf.close();

        File save = new File("save.txt");
        save.delete();
        FileWriter writer = new FileWriter(save.getAbsoluteFile(), true);
        for (int i = 0; i < list.size(); i++) {
            if (!(Integer.parseInt(list.get(i).split(",")[6]) == 0)) {
                writer.write(list.get(i));
                writer.write("\n");
            }
        }
        writer.close();
    }

    public String getArmour() {
        return armour;
    }
    public void setArmour(String armour){
        this.armour = armour;
    }

    public String getName() {
        return name;
    }
    public void setName() {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public int getExperience(int experience) {
        return experience;
    }
    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getWeapon() {
        return weapon;
    }
    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public int getAttack() {
        return attack;
    }
    public int getDefense() {
        return defense;
    }
}
