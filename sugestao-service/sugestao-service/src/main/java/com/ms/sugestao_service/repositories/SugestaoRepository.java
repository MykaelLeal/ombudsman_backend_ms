package com.ms.sugestao_service.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.sugestao_service.entities.Sugestao;

@Repository
public interface SugestaoRepository extends JpaRepository<Sugestao, UUID> {


}


