package com.murali.graphql.resolver;

import com.murali.graphql.dto.Department;
import com.murali.graphql.dto.Employee;
import com.murali.graphql.dto.Organization;
import com.murali.graphql.external.client.EmployeeFeignClient;
import com.murali.graphql.external.client.OrganizationFeignClient;
import com.murali.graphql.schema.OrganizationDto;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor
public class DepartmentResolver implements GraphQLResolver<Department> {

    public EmployeeFeignClient employeeFeignClient;
    private OrganizationFeignClient orgFeignClient;

    public Organization organization(Department department) {
        log.debug("Invocking Department.organization()...");
        if(department.getOrgId() != null) {
            OrganizationDto dto = orgFeignClient.getOrganization(department.getOrgId()+"");
            return Organization.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .build();
        }
        return null;
    }

    public Set<Employee> employees(Department dep) {
        log.debug("Invocking Department.employees()...");
        return new HashSet<>();
    }
}
