package github.jpc.libraryapi.exceptions;

public class OperacaoNaoPermitidaException extends RuntimeException{

    public OperacaoNaoPermitidaException(String mensagem){
        super(mensagem);
    }
}
