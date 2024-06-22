package com.server.slosaeng.domain.intensive_care.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.slosaeng.domain.intensive_care.domain.IntensiveCare;

public interface IntensiveCareRepository extends JpaRepository<IntensiveCare, Long> {

}
