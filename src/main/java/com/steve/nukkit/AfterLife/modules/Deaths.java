package com.steve.nukkit.AfterLife.modules;

import com.steve.nukkit.AfterLife.handler.Mongodb;

public class Deaths {

    public void add(String uuid) {
        Mongodb.update(uuid, "deaths", Integer.parseInt(Mongodb.query(uuid, "uuid").get("deaths").toString()) + 1);
        Mongodb.update(uuid, "kill-streak", 0);
    }

    public void remove(String uuid) {
        Mongodb.update(uuid, "deaths", Integer.parseInt(Mongodb.query(uuid, "uuid").get("deaths").toString()) - 1);
    }

    public Integer get(String uuid) {
        return Integer.parseInt(Mongodb.query(uuid, "uuid").get("deaths").toString());
    }

}
