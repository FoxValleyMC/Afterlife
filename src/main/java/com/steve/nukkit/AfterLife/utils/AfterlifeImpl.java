package com.steve.nukkit.AfterLife.utils;

public interface AfterlifeImpl {

    int getKills();

    int getDeaths();

    int getLevels();

    int getExperience();

    int getNeededXp();

    void addKill();

    void addDeath();

    void addXp();

    void removeXp();

    void addLevel();

    void removeLevel();

}
