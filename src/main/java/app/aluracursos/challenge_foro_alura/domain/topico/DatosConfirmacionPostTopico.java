package app.aluracursos.challenge_foro_alura.domain.topico;

import java.time.LocalDateTime;

public record DatosConfirmacionPostTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaDeCreacion,
        String status
) {
    public DatosConfirmacionPostTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaDeCreacion(), topico.getStatus());
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public String titulo() {
        return titulo;
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
