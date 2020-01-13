package com.steve.nukkit.AfterLife.handler;

import NukkitDB.NukkitDB;
import com.steve.nukkit.AfterLife.Main;
import org.bson.Document;

import java.util.List;

public class Mongodb {

    private static Main plugin;

    public Mongodb(Main plugin) {
        Mongodb.plugin = plugin;
    }

    public static Document query(String key, String fieldName) {
        String database = plugin.getConfig().getString("database");
        String collection = plugin.getConfig().getString("collection");
        return NukkitDB.query(key.toLowerCase(), fieldName, database, collection);
    }

    public static List<Document> getAll() {
        return null;
    }

    public static void createNew(Document document) {
        NukkitDB.insertDocument(document, plugin.getConfig().getString("database"), plugin.getConfig().getString("collection"));
    }

    public static void update(String query, String key, Integer value) {
        NukkitDB.updateDocument(query, "uuid", key.toLowerCase(), value, plugin.getConfig().getString("database"), plugin.getConfig().getString("collection"));
    }

}
