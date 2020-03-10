package cn.hs.service;

import cn.hs.config.CacheUtilsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * @author swt
 */
@Service
public class LoginCacheService extends CacheUtilsService {

    private static Log log = LogFactory.getLog(LoginCacheService.class);

    /**
     * 获取缓存标识前缀
     * @param typeName	类名
     * @return	前缀
     */
    @Override
    protected final String getPrefix(String typeName) {
        return typeName;
    }
}
