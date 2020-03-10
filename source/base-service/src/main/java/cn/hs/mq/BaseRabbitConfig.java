package cn.hs.mq;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息队列配置
 * @author swt
 */
public class BaseRabbitConfig {

    /**
     * 获取队列参数
     * @param maxLength     最大记录数
     * @return              参数
     */
    protected Map<String, Object> getArguments(int maxLength) {
        Map<String, Object> args = new HashMap<>();
        if(maxLength > 0) {
            args.put("x-max-length", maxLength);
        }
        return args;
    }

}
