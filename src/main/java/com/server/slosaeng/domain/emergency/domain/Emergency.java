package com.server.slosaeng.domain.emergency.domain;

import com.server.slosaeng.domain.elder.domain.Elder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
@Table(name = "emergency")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Emergency {

	@Id
	@Column(name = "id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 1000)
	private String info;

	@OneToOne
	private Elder elder;

	public void updateInfo(String info) {
		this.info = info;
	}
}
