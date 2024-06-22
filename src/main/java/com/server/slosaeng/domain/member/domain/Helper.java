package com.server.slosaeng.domain.member.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@SuperBuilder
@Table(name = "helper")
@DiscriminatorValue("HELPER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Helper extends Member {

	@Column(nullable = false)
	private String phone;

	@Column(nullable = false)
	private String idNumber;

	@ElementCollection
	private List<Long> elderIds = new ArrayList<>();

	public void updatePhone(String phone) {
		this.phone = phone;
	}

	public void updateIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public void addElderId(Long elderId) {
		elderIds.add(elderId);
	}
}
