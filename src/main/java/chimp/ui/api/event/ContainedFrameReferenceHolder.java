package chimp.ui.api.event;

import javax.swing.*;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (12/5/6, 19:10)
 */
public class ContainedFrameReferenceHolder implements FrameReferenceHandler {

    private final JFrame frame;

    public ContainedFrameReferenceHolder(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public JFrame getFrame() {
        return frame;
    }
}
