package chimp.ui.test;

import chimp.ui.Inspector.Inspector;
import chimp.ui.api.Form;
import chimp.ui.api.context.FormContext;
import chimp.ui.api.context.FormContextAware;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (12/5/7, 13:48)
 */
public class MyForm implements Form, FormContextAware, ApplicationContextAware {

    private JPanel panel;
    private JButton button1;
    private final JFrame frame;
    private FormContext formContext;
    private ApplicationContext applicationContext;

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

    private void createUIComponents() {
        button1=new JButton();
        button1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Inspector inspector = applicationContext.getBean("inspector", Inspector.class);

                formContext.show(Inspector.class);
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        // TODO: place custom component creation code here 
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
