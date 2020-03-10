package cn.hs.core.exception;

import java.io.Serializable;

/**
 * 运行异常（可以导致数据回滚）
 * @author swt
 */
public class XRuntimeException extends RuntimeException implements Serializable{

    public XRuntimeException() {
    }

    public XRuntimeException(String message) {
        super(message);
    }

    public XRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
