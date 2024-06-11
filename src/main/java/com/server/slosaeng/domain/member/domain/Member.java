package com.server.slosaeng.domain.member.domain;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@SuperBuilder
@Table(name = "member")
@DiscriminatorColumn(name = "D_TYPE")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member implements UserDetails {

	@Id
	@Column(nullable = false, unique = true)
	private String id;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String name;

	@CreationTimestamp
	@Column(name = "joined_at", updatable = false, nullable = false)
	private LocalDateTime joinedAt;

	public Member(String id, String password, String name) {
		this.id = id;
		this.password = password;
		this.name = name;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("user"));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return id;
	}

	public void updatePassword(String password) {
		this.password = password;
	}

	public void updateName(String name) {
		this.name = name;
	}
}
