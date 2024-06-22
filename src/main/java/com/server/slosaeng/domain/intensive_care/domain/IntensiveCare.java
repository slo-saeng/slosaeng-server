package com.server.slosaeng.domain.intensive_care.domain;

import com.server.slosaeng.domain.elder.domain.Elder;

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
@Table(name = "intensice_care")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IntensiveCare {

	@Id
	@Column(name = "id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 1000)
	private String info;

	@Enumerated(EnumType.STRING)
	private Grade grade;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "elder_id")
	private Elder elder;

	public void updateInfo(String info) {
		this.info = info;
	}

	public void updateGrade(Grade grade) {
		this.grade = grade;
	}

}
