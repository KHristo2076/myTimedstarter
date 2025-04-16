package com.example.mytimedstarter.procesor;

import com.example.mytimedstarter.annotation.Timed;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
public class TimedAnnotationBeanPostProcessor implements BeanPostProcessor {
    private static final Logger logger = LoggerFactory
            .getLogger(TimedAnnotationBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization (Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Map<Method, Timed> timedMethods = findTimedMethods(bean);

        if (timedMethods.isEmpty()) {
            return bean;
        }

        return createProxy(bean, timedMethods);
    }

    /**
     * Находит все методы в бине, аннотированные @Timed.
     */
    private Map<Method, Timed> findTimedMethods(Object bean) {
        Map<Method, Timed> timedMethods = new HashMap<>();
        for (Method method : bean.getClass().getMethods()) {
            Timed annotation = method.getAnnotation(Timed.class);
            if (annotation != null) {
                timedMethods.put(method, annotation);
            }
        }
        return timedMethods;
    }

    /**
     * Создает прокси-объект, который оборачивает вызовы аннотированных методов.
     */
    private Object createProxy(Object target, Map<Method, Timed> timedMethods) {
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true); // CGLIB — позволяет проксировать классы без интерфейсов
        proxyFactory.addAdvice(createMethodInterceptor(timedMethods));
        return proxyFactory.getProxy();
    }

    /**
     * Создает перехватчик методов, который логирует время выполнения методов с @Timed.
     */
    private MethodInterceptor createMethodInterceptor(Map<Method, Timed> timedMethods) {
        return invocation -> {
            Method method = invocation.getMethod();
            Timed timed = timedMethods.get(method);

            if (timed == null) {
                return invocation.proceed();
            }

            return invokeAndLog(invocation, method, timed);
        };
    }

    /**
     * Логирует время выполнения метода.
     */
    private Object invokeAndLog(MethodInvocation invocation, Method method, Timed timed) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            return invocation.proceed();
        } finally {
            stopWatch.stop();
            logger.info("Method '{}'{} executed in {} ms",
                    method.getName(),
                    (timed.value().isEmpty() ? "" : " (" + timed.value() + ")"),
                    stopWatch.getTotalTimeMillis());
        }
    }
}
