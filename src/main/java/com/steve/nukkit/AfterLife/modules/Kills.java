package com.steve.nukkit.AfterLife.modules;

import com.steve.nukkit.AfterLife.handler.Mongodb;

public class Kills {

    public void add(String uuid) {
        Mongodb.update(uuid, "kills", Integer.parseInt(Mongodb.query(uuid, "uuid").get("kills").toString()) + 1);
        Mongodb.update(uuid, "kill-streak", Integer.parseInt(Mongodb.query(uuid, "uuid").get("kill-streak").toString()) + 1);
    }

    public void remove(String uuid) {
        Mongodb.update(uuid, "kills", Integer.parseInt(Mongodb.query(uuid, "uuid").get("kills").toString()) - 1);
    }

    public Integer get(String uuid) {
        return Integer.parseInt(Mongodb.query(uuid, "uuid").get("kills").toString());
    }

}
