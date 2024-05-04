package com.example.Backend.domain.common.repositories;

import com.example.Backend.domain.common.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
