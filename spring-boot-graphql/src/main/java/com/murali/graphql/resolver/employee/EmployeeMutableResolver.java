package com.murali.graphql.resolver.employee;

import com.murali.graphql.dto.Employee;
import com.murali.graphql.dto.EmployeeInput;
import com.murali.graphql.external.client.EmployeeFeignClient;
import com.murali.graphql.schema.EmployeeDto;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
@AllArgsConstructor
public class EmployeeMutableResolver implements GraphQLMutationResolver {

	private EmployeeFeignClient employeeFeignClient;

	public Employee addEmployee(EmployeeInput employee) {
		EmployeeDto dto = employeeFeignClient.createEmployee(EmployeeDto.builder()
				.age(employee.getAge())
				.departmentId(employee.getDepartmentId())
				.dob(employee.getDob().format(DateTimeFormatter.ISO_DATE))
				.firstName(employee.getFirstName())
				.lastName(employee.getLastName())
				.orgId(employee.getOrganizationId())
				.position(employee.getPosition())
				.salary(employee.getSalary())
				.build()
		);
		return Employee.builder()
				.age(dto.getAge())
				.dob(LocalDate.parse(dto.getDob(), DateTimeFormatter.ISO_DATE))
				.firstName(dto.getFirstName())
				.lastName(dto.getLastName())
				.departmentId(dto.getDepartmentId())
				.orgId(dto.getOrgId())
				.id(dto.getId())
				.position(dto.getPosition())
				.salary(dto.getSalary())
				.build();
	}

	public String deleteEmployee(String id) {
		return employeeFeignClient.deleteEmployee(id);
	}

	public Employee updateEmployee(String id, EmployeeInput employee) {
		EmployeeDto dto = employeeFeignClient.updateEmployee(id, EmployeeDto.builder()
				.id(Integer.valueOf(id))
				.age(employee.getAge())
				.departmentId(employee.getDepartmentId())
				.dob(employee.getDob().format(DateTimeFormatter.ISO_DATE))
				.firstName(employee.getFirstName())
				.lastName(employee.getLastName())
				.orgId(employee.getOrganizationId())
				.position(employee.getPosition())
				.salary(employee.getSalary())
				.build()
		);
		return Employee.builder()
				.id(dto.getId())
				.age(dto.getAge())
				.dob(LocalDate.parse(dto.getDob(), DateTimeFormatter.ISO_DATE))
				.firstName(dto.getFirstName())
				.lastName(dto.getLastName())
				.departmentId(dto.getDepartmentId())
				.orgId(dto.getOrgId())
				.id(dto.getId())
				.position(dto.getPosition())
				.salary(dto.getSalary())
				.build();
	}

}
