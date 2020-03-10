package cn.hs.web.controller;

import cn.hs.bean.base.SerializeObject;
import cn.hs.bean.type.ResultType;
import cn.hs.core.exception.UnLoginException;
import cn.hs.core.exception.UnauthorizedException;
import cn.hs.core.exception.XRuntimeException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 * @author swt
 */
@RestControllerAdvice
public class GlobalExceptionController extends BaseController {

	@ExceptionHandler(value = Exception.class)
	public SerializeObject defaultErrorHandler(Exception e) throws Exception {
		return new SerializeObject<>(ResultType.EXCEPTION, e.getMessage());
	}

	@ExceptionHandler(value = UnauthorizedException.class)
	public SerializeObject defaultErrorHandler(UnauthorizedException e) throws Exception {
		return new SerializeObject<>(ResultType.UNAUTH, e.getMessage());
	}

	@ExceptionHandler(value = UnLoginException.class)
	public SerializeObject defaultErrorHandler(UnLoginException e) throws Exception {
		return new SerializeObject<>(ResultType.UNLOGIN, e.getMessage());
	}

	@ExceptionHandler(value = XRuntimeException.class)
	public SerializeObject xRuntimeExceptionErrorHandler(Exception e) throws Exception {
		return new SerializeObject<>(ResultType.ERROR, e.getMessage());
	}

}
