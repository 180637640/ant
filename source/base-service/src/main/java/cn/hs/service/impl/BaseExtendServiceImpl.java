package cn.hs.service.impl;

import cn.hs.aop.RemoveCache;
import cn.hs.aop.SaveCache;
import cn.hs.bean.base.BaseBean;
import cn.hs.bean.status.DeleteStatus;
import cn.hs.core.ensure.Ensure;
import cn.hs.mapper.BaseExtendMapper;
import cn.hs.mapper.entity.BaseEntity;
import cn.hs.service.BaseService;
import cn.hs.util.UF;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static cn.hs.config.CacheType.OBJECT;

/**
 * 数据操作基类实现类
 * @author swt
 */
public abstract class BaseExtendServiceImpl<B extends BaseBean, E extends BaseEntity, M extends BaseExtendMapper<E>>
		extends BaseServiceImpl
		implements BaseService<B> {

	/**
	 * 获取B.class
	 * @return
	 */
	private Class<B> getBean() {
		Type type = getClass().getGenericSuperclass();
		Class<B> result = null;
		if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) type;
			result = (Class<B>) parameterizedType.getActualTypeArguments()[0];
		}
		return result;
	}

	/**
	 * 获取B.class
	 * @return
	 */
	private Class<E> getEntry() {
		Type type = getClass().getGenericSuperclass();
		Class<E> result = null;
		if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) type;
			result = (Class<E>) parameterizedType.getActualTypeArguments()[0];
		}
		return result;
	}

	/**
	 * 属性拷贝
	 */
	@Resource
	protected DozerBeanMapper dozerBeanMapper;

	/**
	 * 获取持久化服务类
	 * @return		Mapper
	 */
	protected abstract M getExtendMapper();

	/**
	 * Bean转Entity	子类可以重写覆盖该方法
	 * @param entityBean	Bean
	 * @return				Entity
	 */
	protected E toEntity(B entityBean) {
		return dozerBeanMapper.map(entityBean, getEntry());
	}

	/**
	 * Entity转Bean	子类可以重写覆盖该方法
	 * @param entity		Entity
	 * @return				Bean
	 */
	protected B toBean(E entity) {
		B o = dozerBeanMapper.map(entity, getBean());
		o.setAddTimeString(UF.getFormatDateTime(entity.getAddTime()));
		o.setModifyTimeString(UF.getFormatDateTime(entity.getModifyTime()));
		return o;
	}

	/**
	 * Entity集合转Bean集合
	 * @param list			Entity 集合
	 * @return				Bean集合
	 */
	protected List<B> toListBean(Collection<E> list) {
		if(null == list || list.isEmpty()) {
			return null;
		}
		List<B> dataList = new ArrayList<>(list.size());
		for (E entity : list) {
			dataList.add(toBean(entity));
		}
		return dataList;
	}

	/**
	 * 数据是否存在，不启用唯一验证则子类不需要重写该方法
	 * @param entityBean	数据对象
	 * @param id			例外ID
	 * @return				是否已经存在
	 */
	@Override
	public boolean exists(B entityBean, String id) {
		return false;
	}

	@Override
	@RemoveCache(cleanSearch = true)
	public int save(B entityBean) {
		// 数据完整性校验
		if(null == entityBean) {
			Ensure.that(true).isTrue("00000002");
		}
		// 数据完整性校验
		if(exists(entityBean, entityBean.getId())){
			// 该数据已存在
			Ensure.that(true).isTrue("00000004");
		}
		E entity = toEntity(entityBean);
		if(StringUtils.isBlank(entity.getId())) {
			entity.setId(UF.getRandomUUID());
		}
		getExtendMapper().insert(entity);

		return 0;
	}


	@Override
	@RemoveCache(cleanSearch = true, cleanObjectByKeyPosition = 0)
	public int update(String id, B entityBean) {
		// 数据完整性校验
		if(null == entityBean || StringUtils.isBlank(entityBean.getId())) {
			Ensure.that(true).isTrue("00000002");
		}
		if(exists(entityBean, entityBean.getId())){
			// 该数据已存在
			Ensure.that(true).isTrue("00000004");
		}
		E entity = toEntity(entityBean);
		getExtendMapper().updateById(entity);
		return 0;
	}

	@Override
	@RemoveCache(cleanSearch = true, cleanObjectByKeyPosition = 0)
	public int delete(String id) {
		if(StringUtils.isBlank(id)) {
			return 0;
		}

		return getExtendMapper().deleteById(id);
	}

	@Override
	@RemoveCache(cleanSearch = true, cleanObjectByKeyPosition = 0)
	public int deleteByIds(String[] id){
		if(id == null || id.length<=0){
			return 0;
		}

		return getExtendMapper().deleteBatchIds(Arrays.asList(id));
	}

	@Override
	@RemoveCache(cleanSearch = true, cleanObjectByKeyPosition = 0)
	public int removeByIds(String[] id) {
		if(id == null || id.length<=0){
			return 0;
		}

		return getExtendMapper().updateByPrimaryKeySelective(
				getParams()
						.put("id", id)
						.put("deleted", DeleteStatus.YES)
						.toMap()
		);
	}

	@Override
	@SaveCache(cacheType = OBJECT)
	public B findById(String id) {
		if(StringUtils.isBlank(id)) {
			return null;
		}

		E entity = getExtendMapper().selectById(id);
		if(null == entity) {
			return null;
		}
		return toBean(entity);
	}

}
