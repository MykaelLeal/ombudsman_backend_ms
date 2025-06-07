package com.ms.elogio_service.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.elogio_service.entities.Elogio;


@Repository
public interface ElogioRepository extends JpaRepository<Elogio, UUID> {



}


