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
public class EmployeeFilterDto {

    private FilterFieldDto salary;
    private FilterFieldDto age;
    private FilterFieldDto position;

}
