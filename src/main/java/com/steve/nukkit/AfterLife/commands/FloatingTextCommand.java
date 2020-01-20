package com.steve.nukkit.AfterLife.commands;

import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginCommand;
import com.steve.nukkit.AfterLife.Main;

public class FloatingTextCommand extends PluginCommand {

    private Main plugin;

    public FloatingTextCommand(String name, Main plugin) {
        super(name, plugin);
        this.plugin = plugin;
        this.description = "Create floating text leaderboards";
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        return true;
    }
}
