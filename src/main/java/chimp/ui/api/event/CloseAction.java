package chimp.ui.api.event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (12/5/6, 18:40)
 */
public class CloseAction extends AbstractAction {

    private FrameReferenceHandler referenceHandler;

    public CloseAction(FrameReferenceHandler referenceHandler) {
        this.referenceHandler = referenceHandler;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Frame[] frames = Frame.getFrames();
        for (Frame frame : frames) {
            if (frame.isActive() && frame.getName().equals(referenceHandler.getFrame().getName())) {
                frame.setVisible(false);
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        }
    }

}
