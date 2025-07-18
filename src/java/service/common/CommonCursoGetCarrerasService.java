/*
 * @(#)CommonCursoGetCarrerasService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Mencion;
import java.util.*;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.MiCarreraSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.common.CommonUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonCursoGetCarrerasService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(GenericSession genericSession, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        List<MiCarreraSupport> miCarreraSupport;
        CommonUtil.setAgnoSemAct(ws);

        List<Mencion> list = ContextUtil.getDAO().getMencionPersistence(ActionUtil.getDBUser()).find(genericSession.getUserType(), genericSession.getRut());

        Iterator<Mencion> iter = list.iterator();
        Set<MiCarreraSupport> miCarreraSupportSet = new HashSet<>(0);

        while (iter.hasNext()) {
            Mencion mencion = iter.next();
            MiCarreraSupport carrera = new MiCarreraSupport();

            carrera.setEspCod(mencion.getCarrera().getEspecialidad().getEspCod());
            carrera.setRegimen(mencion.getCarrera().getCarRegimen());
            carrera.setNombreCarrera(mencion.getNombreCarreraFull() + " (" + mencion.getCarrera().getCarRegimen() + ')');
            carrera.setTcrCtip(mencion.getCarrera().getTcarrera().getTcrCtip());
            miCarreraSupportSet.add(carrera);
        }

        miCarreraSupport = new ArrayList<>(miCarreraSupportSet);
        
        miCarreraSupport.sort(Comparator.comparing(MiCarreraSupport::getTcrCtip));

        ws.setMiCarreraSupportList(miCarreraSupport);
        
        return SUCCESS;
    }
}
