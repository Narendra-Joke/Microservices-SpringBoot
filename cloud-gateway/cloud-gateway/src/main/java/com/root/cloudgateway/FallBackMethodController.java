package com.root.cloudgateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallBackMethodController {

    @RequestMapping("/userServiceFallBack")
    public Mono<String> userServiceFallBackMethod(){
        return Mono.just("User Service is taking longer than expected." +
                "Please try again later.");
    }

    @RequestMapping("/departmentServiceFallBack")
    public Mono<String> departmentServiceFallBackMethod(){
        return Mono.just("Department Service is taking longer than expected." +
                "Please try again later.");
    }
}
