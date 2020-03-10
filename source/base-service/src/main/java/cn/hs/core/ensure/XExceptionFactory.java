package cn.hs.core.ensure;

import cn.hs.cache.MessageCache;
import cn.hs.core.exception.XRuntimeException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 异常描述信息
 * @author swt
 */
public class XExceptionFactory {
    private static Log LOG = LogFactory.getLog(XExceptionFactory.class);

    /**
     * 创建异常
     * @param errorCode 错误代码
     * @param args      参数
     * @return
     */
    public static XRuntimeException create(String errorCode, String... args) {
//        LOG.error("errorCode = [" + errorCode + "], args = [" + args + "]");
        String exceptionPattern = MessageCache.getInstance().getExceptionMessage(errorCode);
        if(StringUtils.isBlank(exceptionPattern)) {
//            LOG.error(exceptionPattern);
            return new XRuntimeException(errorCode);
        }
        if (args.length > 0) {
            String errorMsg = String.format(exceptionPattern, args);
//            LOG.error(errorMsg);
            return new XRuntimeException(errorMsg);
        }
//        LOG.error(exceptionPattern);
        return new XRuntimeException(exceptionPattern);
    }

}
