package com.murali.graphql.resolver;

import com.murali.graphql.dto.Organization;
import com.murali.graphql.dto.OrganizationInput;
import com.murali.graphql.external.client.OrganizationFeignClient;
import com.murali.graphql.schema.OrganizationDto;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class OrganizationMutableResolver implements GraphQLMutationResolver {

	private OrganizationFeignClient organizationFeignClient;

	public Organization addOrganization(OrganizationInput organizationInput) {
		OrganizationDto dto = organizationFeignClient.createOrganization(OrganizationDto.builder()
				.name(organizationInput.getName())
				.build()
		);
		return Organization.builder()
				.id(dto.getId())
				.name(dto.getName())
				.build();
	}

	public Organization updateOrganization(String id, OrganizationInput organizationInput) {
		OrganizationDto dto = organizationFeignClient.updateOrganization(id, OrganizationDto.builder()
				.id(Integer.valueOf(id))
				.name(organizationInput.getName())
				.build()
		);
		return Organization.builder()
				.id(dto.getId())
				.name(dto.getName())
				.build();	}

}
