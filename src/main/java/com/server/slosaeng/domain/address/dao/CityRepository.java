package com.server.slosaeng.domain.address.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.server.slosaeng.domain.address.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}