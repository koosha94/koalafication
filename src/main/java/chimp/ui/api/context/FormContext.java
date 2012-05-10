package chimp.ui.api.context;

import chimp.ui.api.Form;

import java.util.Collection;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (12/5/6, 20:51)
 */
public interface FormContext {

    void show(Class<? extends Form> formClass);

    Collection<? extends Form> getForms();

}
