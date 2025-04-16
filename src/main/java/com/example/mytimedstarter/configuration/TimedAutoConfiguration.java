package com.example.mytimedstarter.configuration;

import com.example.mytimedstarter.processor.TimedAnnotationBeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "timed", name = "enabled", havingValue = "true", matchIfMissing = true)
public class TimedAutoConfiguration {

    @Bean
    public TimedAnnotationBeanPostProcessor timedAnnotationBeanPostProcessor() {
        return new TimedAnnotationBeanPostProcessor();
    }
}