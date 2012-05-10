package chimp.service.context;

import chimp.service.ServiceException;
import chimp.service.ServiceMessage;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (12/5/6, 19:46)
 */
public interface ServiceContext {

    void execute(ServiceMessage message) throws ServiceException;

}
