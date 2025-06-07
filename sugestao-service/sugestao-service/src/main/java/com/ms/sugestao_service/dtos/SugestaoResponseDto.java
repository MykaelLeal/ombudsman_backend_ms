package com.ms.sugestao_service.dtos;



import com.ms.sugestao_service.entities.Sugestao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SugestaoResponseDto {
    private String message;
    private Sugestao sugestao;
}

