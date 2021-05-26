package com.murali.rest.repository;

import com.murali.rest.core.domain.Department;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DepartmentRepository extends PagingAndSortingRepository<Department, Integer>, JpaSpecificationExecutor<Department> {

}
