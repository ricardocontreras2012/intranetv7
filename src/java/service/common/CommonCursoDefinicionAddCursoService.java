/*
 * @(#)CommonCursoDefinicionAddCursoService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Curso;
import domain.model.CursoId;
import org.apache.commons.lang3.ObjectUtils;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.DateUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.common.CommonCursoUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonCursoDefinicionAddCursoService {

    /**
     * Method Servicio
     *
     * @param genericSession
     * @param asign
     * @param elect
     * @param electivo
     * @param coord
     * @param secc
     * @param cupo
     * @param inicio
     * @param termino
     * @param diurno
     * @param vesp
     * @param key
     * @return Action status
     * @throws java.lang.Exception
     */
    public static String service(GenericSession genericSession, Integer asign, String elect, String electivo, String coord, Integer secc, Integer cupo, String inicio, String termino,
            String diurno, String vesp, String key) throws Exception{
        WorkSession ws = genericSession.getWorkSession(key);

        int pos = -1;

        Curso curso = new Curso();
        CursoId id = new CursoId();
        id.setCurAsign(asign);
        id.setCurElect(elect);
        id.setCurCoord(coord);
        id.setCurSecc(secc);
        id.setCurAgno(ws.getAgnoAct());
        id.setCurSem(ws.getSemAct());
        id.setCurComp("T");
        curso.setId(id);

        String jornada = "";
        if (diurno != null) {
            jornada += diurno;
        }

        if (vesp != null) {
            jornada += vesp;
        }

        cupo = ObjectUtils.defaultIfNull(cupo, 0);

        String result = ContextUtil.getDAO().getScalarPersistence(ActionUtil.getDBUser()).addCurso(asign, elect, coord, secc, ws.getAgnoAct(), ws.getSemAct(), electivo, cupo, DateUtil.transform(inicio, "yyyy-MM-dd", "dd/MM/yyyy"), DateUtil.transform(termino, "yyyy-MM-dd", "dd/MM/yyyy"), genericSession.getRut(), jornada);

        CommonCursoUtil.getCursos(genericSession, "*", key); //Cerrados
        if ("OK".equals(result)) {
            int largo = ws.getCursoList().size();
            for (int i = 0; i < largo; i++) {
                CursoId idList = ws.getCursoList().get(i).getId();

                if (Integer.compare(id.getCurAsign(), idList.getCurAsign()) == 0) {
                    String aux1 = id.getCurElect() + id.getCurCoord() + id.getCurSecc();
                    String aux2 = idList.getCurElect() + idList.getCurCoord() + idList.getCurSecc();
                    if (aux1.equals(aux2)) {
                        pos = i;
                        break;
                    }
                }

            }
            ws.setPos(pos);
        }

        LogUtil.setLog(genericSession.getRut(), curso.getCodigo(" "));

        return SUCCESS;
    }
}
