package com.murali.rest.schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Jacksonized
public class EmployeeDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String position;
    private Integer salary;
    private Integer age;
    private Integer departmentId;
    private Integer orgId;
    private EmployeeFilterDto filter;

}
