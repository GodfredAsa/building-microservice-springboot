package io.employee.service.service.impl;

import io.employee.service.dto.ApiResponseDto;
import io.employee.service.dto.DepartmentDto;
import io.employee.service.dto.EmployeeDto;
import io.employee.service.entity.Employee;
import io.employee.service.exception.EmailAlreadyExistsException;
import io.employee.service.exception.ResourceNotFoundException;
import io.employee.service.mapper.EmployeeBuilder;
import io.employee.service.mapper.EmployeeMapper;
import io.employee.service.repository.EmployeeRepository;
import io.employee.service.service.APIClient;
import io.employee.service.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private RestTemplate restTemplate;
    private WebClient webClient;
    private APIClient apiClient;
    private static final String URL = "http://localhost:8082/api/departments/";

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        boolean employeeExists = employeeRepository.existsEmployeeByEmail(employeeDto.getEmail());
        if(employeeExists) throw new EmailAlreadyExistsException(String.format("Employee with %s  email exists", employeeDto.getEmail()));
//        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee employee = EmployeeBuilder.buildEmployeeByEmployeeDto(employeeDto);
        return EmployeeMapper.mapToEmployeeDto(employeeRepository.save(employee));
    }


    public List<EmployeeDto> getEmployees(){
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeMapper::mapToEmployeeDto)
                .toList();
    }

    @Override
    public ApiResponseDto getEmployeeByEmail(String email) {
        Employee employee = employeeRepository.getEmployeeByEmail(email);
        if(employee == null) throw new ResourceNotFoundException("employee", email);
        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(URL + employee.getDepartmentCode() , DepartmentDto.class );
        DepartmentDto departmentDto = responseEntity.getBody();
        return ApiResponseDto
                .builder()
                .employee(EmployeeMapper.mapToEmployeeDto(employee))
                .department(departmentDto)
                .build();
    }

    @Override
    public ApiResponseDto getEmployeeByEmailUsingWebclient(String email) {
        Employee employee = employeeRepository.getEmployeeByEmail(email);
        if(employee == null) throw new ResourceNotFoundException("employee", email);
        DepartmentDto departmentDto = webClient.get()
                .uri(URL + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block(); // block is for synchronous

        return ApiResponseDto
                .builder()
                .employee(EmployeeMapper.mapToEmployeeDto(employee))
                .department(departmentDto)
                .build();
    }

    public ApiResponseDto getEmployeeByEmailUsingFeignClient(String email) {
        Employee employee = employeeRepository.getEmployeeByEmail(email);
        if (employee == null) throw new ResourceNotFoundException("employee", email);
        DepartmentDto departmentDto = apiClient.getDepartmentByCode(employee.getDepartmentCode());
        return ApiResponseDto
                .builder()
                .employee(EmployeeMapper.mapToEmployeeDto(employee))
                .department(departmentDto)
                .build();
    }
}
