package com.murali.graphql.resolver.org;

import com.murali.graphql.dto.Department;
import com.murali.graphql.dto.Employee;
import com.murali.graphql.dto.Organization;
import com.murali.graphql.external.client.DepartmentFeignClient;
import com.murali.graphql.external.client.EmployeeFeignClient;
import com.murali.graphql.schema.EmployeeDto;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class OrganizationResolver implements GraphQLResolver<Organization> {

    public EmployeeFeignClient employeeFeignClient;
    private DepartmentFeignClient departmentFeignClient;

    public Set<Department>  departments(Organization org) {
        log.debug("Invocking Organization.departments()...");
        return departmentFeignClient.getDepartmentByOrg(org.getId()+"")
                .stream()
                .map(dto -> Department.builder()
                        .name(dto.getName())
                        .id(dto.getId())
                        .orgId(dto.getOrgId())
                        .build()
                )
                .collect(Collectors.toSet());
    }

    public Set<Employee> employees(Organization org) {
        log.debug("Invocking Organization.employees()...");
        List<EmployeeDto> employeeDtos = employeeFeignClient.getEmployeeByOrg(org.getId()+"");
        return employeeDtos.stream()
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
                .collect(Collectors.toSet());
    }
}
