package hello.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheProxy implements Subject{

    // 프록시는 인터페이스를 구현하고 실제 객체도 알아야한다
    private Subject target;
    private String cacheVal;

    public CacheProxy(Subject target) {
        this.target = target;
    }

    @Override
    public String operation() {
        log.info("프록시 호출");
        if (cacheVal == null) cacheVal = target.operation();
        return cacheVal;
    }
}
