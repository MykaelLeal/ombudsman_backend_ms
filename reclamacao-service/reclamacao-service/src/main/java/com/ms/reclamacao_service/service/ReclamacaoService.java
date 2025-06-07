package com.ms.reclamacao_service.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.reclamacao_service.dtos.ReclamacaoRequestDto;
import com.ms.reclamacao_service.entitie.Reclamacao;
import com.ms.reclamacao_service.repositories.ReclamacaoRepository;

import jakarta.transaction.Transactional;

@Service
public class ReclamacaoService {

    @Autowired
    private ReclamacaoRepository reclamacaoRepository;

    // Método responsável por cadastrar uma reclamação
    @Transactional
    public Reclamacao createReclamacao(ReclamacaoRequestDto reclamacaoDto) {
        Reclamacao reclamacao = new Reclamacao();
        reclamacao.setTitulo(reclamacaoDto.titulo());
        reclamacao.setDescricao(reclamacaoDto.descricao());

        return reclamacaoRepository.save(reclamacao);
    }

    // Método responsável por buscar todas as reclamações
    public List<Reclamacao> getAllReclamacoes() {
        return reclamacaoRepository.findAll();
    }

    // Método responsável por buscar reclamação por ID
    public Optional<Reclamacao> findById(UUID id) {
        return reclamacaoRepository.findById(id);
    }

    // Método responsável por salvar uma reclamação
    @Transactional
    public Reclamacao salvar(Reclamacao reclamacao) {
        return reclamacaoRepository.save(reclamacao);
    }

    // Atualizar reclamação por ID
    @Transactional
    public Reclamacao updateReclamacao(UUID id, ReclamacaoRequestDto reclamacaoDto) {
        Reclamacao reclamacao = reclamacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reclamação não encontrada."));

        reclamacao.setTitulo(reclamacaoDto.titulo());
        reclamacao.setDescricao(reclamacaoDto.descricao());

        return reclamacaoRepository.save(reclamacao);
    }

    // Deletar reclamação por ID
    @Transactional
    public void deleteReclamacaoByID(UUID id) {
        Reclamacao reclamacao = reclamacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reclamação não encontrada."));

        reclamacaoRepository.delete(reclamacao);
    }
}
