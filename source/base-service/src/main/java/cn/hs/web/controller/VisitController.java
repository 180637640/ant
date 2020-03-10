package cn.hs.web.controller;

import cn.hs.bean.VisitRecordBean;
import cn.hs.bean.base.SerializeObject;
import cn.hs.bean.type.ResultType;
import cn.hs.cache.VisitRecordCache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 访问统计相关服务
 * @author swt
 */
@RestController
@RequestMapping("visit")
public class VisitController extends BaseController {

    /**
     * 获取接口访问统计信息
     * @return			    记录集
     */
    @GetMapping("count")
    public SerializeObject<ConcurrentMap<String, AtomicInteger>> count() {
        return new SerializeObject<>(ResultType.NORMAL, VisitRecordCache.getInstance().getCountMap());
    }

    /**
     * 获取接口访问统计信息
     * @return			    记录集
     */
    @GetMapping("list")
    public SerializeObject<List<VisitRecordBean>> list() {
        return new SerializeObject<>(ResultType.NORMAL, VisitRecordCache.getInstance().get());
    }

}
