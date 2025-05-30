/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.support.action;

import static com.opensymphony.xwork2.Action.INPUT;
import infrastructure.support.action.common.ActionCommonSupport;
import static infrastructure.util.AppStaticsUtil.ACTION_EXCEPTION;

/**
 *
 * @author Ricardo
 */
public class ActionValidationCursoSupport extends ActionCommonSupport {

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
            //log.error(FormatUtil.msgLog("Exception " + e.getMessage() + ' ' + Arrays.toString(e.getStackTrace())));
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

    protected boolean isValidPos(Integer pos) throws IllegalArgumentException {
        if (pos == null || pos < 0 ||  pos >= getGenericSession().getWorkSession(getKey()).getCursoList().size()) {
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

