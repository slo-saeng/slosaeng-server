package com.server.slosaeng.domain.address.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.server.slosaeng.domain.address.domain.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
}
