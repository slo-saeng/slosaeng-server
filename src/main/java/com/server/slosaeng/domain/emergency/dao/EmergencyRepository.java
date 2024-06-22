package com.server.slosaeng.domain.emergency.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.server.slosaeng.domain.emergency.domain.Emergency;

@Repository
public interface EmergencyRepository extends JpaRepository<Emergency, Long> {
}
