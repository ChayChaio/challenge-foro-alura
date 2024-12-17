package app.aluracursos.challenge_foro_alura.domain.topico;

import app.aluracursos.challenge_foro_alura.domain.respuesta.RespuestaRepository;
import app.aluracursos.challenge_foro_alura.domain.usuario.UsuarioRepository;
import app.aluracursos.challenge_foro_alura.infra.errores.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccionDelTopico {

    @Autowired
    private UsuarioRepository uRepository;

    @Autowired
    private TopicoRepository tRepository;

    @Autowired
    private RespuestaRepository rRepository;

    public DatosConfirmacionPostTopico postearTopico(DatosTopicoNuevo datosTopicoNuevo){
        if (!uRepository.existsById(datosTopicoNuevo.usuarioId())) {
            throw new ValidacionException("El id de usuario ingresado no existe.");
        }
        if (tRepository.existsByTitulo(datosTopicoNuevo.titulo())){
            throw new ValidacionException("Un tópico con el título ingresado ya existe.");
        }
        var topico = new Topico(datosTopicoNuevo, uRepository);
        tRepository.save(topico);
        return new DatosConfirmacionPostTopico(topico);
    }

    public DatosConfirmacionPostTopico actualizarTopico(Long id, DatosActualizarTopico datosActualizar){
        if (!tRepository.existsById(id)) {
            throw new ValidacionException("El id de tópico ingresado no existe.");
        }
        if (!uRepository.existsById(datosActualizar.usuarioId())){
            throw new ValidacionException("El id de usuario ingresado no existe.");
        }
        if (!tRepository.getReferenceById(id).getUsuario().getId().equals(datosActualizar.usuarioId())){
            throw new ValidacionException("Su id de usuario no tiene autorización para modificar este post.");
        }
        if (tRepository.existsByTitulo(datosActualizar.titulo())){
            if (tRepository.getReferenceById(id).getTitulo().equals(datosActualizar.titulo())) {
                throw new ValidacionException("El título actualizado es igual al título original.");
            } else {
            throw new ValidacionException("Un tópico con el título ingresado ya existe.");
            }
        }
        if (tRepository.existsByMensaje(datosActualizar.mensaje())){
            if (tRepository.getReferenceById(id).getMensaje().equals(datosActualizar.mensaje())) {
                throw new ValidacionException("El mensaje actualizado es igual al mensaje original.");
            } else {
                throw new ValidacionException("Un tópico con el mensaje ingresado ya existe.");
            }
        }
        Topico topico = tRepository.getReferenceById(id);
        topico.actualizar(datosActualizar);
        return new DatosConfirmacionPostTopico(topico);
    }

    public void eliminarTopico(Long id){
        if (!tRepository.existsById(id)) {
           throw new ValidacionException("El id de tópico ingresado no existe.");
        }
        Topico topico = tRepository.getReferenceById(id);
        tRepository.deleteById(id);

    }

    public DatosCompletosTopico topicoPorId(Long id){
        if (!tRepository.existsById(id)) {
           throw new ValidacionException("El id de tópico ingresado no existe");
        }
        return new DatosCompletosTopico(tRepository.getReferenceById(id), uRepository, rRepository);
    }
}
