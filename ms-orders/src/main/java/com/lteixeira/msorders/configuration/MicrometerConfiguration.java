package com.lteixeira.msorders.configuration;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MicrometerConfiguration {
    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags(){
        return registry -> registry.config().commonTags("application","ms-orders");
    }
}
