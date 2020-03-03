package Afterlife.API;

import NukkitDB.NukkitDB;
import PlayerAPI.Overrides.PlayerAPI;

public class AfterlifeAPI {

    public static int getKills(PlayerAPI player) {
        String database = Afterlife.Main.getInstance().getConfig().getString("database");
        String collection = Afterlife.Main.getInstance().getConfig().getString("collection");
        return Integer.parseInt(
                NukkitDB.query(
                        player.getUuid(), "uuid", database, collection
                ).get("kills").toString()
        );
    }

    public static int getKillStreak(PlayerAPI player) {
        String database = Afterlife.Main.getInstance().getConfig().getString("database");
        String collection = Afterlife.Main.getInstance().getConfig().getString("collection");
        return Integer.parseInt(
                NukkitDB.query(
                        player.getUuid(), "uuid", database, collection
                ).get("kill-streak").toString()
        );
    }

    public static int getDeaths(PlayerAPI player) {
        String database = Afterlife.Main.getInstance().getConfig().getString("database");
        String collection = Afterlife.Main.getInstance().getConfig().getString("collection");
        return Integer.parseInt(
                NukkitDB.query(
                        player.getUuid(), "uuid", database, collection
                ).get("deaths").toString()
        );
    }

    public static int getLevels(PlayerAPI player) {
        String database = Afterlife.Main.getInstance().getConfig().getString("database");
        String collection = Afterlife.Main.getInstance().getConfig().getString("collection");
        return Integer.parseInt(
                NukkitDB.query(
                        player.getUuid(), "uuid", database, collection
                ).get("levels").toString()
        );
    }

    public static int getXp(PlayerAPI player) {
        String database = Afterlife.Main.getInstance().getConfig().getString("database");
        String collection = Afterlife.Main.getInstance().getConfig().getString("collection");
        return Integer.parseInt(
                NukkitDB.query(
                        player.getUuid(), "uuid", database, collection
                ).get("global-xp").toString()
        );
    }

    public static int getNeededXp(PlayerAPI player) {
        String database = Afterlife.Main.getInstance().getConfig().getString("database");
        String collection = Afterlife.Main.getInstance().getConfig().getString("collection");
        return Integer.parseInt(
                NukkitDB.query(
                        player.getUuid(), "uuid", database, collection
                ).get("experience").toString()
        );
    }

    public static void addKill(PlayerAPI player) {
        String database = Afterlife.Main.getInstance().getConfig().getString("database");
        String collection = Afterlife.Main.getInstance().getConfig().getString("collection");
        int currentData = getKills(player);
        int updatedData = currentData + 1;
        int currentStreak = getKillStreak(player);
        int updatedStreak = currentStreak + 1;
        NukkitDB.updateDocument(player.getUuid(), "uuid", "kills", updatedData, database, collection);
        NukkitDB.updateDocument(player.getUuid(), "uuid", "kill-streak", updatedStreak, database, collection);
    }

    public static void addDeath(PlayerAPI player) {
        String database = Afterlife.Main.getInstance().getConfig().getString("database");
        String collection = Afterlife.Main.getInstance().getConfig().getString("collection");
        int currentData = getDeaths(player);
        int updatedData = currentData + 1;
        NukkitDB.updateDocument(player.getUuid(), "uuid", "kills", updatedData, database, collection);
        NukkitDB.updateDocument(player.getUuid(), "uuid", "kill-streak", 0, database, collection);
    }

    public static void addXp(PlayerAPI player, int amount) {
        String database = Afterlife.Main.getInstance().getConfig().getString("database");
        String collection = Afterlife.Main.getInstance().getConfig().getString("collection");
        Afterlife.Main main = Afterlife.Main.getInstance();
        int xp = getNeededXp(player) + amount;
        int abs = Math.abs(xp - main.getConfig().getInt("xp-levelup-amount"));
        NukkitDB.updateDocument(player.getUuid(), "uuid", "global-xp", xp, database, collection);
        if (xp >= main.getConfig().getInt("xp-levelup-amount")) {
            addLevel(player);
            NukkitDB.updateDocument(
                    player.getUuid(), "uuid", "experience", abs, database, collection
            );
        } else {
            NukkitDB.updateDocument(
                    player.getUuid(), "uuid", "experience", xp, database, collection
            );
        }
    }

    public static void removeXp(PlayerAPI player, int amount) {
        String database = Afterlife.Main.getInstance().getConfig().getString("database");
        String collection = Afterlife.Main.getInstance().getConfig().getString("collection");
        Afterlife.Main main = Afterlife.Main.getInstance();
        int xp = getNeededXp(player) - amount;
        int globalXp = getXp(player) - amount;
        int abs = Math.abs(getNeededXp(player) - amount);
        int difference = Math.abs(main.getConfig().getInt("xp-levelup-amount") - abs);
        if (xp < 0) {
            if (getLevels(player) > 0) {
                removeLevel(player);
                NukkitDB.updateDocument(
                        player.getUuid(), "uuid", "experience", difference, database, collection
                );
            } else {
                NukkitDB.updateDocument(
                        player.getUuid(), "uuid", "experience", 0, database, collection
                );
            }
        } else {
            NukkitDB.updateDocument(
                    player.getUuid(), "uuid", "experience", xp, database, collection
            );
        }
        NukkitDB.updateDocument(
                player.getUuid(), "uuid", "global-xp", globalXp, database, collection
        );
    }

    public static void addLevel(PlayerAPI player) {
        String database = Afterlife.Main.getInstance().getConfig().getString("database");
        String collection = Afterlife.Main.getInstance().getConfig().getString("collection");
        NukkitDB.updateDocument(
                player.getUuid(), "uuid", "levels", getLevels(player) + 1, database, collection
        );
    }

    public static void removeLevel(PlayerAPI player) {
        String database = Afterlife.Main.getInstance().getConfig().getString("database");
        String collection = Afterlife.Main.getInstance().getConfig().getString("collection");
        if (getLevels(player) > 0) {
            NukkitDB.updateDocument(
                    player.getUuid(), "uuid", "levels", getLevels(player) + 1, database, collection
            );
        }
    }

}
