package com.server.slosaeng.domain.address.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.server.slosaeng.domain.address.domain.Nation;

@Repository
public interface NationRepository extends JpaRepository<Nation, Long> {
}
