package com.root.user.service;

import com.root.user.VO.Department;
import com.root.user.VO.ResponseTemplateVO;
import com.root.user.entity.User;
import com.root.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    public User addUser(User user) {
        System.out.println("Inside addUser method of UserService");
        return userRepository.save(user);
    }

    public User findByUserId(Long userId) {
        System.out.println("Inside findByUserId method of UserService");
        return userRepository.findByUserId(userId);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("Inside getUserWithDepartment method of UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);

        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(),
                Department.class);

        vo.setUser(user);
        vo.setDepartment(department);
        return vo;
    }
}
