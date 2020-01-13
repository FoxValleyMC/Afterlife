package com.steve.nukkit.AfterLife.modules;

import com.steve.nukkit.AfterLife.handler.Mongodb;

public class Levels {

    public void add(String uuid, Integer amount) {
        Mongodb.update(uuid, "levels", Mongodb.query(uuid, "uuid").getInteger("levels") + amount);
    }

    public void remove(String uuid, Integer amount) {
        Mongodb.update(uuid, "levels", Mongodb.query(uuid, "uuid").getInteger("levels") - amount);
    }

    public Integer get(String uuid) {
        return Mongodb.query(uuid, "uuid").getInteger("levels");
    }

}
