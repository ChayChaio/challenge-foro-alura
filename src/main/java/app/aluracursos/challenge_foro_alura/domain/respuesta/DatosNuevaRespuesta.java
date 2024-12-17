package app.aluracursos.challenge_foro_alura.domain.respuesta;

public record DatosNuevaRespuesta(
        Long usuarioId,

        Long topicoId,

        String mensaje
) {

    public DatosNuevaRespuesta(Respuesta respuesta) {
        this(respuesta.getUsuario().getId(),
        respuesta.getTopico().getId(),
         respuesta.getMensaje());
    }
}
