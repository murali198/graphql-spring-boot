package com.murali.rest.core.service;

import com.murali.rest.schema.DepartmentDto;
import com.murali.rest.schema.EmployeeDto;

public interface DepartmentService {

    DepartmentDto createDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentById(String id);

    DepartmentDto updateDepartment(String id, DepartmentDto departmentDto);

    String deleteDepartment(String id);
}
