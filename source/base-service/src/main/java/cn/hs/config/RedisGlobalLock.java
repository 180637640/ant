package cn.hs.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Redis分布式锁
 * @author swt
 *
 * String key = "lock:info:add";
 * 直接获取锁，如果失败马上返回。
 * boolean isOk = redisGlobalLock.lock(key);
 * 指定业务锁有效期
 * isOk = redisGlobalLock.lock(key, 1, TimeUnit.DAYS);
 * 尝试获取锁，如果失败等待20S，每30ms尝试一次，直到获取锁或超时返回
 * isOk = redisGlobalLock.tryLock(key);
 * 指定超时时间
 * isOk = redisGlobalLock.tryLock(key, 10, TimeUnit.SECONDS);
 * 指定超时时间和业务锁有效期
 * isOk = redisGlobalLock.tryLock(key, 10, TimeUnit.SECONDS, 1, TimeUnit.DAYS);
 * 释放锁
 * redisGlobalLock.unlock(key);
 */
@Service
public class RedisGlobalLock {
	private static Log log = LogFactory.getLog(RedisGlobalLock.class);
	private static final String TYPE_NAME = RedisGlobalLock.class.getTypeName();

	/** 默认30ms尝试一次 */
	private final static long LOCK_TRY_INTERVAL	= 30L;
	/** 默认尝试20s */
	private final static long LOCK_TRY_TIMEOUT 	= 20 * 1000L;
	/** 单个业务持有锁的时间30s，防止死锁 */
	private final static long LOCK_EXPIRE 		= 30 * 1000L;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * 获取锁
	 * @param key			锁Key
	 * @return				是否获取锁
	 */
	public boolean lock(String key) {
		return getLock(key, 0, LOCK_EXPIRE, TimeUnit.MILLISECONDS);
	}

	/**
	 * 获取锁
	 * @param key			锁Key
	 * @param expire		有效期
	 * @param expireUnit	有效期时间单位
	 * @return				是否获取锁
	 */
	public boolean lock(String key, long expire, TimeUnit expireUnit) {
		return getLock(key, 0, expire, expireUnit);
	}

	/**
	 * 尝试获取锁
	 * @param key		锁Key
	 * @return			是否获取锁
	 */
	public boolean tryLock(String key) {
		return tryLock(key, LOCK_TRY_TIMEOUT, TimeUnit.MILLISECONDS);
	}

	/**
	 * 尝试获取锁
	 * @param key		锁Key
	 * @param timeout	等待超时时间
	 * @param unit		等待超时时间单位
	 * @return			是否获取锁
	 */
	public boolean tryLock(String key, long timeout, TimeUnit unit) {
		// 超时时间转成毫秒
		timeout = TimeUnit.MILLISECONDS.convert(timeout, unit);
		return getLock(key,timeout, LOCK_EXPIRE, TimeUnit.MILLISECONDS);
	}

	/**
	 * 尝试获取锁
	 * @param key			锁Key
	 * @param timeout		等待超时时间
	 * @param timeoutUnit	等待超时时间单位
	 * @param expire		有效期
	 * @param expireUnit	有效期时间单位
	 * @return
	 */
	public boolean tryLock(String key, long timeout, TimeUnit timeoutUnit, long expire, TimeUnit expireUnit) {
		// 超时时间转成毫秒
		timeout = TimeUnit.MILLISECONDS.convert(timeout, timeoutUnit);
		return getLock(key,timeout, expire, expireUnit);
	}

	/**
	 * 释放锁
	 * @param key	锁Key
	 */
	public void unlock(String key) {
		key = getPrefix(TYPE_NAME) + ":" + key;
		Long oldExpireTime = (Long) redisTemplate.opsForValue().get(key);
		if(null != oldExpireTime && oldExpireTime >= System.currentTimeMillis()) {
			// 大于过期时间，则删除key
			redisTemplate.delete(key);
		}
	}

	/**
	 * 获取锁
	 * @param key		锁键值
	 * @param timeout	超时时间
	 * @param time		全局锁生命周期
	 * @param unit		时间单位
	 * @return			是否获取到锁
	 */
	private boolean getLock(String key, long timeout, long time, TimeUnit unit) {
		key = getPrefix(TYPE_NAME) + ":" + key;
		try {
			long startTimeMillis = System.currentTimeMillis();
			do {
				long newValue = System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(time, unit);
				Boolean isOk = redisTemplate.opsForValue().setIfAbsent(key, newValue);
				if(isOk) {
					// 获得锁
					redisTemplate.expire(key, time, unit);
					return true;
				}
				// 获取过期时间
				Long oldExpireTime = (Long) redisTemplate.opsForValue().get(key);
				if(null == oldExpireTime) {
                    oldExpireTime = 0L;
                }
				if(oldExpireTime >= System.currentTimeMillis()) {
					// 如果没有过有效期
					if((System.currentTimeMillis() - startTimeMillis) > timeout) {
						// 如果超过等待时间，则返回失败
						return false;
					}

					// 休眠
					Thread.sleep(LOCK_TRY_INTERVAL);
					continue;
				}
				// 新的过期时间
				long newExpireTime = System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(time, unit);
				Long currentExpireTime = (Long) redisTemplate.opsForValue().getAndSet(key, newExpireTime);
                if(null == currentExpireTime) {
                    currentExpireTime = 0L;
                }
				if(currentExpireTime.equals(oldExpireTime)) {
					// 获取到锁
					redisTemplate.expire(key, time, unit);
					return true;
				}
			} while (true);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 获取缓存标识前缀
	 * @param typeName	类名
	 * @return	前缀
	 */
	protected final String getPrefix(String typeName) {
		return typeName;
	}

}
