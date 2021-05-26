package com.murali.graphql.dto;

import com.murali.graphql.schema.PagingDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeFilter {

	private PagingInput page;
	private FilterField salary;
	private FilterField age;
	private FilterField position;
}
