package app.aluracursos.challenge_foro_alura.domain.topico;

import app.aluracursos.challenge_foro_alura.domain.respuesta.DatosLimitadosRespuesta;
import app.aluracursos.challenge_foro_alura.domain.respuesta.RespuestaRepository;
import app.aluracursos.challenge_foro_alura.domain.usuario.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.List;

public record DatosDevolucionPorTopico(
        Long topicoId,

        String titulo,

        String mensaje,

        LocalDateTime fechaDeCreacion,

        Cursos curso,

        String Status,

        List<DatosLimitadosRespuesta> respuestas
) {
    public DatosDevolucionPorTopico(Topico topico, RespuestaRepository rRepository, UsuarioRepository uRepository) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaDeCreacion(),
                topico.getCurso(),topico.getStatus(), rRepository.findAllByTopicoId(topico.getId()).stream().map(r -> new DatosLimitadosRespuesta
                        (r.getId(), uRepository.getReferenceById
                        (topico.getUsuario().getId()).getNombre(), r.getMensaje(), r.getFechaDeCreacion(), r.getSolucion())).toList());
    }
}
