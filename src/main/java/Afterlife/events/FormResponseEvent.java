package Afterlife.events;

import Afterlife.Main;
import PlayerAPI.Overrides.PlayerAPI;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.response.FormResponseCustom;
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
                    event.getPlayer().showFormWindow(plugin.formAPI.get("searchWindow"), plugin.formAPI.getId("searchWindow"));
                }

            }

        } else if (event.getResponse() instanceof FormResponseCustom) {

            FormResponseCustom response = (FormResponseCustom) event.getResponse();

            if (event.getFormID() == plugin.formAPI.getId("searchWindow")) {

                String inputText = response.getInputResponse(0);
                Main.getInstance().sendProfile((PlayerAPI) event.getPlayer(), inputText);

            }

        }

    }

}
