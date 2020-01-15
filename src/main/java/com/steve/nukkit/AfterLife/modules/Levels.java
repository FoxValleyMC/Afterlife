package com.steve.nukkit.AfterLife.modules;

import com.steve.nukkit.AfterLife.handler.Mongodb;

public class Levels {

    public void add(String uuid, Integer amount) {
        Mongodb.update(uuid, "levels", Integer.parseInt(Mongodb.query(uuid, "uuid").get("levels").toString()) + amount);
    }

    public void remove(String uuid, Integer amount) {
        Mongodb.update(uuid, "levels", Integer.parseInt(Mongodb.query(uuid, "uuid").get("levels").toString()) - amount);
    }

    public Integer get(String uuid) {
        return Integer.parseInt(Mongodb.query(uuid, "uuid").get("levels").toString());
    }

}
