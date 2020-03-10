package cn.hs.util;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 流水号生成器
 * @author swt
 */
public class SerialCodeWorker {
    private static final int MAX_SEQUENCE = 99999;
    /** 序号 */
    private AtomicInteger sequence = new AtomicInteger();
    /** 最后时间戳 */
    private long lastTimestamp = 0L;

    private static class SerialCodeWorkerHolder {
        private static final SerialCodeWorker instance = new SerialCodeWorker();
    }

    public static SerialCodeWorker getInstance() {
        return SerialCodeWorkerHolder.instance;
    }

    private SerialCodeWorker() {

    }

    public synchronized String nextId() {
        long timestamp = timeGen();
        // 如果上次生成时间和当前时间相同,在同一毫秒内
        if (lastTimestamp == timestamp) {
            int i = sequence.getAndIncrement();
            if(i > MAX_SEQUENCE) {
                timestamp = tilNextMillis(lastTimestamp);
                sequence.set(0);
            }
        } else {
            sequence.set(0);
        }
        lastTimestamp = timestamp;
        return timestamp + "" + String.format("%05d", sequence.get());
    }

    /**
     * ID生成 prefix区分业务
     * @param prefix
     * @return
     */
    public String nextId(String prefix) {
        return prefix + nextId();
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    public static void main(String[] args) {
        Set<String> s = new HashSet<>();
		for(int i = 0; i < 1000000; i++) {
		    s.add(SerialCodeWorker.getInstance().nextId());
		}
		System.out.println(s.size());
    }

}
