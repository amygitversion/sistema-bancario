package com.sistema.bancario.exception;

import java.text.MessageFormat;

public class ClienteExisteException extends Exception{
    public ClienteExisteException (String identificacion){
        super(MessageFormat.format("El cliente com la identificación ya ya existe {0} ", identificacion));
    }
}
