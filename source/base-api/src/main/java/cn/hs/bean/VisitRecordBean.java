package cn.hs.bean;


import cn.hs.util.UF;

import java.util.Map;

/**
 * 访问日志
 * @author swt
 */
public class VisitRecordBean extends LogBean implements java.io.Serializable {

	/** 类名 */
	private String className;
	/** 类名 */
	private String methodName;
	/** IP */
	private String ip;
	/** 主机名 */
	private String hostName;
	/** 令牌 */
	private String token;
	/** 访问时间戳 */
	private long time;
	/** 参数 */
	private Map<String, String> parameterMap;

	public String getKey() {
		return UF.toString(className + "." + methodName);
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public Map<String, String> getParameterMap() {
		return parameterMap;
	}

	public void setParameterMap(Map<String, String> parameterMap) {
		this.parameterMap = parameterMap;
	}
}
