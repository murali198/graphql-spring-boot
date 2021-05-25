package com.murali.rest.core.service.imp;

import com.murali.rest.core.domain.Employee;
import com.murali.rest.core.service.EmployeeService;
import com.murali.rest.repository.DepartmentRepository;
import com.murali.rest.repository.EmployeeRepository;
import com.murali.rest.repository.OrganizationRepository;
import com.murali.rest.schema.EmployeeDto;
import com.murali.rest.schema.EmployeeFilterDto;
import com.murali.rest.schema.FilterFieldDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository empRepository;
    private DepartmentRepository depRepository;
    private OrganizationRepository orgRepository;

    @Override
    public List<EmployeeDto> getEmployee(EmployeeFilterDto filter) {
        Specification<Employee> spec = null;
        List<Employee> empList;
        if (filter.getSalary() != null)
            spec = bySalary(filter.getSalary());
        if (filter.getAge() != null)
            spec = (spec == null ? byAge(filter.getAge()) : spec.and(byAge(filter.getAge())));
        if (filter.getPosition() != null)
            spec = (spec == null ? byPosition(filter.getPosition()) :
                    spec.and(byPosition(filter.getPosition())));
        if (spec != null) {
            empList = empRepository.findAll(spec);
        } else {
            Iterable<Employee> iterable = empRepository.findAll();
            empList = new ArrayList<>();
            iterable.forEach(empList::add);
        }
        if(empList != null) {
            return empList.stream()
                    .map(emp -> EmployeeDto.builder()
                            .id(emp.getId())
                            .age(emp.getAge())
                            .departmentId(emp.getDepartment().getId())
                            .firstName(emp.getFirstName())
                            .lastName(emp.getLastName())
                            .orgId(emp.getOrganization().getId())
                            .position(emp.getPosition())
                            .salary(emp.getSalary())
                            .build())
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = Employee.builder().age(employeeDto.getAge())
                .department(depRepository.findById(Integer.valueOf(employeeDto.getDepartmentId())).get())
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .age(employeeDto.getAge())
                .position(employeeDto.getPosition())
                .organization(orgRepository.findById(Integer.valueOf(employeeDto.getOrgId())).get())
                .build();
        Employee emp = empRepository.save(employee);
        return EmployeeDto.builder()
                .id(emp.getId())
                .age(emp.getAge())
                .departmentId(emp.getDepartment().getId())
                .firstName(emp.getFirstName())
                .lastName(emp.getLastName())
                .orgId(emp.getOrganization().getId())
                .position(emp.getPosition())
                .salary(emp.getSalary())
                .build();
    }

    @Override
    public EmployeeDto getEmployeeById(String id) {
        Employee emp = empRepository.findById(Integer.valueOf(id)).get();
        return EmployeeDto.builder()
                .id(emp.getId())
                .age(emp.getAge())
                .departmentId(emp.getDepartment().getId())
                .firstName(emp.getFirstName())
                .lastName(emp.getLastName())
                .orgId(emp.getOrganization().getId())
                .position(emp.getPosition())
                .salary(emp.getSalary())
                .build();
    }

    @Override
    public EmployeeDto updateEmployee(String id, EmployeeDto employeeDto) {
        Employee employee = Employee.builder().age(employeeDto.getAge())
                .id(Integer.valueOf(id))
                .department(depRepository.findById(Integer.valueOf(employeeDto.getDepartmentId())).get())
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .age(employeeDto.getAge())
                .position(employeeDto.getPosition())
                .organization(orgRepository.findById(Integer.valueOf(employeeDto.getOrgId())).get())
                .build();
        Employee emp = empRepository.save(employee);
        return EmployeeDto.builder()
                .id(emp.getId())
                .age(emp.getAge())
                .departmentId(emp.getDepartment().getId())
                .firstName(emp.getFirstName())
                .lastName(emp.getLastName())
                .orgId(emp.getOrganization().getId())
                .position(emp.getPosition())
                .salary(emp.getSalary())
                .build();
    }

    @Override
    public String deleteEmployee(String id) {
        empRepository.deleteById(Integer.valueOf(id));
        return "Success";
    }

    private Specification<Employee> bySalary(FilterFieldDto filterField) {
        return (Specification<Employee>) (root, query, builder) -> filterField.generateCriteria(builder, root.get("salary"));
    }

    private Specification<Employee> byAge(FilterFieldDto filterField) {
        return (Specification<Employee>) (root, query, builder) -> filterField.generateCriteria(builder, root.get("age"));
    }

    private Specification<Employee> byPosition(FilterFieldDto filterField) {
        return (Specification<Employee>) (root, query, builder) -> filterField.generateCriteria(builder, root.get("position"));
    }
}
