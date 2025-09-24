/*
 * @(#)AlumnoGetCursosService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.inscripcion.alumno;

import action.inscripcion.alumno.AlumnoGetCursosAction;
import com.google.gson.Gson;
import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCarId;
import domain.model.Asignatura;
import domain.model.Curso;
import domain.model.CursoId;
import infrastructure.dto.CursoInsJsonDTO;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.wrapper.CursoResponseWrapper;
import java.util.List;
import java.util.stream.Collectors;
import session.AlumnoSession;
import session.GenericSession;
import session.WorkSession;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AlumnoGetCursosService {

    /**
     * Method Servicio
     *
     * @param action
     * @param genericSession Sesion de trabajo.
     * @param alumnoSession
     * @param asign Código de asignatura.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    public List<Curso> service(AlumnoGetCursosAction action, GenericSession genericSession, AlumnoSession alumnoSession, Integer asign, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        AluCarId idAca = ws.getAluCar().getId();
        
        String json = ContextUtil.getDAO().getCursoRepository(ActionUtil.getDBUser()).findJson(
                idAca.getAcaRut(),
                idAca.getAcaCodCar(),
                idAca.getAcaAgnoIng(),
                idAca.getAcaSemIng(),
                asign
        );
        
        CursoResponseWrapper response = new Gson().fromJson(json, CursoResponseWrapper.class);
        String status = response.getStatus();

        if ("OK".equalsIgnoreCase(status)) {
            List<CursoInsJsonDTO> cursoJsonList = response.getData();

            List<Curso> cursoList = cursoJsonList.stream().map(dto -> {
                Curso curso = new Curso();

                CursoId id = new CursoId();
                id.setCurAsign(dto.ASIGN);
                id.setCurElect(dto.ELECT);
                id.setCurCoord(dto.COORD);
                id.setCurSecc(dto.SECC);
                id.setCurAgno(dto.AGNO);
                id.setCurSem(dto.SEM);
                id.setCurComp(dto.COMP);
                curso.setId(id);

                curso.setCurNombre(dto.NOMBRE);
                curso.setCurProfesores(dto.NOMBRE_PROFESORES);
                curso.setCurHorario(dto.HORARIO);

                Asignatura asignatura = new Asignatura();
                asignatura.setAsiCod(dto.ASIGN);
                curso.setAsignatura(asignatura);

                return curso;
            }).collect(Collectors.toList());

            action.setRetValue(SUCCESS);
            return cursoList;

        } else if ("CAMBIO MENCION".equalsIgnoreCase(status)) {
            alumnoSession.setCambioMencion(response.getMencion());
            
            action.setRetValue("cambioMencion");
            return null;

        } else {
            throw new RuntimeException("Error en JSON: status = " + status);
        }
    }
}
