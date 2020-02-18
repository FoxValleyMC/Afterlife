package Afterlife.events;

import Afterlife.Main;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.response.FormResponseModal;

public class FormResponseEvent implements Listener {

    private Main plugin;

    public FormResponseEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler()
    public void onFormSubmit(PlayerFormRespondedEvent event) {

        if (event.getResponse() instanceof FormResponseModal) {

            FormResponseModal response = (FormResponseModal) event.getResponse();

            if (event.getFormID() == plugin.formAPI.getId("profile")) {

                if (response.getClickedButtonId() == 0) {
                    event.getPlayer().showFormWindow(plugin.formAPI.get("searchWindow"));
                }

            }

        }

    }

}
