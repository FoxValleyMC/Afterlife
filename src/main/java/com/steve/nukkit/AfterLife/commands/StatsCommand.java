package com.steve.nukkit.AfterLife.commands;

import PlayerAPI.Overrides.PlayerAPI;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginCommand;
import com.steve.nukkit.AfterLife.Main;
import com.steve.nukkit.AfterLife.handler.Mongodb;

import java.util.Map;

public class StatsCommand extends PluginCommand {

    private Main plugin;

    public StatsCommand(String name, Main plugin) {
        super(name, plugin);
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (sender instanceof PlayerAPI) {

            switch (args.length) {
                case 0:
                    plugin.sendProfile((PlayerAPI) sender, sender.getName());
                    break;
                case 1:
                    Player player = this.getPlugin().getServer().getPlayer(args[0]);
                    if (player != null) {
                        plugin.sendProfile((Player) sender, player.getName());
                    } else {
                        try {
                            Map<String, Object> aliasDataMap = AliasPro.AliasPro.getPlayer(args[0]);
                            if (aliasDataMap == null) {
                                plugin.sendProfile((Player) sender, args[0]);
                            } else {
                                String uuid = aliasDataMap.get("uuid").toString();
                                Map<String, Object> objectMap = Mongodb.query(uuid, "uuid");
                                if (objectMap == null) {
                                    plugin.sendProfile((Player) sender, args[0]);
                                } else {
                                    plugin.sendProfile((Player) sender, objectMap.get("name").toString());
                                }
                            }
                        } catch (NoClassDefFoundError error) {
                            plugin.sendProfile((Player) sender, args[0]);
                        }
                    }
                    break;
            }
        } else {
            getPlugin().getLogger().error("command can only be run in-game");
        }
        return true;
    }
}
