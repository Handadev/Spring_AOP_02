package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void testA() {
        AInterface target = new AImpl();

        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, handler);
        // handler 의 override invoke를 호출한다.
        // proxy 에서 호출한 method는 call()
        // metod.invoke 되는 대상은 call()
        proxy.call();

        log.info("target.getClass = {}", target.getClass());
        log.info("proxy.getClass = {}", proxy.getClass());

    }

    @Test
    void testB() {
        BInterface target = new BImpl();

        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        BInterface proxy = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(), new Class[]{BInterface.class}, handler);

        proxy.call();

        log.info("target.getClass = {}", target.getClass());
        log.info("proxy.getClass = {}", proxy.getClass());

    }
}
