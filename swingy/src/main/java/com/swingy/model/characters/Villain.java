package com.swingy.model.characters;

import com.swingy.model.artifacts.*;

import javax.validation.constraints.*;

public class Villain {

    @NotNull
    @NotBlank
    public String villainName;

    @NotNull
    @NotBlank
    public String artifact;
    public int drop;
    public int hitPoints;
    public int attack;
    public int x;
    public int y;
    public int experienceDrop;
    public Armour armour;
    public Helm helm;
    public Weapon weapon;
}

