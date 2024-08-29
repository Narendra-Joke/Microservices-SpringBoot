package com.root.department_service.repository;

import com.root.department_service.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Long> {

    Department findByDepartmentId(Long departmentId);
}
