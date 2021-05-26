package com.murali.graphql.external.client;

import com.murali.graphql.config.FeignConfig;
import com.murali.graphql.schema.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "organizationFeignClient", url = "http://localhost:8080/org", configuration = FeignConfig.class)
public interface OrganizationFeignClient {

    @RequestMapping(method = RequestMethod.GET)
    List<OrganizationDto> getAllOrganization();

    @RequestMapping(method = RequestMethod.POST)
    OrganizationDto createOrganization(OrganizationDto orgDto);

    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    OrganizationDto updateOrganization(@PathVariable("id") String id, OrganizationDto orgDto);

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    OrganizationDto getOrganization(@PathVariable("id") String id);
}
