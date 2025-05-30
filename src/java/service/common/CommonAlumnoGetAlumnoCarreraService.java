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
    public static String service(GenericSession genericSession, String key) {
        String retValue = "";
        WorkSession ws = genericSession.getWorkSession(key);
        List<AluCar> aluCarList = ContextUtil.getDAO().getAluCarPersistence(ActionUtil.getDBUser()).find(ws.getAlumno().getAluRut());

        if (aluCarList != null) {
            if (aluCarList.size() == 1) {
                AluCar aluCar = aluCarList.iterator().next();

                aluCar.setAlumno(ws.getAlumno());
                aluCar.setInitValues();
                ws.setAluCar(aluCar);
                ws.setNombre(aluCar.getAlumno().getNombre());
                retValue = SUCCESS;
            } else {
                ws.setAluCarList(aluCarList);
                retValue = "multiplesIngresos";
            }
        }

        return retValue;
    }
}
