/*
 * @(#)TitulosyGradosNominaAddAlumnoService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.titulosygrados;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.ExpedienteLogro;
import domain.model.ExpedienteLogroId;
import domain.model.PlanLogro;
import java.util.ArrayList;
import java.util.List;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Ricardo Contreras S.
 */
public class TitulosyGradosNominaAddAlumnoService {

    /**
     * Method description
     *
     *
     * @param genericSession
     * @param key
     *
     * @return
     *
     * @throws Exception
     */
    public static String service(GenericSession genericSession,
            String key)
            throws Exception {

        WorkSession ws = genericSession.getWorkSession(key);

        ExpedienteLogro expediente = new ExpedienteLogro();
        ExpedienteLogroId id = new ExpedienteLogroId();

        expediente.setAluCar(ws.getAluCar());
        expediente.setExplRol(ContextUtil.getDAO().getScalarPersistence(ActionUtil.getDBUser()).getRol(ws.getAluCar().getId().getAcaRut()));
        expediente.setNomina(ws.getExpedienteLogro().getNomina());
        expediente.setPlanLogro(ws.getExpedienteLogro().getPlanLogro());
        expediente.setId(id);

        List<PlanLogro> logros
                = ContextUtil.getDAO().getPlanLogroPersistence(ActionUtil.getDBUser()).find(ws.getAluCar().getId(), ws.getAluCar().getPlan().getId(), expediente.getPlanLogro().getLogro().getLogrCod());

        /// OJO Puede que haya mas de una (caso diplomados de carrera de derecho) En ese caso hay que retornar multiples valores
        if (logros != null && !logros.isEmpty()) {
            id.setExplCorrel(logros.get(0).getId().getPlalCorrel());
            id.setExplCodCar(logros.get(0).getId().getPlalCodCar());            
        }
        ///////

        /// OTRO ojo falta ver que pasa si el alumno no tiene ese logro. REtornar msg de error.
        if (ws.getExpedienteLogroList() == null) {
            ws.setExpedienteLogroList(new ArrayList<>());
        }
        ws.getExpedienteLogroList().add(expediente);

        return SUCCESS;       
    }
}
