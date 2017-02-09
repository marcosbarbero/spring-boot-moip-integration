package com.marcosbarbero.boot.moip.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import br.com.moip.Client;
import lombok.Data;

import static com.marcosbarbero.boot.moip.properties.MoipProperties.PREFIX;

/**
 * @author Marcos Barbero
 * @since 2017-02-09
 */
@Data
@ConfigurationProperties(PREFIX)
public class MoipProperties {
    public static final String PREFIX = "moip";

    private Security security = new Security();
    private Environment environment;

    @Data
    public static class Security {
        private Basic basic = new Basic();
        private OAuth oAuth = new OAuth();
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
