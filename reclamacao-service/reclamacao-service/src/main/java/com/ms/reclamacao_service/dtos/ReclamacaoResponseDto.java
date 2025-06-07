package com.ms.reclamacao_service.dtos;



import com.ms.reclamacao_service.entitie.Reclamacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReclamacaoResponseDto {
    private String message;
    private Reclamacao reclamacao;
}

