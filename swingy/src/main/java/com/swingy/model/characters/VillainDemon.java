package com.swingy.model.characters;

import java.util.Random;
import com.swingy.model.artifacts.*;

public class VillainDemon extends Villain {
    Random random = new Random();
    int increaseDefense = random.nextInt(2) + 1;
    public VillainDemon() {
        villainName = "Dragon";
        artifact = "Upgraded Armour";
        drop = random.nextInt(2);
        hitPoints = 30;
        attack = 20;
        experienceDrop = 200;
        armour = new Armour(artifact, increaseDefense);
    }
}
