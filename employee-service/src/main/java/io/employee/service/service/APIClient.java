package io.employee.service.service;

import io.employee.service.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(url = "http://localhost:8082", value = "DEPARTMENT-SERVICE")
@FeignClient(name = "DEPARTMENT-SERVICE") // uses eureka client internal LB to call different instances of Dpt srv

public interface APIClient {

    @GetMapping("api/departments/{code}")
    DepartmentDto getDepartmentByCode(@PathVariable String code);
}
