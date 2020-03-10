package cn.hs.bean.base;

import java.io.Serializable;

/**
 * 键值对对象
 * @author swt
 */
public class KeyValueBean implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 键 */
	private String key;
	/** 值 */
	private String value;

	public KeyValueBean() {
	}

	public KeyValueBean(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
