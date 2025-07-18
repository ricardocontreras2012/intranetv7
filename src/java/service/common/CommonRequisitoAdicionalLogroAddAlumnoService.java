/*
 * @(#)CommonRequisitoAdicionalLogroAddAlumnoService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import domain.model.AluCar;
import domain.model.InscripcionAdicionalLogroId;
import domain.model.AluCarId;
import domain.model.InscripcionAdicionalLogro;
import domain.model.Alumno;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.List;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonRequisitoAdicionalLogroAddAlumnoService {

    /**
     * Method description
     *
     * @param action Clase(action) que invoca al servicio.
     * @param genericSession Sesion de trabajo.
     * @param keyParent
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     * @throws Exception Si el servico genera una exception.
     */
    public String service(ActionCommonSupport action, GenericSession genericSession,
            String keyParent, String key)
            throws Exception {
        String retValue = SUCCESS;
        Alumno alumno = genericSession.getWorkSession(key).getAlumno();
        WorkSession wsParent = genericSession.getWorkSession(keyParent);
        List<AluCar> aluCarList = ContextUtil.getDAO().getAluCarPersistence(ActionUtil.getDBUser()).findActivo(
                        alumno.getAluRut(),
                        wsParent.getMiCarreraSupport().getTcrCtip(),
                        wsParent.getMiCarreraSupport().getEspCod(),
                        wsParent.getMiCarreraSupport().getRegimen());

        if (aluCarList.isEmpty()) {
            action.addActionError(action.getText("error.alumno.no.registrado"));
            retValue = "notFound";
        } else {
            AluCarId aluCarId = aluCarList.get(0).getId();
            AluCar aluCar = ContextUtil.getDAO().getAluCarPersistence(ActionUtil.getDBUser()).find(aluCarId);

            if (ContextUtil.getDAO().getCalificacionAdicionalLogroPersistence(ActionUtil.getDBUser()).puedeInscribirAdicional(
                            aluCar, wsParent.getTrequisitoLogroAdicional().getTrlaCod())) {
                InscripcionAdicionalLogroId inscripcionAdicionalId = new InscripcionAdicionalLogroId();

                inscripcionAdicionalId.setIalAgno(wsParent.getAgnoAct());
                inscripcionAdicionalId.setIalSem(wsParent.getSemAct());
                inscripcionAdicionalId.setIalRut(aluCarId.getAcaRut());
                inscripcionAdicionalId.setIalCodCar(aluCarId.getAcaCodCar());
                inscripcionAdicionalId.setIalAgnoIng(aluCarId.getAcaAgnoIng());
                inscripcionAdicionalId.setIalSemIng(aluCarId.getAcaSemIng());
                inscripcionAdicionalId.setIalReqAdic(wsParent.getTrequisitoLogroAdicional().getTrlaCod());

                InscripcionAdicionalLogro inscripcionRequisitoAdicionalLogro = new InscripcionAdicionalLogro();

                inscripcionRequisitoAdicionalLogro.setId(inscripcionAdicionalId);
                inscripcionRequisitoAdicionalLogro.setAluCar(aluCarList.get(0));
                wsParent.setInscripcionAdicionalLogro(inscripcionRequisitoAdicionalLogro);
            } else {
                action.addActionError(action.getText("error.sin.requisitos.adicionales"));
                retValue = "sinRequisitos";
            }
        }

        return retValue;
    }
}
