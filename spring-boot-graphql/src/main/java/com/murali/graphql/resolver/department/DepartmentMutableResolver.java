package com.murali.graphql.resolver.department;

import com.murali.graphql.dto.Department;
import com.murali.graphql.dto.DepartmentInput;
import com.murali.graphql.external.client.DepartmentFeignClient;
import com.murali.graphql.schema.DepartmentDto;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class DepartmentMutableResolver implements GraphQLMutationResolver {

	private DepartmentFeignClient departmentFeignClient;

	public Department addDepartment(DepartmentInput dep) {
		DepartmentDto dto= departmentFeignClient.createDepartment(DepartmentDto.builder()
				.name(dep.getName())
				.orgId(dep.getOrganizationId())
				.build()
		);
		return Department.builder()
				.id(dto.getId())
				.orgId(dto.getOrgId())
				.name(dto.getName())
				.build();
	}

	public String deleteDepartment(String id) {
		return departmentFeignClient.deleteDepartment(id);
	}

	public Department updateDepartment(String id, DepartmentInput dep) {
		DepartmentDto dto= departmentFeignClient.updateDepartment(id, DepartmentDto.builder()
				.id(Integer.valueOf(id))
				.name(dep.getName())
				.orgId(dep.getOrganizationId())
				.build()
		);
		return Department.builder()
				.id(dto.getId())
				.orgId(dto.getOrgId())
				.name(dto.getName())
				.build();	}
}
