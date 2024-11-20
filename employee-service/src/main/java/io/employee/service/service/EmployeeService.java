package io.employee.service.service;

import io.employee.service.dto.ApiResponseDto;
import io.employee.service.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    ApiResponseDto getEmployeeByEmail(String email);
    ApiResponseDto getEmployeeByEmailUsingWebclient(String email);
    ApiResponseDto getEmployeeByEmailUsingFeignClient(String email);
}
