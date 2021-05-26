package com.murali.graphql.resolver;

import com.murali.graphql.dto.Employee;
import com.murali.graphql.dto.EmployeeFilter;
import com.murali.graphql.external.client.EmployeeFeignClient;
import com.murali.graphql.schema.EmployeeDto;
import com.murali.graphql.schema.EmployeeFilterDto;
import com.murali.graphql.schema.FilterFieldDto;
import com.murali.graphql.schema.FilterOperator;
import com.murali.graphql.schema.PagingDto;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class EmployeeQueryResolver implements GraphQLQueryResolver {

	private EmployeeFeignClient employeeFeignClient;

	public List<Employee> getAllEmployee() {
		List<EmployeeDto> employeeDtoList = employeeFeignClient.getEmployee(EmployeeFilterDto.builder().build());
		return employeeDtoList.stream()
				.map(dto -> Employee.builder()
						.age(dto.getAge())
						.dob(LocalDate.parse(dto.getDob(), DateTimeFormatter.ISO_DATE))
						.firstName(dto.getFirstName())
						.lastName(dto.getLastName())
						.departmentId(dto.getDepartmentId())
						.orgId(dto.getOrgId())
						.id(dto.getId())
						.position(dto.getPosition())
						.salary(dto.getSalary())
				.build())
				.collect(Collectors.toList());
	}

	public Employee getEmployee(Integer id) {
		EmployeeDto dto = employeeFeignClient.getEmployee(id+"");
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

	public List<Employee> getEmployeeWithFilter(EmployeeFilter filter) {
		EmployeeFilterDto filterDto = EmployeeFilterDto.builder()
				.page(PagingDto.builder()
						.limit(filter.getPage().getLimit())
						.page(filter.getPage().getPage())
						.build())
				.age(FilterFieldDto.builder()
						.operator(FilterOperator.valueOf(filter.getAge().getOperator().name()))
						.value(filter.getAge().getValue())
						.build())
				.position(FilterFieldDto.builder()
						.operator(FilterOperator.valueOf(filter.getPosition().getOperator().name()))
						.value(filter.getPosition().getValue())
						.build())
				.salary(FilterFieldDto.builder()
						.operator(FilterOperator.valueOf(filter.getSalary().getOperator().name()))
						.value(filter.getSalary().getValue())
						.build())
				.build();
		List<EmployeeDto> employeeDtoList = employeeFeignClient.getEmployee(filterDto);
		return employeeDtoList.stream()
				.map(dto -> Employee.builder()
						.age(dto.getAge())
						.dob(LocalDate.parse(dto.getDob(), DateTimeFormatter.ISO_DATE))
						.firstName(dto.getFirstName())
						.lastName(dto.getLastName())
						.departmentId(dto.getDepartmentId())
						.orgId(dto.getOrgId())
						.id(dto.getId())
						.position(dto.getPosition())
						.salary(dto.getSalary())
						.build())
				.collect(Collectors.toList());
	}
}
