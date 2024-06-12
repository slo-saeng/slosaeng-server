package com.server.slosaeng.domain.member.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.server.slosaeng.domain.member.domain.Master;

@Repository
public interface MasterRepository extends JpaRepository<Master, String> {
}
