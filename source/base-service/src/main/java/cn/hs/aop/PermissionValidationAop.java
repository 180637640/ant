package cn.hs.aop;

import cn.hs.bean.LoginBean;
import cn.hs.core.exception.UnLoginException;
import cn.hs.core.exception.UnauthorizedException;
import cn.hs.core.login.LoginHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 权限验证
 * @author swt
 */
@Aspect
@Component
public class PermissionValidationAop {
    private static Log log = LogFactory.getLog(PermissionValidationAop.class);

    @Pointcut("@annotation(cn.hs.aop.RequiresPermissions)")
    public void requiresPermissions(){};

    @Around("requiresPermissions()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if(null == method) {
            return joinPoint.proceed();
        }
        RequiresPermissions requiresPermissions = method.getAnnotation(RequiresPermissions.class);
        if(null == requiresPermissions) {
            return joinPoint.proceed();
        }

        // 登录验证
        LoginBean loginBean = LoginHelper.getLoginBean();
        if(null == loginBean || StringUtils.isBlank(loginBean.getId())) {
            throw new UnLoginException("00000102");
        }

        // 权限验证
        if(requiresPermissions.value().length == 1) {
            // 如果不包含该权限
            if(false) {
                throw new UnauthorizedException("00000100");
            }
            return joinPoint.proceed();
        }

        if (Logical.AND.equals(requiresPermissions.logical())) {
            // 如果不包含该权限
            if(false) {
                throw new UnauthorizedException("00000100");
            }
        }

        if (Logical.OR.equals(requiresPermissions.logical())) {
            // 如果不包含该权限
            if(false) {
                throw new UnauthorizedException("00000100");
            }
        }

        return joinPoint.proceed();
    }
}
