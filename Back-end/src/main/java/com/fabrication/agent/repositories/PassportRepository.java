package com.fabrication.agent.repositories;

import com.fabrication.entities.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepository extends JpaRepository<Passport,Long> {
}
