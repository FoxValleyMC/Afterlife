package Afterlife.window;

import cn.nukkit.form.window.FormWindowModal;

public class ProfileWindow extends FormWindowModal {

    public ProfileWindow(String title, String content, String trueButtonText, String falseButtonText) {
        super(title, content, trueButtonText, falseButtonText);
    }

    @Override
    public void setResponse(String data) {
        System.out.println(data);
    }

    @Override
    public String toString() {
        return "ModalFormWindow(window=profile, true="+getButton1()+", false="+getButton2()+")";
    }
}
