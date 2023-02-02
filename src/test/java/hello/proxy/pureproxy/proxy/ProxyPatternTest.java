package hello.proxy.pureproxy.proxy;

import hello.proxy.pureproxy.proxy.code.CacheProxy;
import hello.proxy.pureproxy.proxy.code.ProxyPatternClient;
import hello.proxy.pureproxy.proxy.code.RealSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @Test
    void noProxy() {
        // 클라이언트가 RealSubject를 받아오는 형식

        RealSubject subject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(subject);
        client.execute();
        client.execute();
        client.execute();
        client.execute();

    }

    @Test
    void cacheProxy() {
        // 먼저 캐시 프록시에 realsubject를 받아서 설정하고,
        // 클라이언트는 프록시를 받아서 설정하는 것이다

        CacheProxy cacheProxy = new CacheProxy(new RealSubject());
        ProxyPatternClient client = new ProxyPatternClient(cacheProxy);

        client.execute();
        client.execute();
        client.execute();
        client.execute();
    }
}
