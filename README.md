# CHALLENGE - Foro API

Esta es una API RESTful para un sistema de foros desarrollada en Spring Boot. Permite la gestión de usuarios, tópicos, respuestas y autenticación mediante JWT.

<p align="center">
    <img src="https://github.com/migmm/alura_challenge-desafio_1-logica/blob/main/assets/aluraoracle.png" alt="Logo"/>
</p>

## Requisitos

- Java 17 o superior
- Maven
- Base de datos MySQL (o cualquier otra compatible con Spring Data JPA)
- Postman (para probar los endpoints)

## Configuración

1. **Clona el repositorio:**

```bash
   git clone https://github.com/tu-usuario/foro-api.git
   cd foro-api
```
2. **Configura la base de datos:**

Crea una base de datos en MySQL (o tu gestor de bases de datos preferido).

3. **Actualiza el archivo application.properties con las credenciales de tu base de datos:**

        
```bash
    spring.datasource.url=jdbc:mysql://localhost:3306/nombre_de_tu_base_de_datos
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    spring.jpa.hibernate.ddl-auto=update
```

4. **Compila y ejecuta la aplicación:**
```bash
    mvn clean install
    mvn spring-boot:run
```
    La aplicación estará disponible en http://localhost:8080.

## Endpoints

Hay endpoints que necesitan autenticación y otros que no, para los que necesitan autenticacion primero hay que loguearse y cuando se recibe el Bearer token se usa en cada endpoint privado.

Luego de hacer la migracion se puede usar este set de credenciales para pruebas

    Usuario: george_h
    Password: password123


Autenticación

Login: POST /auth/login

    Body:
```json

        {
          "username": "nombre_de_usuario",
          "password": "contraseña"
        }
```
Registro: POST /auth/register

        Body:
```json

        {
          "username": "nuevo_usuario",
          "password": "nueva_contraseña"
        }
```
Usuarios

Crear usuario: POST /users

        Body:
```json
        {
          "username": "nuevo_usuario",
          "password": "nueva_contraseña"
        }
```
Obtener todos los usuarios: GET /users

Obtener usuario por ID: GET /users/{id}

Actualizar usuario: PUT /users/{id}

        Body:
```json

        {
          "username": "usuario_actualizado",
          "password": "contraseña_actualizada"
        }
```
    Eliminar usuario: DELETE /users/{id}

Tópicos

    Crear tópico: POST /topics

        Body:
```json

        {
          "title": "Título del tópico",
          "message": "Mensaje del tópico",
          "author": "Autor del tópico",
          "course": "Curso relacionado"
        }
```
    Obtener todos los tópicos: GET /topics?page=0&size=10&sortBy=title
    - Este endpoint esta visible sin autenticación.

    Obtener tópico por ID: GET /topics/{id}

    Obtener tópico con respuestas: GET /topics/{id}/with-replies

    Actualizar tópico: PUT /topics/{id}

        Body:
```json

        {
          "title": "Título actualizado",
          "message": "Mensaje actualizado",
          "author": "Autor actualizado",
          "course": "Curso actualizado"
        }
```
    Eliminar tópico: DELETE /topics/{id}

Respuestas

    Crear respuesta: POST /replies

        Body:
```json

        {
          "message": "Mensaje de la respuesta",
          "status": "ACTIVE",
          "topicId": 1,
          "userId": 1
        }
```
    Obtener todas las respuestas: GET /replies

    Obtener respuesta por ID: GET /replies/{id}

    Actualizar respuesta: PUT /replies/{id}

        Body:
```json

        {
          "message": "Mensaje actualizado",
          "status": "INACTIVE"
        }
```
Eliminar respuesta: DELETE /replies/{id}


