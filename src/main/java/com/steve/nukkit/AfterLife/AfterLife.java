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

    public String GetLevels(String uuid) {
        return  levels.get(uuid);
    }

    public String GetExperience(String uuid) {
        return experience.get(uuid);
    }

    public String GetKills(String uuid) {
        return  kills.get(uuid);
    }

    public String GetDeaths(String uuid) {
        return deaths.get(uuid);
    }
}
