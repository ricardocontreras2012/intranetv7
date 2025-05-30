/*
 * @(#)CommonRequisitoAdicionalLogroEnableInscripcionService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.RequisitoLogroAdicional;
import domain.model.TrequisitoLogroAdicional;
import java.util.*;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.MiCarreraSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonRequisitoAdicionalLogroEnableInscripcionService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param pos Numero del registro seleccionado en el formulario.
     * @param agno
     * @param sem
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public static String service(GenericSession genericSession, Integer pos, Integer agno, Integer sem, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        ws.setTrequisitoLogroAdicional(null);
        ws.setInscripcionAdicionalLogroList(null);

        MiCarreraSupport miCarreraSupport = ws.getMiCarreraSupportList().get(pos);

        ws.setAgnoAct(agno);
        ws.setSemAct(sem);
        ws.setNombreCarrera(miCarreraSupport.getNombreCarrera());
        ws.setMiCarreraSupport(miCarreraSupport);

        Integer tipoCarrera = miCarreraSupport.getTcrCtip();
        Integer especialidad = miCarreraSupport.getEspCod();
        String regimen = miCarreraSupport.getRegimen();

        ws.setTrequisitoLogroAdicionalList(getListaTrequisitoLogroAdicional(ContextUtil.getDAO().getRequisitoGradoTituloAdicPersistence(ActionUtil.getDBUser()).find(tipoCarrera, especialidad, regimen)));

        return SUCCESS;
    }

    /**
     * Method description
     *
     * @param lTrequisitoLogroAdicionalPlan
     *
     * @return
     */
    private static List<TrequisitoLogroAdicional> getListaTrequisitoLogroAdicional(
            List<RequisitoLogroAdicional> lTrequisitoLogroAdicionalPlan) {
        Set<TrequisitoLogroAdicional> sTrequisitoLogroAdicional = new HashSet<>(0);

        for (RequisitoLogroAdicional aLTrequisitoLogroAdicionalPlan : lTrequisitoLogroAdicionalPlan) {
            sTrequisitoLogroAdicional.add(aLTrequisitoLogroAdicionalPlan.getTrequisitoLogroAdicional());
        }

        List<TrequisitoLogroAdicional> lTrequisitoLogroAdicional
                = new ArrayList<>(sTrequisitoLogroAdicional);

        lTrequisitoLogroAdicional.sort(Comparator.comparing(TrequisitoLogroAdicional::getTrlaDes));

        return lTrequisitoLogroAdicional;
    }
}
