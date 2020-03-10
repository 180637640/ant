package cn.hs.bean.base;

import cn.hs.bean.type.ResultType;
import cn.hs.cache.MessageCache;

import java.io.Serializable;

/**
 * 返回值
 * @author swt
 */
public class SerializeObject<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private int code 	= ResultType.NORMAL;
	private String msg 	= "";
	private T data;
	
	public SerializeObject() {
		
	}

	public SerializeObject(int code) {
		super();
		this.code = code;
		this.data = null;
	}

	public SerializeObject(int code, String msg) {
		super();
		this.code = code;
		this.msg = getMessage(msg);
	}

	public SerializeObject(int code, T data) {
		super();
		this.code = code;
		this.data = data;
	}

	public SerializeObject(int code, String msg, T data) {
		super();
		this.code = code;
		this.msg = getMessage(msg);
		this.data = data;
	}

	/**
	 * 从缓存获取数据
	 * @param msgCode	消息ID
	 * @return			消息内容
	 */
	private String getMessage(String msgCode) {
		String msg = MessageCache.getInstance().getExceptionMessage(msgCode);
		if(null == msg) {
			return msgCode;
		}
		return msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}
