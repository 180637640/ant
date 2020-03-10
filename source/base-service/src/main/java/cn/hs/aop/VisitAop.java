package cn.hs.aop;

import cn.hs.bean.LoginBean;
import cn.hs.bean.VisitRecordBean;
import cn.hs.cache.VisitRecordCache;
import cn.hs.core.login.LoginHelper;
import cn.hs.util.UF;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static cn.hs.config.QueueName.QUEUE_SYS_VISIT_LOG;

/**
 * 访问统计
 * @author swt
 */
@Aspect
@Component
public class VisitAop {
    private static Log log = LogFactory.getLog(VisitAop.class);

    @Resource
    private AmqpTemplate amqpTemplate;

    @Around("execution(* cn.hs.*.web.controller.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if(null == method) {
            return joinPoint.proceed();
        }
        String className = signature.getMethod().getDeclaringClass().getName();
        String methodName = signature.getMethod().getName();

        VisitRecordBean o = new VisitRecordBean();
        o.setClassName(className);
        o.setMethodName(methodName);
        o.setTime(System.currentTimeMillis());
        o.setAddTimeString(UF.getFormatDateTime());

        LoginBean loginBean = LoginHelper.getLoginBean();
        if(null != loginBean) {
            o.setToken(loginBean.getAccessToken());
            o.setIp(loginBean.getIp());
            o.setHostName(loginBean.getHostName());

            o.setUserType(loginBean.getLoginType());
            o.setUserId(loginBean.getId());
            o.setUserLoginName(loginBean.getLoginName());
            o.setUserName(loginBean.getName());
        } else {
            o.setIp("");
            o.setHostName("");
        }
        // 请求参数
        Map<String, String> parameterMap = new HashMap<>();
        if(null != requestAttributes) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            HttpServletRequest request = servletRequestAttributes.getRequest();
            if(null != request) {
                // 获取请求头的所有name值
                Enumeration enumeration = request.getHeaderNames();
                while(enumeration.hasMoreElements()){
                    String name	=(String) enumeration.nextElement();
                    String value = request.getHeader(name);
                    parameterMap.put(name, value);
                }
                // 获取请求参数的所有name值
                Map<String, String[]> map = request.getParameterMap();
                for(Map.Entry<String, String[]> entry : map.entrySet()){
                    String name = entry.getKey();
                    String value = "";
                    if(null != entry.getValue()) {
                        for (String val : entry.getValue()) {
                            if(StringUtils.isNotBlank(value)) {
                                value += ",";
                            }
                            value += val;
                        }
                      }

                    parameterMap.put(name, value);
                }
            }
        }
        o.setParameterMap(parameterMap);

        // 压入队列
        addToQueue(o);
        // 压入本地缓存
        VisitRecordCache.getInstance().put(o);

        return joinPoint.proceed();
    }

    /**
     * 压入缓存
     * @param value
     */
    public void addToQueue(VisitRecordBean value) {
        if(null == value) {
            return;
        }
        amqpTemplate.convertAndSend(QUEUE_SYS_VISIT_LOG, value);
    }

}
