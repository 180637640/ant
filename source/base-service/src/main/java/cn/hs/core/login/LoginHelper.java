package cn.hs.core.login;

import cn.hs.bean.LoginBean;
import cn.hs.service.LoginInfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录信息
 * @author swt
 */
public class LoginHelper {
    private static Log log = LogFactory.getLog(LoginHelper.class);

    /** 登录信息 */
    private static final String KEY_LOGIN_INFO = "KEY_LOGIN_INFO";

    /** 登录Token */
    private static final String KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN";

    /** 本地线程登录信息 */
    private static final ThreadLocal<Map<String, Object>> LOCAL_MAP = new ThreadLocal<>();

    /** 登录信息 */
    private static LoginInfoService loginInfoService;

    public static void setLoginInfoService(LoginInfoService loginInfoService) {
        LoginHelper.loginInfoService = loginInfoService;
    }

    /**
     * 获取登录信息
     * @return
     */
    public static LoginBean getLoginBean() {
        init();
        return getLocalLoginBean();
    }

    /**
     * 获取登录ID
     * @return  ID
     */
    public static String getLoginId() {
        LoginBean loginBean = getLoginBean();
        if(null == loginBean) {
            return null;
        }
        return loginBean.getId();
    }

    /**
     * 获取登录用户名
     * @return  用户名
     */
    public static String getLoginName() {
        LoginBean loginBean = getLoginBean();
        if(null == loginBean) {
            return null;
        }
        return loginBean.getLoginName();
    }

    /**
     * 获取名称
     * @return  名称
     */
    public static String getName() {
        LoginBean loginBean = getLoginBean();
        if(null == loginBean) {
            return null;
        }
        return loginBean.getName();
    }

    /**
     * 移除本地变量
     */
    public static void clear() {
        LOCAL_MAP.remove();
    }

    /**
     * 设置登录Token
     * @param accessToken   登录成功后分配的Token
     */
    public static void setLocalAccessToken(String accessToken) {
        Map<String, Object> map = LOCAL_MAP.get();
        if(null == map) {
            map = new HashMap<>();
        }
        map.put(KEY_ACCESS_TOKEN, accessToken);
        LOCAL_MAP.set(map);
    }

    private static LoginBean getLocalLoginBean() {
        Map<String, Object> map = LOCAL_MAP.get();
        if(null == map) {
//            log.info("accessToken: " + getLocalAccessToken());
            return null;
        }
        if(!map.containsKey(KEY_LOGIN_INFO)) {
//            log.info("accessToken: " + getLocalAccessToken());
            return null;
        }
        return (LoginBean) map.get(KEY_LOGIN_INFO);
    }

    private static String getLocalAccessToken() {
        Map<String, Object> map = LOCAL_MAP.get();
        if(null == map) {
            return null;
        }
        if(!map.containsKey(KEY_ACCESS_TOKEN)) {
            return null;
        }
        return (String) map.get(KEY_ACCESS_TOKEN);
    }

    /**
     * 调用之前初始化登录信息
     */
    private static void init() {
        Map<String, Object> map = LOCAL_MAP.get();
        if(null == map) {
            return;
        }
        String accessToken = getLocalAccessToken();
        if(StringUtils.isBlank(accessToken)) {
            return;
        }

        // 判断有没有初始化登录信息
        LoginBean loginBean = getLocalLoginBean();
        if(null != loginBean) {
            return;
        }

        // 初始化登录信息
        loginBean = loginInfoService.get(accessToken);
        if(null == loginBean) {
            return;
        }
        map.put(KEY_LOGIN_INFO, loginBean);

        LOCAL_MAP.set(map);
    }
}
