package com.ms.sugestao_service.dtos;

import jakarta.validation.constraints.NotBlank;

public record SugestaoRequestDto (

    @NotBlank(message = "O titulo é obrigatório.")
    String titulo,

    @NotBlank(message = "A descrição é obrigatória.")
    String descricao

) {}
