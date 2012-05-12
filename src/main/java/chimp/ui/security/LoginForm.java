package chimp.ui.security;

import chimp.service.ServiceException;
import chimp.service.context.ServiceContext;
import chimp.service.context.ServiceContextAware;
import chimp.service.security.LoginServiceMessage;
import chimp.ui.Administrator.AdminMainWindow;
import chimp.ui.api.Form;
import chimp.ui.api.components.ImagePanel;
import chimp.ui.api.context.FormContext;
import chimp.ui.api.context.FormContextAware;
import chimp.ui.api.pos.CenteredForm;
import chimp.ui.general.ApplicationMainForm;
import chimp.ui.api.event.CloseAction;
import chimp.ui.api.event.ContainedFrameReferenceHolder;
import chimp.ui.api.event.FrameReferenceHandler;
import chimp.ui.api.event.KeyActionBinder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (12/5/6, 17:23)
 */
public class LoginForm implements ServiceContextAware, Form, FormContextAware, CenteredForm {
    public static final int ESC_KEY_CODE = 27;
    private JPanel loginPanel;
    private JButton loginButton;
    private JButton cancelButton;
    @SuppressWarnings("UnusedDeclaration")
    private ImagePanel imagePanel;
    private JTextField screenNameField;
    private JPasswordField passwordField;
    private JFrame frame;
    private ServiceContext serviceContext;
    private FormContext formContext;

    public LoginForm() throws IOException, ClassNotFoundException, UnsupportedLookAndFeelException, IllegalAccessException, InstantiationException {
        frame = new JFrame("Login");
        frame.setContentPane(loginPanel);
        frame.pack();
        setProperties();
    }

    @Override
    public JFrame getFrame() {
        return frame;
    }

    @Override
    public void show() {
        frame.setVisible(true);
    }

    private void createUIComponents() {
        try {
            imagePanel = new ImagePanel(ImageIO.read(getClass().getResource("/green.png")));
        } catch (IOException ignored) {
        }
        loginButton = new JButton();
        cancelButton = new JButton();
        loginButton.setDefaultCapable(true);
        loginButton.setAction(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    final String username = screenNameField.getText();
                    if (username.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Username cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                        screenNameField.requestFocus();
                        return;
                    }
                    final String password = new String(passwordField.getPassword());
                    if (password.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                        passwordField.requestFocus();
                        return;
                    }
                    serviceContext.execute(new LoginServiceMessage(username, password));
                } catch (ServiceException e1) {
                    JOptionPane.showMessageDialog(frame, "Authentication failed.", "Error", JOptionPane.ERROR_MESSAGE);
                    screenNameField.requestFocus();
                    return;
                }
                hide();
                if(screenNameField.getText().equals("admin")){
                    formContext.show(AdminMainWindow.class);
                }
                else{
                    formContext.show(ApplicationMainForm.class);
                }
            }

        });
        final CloseAction closeAction = new CloseAction(new FrameReferenceHandler() {
            @Override
            public JFrame getFrame() {
                return frame;
            }
        });
        cancelButton.setAction(closeAction);
    }

    @Override
    public void hide() {
        frame.setVisible(false);
    }

    private void setProperties() {
        frame.getRootPane().setDefaultButton(loginButton);
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        final CloseAction closeAction = new CloseAction(new ContainedFrameReferenceHolder(frame));
        screenNameField.addKeyListener(new KeyActionBinder(ESC_KEY_CODE, closeAction));
        passwordField.addKeyListener(new KeyActionBinder(ESC_KEY_CODE, closeAction));
        loginButton.addKeyListener(new KeyActionBinder(ESC_KEY_CODE, closeAction));
        cancelButton.addKeyListener(new KeyActionBinder(ESC_KEY_CODE, closeAction));
    }

    @Override
    public void setServiceContext(ServiceContext serviceContext) {
        this.serviceContext = serviceContext;
    }

    @Override
    public void setFormContext(FormContext formContext) {
        this.formContext = formContext;
    }
}
