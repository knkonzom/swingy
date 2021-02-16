package com.swingy.model.characters;

import java.io.IOException;
import java.util.List;

import com.swingy.model.storage.Storage;

public class Villain {

    List<String> list;
    Storage level1;
    public String type;
    public int HP;
    public int attack;
    public int level;

    public Villain(String name) throws IOException {
        level1 = new Storage(name);
        list = level1.loadData(name);
        this.type = this.getClass().getSimpleName();
        this.HP = 20 * Integer.parseInt(list.toString().split(",")[2]);
        this.attack = 11 * Integer.parseInt(list.toString().split(",")[2]);
        this.level = Integer.parseInt(list.toString().split(",")[2]);
    }

    public int getHP() {
        return HP;
    }

    public int getAttack() {
        return attack;
    }

    public int getLevel() {
        return level;
    }

    public String getType() {
        return type;
    }
}
