package cn.hs.bean.base;

import java.io.Serializable;

/**
 * 基类
 * @author swt
 */
public class BaseBean implements Serializable {
	private static final long serialVersionUID = 1L;
	/** ID */
	private String id;
	/** 添加时间 */
	private String addTimeString;
	/** 修改时间 */
	private String modifyTimeString;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddTimeString() {
		return addTimeString;
	}

	public void setAddTimeString(String addTimeString) {
		this.addTimeString = addTimeString;
	}

	public String getModifyTimeString() {
		return modifyTimeString;
	}

	public void setModifyTimeString(String modifyTimeString) {
		this.modifyTimeString = modifyTimeString;
	}
}
