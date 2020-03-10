package cn.hs.service;


import cn.hs.bean.LoginBean;
import cn.hs.bean.base.DataTable;

/**
 * 登录信息缓存管理
 * @author 宋文涛
 */
public interface LoginInfoService {

    /**
     * 返回此登录信息缓存中指定键所映射到的值
     * <br>通过登录Session Id获取登录信息
     * @param key		主键
     * @return			实体
     */
    LoginBean get(String key);

    /**
     * 将指定 key 映射到此登录信息缓存中的指定 value
     * @param key		主键
     * @param value		值
     * @return			实体
     */
    LoginBean put(String key, LoginBean value);

    /**
     * 从登录信息缓存中移除该键及其相应的值
     * @param key		主键
     * @return			实体
     */
    LoginBean remove(String key);

    /**
     * 刷新最后响应时间
     * @param key		主键
     */
    void refreshTime(String key);

    /**
     * 检索在线用户数据
     * @param pageNum   页码
     * @param pageSize  每页记录数
     * @param loginType 登录账号类型(1:维护账号 2：内部用户 3：会员)
     * @param keyword   关键字
     * @return          数据列表
     */
    DataTable<LoginBean> query(int pageNum, int pageSize, Integer loginType, String keyword);

}
