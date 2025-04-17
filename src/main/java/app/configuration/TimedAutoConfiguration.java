package app.configuration;

import app.annotation.Timed;
import app.processor.TimedAnnotationBeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Timed.class)
@ConditionalOnProperty(prefix = "timed", name = "enabled", havingValue = "true", matchIfMissing = true)
public class TimedAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public TimedAnnotationBeanPostProcessor timedAnnotationBeanPostProcessor() {
        return new TimedAnnotationBeanPostProcessor();
    }
}