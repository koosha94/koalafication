package chimp.ui.Inspector;

import chimp.service.ServiceException;
import chimp.service.ServiceProvider;
import chimp.service.inspector.InspectorServiceMessage;
import chimp.ui.api.Form;
import chimp.ui.api.context.FormContext;
import chimp.ui.api.context.FormContextAware;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.swing.*;
import javax.swing.tree.TreeModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author koosha
 * @since 1.0 (5/10/12, 9:43 PM)
 */
@XmlRootElement
public class Inspector implements ServiceProvider<InspectorServiceMessage>,Form,FormContextAware,ApplicationContextAware{
    private JPanel Value;
    private JPanel Action;
    private JTabbedPane Tabs;
    private JTree tree1;
    private JTextField textField1;
    private JTextField textField2;
    private JList list1;
    private JButton commitButton;
    private JPanel mainPage;
    private JFrame frame;
    private FormContext formContext;
    private ApplicationContext applicationContext;
    public void setName(String name){
         textField1.setText(name);
    }
    public Inspector() {
        frame = new JFrame("Inspector");
        frame.setContentPane(mainPage);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        commitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    JAXBContext jaxbContext = JAXBContext.newInstance(chimp.ui.Inspector.Inspector.class);

                    Marshaller marshaller = jaxbContext.createMarshaller();
                    Inspector inspector = applicationContext.getBean("inspector", Inspector.class);
                    marshaller.marshal(inspector,System.out);


                } catch (JAXBException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
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
        frame.setVisible(true);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setFormContext(FormContext formContext) {
        this.formContext=formContext;
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext  = applicationContext;
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void execute(InspectorServiceMessage message) throws ServiceException {
        setName(message.getName());
    }
}
