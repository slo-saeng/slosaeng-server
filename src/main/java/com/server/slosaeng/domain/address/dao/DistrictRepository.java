package com.server.slosaeng.domain.address.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.server.slosaeng.domain.address.domain.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
	List<District> findAllByCityId(Long cityId);
}
