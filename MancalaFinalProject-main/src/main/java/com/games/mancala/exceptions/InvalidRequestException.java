package com.games.mancala.exceptions;

public class InvalidRequestException  extends RuntimeException {
    
    private static final long serialVersionUID = -6383897636742293548L;

    public InvalidRequestException(String s) {
        super(s);
    }
    
    public InvalidRequestException(String s,Exception cause) {
        super(s,cause);
    }

}
