package com.ms.reclamacao_service.dtos;

import jakarta.validation.constraints.NotBlank;

public record ReclamacaoRequestDto (

    @NotBlank(message = "O titulo é obrigatório.")
    String titulo,

    @NotBlank(message = "A descrição é obrigatória.")
    String descricao

) {}
