package io.employee.service.controller;

import io.employee.service.dto.ApiResponseDto;
import io.employee.service.dto.EmployeeDto;
import io.employee.service.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("api/employees")
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        return new ResponseEntity<>(employeeService.saveEmployee(employeeDto), CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto> getEmployeeByEmail(@RequestParam(name = "email") String email){
        return ResponseEntity.ok(employeeService.getEmployeeByEmail(email));
    }

    @GetMapping("/webclient")
    public ResponseEntity<ApiResponseDto> getEmployeeByEmailUsingWebclient(@RequestParam(name = "email") String email){
        return ResponseEntity.ok(employeeService.getEmployeeByEmailUsingWebclient(email));
    }

    @GetMapping("/openfeign")
    public ResponseEntity<ApiResponseDto> getEmployeeByEmailUsingFeignClient(@RequestParam(name = "email") String email){
        return ResponseEntity.ok(employeeService.getEmployeeByEmailUsingFeignClient(email));
    }

}
