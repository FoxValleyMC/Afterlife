package com.steve.nukkit.AfterLife.events;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import com.steve.nukkit.AfterLife.Main;
import com.steve.nukkit.AfterLife.handler.Mongodb;
import org.bson.Document;

public class JoinEvent implements Listener {

    private Main plugin;

    public JoinEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();

        if (Mongodb.query(uuid, "uuid") == null) {
            Document document = new Document();
            document.append("uuid", uuid);
            document.append("kills", 0);
            document.append("deaths", 0);
            document.append("experience", 0);
            document.append("levels", 0);
            document.append("name", player.getName());
            Mongodb.createNew(document);
        }

    }

}
