package Afterlife.commands;

import PlayerAPI.Overrides.PlayerAPI;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginCommand;
import Afterlife.Main;

public class StatsCommand extends PluginCommand {

    private Main plugin;

    public StatsCommand(String name, Main plugin) {
        super(name, plugin);
        this.plugin = plugin;
        this.setAliases(new String[]{"profile"});
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (sender instanceof PlayerAPI) {
            switch (args.length) {
                case 0:
                    plugin.sendProfile(sender, sender.getName());
                    break;
                case 1:
                    // TODO - view other players data
                    break;
            }
        } else {
            getPlugin().getLogger().error("command can only be run in-game");
        }
        return true;
    }
}
