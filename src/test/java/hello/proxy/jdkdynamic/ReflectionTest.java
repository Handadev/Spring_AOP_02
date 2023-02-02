package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {
        Hello target = new Hello();

        // 공통 로직
        log.info("start");
        String result1 = target.callA(); // 호출 대상이 다름
        log.info("result1 = {}", result1);
        // 공통 로직 종료

        // 공통 로직
        log.info("start");
        String result2 = target.callB(); // 호출 대상이 다름
        log.info("result2 = {}", result2);
        // 공통 로직 종료

        // 동적으로 변경해야하는 어려움이 있음
    }

    @Test
    void reflection1() throws Exception {
        // 클래스 정보 확인
        Class<?> classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        Method callA = classHello.getMethod("callA");
        // 타겟 인스턴스에 있는 callA 메소드를 호출한다
        Object result1 = callA.invoke(target);
        log.info("result1 = {}", result1);

        Method callB = classHello.getMethod("callA");
        // 타겟 인스턴스에 있는 callA 메소드를 호출한다
        Object result2 = callB.invoke(target);
        log.info("result2 = {}", result2);
    }

    @Test
    void reflection2() throws Exception {
        // 클래스 정보 확인
        Class<?> classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
        // 단점 : 리플렉션은 컴파일시점에서 오류를 발견할 수 없다
        Hello target = new Hello();
        Method callA = classHello.getMethod("callA");
        dynamicCall(callA, target);

        Method callB = classHello.getMethod("callA");
        dynamicCall(callB, target);
    }

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start");
        Object result = method.invoke(target);
        log.info("result = {}", result);
    }

    static class Hello {
        public String callA() {
            log.info("call A");
            return "A";
        }

        public String callB() {
            log.info("call B");
            return "B";
        }
    }


}
