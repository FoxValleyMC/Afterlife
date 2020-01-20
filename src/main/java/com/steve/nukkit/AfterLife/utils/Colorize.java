package com.steve.nukkit.AfterLife.utils;

import cn.nukkit.utils.TextFormat;

public class Colorize {

    public static String Register(String rawString) {
        return rawString
                .replace("&0", TextFormat.BLACK+"")
                .replace("&1", TextFormat.DARK_BLUE+"")
                .replace("&2", TextFormat.DARK_GREEN+"")
                .replace("&3", TextFormat.DARK_AQUA+"")
                .replace("&4", TextFormat.DARK_RED+"")
                .replace("&5", TextFormat.DARK_PURPLE+"")
                .replace("&6", TextFormat.GOLD+"")
                .replace("&7", TextFormat.GRAY+"")
                .replace("&8", TextFormat.DARK_GRAY+"")
                .replace("&9", TextFormat.BLUE+"")
                .replace("&a", TextFormat.GREEN+"")
                .replace("&b", TextFormat.AQUA+"")
                .replace("&c", TextFormat.RED+"")
                .replace("&d", TextFormat.LIGHT_PURPLE+"")
                .replace("&e", TextFormat.YELLOW+"")
                .replace("&f", TextFormat.WHITE+"")
                .replace("&k", TextFormat.OBFUSCATED+"")
                .replace("&l", TextFormat.BOLD+"")
                .replace("&m", TextFormat.STRIKETHROUGH+"")
                .replace("&n", TextFormat.UNDERLINE+"")
                .replace("&o", TextFormat.ITALIC+"")
                .replace("&r", TextFormat.RESET+"");
    }

}
