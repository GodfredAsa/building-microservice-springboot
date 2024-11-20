package io.employee.service.mapper;

import io.employee.service.dto.EmployeeDto;
import io.employee.service.entity.Employee;

public class EmployeeBuilder {

    public static Employee buildEmployeeByEmployeeDto(EmployeeDto employeeDto){
       return Employee.builder()
                .id(employeeDto.getId())
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .email(employeeDto.getEmail())
               .departmentCode(employeeDto.getDepartmentCode())
                .build();
    }

    public static EmployeeDto buildEmployeeDtoByEmployee(Employee employee){
        return EmployeeDto.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .build();
    }
}
