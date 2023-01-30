package com.mx.gtorreblanca.pointsaleadmin.exeptions;

import com.mx.gtorreblanca.pointsaleadmin.constants.MessageExceptionConstant;

public class DataOriginException extends BusinessException{

    private transient Throwable cause;
    private transient String message = MessageExceptionConstant.DATA_ORIGIN_MESSAGE;

    public DataOriginException() {
        super();
    }

    public DataOriginException(final String message) {
        this.message = message;
    }

    public DataOriginException(final String message, final Throwable cause) {
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
