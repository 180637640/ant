package cn.hs.service.impl;

import cn.hs.util.ParameterMap;
import cn.hs.util.UF;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 数据操作基类实现类
 * @author swt
 */
public class BaseServiceImpl {

	private Log log;
	/** JSON操作 */
	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * @return the log
	 */
	protected final Log getLog() {
		if (null == log) {
			log = LogFactory.getLog(this.getClass());
		}
		return log;
	}

	/**
	 * DEBUG级别日志
	 * @param message
	 */
	public final void debug(Object message) {
		getLog().debug(message);
	}

	/**
	 * INFO级别日志
	 * @param message
	 */
	public final void info(Object message) {
		getLog().info(message);
	}

	/**
	 * WARN级别日志
	 * @param message
	 */
	public final void warn(Object message) {
		getLog().warn(message);
	}

	/**
	 * ERROR级别日志
	 * @param message
	 */
	public final void error(Object message) {
		getLog().error(message);
	}

	/**
	 * ERROR级别日志
	 * @param message
	 * @param t
	 */
	public final void error(Object message, Throwable t) {
		getLog().error(message, t);
	}

	/**
	 * 获取ObjectMapper
	 * @return ObjectMapper
	 */
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
	 * 获取参数集合
	 * @return	ParameterMap
	 */
	protected ParameterMap getParams() {
		return new ParameterMap();
	}

	/**
	 * 获取参数集合
	 * @param keyword           关键字
	 * @param order			    排序字段
	 * @param sort			    排序方式 desc或asc
	 * @return					ParameterMap
	 */
	protected ParameterMap getParams(String keyword, String order, String sort) {
		return getParams()
				.put("keyword", UF.escapeSql(keyword))
				.put("order", order)
				.put("sort", sort);
	}

}
