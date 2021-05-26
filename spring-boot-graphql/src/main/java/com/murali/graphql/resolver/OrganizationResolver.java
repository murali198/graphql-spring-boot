package com.murali.graphql.resolver;

import com.murali.graphql.dto.Department;
import com.murali.graphql.dto.Employee;
import com.murali.graphql.dto.Organization;
import com.murali.graphql.external.client.DepartmentFeignClient;
import com.murali.graphql.external.client.EmployeeFeignClient;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor
public class OrganizationResolver implements GraphQLResolver<Organization> {

    public EmployeeFeignClient employeeFeignClient;
    private DepartmentFeignClient departmentFeignClient;

    public Set<Department>  departments(Organization org) {
        log.debug("Invocking Organization.departments()...");
        return new HashSet<>();
    }

    public Set<Employee> employees(Organization org) {
        log.debug("Invocking Organization.employees()...");
        return new HashSet<>();
    }
}
