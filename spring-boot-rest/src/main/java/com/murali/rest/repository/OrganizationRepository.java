package com.murali.rest.repository;

import com.murali.rest.core.domain.Organization;
import org.springframework.data.repository.CrudRepository;

public interface OrganizationRepository extends CrudRepository<Organization, Integer> {
}
