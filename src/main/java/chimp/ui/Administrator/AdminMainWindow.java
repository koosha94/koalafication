package chimp.ui.Administrator;

import chimp.ui.api.Form;

import javax.swing.*;

/**
 * @author koosha
 * @since 1.0 (5/12/12, 8:20 PM)
 */
public class AdminMainWindow implements Form {

    private JPanel AdminMainPane;
    private JFrame frame;

    public AdminMainWindow() {
        frame = new JFrame("AdminMainWindow");
        frame.setContentPane(AdminMainPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }


    @Override
    public JFrame getFrame() {

        return frame;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void show() {
        frame.setVisible(true);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void hide() {
        frame.setVisible(false);
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
