/*
 * @(#)EnableRecordService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import java.util.List;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.common.CommonAlumnoUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class EnableRecordService {

    /**
     * Method Servicio
     *
     * @param action Clase(action) que invoca al servicio.
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    public String service(ActionCommonSupport action, GenericSession genericSession, String key) {
        String retValue = "";
        WorkSession ws = genericSession.getWorkSession(key);

        if (!"PR".equals(genericSession.getUserType())
                || "S".equals(ContextUtil.getDAO().getProfesorRepository(ActionUtil.getDBUser()).esProfesorDe(
                        genericSession.getRut(), ws.getAlumno().getAluRut()))) {
            List<AluCar> aluCarList = ContextUtil.getDAO().getAluCarRepository(ActionUtil.getDBUser()).find(ws.getAlumno().getAluRut());

            if (aluCarList != null) {
                if (aluCarList.size() == 1) {
                    AluCar aluCar = aluCarList.iterator().next();                    
                    CommonAlumnoUtil.loadAluCar(genericSession, ws, aluCar);

                    aluCar.setAlumno(ws.getAlumno());
                    ws.setNombre(aluCar.getAlumno().getAluNombreSocial());

                    retValue = SUCCESS;
                } else {
                    ws.setAluCarList(aluCarList);
                    retValue = "multiplesIngresos";
                }
            }
        } else {
            action.addActionError(action.getText("error.profesor.consulta.alumno"));
            retValue = "sinPermiso";
        }

        return retValue;
    }
}
