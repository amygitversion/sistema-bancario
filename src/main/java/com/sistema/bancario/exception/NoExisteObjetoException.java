package com.sistema.bancario.exception;

import java.text.MessageFormat;

public class NoExisteObjetoException extends RuntimeException{
    public NoExisteObjetoException(String descripcionObjeto, String id) {
        super(MessageFormat.format("No existe {0} {1}",descripcionObjeto, id));
    }
}
