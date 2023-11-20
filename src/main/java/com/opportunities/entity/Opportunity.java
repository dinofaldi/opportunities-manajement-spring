package com.opportunities.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "opportunities")
public class Opportunity {

  @Id
  private String id;

  private String company;

  private String position;

  private String description;

  private String link;

  @Column(name = "company_url")
  private String companyUrl;

  private String status;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;
}