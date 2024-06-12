package com.server.slosaeng.domain.member.application;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.slosaeng.domain.login.application.RefreshTokenService;
import com.server.slosaeng.domain.member.dao.DoctorRepository;
import com.server.slosaeng.domain.member.domain.Doctor;
import com.server.slosaeng.domain.member.domain.Role;
import com.server.slosaeng.domain.member.dto.request.DoctorRequestDto;
import com.server.slosaeng.domain.member.dto.response.DoctorResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorService {
	private final RefreshTokenService refreshTokenService;
	private final DoctorRepository doctorRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public String save(DoctorRequestDto doctorRequestDto) {
		if (isExistDoctor(doctorRequestDto.getId())) {
			throw new IllegalArgumentException("Duplicate doctor id");
		}
		return doctorRepository.save(Doctor.builder()
			.id(doctorRequestDto.getId())
			.password(doctorRequestDto.getPassword())
			.name(doctorRequestDto.getName())
			.role(Role.DOCTOR)
			.position(doctorRequestDto.getPosition())
			.phone(doctorRequestDto.getPhone())
			.birth(doctorRequestDto.getBirth())
			.build()).getId();
	}

	public DoctorResponseDto findById(String doctorId) {
		Doctor doctor = findDoctorById(doctorId);
		return DoctorResponseDto.builder()
			.id(doctor.getId())
			.name(doctor.getName())
			.role(doctor.getRole())
			.position(doctor.getPosition())
			.phone(doctor.getPhone())
			.birth(doctor.getBirth())
			.build();
	}

	public void update(String doctorId, DoctorRequestDto doctorRequestDto) {
		Doctor doctor = findDoctorById(doctorId);
		doctor.updatePassword(bCryptPasswordEncoder.encode(doctorRequestDto.getPassword()));
		doctor.updateName(doctorRequestDto.getName());
		doctor.updatePosition(doctorRequestDto.getPosition());
		doctor.updatePhone(doctorRequestDto.getPhone());
		doctor.updateBirth(doctorRequestDto.getBirth());
		doctorRepository.save(doctor);
	}

	public void delete(String doctorId) {
		refreshTokenService.delete(doctorId);
		doctorRepository.deleteById(doctorId);
	}

	private Doctor findDoctorById(String doctorId) {
		return doctorRepository.findById(doctorId)
			.orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
	}

	private boolean isExistDoctor(String doctorId) {
		return doctorRepository.existsById(doctorId);
	}
}
