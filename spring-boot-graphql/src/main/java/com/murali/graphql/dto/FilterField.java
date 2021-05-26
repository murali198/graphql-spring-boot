package com.murali.graphql.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterField {

	private FilterOperator operator;
	private String value;

	public void setOperator(String operator) {
		this.operator = FilterOperator.valueOf(operator);
	}
}
