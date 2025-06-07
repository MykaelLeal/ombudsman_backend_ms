package com.ms.reclamacao_service.repositories;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.reclamacao_service.entitie.Reclamacao;


@Repository
public interface ReclamacaoRepository extends JpaRepository<Reclamacao, UUID> {


}
