package com.root.department_service.controller;

import com.root.department_service.entity.Department;
import com.root.department_service.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/")
    public Department addDepartment(@RequestBody Department department){
        log.info("Inside addDepartment method of DepartmentController");
        return departmentService.addDepartment(department);
    }

    @GetMapping("/{id}")
    public Department findByDepartmentId(@PathVariable("id") Long departmentId){
     log.info("Inside findByDepartmentId method of DepartmentController");
     return departmentService.findByDepartmentId(departmentId);
    }
}
