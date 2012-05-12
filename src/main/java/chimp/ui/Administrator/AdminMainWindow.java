package chimp.ui.Administrator;

import chimp.ui.Inspector.Inspector;
import chimp.ui.api.Form;
import chimp.ui.api.context.FormContext;
import chimp.ui.api.context.FormContextAware;
import com.jgoodies.forms.layout.ColumnSpec;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author koosha
 * @since 1.0 (5/12/12, 8:20 PM)
 */
public class AdminMainWindow implements Form , FormContextAware ,ApplicationContextAware{

    private JPanel AdminMainPane;
    private JTable events;
    private JButton run;
    private JScrollPane eventsPane;
    private JFrame frame;
    final static String Columns[]={"salam","khubi"};
    private FormContext formContext;
    private ApplicationContext applicationContext;

    public AdminMainWindow() {
        frame = new JFrame("AdminMainWindow");
        frame.setContentPane(AdminMainPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               //formContext.show(Inspector.class);
                Inspector inspector = applicationContext.getBean("inspector", Inspector.class);
                inspector.show();
                inspector.Tabs.setSelectedIndex(1);
               // events.setModel(dataModel);
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
        frame.setVisible(false);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private void createUIComponents() {
        String[] columnNames = {"شماره",
                "نوع رویداد",
                "شرح رویداد"
                };
        Object[][] data = {
                {new Integer(1), "درخواست ثبت شرکت",
                        "کاربر koosha درخواست ثبت شرکت koala را دارد"},
                {new Integer(2), "درخواست ثبت اختراع",
                        "کاربر mohammad درخواست ثبت اختراع جدید دارد"},
        };
        events = new JTable(data,columnNames);

        for (int i = 0; i < events.getColumnCount(); i++) {
            TableColumn column = events.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(50); //third column is bigger
            } else {
                column.setPreferredWidth(300);
            }
        }
       // events.repaint();
        // TODO: place custom component creation code here
    }

    @Override
    public void setFormContext(FormContext formContext) {

        //To change body of implemented methods use File | Settings | File Templates.
        this.formContext = formContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //To change body of implemented methods use File | Settings | File Templates.
        this.applicationContext = applicationContext;
    }
}
