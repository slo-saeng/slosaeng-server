package com.server.slosaeng.domain.login.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.server.slosaeng.domain.login.domain.RefreshToken;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	Optional<RefreshToken> findByMemberId(String memberId);

	Optional<RefreshToken> findByRefreshToken(String refreshToken);

	void deleteByMemberId(String memberId);

	boolean existsByMemberId(String memberId);
}
