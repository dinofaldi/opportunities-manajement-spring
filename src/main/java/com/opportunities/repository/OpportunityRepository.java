package com.opportunities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.opportunities.entity.Opportunity;
import com.opportunities.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OpportunityRepository extends JpaRepository<Opportunity, String> {
  
  Page<Opportunity> findAllByUser(User user, Pageable pageable);
}
