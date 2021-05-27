package com.murali.rest.repository;

import com.murali.rest.core.domain.Department;
import com.murali.rest.core.domain.Organization;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DepartmentRepository extends PagingAndSortingRepository<Department, Integer>, JpaSpecificationExecutor<Department> {

    List<Department> findByOrganization(Organization org);

}
