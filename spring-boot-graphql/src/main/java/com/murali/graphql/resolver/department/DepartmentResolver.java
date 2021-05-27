package com.murali.graphql.resolver.department;

import com.murali.graphql.dto.Department;
import com.murali.graphql.dto.Employee;
import com.murali.graphql.dto.Organization;
import com.murali.graphql.external.client.EmployeeFeignClient;
import com.murali.graphql.external.client.OrganizationFeignClient;
import com.murali.graphql.schema.EmployeeDto;
import com.murali.graphql.schema.OrganizationDto;
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
        List<EmployeeDto> employeeDtos = employeeFeignClient.getEmployeeByDep(dep.getId()+"");
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
