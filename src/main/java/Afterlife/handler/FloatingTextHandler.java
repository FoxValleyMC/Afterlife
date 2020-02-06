package Afterlife.handler;

import Afterlife.Main;
import Afterlife.modules.GetData;
import Afterlife.utils.Colorize;
import PlayerAPI.Overrides.PlayerAPI;
import cn.nukkit.Player;
import cn.nukkit.level.particle.FloatingTextParticle;
import cn.nukkit.math.Vector3;

import java.util.Map;

public class FloatingTextHandler {

    public static void addParticle(Vector3 location, String type, PlayerAPI player) {
        Map<String, Object> objectMap = (Map<String, Object>) Main.getInstance().getConfig().get("texts-title");
        String title = objectMap.get(type).toString();
        FloatingTextParticle particle = new FloatingTextParticle(location, Colorize.Register(title)+"\n"+ GetData.GetLeaderboard(type));
        player.getLevel().addParticle(particle);
        Main.getInstance().ftps.put(type, particle);
    }

}
