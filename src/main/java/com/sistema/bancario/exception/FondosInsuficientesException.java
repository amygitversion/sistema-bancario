package com.sistema.bancario.exception;

import java.text.MessageFormat;

public class FondosInsuficientesException extends RuntimeException{
    public FondosInsuficientesException(Double montoMovimiento, Double saldo) {
        super(MessageFormat.format("Fondos Insuficientes. El Monto a Debitar: {0}, es Mayor que el Saldo Actual: {1}", montoMovimiento, saldo));
    }
}
