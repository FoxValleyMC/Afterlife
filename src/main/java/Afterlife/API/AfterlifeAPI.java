package Afterlife.API;

import NukkitDB.Provider.MongoDB;
import PlayerAPI.Overrides.PlayerAPI;

public class AfterlifeAPI {

    public static int getKills(PlayerAPI player) {
        String collection = Afterlife.Main.getInstance().getConfig().getString("collection");
        return Integer.parseInt(
                MongoDB.getDocument(
                        MongoDB.getCollection(collection), "uuid", player.getUuid()
                ).get("kills").toString()
        );
    }

    public static int getKillStreak(PlayerAPI player) {
        String collection = Afterlife.Main.getInstance().getConfig().getString("collection");
        return Integer.parseInt(
                MongoDB.getDocument(
                        MongoDB.getCollection(collection), "uuid", player.getUuid()
                ).get("kill-streak").toString()
        );
    }

    public static int getDeaths(PlayerAPI player) {
        String collection = Afterlife.Main.getInstance().getConfig().getString("collection");
        return Integer.parseInt(
                MongoDB.getDocument(
                        MongoDB.getCollection(collection), "uuid", player.getUuid()
                ).get("deaths").toString()
        );
    }

    public static int getLevels(PlayerAPI player) {
        String collection = Afterlife.Main.getInstance().getConfig().getString("collection");
        return Integer.parseInt(
                MongoDB.getDocument(
                        MongoDB.getCollection(collection), "uuid", player.getUuid()
                ).get("levels").toString()
        );
    }

    public static int getXp(PlayerAPI player) {
        String collection = Afterlife.Main.getInstance().getConfig().getString("collection");
        return Integer.parseInt(
                MongoDB.getDocument(
                        MongoDB.getCollection(collection), "uuid", player.getUuid()
                ).get("global-xp").toString()
        );
    }

    public static int getNeededXp(PlayerAPI player) {
        String collection = Afterlife.Main.getInstance().getConfig().getString("collection");
        return Integer.parseInt(
                MongoDB.getDocument(
                        MongoDB.getCollection(collection), "uuid", player.getUuid()
                ).get("experience").toString()
        );
    }

    public static void addKill(PlayerAPI player) {
        String collection = Afterlife.Main.getInstance().getConfig().getString("collection");
        int currentData = getKills(player);
        int updatedData = currentData + 1;
        int currentStreak = getKillStreak(player);
        int updatedStreak = currentStreak + 1;
        MongoDB.updateOne(
                MongoDB.getCollection(collection),
                "uuid",
                player.getUuid(),
                "kills",
                updatedData
        );
        MongoDB.updateOne(
                MongoDB.getCollection(collection),
                "uuid",
                player.getUuid(),
                "kill-streak",
                updatedStreak
        );
    }

    public static void addDeath(PlayerAPI player) {
        String collection = Afterlife.Main.getInstance().getConfig().getString("collection");
        int currentData = getDeaths(player);
        int updatedData = currentData + 1;
        MongoDB.updateOne(
                MongoDB.getCollection(collection),
                "uuid",
                player.getUuid(),
                "kills",
                updatedData
        );
        MongoDB.updateOne(
                MongoDB.getCollection(collection),
                "uuid",
                player.getUuid(),
                "kill-streak",
                0
        );
    }

    public static void addXp(PlayerAPI player, int amount) {
        String collection = Afterlife.Main.getInstance().getConfig().getString("collection");
        Afterlife.Main main = Afterlife.Main.getInstance();
        int xp = getNeededXp(player) + amount;
        int abs = Math.abs(xp - main.getConfig().getInt("xp-levelup-amount"));
        MongoDB.updateOne(
                MongoDB.getCollection(collection),
                "uuid",
                player.getUuid(),
                "global-xp",
                xp
        );
        if (xp >= main.getConfig().getInt("xp-levelup-amount")) {
            addLevel(player);
            MongoDB.updateOne(
                    MongoDB.getCollection(collection),
                    "uuid",
                    player.getUuid(),
                    "experience",
                    abs
            );
        } else {
            MongoDB.updateOne(
                    MongoDB.getCollection(collection),
                    "uuid",
                    player.getUuid(),
                    "experience",
                    xp
            );
        }
    }

    public static void removeXp(PlayerAPI player, int amount) {
        String collection = Afterlife.Main.getInstance().getConfig().getString("collection");
        Afterlife.Main main = Afterlife.Main.getInstance();
        int xp = getNeededXp(player) - amount;
        int globalXp = getXp(player) - amount;
        int abs = Math.abs(getNeededXp(player) - amount);
        int difference = Math.abs(main.getConfig().getInt("xp-levelup-amount") - abs);
        if (xp < 0) {
            if (getLevels(player) > 0) {
                removeLevel(player);
                MongoDB.updateOne(
                        MongoDB.getCollection(collection),
                        "uuid",
                        player.getUuid(),
                        "experience",
                        difference
                );
            } else {
                MongoDB.updateOne(
                        MongoDB.getCollection(collection),
                        "uuid",
                        player.getUuid(),
                        "experience",
                        0
                );
            }
        } else {
            MongoDB.updateOne(
                    MongoDB.getCollection(collection),
                    "uuid",
                    player.getUuid(),
                    "experience",
                    xp
            );
        }
        MongoDB.updateOne(
                MongoDB.getCollection(collection),
                "uuid",
                player.getUuid(),
                "experience",
                globalXp
        );
    }

    public static void addLevel(PlayerAPI player) {
        String collection = Afterlife.Main.getInstance().getConfig().getString("collection");
        MongoDB.updateOne(
                MongoDB.getCollection(collection),
                "uuid",
                player.getUuid(),
                "levels",
                getLevels(player) + 1
        );
    }

    public static void removeLevel(PlayerAPI player) {
        String collection = Afterlife.Main.getInstance().getConfig().getString("collection");
        if (getLevels(player) > 0) {
            MongoDB.updateOne(
                    MongoDB.getCollection(collection),
                    "uuid",
                    player.getUuid(),
                    "levels",
                    getLevels(player) - 1
            );
        }
    }

}
