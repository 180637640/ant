package cn.hs.web.listener;

import cn.hs.cache.MessageCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;

/**
 * 系统初始化
 * @author swt
 */
@Component
public class InitApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private MessageCache messageCache = MessageCache.getInstance();

    @Autowired
    private ResourcePatternResolver resourcePatternResolver;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            Resource[] resources = resourcePatternResolver.getResources("classpath*:message.properties");
            Properties properties = new Properties();
            for (Resource resource : resources) {
                properties.load(resource.getInputStream());
                break;
            }

            Set<String> names = properties.stringPropertyNames();
            for (String name : names) {
                messageCache.put(name, properties.getProperty(name));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
