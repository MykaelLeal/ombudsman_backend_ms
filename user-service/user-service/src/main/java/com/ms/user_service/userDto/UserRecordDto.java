package com.ms.user_service.userDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRecordDto(@NotBlank String name, 
                            @NotBlank @Email String email,
                            @NotBlank String password) {


    
}
