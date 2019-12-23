package com.steve.nukkit.AfterLife.modules;

import cn.nukkit.Player;
import cn.nukkit.utils.TextFormat;
import com.steve.nukkit.AfterLife.AfterLife;
import com.steve.nukkit.AfterLife.Main;
import com.steve.nukkit.AfterLife.handler.Mongodb;

public class Experience {

    public void add(String uuid, Integer amount) {
        int xp = Mongodb.query(uuid, "uuid").getInteger("experience") + amount;
        int abs = Math.abs(xp - Main.getPlugin().getConfig().getInt("xp-levelup-amount"));

        if (xp >= Main.getPlugin().getConfig().getInt("xp-levelup-amount")) {
            AfterLife.levels.add(uuid, 1);
            Mongodb.update(uuid, "experience", abs);
        } else {
            Mongodb.update(uuid, "experience", xp);
        }
    }

    public void remove(String uuid, Integer amount) {
        int xp = Mongodb.query(uuid, "uuid").getInteger("experience") - amount;
        int abs = Math.abs(Mongodb.query(uuid, "uuid").getInteger("experience") - amount);
        int difference = Math.abs(Main.getPlugin().getConfig().getInt("xp-levelup-amount") - abs);

        int levels = Mongodb.query(uuid, "uuid").getInteger("levels");

        if (xp < 0) {
            if (levels > 0) {
                AfterLife.levels.remove(uuid, 1);
                Mongodb.update(uuid, "experience", difference);
            } else {
                Mongodb.update(uuid, "experience", 0);
            }
        } else {
            Mongodb.update(uuid, "experience", xp);
        }

    }

    public int get(String uuid) {
        return Mongodb.query(uuid, "uuid").getInteger("experience");
    }

}
