package br.com.healthmonitor.service

import br.com.healthmonitor.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email)
            ?: throw UsernameNotFoundException("Usuário com email $email não encontrado.")

        return org.springframework.security.core.userdetails.User(
            user.email,
            user.password,
            emptyList() // ou user.roles.map { SimpleGrantedAuthority(it.name) } se usar roles
        )
    }
}