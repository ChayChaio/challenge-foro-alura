package app.aluracursos.challenge_foro_alura.domain.respuesta;

import java.time.LocalDateTime;

public record DatosLimitadosRespuesta(
        Long respuestaId,
        String nombre,
        String mensaje,
        LocalDateTime fechaDeCreacion,
        Boolean solucion
) {
    public DatosLimitadosRespuesta(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getNombre(), respuesta.getMensaje(), respuesta.getFechaDeCreacion()
        ,respuesta.getSolucion());
    }
}
