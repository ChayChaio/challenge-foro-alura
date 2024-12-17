package app.aluracursos.challenge_foro_alura.controller;

import app.aluracursos.challenge_foro_alura.domain.respuesta.RespuestaRepository;
import app.aluracursos.challenge_foro_alura.domain.topico.*;
import app.aluracursos.challenge_foro_alura.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private AccionDelTopico validar;

    @PostMapping
    public ResponseEntity<DatosConfirmacionPostTopico> enviarNuevoTopico(@RequestBody @Valid DatosTopicoNuevo datosTopicoNuevo,
                UriComponentsBuilder uriComponentsBuilder){
        DatosConfirmacionPostTopico datosConfirmacion = validar.postearTopico(datosTopicoNuevo);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(datosConfirmacion.id()).toUri();
        return ResponseEntity.created(url).body(datosConfirmacion);
    }

    @GetMapping
    public ResponseEntity<Page<DatosConfirmacionPostTopico>> listarTodosLosTopicos(@PageableDefault
                                                                            @SortDefault(sort = "status", direction = Sort.Direction.ASC)
                                                                            @SortDefault(sort = "id", direction = Sort.Direction.ASC)
                                                                            Pageable pageable){
    return ResponseEntity.ok(topicoRepository.findAll(pageable).map(DatosConfirmacionPostTopico::new));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosConfirmacionPostTopico> actualizarTopico(@PathVariable("id") Long id, @RequestBody @Valid DatosActualizarTopico datosActualizar){
        DatosConfirmacionPostTopico datosActualizados = validar.actualizarTopico(id, datosActualizar);
        return ResponseEntity.ok(datosActualizados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        validar.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/topicoPorId/{id}")
    public ResponseEntity<DatosCompletosTopico> buscarTopicoPorId(@PathVariable Long id){
        return ResponseEntity.ok(validar.topicoPorId(id));
    }

    @PostMapping("/buscar")
    public ResponseEntity<List<DatosDevolucionPorTopico>> buscarTopicoPorPalabraClave(@RequestBody
                                                                   @Schema(description = "Request para la búsqueda de tópicos") DatosBusquedaTopicos datos){

        if (!usuarioRepository.existsById(datos.usuarioId())){
            return ResponseEntity.notFound().build();
        }

        List<Topico> topicos = topicoRepository.buscarPorPalabraClave(datos.busqueda());

        if (topicos.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        List<DatosDevolucionPorTopico> datosTopicos = topicos
                .stream()
                .map(t -> new DatosDevolucionPorTopico(t,respuestaRepository, usuarioRepository))
                .toList();
        if (datosTopicos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(datosTopicos);
    }

}
