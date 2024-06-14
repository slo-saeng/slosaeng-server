package com.server.slosaeng.domain.member.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.slosaeng.domain.login.application.RefreshTokenService;
import com.server.slosaeng.domain.member.dao.DoctorRepository;
import com.server.slosaeng.domain.member.domain.Doctor;
import com.server.slosaeng.domain.member.domain.Role;
import com.server.slosaeng.domain.member.dto.request.DoctorRequestDto;
import com.server.slosaeng.domain.member.dto.response.DoctorResponseDto;

import jakarta.transaction.Transactional;
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
			.password(bCryptPasswordEncoder.encode(doctorRequestDto.getPassword()))
			.name(doctorRequestDto.getName())
			.role(Role.NOT_APPROVED)
			.position(doctorRequestDto.getPosition())
			.phone(doctorRequestDto.getPhone())
			.birth(doctorRequestDto.getBirth())
			.institutionNumber(doctorRequestDto.getInstitutionNumber())
			.build()).getId();
	}

	public void approve(String doctorId) {
		Doctor doctor = findDoctorById(doctorId);
		doctor.updateRole(Role.DOCTOR);
		doctorRepository.save(doctor);
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
			.institutionNumber(doctor.getInstitutionNumber())
			.build();
	}

	public List<DoctorResponseDto> findNotApprovedDoctors() {
		return doctorRepository.findByRole(Role.NOT_APPROVED).stream()
			.map(doctor -> DoctorResponseDto.builder()
				.id(doctor.getId())
				.name(doctor.getName())
				.role(doctor.getRole())
				.position(doctor.getPosition())
				.phone(doctor.getPhone())
				.birth(doctor.getBirth())
				.institutionNumber(doctor.getInstitutionNumber())
				.build())
			.collect(Collectors.toList());
	}

	public void update(String doctorId, DoctorRequestDto doctorRequestDto) {
		Doctor doctor = findDoctorById(doctorId);
		doctor.updatePassword(bCryptPasswordEncoder.encode(doctorRequestDto.getPassword()));
		doctor.updateName(doctorRequestDto.getName());
		doctor.updatePosition(doctorRequestDto.getPosition());
		doctor.updatePhone(doctorRequestDto.getPhone());
		doctor.updateBirth(doctorRequestDto.getBirth());
		doctor.updateInstitutionNumber(doctorRequestDto.getInstitutionNumber());
		doctorRepository.save(doctor);
	}

	@Transactional
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
