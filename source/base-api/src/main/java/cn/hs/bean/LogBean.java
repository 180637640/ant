package cn.hs.bean;


import cn.hs.bean.base.BaseBean;

/**
 * 操作日志
 * @author swt
 */
public class LogBean extends BaseBean implements java.io.Serializable {

	/** 功能 */
	private String type;
	/** 功能流水号 */
	private String thirdNumber;
	/** 用户类型 1：维护账号 2：用户 3：会员 */
	private Integer userType;
	/** 用户id */
	private String userId;
	/** 用户名称 */
	private String userName;
	/** 用户登录名 */
	private String userLoginName;
	/** 备注 */
	private String description;
	/** 参数 */
	private String parameterMapJson;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getThirdNumber() {
		return thirdNumber;
	}

	public void setThirdNumber(String thirdNumber) {
		this.thirdNumber = thirdNumber;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserLoginName() {
		return userLoginName;
	}

	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParameterMapJson() {
		return parameterMapJson;
	}

	public void setParameterMapJson(String parameterMapJson) {
		this.parameterMapJson = parameterMapJson;
	}
}
