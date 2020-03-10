package cn.hs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池配置
 * @author songwentao
 */
@Configuration
@EnableAsync
public class ThreadAsyncConfigurer {

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(3);
        // 设置最大线程数
        executor.setMaxPoolSize(10);
        // 线程池所使用的缓冲队列
        executor.setQueueCapacity(2);
        // 等待任务在关机时完成--表明等待所有线程执行完
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 设置线程存活时间
        executor.setKeepAliveSeconds(600);
        // 线程名称前缀
        executor.setThreadNamePrefix("ant-task-");
        return executor;
    }

}
