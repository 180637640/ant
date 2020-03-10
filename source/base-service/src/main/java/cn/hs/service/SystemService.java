package cn.hs.service;

import cn.hs.bean.LogBean;
import cn.hs.bean.LoginBean;
import cn.hs.bean.type.LogType;
import cn.hs.config.CacheService;
import cn.hs.core.login.LoginHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static cn.hs.config.QueueName.QUEUE_SYS_LOG;

/**
 * 系统服务
 * @author swt
 */
@Service
public class SystemService extends CacheService {
    private static Log log = LogFactory.getLog(SystemService.class);

    @Resource
    private AmqpTemplate amqpTemplate;

    /**
     * 添加操作日志
     * @param type          日志类型
     */
    public void log(LogType type) {
        log(type, null, null, null);
    }

    /**
     * 添加操作日志
     * @param type          日志类型
     * @param description   操作描述
     */
    public void log(LogType type, String description) {
        log(type, null, description, null);
    }

    /**
     * 添加操作日志
     * @param type          日志类型
     * @param description   操作描述
     */
    public void log(LogType type, String description, LoginBean loginBean) {
        log(type, null, description, loginBean);
    }

    /**
     * 添加操作日志
     * @param type          日志类型
     * @param thirdNumber   功能流水号
     * @param description   操作描述
     * @param loginBean     登录信息
     */
    public void log(LogType type, String thirdNumber, String description, LoginBean loginBean) {
        if(null == type) {
            return;
        }
        LogBean logBean = new LogBean();
        logBean.setType(type.toString());
        logBean.setThirdNumber(thirdNumber);
        logBean.setDescription(description);
        // 操作会员资料
        if(null == loginBean) {
            loginBean = LoginHelper.getLoginBean();
        }
        if(null != loginBean) {
            if(null == logBean.getUserType()) {
                logBean.setUserType(loginBean.getLoginType());
            }
            if(StringUtils.isBlank(logBean.getUserId())) {
                logBean.setUserId(loginBean.getId());
            }
            if(StringUtils.isBlank(logBean.getUserName())) {
                logBean.setUserName(loginBean.getName());
            }
            if(StringUtils.isBlank(logBean.getUserLoginName())) {
                logBean.setUserLoginName(loginBean.getLoginName());
            }
        }

        amqpTemplate.convertAndSend(QUEUE_SYS_LOG, logBean);
    }

}
