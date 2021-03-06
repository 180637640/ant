package cn.hs.ant.core;

import cn.hs.ant.core.adapter.ExtractAdapter;
import cn.hs.ant.core.adapter.JdbcExtractAdapter;
import cn.hs.ant.core.adapter.JdbcLoadAdapter;
import cn.hs.ant.core.adapter.LoadAdapter;
import cn.hs.service.JobService;
import cn.hs.util.UF;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static cn.hs.ant.bean.AntDatabaseProperties.ANT_EXTRACT;
import static cn.hs.ant.bean.AntDatabaseProperties.ANT_FIXED_DELAY;
import static cn.hs.ant.bean.TerminalTypes.*;

/**
 * 分发服务
 * @author swt
 */
@Service
public class ForwardService {
    private static Log log = LogFactory.getLog(ForwardService.class);

    @Resource
    private JobService jobService;

    /**
     * 执行任务
     * @param file  任务配置文件
     */
    @Async
    public void executeTask(File file) {
        // 读取配置文件
        Properties properties = loadProperties(file);
        String jobName = file.getPath();

        if(!jobService.canExecute(jobName)) {
            log.info(String.format("已经有相同任务在执行，[ %s ]", jobName));
            return;
        }
        try {
            String extract = properties.getProperty(ANT_EXTRACT);
            String fixedDelay = getProperty(properties, ANT_FIXED_DELAY);
            if(StringUtils.isBlank(fixedDelay)) {
                fixedDelay = "0";
            }

            long nextUpdateTime = System.currentTimeMillis() + UF.toInt(fixedDelay);
            // 开始任务
            jobService.start(jobName, nextUpdateTime);
            log.info(String.format("开始执行，[ %s ]", jobName));

            TransformService transformService = new TransformService();
            transformService.setExtractAdapter(getExtractAdapter(extract, properties));
            transformService.setLoadAdapter(getLoadAdapter(extract, properties));

            // 任务完成
            transformService.setOnFinishListener(() -> {
                jobService.end(jobName);
                log.info(String.format("执行结束，[ %s ]", jobName));
            });

            transformService.start();
        } catch (Exception e) {
            jobService.exception(jobName, e.getMessage());
        }
    }

    /**
     * 获取抽取适配器
     * @param properties    属性文件
     * @return              适配器
     */
    private ExtractAdapter getExtractAdapter(String extract, Properties properties) {
        if(StringUtils.equalsAnyIgnoreCase(extract, MYSQL, ORACLE, POSTGRESQL, DB2, SQLSERVER)) {
            return new JdbcExtractAdapter(properties);
        }
        return null;
    }

    /**
     * 获取加载适配器
     * @param properties    属性文件
     * @return              适配器
     */
    private LoadAdapter getLoadAdapter(String extract, Properties properties) {
        if(StringUtils.equalsAnyIgnoreCase(extract, MYSQL, ORACLE, POSTGRESQL, DB2, SQLSERVER)) {
            return new JdbcLoadAdapter(properties);
        }
        return null;
    }

    /**
     * 获取属性值
     * @param properties    属性
     * @param key           健
     * @return              值
     */
    private String getProperty(Properties properties, String key) {
        String value = properties.getProperty(key);
        return UF.toString(value);
    }

    /**
     * 读取属性文件
     * @param file  属性文件
     * @return      属性
     */
    private Properties loadProperties(File file) {
        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(file);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
