package chimp.ui.api.context;

import chimp.ui.api.Form;
import chimp.ui.api.ShutdownCallback;
import chimp.ui.api.pos.CenteredForm;
import chimp.ui.api.pos.MaximizedForm;
import chimp.ui.api.pos.PositionedForm;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (12/5/6, 20:52)
 */
public class DefaultFormContext implements FormContextEx, BeanPostProcessor {

    private Map<Class<? extends Form>, Form> map = new HashMap<Class<? extends Form>, Form>();
    private ShutdownCallback callback = null;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Form) {
            final Form form = (Form) bean;
            form.getFrame().setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            form.getFrame().addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    if (callback != null) {
                        callback.perform(form);
                    }
                    form.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
            });
            map.put(form.getClass(), form);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof FormContextAware) {
            FormContextAware aware = (FormContextAware) bean;
            aware.setFormContext(this);
        }
        return bean;
    }

    @Override
    public void show(Class<? extends Form> formClass) {
        final Form form = map.get(formClass);
        if (form instanceof PositionedForm) {
            PositionedForm positionedForm = (PositionedForm) form;
            final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
            final JFrame frame = form.getFrame();
            if (positionedForm instanceof CenteredForm) {
                frame.setBounds(size.width / 2 - frame.getWidth() / 2, size.height / 2 - frame.getHeight() / 2, frame.getWidth(), frame.getHeight());
            }
            if (positionedForm instanceof MaximizedForm) {
                frame.setBounds(0, 0, size.width, size.height);
            }
        }
        form.show();
    }

    @Override
    public Collection<? extends Form> getForms() {
        return map.values();
    }

    @Override
    public void setShutdownCallback(ShutdownCallback callback) {
        this.callback = callback;
    }
}
