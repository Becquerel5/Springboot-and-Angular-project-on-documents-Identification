package com.fabrication.agent.repositories;

import com.fabrication.entities.Cni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CniRepository extends JpaRepository<Cni,Long> {
}
