package fintrack.exceptions;

/**
 * Exceção personalizada para entrada inválida do usuário
 */
public class EntradaInvalidaException extends Exception {

    public EntradaInvalidaException(String mensagem) {
        super(mensagem);
    }

    public EntradaInvalidaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}

