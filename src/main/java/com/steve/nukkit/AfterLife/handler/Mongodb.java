package com.steve.nukkit.AfterLife.handler;

import com.steve.nukkit.AfterLife.Main;
import NukkitDB.NukkitDB;

import java.util.List;
import java.util.Map;

public class Mongodb {

    private static Main plugin;

    public Mongodb(Main plugin) {
        Mongodb.plugin = plugin;
    }

    public static Map<String, Object> query(String key, String fieldName) {
        String database = plugin.getConfig().getString("database");
        String collection = plugin.getConfig().getString("collection");
        return NukkitDB.query(key.toLowerCase(), fieldName, database, collection);
    }

    public static List<Map<String, Object>> GetAll() {
        String database = plugin.getConfig().getString("database");
        String collection = plugin.getConfig().getString("collection");
        return NukkitDB.GetAll(database, collection);
    }

    public static void createNew(Map<String, Object> objectMap) {
        NukkitDB.insertDocument(objectMap, plugin.getConfig().getString("database"), plugin.getConfig().getString("collection"));
    }

    public static void update(String query, String key, Integer value) {
        NukkitDB.updateDocument(query, "uuid", key.toLowerCase(), value, plugin.getConfig().getString("database"), plugin.getConfig().getString("collection"));
    }

}
