package app.configuration;

import app.processor.TimedAnnotationBeanPostProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TimedAutoConfigurationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(TimedAutoConfiguration.class));

    @Test
    void shouldRegisterBeanPostProcessorByDefault() {
        this.contextRunner.run(context -> {
            assertThat(context).hasSingleBean(TimedAnnotationBeanPostProcessor.class);
        });
    }

    @Test
    void shouldRegisterBeanPostProcessorWhenEnabledPropertyIsTrue() {
        this.contextRunner.withPropertyValues("timed.enabled=true").run(context -> {
            assertThat(context).hasSingleBean(TimedAnnotationBeanPostProcessor.class);
        });
    }

    @Test
    void shouldNotRegisterBeanPostProcessorWhenEnabledPropertyIsFalse() {
        this.contextRunner.withPropertyValues("timed.enabled=false").run(context -> {
            assertThat(context).doesNotHaveBean(TimedAnnotationBeanPostProcessor.class);
        });
    }
}
