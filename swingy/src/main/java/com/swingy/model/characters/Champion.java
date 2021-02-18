package com.swingy.model.characters;
import javax.validation.constraints.*;

public class Champion {

    @NotNull
    @NotBlank
    public String champName = null;
    
    @NotNull
    @NotBlank
    public String champClass = null;
    public int level;
    public int experience;
    public int attack;
    public int defense;
    public int hitPoints;
    public int x;
    public int y;
    public int saveX;
    public int saveY;
}
