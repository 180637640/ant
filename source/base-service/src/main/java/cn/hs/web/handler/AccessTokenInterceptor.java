package cn.hs.web.handler;

import cn.hs.core.login.LoginHelper;
import cn.hs.service.LoginInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 * @author swt
 */
@Component
public class AccessTokenInterceptor implements HandlerInterceptor {

    @Qualifier("loginInfoServiceImpl")
    @Autowired
    private LoginInfoService loginInfoService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 在请求处理之前进行调用
        LoginHelper.clear();
        String accessToken = httpServletRequest.getHeader("accessToken");
        if(StringUtils.isBlank(accessToken)) {
            return true;
        }

        // 初始化登录信息
        LoginHelper.setLoginInfoService(loginInfoService);
        LoginHelper.setLocalAccessToken(accessToken);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        // 请求处理之后进行调用，但是在视图被渲染之前
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        // 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
        // 清理登录信息
        LoginHelper.clear();
    }

}
