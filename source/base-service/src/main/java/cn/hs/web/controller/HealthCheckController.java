package cn.hs.web.controller;

import cn.hs.bean.base.SerializeObject;
import cn.hs.bean.type.ResultType;
import cn.hs.core.SerializeObjectError;
import cn.hs.web.handler.HealthCheckIndicator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * 服务状态管理
 * @author swt
 */
@RestController
@RequestMapping("healthCheck")
public class HealthCheckController extends BaseController {

    @Resource
    private HealthCheckIndicator healthCheckIndicator;

    /**
     * 设置服务状态
     * @param up		    状态
     * @return			    操作结果
     */
    @RequestMapping(value = "up", method = GET)
    public SerializeObject up(Boolean up) {
        if(null == up) {
            return new SerializeObjectError("00000002");
        }
        healthCheckIndicator.setUp(up);
        return new SerializeObject<>(ResultType.NORMAL);
    }

}
