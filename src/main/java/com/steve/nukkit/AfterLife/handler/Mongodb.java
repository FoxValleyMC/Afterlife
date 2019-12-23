package com.steve.nukkit.AfterLife.handler;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.steve.nukkit.AfterLife.Main;
import org.bson.Document;

import java.util.LinkedList;
import java.util.List;

public class Mongodb {

    private static MongoClient client = null;
    private static MongoDatabase database = null;

    public static void connect() {
        String database = Main.getPlugin().getConfig().getString("database");
        MongoClientURI uri = new MongoClientURI(Main.getPlugin().getConfig().getString("uri"));
        Mongodb.client = new MongoClient(uri);
        Mongodb.database = Mongodb.client.getDatabase(database);
    }

    public static void close() {
        if (Mongodb.client != null) Mongodb.client.close();
    }

    public static Document query(String index, String fieldName) {
        return Mongodb.getCollection().find(Filters.eq(fieldName, index)).first();
    }

    public static List<Document> getAll() {
        FindIterable<Document> cursor = Mongodb.getCollection().find();
        List<Document> list = new LinkedList<>();
        for (Document document : cursor) {
            list.add(document);
        }
        return list;
    }

    public static MongoDatabase getDatabase() {
        return Mongodb.database;
    }

    public static MongoCollection<Document> getCollection() {
        return Mongodb.getDatabase().getCollection(Main.getPlugin().getConfig().getString("collection"));
    }

    public static void createNew(Document document) {
        Mongodb.getCollection().insertOne(document);
    }

    public static void update(String query, String key, Integer value) {
        Mongodb.getCollection().updateOne(Filters.eq("uuid", query), new Document("$set", new Document(key, value)));
    }

}
