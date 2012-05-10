package chimp.service;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (12/5/6, 19:38)
 */
public interface ServiceProvider<M extends ServiceMessage> {

    void execute(M message) throws ServiceException;

}
