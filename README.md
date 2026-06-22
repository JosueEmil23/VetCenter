readme_content = """# VetCenter: Sistema de Gestión Veterinaria

VetCenter es un ecosistema de microservicios diseñado para la gestión integral de una clínica veterinaria. Este proyecto utiliza una arquitectura basada en Spring Boot, orquestada mediante Docker para facilitar el despliegue y la comunicación entre servicios.

## 🚀 Arquitectura de Servicios
El sistema está compuesto por los siguientes microservicios:

* **API Gateway**: Punto de entrada único para las peticiones del cliente.
* **Eureka Server**: Servidor de descubrimiento de servicios para la comunicación entre microservicios.
* **Auth Service**: Gestión de autenticación, usuarios y seguridad mediante JWT.
* **Duenos & Mascotas**: Gestión de la información básica de clientes y sus mascotas.
* **Reservas**: Gestión de turnos y citas veterinarias.
* **Historial Service**: Almacenamiento de historiales clínicos y eventos.

## 🛠️ Tecnologías Utilizadas
* **Backend**: Java 25, Spring Boot, Spring Cloud, Spring Security, JWT.
* **Orquestación**: Docker, Docker Compose.
* **Comunicación**: Feign Client (Síncrono), RabbitMQ (Asíncrono/Eventos).
* **Bases de Datos**: (Indicar aquí su base de datos, ej: MySQL/PostgreSQL).

## 🐳 Despliegue con Docker

Este proyecto está preparado para ser levantado íntegramente mediante **Docker Compose**.

### Requisitos previos
* Tener instalado [Docker Desktop](https://www.docker.com/products/docker-desktop/) (con Docker Compose).
* Tener instalado Git.

### Instrucciones de ejecución
1. Clona el repositorio:
   ```bash
   git clone [https://github.com/JosueEmil23/VetCenter.git](https://github.com/JosueEmil23/VetCenter.git)
   cd VetCenter
2. **Levanta todos los servicios:**
   ```bash
   docker-compose up --build
