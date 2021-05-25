package com.murali.rest.schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

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

    public Predicate generateCriteria(CriteriaBuilder builder, Path field) {
        try {
            int v = Integer.parseInt(value);
            switch (operator.name()) {
                case "lt": return builder.lt(field, v);
                case "le": return builder.le(field, v);
                case "gt": return builder.gt(field, v);
                case "ge": return builder.ge(field, v);
                case "eq": return builder.equal(field, v);
            }
        } catch (NumberFormatException e) {
            switch (operator.name()) {
                case "endsWith": return builder.like(field, "%" + value);
                case "startsWith": return builder.like(field, value + "%");
                case "contains": return builder.like(field, "%" + value + "%");
                case "eq": return builder.equal(field, value);
            }
        }

        return null;
    }
}
