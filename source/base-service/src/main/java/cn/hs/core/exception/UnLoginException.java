package cn.hs.core.exception;

import java.io.Serializable;

/**
 * 未登录的异常
 * @author swt
 */
public class UnLoginException extends RuntimeException implements Serializable{

    public UnLoginException() {
    }

    public UnLoginException(String message) {
        super(message);
    }

    public UnLoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
