package com.ms.elogio_service.dtos;


import com.ms.elogio_service.entities.Elogio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElogioResponseDto {
    private String message;
    private Elogio elogio;
}

