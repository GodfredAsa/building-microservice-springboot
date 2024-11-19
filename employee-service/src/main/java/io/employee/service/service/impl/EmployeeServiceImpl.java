package io.employee.service.service.impl;

import io.employee.service.dto.EmployeeDto;
import io.employee.service.entity.Employee;
import io.employee.service.exception.EmailAlreadyExistsException;
import io.employee.service.exception.ResourceNotFoundException;
import io.employee.service.mapper.EmployeeBuilder;
import io.employee.service.mapper.EmployeeMapper;
import io.employee.service.repository.EmployeeRepository;
import io.employee.service.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        boolean employeeExists = employeeRepository.existsEmployeeByEmail(employeeDto.getEmail());
        if(employeeExists) throw new EmailAlreadyExistsException(String.format("Employee with %s  email exists", employeeDto.getEmail()));
//        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee employee = EmployeeBuilder.buildEmployeeFromEmployeeDto(employeeDto);
        return EmployeeMapper.mapToEmployeeDto(employeeRepository.save(employee));
    }

    @Override
    public EmployeeDto getEmployeeByEmail(String email) {
        Employee employee = employeeRepository.getEmployeeByEmail(email);
        if(employee == null) throw new ResourceNotFoundException("employee", email);
        return EmployeeMapper.mapToEmployeeDto(employee);
    }
}
