package chimp.ui.api.event;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (12/5/6, 19:06)
 */
public class KeyActionBinder extends KeyAdapter {

    private int keyCode;
    private Action action;

    public KeyActionBinder(int keyCode, Action action) {
        this.keyCode = keyCode;
        this.action = action;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == keyCode) {
            action.actionPerformed(new ActionEvent(this, 0, "perform"));
        }
    }
}
