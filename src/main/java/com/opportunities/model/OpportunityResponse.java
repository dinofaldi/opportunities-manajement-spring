package com.opportunities.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpportunityResponse {
  
  private String id;

  private String company;

  private String position;

  private String description;

  private String link;

  private String companyUrl;

  private String status;
}
