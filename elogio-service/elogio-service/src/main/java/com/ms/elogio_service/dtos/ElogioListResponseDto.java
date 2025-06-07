package com.ms.elogio_service.dtos;

import java.util.List;

import com.ms.elogio_service.entities.Elogio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElogioListResponseDto {
    private String message;
    private List<Elogio> elogios;
}
