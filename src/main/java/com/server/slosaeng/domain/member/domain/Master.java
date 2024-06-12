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
@Table(name = "master")
@DiscriminatorValue("MASTER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Master extends Member {

	@Column(nullable = false)
	private Long institutionNumber;

	public void updateInstitutionNumber(Long institutionNumber) {
		this.institutionNumber = institutionNumber;
	}
}
