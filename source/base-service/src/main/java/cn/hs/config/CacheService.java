package cn.hs.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 缓存服务
 * @author swt
 */
@Service
public class CacheService extends CacheBaseService{

	private static Log log = LogFactory.getLog(CacheService.class);

	@Autowired
	private Config config;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * 获取缓存
	 * @param typeName	类名
	 * @param cacheType	缓存类型
	 * @param hashKey	缓存key
	 * @return			缓存数据
	 */
	public Object getCache(String typeName, CacheType cacheType, String hashKey) {
		if(null == hashKey || null == redisTemplate) {
			return null;
		}

		Object obj = null;
		try {
			String key = getPrefix(typeName) + ":" + cacheType;

			// 判断是否超时
			long time = redisTemplate.getExpire(key);
			if(time <= 0) {
				redisTemplate.delete(key);
				return null;
			}

			obj = redisTemplate.opsForHash().get(key, getHashCode(hashKey.hashCode()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return obj;
	}

	/**
	 * 设置缓存
	 * @param typeName	类名
	 * @param cacheType	缓存类型
	 * @param hashKey	主键
	 * @param value		对象
	 * @param timeout	超时时间
	 * @param unit		时间单位
	 */
	public void setCache(String typeName, CacheType cacheType, String hashKey, Object value, long timeout, TimeUnit unit) {
		try {
			if(null == hashKey || null == value || null == redisTemplate) {
				return;
			}
			if(timeout <= 0) {
				timeout = config.getCacheTimeout();
			}

			String key = getPrefix(typeName) + ":" + cacheType;
			// 设置超时时间
			boolean firstElement = false;
			if(!redisTemplate.hasKey(key)) {
				firstElement = true;
			}
			// 设置缓存
			redisTemplate.opsForHash().put(key, getHashCode(hashKey.hashCode()), value);
			if(firstElement) {
				redisTemplate.expire(key, timeout, unit);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
