package com.server.slosaeng.domain.institution.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.server.slosaeng.domain.institution.domain.Institution;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {
}
