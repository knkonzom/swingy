package com.swingy.model.characters;

import java.util.Random;
import com.swingy.model.artifacts.*;

public class VillainOrc extends Villain {
    Random random = new Random();
    int increaseAttack = random.nextInt(2) + 1;
    public VillainOrc() {
        villainName = "Orc";
        artifact = "Upgraded Weapon";
        drop = random.nextInt(2);
        hitPoints = 15;
        attack = 25;
        experienceDrop = 75;
        weapon = new Weapon(artifact, increaseAttack);
    }
}
