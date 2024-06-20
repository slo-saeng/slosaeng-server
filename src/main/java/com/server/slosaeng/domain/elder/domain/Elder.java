package com.server.slosaeng.domain.elder.domain;

import com.server.slosaeng.domain.address.domain.City;
import com.server.slosaeng.domain.address.domain.District;
import com.server.slosaeng.domain.address.domain.Nation;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "elder")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Elder {

	@Id
	@Column(name = "id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String idNumber;

	@Column(nullable = false)
	private String phone;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Enumerated(EnumType.STRING)
	private BloodType bloodType;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "nation_id")
	private Nation nation;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "city_id")
	private City city;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "district_id")
	private District district;

	@Column(nullable = false)
	private String detailAddress;

	@Column(length = 1000)
	private String etc;

	public void updateName(String name) {
		this.name = name;
	}

	public void updateIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public void updatePhone(String phone) {
		this.phone = phone;
	}

	public void updateGender(Gender gender) {
		this.gender = gender;
	}

	public void updateBloodType(BloodType bloodType) {
		this.bloodType = bloodType;
	}

	public void updateNation(Nation nation) {
		this.nation = nation;
	}

	public void updateCity(City city) {
		this.city = city;
	}

	public void updateDistrict(District district) {
		this.district = district;
	}

	public void updateDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public void updateEtc(String etc) {
		this.etc = etc;
	}
}
