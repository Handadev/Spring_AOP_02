package hello.proxy.app.v1;


import org.springframework.web.bind.annotation.*;

// @RequestMapping// @Controller || @RequestMapping 있으면 컨트롤러로 인식
// @ResponseBody
@RestController // spring boot 3.0부터는 controller, restcontroller가 붙어있어야 컴포넌트 스캔 대상이 된다
public interface OrderControllerV1 {

    @GetMapping("/v1/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v1/nolog")
    String noLog();
}
