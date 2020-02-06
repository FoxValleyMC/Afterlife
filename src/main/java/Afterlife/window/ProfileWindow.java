package Afterlife.window;

import cn.nukkit.form.response.FormResponse;
import cn.nukkit.form.response.FormResponseModal;
import cn.nukkit.form.window.FormWindow;

public class ProfileWindow extends FormWindow {

    private final String type = "modal";
    private String title = "";
    private String content = "";
    private String button1 = "";
    private String button2 = "";

    private FormResponseModal response = null;

    public ProfileWindow() {

    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getButton1() {
        return this.button1;
    }

    public void setButton1(String button1) {
        this.button1 = button1;
    }

    public String getButton2() {
        return this.button2;
    }

    public void setButton2(String button2) {
        this.button2 = button2;
    }

    @Override
    public void setResponse(String data) {
        if (data.equals("null")) {
            this.closed = true;
            return;
        }

        if ("true".equals(data)) {

        } else {
            this.closed = true;
        }

    }

    @Override
    public FormResponse getResponse() {
        return this.response;
    }

}
