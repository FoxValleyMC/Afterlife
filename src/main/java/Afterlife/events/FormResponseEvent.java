package Afterlife.events;

import cn.nukkit.event.Listener;

public class FormResponseEvent implements Listener {

//    private Main plugin;
//
//    public FormResponseEvent(Main plugin) {
//        this.plugin = plugin;
//    }
//
//    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
//    public void onFormSubmit(PlayerFormRespondedEvent event) {
//        Player player = event.getPlayer();
//        FormResponse response = event.getResponse();
//        if (response instanceof FormResponseModal) {
//            FormResponseModal modalResponse = (FormResponseModal) response;
//            if (event.getFormID() == 6 && modalResponse.getClickedButtonText().equals("search")) {
//                player.showFormWindow(Main.getPlugin().forms.get("searchForm"),7);
//            }
//        }
//
//        if (response instanceof FormResponseCustom) {
//            FormResponseCustom customResponse = (FormResponseCustom) response;
//            if (event.getFormID() == 7) {
//                String name = customResponse.getInputResponse(0);
//                Player playerType = plugin.getServer().getPlayer(name);
//                if (playerType != null) {
//                    plugin.sendProfile(player, playerType.getName());
//                } else {
//                    try {
//                        Map<String, Object> aliasDataMap = AliasPro.AliasPro.getPlayer(name);
//                        if (aliasDataMap == null) {
//                            plugin.sendProfile(player, name);
//                        } else {
//                            String uuid = aliasDataMap.get("uuid").toString();
//                            Map<String, Object> objectMap = Mongodb.query(uuid, "uuid");
//                            if (objectMap == null) {
//                              plugin.sendProfile(player, name);
//                            } else {
//                                plugin.sendProfile(player, objectMap.get("name").toString());
//                            }
//                        }
//                    } catch (NoClassDefFoundError error) {
//                        plugin.sendProfile(player, name);
//                    }
//                }
//            }
//        }
//    }
}
