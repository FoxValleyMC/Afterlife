package com.steve.nukkit.AfterLife.events;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import com.steve.nukkit.AfterLife.handler.Mongodb;

import java.util.HashMap;
import java.util.Map;

public class JoinEvent implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();

        if (Mongodb.query(uuid, "uuid") == null) {
            Map<String, Object> objectMap = new HashMap<String, Object>();
            objectMap.put("uuid", uuid);
            objectMap.put("name", player.getName().toLowerCase());
            objectMap.put("kills", 0);
            objectMap.put("kill-streak", 0);
            objectMap.put("deaths", 0);
            objectMap.put("experience", 0);
            objectMap.put("global-xp", 0);
            objectMap.put("levels", 0);
            Mongodb.createNew(objectMap);
        }

    }

}
