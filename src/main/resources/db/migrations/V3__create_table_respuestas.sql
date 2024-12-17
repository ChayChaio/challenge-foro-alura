
CREATE TABLE respuestas (
    id BIGSERIAL PRIMARY KEY,
        nombre VARCHAR(255) NOT NULL,
        mensaje TEXT NOT NULL,
        fecha_de_creacion TIMESTAMP NOT NULL,
        topico_id BIGSERIAL NOT NULL,
        usuario_id BIGSERIAL NOT NULL,

   CONSTRAINT fk_respuestas_topico_id FOREIGN KEY (topico_id) REFERENCES topicos(id),
        CONSTRAINT fk_respuestas_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
