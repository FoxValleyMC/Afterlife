package com.steve.nukkit.AfterLife.handler;

import cn.nukkit.Player;
import cn.nukkit.math.Vector3;
import com.steve.nukkit.AfterLife.Main;

import java.util.List;
import java.util.Map;

public class FloatingTextHandler {

    public static void addParticle(String type) {
        //Object title = Main.getPlugin().getConfig().get("texts-title");
        List<Map> title = Main.getPlugin().getConfig().getMapList("texts-title");
        System.out.println(title);
    }

}
