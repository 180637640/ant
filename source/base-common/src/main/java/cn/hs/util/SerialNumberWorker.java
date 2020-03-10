package cn.hs.util;

import java.util.HashSet;
import java.util.Set;

/**
 * 流水号生成器
 * @author swt
 */
public class SerialNumberWorker {
    /** 序号 */
    private long sequence = 0L;
    /** 最后时间戳 */
    private Long lastTimestamp = 0L;

    private static class ISerialNumberHolder {
        private static final SerialNumberWorker instance = new SerialNumberWorker();
    }

    public static SerialNumberWorker getInstance() {
        return ISerialNumberHolder.instance;
    }

    private SerialNumberWorker() {

    }

    public synchronized String nextId() {
        Long timestamp = timeGen();
        // 如果上次生成时间和当前时间相同,在同一毫秒内
        if (lastTimestamp.equals(timestamp)) {
            sequence++;
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        return timestamp.toString() + sequence;
    }

    /**
     * ID生成 prefix区分业务
     * @param prefix
     * @return
     */
    public String nextId(String prefix) {
        return prefix + nextId();
    }

    private Long timeGen() {
        return Long.valueOf(UF.getFormatDateTime("yyyyMMddHHmmssSSS", UF.getDateTime()));
    }

    public static void main(String[] args) {
        Set<String> s = new HashSet<>();
		for(int i = 0; i < 1000000; i++) {
		    s.add(SerialNumberWorker.getInstance().nextId());
		}
		System.out.println(s.size());
    }

}