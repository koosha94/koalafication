package chimp.ui.test;

import chimp.ui.api.Form;
import chimp.ui.api.context.FormContext;
import chimp.ui.api.context.FormContextAware;

import javax.swing.*;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (12/5/7, 13:48)
 */
public class MyForm implements Form, FormContextAware {

    private JPanel panel;
    private final JFrame frame;
    private FormContext formContext;

    public MyForm() {
        frame = new JFrame("MyForm");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
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

    @Override
    public void setFormContext(FormContext formContext) {
        this.formContext = formContext;
    }
}
