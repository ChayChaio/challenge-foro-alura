package app.aluracursos.challenge_foro_alura.domain.respuesta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {


    void deleteAllByTopicoId(Long id);

    @Query("SELECT r FROM Respuesta r WHERE r.topico.id = :id ORDER BY r.solucion DESC")
    List<Respuesta> findAllByTopicoId(Long id);
}
