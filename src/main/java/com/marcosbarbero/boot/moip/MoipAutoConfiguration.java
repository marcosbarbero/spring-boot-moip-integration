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

/**
 * @author Marcos Barbero
 * @since 2017-02-09
 */
@Configuration
@EnableConfigurationProperties(MoipProperties.class)
@ConditionalOnClass(API.class)
public class MoipAutoConfiguration {

    @Bean
    public API api(final MoipProperties moipProperties) {
        return new API(this.client(moipProperties));
    }

    private Authentication auth(final MoipProperties moipProperties) {
        Authentication authentication;
        MoipProperties.Security security = moipProperties.getSecurity();
        if (!StringUtils.isEmpty(security.getBasic().getKey())) {
            authentication = new BasicAuth(security.getBasic().getToken(), security.getBasic().getKey());
        } else {
            authentication = new OAuth(security.getOauth().getAccessToken());
        }
        return authentication;
    }

    private Client client(final MoipProperties moipProperties) {
        return new Client(moipProperties.getEnvironment().getUrl(), auth(moipProperties));
    }

}
