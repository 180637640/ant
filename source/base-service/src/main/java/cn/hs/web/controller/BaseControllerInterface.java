package cn.hs.web.controller;

import cn.hs.bean.base.SerializeObject;

/**
 * 通用服务基础接口
 * @author swt
 */
public interface BaseControllerInterface<T> {

    /**
     * 根据ID查找信息
     * @param accessToken	登录成功后分配的Key
     * @param id		    ID
     * @return			    记录集
     */
    SerializeObject<T> find(String accessToken, String id);

    /**
     * 新增信息（id为null、''）或修改信息（id不为空）
     * @param accessToken		登录成功后分配的Key
     * @param entity		    记录集
     * @return				    是否操作成功
     */
    SerializeObject update(String accessToken, T entity);

    /**
     * 删除信息(物理删除)
     * @param accessToken		登录成功后分配的Key
     * @param id			    ID数组
     * @return				    是否操作成功
     */
    SerializeObject delete(String accessToken, String[] id);

    /**
     * 删除信息(逻辑删除)
     * @param accessToken		登录成功后分配的Key
     * @param id			    ID数组
     * @return				    是否操作成功
     */
    SerializeObject remove(String accessToken, String[] id);

    /**
     * 验证是否存在
     * @param accessToken	    登录成功后分配的Key
     * @param entity	        验证参数
     * @return			        是否验证通过
     */
    SerializeObject verify(String accessToken, T entity);

}
