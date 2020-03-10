package cn.hs.core.exception;

import java.io.Serializable;

/**
 * 未经授权的异常
 * @author swt
 */
public class UnauthorizedException extends RuntimeException implements Serializable{

    public UnauthorizedException() {
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
