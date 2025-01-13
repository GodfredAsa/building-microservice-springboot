package io.department_service;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Department-Service REST APIs",
				description = "Department-Service REST API Documentation",
				contact = @Contact(
						name = "Godfred Asumadu Asamoah",
						email = "degreatasaa@gmail.com",
						url = "https://godfredasa.github.io/my_portfolio/"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://godfredasa.github.io/my_portfolio/"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Department-Service",
				url = "https://godfredasa.github.io/my_portfolio/"
		)
)
@SpringBootApplication
public class DepartmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartmentServiceApplication.class, args);
	}

}
