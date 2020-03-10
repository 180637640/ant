package cn.hs.core;

import cn.hs.bean.base.SerializeObject;
import cn.hs.bean.type.ResultType;
import cn.hs.cache.MessageCache;

/**
 * 错误返回值
 * @author swt
 */
public class SerializeObjectError<T> extends SerializeObject<T> {

    public SerializeObjectError(String code, String... args) {
        this.setCode(ResultType.ERROR);
        String msg = MessageCache.getInstance().getExceptionMessage(code);
        if (args.length > 0) {
            msg = String.format(msg, args);
        }
        this.setMsg(msg);
    }
}
