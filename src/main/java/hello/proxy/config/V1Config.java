package hello.proxy.config;

import hello.proxy.app.v1.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class V1Config {
    // 수동 의존관계 주입
    @Bean
    public OrderControllerV1 orderControllerV1() {
        return new OrderControllerImplV1(orderServiceV1());
    }

    @Bean
    public OrderServiceV1 orderServiceV1() {
        return new OrderServiceImplV1(orderRepositoryV1());
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1() {
        return new OrderRepositoryImplV1();
    }
}
