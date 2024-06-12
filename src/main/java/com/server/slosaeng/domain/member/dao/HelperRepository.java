package com.server.slosaeng.domain.member.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.server.slosaeng.domain.member.domain.Helper;

@Repository
public interface HelperRepository extends JpaRepository<Helper, String> {
}
