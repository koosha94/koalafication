package chimp.ui.general;

import chimp.ui.api.Form;
import chimp.ui.api.pos.MaximizedForm;

import javax.swing.*;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (12/5/6, 20:49)
 */
public class ApplicationMainForm implements Form, MaximizedForm {

    private JTree tree1;
    private JPanel mainPanel;
    private JFrame frame;

    public ApplicationMainForm() {
        frame = new JFrame("Chimp - Main");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setProperties();
        frame.pack();
    }

    private void setProperties() {
    }

    @Override
    public JFrame getFrame() {
        return frame;
    }

    @Override
    public void show() {
        frame.setVisible(true);
    }

    @Override
    public void hide() {
        frame.setVisible(false);
    }

}
