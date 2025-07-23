/*
 * @(#)CommonAlumnoGetAlumnoCarreraService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import java.util.List;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.common.CommonAlumnoUtil;

/**
 *
 * @author Ricardo Contreras S.
 */
public class CommonAlumnoGetAlumnoCarreraService {

    /**
     * Method description
     *
     *
     * @param genericSession
     * @param key
     *
     * @return
     */
    public String service(GenericSession genericSession, String key) {
        String retValue = "";
        WorkSession ws = genericSession.getWorkSession(key);
        List<AluCar> aluCarList = ContextUtil.getDAO().getAluCarRepository(ActionUtil.getDBUser()).find(ws.getAlumno().getAluRut());

        if (aluCarList != null) {
            if (aluCarList.size() == 1) {
                AluCar aluCar = aluCarList.iterator().next();

                aluCar.setAlumno(ws.getAlumno());
                aluCar.setInitValues();
                ws.setAluCar(aluCar);
                ws.setNombre( CommonAlumnoUtil.getNombreSocial(aluCar.getAlumno()));
                retValue = SUCCESS;
            } else {
                ws.setAluCarList(aluCarList);
                retValue = "multiplesIngresos";
            }
        }

        return retValue;
    }
}
