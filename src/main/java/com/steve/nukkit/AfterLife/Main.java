package com.steve.nukkit.AfterLife;

import cn.nukkit.Player;
import cn.nukkit.command.CommandMap;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowModal;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;
import com.steve.nukkit.AfterLife.commands.StatsCommand;
import com.steve.nukkit.AfterLife.commands.testCommand;
import com.steve.nukkit.AfterLife.events.CustomEvent;
import com.steve.nukkit.AfterLife.events.FormResponseEvent;
import com.steve.nukkit.AfterLife.events.JoinEvent;
import com.steve.nukkit.AfterLife.handler.Mongodb;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

public class Main extends PluginBase {

    private static Main plugin;
    private static AfterLife api;

    public Map<String, FormWindow> forms = new HashMap<>();

    @Override
    public void onLoad() {
        CommandMap commandMap = this.getServer().getCommandMap();
        commandMap.register("Afterlife", new testCommand("afterlife", this));
        commandMap.register("Afterlife", new StatsCommand("stats", this));
    }

    @Override
    public void onEnable() {
        // registers plugin instance
        Main.plugin = this;

        // saves config.yml to resources
        this.saveDefaultConfig();

        // registers plugin event listeners
        this.getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        this.getServer().getPluginManager().registerEvents(new CustomEvent(this), this);
        this.getServer().getPluginManager().registerEvents(new FormResponseEvent(this), this);

        // registers api methods
        api = new AfterLife();

        // registers MongoDB database methods
        if (this.getServer().getPluginManager().getPlugin("NukkitDB") != null) {
            new Mongodb(this);
        } else {
            this.getLogger().error("NukkitDB plugin library is not installed!");
            this.getLogger().error("Plugin will not function as intended... (disabling)");
            this.getServer().getPluginManager().disablePlugin(this);
        }

        // registers search form
        FormWindowCustom form = new FormWindowCustom("Search player profile");
        ElementInput input = new ElementInput("", "enter player's FULL name");
        form.addElement(input);
        forms.put("searchForm", form);
    }

    /**
     * @apiNote returns plugin instance
     * @return Main
     */
    public static Main getPlugin() {
        return Main.plugin;
    }

    /**
     * @apiNote returns api methods
     * @return AfterLife
     */
    private AfterLife Api() {
        return api;
    }

    public void sendProfile(Player sender, String player) {

        Document query = Mongodb.query(player, "name");

        try {
            switch (getPlugin().getConfig().getString("view-stats")) {
                case "standard":
                    sender.sendMessage(TextFormat.GREEN+"= "+TextFormat.YELLOW+query.getString("name")+"'s leaderboard!"+TextFormat.GREEN+" =");
                    sender.sendMessage(TextFormat.GREEN+"| "+TextFormat.GRAY+"Levels: "+TextFormat.WHITE+Api().GetLevels(query.getString("uuid")));
                    sender.sendMessage(TextFormat.GREEN+"| "+TextFormat.GRAY+"Experience: "+TextFormat.WHITE+Api().GetExperience(query.getString("uuid")));
                    sender.sendMessage(TextFormat.GREEN+"| "+TextFormat.GRAY+"Most Kills: "+TextFormat.WHITE+Api().GetKills(query.getString("uuid")));
                    sender.sendMessage(TextFormat.GREEN+"| "+TextFormat.GRAY+"Highest Kill-streak: "+TextFormat.WHITE+Api().GetStreaks(query.getString("uuid")));
                    sender.sendMessage(TextFormat.GREEN+"| "+TextFormat.GRAY+"Most Deaths: "+TextFormat.WHITE+Api().GetDeaths(query.getString("uuid")));
                    sender.sendMessage(TextFormat.GREEN+"= "+TextFormat.YELLOW+"Usage: /stats <name>"+TextFormat.GREEN+" =");
                    break;
                case "form":
                    String title = query.getString("name")+"'s Leaderboard";
                    String content =
                            "Levels: "+Api().GetLevels(query.getString("uuid"))+"\n"+
                            "Experience: "+Api().GetExperience(query.getString("uuid"))+"\n"+
                            "Most Kills: "+Api().GetKills(query.getString("uuid"))+"\n"+
                            "Highest Kill-streak: "+Api().GetStreaks(query.getString("uuid"))+"\n"+
                            "Most Deaths: "+Api().GetDeaths(query.getString("uuid"))+"\n";

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
