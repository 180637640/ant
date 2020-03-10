package cn.hs.web.controller;

import cn.hs.cache.MessageCache;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author swt
 */
public class BaseController {

	protected static Log log;
	private static MessageCache messageCache;
	/** JSON操作 */
	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * @return the log
	 */
	public final Log getLog() {
		if(null == log) {
			log = LogFactory.getLog(this.getClass());
		}
		return log;
	}

	public ObjectMapper getMapper() {
		return mapper;
	}

	/**
	 * Object对象转JSON
	 * @param value	对象
	 * @return		json字符串
	 */
	public String toJsonText(Object value) {
		if(null == value) {
			return null;
		}
		try {
			return getMapper().writeValueAsString(value);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * DEBUG级别日志
	 * @param message	错误描述
	 */
	public final void debug(Object message) {
		getLog().debug(message);
	}

	/**
	 * INFO级别日志
	 * @param message	错误描述
	 */
	public final void info(Object message) {
		getLog().info(message);
	}

	/**
	 * WARN级别日志
	 * @param message	错误描述
	 */
	public final void warn(Object message) {
		getLog().warn(message);
	}

	/**
	 * ERROR级别日志
	 * @param message	错误描述
	 */
	public final void error(Object message) {
		getLog().error(message);
	}

	/**
	 * ERROR级别日志
	 * @param message	错误描述
	 * @param t 		异常
	 */
	public final void error(Object message, Throwable t) {
		getLog().error(message, t);
	}

	/**
	 * 获取消息缓存
	 * @return			异常信息缓存
	 */
	public static MessageCache getMessageCache() {
		if(null == messageCache) {
			messageCache = MessageCache.getInstance();
		}
		return messageCache;
	}

	/**
	 * 获取缓存的消息
	 * @param code	编号
	 * @return		消息内容
	 */
	public final String getMessage(String code) {
		return getMessageCache().getExceptionMessage(code);
	}

}
