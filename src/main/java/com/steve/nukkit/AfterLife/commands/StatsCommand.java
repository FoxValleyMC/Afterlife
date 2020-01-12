package com.steve.nukkit.AfterLife.commands;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginCommand;
import com.steve.nukkit.AfterLife.Main;

public class StatsCommand extends PluginCommand {

    private Main plugin;

    public StatsCommand(String name, Main plugin) {
        super(name, plugin);
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (sender instanceof Player) {

            switch (args.length) {
                case 0:
                    plugin.sendProfile((Player) sender, sender.getName());
                    break;
                case 1:
                    Player player = this.getPlugin().getServer().getPlayer(args[0]);
                    if (player != null) {
                        plugin.sendProfile((Player) sender, player.getName());
                    } else {
                        plugin.sendProfile((Player) sender, args[0]);
                    }
                    break;
            }
        } else {
            getPlugin().getLogger().error("command can only be run in-game");
        }
        return true;
    }
}
