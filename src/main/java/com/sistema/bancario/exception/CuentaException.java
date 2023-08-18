package com.sistema.bancario.exception;

import java.text.MessageFormat;

public class CuentaException extends Exception {
    public CuentaException(String tipoCuenta, String nombreCliente) {
        super(MessageFormat.format("Ya existe la cuenta tipo {0} para el cliente {1}", tipoCuenta, nombreCliente));
    }

    public CuentaException(String id) {
        super(MessageFormat.format("No se encontraron datos para el id {0} ", id));
    }
}
