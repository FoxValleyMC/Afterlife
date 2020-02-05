package com.steve.nukkit.AfterLife.utils;

public interface AfterlifeImpl {

    int getKills();

    int getKillStreak();

    int getDeaths();

    int getLevels();

    int getXp();

    int getNeededXp();

    void addKill();

    void addDeath();

    void addXp(int amount);

    void removeXp(int amount);

    void addLevel();

    void removeLevel();

}
