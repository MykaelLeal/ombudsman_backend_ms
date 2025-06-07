package com.ms.sugestao_service.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.sugestao_service.dtos.SugestaoListResponseDto;
import com.ms.sugestao_service.dtos.SugestaoRequestDto;
import com.ms.sugestao_service.dtos.SugestaoResponseDto;
import com.ms.sugestao_service.entities.Sugestao;
import com.ms.sugestao_service.service.SugestaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/sugestoes")
public class SugestaoController {

    @Autowired
    private SugestaoService sugestaoService;

    @Operation(summary = "Criar uma sugestão", description = "Cria uma nova sugestão com título, descrição e data atual.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Sugestão criada com sucesso."),
        @ApiResponse(responseCode = "400", description = "Dados inválidos.")
    })
    @PostMapping("/create")
    public ResponseEntity<SugestaoResponseDto> createSugestao(@Valid @RequestBody SugestaoRequestDto sugestaoDTO) {
        Sugestao sugestao = sugestaoService.createSugestao(sugestaoDTO);
        if (sugestao == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new SugestaoResponseDto("Dados inválidos.", null));
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SugestaoResponseDto("Sugestão criada com sucesso.", sugestao));
    }

    @Operation(summary = "Listar todas as sugestões", description = "Retorna a lista de todas as sugestões.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sugestões listadas com sucesso."),
        @ApiResponse(responseCode = "404", description = "Nenhuma sugestão encontrada.")
    })
    @GetMapping("/")
    public ResponseEntity<SugestaoListResponseDto> getAllSugestoes() {
        List<Sugestao> sugestoes = sugestaoService.getAllSugestoes();
        if (sugestoes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new SugestaoListResponseDto("Nenhuma sugestão encontrada.", sugestoes));
        }

        return ResponseEntity.ok(new SugestaoListResponseDto("Sugestões listadas com sucesso.", sugestoes));
    }

    @Operation(summary = "Buscar sugestão por ID", description = "Retorna uma sugestão específica pelo seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sugestão encontrada com sucesso."),
        @ApiResponse(responseCode = "404", description = "Sugestão não encontrada.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SugestaoResponseDto> getSugestaoById(@PathVariable UUID id) {
        Optional<Sugestao> sugestaoOpt = sugestaoService.findById(id);

        return sugestaoOpt.map(sugestao ->
            ResponseEntity.ok(new SugestaoResponseDto("Sugestão encontrada com sucesso.", sugestao))
        ).orElseGet(() ->
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new SugestaoResponseDto("Sugestão não encontrada.", null))
        );
    }

    @Operation(summary = "Atualizar uma sugestão", description = "Atualiza os dados de uma sugestão específica.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sugestão atualizada com sucesso."),
        @ApiResponse(responseCode = "404", description = "Sugestão não encontrada para atualizar.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SugestaoResponseDto> updateSugestao(@PathVariable UUID id, @RequestBody SugestaoRequestDto sugestaoDTO) {
        Sugestao sugestaoAtualizada = sugestaoService.updateSugestao(id, sugestaoDTO);

        if (sugestaoAtualizada == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new SugestaoResponseDto("Sugestão não encontrada para atualizar.", null));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SugestaoResponseDto("Sugestão atualizada com sucesso.", sugestaoAtualizada));
    }

    @Operation(summary = "Deletar uma sugestão", description = "Remove uma sugestão pelo seu ID.")
    @ApiResponse(responseCode = "200", description = "Sugestão deletada com sucesso.")
    @DeleteMapping("/{id}")
    public ResponseEntity<SugestaoResponseDto> deleteSugestao(@PathVariable UUID id) {
        try {
            sugestaoService.deleteSugestaoByID(id);
            return ResponseEntity.ok(new SugestaoResponseDto("Sugestão deletada com sucesso.", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new SugestaoResponseDto(e.getMessage(), null));
        }
    }
}
