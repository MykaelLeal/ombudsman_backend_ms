package com.ms.reclamacao_service.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ms.reclamacao_service.dtos.ReclamacaoListResponseDto;
import com.ms.reclamacao_service.dtos.ReclamacaoRequestDto;
import com.ms.reclamacao_service.dtos.ReclamacaoResponseDto;
import com.ms.reclamacao_service.entitie.Reclamacao;
import com.ms.reclamacao_service.service.ReclamacaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/reclamacoes")
public class ReclamacaoController {

    @Autowired
    private ReclamacaoService reclamacaoService;


    @Operation(summary = "Criar uma reclamação", description = "Cria uma nova reclamação com título, descrição e data atual.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Reclamação criada com sucesso."),
        @ApiResponse(responseCode = "400", description = "Dados inválidos.")})
    @PostMapping("/create")
    public ResponseEntity<ReclamacaoResponseDto> createReclamacao(@Valid @RequestBody ReclamacaoRequestDto reclamacaoDTO) {
        Reclamacao reclamacao = reclamacaoService.createReclamacao(reclamacaoDTO);
        if (reclamacao == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ReclamacaoResponseDto("Dados inválidos.", null));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ReclamacaoResponseDto("Reclamação criada com sucesso.", reclamacao));
    }



    @Operation(summary = "Listar todas as reclamações", description = "Retorna a lista de todas as reclamações.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reclamações listadas com sucesso."),
        @ApiResponse(responseCode = "404", description = "Nenhuma reclamação encontrada.")})
    @GetMapping("/")
    public ResponseEntity<ReclamacaoListResponseDto> getAllReclamacoes() {
        List<Reclamacao> reclamacoes = reclamacaoService.getAllReclamacoes();
        if (reclamacoes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ReclamacaoListResponseDto("Nenhuma reclamação encontrada.", reclamacoes));
        }

        return ResponseEntity.ok(new ReclamacaoListResponseDto("Reclamações listadas com sucesso.", reclamacoes));
    }



    @Operation(summary = "Buscar reclamação por ID", description = "Retorna uma reclamação específica pelo seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reclamação encontrada com sucesso."),
        @ApiResponse(responseCode = "404", description = "Reclamação não encontrada.")})
    @GetMapping("/{id}")
    public ResponseEntity<ReclamacaoResponseDto> getReclamacaoById(@PathVariable UUID id) {
        Optional<Reclamacao> reclamacaoOpt = reclamacaoService.findById(id);

        return reclamacaoOpt.map(reclamacao ->
            ResponseEntity.ok(new ReclamacaoResponseDto("Reclamação encontrada com sucesso.", reclamacao))

        ).orElseGet(() ->
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ReclamacaoResponseDto("Reclamação não encontrada.", null))
        );
    }



    @Operation(summary = "Atualizar uma reclamação", description = "Atualiza os dados de uma reclamação específica.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reclamação atualizada com sucesso."),
        @ApiResponse(responseCode = "404", description = "Reclamação não encontrada para atualizar.")})
    @PutMapping("/{id}")
    public ResponseEntity<ReclamacaoResponseDto> updateReclamacao(@PathVariable UUID id, @RequestBody ReclamacaoRequestDto reclamacaoDTO) {
        Reclamacao reclamacaoAtualizada = reclamacaoService.updateReclamacao(id, reclamacaoDTO);

        if (reclamacaoAtualizada == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ReclamacaoResponseDto("Reclamação não encontrada para atualizar.", null));
        }
        return ResponseEntity.status(HttpStatus.OK)
            .body(new ReclamacaoResponseDto("Reclamação atualizada com sucesso.", reclamacaoAtualizada));
    }

    

    @Operation(summary = "Deletar uma reclamação", description = "Remove uma reclamação pelo seu ID.")
    @ApiResponse(responseCode = "200", description = "Reclamação deletada com sucesso.")
    @DeleteMapping("/{id}")
    public ResponseEntity<ReclamacaoResponseDto> deleteReclamacao(@PathVariable UUID id) {
        try {
            reclamacaoService.deleteReclamacaoByID(id);
            return ResponseEntity.ok(new ReclamacaoResponseDto("Reclamação deletada com sucesso.", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ReclamacaoResponseDto(e.getMessage(), null));
        }
    }

}

