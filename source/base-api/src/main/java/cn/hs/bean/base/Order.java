package cn.hs.bean.base;

import java.io.Serializable;

/**
 * 排序
 * @author swt
 */
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 排序字段 */
	private String order 	= "";
	/** 排序方式 desc或asc */
	private String sort		= "";

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
}
