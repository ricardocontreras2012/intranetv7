/*
 * @(#)EnableInscripcionService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.inscripcion;

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
public class EnableInscripcionService {

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
    public String service(GenericSession genericSession, String key) throws Exception {
        String retValue = "";
        WorkSession ws = genericSession.getWorkSession(key);
        List<AluCar> aluCarList = ContextUtil.getDAO().getAluCarRepository(ActionUtil.getDBUser()).find(ws.getAlumno().getAluRut());

        if (aluCarList != null) {
            if (aluCarList.size() == 1) {
                AluCar aluCar = aluCarList.iterator().next();

                aluCar.setAlumno(ws.getAlumno());
                aluCar.setInitValues();
                ws.setAluCar(aluCar);
                ws.setNombre(ws.getAluCar().getAlumno().getNombreSocial());

                ws.setAgnoAct(aluCar.getParametros().getAgnoIns());
                ws.setSemAct(aluCar.getParametros().getSemIns());

                retValue = SUCCESS;
            } else {
                ws.setAluCarList(aluCarList);
                retValue = "multiplesIngresos";
            }
        }

        return retValue;
    }
}
