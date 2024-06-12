package com.server.slosaeng.domain.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@SuperBuilder
@Table(name = "doctor")
@DiscriminatorValue("DOCTOR")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Doctor extends Member {

	@Column(nullable = false)
	private String phone;

	@Column(nullable = false)
	private String position;

	@Column(nullable = false)
	private String birth;

	public void updatePhone(String phone) {
		this.phone = phone;
	}

	public void updatePosition(String position) {
		this.position = position;
	}

	public void updateBirth(String birth) {
		this.birth = birth;
	}
}
