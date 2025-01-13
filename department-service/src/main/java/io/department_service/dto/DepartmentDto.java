package io.department_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "DepartmentDto Model Info")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    private Long id;
    @Schema(description = "Department name ")
    private String name;
    @Schema(description = "Department Description ")
    private String description;
    @Schema(description = "Department Code ")
    private String code;
}
