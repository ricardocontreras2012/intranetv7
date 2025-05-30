/*
 * @(#)ActionValidationPosSupport.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package infrastructure.support.action;

import infrastructure.support.action.common.ActionCommonSupport;
import java.util.List;
import static infrastructure.util.AppStaticsUtil.ACTION_EXCEPTION;

/**
 * Clase de la cual se extienden los actions se extiendende la aplicacion que
 * validan un valor de una lista (validacion programatica).
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class ActionValidationPosSupport extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer pos;

    /**
     * Method description
     *
     *
     * @return
     */
    @Override
    public String execute() {
        String retValue;  

        try {
            retValue = isValidParam()
                    ? this.action()
                    : INPUT;        
            
        } catch (Exception e) {                 
            retValue = ACTION_EXCEPTION;
        }

        return retValue;
    }

    /**
     * Validación programática.
     *
     * @return true: Si el parámetro es válido; false: de lo contrario.
     */
    protected boolean isValidParam() {
       return false;
    }

    /**
     * Valida que la posición (pos) esté en rango permito en la lista.
     *
     * @param pos Indice o posición en la lista.
     * @param lista Lista de valores.
     * @return true: si pos en un índice que está dentro del rango permitido.
     */
    @SuppressWarnings("rawtypes")
    protected static boolean isValidPos(Integer pos, List lista) throws IllegalArgumentException {
        if (pos == null || pos < 0 || lista == null || pos >= lista.size()) {  
            throw new IllegalArgumentException();
        }

        return true;
    }

    /**
     * Obtiene la posición selecionada de la lista.
     *
     * @return Posición de la lista.
     */
    public Integer getPos() {
        return pos;
    }

    /**
     * Resistra la posición seleccionada de la lista.
     *
     * @param pos Posición seleccionada.
     */
    public void setPos(Integer pos) {
        this.pos = pos;
    }
}
