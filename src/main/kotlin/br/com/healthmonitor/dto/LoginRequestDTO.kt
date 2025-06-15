package br.com.healthmonitor.dto

import jakarta.validation.constraints.NotBlank

data class LoginRequestDTO(
    @NotBlank(message = "Username cannot be blank")
    val username: String,
    @NotBlank(message = "Password cannot be blank")
    val password: String
)
