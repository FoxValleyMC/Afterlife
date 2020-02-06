package Afterlife.commands;

import Afterlife.handler.FloatingTextHandler;
import PlayerAPI.Overrides.PlayerAPI;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginCommand;
import Afterlife.Main;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import cn.nukkit.utils.TextFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FloatingTextCommand extends PluginCommand {

    private Main plugin;

    public FloatingTextCommand(String name, Main plugin) {
        super(name, plugin);
        this.plugin = plugin;
        this.setAliases(new String[]{"sl"});
        this.description = "Create floating text leaderboards";
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        List<String> stringList = new ArrayList<>();
        stringList.add("levels");
        stringList.add("kills");
        stringList.add("kdr");
        stringList.add("xp");
        stringList.add("streaks");

        if (sender instanceof PlayerAPI) {
            if (plugin.getConfig().getBoolean("texts-enabled")){
                if (sender.hasPermission("leaderboard.create")) {
                    if (args.length == 1) {
                        if (stringList.contains(args[0])) {
                            if (!plugin.ftps.containsKey(args[0])) {
                                Level level = ((PlayerAPI) sender).getLevel();
                                double x = Math.round(((PlayerAPI) sender).getX());
                                double y = Math.round(((PlayerAPI) sender).getY());
                                double z =  Math.round(((PlayerAPI) sender).getZ());
                                Position position = new Position(x, y+1.7, z, level);
                                FloatingTextHandler.addParticle(position, args[0], (PlayerAPI) sender);
                                Map<String, Object> textsMap = new HashMap<>();
                                textsMap.put("level", level.getName());
                                textsMap.put("type", args[0]);
                                textsMap.put("x", x);
                                textsMap.put("y", y);
                                textsMap.put("z", z);
                                sender.sendMessage("Set leaderboard " + args[0] + " at " + textsMap.toString());
                                plugin.texts.set(args[0], textsMap);
                                plugin.texts.save();
                            } else {
                                sender.sendMessage("data already set...");
                            }
                        } else {
                            sender.sendMessage("Incorrect data type... ("+args[0]+")");
                        }
                    } else {
                        sender.sendMessage("Usage: /setleaderboard <type>");
                    }
                } else {
                    sender.sendMessage(TextFormat.RED+"You do not have permission to create leaderboards!");
                }
            }
        } else {
            sender.sendMessage("Commands can only be run in-game...");
        }

        return true;
    }
}
