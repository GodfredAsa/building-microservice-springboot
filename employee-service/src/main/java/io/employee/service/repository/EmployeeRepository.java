package io.employee.service.repository;

import io.employee.service.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee getEmployeeByEmail(String email);

    boolean existsEmployeeByEmail(String email);
}
