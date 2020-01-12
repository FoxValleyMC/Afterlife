package com.steve.nukkit.AfterLife.events;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.response.FormResponse;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.response.FormResponseModal;
import com.steve.nukkit.AfterLife.Main;

public class FormResponseEvent implements Listener {

    private Main plugin;

    public FormResponseEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void onFormSubmit(PlayerFormRespondedEvent event) {
        Player player = event.getPlayer();
        FormResponse response = event.getResponse();
        if (response instanceof FormResponseModal) {
            FormResponseModal modalResponse = (FormResponseModal) response;
            if (event.getFormID() == 0 && modalResponse.getClickedButtonText().equals("search")) {
                player.showFormWindow(Main.getPlugin().forms.get("searchForm"),1);
            }
        }

        if (response instanceof FormResponseCustom) {
            FormResponseCustom customResponse = (FormResponseCustom) response;
            if (event.getFormID() == 1) {
                Player playerType = Server.getInstance().getPlayer(customResponse.getInputResponse(0));
                if (playerType != null) {
                    plugin.sendProfile(player, playerType.getName());
                } else {
                    plugin.sendProfile(player, customResponse.getInputResponse(0));
                }
            }
        }
    }
}
