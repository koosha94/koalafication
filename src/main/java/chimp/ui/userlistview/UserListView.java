package chimp.ui.userlistview;

import chimp.service.ServiceException;
import chimp.service.context.ServiceContext;
import chimp.service.context.ServiceContextAware;
import chimp.service.inspector.InspectorServiceMessage;
import chimp.ui.Inspector.Inspector;
import chimp.ui.api.Form;
import chimp.ui.api.context.FormContext;
import chimp.ui.api.context.FormContextAware;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author koosha
 * @since 1.0 (5/12/12, 4:16 PM)
 */
public class UserListView implements Form , ApplicationContextAware, FormContextAware ,ServiceContextAware{

    private JTabbedPane tabbedPane1;
    private JButton button1;
    private JList list1;
    private JTextField textField1;
    private JPanel UserListView;
    private JFrame frame;
    private ApplicationContext applicationContext;
    private FormContext formcontext;
    private ServiceContext serviceContext;

    public UserListView() {
        frame = new JFrame("UserListView");
        frame.setContentPane(UserListView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                formcontext.show(chimp.ui.Inspector.Inspector.class);
                Inspector inspector = applicationContext.getBean("inspector", Inspector.class);
               // inspector.setName(textField1.getText());
                try {
                    serviceContext.execute(new InspectorServiceMessage(textField1.getText()));
                } catch (ServiceException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }



    @Override
    public JFrame getFrame() {
        return frame;

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

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setFormContext(FormContext formContext) {
        formcontext = formContext;
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setServiceContext(ServiceContext serviceContext) {

        //To change body of implemented methods use File | Settings | File Templates.
        this.serviceContext = serviceContext;
    }
}
