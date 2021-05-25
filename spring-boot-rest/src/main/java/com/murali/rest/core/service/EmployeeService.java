package com.murali.rest.core.service;

import com.murali.rest.schema.EmployeeDto;
import com.murali.rest.schema.EmployeeFilterDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(String id);

    EmployeeDto updateEmployee(String id, EmployeeDto employeeDto);

    String deleteEmployee(String id);

    List<EmployeeDto> getEmployee(EmployeeFilterDto filterDto);
}
