package br.com.healthmonitor.dto

import jakarta.validation.constraints.NotBlank

data class LoginRequestDTO(
    @NotBlank(message = "Username não pode ser em branco")
    val username: String,
    @NotBlank(message = "Password não pode ser em branco")
    val password: String
)