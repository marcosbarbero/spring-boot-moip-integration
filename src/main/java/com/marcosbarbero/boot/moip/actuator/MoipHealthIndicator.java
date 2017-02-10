package com.marcosbarbero.boot.moip.actuator;

import com.marcosbarbero.boot.moip.properties.MoipProperties;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

/**
 * @author Marcos Barbero
 * @since 2017-02-10
 */
@RequiredArgsConstructor
public class MoipHealthIndicator extends AbstractHealthIndicator {

    private final MoipProperties moipProperties;
    private final RestTemplate restTemplate;

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        final String url = this.moipProperties.getEnvironment().getUrl();
        try {
            final ResponseEntity<String> response = this.restTemplate.getForEntity(url, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                builder.up();
            } else {
                builder.outOfService();
            }
        } catch (Exception ex) {
            builder.down();
        }
    }
}
