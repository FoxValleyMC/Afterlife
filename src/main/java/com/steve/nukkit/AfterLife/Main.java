package com.steve.nukkit.AfterLife;

import cn.nukkit.command.CommandMap;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.plugin.PluginBase;
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
        this.getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
        this.getServer().getPluginManager().registerEvents(new CustomEvent(this), this);
        this.getServer().getPluginManager().registerEvents(new FormResponseEvent(), this);

        // creates connection to mongodb database
        Mongodb.connect();

        // registers api methods
        api = new AfterLife();

        // registers users stats profile
        for (Document document: Mongodb.getAll()) {
            this.getLogger().info("Loaded "+document.getString("name")+"'s Leaderboard");
        }

        // registers search form
        FormWindowCustom form = new FormWindowCustom("Search player profile");
        ElementInput input = new ElementInput("", "enter player's FULL name");
        form.addElement(input);
        forms.put("searchForm", form);
        System.out.println(forms);

    }

    @Override
    public void onDisable() {
        // stops connection to mongodb database
        Mongodb.close();
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
    public AfterLife Api() {
        return api;
    }
}
