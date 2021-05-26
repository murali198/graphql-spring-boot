package com.murali.graphql.external.client;

import com.murali.graphql.config.FeignConfig;
import com.murali.graphql.dto.Department;
import com.murali.graphql.schema.DepartmentDto;
import com.murali.graphql.schema.EmployeeDto;
import com.murali.graphql.schema.EmployeeFilterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "departmentFeignClient", url = "http://localhost:8080/department", configuration = FeignConfig.class)
public interface DepartmentFeignClient {

    @RequestMapping(method = RequestMethod.GET)
    List<DepartmentDto> getAllDepartment();

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", consumes = "application/json")
    String deleteDepartment(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}", consumes = "application/json")
    DepartmentDto updateDepartment(@PathVariable("id") String id, DepartmentDto depDto);

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    DepartmentDto createDepartment(DepartmentDto depDto);

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    DepartmentDto getDepartment(@PathVariable("id") String id);

}
