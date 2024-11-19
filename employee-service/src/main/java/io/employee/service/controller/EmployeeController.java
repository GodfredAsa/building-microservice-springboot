package io.employee.service.controller;

import io.employee.service.dto.EmployeeDto;
import io.employee.service.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.*;

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
    public ResponseEntity<EmployeeDto> getEmployeeByEmail(@RequestParam(name = "email") String email){
        return new ResponseEntity<>(employeeService.getEmployeeByEmail(email), OK);
    }
}
