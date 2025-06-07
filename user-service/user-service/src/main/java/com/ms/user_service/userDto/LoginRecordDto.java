package com.ms.user_service.userDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRecordDto(
    @Email
    @NotBlank(message = "O email é obrigatório.")
    String email,

    @NotBlank(message = "A senha é obrigatória.")
    String password
    
) {}
