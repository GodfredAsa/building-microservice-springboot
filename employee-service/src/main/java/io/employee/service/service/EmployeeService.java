package io.employee.service.service;

import io.employee.service.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeByEmail(String email);

//    boolean employeeExistsByEmail(String email);

}
