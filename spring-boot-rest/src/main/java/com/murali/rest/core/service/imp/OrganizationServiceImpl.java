package com.murali.rest.core.service.imp;

import com.murali.rest.core.domain.Organization;
import com.murali.rest.core.service.OrganizationService;
import com.murali.rest.repository.OrganizationRepository;
import com.murali.rest.schema.OrganizationDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository orgRepository;

    @Override
    public OrganizationDto createOrganization(OrganizationDto organizationDto) {
        Organization org = Organization.builder()
                .name(organizationDto.getName())
                .build();
        Organization organization = orgRepository.save(org);
        return OrganizationDto.builder()
                .id(organization.getId())
                .name(organization.getName())
                .build();
    }

    @Override
    public OrganizationDto getOrganizationById(String id) {
        Organization organization = orgRepository.findById(Integer.valueOf(id)).get();
        return OrganizationDto.builder()
                .id(organization.getId())
                .name(organization.getName())
                .build();
    }

    @Override
    public OrganizationDto updateOrganization(String id, OrganizationDto organizationDto) {
        Organization org = Organization.builder()
                .id(Integer.valueOf(id))
                .name(organizationDto.getName())
                .build();
        Organization organization = orgRepository.save(org);
        return OrganizationDto.builder()
                .id(organization.getId())
                .name(organization.getName())
                .build();
    }

    @Override
    public String deleteOrganization(String id) {
        orgRepository.deleteById(Integer.valueOf(id));
        return "Success";
    }

    @Override
    public List<OrganizationDto> getAllOrganization() {
        List<Organization> organizations = new ArrayList<>();
        orgRepository.findAll().forEach(organizations::add);
        return organizations.stream()
                .map(org -> OrganizationDto.builder()
                        .name(org.getName())
                        .id(org.getId())
                        .build()
                )
                .collect(Collectors.toList());
    }
}
