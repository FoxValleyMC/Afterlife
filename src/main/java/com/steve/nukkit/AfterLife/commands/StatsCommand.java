package com.steve.nukkit.AfterLife.commands;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginCommand;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowModal;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.utils.TextFormat;
import com.steve.nukkit.AfterLife.Main;
import com.steve.nukkit.AfterLife.handler.Mongodb;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class StatsCommand extends PluginCommand {

    private Main plugin;

    public StatsCommand(String name, Main plugin) {
        super(name, plugin);
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (sender instanceof Player) {

            switch (args.length) {
                case 0:
                    sendProfile((Player) sender, sender.getName());
                    break;
                case 1:
                    Player player = this.getPlugin().getServer().getPlayer(args[0]);
                    if (player != null) {
                        sendProfile((Player) sender, player.getName());
                    } else {
                        sendProfile((Player) sender, args[0]);
                    }
                    break;
            }
        } else {
            getPlugin().getLogger().error("command can only be run in-game");
        }

        return true;
    }

    private void sendProfile(Player sender, String player) {

        Document query = Mongodb.query(player, "name");

        try {
            switch (getPlugin().getConfig().getString("view-stats")) {
                case "standard":
                    sender.sendMessage(TextFormat.GREEN+"= "+TextFormat.YELLOW+query.getString("name")+"'s leaderboard!"+TextFormat.GREEN+" =");
                    sender.sendMessage(TextFormat.GREEN+"| "+TextFormat.GRAY+"Levels: "+TextFormat.WHITE+plugin.Api().GetLevels(query.getString("uuid")));
                    sender.sendMessage(TextFormat.GREEN+"| "+TextFormat.GRAY+"Experience: "+TextFormat.WHITE+plugin.Api().GetExperience(query.getString("uuid")));
                    sender.sendMessage(TextFormat.GREEN+"| "+TextFormat.GRAY+"Kills: "+TextFormat.WHITE+plugin.Api().GetKills(query.getString("uuid")));
                    sender.sendMessage(TextFormat.GREEN+"| "+TextFormat.GRAY+"Deaths: "+TextFormat.WHITE+plugin.Api().GetDeaths(query.getString("uuid")));
                    sender.sendMessage(TextFormat.GREEN+"= "+TextFormat.YELLOW+"Usage: /stats <name>"+TextFormat.GREEN+" =");
                    break;
                case "form":
                    String title = query.getString("name")+"'s Leaderboard";
                    String content =
                            "Levels: "+plugin.Api().GetLevels(query.getString("uuid"))+"\n"+
                            "Experience: "+plugin.Api().GetExperience(query.getString("uuid"))+"\n"+
                            "Kills: "+plugin.Api().GetKills(query.getString("uuid"))+"\n"+
                            "Deaths: "+plugin.Api().GetDeaths(query.getString("uuid"))+"\n";

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
