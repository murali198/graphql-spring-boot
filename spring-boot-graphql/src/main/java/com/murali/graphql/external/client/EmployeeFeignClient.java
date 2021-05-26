package com.murali.graphql.external.client;

import com.murali.graphql.config.FeignConfig;
import com.murali.graphql.schema.EmployeeDto;
import com.murali.graphql.schema.EmployeeFilterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "employeeFeignClient", url = "http://localhost:8080/employee", configuration = FeignConfig.class)
public interface EmployeeFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/filter", consumes = "application/json")
    List<EmployeeDto> getEmployee(EmployeeFilterDto filterDto);

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", consumes = "application/json")
    String deleteEmployee(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}", consumes = "application/json")
    EmployeeDto updateEmployee(@PathVariable("id") String id, EmployeeDto employeeDto);

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    EmployeeDto getEmployee(@PathVariable("id") String id);

}
