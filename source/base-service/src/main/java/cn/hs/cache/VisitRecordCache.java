package cn.hs.cache;

import cn.hs.bean.VisitRecordBean;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 访问记录缓存（本模块有效）
 * @author swt
 */
public class VisitRecordCache {
    /** 队列最大等待数量 */
    private static int MAX_WAIT_SIZE = 1000;

    /** 接口访问计数 */
    private ConcurrentMap<String, AtomicInteger> countMap = new ConcurrentHashMap<>();
    /** 接口访问记录（保存到内存时使用） */
    private List<VisitRecordBean> recordBeanList = new CopyOnWriteArrayList<>();

    private VisitRecordCache(){

    }

    private static class MessageCacheCacheHolder {
        private static final VisitRecordCache INSTANCE = new VisitRecordCache();
    }

    public static VisitRecordCache getInstance() {
        return MessageCacheCacheHolder.INSTANCE;
    }

    /**
     * 查询访问记录明细
     * @return      记录明细
     */
    public List<VisitRecordBean> get() {
        return recordBeanList;
    }

    /**
     * 查询访问统计信息
     * @return      访问计数
     */
    public ConcurrentMap<String, AtomicInteger> getCountMap() {
        return countMap;
    }

    /**
     * 放入缓存
     * @param value 访问记录
     */
    public void put(VisitRecordBean value) {
        recordBeanList.add(value);
        String key = value.getKey();
        if(!countMap.containsKey(key)) {
            countMap.put(key, new AtomicInteger(0));
        }
        // 计数器
        countMap.get(key).getAndIncrement();

        // 压入缓存
        if(recordBeanList.size() > MAX_WAIT_SIZE) {
            // 移除数量
            int cleanSize = MAX_WAIT_SIZE / 5;
            for (int i = 0; i < cleanSize; i++) {
                if(recordBeanList.isEmpty()) {
                    break;
                }
                recordBeanList.remove(0);
            }
        }
    }
}
