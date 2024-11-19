package io.department_service.repository;

import io.department_service.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {


    Department getDepartmentByCode(String code);
}
