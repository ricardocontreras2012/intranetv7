/*
 * @(#)SaveNewAsistenciaService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.asistencia;

import domain.model.AsistenciaAlumnoNomina;
import domain.model.AsistenciaAlumnoNominaId;
import domain.model.AluCar;
import domain.model.AsistenciaAlumno;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.Map;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.DateUtil.stringToDate;
import static infrastructure.util.FormatUtil.isNotNull;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.LogUtil;
import java.util.List;
import java.util.stream.IntStream;
import domain.repository.AsistenciaAlumnoNominaRepository;
import domain.repository.AsistenciaAlumnoRepository;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class SaveNewAsistenciaService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param map
     * @param key LLave para acceder a los datos de la sesion.
     * @param fecha
     * @return Action status.
     * @throws Exception Si el servico genera una exception.
     */
    public String service(GenericSession genericSession, Map<String, String[]> map, String key, String fecha) throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);
        String dbUser = ActionUtil.getDBUser();

        AsistenciaAlumnoNominaRepository asistenciaAlumnoNominaRepo
                = ContextUtil.getDAO().getAsistenciaAlumnoNominaRepository(dbUser);
        AsistenciaAlumnoRepository asistenciaAlumnoRepo
                = ContextUtil.getDAO().getAsistenciaAlumnoRepository(dbUser);

        beginTransaction(dbUser);

        AsistenciaAlumno asistencia = new AsistenciaAlumno();
        asistencia.setCurso(ws.getCurso());
        asistencia.setAsaFecha(stringToDate(fecha));
        asistencia.setAsaCorrel(ContextUtil.getDAO().getScalarRepository(dbUser).getSecuenciaAsistencia());

        asistenciaAlumnoRepo.makePersistent(asistencia);

        List<AluCar> nominaCurso = ws.getNominaCurso();

        IntStream.range(0, nominaCurso.size()).forEach(i -> {
            AluCar aluCar = nominaCurso.get(i);
            AsistenciaAlumnoNomina nomina = new AsistenciaAlumnoNomina();
            AsistenciaAlumnoNominaId nominaId = new AsistenciaAlumnoNominaId();

            nominaId.setAanRut(aluCar.getId().getAcaRut());
            nominaId.setAanCodCar(aluCar.getId().getAcaCodCar());
            nominaId.setAanAgnoIng(aluCar.getId().getAcaAgnoIng());
            nominaId.setAanSemIng(aluCar.getId().getAcaSemIng());
            nominaId.setAanCorrel(asistencia.getAsaCorrel());
            nomina.setId(nominaId);

            nomina.setAanAsistio(isNotNull(map.get("ck_" + i)) ? 1 : 0);
            asistenciaAlumnoNominaRepo.makePersistent(nomina);
        });

        commitTransaction();
        ws.setAsistenciaAlumnoList(asistenciaAlumnoRepo.find(ws.getCurso()));
        LogUtil.setLogCurso(genericSession.getRut(), ws.getCurso());

        return SUCCESS;
    }
}