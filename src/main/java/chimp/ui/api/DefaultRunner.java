package chimp.ui.api;

import chimp.ui.api.context.FormContext;
import chimp.ui.api.context.FormContextEx;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (12/5/6, 21:01)
 */
public class DefaultRunner implements ApplicationContextAware, Ordered {

    private Form defaultForm;
    private ShutdownCallback callback = null;

    public DefaultRunner(Form defaultForm) {
        this.defaultForm = defaultForm;
    }

    public DefaultRunner(Form defaultForm, ShutdownCallback callback) {
        this.defaultForm = defaultForm;
        this.callback = callback;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        final FormContext formContext = applicationContext.getBeansOfType(FormContext.class).values().iterator().next();
        if (formContext instanceof FormContextEx) {
            FormContextEx contextEx = (FormContextEx) formContext;
            contextEx.setShutdownCallback(callback);
        }
        formContext.show(defaultForm.getClass());
    }

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }
}
