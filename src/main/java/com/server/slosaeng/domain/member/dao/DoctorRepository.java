package com.server.slosaeng.domain.member.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.server.slosaeng.domain.member.domain.Doctor;
import com.server.slosaeng.domain.member.domain.Role;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {
	List<Doctor> findByRole(Role role);
}
