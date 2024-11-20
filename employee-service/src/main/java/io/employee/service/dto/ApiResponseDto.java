package io.employee.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApiResponseDto {
    @JsonProperty
    private EmployeeDto employee;
    @JsonProperty
    private DepartmentDto department;
}
