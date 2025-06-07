package com.ms.sugestao_service.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.sugestao_service.dtos.SugestaoRequestDto;
import com.ms.sugestao_service.entities.Sugestao;
import com.ms.sugestao_service.repositories.SugestaoRepository;

import jakarta.transaction.Transactional;

@Service
public class SugestaoService {

    @Autowired
    private SugestaoRepository sugestaoRepository;

    // Criar sugestão
    @Transactional
    public Sugestao createSugestao(SugestaoRequestDto sugestaoDto) {
        Sugestao sugestao = new Sugestao();
        sugestao.setTitulo(sugestaoDto.titulo());
        sugestao.setDescricao(sugestaoDto.descricao());
        return sugestaoRepository.save(sugestao);
    }

    // Listar todas
    public List<Sugestao> getAllSugestoes() {
        return sugestaoRepository.findAll();
    }

    // Buscar por ID
    public Optional<Sugestao> findById(UUID id) {
        return sugestaoRepository.findById(id);
    }

    // Salvar sugestão
    @Transactional
    public Sugestao salvar(Sugestao sugestao) {
        return sugestaoRepository.save(sugestao);
    }

    // Atualizar sugestão
    @Transactional
    public Sugestao updateSugestao(UUID id, SugestaoRequestDto sugestaoDto) {
        Sugestao sugestao = sugestaoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Sugestão não encontrada."));
        sugestao.setTitulo(sugestaoDto.titulo());
        sugestao.setDescricao(sugestaoDto.descricao());
        return sugestaoRepository.save(sugestao);
    }

    // Deletar sugestão
    @Transactional
    public void deleteSugestaoByID(UUID id) {
        Sugestao sugestao = sugestaoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Sugestão não encontrada."));
        sugestaoRepository.delete(sugestao);
    }
}
