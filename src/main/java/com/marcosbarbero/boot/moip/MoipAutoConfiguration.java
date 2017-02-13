package com.marcosbarbero.boot.moip;

import com.marcosbarbero.boot.moip.properties.MoipProperties;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import br.com.moip.API;
import br.com.moip.Client;
import br.com.moip.authentication.Authentication;
import br.com.moip.authentication.BasicAuth;
import br.com.moip.authentication.OAuth;
import lombok.RequiredArgsConstructor;

/**
 * @author Marcos Barbero
 * @since 2017-02-09
 */
@Configuration
@RequiredArgsConstructor
@ConditionalOnClass(API.class)
@EnableConfigurationProperties(MoipProperties.class)
public class MoipAutoConfiguration {

    private final MoipProperties moipProperties;

    @Bean
    public API api() {
        return new API(this.client());
    }

    private Client client() {
        return new Client(this.moipProperties.getEnvironment().getUrl(), authentication());
    }

    private Authentication authentication() {
        MoipProperties.Security security = this.moipProperties.getSecurity();
        if (!StringUtils.isEmpty(security.getBasic().getKey())) {
            return new BasicAuth(security.getBasic().getToken(), security.getBasic().getKey());
        }
        return new OAuth(security.getOauth().getAccessToken());
    }

}
