package com.swingy.model.characters;

import java.util.Random;
import com.swingy.model.artifacts.*;

public class VillainWitch extends Villain {
    Random random = new Random();
    int increaseHP = random.nextInt(2) + 1;
    public VillainWitch() {
        villainName = "Witch";
        artifact = "Upgraded Helm";
        drop = random.nextInt(2);
        hitPoints = 20;
        attack = 15;
        experienceDrop = 100;
        helm = new Helm(artifact, increaseHP);
    }
}
