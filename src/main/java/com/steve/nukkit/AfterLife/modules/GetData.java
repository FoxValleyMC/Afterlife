package com.steve.nukkit.AfterLife.modules;

import com.steve.nukkit.AfterLife.Main;
import com.steve.nukkit.AfterLife.handler.Mongodb;

import java.util.HashMap;
import java.util.Map;

public class GetData {

    public static String GetLeaderboard(String type) {
        Map<String, Integer> stats = new HashMap<>();
        Map<String, Integer> sort = new HashMap<>();
        for (Map<String, Object> objectMap : Mongodb.GetAll()) {
            if (objectMap.containsKey(type)) {
                stats.put(objectMap.get("name").toString(), Integer.parseInt(objectMap.get(type).toString()));
            }
        }

        stats.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> sort.put(x.getKey(), x.getValue()));

        String finalRankings = "";
        int rank = 1;
        for (Map.Entry<String, Integer> entry : sort.entrySet()) {
            String name = entry.getKey();
            int data = entry.getValue();

            finalRankings = rank+".) "+name+": "+data;
            if (rank > Main.getPlugin().getConfig().getInt("texts-top")) {
                return finalRankings;
            }
            rank++;
        }
        return finalRankings;
    }
}
