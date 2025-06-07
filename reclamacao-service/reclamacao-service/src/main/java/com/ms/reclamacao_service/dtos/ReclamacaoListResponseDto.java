package com.ms.reclamacao_service.dtos;

import java.util.List;

import com.ms.reclamacao_service.entitie.Reclamacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReclamacaoListResponseDto {
    private String message;
    private List<Reclamacao> reclamacoes;
}
