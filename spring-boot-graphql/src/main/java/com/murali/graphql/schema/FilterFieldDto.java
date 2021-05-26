package com.murali.graphql.schema;

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
public class FilterFieldDto {

    private FilterOperator operator;
    private String value;

    public String getOperator() {
        return operator.name();
    }

    public void setOperator(String operator) {
        this.operator = FilterOperator.valueOf(operator);
    }
}
