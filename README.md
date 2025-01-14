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
spring.application.name=foro
spring.datasource.url=jdbc:mysql://localhost:3306/foro_db
spring.datasource.username=usuario
spring.datasource.password=contraseña
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.flyway.baseline-on-migrate=true
jwt.secret=mySuperSecretKeyWithAtLeast64Characters1234567890abcdefghijklmnopqrstuvwxyz
jwt.expiration=86400000
spring.jpa.open-in-view=false
```

Nota: la clave jwt.secret debe tener al menos 512 bits (64 caracteres) para algoritmo HS512.
4. **Compila y ejecuta la aplicación:**
```bash
    mvn clean install
    mvn spring-boot:run
```
    La aplicación estará disponible en http://localhost:8080.

## Endpoints

Hay endpoints que necesitan autenticación y otros que no, para los que necesitan autenticacion primero hay que loguearse y cuando se recibe el Bearer token se usa en cada endpoint privado.

Luego de hacer la migracion se tiene que crear un usuario con el endopoint /auth/register enviando username y password y despues se hace el login con las credenciales con las que se registró el usuario.


## Autenticación

### Registrar Usuario
- **POST** `/auth/register`
  - Registra un nuevo usuario.
  - **Body**:
    ```json
    {
      "username": "testuser",
      "password": "password123"
    }
    ```

### Iniciar Sesión
- **POST** `/auth/login`
  - Inicia sesión y devuelve un token JWT.
  - **Body**:
    ```json
    {
      "username": "testuser",
      "password": "password123"
    }
    ```

## Usuarios

### Crear Usuario
- **POST** `/usuarios`
  - Crea un nuevo usuario.
  - **Body**:
    ```json
    {
      "username": "newuser",
      "password": "newpassword"
    }
    ```

### Obtener Todos los Usuarios
- **GET** `/usuarios`
  - Devuelve una lista de todos los usuarios.

### Eliminar Usuario
- **DELETE** `/usuarios/{id}`
  - Elimina un usuario por su ID.

## Tópicos

### Crear Tópico
- **POST** `/topicos`
  - Crea un nuevo tópico.
  - **Body**:
    ```json
    {
      "title": "New Topic",
      "message": "This is a new topic",
      "author": "author1",
      "course": "course1"
    }
    ```

### Obtener Todos los Tópicos
- **GET** `/topicos`
  - Devuelve una lista paginada de tópicos.
  - **Parámetros**:
    - `page`: Número de página (por defecto 0).
    - `size`: Tamaño de la página (por defecto 10).
    - `sort`: Campo y dirección de ordenación (por defecto `createdAt,asc`).

### Obtener Tópico por ID
- **GET** `/topicos/{id}`
  - Devuelve un tópico por su ID.

### Actualizar Tópico
- **PUT** `/topicos/{id}`
  - Actualiza un tópico existente.
  - **Body**:
    ```json
    {
      "title": "Updated Topic",
      "message": "This is an updated topic",
      "status": "ACTIVO",
      "author": "author1",
      "course": "course1"
    }
    ```

### Eliminar Tópico
- **DELETE** `/topicos/{id}`
  - Elimina un tópico por su ID.

## Respuestas

### Crear Respuesta
- **POST** `/respuestas`
  - Crea una nueva respuesta.
  - **Body**:
    ```json
    {
      "message": "This is a reply",
      "topicId": 1,
      "userId": 1
    }
    ```

### Obtener Respuestas por Tópico
- **GET** `/respuestas/topico/{topicId}`
  - Devuelve una lista de respuestas asociadas a un tópico.

### Eliminar Respuesta
- **DELETE** `/respuestas/{id}`
  - Elimina una respuesta por su ID.


