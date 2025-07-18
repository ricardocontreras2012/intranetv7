/*
 * @(#)AlumnoEncuestaAyudanteService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import domain.model.ComentarioEncuestaAyudante;
import domain.model.CursoAyudante;
import domain.model.EncuestaAyudante;
import java.util.List;
import java.util.Map;
import domain.repository.RespuestaEncuestaAyudantePersistence;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.FormatUtil.isNotNull;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.common.CommonAlumnoUtil;
import java.util.Objects;
import java.util.Optional;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AlumnoEncuestaAyudanteService {

    public String search(GenericSession genericSession, String key) {
        return CommonAlumnoUtil.searchEncuestaAyudante(genericSession, key, "I");
    }

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    public String showFormServiceAction(GenericSession genericSession, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        EncuestaAyudante encuesta = ws.getEncuestaAyudante();

        verificarRespuestas(ws.getCursosEncuestaAyudante(), ws.getAluCar(), encuesta);

        return Optional.ofNullable(encuesta)
                .filter(e -> !ws.getCursosEncuestaAyudante().isEmpty())
                .map(e -> {
                    ws.setCursoAyudante(ws.getCursosEncuestaAyudante().get(0));
                    return SUCCESS;
                })
                .orElse("stack");
    }

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param parameters Todos los valores del formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    public String saveServiceAction(GenericSession genericSession, Map<String, String[]> parameters, String key) {
        int correl;
        WorkSession ws = genericSession.getWorkSession(key);
        CursoAyudante cursoAyudante = ws.getCursoAyudante();

        if (cursoAyudante == ws.getCursosEncuestaAyudante().get(0)) {
            beginTransaction(ActionUtil.getDBUser());
            correl = ContextUtil.getDAO().getScalarPersistence(ActionUtil.getDBUser()).getSecuenciaEncuesta();

            parameters.entrySet().stream()
                    .filter(entry -> entry.getKey().startsWith("P_"))
                    .forEach(entry -> {
                        String field = entry.getKey();
                        String[] values = entry.getValue();
                        int pregunta = Integer.parseInt(field.substring(field.lastIndexOf('_') + 1));

                        Optional.ofNullable(values)
                                .filter(val -> val.length > 0)
                                .map(val -> val[0])
                                .map(v -> {
                                    try {
                                        return Integer.parseInt(v); // Intenta convertirlo a Integer
                                    } catch (NumberFormatException e) {
                                        return null; // Descarta valores no numéricos
                                    }
                                })
                                .filter(Objects::nonNull) // Asegura que solo valores numéricos sean procesados
                                .ifPresent(value -> ContextUtil.getDAO()
                                .getRespuestaEncuestaAyudantePersistence(ActionUtil.getDBUser())
                                .doSave(ws.getAluCar(), cursoAyudante, pregunta, value, correl));
                    });

            String[] tmpPositivo = parameters.get("comentarioPositivo");
            String[] tmpMejora = parameters.get("comentarioMejora");

            if (isNotNull(tmpPositivo) || isNotNull(tmpMejora)) {
                EncuestaAyudante encuesta = ws.getEncuestaAyudante();
                ComentarioEncuestaAyudante comenEncta = new ComentarioEncuestaAyudante();

                encuesta.setEnaAgno(cursoAyudante.getId().getCayuAgno());
                encuesta.setEnaCiclo(cursoAyudante.getId().getCayuSem());
                comenEncta.setCursoAyudante(cursoAyudante);
                comenEncta.setEncuestaAyudante(encuesta);
                comenEncta.setCeaCorrel(correl + 100000);

                if (tmpPositivo != null) {
                    comenEncta.setCeaComen1(tmpPositivo[0]);
                }

                if (tmpMejora != null) {
                    comenEncta.setCeaComen2(tmpMejora[0]);
                }

                ContextUtil.getDAO()
                        .getComentarioEncuestaAyudantePersistence(ActionUtil.getDBUser())
                        .save(comenEncta);
            }

            commitTransaction();
            ws.getCursosEncuestaAyudante().remove(0);
        }

        return SUCCESS;
    }

    /**
     * Method description
     *
     * @param cursoList
     * @param aluCar
     * @param encuesta
     */
    private void verificarRespuestas(List<CursoAyudante> cursoList, AluCar aluCar, EncuestaAyudante encuesta) {
        if (encuesta != null) {
            while (!cursoList.isEmpty()) {
                RespuestaEncuestaAyudantePersistence respuestaPersistence
                        = ContextUtil.getDAO().getRespuestaEncuestaAyudantePersistence(ActionUtil.getDBUser());

                // EncuestaDocente ya esta contestada?
                if (respuestaPersistence.find(aluCar, encuesta, cursoList.get(0)).isEmpty()) {
                    cursoList.remove(0);
                } else {
                    break;
                }
            }
        }
    }
}
