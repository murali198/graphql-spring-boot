package com.murali.rest.controller;

import com.murali.rest.core.service.EmployeeService;
import com.murali.rest.schema.EmployeeDto;
import com.murali.rest.schema.EmployeeFilterDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "employee")
@Slf4j
@AllArgsConstructor
public class EmployeeRestController {

    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody @Validated EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.createEmployee(employeeDto));
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("id") String id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PatchMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") String id, @RequestBody @Validated EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") String id) {
        return ResponseEntity.ok(employeeService.deleteEmployee(id));
    }

    @PostMapping("/filter")
    public ResponseEntity<List<EmployeeDto>> getEmployee(@RequestBody @Validated EmployeeFilterDto filterDto) {
        return ResponseEntity.ok(employeeService.getEmployee(filterDto));
    }
}
