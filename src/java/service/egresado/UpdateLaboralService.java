/*
 * @(#)UpdateLaboralService.java
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
public class UpdateLaboralService {

    /**
     * Method description
     *
     *
     * @param action
     * @param genericSession
     * @param correlAluEmp
     * @param correlFicha
     * @param dependiente
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
    public String service(ActionCommonSupport action, GenericSession genericSession, Integer correlAluEmp, Integer correlFicha,
                                 String dependiente, Integer rutEmpleador, String tipoEmpleador,
                                 Integer indepActividadEconomica, Integer areaTrabajo, String lugar, Integer region,
                                 Integer comuna, String otroLugar, String cargo, Integer tipoTrabajo, Integer sueldo,
                                 Integer desdeAgno, Integer desdeMes, Integer hastaAgno, Integer hastaMes,
                                 Integer desdeAgnoEmpresa, Integer desdeMesEmpresa, Integer hastaAgnoEmpresa, Integer hastaMesEmpresa,
                                 String descripcion, String keyDummy) {
        Alumno alumno = ContextUtil.getDAO().getAlumnoRepository(ActionUtil.getDBUser()).getMisDatos(genericSession.getRut());

        beginTransaction(ActionUtil.getDBUser());
        ContextUtil.getDAO().getAlumnoEmpleadorRepository(ActionUtil.getDBUser()).updateLaboral(
            correlAluEmp, alumno.getAluRut(), rutEmpleador, indepActividadEconomica, desdeAgnoEmpresa, desdeMesEmpresa, hastaAgnoEmpresa, hastaMesEmpresa);
        ContextUtil.getDAO().getFichaLaboralRepository(ActionUtil.getDBUser()).updateLaboral(
            correlFicha, correlAluEmp, areaTrabajo, region, comuna, otroLugar, cargo, tipoTrabajo, sueldo,
                desdeAgno, desdeMes, hastaAgno, hastaMes,
                descripcion);
        commitTransaction();
        action.addActionMessage(action.getText("message.datos.grabados"));

        return SUCCESS;
    }
}
