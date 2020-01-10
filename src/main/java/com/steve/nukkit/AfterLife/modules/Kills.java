package com.steve.nukkit.AfterLife.modules;

import com.steve.nukkit.AfterLife.handler.Mongodb;

public class Kills {

    public void add(String uuid) {
        Mongodb.update(uuid, "kills", Integer.parseInt(Mongodb.query(uuid, "uuid").getString("kills")) + 1);
        Mongodb.update(uuid, "kill-streak", Integer.parseInt(Mongodb.query(uuid, "uuid").getString("kill-streak")) + 1);
    }

    public void remove(String uuid) {
        Mongodb.update(uuid, "kills", Integer.parseInt(Mongodb.query(uuid, "uuid").getString("kills")) - 1);
    }

    public String get(String uuid) {
        return Mongodb.query(uuid, "uuid").getString("kills");
    }

}
