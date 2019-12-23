package com.steve.nukkit.AfterLife.events;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.response.FormResponse;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.response.FormResponseModal;
import cn.nukkit.form.window.FormWindowModal;
import cn.nukkit.utils.TextFormat;
import com.steve.nukkit.AfterLife.Main;
import com.steve.nukkit.AfterLife.handler.Mongodb;
import org.bson.Document;

public class FormResponseEvent implements Listener {

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
                sendProfile(player, customResponse.getInputResponse(0));
            }
        }
    }

    /**
     * TODO: 12/22/2019
     * remove duplicate code from `StatsCommand.java`
     */
    private void sendProfile(Player sender, String player) {

        Document query = Mongodb.query(player, "name");

        try {
            switch (Main.getPlugin().getConfig().getString("view-stats")) {
                case "standard":
                    sender.sendMessage(TextFormat.GREEN+"= "+TextFormat.YELLOW+query.getString("name")+"'s leaderboard!"+TextFormat.GREEN+" =");
                    sender.sendMessage(TextFormat.GREEN+"| "+TextFormat.GRAY+"Levels: "+TextFormat.WHITE+Main.getPlugin().Api().GetLevels(query.getString("uuid")));
                    sender.sendMessage(TextFormat.GREEN+"| "+TextFormat.GRAY+"Experience: "+TextFormat.WHITE+Main.getPlugin().Api().GetExperience(query.getString("uuid")));
                    sender.sendMessage(TextFormat.GREEN+"| "+TextFormat.GRAY+"Kills: "+TextFormat.WHITE+Main.getPlugin().Api().GetKills(query.getString("uuid")));
                    sender.sendMessage(TextFormat.GREEN+"| "+TextFormat.GRAY+"Deaths: "+TextFormat.WHITE+Main.getPlugin().Api().GetDeaths(query.getString("uuid")));
                    sender.sendMessage(TextFormat.GREEN+"= "+TextFormat.YELLOW+"Usage: /stats <name>"+TextFormat.GREEN+" =");
                    break;
                case "form":
                    String title = query.getString("name")+"'s Leaderboard";
                    String content =
                            "Levels: "+Main.getPlugin().Api().GetLevels(query.getString("uuid"))+"\n"+
                            "Experience: "+Main.getPlugin().Api().GetExperience(query.getString("uuid"))+"\n"+
                            "Kills: "+Main.getPlugin().Api().GetKills(query.getString("uuid"))+"\n"+
                            "Deaths: "+Main.getPlugin().Api().GetDeaths(query.getString("uuid"))+"\n";

                    FormWindowModal form = new FormWindowModal(title, content, "search", "close");
                    sender.showFormWindow(form, 0);
                    break;
                default:
                    sender.sendMessage(TextFormat.RED+"An error occurred and could not display your game statistics!");
            }
        } catch (NullPointerException e) {
            sender.sendMessage(TextFormat.RED+"Player does not exist in our database! "+TextFormat.WHITE+"(maybe name is incorrectly spelled?)");
        }
    }
}
