package com.murali.rest.repository;

import com.murali.rest.core.domain.Organization;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrganizationRepository extends PagingAndSortingRepository<Organization, Integer> {
}
