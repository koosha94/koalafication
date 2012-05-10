package chimp.business;

import chimp.ui.api.Form;
import chimp.ui.api.ShutdownCallback;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (12/5/6, 21:24)
 */
public class DefaultShutdownCallback implements ShutdownCallback {

    @Override
    public void perform(Form form) {
        System.out.println("CLOSED: " + form.getFrame().getTitle());
    }

}
