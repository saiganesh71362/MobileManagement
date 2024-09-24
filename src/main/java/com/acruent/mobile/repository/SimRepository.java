package com.acruent.mobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acruent.mobile.entity.Sim;

public interface SimRepository extends JpaRepository<Sim, Long> {

	Sim findByName(String name);
}
