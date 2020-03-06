package Afterlife;

import Afterlife.API.AfterlifeAPI;
import Afterlife.commands.FloatingTextCommand;
import Afterlife.commands.StatsCommand;
import Afterlife.events.DamageEvent;
import Afterlife.events.FormResponseEvent;
import Afterlife.events.JoinEvent;
import FormAPI.api.FormAPI;
import PlayerAPI.Overrides.PlayerAPI;
import cn.nukkit.command.CommandMap;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowModal;
import cn.nukkit.level.particle.FloatingTextParticle;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

import java.util.HashMap;
import java.util.Map;

public class Main extends PluginBase {

    private static Main instance;

    public Config texts;
    public FormAPI formAPI = new FormAPI();

    public Map<String, FloatingTextParticle> ftps = new HashMap<>();

    @Override
    public void onLoad() {
        CommandMap commandMap = getServer().getCommandMap();
        commandMap.register("Afterlife", new FloatingTextCommand("setleaderboard", this));
        commandMap.register("Afterlife", new StatsCommand("stats", this));
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
        getServer().getPluginManager().registerEvents(new FormResponseEvent(this), this);

        // registers search form
        FormWindowCustom windowCustom = new FormWindowCustom("Search player profile");
        ElementInput input = new ElementInput("Enter online player name", "xBox username or server alias");
        windowCustom.addElement(input);
        formAPI.add("searchWindow", windowCustom);

        // registers texts config data file
        saveResource("texts.yml");
        texts = new Config(this.getDataFolder()+"/texts.yml", Config.YAML);

        // disable if config empty
        if (!getConfig().getBoolean("use-MongoDB") || getConfig().getString("collection").isEmpty()) {
            getLogger().error("Please edit config");
            getServer().getPluginManager().disablePlugin(this);
        }

    }

    public static Main getInstance() {
        return instance;
    }

    public void sendProfile(PlayerAPI sender, String query) {

        PlayerAPI player = PlayerAPI.get(query);
        if (player == null) {
            sender.sendMessage(TextFormat.RED + query + " " + "could not be found online!");
            return;
        }

        try {
            int kills = AfterlifeAPI.getKills(player);
            int streaks = AfterlifeAPI.getKillStreak(player);
            int experience = AfterlifeAPI.getXp(player);
            int levels = AfterlifeAPI.getLevels(player);
            int deaths = AfterlifeAPI.getDeaths(player);
            switch (getConfig().getString("view-stats")) {
                case "standard":
                    sender.sendMessage(TextFormat.GREEN+"= "+TextFormat.YELLOW+player.getAlias()+"'s leaderboard!"+TextFormat.GREEN+" =");
                    sender.sendMessage(TextFormat.GREEN+"| "+TextFormat.GRAY+"Levels: "+TextFormat.WHITE+levels);
                    sender.sendMessage(TextFormat.GREEN+"| "+TextFormat.GRAY+"Experience: "+TextFormat.WHITE+experience);
                    sender.sendMessage(TextFormat.GREEN+"| "+TextFormat.GRAY+"Most Kills: "+TextFormat.WHITE+kills);
                    sender.sendMessage(TextFormat.GREEN+"| "+TextFormat.GRAY+"Highest Kill-streak: "+TextFormat.WHITE+streaks);
                    sender.sendMessage(TextFormat.GREEN+"| "+TextFormat.GRAY+"Most Deaths: "+TextFormat.WHITE+deaths);
                    sender.sendMessage(TextFormat.GREEN+"= "+TextFormat.YELLOW+"Usage: /stats <name>"+TextFormat.GREEN+" =");
                    break;
                case "form":
                    String title = player.getAlias()+"'s Leaderboard";
                    String content =
                            "Levels: "+levels+"\n"+
                            "Experience: "+experience+"\n"+
                            "Most Kills: "+kills+"\n"+
                            "Highest Kill-streak: "+streaks+"\n"+
                            "Most Deaths: "+deaths+"\n";
                    FormWindow window = new FormWindowModal(title, content, "search", "close");
                    formAPI.add("profile", window);
                    player.showFormWindow(formAPI.get("profile"), formAPI.getId("profile"));
                    break;
            }
        } catch (NullPointerException e) {
            sender.sendMessage(TextFormat.RED+"Player does not exist in our database!");
        }

    }

}
