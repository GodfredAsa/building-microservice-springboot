package io.employee.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DepartmentDto {
    @JsonProperty
    private Long id;
    @JsonProperty

    private String name;
    @JsonProperty

    private String description;
    @JsonProperty

    private String code;
}
