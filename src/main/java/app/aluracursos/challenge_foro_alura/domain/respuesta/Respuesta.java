package app.aluracursos.challenge_foro_alura.domain.respuesta;

import app.aluracursos.challenge_foro_alura.domain.topico.Topico;
import app.aluracursos.challenge_foro_alura.domain.topico.TopicoRepository;
import app.aluracursos.challenge_foro_alura.domain.usuario.Usuario;
import app.aluracursos.challenge_foro_alura.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Respuesta")
@Table(name = "respuestas")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@SecurityRequirement(name = "bearer-key")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;
    
    private String mensaje;

    private LocalDateTime fechaDeCreacion;

    private Boolean solucion;

    public Respuesta(@Valid DatosNuevaRespuesta datos, UsuarioRepository uRepository, TopicoRepository tRepository) {
        this.usuario = uRepository.findById(datos.usuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con el ID: " + datos.usuarioId()));
        this.topico = tRepository.findById(datos.topicoId())
                .orElseThrow(() -> new IllegalArgumentException("Topico no encontrado con el ID: " + datos.topicoId()));

        this.nombre = uRepository.getReferenceById(datos.usuarioId()).getNombre();

        this.mensaje = datos.mensaje();
        this.fechaDeCreacion = LocalDateTime.now();
        this.solucion = false;


    }

    public void actualizarSolucion(Boolean solucion) {
        this.solucion = solucion;
    }
}
