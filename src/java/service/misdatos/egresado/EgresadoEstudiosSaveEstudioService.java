/*
 * @(#)EgresadoEstudiosSaveEstudioService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.misdatos.egresado;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Alumno;
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
public class EgresadoEstudiosSaveEstudioService {

    /**
     * Method description
     *
     *
     * @param action
     * @param genericSession
     * @param pais
     * @param institucionEducacional
     * @param otraInstitucion
     * @param tipoEstudio
     * @param nombreEstudio
     * @param desdeAgno
     * @param desdeMes
     * @param hastaAgno
     * @param hastaMes
     * @param estadoEstudio
     * @param areaEstudio
     * @param keyDummy
     *
     * @return
     */
    public String service(ActionCommonSupport action, GenericSession genericSession, Integer pais,
            Integer institucionEducacional, String otraInstitucion, Integer tipoEstudio,
            String nombreEstudio, Integer desdeAgno, Integer desdeMes, Integer hastaAgno,
            Integer hastaMes, Integer estadoEstudio, Integer areaEstudio, String keyDummy) {

        /*
         * FichaEstudio ficha = new FichaEstudio();
         * ficha.setFestCorrel(correl);
         * ficha.setPais(null);
         * ficha.setInstitucionEducacional(null);
         * ficha.setFestOtraInstitucion(otraInstitucion);
         * ficha.setTestudio(null);
         * ficha.setFestNombreEstudio(nombreEstudio);
         * ficha.setFestDesdeAgno(desdeAgno);
         * ficha.setFestDesdeMes(desdeMes);
         * ficha.setFestHastaAgno(hastaAgno);
         * ficha.setFestHastaMes(hastaMes);
         * ficha.setEstadoEstudio(null);
         * ficha.setAreaEstudio(null);
         */
        String user = ActionUtil.getDBUser();
        Alumno alumno = ContextUtil.getDAO().getAlumnoRepository(user).getMisDatos(genericSession.getRut());
        Integer correl = ContextUtil.getDAO().getScalarRepository(user).getSecuenciaFichaEgresado();

        beginTransaction(user);
        ContextUtil.getDAO().getFichaEstudioRepository(user).createEstudio(
                correl, alumno.getAluRut(), pais, institucionEducacional, otraInstitucion, tipoEstudio, nombreEstudio,
                desdeAgno, desdeMes, hastaAgno, hastaMes, estadoEstudio, areaEstudio);
        commitTransaction();
        action.addActionMessage(action.getText("message.datos.grabados"));

        return SUCCESS;
    }
}
