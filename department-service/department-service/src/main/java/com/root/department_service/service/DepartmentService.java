package com.root.department_service.service;

import com.root.department_service.entity.Department;
import com.root.department_service.repository.DepartmentRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DepartmentService {
    @Autowired
    private DepartmentRepo departmentRepo;

    public Department addDepartment(Department department) {
        log.info("Inside addDepartment method of DepartmentService");
        return departmentRepo.save(department);
    }

    public Department findByDepartmentId(Long departmentId) {
        log.info("Inside findDepartmentById method of DepartmentService");
        return departmentRepo.findByDepartmentId(departmentId);
    }
}
