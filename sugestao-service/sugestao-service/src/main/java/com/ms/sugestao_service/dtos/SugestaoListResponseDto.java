package com.ms.sugestao_service.dtos;

import java.util.List;

import com.ms.sugestao_service.entities.Sugestao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SugestaoListResponseDto {
    private String message;
    private List<Sugestao> sugestoes;
}
