package app.aluracursos.challenge_foro_alura.controller;

import app.aluracursos.challenge_foro_alura.domain.respuesta.*;
import app.aluracursos.challenge_foro_alura.domain.topico.Topico;
import app.aluracursos.challenge_foro_alura.domain.topico.TopicoRepository;
import app.aluracursos.challenge_foro_alura.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos/{topicoId}")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private AccionDeLaRespuesta validar;

    @PostMapping
    public ResponseEntity<DatosConfirmacionRespuesta> nuevaRespuesta(@RequestBody @Valid DatosNuevaRespuesta datos,
                                                              UriComponentsBuilder uriComponentsBuilder){
        Respuesta respuesta = new Respuesta(datos, usuarioRepository, topicoRepository);
        respuestaRepository.save(respuesta);
        URI url = uriComponentsBuilder.path("/topicos/{topicoId}/{respuestaId}").buildAndExpand(respuesta.getTopico().getId(), respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(new DatosConfirmacionRespuesta(
                respuesta, respuestaRepository, usuarioRepository, topicoRepository));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@RequestBody DatosEliminarRespuesta datos,@PathVariable Long id){
        if (!respuestaRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        respuestaRepository.delete(respuesta);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosLimitadosRespuesta> actualizarSolucionRespuesta(@RequestBody DatosSolucionRespuesta datos){
        Topico topico = topicoRepository.getReferenceById(datos.topicoId());
        if (topico.getStatus() == "Cerrado"){
            System.out.println("El tópico ya se encuentra cerrado, pero puede elegir más de una solución.");
        }
        Respuesta respuesta = respuestaRepository.getReferenceById(datos.respuestaId());
        if (!topicoRepository.getReferenceById(datos.topicoId()).getUsuario().getId().equals(datos.usuarioId())) {
            return ResponseEntity.badRequest().build();
        }
        respuesta.actualizarSolucion();
        topico.actualizarStatus(respuesta.getSolucion());
        return ResponseEntity.ok(new DatosLimitadosRespuesta(respuesta));
    }

}
