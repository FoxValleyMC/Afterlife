package com.steve.nukkit.AfterLife;

import com.steve.nukkit.AfterLife.modules.Deaths;
import com.steve.nukkit.AfterLife.modules.Experience;
import com.steve.nukkit.AfterLife.modules.Kills;
import com.steve.nukkit.AfterLife.modules.Levels;

public class AfterLife {

    public static Kills kills;
    public static Deaths deaths;
    public static Experience experience;
    public static Levels levels;

    AfterLife() {
        kills = new Kills();
        deaths = new Deaths();
        experience = new Experience();
        levels = new Levels();
    }

    public int GetLevels(String uuid) {
        return  levels.get(uuid);
    }

    public int GetExperience(String uuid) {
        return experience.get(uuid);
    }

    public int GetKills(String uuid) {
        return  kills.get(uuid);
    }

    public int GetDeaths(String uuid) {
        return deaths.get(uuid);
    }
}
