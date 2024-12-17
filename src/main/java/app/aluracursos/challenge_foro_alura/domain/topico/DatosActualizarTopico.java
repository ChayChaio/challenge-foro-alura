package app.aluracursos.challenge_foro_alura.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotNull
        Long topicoId,

        @NotNull
        Long usuarioId,

        String titulo,

        String mensaje
) {
    public DatosActualizarTopico(Long topicoId, Long usuarioId, String titulo, String mensaje) {
        this.topicoId = topicoId;
        this.usuarioId = usuarioId;
        this.titulo = titulo;
        this.mensaje = mensaje;
    }
}
