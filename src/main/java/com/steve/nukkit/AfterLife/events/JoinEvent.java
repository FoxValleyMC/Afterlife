package com.steve.nukkit.AfterLife.events;

import cn.nukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;

public class JoinEvent implements Listener {

//    private Main plugin;
//
//    public JoinEvent(Main plugin) {
//        this.plugin = plugin;
//    }
//
//    @EventHandler(priority = EventPriority.NORMAL)
//    public void onJoin(PlayerJoinEvent event) {
//        PlayerAPI player = (PlayerAPI) event.getPlayer();
//        String uuid = player.getUniqueId().toString();
//
//        if (Mongodb.query(uuid, "uuid") == null) {
//            Map<String, Object> objectMap = new HashMap<>();
//            objectMap.put("uuid", uuid);
//            objectMap.put("name", player.getName().toLowerCase());
//            objectMap.put("kills", 0);
//            objectMap.put("kill-streak", 0);
//            objectMap.put("deaths", 0);
//            objectMap.put("experience", 0);
//            objectMap.put("global-xp", 0);
//            objectMap.put("levels", 0);
//            Mongodb.createNew(objectMap);
//        }
//
//        // render leaderboards
//        Map<String, Object> objectMap = plugin.texts.getAll();
//        if (!objectMap.isEmpty()) {
//            for (String key : objectMap.keySet()){
//                Map<String, Object> dataMap = (Map<String, Object>) plugin.texts.get(key);
//                Level level = plugin.getServer().getLevelByName(dataMap.get("level").toString());
//                double x = (double) dataMap.get("x");
//                double y = (double) dataMap.get("y");
//                double z = (double) dataMap.get("z");
//                Position position = new Position(x, y+1.7, z, level);
//                FloatingTextHandler.addParticle(position, key, player);
//            }
//        }
//    }
}
