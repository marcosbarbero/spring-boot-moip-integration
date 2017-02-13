package com.marcosbarbero.boot.moip.properties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.util.StringUtils;

import br.com.moip.Client;
import lombok.Data;

import static com.marcosbarbero.boot.moip.properties.MoipProperties.PREFIX;

/**
 * @author Marcos Barbero
 * @since 2017-02-09
 */
@Data
@ConfigurationProperties(PREFIX)
public class MoipProperties implements InitializingBean {
    public static final String PREFIX = "moip";

    @NestedConfigurationProperty
    private Security security = new Security();

    private Environment environment = Environment.SANDBOX;
    private boolean healthIndicatorEnabled;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (!StringUtils.isEmpty(this.security.getOauth().getAccessToken())) {
            return;
        }

        if (!StringUtils.isEmpty(this.security.getBasic().getKey())
                && !StringUtils.isEmpty(this.security.getBasic().getToken())) {
            return;
        }

        throw new IllegalArgumentException("You need to set basic or oauth authentication properties.");
    }

    @Data
    public static class Security {

        @NestedConfigurationProperty
        private Basic basic = new Basic();

        @NestedConfigurationProperty
        private OAuth oauth = new OAuth();
    }

    @Data
    public static class Basic {
        private String token;
        private String key;
    }

    @Data
    public static class OAuth {
        private String accessToken;
    }

    public enum Environment {
        PRODUCTION(Client.PRODUCTION),
        SANDBOX(Client.SANDBOX);

        private String url;

        Environment(final String url) {
            this.url = url;
        }

        public String getUrl() {
            return this.url;
        }
    }
}
