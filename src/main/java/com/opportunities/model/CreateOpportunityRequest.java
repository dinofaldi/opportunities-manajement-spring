package com.opportunities.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOpportunityRequest {

  @NotBlank
  @Size(max = 100)
  private String company;

  @NotBlank
  @Size(max = 100)
  private String position;

  @NotBlank
  private String description;

  @NotBlank
  @Size(max = 255)
  private String link;

  @NotBlank
  @Size(max = 255)
  private String companyUrl;

  @NotBlank
  @Size(max = 100)
  private String status;
}
