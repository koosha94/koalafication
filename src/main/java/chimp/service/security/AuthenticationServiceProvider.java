package chimp.service.security;

import chimp.service.ServiceException;
import chimp.service.ServiceProvider;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (12/5/6, 19:44)
 */
public class AuthenticationServiceProvider implements ServiceProvider<LoginServiceMessage> {

    @Override
    public void execute(LoginServiceMessage message) throws ServiceException {
        if (!message.getUsername().equals("milad") || !message.getPassword().equals("123456")) {
            throw new AuthenticationFailureException();
        }
    }

}
