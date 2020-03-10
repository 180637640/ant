package cn.hs.bean;


import cn.hs.bean.base.BaseBean;

/**
 * 任务信息
 * @author swt
 */
public class JobBean extends BaseBean implements java.io.Serializable {

	/** 类型 */
	private Integer type;
	/** 标题 */
	private String name;
	/** 状态(1:待执行 2:执行中 3:异常 4：执行结束) */
	private Integer status;
	/** 异常信息 */
	private String latestException;
	/** 下次更新时间 */
	private long nextUpdateTime = 0;
	/** 本次更新总条数 */
	private long totalRow;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLatestException() {
		return latestException;
	}

	public void setLatestException(String latestException) {
		this.latestException = latestException;
	}

	public long getNextUpdateTime() {
		return nextUpdateTime;
	}

	public void setNextUpdateTime(long nextUpdateTime) {
		this.nextUpdateTime = nextUpdateTime;
	}

	public long getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(long totalRow) {
		this.totalRow = totalRow;
	}
}
