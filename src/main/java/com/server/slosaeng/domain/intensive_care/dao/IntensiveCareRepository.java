package com.server.slosaeng.domain.intensive_care.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.server.slosaeng.domain.intensive_care.domain.IntensiveCare;

@Repository
public interface IntensiveCareRepository extends JpaRepository<IntensiveCare, Long> {

}
