package cn.hs;

import cn.hs.config.Config;
import org.dozer.DozerBeanMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableEurekaClient
@EnableScheduling
@EnableFeignClients
@EnableAsync
@SpringBootApplication(exclude= {
		DataSourceAutoConfiguration.class
})
public class AntServiceApplication {

	@Bean
	@ConfigurationProperties(prefix = "config")
	public Config config(){
		return new Config();
	}

	@Bean
	public DozerBeanMapper dozerBeanMapper(){
		return new DozerBeanMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(AntServiceApplication.class, args);
	}
}
