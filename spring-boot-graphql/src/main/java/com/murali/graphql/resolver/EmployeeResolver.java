package com.murali.graphql.resolver;

import com.murali.graphql.dto.Department;
import com.murali.graphql.dto.Employee;
import com.murali.graphql.dto.Organization;
import com.murali.graphql.external.client.DepartmentFeignClient;
import com.murali.graphql.external.client.OrganizationFeignClient;
import com.murali.graphql.schema.DepartmentDto;
import com.murali.graphql.schema.OrganizationDto;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class EmployeeResolver implements GraphQLResolver<Employee> {

    public DepartmentFeignClient depFeignClient;
    public OrganizationFeignClient orgFeignClient;

    public Department department(Employee employee) {
        log.debug("Invocking Employee.department()...");
        if(employee.getDepartmentId() != null) {
            DepartmentDto departmentDto = depFeignClient.getDepartment(employee.getDepartmentId()+"");
            return Department.builder()
                    .id(departmentDto.getId())
                    .name(departmentDto.getName())
                    .build();
        }
        return null;
    }

    public Organization organization(Employee employee) {
        log.debug("Invocking Employee.organization()...");
        if(employee.getOrgId() != null) {
            OrganizationDto dto = orgFeignClient.getOrganization(employee.getOrgId()+"");
            return Organization.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .build();
        }
        return null;
    }
}
