package com.murali.rest.repository;

import com.murali.rest.core.domain.Department;
import com.murali.rest.core.domain.Employee;
import com.murali.rest.core.domain.Organization;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {

    List<Employee> findByDepartment(Department department);

    List<Employee> findByOrganization(Organization organization);

}
