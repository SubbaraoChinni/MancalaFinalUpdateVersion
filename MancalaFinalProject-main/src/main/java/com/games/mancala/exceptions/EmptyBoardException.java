package com.games.mancala.exceptions;

public class EmptyBoardException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public EmptyBoardException(String s) {
        super(s);
    }
    
    public EmptyBoardException(String s,Exception cause) {
        super(s,cause);
    }

}
