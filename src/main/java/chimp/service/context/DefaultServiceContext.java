package chimp.service.context;

import chimp.service.ServiceException;
import chimp.service.ServiceMessage;
import chimp.service.ServiceProvider;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohammad Milad Naseri (m.m.naseri@gmail.com)
 * @since 1.0 (12/5/6, 19:49)
 */
public class DefaultServiceContext implements ServiceContext, BeanPostProcessor {

    private Map<Class<?>, ServiceProvider> map = new HashMap<Class<?>, ServiceProvider>();
    private Logger logger = Logger.getLogger("service-context");

    public DefaultServiceContext() {
        logger.info("Starting service context");
    }

    @Override
    public void execute(ServiceMessage message) throws ServiceException {
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }
        @SuppressWarnings("unchecked")
        final ServiceProvider<ServiceMessage> provider = map.get(message.getClass());
        provider.execute(message);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ServiceProvider) {
            final ServiceProvider provider = (ServiceProvider) bean;
            final Class type = (Class) ((ParameterizedType) provider.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
            logger.info("<" + provider.getClass().getSimpleName() + "> will provide service for <" + type.getSimpleName() + ">");
            map.put(type, provider);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ServiceContextAware) {
            ServiceContextAware aware = (ServiceContextAware) bean;
            aware.setServiceContext(this);
        }
        return bean;
    }

}
