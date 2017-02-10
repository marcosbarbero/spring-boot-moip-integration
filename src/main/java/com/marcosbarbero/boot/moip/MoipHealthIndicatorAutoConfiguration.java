package com.marcosbarbero.boot.moip;

import com.marcosbarbero.boot.moip.actuate.MoipHealthIndicator;
import com.marcosbarbero.boot.moip.properties.MoipProperties;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.client.RestTemplate;

import static com.marcosbarbero.boot.moip.properties.MoipProperties.PREFIX;

/**
 * @author Marcos Barbero
 * @since 2017-02-10
 */
@Configuration
@ConditionalOnClass(AbstractHealthIndicator.class)
@ConditionalOnProperty(name = PREFIX + ".health-indicator-enabled", havingValue = "true")
public class MoipHealthIndicatorAutoConfiguration {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ConditionalOnMissingBean(RestTemplate.class)
    public RestTemplate restOperations() {
        return new RestTemplate();
    }

    @Bean
    public MoipHealthIndicator moipHealthIndicator(final MoipProperties moipProperties, final RestTemplate
            restTemplate) {
        return new MoipHealthIndicator(moipProperties, restTemplate);
    }
}
