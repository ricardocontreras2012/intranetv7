/*
 * @(#)CommonInscripcionGetFotoProfesorService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;


import domain.model.AluCar;
import domain.model.Curso;
import domain.model.Profesor;
import java.io.InputStream;
import java.util.List;
import session.GenericSession;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.common.CommonCursoUtil;
import static infrastructure.util.common.CommonUsersUtil.getFotoProfesor;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonInscripcionGetFotoProfesorService {

     public ActionInputStreamUtil service(GenericSession genericSession, Integer pos, String source, String key) {
        return new ActionInputStreamUtil("1.jpg", "image/jpeg", getInput(genericSession, pos, source, key));
    }

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param pos Identificador del curso.
     * @param source Identifica inscripcion o curso.
     * @param key LLave para acceder a los datos de la sesion.
     * @return
     * @throws java.lang.Exception
     */
    private InputStream getInput(GenericSession genericSession, Integer pos, String source, String key) {
        Curso curso;
        AluCar aluCar = genericSession.getWorkSession(key).getAluCar();

        curso = "cursos".equals(source)
                ? aluCar.getCursosInscripcion().get(pos)
                : aluCar.getInsList().get(pos).getCurso();

        String dv;
        Integer rut;
        
        List<Profesor> lProfesor = CommonCursoUtil.getProfesores(curso);

        if (lProfesor.isEmpty()) {
            rut = 0;
            dv = "NO";
        } else {
            Profesor profesor = lProfesor.iterator().next();
            rut = profesor.getProfRut();
            dv = profesor.getProfDv();
        }

        LogUtil.setLog(genericSession.getRut(), rut);
        return getFotoProfesor(rut, dv);
    }
}
