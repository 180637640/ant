package cn.hs.web.controller;

import cn.hs.bean.JobBean;
import cn.hs.bean.base.SerializeObject;
import cn.hs.bean.type.ResultType;
import cn.hs.service.JobService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 定时任务管理相关服务
 * @author swt
 */
@RestController
@RequestMapping("job")
public class JobController extends BaseController {

    @Resource
    private JobService jobService;

    /**
     * 根据ID查找信息
     * @param accessToken	登录成功后分配的Key
     * @param name		    名称
     * @return			    记录集
     */
    @GetMapping("start")
    public SerializeObject<String> start(@RequestHeader(required = false) String accessToken, String name) {
        jobService.start(name, System.currentTimeMillis());
        return new SerializeObject<>(ResultType.NORMAL);
    }

    /**
     * 根据ID查找信息
     * @param accessToken	登录成功后分配的Key
     * @param name		    名称
     * @return			    记录集
     */
    @GetMapping("end")
    public SerializeObject<String> end(@RequestHeader(required = false) String accessToken, String name) {
        jobService.end(name);
        return new SerializeObject<>(ResultType.NORMAL);
    }

    /**
     * 查询列表
     * @param accessToken	        登录成功后分配的Key
     * @return			            列表数据
     */
//    @RequiresPermissions("sys:cruiseProblem")
    @GetMapping("list")
    public SerializeObject<List<JobBean>> list(@RequestHeader(required = false) String accessToken) {
        List<JobBean> list = jobService.list();
        return new SerializeObject<>(ResultType.NORMAL, list);
    }

}
