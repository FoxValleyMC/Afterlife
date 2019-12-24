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

        // registers api methods
        api = new AfterLife();

        // registers MongoDB database methods
        if (this.getServer().getPluginManager().getPlugin("NukkitDB") != null) {
            new Mongodb(this, (NukkitDB.Main) this.getServer().getPluginManager().getPlugin("NukkitDB"));
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
        Mongodb.query("AtomizationYT", "name");
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
