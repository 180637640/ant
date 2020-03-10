package cn.hs.bean;


import cn.hs.bean.type.LoginSource;
import cn.hs.bean.type.LoginType;
import cn.hs.bean.type.OnlineType;
import cn.hs.bean.type.YesNo;

import java.util.Set;

/**
 * 登录账号信息
 * @author swt
 */
public class LoginBean implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int loginType 			= LoginType.ADMINISTRATOR;
	private int loginSource 		= LoginSource.WEB;
	private String accessToken		= "";
	private String refreshToken		= "";
	private String id 				= "";
	private String loginName 		= "";
	private String headImage		= "";
	private String name 			= "";
	private String mobile 			= "";
	private String ip 				= "";
	private String hostName 		= "";
	private long loginTime			= 0;
	private long updateTimeMillis 	= 0;
	private int onlineType 			= OnlineType.ONLINE;

	/** 权限列表 */
	private Set<String> authorizeSet;
	/** 部门 */
	private String departmentId;
	private String departmentName;
	/** 单位（部门所在单位） */
	private String upperDepartmentId;
	private String upperDepartmentName;

	public String getUpperDepartmentId() {
		return upperDepartmentId;
	}

	public void setUpperDepartmentId(String upperDepartmentId) {
		this.upperDepartmentId = upperDepartmentId;
	}

	public String getUpperDepartmentName() {
		return upperDepartmentName;
	}

	public void setUpperDepartmentName(String upperDepartmentName) {
		this.upperDepartmentName = upperDepartmentName;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getLoginType() {
		return loginType;
	}

	public void setLoginType(int loginType) {
		this.loginType = loginType;
	}

	public int getLoginSource() {
		return loginSource;
	}

	public void setLoginSource(int loginSource) {
		this.loginSource = loginSource;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}

	public long getUpdateTimeMillis() {
		return updateTimeMillis;
	}

	public void setUpdateTimeMillis(long updateTimeMillis) {
		this.updateTimeMillis = updateTimeMillis;
	}

	public int getOnlineType() {
		return onlineType;
	}

	public void setOnlineType(int onlineType) {
		this.onlineType = onlineType;
	}

	public Set<String> getAuthorizeSet() {
		return authorizeSet;
	}

	public void setAuthorizeSet(Set<String> authorizeSet) {
		this.authorizeSet = authorizeSet;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
}
