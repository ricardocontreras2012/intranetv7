/*
 * @(#)EgresadoEstudiosUpdateEstudioService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */


package service.egresado;


import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;


/**
 *
 * @author Alvaro Romero C.
 */
public class EgresadoEstudiosUpdateEstudioService {

    /**
     * Method description
     *
     *
     * @param action
     * @param genericSession
     * @param correl
     * @param areaEstudio
     * @param desdeAgno
     * @param desdeMes
     * @param hastaAgno
     * @param estadoEstudio
     * @param hastaMes
     * @param institucionEducacional
     * @param keyDummy
     * @param nombreEstudio
     * @param otraInstitucion
     * @param tipoEstudio
     * @param pais
     *
     * @return
     */
    public String service(ActionCommonSupport action, GenericSession genericSession, Integer correl,
                                 Integer pais, Integer institucionEducacional, String otraInstitucion,
                                 Integer tipoEstudio, String nombreEstudio, Integer desdeAgno, Integer desdeMes,
                                 Integer hastaAgno, Integer hastaMes, Integer estadoEstudio, Integer areaEstudio,
                                 String keyDummy) {
        String user = ActionUtil.getDBUser();        

        beginTransaction(user);
        ContextUtil.getDAO().getFichaEstudioRepository(user).updateEstudio(
            correl, pais, institucionEducacional, otraInstitucion, tipoEstudio, nombreEstudio, desdeAgno, desdeMes,
            hastaAgno, hastaMes, estadoEstudio, areaEstudio);

        commitTransaction();
        action.addActionMessage(action.getText("message.datos.grabados"));

        return SUCCESS;
    }
}
