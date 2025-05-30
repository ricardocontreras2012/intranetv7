/*
 * @(#)InfrastructureExceptionUtil.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package infrastructure.util;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class InfrastructureExceptionUtil extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    public InfrastructureExceptionUtil() {
    }

    /**
     *
     * @param message
     */
    public InfrastructureExceptionUtil(String message) {
        super(message);
    }

    /**
     *
     * @param cause
     */
    public InfrastructureExceptionUtil(Throwable cause) {
        super(cause);
    }

    /**
     *
     * @param message
     * @param cause
     */
    public InfrastructureExceptionUtil(String message, Throwable cause) {
        super(message, cause);
    }
}
