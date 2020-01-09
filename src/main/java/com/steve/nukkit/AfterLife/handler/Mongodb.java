package com.steve.nukkit.AfterLife.handler;

import com.steve.nukkit.AfterLife.Main;
import org.bson.Document;

import java.util.List;

public class Mongodb {

    private static Main plugin;
    private static NukkitDB.Main nukkitDB;

    public Mongodb(Main plugin, NukkitDB.Main nukkitDB) {
        Mongodb.plugin = plugin;
        Mongodb.nukkitDB = nukkitDB;
    }

    public static Document query(String key, String fieldName) {
        String database = plugin.getConfig().getString("database");
        String collection = plugin.getConfig().getString("collection");
        return nukkitDB.get().query(key.toLowerCase(), fieldName, database, collection);
    }

    public static List<Document> getAll() {
        return null;
    }

    public static void createNew(Document document) {
        nukkitDB.get().insertDocument(document, plugin.getConfig().getString("database"), plugin.getConfig().getString("collection"));
    }

    public static void update(String query, String key, Integer value) {
        nukkitDB.get().updateDocument("uuid", query, key.toLowerCase(), value.toString(), plugin.getConfig().getString("database"), plugin.getConfig().getString("collection"));
    }

}
