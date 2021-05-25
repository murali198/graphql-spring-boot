package com.murali.rest.core.service.imp;

import com.murali.rest.core.domain.Department;
import com.murali.rest.core.domain.Organization;
import com.murali.rest.core.service.DepartmentService;
import com.murali.rest.repository.DepartmentRepository;
import com.murali.rest.repository.OrganizationRepository;
import com.murali.rest.schema.DepartmentDto;
import com.murali.rest.schema.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository depRepository;
    private OrganizationRepository orgRepository;

    @Override
    public DepartmentDto createDepartment(DepartmentDto depDto) {
        Department dep = Department.builder()
                .name(depDto.getName())
                .organization(orgRepository.findById(Integer.valueOf(depDto.getOrgId())).get())
                .build();
        Department department = depRepository.save(dep);
        return DepartmentDto.builder()
                .id(department.getId())
                .name(department.getName())
                .orgId(department.getOrganization().getId())
                .build();
    }

    @Override
    public DepartmentDto getDepartmentById(String id) {
        Department department = depRepository.findById(Integer.valueOf(id)).get();
        return DepartmentDto.builder()
                .id(department.getId())
                .name(department.getName())
                .orgId(department.getOrganization().getId())
                .build();
    }

    @Override
    public DepartmentDto updateDepartment(String id, DepartmentDto depDto) {
        Department dep = Department.builder()
                .id(Integer.valueOf(id))
                .name(depDto.getName())
                .organization(orgRepository.findById(Integer.valueOf(depDto.getOrgId())).get())
                .build();
        Department department = depRepository.save(dep);
        return DepartmentDto.builder()
                .id(department.getId())
                .name(department.getName())
                .orgId(department.getOrganization().getId())
                .build();
    }

    @Override
    public String deleteDepartment(String id) {
        depRepository.deleteById(Integer.valueOf(id));
        return "Success";
    }
}
