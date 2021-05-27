package com.murali.graphql.resolver.org;

import com.murali.graphql.dto.Organization;
import com.murali.graphql.external.client.OrganizationFeignClient;
import com.murali.graphql.schema.OrganizationDto;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class OrganizationQueryResolver implements GraphQLQueryResolver {

	private OrganizationFeignClient organizationFeignClient;

	public List<Organization> getAllOrganization() {
		return organizationFeignClient.getAllOrganization()
				.stream()
				.map(dto -> Organization.builder()
						.name(dto.getName())
						.id(dto.getId())
						.build()
				)
				.collect(Collectors.toList());
	}

	public Organization getOrganization(String id) {
		OrganizationDto dto = organizationFeignClient.getOrganization(id);
		return Organization.builder()
				.name(dto.getName())
				.id(dto.getId())
				.build();
	}
}
