package cn.hs.bean.type;

/**
 * 登录账号类型
 * @author swt
 */
public class LoginType extends BaseType {
	/** 维护账号 */
	public static final int ADMINISTRATOR 	= 1;
	/** 用户 */
	public static final int USER 			= 2;
	/** 会员 */
	public static final int MEMBER 			= 3;
	/** 浙江易舸单点登录 */
	public static final int CAS 			= 4;
}
