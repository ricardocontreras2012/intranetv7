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
public class CommonRCurricularEditMencionService {
    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @param menCodCar
     * @param menCodMen
     * @param menPrefijo
     * @param menPlanComun
     * @param menNom
     * @return
     */
    public String service(GenericSession genericSession, String key, Integer menCodCar, Integer menCodMen, String menPrefijo, String menPlanComun, String menNom) {
        String retValue = SUCCESS;
        Mencion mencionActual = MencionSupport.getMencion(menCodCar, menCodMen); 
        
        if(mencionActual == null)
        {
            retValue = ERROR;
        }
        else
        {
            mencionActual.setMenPrefijo(menPrefijo);
            mencionActual.setMenPlanComun(menPlanComun);
            mencionActual.setMenNom(menNom);
            
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
        
        return retValue;
    }
}
