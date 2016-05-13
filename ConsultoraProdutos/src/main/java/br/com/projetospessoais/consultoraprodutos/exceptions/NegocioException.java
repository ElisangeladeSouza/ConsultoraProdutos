package br.com.projetospessoais.consultoraprodutos.exceptions;

/**
 * A classe NegocioException representa um tipo de exceção usado para 
 * representar erros de negócio.
 * 
 * @author elisangela
 */
public class NegocioException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Classe destinada a servir de ponte para tratar exceções de negócio.
     * @param message
     */
    public NegocioException(String message) {
        super(message);
    }
}
