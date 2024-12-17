package app.aluracursos.challenge_foro_alura.domain.respuesta;

public record DatosSolucionRespuesta(
        Long usuarioId,
        Long topicoId,
        Long respuestaId,
        Boolean solucion
) {
}
