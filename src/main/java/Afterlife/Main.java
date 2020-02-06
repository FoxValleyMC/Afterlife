package Afterlife;

import Afterlife.commands.FloatingTextCommand;
import Afterlife.commands.StatsCommand;
import Afterlife.events.DamageEvent;
import Afterlife.events.JoinEvent;
import Afterlife.window.ProfileWindow;
import PlayerAPI.Overrides.PlayerAPI;
import cn.nukkit.command.CommandMap;
import cn.nukkit.command.CommandSender;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.level.particle.FloatingTextParticle;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

import java.util.HashMap;
import java.util.Map;

public class Main extends PluginBase {

    private static Main instance;

    public Config texts;

    public Map<String, FloatingTextParticle> ftps = new HashMap<>();
    public Map<String, FormWindow> forms = new HashMap<>();

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

    public void sendProfile(PlayerAPI sender, String query) {

        PlayerAPI player = (PlayerAPI) getServer().getPlayer(query);

        try {
            switch (getConfig().getString("view-stats")) {
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
                    String content = "Levels: "+player.getLevels()+"\n"+
                                    "Experience: "+player.getX()+"\n"+
                                    "Most Kills: "+player.getKills()+"\n"+
                                    "Highest Kill-streak: "+player.getKillStreak()+"\n"+
                                    "Most Deaths: "+player.getDeaths()+"\n";
                    ProfileWindow window = new ProfileWindow();
                    window.setTitle(title);
                    window.setContent(content);
                    window.setButton1("search");
                    window.setButton2("close");
                    sender.showFormWindow(window);
                    break;
            }
        } catch (NullPointerException e) {
            sender.sendMessage(TextFormat.RED+"Player does not exist in our database!");
        }

    }

}
