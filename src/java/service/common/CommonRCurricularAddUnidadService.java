/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Mencion;
import domain.model.Unidad;
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
public class CommonRCurricularAddUnidadService {
    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @param duty
     * @param menCodCar
     * @param menCodMen
     * @param uniCod
     * @param uniNom
     * @param uniCC
     * @param uniUrl
     * @param uniTipo
     * @param uniColorSala
     * @param uniAcadMayor
     * @param uniAdmMayor
     * @param uniMayor
     * @param uniSuperior
     * @return
     */
    public String service(GenericSession genericSession, String key, String duty, Integer menCodCar, Integer menCodMen, Integer uniCod, String uniNom, Integer uniCC, String uniUrl, Integer uniTipo, String uniColorSala, Integer uniAcadMayor, Integer uniAdmMayor, Integer uniMayor, Integer uniSuperior) {
        String retValue = SUCCESS;
        
        if(null == duty)
        {
            retValue = ERROR;
        }
        
        else switch (duty) {
            case "create":
                {
                    Unidad newUnidad = new Unidad();
                    newUnidad.setUniCod(uniCod);
                    newUnidad.setUniNom(uniNom);
                    newUnidad.setUniCC(uniCC);
                    newUnidad.setUniUrl(uniUrl);
                    newUnidad.setUniTipo(uniTipo);
                    newUnidad.setUniColorSala(uniColorSala);
                    newUnidad.setAtributoUniAcadMayor(uniAcadMayor);
                    newUnidad.setAtributoUniAdmMayor(uniAdmMayor);
                    newUnidad.setAtributoUniMayor(uniMayor);
                    newUnidad.setAtributoUniSuperior(uniSuperior);
                    newUnidad.setUniAcadMayor(ContextUtil.getDAO().getUnidadRepository(ActionUtil.getDBUser()).find(uniAcadMayor));
                    newUnidad.setUniAdmMayor(ContextUtil.getDAO().getUnidadRepository(ActionUtil.getDBUser()).find(uniAdmMayor));
                    newUnidad.setUniMayor(ContextUtil.getDAO().getUnidadRepository(ActionUtil.getDBUser()).find(uniMayor));
                    newUnidad.setUniSuperior(ContextUtil.getDAO().getUnidadRepository(ActionUtil.getDBUser()).find(uniSuperior));
                    Mencion mencionActual = MencionSupport.getMencion(menCodCar, menCodMen);
                    mencionActual.setAtributoMenUnidad(uniCod);
                    mencionActual.setUnidad(newUnidad);
                    try {
                        beginTransaction(ActionUtil.getDBUser());
                        ContextUtil.getDAO().getUnidadRepository(ActionUtil.getDBUser()).save(newUnidad);
                        ContextUtil.getDAO().getMencionRepository(ActionUtil.getDBUser()).update(mencionActual);
                        commitTransaction();
                    }
                    catch (Exception e) {
                        retValue = ERROR;
                        e.printStackTrace();
                    }       break;
                }
            case "update":
                {
                    Unidad newUnidad = ContextUtil.getDAO().getUnidadRepository(ActionUtil.getDBUser()).findById(uniCod);
                    newUnidad.setUniNom(uniNom);
                    newUnidad.setUniCC(uniCC);
                    newUnidad.setUniUrl(uniUrl);
                    newUnidad.setUniTipo(uniTipo);
                    newUnidad.setUniColorSala(uniColorSala);
                    newUnidad.setAtributoUniAcadMayor(uniAcadMayor);
                    newUnidad.setAtributoUniAdmMayor(uniAdmMayor);
                    newUnidad.setAtributoUniMayor(uniMayor);
                    newUnidad.setAtributoUniSuperior(uniSuperior);
                    newUnidad.setUniAcadMayor(ContextUtil.getDAO().getUnidadRepository(ActionUtil.getDBUser()).find(uniAcadMayor));
                    newUnidad.setUniAdmMayor(ContextUtil.getDAO().getUnidadRepository(ActionUtil.getDBUser()).find(uniAdmMayor));
                    newUnidad.setUniMayor(ContextUtil.getDAO().getUnidadRepository(ActionUtil.getDBUser()).find(uniMayor));
                    newUnidad.setUniSuperior(ContextUtil.getDAO().getUnidadRepository(ActionUtil.getDBUser()).find(uniSuperior));
                    Mencion mencionActual = MencionSupport.getMencion(menCodCar, menCodMen);
                    mencionActual.setAtributoMenUnidad(uniCod);
                    mencionActual.setUnidad(newUnidad);
                    try {
                        beginTransaction(ActionUtil.getDBUser());
                        ContextUtil.getDAO().getUnidadRepository(ActionUtil.getDBUser()).update(newUnidad);
                        ContextUtil.getDAO().getMencionRepository(ActionUtil.getDBUser()).update(mencionActual);
                        commitTransaction();
                    }
                    catch (Exception e) {
                        retValue = ERROR;
                        e.printStackTrace();
                    }       break;
                }
            default:
                retValue = ERROR;
                break;
        }

        return retValue;
    }
}
