package cn.hs.ant.job;

import cn.hs.config.RedisGlobalLock;
import cn.hs.ant.core.ForwardService;
import cn.hs.util.UF;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.nio.file.Paths;

/**
 * 抽取任务
 * @author swt
 */
@Component
public class TaskJob {
    private static Log log = LogFactory.getLog(TaskJob.class);

    @Resource
    private RedisGlobalLock redisGlobalLock;
    @Resource
    private ForwardService forwardService;
    @Value("${ant.job.file.path}")
    private String filePath;

    /**
     * 轨迹文件同步数据到磁盘
     */
    @Scheduled(fixedDelay = 10 * 1000, initialDelay = 500)
    public void process() {
        // 全局锁 1分钟最多执行一次
        String key = "ExtractJob:process:" + UF.getFormatDateTime("yyyyMMddHHmm", UF.getDateTime());
        if(!redisGlobalLock.lock(key)) {
            return;
        }

        try{
            if(StringUtils.isBlank(filePath)) {
                log.error("ant.job.file.path 不存在");
                return;
            }
            File file = Paths.get(filePath).toFile();
            if(!file.exists() || !file.isDirectory()) {
                log.error("ant.job.file.path 配置错误");
                return;
            }

            File[] files = file.listFiles(f->{
                String fileName = f.getName().toLowerCase();
                if(f.isDirectory()) {
                    return false;
                }
                if(fileName.endsWith(".properties")) {
                    return true;
                }
                return false;
            });

            if(null == files || files.length <= 0) {
                return;
            }

            for (File f : files) {
                forwardService.executeTask(f);
            }
        } catch (Exception e) {
            log.error(e);
        } finally {
            redisGlobalLock.unlock(key);
        }
    }

}
