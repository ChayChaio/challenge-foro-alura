package app.aluracursos.challenge_foro_alura.infra.errores;

public class ValidacionException extends RuntimeException {
    public ValidacionException(String mensaje) {
        super(mensaje);
    }
}