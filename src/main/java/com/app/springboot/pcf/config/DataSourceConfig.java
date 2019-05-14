package com.app.springboot.pcf.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Anish Panthi
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.app.springboot.pcf.repository")
@EntityScan(basePackages = "com.app.springboot.pcf.domain")
public class DataSourceConfig {
}
