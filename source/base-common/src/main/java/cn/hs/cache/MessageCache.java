package cn.hs.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 异常信息缓存
 * @author swt
 */
public class MessageCache {

    private ConcurrentMap<String, String> map = new ConcurrentHashMap<>();

    private MessageCache(){

    }

    static class MessageCacheCacheHolder {
        private static final MessageCache INSTANCE = new MessageCache();
    }

    public static MessageCache getInstance() {
        return MessageCacheCacheHolder.INSTANCE;
    }

    public String getExceptionMessage(String key) {
        return map.get(key);
    }

    public void put(String key, String value) {
        map.put(key, value);
    }
}
