package chimp.runner;

import chimp.ui.security.LoginForm;
import com.apple.eawt.Application;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (12/5/6, 18:36)
 */
public class Runner {

    private static void initApplication() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Chimp");
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        Application application = Application.getApplication();
        application.setDockIconImage(ImageIO.read(LoginForm.class.getResource("/web.png")));
        application.setDockMenu(getDockMenu());
        application.setDefaultMenuBar(getMenuBar());
    }

    private static JMenuBar getMenuBar() {
        final JMenuBar menuBar = new JMenuBar();
        final JMenu menu = new JMenu();
        menu.setText("File");
        final JMenuItem item = new JMenuItem("Open ...", 'o');
        item.setActionCommand("file-open");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
            }
        });
        menu.add(item);
        menu.addSeparator();
        final JMenuItem exitItem = new JMenuItem("Exit", 'x');
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(exitItem);
        menuBar.add(menu);
        return menuBar;
    }

    private static PopupMenu getDockMenu() {
        return new PopupMenu();
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, IllegalAccessException, InstantiationException, IOException {
        initApplication();
        new ClassPathXmlApplicationContext("/spring/context.xml");
    }

}
