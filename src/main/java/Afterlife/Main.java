package Afterlife;

import Afterlife.events.DamageEvent;
import Afterlife.events.JoinEvent;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.level.particle.FloatingTextParticle;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

import java.util.HashMap;
import java.util.Map;

public class Main extends PluginBase {

    private static Main instance;

    public Config texts;

    public Map<String, FloatingTextParticle> ftps = new HashMap<>();
    public Map<String, FormWindow> forms = new HashMap<>();

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {
        // registers plugin instance
        instance = this;

        // saves config.yml to resources
        saveDefaultConfig();

        // registers plugin event listeners
        getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
        getServer().getPluginManager().registerEvents(new DamageEvent(this), this);

        // registers search form
        FormWindowCustom form = new FormWindowCustom("Search player profile");
        ElementInput input = new ElementInput("", "enter player's FULL name");
        form.addElement(input);
        forms.put("searchForm", form);

        // registers texts config data file
        saveResource("texts.yml");
        texts = new Config(this.getDataFolder()+"/texts.yml", Config.YAML);

    }

    public static Main getInstance() {
        return instance;
    }
}
