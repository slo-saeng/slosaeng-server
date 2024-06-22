package com.server.slosaeng.domain.elder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.server.slosaeng.domain.elder.domain.Elder;

@Repository
public interface ElderRepository extends JpaRepository<Elder, Long> {
}
