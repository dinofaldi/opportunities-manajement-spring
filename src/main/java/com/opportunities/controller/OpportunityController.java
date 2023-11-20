package com.opportunities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.opportunities.entity.User;
import com.opportunities.model.CreateOpportunityRequest;
import com.opportunities.model.OpportunityResponse;
import com.opportunities.model.UpdateStatusRequest;
import com.opportunities.model.WebResponse;
import com.opportunities.service.OpportunityService;

@RestController
public class OpportunityController {
  
  @Autowired
  private OpportunityService opportunityService;

  @PostMapping(
    path = "/api/opportunities",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public WebResponse<OpportunityResponse> create(User user, @RequestBody CreateOpportunityRequest request) {
    OpportunityResponse opportunityResponse = opportunityService.create(user, request);
    return WebResponse.<OpportunityResponse>builder().data(opportunityResponse).build();
  }

  @GetMapping(
    path = "/api/opportunities/{id}",
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public WebResponse<OpportunityResponse> findById(User user, @PathVariable("id") String id) {
    OpportunityResponse opportunityResponse = opportunityService.findById(user, id);
    return WebResponse.<OpportunityResponse>builder().data(opportunityResponse).build();
  }

  @GetMapping(
    path = "/api/opportunities",
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public WebResponse<Page<OpportunityResponse>> findAll(User user, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
    Page<OpportunityResponse> opportunityResponses = opportunityService.findAll(user, page, size);
    return WebResponse.<Page<OpportunityResponse>>builder().data(opportunityResponses).build();
  }

  @PutMapping(
    path = "/api/opportunities/{id}",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public WebResponse<OpportunityResponse> update(User user, @PathVariable("id") String id, @RequestBody CreateOpportunityRequest request) {
    OpportunityResponse opportunityResponse = opportunityService.update(user, id, request);
    return WebResponse.<OpportunityResponse>builder().data(opportunityResponse).build();
  }

  @DeleteMapping(
    path = "/api/opportunities/{id}",
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public WebResponse<String> delete(User user, @PathVariable("id") String id) {
    opportunityService.delete(user, id);
    return WebResponse.<String>builder().data("OK").build();
  }

  @PutMapping(
    path = "/api/opportunities/{id}/status",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public WebResponse<OpportunityResponse> updateStatus(User user, @PathVariable("id") String id, @RequestBody UpdateStatusRequest request) {
    OpportunityResponse opportunityResponse = opportunityService.updateStatus(user, id, request);
    return WebResponse.<OpportunityResponse>builder().data(opportunityResponse).build();
  }
}
