package com.murali.graphql.resolver;

import com.murali.graphql.dto.Department;
import com.murali.graphql.external.client.DepartmentFeignClient;
import com.murali.graphql.schema.DepartmentDto;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@AllArgsConstructor
public class DepartmentQueryResolver implements GraphQLQueryResolver {

	private DepartmentFeignClient departmentFeignClient;

	public List<Department> getAllDepartment(DataFetchingEnvironment environment) {
		return departmentFeignClient.getAllDepartment()
				.stream()
				.map(dto -> Department.builder()
						.name(dto.getName())
						.id(dto.getId())
						.orgId(dto.getOrgId())
						.build()
				)
				.collect(Collectors.toList());
	}

	public Department getDepartment(Integer id) {
		DepartmentDto dto = departmentFeignClient.getDepartment(id+"");
		return Department.builder()
						.name(dto.getName())
						.id(dto.getId())
						.orgId(dto.getOrgId())
						.build();
	}
}
