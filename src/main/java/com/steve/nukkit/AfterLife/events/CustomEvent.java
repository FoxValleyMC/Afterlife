package com.steve.nukkit.AfterLife.events;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.level.Position;
import cn.nukkit.potion.Effect;
import com.steve.nukkit.AfterLife.AfterLife;
import com.steve.nukkit.AfterLife.Main;

import java.util.List;

public class CustomEvent implements Listener {

    private Main plugin;

    public CustomEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEvent(EntityDamageEvent event) {

        // player who is getting damaged
        Player victim = (Player) event.getEntity();

        // position to teleport to on death
        Position position = plugin.getServer().getDefaultLevel().getSafeSpawn();

        // check if victim is a player entity
        if (victim != null) {

            // check if victims health becomes lower than 1 minecraft heart value
            if (event.getFinalDamage() >= victim.getHealth()) {

                // cancel event and tp victim to spawn simulating players death
                event.setCancelled(true);
                victim.teleport(position);
                victim.setHealth(victim.getMaxHealth());

                // add to victims death and xp score
                AfterLife.deaths.add(victim.getUniqueId().toString());
                if (plugin.getConfig().getBoolean("use-levels")) {
                    AfterLife.experience.remove(victim.getUniqueId().toString(), plugin.getConfig().getInt("loose-xp-amount"));
                }

                // log death message
                logMessage(event.getCause(), victim);

                // if player was demonetized by another player
                if (event instanceof EntityDamageByEntityEvent) {

                    // player who executed the event
                    Player killer = (Player) ((EntityDamageByEntityEvent) event).getDamager();

                    // check if killer is a player entity
                    if (killer != null) {

                        // add kill and xp values to leaderboard score
                        AfterLife.kills.add(killer.getUniqueId().toString());
                        if (plugin.getConfig().getBoolean("use-levels")) {
                            AfterLife.experience.add(killer.getUniqueId().toString(), plugin.getConfig().getInt("add-xp-amount"));
                        }

                        // log message
                        plugin.getServer().broadcastMessage(victim.getName()+" was killed by "+killer.getName());

                        // apply victory effects
                        List<String> list = plugin.getConfig().getStringList("victory-effects");
                        for (String string : list) {
                            String[] a = string.split(":");
                            Effect effect = Effect.getEffect(Integer.parseInt(a[0]));
                            effect.setAmplifier(Integer.parseInt(a[1]));
                            effect.setDuration(Integer.parseInt(a[2]));
                            killer.addEffect(effect);
                        }
                    }
                }
            }
        }
    }

    private void logMessage(EntityDamageEvent.DamageCause damageCause, Player victim) {
        switch (damageCause) {
            case CONTACT:
                plugin.getServer().broadcastMessage(victim.getName()+" Was killed by contact with another block!");
                break;
            case ENTITY_ATTACK:
                plugin.getServer().broadcastMessage(victim.getName()+" was killed by another entity!");
                break;
            case PROJECTILE:
                plugin.getServer().broadcastMessage(victim.getName()+" was killed by a projectile attack!");
                break;
            case SUFFOCATION:
                plugin.getServer().broadcastMessage(victim.getName()+" Suffocated in a wall!");
                break;
            case FALL:
                plugin.getServer().broadcastMessage(victim.getName()+" Fell from a high place!");
                break;
            case FIRE:
                plugin.getServer().broadcastMessage(victim.getName()+" Went up in flames!");
                break;
            case FIRE_TICK:
                plugin.getServer().broadcastMessage(victim.getName()+" Burned to death!");
                break;
            case LAVA:
                plugin.getServer().broadcastMessage(victim.getName()+" Tried to swim in lava!");
                break;
            case DROWNING:
                plugin.getServer().broadcastMessage(victim.getName()+" Drown!");
                break;
            case BLOCK_EXPLOSION:
            case ENTITY_EXPLOSION:
                plugin.getServer().broadcastMessage(victim.getName()+" Blew up!");
                break;
            case VOID:
                plugin.getServer().broadcastMessage(victim.getName()+" Fell into the void!");
                break;
            case SUICIDE:
                plugin.getServer().broadcastMessage(victim.getName()+" Committed suicide!");
                break;
            case MAGIC:
                plugin.getServer().broadcastMessage(victim.getName()+" Was killed by magic!");
                break;
            case LIGHTNING:
                plugin.getServer().broadcastMessage(victim.getName()+" Was struck by lightning!");
                break;
            case HUNGER:
                plugin.getServer().broadcastMessage(victim.getName()+" Starved to death!");
                break;
            default:
                plugin.getServer().broadcastMessage(victim.getName()+" Died!");
        }
    }

}
