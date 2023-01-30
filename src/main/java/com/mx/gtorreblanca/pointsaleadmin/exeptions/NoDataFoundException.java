package com.mx.gtorreblanca.pointsaleadmin.exeptions;

import com.mx.gtorreblanca.pointsaleadmin.constants.MessageExceptionConstant;

public class NoDataFoundException extends BusinessException{

    private transient Throwable cause;
    private transient String message = MessageExceptionConstant.NO_DATA_FOUND_MESSAGE;

    public NoDataFoundException() {
        super();
    }

    public NoDataFoundException(final String message) {
        this.message = message;
    }

    public NoDataFoundException(final String message, final Throwable cause) {
        this.message = message;
        this.cause = cause;
    }

    @Override
    public Throwable getCause() {
        return this.cause;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
