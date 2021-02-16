package com.swingy.model.characters;

import com.swingy.model.weaponfactory.Armour;
import com.swingy.model.weaponfactory.Knife;
import com.swingy.model.weaponfactory.SilverArmour;
import com.swingy.model.weaponfactory.Weapon;
//import javax.validation.constraints.NotNull;

public class Hero {

    public String type = this.getClass().getSimpleName();
    public int level = 1;
    public int experience = 1000;
    public int HP = 40 * this.level;
    public Weapon knife = new Knife();
    public Armour silverArmour = new SilverArmour();
    public String armourName = silverArmour.name;
    public String weaponName = knife.name;
    public int attack = knife.attack;
    public int defense = silverArmour.defense;

    public int getHP() {
        return HP;
    }
}
