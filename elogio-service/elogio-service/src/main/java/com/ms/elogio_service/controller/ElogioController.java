package com.ms.elogio_service.controller;

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

import com.ms.elogio_service.dtos.ElogioListResponseDto;
import com.ms.elogio_service.dtos.ElogioRequestDto;
import com.ms.elogio_service.dtos.ElogioResponseDto;
import com.ms.elogio_service.entities.Elogio;
import com.ms.elogio_service.service.ElogioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/elogios")
public class ElogioController {

    @Autowired
    private ElogioService elogioService;


    @Operation(summary = "Criar um elogio", description = "Cria um novo elogio com título, descrição e data atual.")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Elogio criado com sucesso."),
    @ApiResponse(responseCode = "400", description = "Dados inválidos.")})
    @PostMapping("/create")
    public ResponseEntity<ElogioResponseDto> createElogio(@Valid @RequestBody ElogioRequestDto elogioDTO) {
        Elogio elogio = elogioService.createElogio(elogioDTO);
        if (elogio == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ElogioResponseDto("Dados inválidos.", null));
        }

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ElogioResponseDto("Elogio criado com sucesso.", elogio));
    }



    @Operation(summary = "Listar todos os elogios.", description = "Retorna a lista de todos os elogios.")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Elogios listados com sucesso."),
    @ApiResponse(responseCode = "404", description = "Nenhum elogio encontrado.")})
    @GetMapping("/")
    public ResponseEntity<ElogioListResponseDto> getAllElogios() {
       List<Elogio> elogios = elogioService.getAllElogios();
        if (elogios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ElogioListResponseDto("Nenhum elogio encontrado.", elogios));
        }

        return ResponseEntity.ok(new ElogioListResponseDto("Elogios listados com sucesso.", elogios));
    }



    @Operation(summary = "Buscar elogio por ID", description = "Retorna um elogio específico pelo seu ID.")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Elogio encontrado com sucesso."),
    @ApiResponse(responseCode = "404", description = "Elogio não encontrado.")})
    @GetMapping("/{id}")
    public ResponseEntity<ElogioResponseDto> getElogioById(@PathVariable UUID id) {
        Optional<Elogio> elogioOpt = elogioService.findById(id);

        return elogioOpt.map(elogio ->
            ResponseEntity.ok(new ElogioResponseDto("Elogio encontrado com sucesso.", elogio))

        ).orElseGet(() ->
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ElogioResponseDto("Elogio não encontrado.", null))
        );
    }


    @Operation(summary = "Atualizar um elogio", description = "Atualiza os dados de um elogio específico.")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Elogio atualizado com sucesso"),
    @ApiResponse(responseCode = "404", description = "Elogio não encontrado para atualizar.")})
    @PutMapping("/{id}")
    public ResponseEntity<ElogioResponseDto> updateElogio(@PathVariable UUID id, @RequestBody ElogioRequestDto elogioDTO) {
        Elogio elogioAtualizado = elogioService.updateElogio(id, elogioDTO);

        if (elogioAtualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ElogioResponseDto("Elogio não encontrado para atualizar.", null));
        }
        return ResponseEntity.status(HttpStatus.OK)
            .body(new ElogioResponseDto("Elogio atualizado com sucesso.", elogioAtualizado));
            
    }


    @Operation(summary = "Deletar um elogio", description = "Remove um elogio pelo seu ID.")
    @ApiResponse(responseCode = "200", description = "Elogio deletado com sucesso.")
    @DeleteMapping("/{id}")
    public ResponseEntity<ElogioResponseDto> deleteElogio(@PathVariable UUID id) {
        try {
            elogioService.deleteElogioByID(id);
            return ResponseEntity.ok(new ElogioResponseDto("Elogio deletado com sucesso.", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ElogioResponseDto(e.getMessage(), null));
        }
    }
    
}


