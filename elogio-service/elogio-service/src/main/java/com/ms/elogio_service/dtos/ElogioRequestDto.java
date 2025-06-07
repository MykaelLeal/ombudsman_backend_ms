package com.ms.elogio_service.dtos;

import jakarta.validation.constraints.NotBlank;

public record ElogioRequestDto (

    @NotBlank(message = "O titulo é obrigatório.")
    String titulo,

    @NotBlank(message = "A descrição é obrigatória.")
    String descricao

) {}
