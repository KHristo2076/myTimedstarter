package com.example.mytimedstarter.configuration;

import com.example.mytimedstarter.procesor.TimedAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimedAutoConfiguration {

    @Bean
    public TimedAnnotationBeanPostProcessor timedAnnotationBeanPostProcessor() {
        return new TimedAnnotationBeanPostProcessor();
    }
}