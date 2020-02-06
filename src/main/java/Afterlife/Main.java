package Afterlife;

import cn.nukkit.plugin.PluginBase;

public class Main extends PluginBase {

    private static Main instance;

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {
        instance = this;
    }

    public static Main getInstance() {
        return instance;
    }
}
