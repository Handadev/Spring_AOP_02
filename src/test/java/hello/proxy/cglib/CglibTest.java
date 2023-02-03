package hello.proxy.cglib;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    @Test
    void test() {
        ConcreteService target = new ConcreteService();

        // 그냥 있는 구체 클래스를 상속받은 프록시를 생성해주어야한다
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteService.class);
        enhancer.setCallback(new TimeMethodInterceptor(target));
        ConcreteService proxy = (ConcreteService) enhancer.create(); // 부모타입 설정해서 프록시 생성
        log.info("targetClass = {}", target.getClass());
        log.info("proxyClass = {}", proxy.getClass());

    }
}
