package hello.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeDecorator 실행");
        long startTime = System.currentTimeMillis();

//        Object result = methodProxy.invoke(target, args);
        // invocation에서 알아서 target 클래스를 찾아 호출하고 결과를 처리한다
        // target 정보는 invocation에 이미 들어가있음
        Object result = invocation.proceed();

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeDecorator 종료 resultTime = {}", resultTime);
        return result;
    }
}
