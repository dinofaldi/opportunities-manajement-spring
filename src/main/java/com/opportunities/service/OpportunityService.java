package com.opportunities.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.opportunities.entity.Opportunity;
import com.opportunities.entity.User;
import com.opportunities.model.CreateOpportunityRequest;
import com.opportunities.model.OpportunityResponse;
import com.opportunities.model.UpdateStatusRequest;
import com.opportunities.repository.OpportunityRepository;

@Service
public class OpportunityService {
  
  @Autowired
  private OpportunityRepository opportunityRepository;

  @Autowired
  private ValidationService validationService;

  private OpportunityResponse toOpportunityResponse(Opportunity opportunity) {
    return OpportunityResponse.builder()
      .id(opportunity.getId())
      .company(opportunity.getCompany())
      .position(opportunity.getPosition())
      .description(opportunity.getDescription())
      .link(opportunity.getLink())
      .companyUrl(opportunity.getCompanyUrl())
      .status(opportunity.getStatus())
      .build();
  }

  @Transactional
  public OpportunityResponse create(User user, CreateOpportunityRequest createOpportunityRequest) {
    validationService.validate(createOpportunityRequest);

    Opportunity opportunity = new Opportunity();
    opportunity.setId(UUID.randomUUID().toString());
    opportunity.setCompany(createOpportunityRequest.getCompany());
    opportunity.setPosition(createOpportunityRequest.getPosition());
    opportunity.setDescription(createOpportunityRequest.getDescription());
    opportunity.setLink(createOpportunityRequest.getLink());
    opportunity.setCompanyUrl(createOpportunityRequest.getCompanyUrl());
    opportunity.setStatus(createOpportunityRequest.getStatus());
    opportunity.setUser(user);

    opportunityRepository.save(opportunity);

    return toOpportunityResponse(opportunity);
  }

  @Transactional(readOnly = true)
  public OpportunityResponse findById(User user, String id) {
    Opportunity opportunity = opportunityRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));
    if (!opportunity.getUser().getId().equals(user.getId())) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found");
    }

    return toOpportunityResponse(opportunity);
  }

  @Transactional(readOnly = true)
  public Page<OpportunityResponse> findAll(User user, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<Opportunity> opportunities = opportunityRepository.findAllByUser(user, pageable);

    return opportunities.map(this::toOpportunityResponse);
  }

  @Transactional
  public OpportunityResponse update(User user, String id, CreateOpportunityRequest createOpportunityRequest) {
    validationService.validate(createOpportunityRequest);

    Opportunity opportunity = opportunityRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));
    if (!opportunity.getUser().getId().equals(user.getId())) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found");
    }

    opportunity.setCompany(createOpportunityRequest.getCompany());
    opportunity.setPosition(createOpportunityRequest.getPosition());
    opportunity.setDescription(createOpportunityRequest.getDescription());
    opportunity.setLink(createOpportunityRequest.getLink());
    opportunity.setCompanyUrl(createOpportunityRequest.getCompanyUrl());
    opportunity.setStatus(createOpportunityRequest.getStatus());

    opportunityRepository.save(opportunity);

    return toOpportunityResponse(opportunity);
  }

  @Transactional
  public void delete(User user, String id) {
    Opportunity opportunity = opportunityRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));
    if (!opportunity.getUser().getId().equals(user.getId())) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found");
    }

    opportunityRepository.delete(opportunity);
  }

  @Transactional
  public OpportunityResponse updateStatus(User user, String id, UpdateStatusRequest request) {
    Opportunity opportunity = opportunityRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));
    if (!opportunity.getUser().getId().equals(user.getId())) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found");
    }

    opportunity.setStatus(request.getStatus());

    opportunityRepository.save(opportunity);

    return toOpportunityResponse(opportunity);
  }
}
