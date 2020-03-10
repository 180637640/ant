package cn.hs.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 缓存服务
 * @author swt
 */
@Service
public class CacheBaseService {

	private static Log log = LogFactory.getLog(CacheBaseService.class);

	@Autowired
	private Config config;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * 清除所有缓存（数据量多的时候会很慢，慎用）
	 */
	public void cleanSystemCache() {
		if(null == redisTemplate){
			return;
		}
		String sysId = config.getSystemId()  + "*";
		Set<String> keys = redisTemplate.keys(sysId);
		if(null != keys && !keys.isEmpty()) {
			redisTemplate.delete(keys);
		}
	}

	/**
	 * 清除多个服务缓存
	 * @param entityClasses 其他服务接口
	 */
	public void cleanCache(Class<?>... entityClasses) {
		if(null == entityClasses || entityClasses.length <= 0 || null == redisTemplate) {
			return;
		}

		for(Class<?> o :entityClasses) {
			for (CacheType cacheType : CacheType.values()) {
				cleanCache(o.getTypeName(), cacheType);
			}
		}
	}

	/**
	 * 清除缓存(本类)
	 * @param typeName	类名
	 */
	public void cleanCache(String typeName) {
		for (CacheType cacheType : CacheType.values()) {
//			cleanCache(getPrefix(typeName), cacheType);
			cleanCache(typeName, cacheType);
		}
	}

	/**
	 * 清除缓存
	 * @param typeName	类名
	 * @param cacheType	缓存类型
	 */
	public void cleanCache(String typeName, CacheType cacheType) {
		if(null == redisTemplate || null == cacheType) {
			return;
		}

		String key = getPrefix(typeName) + ":" + cacheType;
		redisTemplate.delete(key);
	}

	/**
	 * 清除缓存
	 * @param typeName	类名
	 * @param cacheType	缓存类型
	 * @param hashKey	主键
	 */
	public void cleanCache(String typeName, CacheType cacheType, String hashKey) {
		if(null == redisTemplate || null == cacheType || StringUtils.isBlank(hashKey)) {
			return;
		}

		String key = getPrefix(typeName) + ":" + cacheType;
		redisTemplate.opsForHash().delete(key, getHashCode(hashKey.hashCode()));
	}

	/**
	 * 清除缓存
	 * @param cacheType	缓存类型
	 * @param hashKey	主键数组
	 */
	public void cleanCache(String typeName, CacheType cacheType, String[] hashKey) {
		if(null == redisTemplate || null == hashKey || hashKey.length <= 0) {
			return;
		}

		for (String k : hashKey) {
			cleanCache(typeName, cacheType, k);
		}
	}

	/**
	 * 生成 HashCode 字符串形式
	 * @param hashCode	数字
	 * @return			字符串
	 */
	protected final String getHashCode(int hashCode) {
		return Integer.toString(hashCode);
	}

	/**
	 * 获取缓存标识前缀
	 * @param typeName	类名
	 * @return	前缀
	 */
	protected String getPrefix(String typeName) {
		return config.getSystemId() + ":" + typeName;
	}

}
