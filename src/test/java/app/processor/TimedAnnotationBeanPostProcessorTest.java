package app.processor;

import app.service.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest({"timed.annotation.enabled=true"})
public class TimedAnnotationBeanPostProcessorTest {

    @Autowired
    private MyService myService;

    @Test
    void testBeanShouldBeProxied() {
        assertTrue(AopUtils.isAopProxy(myService), "MyService должен быть AOP-прокси");
    }

    @Test
    void testProcessDataMethodRunsSuccessfully() {
        assertDoesNotThrow(() -> myService.processData());
    }

    @Test
    void testFetchDataMethodRunsSuccessfully() {
        assertDoesNotThrow(() -> myService.fetchData());
    }
}
