
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE topic (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    created_at DATETIME NOT NULL,
    status VARCHAR(50) NOT NULL,
    author VARCHAR(255) NOT NULL,
    course VARCHAR(255) NOT NULL
);

CREATE TABLE reply (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    message TEXT NOT NULL,
    created_at DATETIME NOT NULL,
    topic_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (topic_id) REFERENCES topic(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);

INSERT INTO user (username, password) VALUES
('john_doe', 'password123'),
('jane_smith', 'password123'),
('alice_w', 'password123'),
('bob_brown', 'password123'),
('charlie_d', 'password123'),
('diana_r', 'password123'),
('edward_k', 'password123'),
('fiona_g', 'password123'),
('george_h', 'password123'),
('hannah_m', 'password123');

INSERT INTO topic (title, message, created_at, status, author, course) VALUES
('Cómo usar Spring Boot', 'Estoy aprendiendo Spring Boot y tengo algunas preguntas.', NOW(), 'ACTIVO', 'john_doe', 'Spring Boot'),
('Mejores prácticas para APIs REST', '¿Cuáles son las mejores prácticas para diseñar APIs REST?', NOW(), 'ACTIVO', 'jane_smith', 'Diseño de APIs'),
('Cierres en JavaScript', '¿Alguien puede explicar los cierres en JavaScript en términos simples?', NOW(), 'ACTIVO', 'alice_w', 'JavaScript'),
('Python vs Java', '¿Qué lenguaje es mejor para el desarrollo backend?', NOW(), 'ACTIVO', 'bob_brown', 'Programación'),
('Docker para principiantes', '¿Cómo empiezo con Docker?', NOW(), 'ACTIVO', 'charlie_d', 'DevOps'),
('Hooks en React', '¿Qué son los hooks en React y cómo los uso?', NOW(), 'ACTIVO', 'diana_r', 'React'),
('Optimización de SQL', '¿Cómo puedo optimizar mis consultas SQL?', NOW(), 'ACTIVO', 'edward_k', 'Base de datos'),
('Arquitectura de microservicios', '¿Cuáles son los pros y contras de los microservicios?', NOW(), 'ACTIVO', 'fiona_g', 'Arquitectura'),
('Conceptos básicos de Kubernetes', '¿Alguien puede explicar los conceptos básicos de Kubernetes?', NOW(), 'ACTIVO', 'george_h', 'DevOps'),
('Pruebas unitarias en Java', '¿Cuáles son las mejores herramientas para pruebas unitarias en Java?', NOW(), 'ACTIVO', 'hannah_m', 'Java');

INSERT INTO reply (message, created_at, topic_id, user_id) VALUES
('Primero, asegúrate de tener Java instalado.', NOW(), 1, 2),
('Luego, crea un proyecto con Spring Initializr.', NOW(), 1, 3),
('Usa nombres de recursos significativos y métodos HTTP adecuados.', NOW(), 2, 1),
('Siempre versiona tus APIs.', NOW(), 2, 4),
('Los cierres son funciones que recuerdan su ámbito léxico.', NOW(), 3, 5),
('Son útiles para la encapsulación.', NOW(), 3, 6),
('Python es genial para el desarrollo rápido.', NOW(), 4, 7),
('Java es mejor para aplicaciones a gran escala.', NOW(), 4, 8),
('Comienza instalando Docker en tu máquina.', NOW(), 5, 9),
('Luego, aprende a crear imágenes de Docker.', NOW(), 5, 10);