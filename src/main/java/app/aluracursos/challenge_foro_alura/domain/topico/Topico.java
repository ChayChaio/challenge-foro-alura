package app.aluracursos.challenge_foro_alura.domain.topico;

import app.aluracursos.challenge_foro_alura.domain.respuesta.Respuesta;
import app.aluracursos.challenge_foro_alura.domain.usuario.Usuario;
import app.aluracursos.challenge_foro_alura.domain.usuario.UsuarioRepository;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensaje;

    private LocalDateTime fechaDeCreacion;

    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private Cursos curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL)
    private List<Respuesta> respuestas = new ArrayList<>();


    public Topico(DatosTopicoNuevo datos, UsuarioRepository usuarioRepository) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaDeCreacion = LocalDateTime.now();
        this.status = "Abierto";
        this.usuario = usuarioRepository.findById(datos.usuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con el ID: " + datos.usuarioId()));

        this.curso = datos.curso();
    }


    public void actualizar(@Valid DatosActualizarTopico datosActualizar) {
        if (datosActualizar.titulo() != null) {
            this.titulo = datosActualizar.titulo();
        }
        if (datosActualizar.mensaje() != null) {
            this.mensaje = datosActualizar.mensaje();
        }
    }


    @Override
    public String toString() {
        return "Topico{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", fechaDeCreacion=" + fechaDeCreacion +
                ", status=" + status +
                ", usuario=" + usuario.getNombre() +
                ", curso=" + curso +
                '}';
    }

}


