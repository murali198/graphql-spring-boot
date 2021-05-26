package com.murali.rest.core.service;

import com.murali.rest.schema.OrganizationDto;

import java.util.List;

public interface OrganizationService {

    OrganizationDto createOrganization(OrganizationDto organizationDto);

    OrganizationDto getOrganizationById(String id);

    OrganizationDto updateOrganization(String id, OrganizationDto organizationDto);

    String deleteOrganization(String id);

    List<OrganizationDto> getAllOrganization();

}
