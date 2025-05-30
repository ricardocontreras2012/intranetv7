/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Mencion;
import session.GenericSession;
import infrastructure.support.MencionSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;

/**
 *
 * @author Javier Frez V.
 */
public class CommonRCurricularDeleteUnidadService {
    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @param menCodCar
     * @param menCodMen
     * @param uniCod
     * @param duty
     * @return
     */
    public static String service(GenericSession genericSession, String key, Integer menCodCar, Integer menCodMen, Integer uniCod, String duty) {
        String retValue = SUCCESS;

        Mencion mencionActual = MencionSupport.getMencion(menCodCar, menCodMen); 
        mencionActual.setAtributoMenUnidad(null);
        mencionActual.setUnidad(null);
        
        if("Eliminar".equals(duty)) {
            
        }
        else if("Desligar".equals(duty)) {
            try {
                beginTransaction(ActionUtil.getDBUser());
                ContextUtil.getDAO().getMencionPersistence(ActionUtil.getDBUser()).update(mencionActual);
                commitTransaction();
            }
            catch (Exception e) {
                retValue = ERROR;
                e.printStackTrace();
            }
        }
        else 
        {
            retValue = ERROR;
        }
        
        return retValue;
    }
}
