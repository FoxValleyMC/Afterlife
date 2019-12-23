package com.steve.nukkit.AfterLife.commands;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginCommand;
import com.steve.nukkit.AfterLife.AfterLife;
import com.steve.nukkit.AfterLife.Main;

public class testCommand extends PluginCommand {

    private Main plugin;

    public testCommand(String name, Main plugin) {
        super(name, plugin);
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        return true;
    }
}
