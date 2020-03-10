package cn.hs.web.handler;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * 健康监测
 * @author swt
 */
@Component
public class HealthCheckIndicator implements HealthIndicator {

    private Boolean isUp = true;

    @Override
    public Health health() {
        if(isUp) {
            return new Health.Builder().up().build();
        }
        return new Health.Builder().down().build();
    }

    public Boolean getUp() {
        return isUp;
    }

    public void setUp(Boolean up) {
        isUp = up;
    }
}
