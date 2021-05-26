package com.murali.graphql.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Organization {

	private Integer id;
	private String name;
	private Set<Employee> employees;

}
