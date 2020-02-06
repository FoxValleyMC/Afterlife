package com.steve.nukkit.AfterLife.commands;

import cn.nukkit.command.PluginCommand;
import com.steve.nukkit.AfterLife.Main;

public class FloatingTextCommand extends PluginCommand {

//    private Main plugin;
//
    public FloatingTextCommand(String name, Main plugin) {
        super(name, plugin);
//        this.plugin = plugin;
//        this.description = "Create floating text leaderboards";
    }
//
//    @Override
//    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
//
//        List<String> stringList = new ArrayList<>();
//        stringList.add("levels");
//        stringList.add("kills");
//        stringList.add("kdr");
//        stringList.add("xp");
//        stringList.add("streaks");
//
//        if (sender instanceof Player) {
//            if (plugin.getConfig().getBoolean("texts-enabled")){
//                if (sender.hasPermission("leaderboard.create")) {
//                    if (args.length == 1) {
//                        if (stringList.contains(args[0])) {
//                            if (!plugin.ftps.containsKey(args[0])) {
//                                Level level = ((Player) sender).getLevel();
//                                double x = Math.round(((Player) sender).getX());
//                                double y = Math.round(((Player) sender).getY());
//                                double z =  Math.round(((Player) sender).getZ());
//                                Position position = new Position(x, y+1.7, z, level);
//                                FloatingTextHandler.addParticle(position, args[0], (Player) sender);
//                                Map<String, Object> textsMap = new HashMap<>();
//                                textsMap.put("level", level.getName());
//                                textsMap.put("type", args[0]);
//                                textsMap.put("x", x);
//                                textsMap.put("y", y);
//                                textsMap.put("z", z);
//                                sender.sendMessage("Set leaderboard " + args[0] + " at " + textsMap.toString());
//                                plugin.texts.set(args[0], textsMap);
//                                plugin.texts.save();
//                            } else {
//                                sender.sendMessage("data already set...");
//                            }
//                        } else {
//                            sender.sendMessage("Incorrect data type... ("+args[0]+")");
//                        }
//                    } else {
//                        sender.sendMessage("Usage: /setleaderboard <type>");
//                    }
//                } else {
//                    sender.sendMessage(TextFormat.RED+"You do not have permission to create leaderboards!");
//                }
//            }
//        } else {
//            sender.sendMessage("Commands can only be run in-game...");
//        }
//
//        return true;
//    }
}
