package com.poec.checkpoint.domaine.exceptions;

public class PositionOutOfGridException extends RuntimeException{
    public PositionOutOfGridException(String message) {
        super(message);
    }
}
