package com.marcosbarbero.boot.moip;

import com.marcosbarbero.boot.moip.properties.MoipProperties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Marcos Barbero
 * @since 2017-02-09
 */
@Configuration
@EnableConfigurationProperties(MoipProperties.class)
public class MoipAutoConfiguration {


}
