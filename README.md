# Projeto HealthMonitor 🩺

### 🎯 Objetivo:
Desenvolver um sistema completo de monitoramento de saúde integrado a um dispositivo IoT com ESP32, usando uma arquitetura moderna baseada em Kotlin + Spring Boot + MariaDB + JWT.

---

## 📁 Estrutura da Aplicação

- `healthmonitor-api` → Backend (Spring Boot)
- `healthmonitor-app` → Frontend multiplataforma (Kotlin Multiplatform)
- `esp32-module` → Comunicação via protocolo serial com ESP32

---

## 🚀 Tecnologias

| Tecnologia | Descrição |
|------------|-----------|
| Kotlin     | Linguagem principal |
| Spring Boot | Backend RESTful |
| MariaDB    | Banco de dados relacional |
| JWT        | Autenticação segura |
| ESP32      | Coleta de dados de saúde (batimentos, temperatura, etc) |
| Ktor ou Retrofit | Comunicação frontend/backend |
| Kotlin Multiplatform | Frontend mobile, desktop, web |

---

## 🔐 Fluxo de Autenticação

1. Usuário se autentica via login (email/senha)
2. Backend valida e retorna um token JWT
3. Frontend envia o token nas requisições subsequentes

---

## 🔄 Endpoints principais (backend)

- `POST /auth/login` → Login de usuário
- `POST /auth/register` → Cadastro
- `GET /pacientes` → Listagem de pacientes
- `POST /dados` → Enviar dados de saúde
- `GET /dados/{pacienteId}` → Histórico

---

## 📝 Observação
Este projeto é um estudo acadêmico para a disciplina *Laboratório de Desenvolvimento Multiplataforma - FATEC Itaquera*, sob orientação do Prof. Antônio Carvalho Neto.
