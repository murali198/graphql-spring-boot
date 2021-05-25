package com.murali.rest.controller;

import com.murali.rest.core.service.DepartmentService;
import com.murali.rest.schema.DepartmentDto;
import com.murali.rest.schema.EmployeeDto;
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

@RestController
@RequestMapping(value = "department")
@Slf4j
@AllArgsConstructor
public class DepartmentRestController {

    private DepartmentService depService;

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody @Validated DepartmentDto depDto) {
        return ResponseEntity.ok(depService.createDepartment(depDto));
    }

    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("id") String id) {
        return ResponseEntity.ok(depService.getDepartmentById(id));
    }

    @PatchMapping("{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("id") String id, @RequestBody @Validated DepartmentDto depDto) {
        return ResponseEntity.ok(depService.updateDepartment(id, depDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") String id) {
        return ResponseEntity.ok(depService.deleteDepartment(id));
    }
}