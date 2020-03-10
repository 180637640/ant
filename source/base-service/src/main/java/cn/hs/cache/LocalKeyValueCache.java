package cn.hs.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 本地键值对缓存（本模块有效）
 * @author swt
 */
public class LocalKeyValueCache {

    private ConcurrentMap<String, String> map = new ConcurrentHashMap<>();

    private LocalKeyValueCache(){

    }

    static class MessageCacheCacheHolder {
        private static final LocalKeyValueCache INSTANCE = new LocalKeyValueCache();
    }

    public static LocalKeyValueCache getInstance() {
        return MessageCacheCacheHolder.INSTANCE;
    }

    public String get(String key) {
        return map.get(key);
    }

    public void put(String key, String value) {
        map.put(key, value);
    }
}
