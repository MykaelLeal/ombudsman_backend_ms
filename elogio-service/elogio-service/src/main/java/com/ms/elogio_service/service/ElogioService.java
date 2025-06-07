package com.ms.elogio_service.service;

import java.util.List;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.elogio_service.dtos.ElogioRequestDto;
import com.ms.elogio_service.entities.Elogio;
import com.ms.elogio_service.repositories.ElogioRepository;

import jakarta.transaction.Transactional;


@Service
public class ElogioService {


    @Autowired
    private ElogioRepository elogioRepository;

    // Método responsável por cadastrar um elogio
    @Transactional
    public Elogio createElogio(ElogioRequestDto elogioDto) {

        Elogio elogio = new Elogio();
        elogio.setTitulo(elogioDto.titulo());
        elogio.setDescricao(elogioDto.descricao());

        return elogioRepository.save(elogio);
    }

    
    // Método responsável por buscar todos os elogios
    public List<Elogio> getAllElogios() {
        return elogioRepository.findAll();
    }


    // Método responsável por buscar elogio por ID
    public Optional<Elogio> findById(UUID id) {
       return elogioRepository.findById(id);

    }


    // Método responsável por salvar um elogio
    @Transactional
    public Elogio salvar(Elogio elogio) {
        return elogioRepository.save(elogio);
    }


    // Atualizar elogio por ID
    @Transactional
    public Elogio updateElogio(UUID id, ElogioRequestDto elogioDto) {
        Elogio elogio = elogioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Elogio não encontrado."));

        elogio.setTitulo(elogioDto.titulo());
        elogio.setDescricao(elogioDto.descricao());

        return elogioRepository.save(elogio);
    }


    // Deletar elogio por ID
    @Transactional
    public void deleteElogioByID(UUID id) {
        Elogio elogio = elogioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Elogio não encontrado."));

        elogioRepository.delete(elogio);
       
    }

}
