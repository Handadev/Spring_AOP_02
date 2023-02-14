package hello.proxy.posprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanPostProcessorTest {

    @Test
    void basicConfig() {
        // 스프링 컨테이너 생성
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanPostProcessorConfig.class);

        // a bean 으로 등록
         B a = applicationContext.getBean("beanA", B.class);
        a.hello();

        // b 는 없음
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean(A.class));
    }

    // 빈 후처리기
    @Slf4j
    static class AtoBPostProcessor implements BeanPostProcessor {

        // 초기화 끝나고 처리
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            log.info("beanName = {} bean = {}", beanName, bean);
            if (bean instanceof A) {
                return new B();
            }
            return bean;
        }
    }

    @Slf4j
    @Configuration
    static class BeanPostProcessorConfig {
        @Bean(name = "beanA")
        public A a() {
            return new A();
        }

        @Bean
        public AtoBPostProcessor atoBPostProcessor() {
            return new AtoBPostProcessor();
        }
    }

    @Slf4j
    static class A {
        public void hello() {
            log.info("AAAAAAA");
        }
    }

    @Slf4j
    static class B {
        public void hello() {
            log.info("bBBBBBBBBB");
        }
    }
}
