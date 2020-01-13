package com.steve.nukkit.AfterLife.modules;

import com.steve.nukkit.AfterLife.handler.Mongodb;

public class Deaths {

    public void add(String uuid) {
        Mongodb.update(uuid, "deaths", Mongodb.query(uuid, "uuid").getInteger("deaths") + 1);
        Mongodb.update(uuid, "kill-streak", 0);
    }

    public void remove(String uuid) {
        Mongodb.update(uuid, "deaths", Mongodb.query(uuid, "uuid").getInteger("deaths") - 1);
    }

    public Integer get(String uuid) {
        return Mongodb.query(uuid, "uuid").getInteger("deaths");
    }

}
