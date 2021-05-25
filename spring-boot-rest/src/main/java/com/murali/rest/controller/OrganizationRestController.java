package com.murali.rest.controller;

import com.murali.rest.core.service.OrganizationService;
import com.murali.rest.schema.OrganizationDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "org")
@Slf4j
@AllArgsConstructor
public class OrganizationRestController {

    private OrganizationService orgService;

    @PostMapping
    public ResponseEntity<OrganizationDto> createOrganization(@RequestBody @Validated OrganizationDto orgDto) {
        return ResponseEntity.ok(orgService.createOrganization(orgDto));
    }

    @GetMapping("{id}")
    public ResponseEntity<OrganizationDto> getOrganization(@PathVariable("id") String id) {
        return ResponseEntity.ok(orgService.getOrganizationById(id));
    }

    @PatchMapping("{id}")
    public ResponseEntity<OrganizationDto> updateOrganization(@PathVariable("id") String id, @RequestBody @Validated OrganizationDto orgDto) {
        return ResponseEntity.ok(orgService.updateOrganization(id, orgDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrganization(@PathVariable("id") String id) {
        return ResponseEntity.ok(orgService.deleteOrganization(id));
    }
}
