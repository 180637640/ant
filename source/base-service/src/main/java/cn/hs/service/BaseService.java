package cn.hs.service;


/**
 * 数据操作基类接口
 * @author swt
 */
public interface BaseService<T> {

	/**
	 * 添加
	 * @param entityBean	实体
	 * @return 				0:操作成功 1：不能为空 2：数据已存在
	 */
	int save(T entityBean);

	/**
	 * 编辑
	 * @param id			ID
	 * @param entityBean	实体
	 * @return 				0:操作成功 1：不能为空 2：数据已存在
	 */
	int update(String id, T entityBean);

	/**
	 * 根据ID删除
	 * @param id	ID
	 * @return		操作影响的记录数
	 */
	int delete(String id);

	/**
	 * 删除对象
	 * @param id	ID数组
	 * @return		操作影响的记录数	0：请求参数有误 >=1：操作成功
	 */
	int deleteByIds(String[] id);

	/**
	 * 删除对象(逻辑删除)
	 * @param id	ID数组
	 * @return		操作影响的记录数	0：请求参数有误 >=1：操作成功
	 */
	int removeByIds(String[] id);

	/**
     * 判断数据是否存在
     * @param entityBean	数据对象
     * @param id			例外ID
     * @return				True 是 | False 否
     */
    boolean exists(T entityBean, String id);

	/**
	 * 根据ID, 查找对象
	 * @param id	ID
	 * @return		实例
	 */
	T findById(String id);

}
