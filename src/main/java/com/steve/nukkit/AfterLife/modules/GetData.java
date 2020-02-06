package com.steve.nukkit.AfterLife.modules;


public class GetData {

//    public static String GetLeaderboard(String dataType) {
//
//        String type = "";
//
//        switch (dataType) {
//            case "levels":
//                type = "levels";
//                break;
//            case "kills":
//                type = "kills";
//                break;
//            case "xp":
//                type = "global-xp";
//                break;
//            case "streaks":
//                type = "kill-streak";
//                break;
//            case "kdr":
//                type = "kdr";
//                break;
//            default:
//                System.out.println("Unexpected value: " + dataType);
//        }
//
//        Map<String, Integer> stats = new HashMap<>();
//        Map<String, Integer> sort;
//        for (Map<String, Object> objectMap : Mongodb.GetAll()) {
//            if (objectMap.containsKey(type)) {
//                stats.put(objectMap.get("name").toString(), Integer.parseInt(objectMap.get(type).toString()));
//            }
//        }
//
//        sort = GetData.sort(stats);
//
//        StringBuilder finalRankings = new StringBuilder();
//        int rank = 1;
//        for (Map.Entry<String, Integer> entry : sort.entrySet()) {
//            String name = entry.getKey();
//            int data = entry.getValue();
//            finalRankings.append(TextFormat.RED).append(rank).append(".) ").append(TextFormat.YELLOW).append(name).append(TextFormat.WHITE).append(": ").append(TextFormat.GREEN).append(data).append("\n");
//            if (rank > Main.getPlugin().getConfig().getInt("texts-top")) {
//                return finalRankings.toString();
//            }
//            rank++;
//        }
//        return finalRankings.toString();
//    }
//    private static Map<String, Integer> sort(Map<String, Integer> map) {
//        return map.entrySet()
//                .stream()
//                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
//                .collect(Collectors.toMap(
//                        Map.Entry::getKey, Map.Entry::getValue,
//                        (e1, e2) -> e1, LinkedHashMap::new
//                ));
//    }
}
