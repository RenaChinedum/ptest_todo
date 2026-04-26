package com.rena.testtodo.persitance.repository;

import com.rena.testtodo.persitance.entity.Users;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);
}
