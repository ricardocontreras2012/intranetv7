/*
 * @(#)EgresadoLaboralesSaveLaboralService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.egresado;

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
public class EgresadoLaboralesSaveLaboralService {

    /**
     * Method description
     *
     *
     * @param action
     * @param genericSession
     * @param dependiente
     * @param correlAluEmp
     * @param rutEmpleador
     * @param tipoEmpleador
     * @param indepActividadEconomica
     * @param areaTrabajo
     * @param lugar
     * @param region
     * @param comuna
     * @param otroLugar
     * @param cargo
     * @param tipoTrabajo
     * @param sueldo
     * @param desdeAgno
     * @param desdeMes
     * @param hastaAgno
     * @param hastaMes
     * @param desdeAgnoEmpresa
     * @param desdeMesEmpresa
     * @param hastaAgnoEmpresa
     * @param hastaMesEmpresa
     * @param descripcion
     * @param keyDummy
     *
     * @return
     */
    public static String service(ActionCommonSupport action, GenericSession genericSession, Integer correlAluEmp, String dependiente,
            Integer rutEmpleador, String tipoEmpleador, Integer indepActividadEconomica,
            Integer areaTrabajo, String lugar, Integer region, Integer comuna,
            String otroLugar, String cargo, Integer tipoTrabajo, Integer sueldo,
            Integer desdeAgno, Integer desdeMes, Integer hastaAgno, Integer hastaMes,
            Integer desdeAgnoEmpresa, Integer desdeMesEmpresa, Integer hastaAgnoEmpresa, Integer hastaMesEmpresa,
            String descripcion, String keyDummy) {

        String user = ActionUtil.getDBUser();
        Alumno alumno = ContextUtil.getDAO().getAlumnoPersistence(user).getMisDatos(genericSession.getRut());

        beginTransaction(user);
        if (correlAluEmp == null) {
            correlAluEmp = ContextUtil.getDAO().getScalarPersistence(ActionUtil.getDBUser()).getSecuenciaFichaEgresado();
            ContextUtil.getDAO().getAlumnoEmpleadorPersistence(user).createLaboral(
                    correlAluEmp, alumno.getAluRut(), rutEmpleador, indepActividadEconomica, desdeAgnoEmpresa, desdeMesEmpresa, hastaAgnoEmpresa, hastaMesEmpresa);
        }

        Integer correlFicha = ContextUtil.getDAO().getScalarPersistence(ActionUtil.getDBUser()).getSecuenciaFichaEgresado();
        ContextUtil.getDAO().getFichaLaboralPersistence(user).createLaboral(
                correlFicha, correlAluEmp, areaTrabajo, region, comuna, otroLugar, cargo, tipoTrabajo,
                sueldo, desdeAgno, desdeMes, hastaAgno, hastaMes,
                descripcion);
        commitTransaction();
        action.addActionMessage(action.getText("message.datos.grabados"));

        return SUCCESS;
    }
}
