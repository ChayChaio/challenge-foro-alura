
CREATE TABLE topicos (
    id BIGSERIAL PRIMARY KEY,
        titulo VARCHAR(255) NOT NULL,
        mensaje TEXT NOT NULL,
        fecha_de_creacion TIMESTAMP NOT NULL,
        status BOOLEAN NOT NULL,
        usuario_id BIGSERIAL NOT NULL,
        curso VARCHAR(255) NOT NULL,

   CONSTRAINT fk_topicos_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
