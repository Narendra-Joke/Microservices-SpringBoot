package com.root.user.controller;

import com.root.user.VO.Department;
import com.root.user.VO.ResponseTemplateVO;
import com.root.user.entity.User;
import com.root.user.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    public static final String USER_SERVICE = "userService";
    public static final String RATE_LIMITER = "userRateLimiter";
    @Autowired
    private UserService userService;

    private int attempts = 1;

    @PostMapping("/")
    public User addUser(@RequestBody User user){
        log.info("Inside addUser method of UserController");
        return userService.addUser(user);
    }

//    @GetMapping("/{id}")
//    public User findByUserId(@PathVariable("id") Long userId){
//        log.info("Inside findByUserId method of UserController");
//        return userService.findByUserId(userId);
//    }

    @GetMapping("/{id}")
    @CircuitBreaker(name = USER_SERVICE,fallbackMethod = "getUserFallBackMethod")
//    @Retry(name = USER_SERVICE,fallbackMethod = "getUserFallBackMethod")
    @RateLimiter(name = RATE_LIMITER, fallbackMethod = "getUserFallBackMethod")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId){
        System.out.println("Inside getUserWithDepartment method of UserController");
        System.out.println("retry method called | attempts : " + attempts++ +
                "| " + new Date());
        return userService.getUserWithDepartment(userId);
    }

    public ResponseTemplateVO getUserFallBackMethod(Long userId,Exception e){
        User user = User.builder()
                .userId(1L)
                .firstName("dummyFirstName")
                .lastName("dummyLastName")
                .email("dummy@gmail.com")
                .departmentId(1L)
                .build();

        Department department = Department.builder()
                .departmentId(1L)
                .departmentName("dummyName")
                .departmentCode("dummyDepartmentCode")
                .departmentAddress("dummyDepartmentAddress")
                .build();

        ResponseTemplateVO vo = ResponseTemplateVO.builder()
                .user(user)
                .department(department)
                .build();
        return vo;
    }
}