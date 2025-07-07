# 📚 Sistema de Registro Escolar

Sistema de registro escolar desarrollado con **Angular** (frontend) y **Spring Boot** (backend), dockerizado para facilitar su despliegue local.

## 🧱 Tecnologías Utilizadas

- 🔧 **Backend**: Spring Boot 3 + Maven + JWT
- 💻 **Frontend**: Angular + TailwindCSS + PrimeNG
- 🐘 **Base de Datos**: MySQL
- 🐳 **Docker** y **Docker Compose**
- 🔐 Autenticación: JWT para proteger endpoints del sistema
---

## 🚀 Cómo ejecutar la aplicación

### ✅ Requisitos previos

- Docker y Docker Compose instalados

### ▶️ Pasos para ejecutar

1. Clona el repositorio:

   ```bash
   git clone https://github.com/VictorVenegas07/schoolRegistryBackend.git
   cd schoolRegistryBackend

2. Ejecuta los contenedores con Docker Compose:
   ```bash
   docker-compose up --build
3. Accede a las aplicaciones:

🌐 Frontend Angular: http://localhost/login

📦 Backend Spring Boot: http://localhost:8080/swagger-ui/index.html#/

🐘 pgAdmin o Adminer (si está configurado): http://localhost:8081
---
## Acceso a la aplicacion 
- 👤 **UserDefault**: admin
- 🔑 **PasswordUser**: admin123
---
## Diagrama relacional base de datos 

![image](https://github.com/user-attachments/assets/7fd97115-57e9-462b-aecf-302e6168396f)


