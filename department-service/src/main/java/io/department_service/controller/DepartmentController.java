package io.department_service.controller;

import io.department_service.dto.DepartmentDto;
import io.department_service.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
@Tag(
        name = "Department Service - DepartmentController",
        description = "Department Controller Exposes REST APIs for Department Service"
)
public class DepartmentController {

    private DepartmentService departmentService;

    @Operation(summary = "Create department REST API", description = "Save department object into department database")
    @ApiResponse(responseCode = "201", description = "HTTP Status 201 Created")
    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto saveDepartmentDto = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(saveDepartmentDto, CREATED);
    }

    @Operation(summary = "Get department REST API", description = "Get department object from department database")
    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")
    @GetMapping("/{code}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable String code){
        DepartmentDto saveDepartmentDto = departmentService.getDepartmentByCode(code);
        return new ResponseEntity<>(saveDepartmentDto, OK);
    }
}
