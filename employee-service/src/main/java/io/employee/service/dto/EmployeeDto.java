package io.employee.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {
    @JsonProperty

    private Long id;
    @JsonProperty

    private String firstName;
    @JsonProperty

    private String lastName;
    @JsonProperty

    private String email;
    @JsonProperty

    private String departmentCode;
}
