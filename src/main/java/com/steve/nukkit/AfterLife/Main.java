package com.steve.nukkit.AfterLife;

import PlayerAPI.Overrides.PlayerAPI;
import cn.nukkit.Player;
import cn.nukkit.command.CommandMap;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowModal;
import cn.nukkit.level.particle.FloatingTextParticle;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import com.steve.nukkit.AfterLife.commands.FloatingTextCommand;
import com.steve.nukkit.AfterLife.commands.StatsCommand;
import com.steve.nukkit.AfterLife.commands.testCommand;
import com.steve.nukkit.AfterLife.events.CustomEvent;
import com.steve.nukkit.AfterLife.events.FormResponseEvent;
import com.steve.nukkit.AfterLife.events.JoinEvent;
import com.steve.nukkit.AfterLife.handler.Mongodb;

import java.util.HashMap;
import java.util.Map;

public class Main extends PluginBase {

    private static Main plugin;
    private static AfterLife api;

    public Map<String, FloatingTextParticle> ftps = new HashMap<>();
    public Map<String, FormWindow> forms = new HashMap<>();

    public Config texts;

    @Override
    public void onLoad() {
        CommandMap commandMap = this.getServer().getCommandMap();
        commandMap.register("Afterlife", new testCommand("afterlife", this));
        commandMap.register("Afterlife", new StatsCommand("stats", this));
        commandMap.register("Afterlife", new FloatingTextCommand("setleaderboard", this));
    }

    @Override
    public void onEnable() {
        // registers plugin instance
        Main.plugin = this;

        // saves config.yml to resources
        this.saveDefaultConfig();

        // registers plugin event listeners
        this.getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
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

        // registers texts config data file
        saveResource("texts.yml");
        texts = new Config(getPlugin().getDataFolder()+"/texts.yml", Config.YAML);
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

    public void sendProfile(Player sender, String query) {

        PlayerAPI player = (PlayerAPI) getServer().getPlayer(query);

        try {
            switch (getPlugin().getConfig().getString("view-stats")) {
                case "standard":
                    sender.sendMessage(TextFormat.GREEN+"= "+TextFormat.YELLOW+player.getName()+"'s leaderboard!"+TextFormat.GREEN+" =");
                    sender.sendMessage(TextFormat.GREEN+"| "+TextFormat.GRAY+"Levels: "+TextFormat.WHITE+player.getLevels());
                    sender.sendMessage(TextFormat.GREEN+"| "+TextFormat.GRAY+"Experience: "+TextFormat.WHITE+player.getXp());
                    sender.sendMessage(TextFormat.GREEN+"| "+TextFormat.GRAY+"Most Kills: "+TextFormat.WHITE+player.getKills());
                    sender.sendMessage(TextFormat.GREEN+"| "+TextFormat.GRAY+"Highest Kill-streak: "+TextFormat.WHITE+player.getKillStreak());
                    sender.sendMessage(TextFormat.GREEN+"| "+TextFormat.GRAY+"Most Deaths: "+TextFormat.WHITE+player.getDeaths());
                    sender.sendMessage(TextFormat.GREEN+"= "+TextFormat.YELLOW+"Usage: /stats <name>"+TextFormat.GREEN+" =");
                    break;
                case "form":
                    String title = player.getName()+"'s Leaderboard";
                    String content =
                            "Levels: "+player.getLevels()+"\n"+
                            "Experience: "+player.getX()+"\n"+
                            "Most Kills: "+player.getKills()+"\n"+
                            "Highest Kill-streak: "+player.getKillStreak()+"\n"+
                            "Most Deaths: "+player.getDeaths()+"\n";

                    FormWindowModal form = new FormWindowModal(title, content, "search", "close");
                    sender.showFormWindow(form, 6);
                    break;
                default:
                    sender.sendMessage(TextFormat.RED+"An error occurred and could not display your game statistics!");
            }
        } catch (NullPointerException e) {
            sender.sendMessage(TextFormat.RED+"Player does not exist in our database! "+TextFormat.WHITE+"(maybe name is incorrectly spelled?)");
        }
    }
}
