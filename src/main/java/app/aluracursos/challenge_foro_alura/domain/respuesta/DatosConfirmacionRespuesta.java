package app.aluracursos.challenge_foro_alura.domain.respuesta;

import app.aluracursos.challenge_foro_alura.domain.topico.TopicoRepository;
import app.aluracursos.challenge_foro_alura.domain.usuario.UsuarioRepository;

import java.time.LocalDateTime;

public record DatosConfirmacionRespuesta(
        Long id,

        String nombreUsuario,

        String tituloTopico,

        String mensaje,

        LocalDateTime fechaDeCreacion
) {
    public DatosConfirmacionRespuesta(Respuesta respuesta, RespuestaRepository repository,
                                      UsuarioRepository uRepository, TopicoRepository tRepository) {
        this(respuesta.getId(), uRepository.getReferenceById(repository.getReferenceById(respuesta.getId()).getUsuario().getId()).getNombre()
                , tRepository.getReferenceById(repository.getReferenceById(respuesta.getId()).getTopico().getId()).getTitulo()
                ,respuesta.getMensaje(), respuesta.getFechaDeCreacion());
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public String nombreUsuario() {
        return nombreUsuario;
    }

    @Override
    public String tituloTopico() {
        return tituloTopico;
    }

    @Override
    public String mensaje() {
        return mensaje;
    }

    @Override
    public LocalDateTime fechaDeCreacion() {
        return fechaDeCreacion;
    }
}
