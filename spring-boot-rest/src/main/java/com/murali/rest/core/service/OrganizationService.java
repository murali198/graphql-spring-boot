package com.murali.rest.core.service;

import com.murali.rest.core.domain.Organization;
import com.murali.rest.schema.OrganizationDto;

public interface OrganizationService {

    OrganizationDto createOrganization(OrganizationDto organizationDto);

    OrganizationDto getOrganizationById(String id);

    OrganizationDto updateOrganization(String id, OrganizationDto organizationDto);

    String deleteOrganization(String id);
}
