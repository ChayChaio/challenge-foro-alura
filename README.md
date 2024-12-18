# Challenge Foro Hub - API REST

Este proyecto es una implementación de un foro desarrollado con Java y Spring Boot, donde los usuarios pueden crear tópicos para plantear dudas o sugerencias, y responder a los mismos dentro de una comunidad.

## Índice

1. [Características principales](#características-principales)
2. [Tecnologías utilizadas](#tecnologías-utilizadas)
3. [Endpoints principales](#endpoints-principales)
   - [Tópicos](#tópicos)
   - [Respuestas](#respuestas)
   - [Autenticación](#autenticación)
4. [Instalación y configuración](#instalación-y-configuración)
5. [Pruebas](#pruebas)
6. [Mejoras futuras](#mejoras-futuras)
7. [Autoría](#autoría)

## Características principales

1. **Gestión de tópicos:**
   - Crear, listar, actualizar y eliminar tópicos.
   - Buscar tópicos por palabras clave en el título o contenido.

2. **Gestión de respuestas:**
   - Asociar respuestas a un tópico específico.
   - Seleccionar respuestas como soluciones al tópico.
   - Actualizar el estado del tópico a "Cerrado" cuando tiene al menos una respuesta marcada como solución (pueden seleccionarse más de una).

3. **Autenticación y autorización:**
   - Uso de JWT (JSON Web Tokens) para asegurar el acceso a los endpoints.
   - Roles de usuario (en desarrollo): administrador, moderador, instructor y estudiante.

4. **Documentación interactiva:**
   - Endpoint para probar la API con Swagger: `/swagger-ui.html`.

5. **Persistencia de datos:**
   - Uso de PostgreSQL como base de datos.
   - Migraciones gestionadas con Flyway.

## Tecnologías utilizadas

- **Lenguaje:** Java 17.
- **Frameworks:** Spring Boot 3, Spring Data JPA, Spring Security, Spring Validation.
- **Base de datos:** PostgreSQL.
- **Herramientas adicionales:**
  - Flyway: para control de versiones de la base de datos.
  - Lombok: para reducir el boilerplate de código.
  - BCrypt: para hashear contraseñas de forma segura.
  - springdoc-openapi: para la generación automática de la documentación de la API.

## Endpoints principales

### Tópicos
- **Listar tópicos:** `GET /topicos`
- **Buscar tópicos por palabras clave:** `POST /topicos/busqueda`
- **Crear un tópico:** `POST /topicos`
- **Actualizar un tópico:** `PUT /topicos/{id}`
- **Eliminar un tópico:** `DELETE /topicos/{id}`

### Respuestas
- **Listar respuestas de un tópico:** `GET /topicos/{topicoId}/respuestas`
- **Crear una respuesta:** `POST /topicos/{topicoId}/respuestas`
- **Marcar una respuesta como solución:** `PUT /topicos/{topicoId}/respuestas/{respuestaId}/solucion`
- **Eliminar una respuesta:** `DELETE /topicos/{topicoId}/respuestas/{respuestaId}`

### Autenticación
- **Generar token JWT:** `POST /auth`

## Instalación y configuración

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/ChayChaio/challenge-foro-alura.git
   cd challenge-foro-alura
   ```

2. **Configurar la base de datos:**
   - Crear una base de datos PostgreSQL.
   - Configurar las credenciales en el archivo `application.properties` o `application.yml`.

3. **Ejecutar las migraciones:**
   ```bash
   ./mvnw flyway:migrate
   ```

4. **Compilar y ejecutar la aplicación:**
   ```bash
   ./mvnw spring-boot:run
   ```

5. **Acceder a la documentación:**
   - Abrir el navegador en: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Pruebas

El funcionamiento de la API se puede probar utilizando:
- **Insomnia** o cualquier cliente REST.
- **Swagger** para pruebas interactivas.

## Mejoras futuras

- Implementación completa de la gestión de roles y permisos.
- Optimizaciones en las consultas a la base de datos.

---

## Autoría

Este proyecto fue desarrollado por Rosario Díaz. Para consultas o sugerencias, no dudes en contactarme a través de este repositorio.

