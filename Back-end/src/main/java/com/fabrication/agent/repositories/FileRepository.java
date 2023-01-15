package com.fabrication.agent.repositories;

import com.fabrication.entities.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileDB,Long> {
}
