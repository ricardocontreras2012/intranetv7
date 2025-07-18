/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import static com.opensymphony.xwork2.Action.ERROR;
import domain.model.Mencion;
import domain.model.MencionId;
import session.GenericSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Javier Frez V.
 */
public class CommonRCurricularCreateMencionService {
    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @param menCodCar
     * @param menPrefijo
     * @param menPlanComun
     * @param menNom
     * @return
     */
    public String service(GenericSession genericSession, String key, Integer menCodCar, String menPrefijo, String menPlanComun, String menNom) {
        List<Mencion> listaMenciones = ContextUtil.getDAO().getMencionPersistence(ActionUtil.getDBUser()).findByCarrera(menCodCar);
        Integer max_menCodMen = listaMenciones.stream().map(Mencion::getId).filter(Objects::nonNull).map(MencionId::getMenCodMen).filter(Objects::nonNull).max(Integer::compareTo).orElse(null);
        String retValue = SUCCESS;
        
        try {
            Mencion newMencion = new Mencion();
            MencionId newMencionId = new MencionId();
            newMencionId.setMenCodCar(menCodCar);
            newMencionId.setMenCodMen((max_menCodMen + 1));
            newMencion.setId(newMencionId);
            newMencion.setMenNom(menNom);
            newMencion.setMenPrefijo(menPrefijo);
            newMencion.setMenPlanComun(menPlanComun);
            newMencion.setCarrera(ContextUtil.getDAO().getCarreraPersistence(ActionUtil.getDBUser()).find(menCodCar));
            newMencion.setUnidad(null);

            beginTransaction(ActionUtil.getDBUser());
            ContextUtil.getDAO().getMencionPersistence(ActionUtil.getDBUser()).save(newMencion);
            commitTransaction();
        }
        catch (Exception e) {
            retValue = ERROR;
            e.printStackTrace();
        }

        return retValue;
    }
}
