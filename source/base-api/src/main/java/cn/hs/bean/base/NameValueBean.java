package cn.hs.bean.base;

import java.io.Serializable;

/**
 * 键值对对象
 * @author swt
 */
public class NameValueBean implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 名称 */
	private String name;
	/** 值 */
	private String value;

	public NameValueBean() {
	}

	public NameValueBean(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
