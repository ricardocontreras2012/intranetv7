/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.support.action.post;

import static com.opensymphony.xwork2.Action.INPUT;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.action.ServletRequestAware;
import infrastructure.support.action.common.ActionCommonSupport;
import static infrastructure.util.AppStaticsUtil.ACTION_DENIED;
import static infrastructure.util.AppStaticsUtil.ACTION_EXCEPTION;

/**
 * Clase de la cual se extienden los actions se extiendende la aplicacion que
 * validan un valor de una lista (validacion programatica).
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class ActionPostValidationSupport extends ActionCommonSupport implements ServletRequestAware{

    private static final long serialVersionUID = 1L;
    private Integer pos;
    private HttpServletRequest request;

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
            if ("post".equalsIgnoreCase(request.getMethod())) {

                retValue = isValidParam()
                        ? this.action()
                        : INPUT;
            } else {
                retValue = ACTION_DENIED;
            }
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

    @Override
    public void withServletRequest(HttpServletRequest hsr) {
        request = hsr;        
    }
}
