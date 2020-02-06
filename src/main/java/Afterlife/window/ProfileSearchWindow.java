package Afterlife.window;

import cn.nukkit.form.element.Element;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.response.FormResponse;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.window.FormWindow;

import java.util.List;

public class ProfileSearchWindow extends FormWindow {

    private final String type = "custom_form";
    private String title = "";
    private ElementButtonImageData icon;
    private List<Element> content;

    private FormResponseCustom response;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Element> getElements() {
        return content;
    }

    public void addElement(Element element) {
        content.add(element);
    }

    public ElementButtonImageData getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        if (!icon.isEmpty()) this.icon = new ElementButtonImageData(ElementButtonImageData.IMAGE_DATA_TYPE_URL, icon);
    }

    public void setIcon(ElementButtonImageData icon) {
        this.icon = icon;
    }

    @Override
    public FormResponse getResponse() {
        return response;
    }

    @Override
    public void setResponse(String data) {
        System.out.println(data);
    }
}
