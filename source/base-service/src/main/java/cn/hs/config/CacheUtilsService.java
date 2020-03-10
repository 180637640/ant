package cn.hs.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 缓存服务
 * @author swt
 */
@Service
public class CacheUtilsService extends CacheBaseService{
	private static Log log = LogFactory.getLog(CacheUtilsService.class);
	private static final String TYPE_NAME = CacheUtilsService.class.getTypeName();

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * 清除缓存
	 * @param hashKey	缓存key
	 * @return			缓存数据
	 */
	public void deleteCache(String hashKey) {
		deleteCache(TYPE_NAME, hashKey);
	}

	/**
	 * 清除缓存
	 * @param typeName	类名
	 * @param hashKey	缓存key
	 * @return			缓存数据
	 */
	public void deleteCache(String typeName, String hashKey) {
		if(null == hashKey || null == redisTemplate) {
			return;
		}

		try {
			String key = getPrefix(typeName) + ":" + hashKey;
			redisTemplate.delete(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}


	/**
	 * 获取缓存
	 * @param hashKey	缓存key
	 * @return			缓存数据
	 */
	public Object getCache(String hashKey) {
		return getCache(TYPE_NAME, hashKey);
	}

	/**
	 * 获取缓存
	 * @param typeName	类名
	 * @param hashKey	缓存key
	 * @return			缓存数据
	 */
	public Object getCache(String typeName, String hashKey) {
		if(null == hashKey || null == redisTemplate) {
			return null;
		}

		Object obj = null;
		try {
			String key = getPrefix(typeName) + ":" + hashKey;
			obj = redisTemplate.opsForValue().get(key);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return obj;
	}

	/**
	 * 获取缓存
	 * @param typeName		类名
	 * @param hashKeyList	缓存key
	 * @return				缓存数据
	 */
	public List<Object> getCache(String typeName, List<String> hashKeyList) {
		if(null == hashKeyList || hashKeyList.isEmpty() || null == redisTemplate) {
			return null;
		}
		try {
		    List<String> list = new ArrayList<>(hashKeyList.size());
		    for(String val : hashKeyList) {
		        list.add(getPrefix(typeName) + ":" + val);
            }
			return redisTemplate.opsForValue().multiGet(list);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 设置缓存
	 * @param hashKey	主键
	 * @param value		对象
	 */
	public void setCache(String hashKey, Object value) {
		setCache(TYPE_NAME, hashKey, value, 0, null);
	}

	/**
	 * 设置缓存
	 * @param typeName	类名
	 * @param hashKey	主键
	 * @param value		对象
	 */
	public void setCache(String typeName, String hashKey, Object value) {
		setCache(typeName, hashKey, value, 0, null);
	}

	/**
	 * 设置缓存
	 * @param typeName	类名
	 * @param hashKey	主键
	 * @param value		对象
	 * @param timeout	超时时间
	 * @param unit		时间单位
	 */
	public void setCache(String typeName, String hashKey, Object value, long timeout, TimeUnit unit) {
		try {
			if(null == hashKey || null == value || null == redisTemplate) {
				return;
			}
			String key = getPrefix(typeName) + ":" + hashKey;
			if(timeout <= 0) {
				// 永久
				redisTemplate.opsForValue().set(key, value);
			} else {
				// 临时
				redisTemplate.opsForValue().set(key, value, timeout, unit);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * 移除数据
	 * @param typeName	类名
	 * @param hashKey	主键
	 * @param value		对象
	 */
	public void opsForSetRemove(String typeName, String hashKey, Object value) {
		try {
			if(null == hashKey || null == value || null == redisTemplate) {
				return;
			}
			String key = getPrefix(typeName) + ":" + hashKey;
			redisTemplate.opsForSet().remove(key, value);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * 添加队列
	 * @param typeName	类名
	 * @param hashKey	主键
	 * @param value		对象
	 */
	public void opsForSetAdd(String typeName, String hashKey, Object value) {
		try {
			if(null == hashKey || null == value || null == redisTemplate) {
				return;
			}
			String key = getPrefix(typeName) + ":" + hashKey;
			redisTemplate.opsForSet().add(key, value);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * 获取队列数据
	 * @param typeName	类名
	 * @param hashKey	主键
	 * @return			集合
	 */
	public Set<Object> opsForSetMembers(String typeName, String hashKey) {
		try {
			if(null == hashKey || null == redisTemplate) {
				return null;
			}
			String key = getPrefix(typeName) + ":" + hashKey;
			return redisTemplate.opsForSet().members(key);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

}
