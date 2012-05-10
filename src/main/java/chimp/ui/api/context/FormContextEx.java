package chimp.ui.api.context;

import chimp.ui.api.ShutdownCallback;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (12/5/6, 21:33)
 */
public interface FormContextEx extends FormContext {

    void setShutdownCallback(ShutdownCallback callback);

}
