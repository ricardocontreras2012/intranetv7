/*
 * @(#)CommonProfesorUtil.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.util.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.CursoProfesor;
import domain.model.Profesor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.lang3.StringUtils;
import static org.apache.struts2.ServletActionContext.getRequest;
import domain.repository.ProfesorPersistence;
import session.GenericSession;
import session.ProfesorSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.FormatUtil.cleanName;
import infrastructure.util.LogUtil;
import infrastructure.util.SystemParametersUtil;
import domain.model.MencionInfoIntranetProfesorView;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonProfesorUtil {

    private CommonProfesorUtil() {
    }

    /**
     * Obtiene una lista de profesores basándose en los parámetros
     * proporcionados (rut, apellido paterno, apellido materno y nombre). Si el
     * rut no es nulo, se busca al profesor con ese rut específico. Si el rut es
     * nulo, se realizan búsquedas por los apellidos y nombre.
     *
     * @param rut el número de identificación del profesor (RUT).
     * @param paterno el apellido paterno del profesor.
     * @param materno el apellido materno del profesor.
     * @param nombre el nombre del profesor.
     * @return una lista de profesores que coinciden con los parámetros de
     * búsqueda.
     */
    public static List<Profesor> getProfesor(Integer rut, String paterno, String materno, String nombre) {
        // Se inicializa la lista de profesores como vacía
        List<Profesor> lProfesor = new ArrayList<>();

        // Obtener la persistencia de los profesores a través del DAO
        ProfesorPersistence profesorPersistence = ContextUtil.getDAO().getProfesorPersistence(ActionUtil.getDBUser());

        // Si el rut no es nulo, buscar un único profesor por rut
        if (rut != null) {
            Profesor profesor = profesorPersistence.find(rut);

            if (profesor != null) {
                lProfesor.add(profesor);  // Se agrega el profesor encontrado a la lista
            }
        } else {
            // Si el rut es nulo, buscar por apellidos o nombre
            if (StringUtils.isNotEmpty(paterno) || StringUtils.isNotEmpty(materno) || StringUtils.isNotEmpty(nombre)) {
                // Buscar profesores por los apellidos y nombre
                lProfesor = profesorPersistence.find(
                        cleanName(paterno),
                        cleanName(materno),
                        cleanName(nombre)
                );
            }
        }

        // Retorna la lista de profesores encontrados
        return lProfesor;
    }

    public static List<Profesor> getProfesorPersona(Integer rut, String paterno, String materno, String nombre) {
        List<Profesor> lProfesor = null;
        ProfesorPersistence profesorPersistence
                = ContextUtil.getDAO().getProfesorPersistence(ActionUtil.getDBUser());

        if (rut != null) {
            Profesor profesor = profesorPersistence.find(rut);

            lProfesor = new ArrayList<>();

            if (profesor != null) {
                lProfesor.add(profesor);
            } else {
                profesorPersistence.creaProfesor(rut);
                profesor = profesorPersistence.find(rut);
                if (profesor != null) {
                    lProfesor.add(profesor);
                }
            }
        } else {
            if (!StringUtils.isEmpty(paterno) || !StringUtils.isEmpty(materno) || !StringUtils.isEmpty(nombre)) {
                lProfesor = profesorPersistence.find(cleanName(paterno),
                        cleanName(materno), cleanName(nombre));
            }
        }

        return lProfesor;
    }

    public static String getAutoevaluacion(WorkSession ws, Integer rut) {
        String retValue;

        ws.setCursosAutoEvaluacion(ContextUtil.getDAO().getRespuestaAutoEvaluacionAcademicoPersistence(ActionUtil.getDBUser()).getCursos(rut));

        if (ws.getCursosAutoEvaluacion().size() > 0) {
            //Evita Lazy
            ws.getCursosAutoEvaluacion().stream()
                    .map(CursoProfesor::getCurso)
                    .forEach(curso -> System.out.println(">>>>>>> evita lazy " + curso));

            retValue = SUCCESS;
        } else {
            retValue = "stack";
        }

        return retValue;
    }

    public static boolean login(Integer rut, String passwd, String key, Map<String, Object> sesion, Integer flag) {

        ProfesorPersistence profesorPersistence
                = ContextUtil.getDAO().getProfesorPersistence("PR");
        Profesor profesor = Objects.equals(flag, SystemParametersUtil.INGRESO_REGULAR) ? profesorPersistence.find(rut, passwd) : profesorPersistence.find(rut);

        if (profesor != null) {
            GenericSession genericSession = new GenericSession("PR", rut, passwd, flag);
            genericSession.setSessionMap(new HashMap<>());
            WorkSession ws = new WorkSession("PR");
            genericSession.getSessionMap().put(key, ws);

            ProfesorSession profesorSession = new ProfesorSession();
            genericSession.setProfesorSession(profesorSession);
            genericSession.setProfesor(profesor);

            profesorSession.setProfesor(profesor);
            profesor.setCarga();

            ws.setProfesor(profesor);
            ws.setCursoList(profesor.getCarga());
            genericSession.setSessionMap(new HashMap<>());
            genericSession.getSessionMap().put(key, ws);
            profesorPersistence.setLastLogin(rut);
            getComplemento(genericSession, key);

            getRequest().getSession().setMaxInactiveInterval(1800);

            sesion.put("genericSession", genericSession);
            sesion.put("profesorSession", profesorSession);
            LogUtil.setLog(rut);

            return true;
        }
        return false;
    }

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para aceder a los datos de la sesion.
     */
    public static void getComplemento(GenericSession genericSession, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        Profesor profesor = genericSession.getProfesorSession().getProfesor();

        genericSession.setDv(profesor.getProfDv());
        genericSession.setPaterno(profesor.getProfPat());
        genericSession.setMaterno(profesor.getProfMat());
        genericSession.setNombres(profesor.getProfNom());
        genericSession.setNombre(profesor.getProfNombreSimple());
        genericSession.setEmail(profesor.getProfEmail());
        genericSession.setNombreMensaje(profesor.getNombreMensaje());

        if ("PR".equals(genericSession.getUserType())) {
            getAutoevaluacion(ws, genericSession.getRut());
        }

        ws.setTmaterialSelectOption(ContextUtil.getTipoMaterialMap().get("PR"));
        setTipoCursos(genericSession, key);
        ws.setNombre(profesor.getNombre());
        genericSession.getProfesorSession().setMencionInfoIntranetProfesorViewList(getDistinctInfoIntranet(ContextUtil.getDAO().getMencionInfoIntranetProfesorPersistence(ActionUtil.getDBUser()).find(profesor.getProfRut())));
    }

    /**
     * Método para configurar el tipo de curso en la sesión de trabajo según los
     * cursos del profesor.
     *
     * @param genericSession La sesión genérica que contiene la información de
     * los cursos y la sesión de trabajo.
     * @param key La clave para obtener la sesión de trabajo en
     * `genericSession`.
     */
    private static void setTipoCursos(GenericSession genericSession, String key) {
        // Usar AtomicBoolean para permitir la modificación dentro del stream
        AtomicBoolean cursoCarrera = new AtomicBoolean(false);
        AtomicBoolean cursoPrograma = new AtomicBoolean(false);

        // Iterar a través de los cursos del profesor y determinar el tipo de curso
        genericSession.getProfesorSession().getProfesor().getCarga().stream()
                .filter(curso -> curso.getCursoActual() != null && curso.getCursoActual().getTipo() != null)
                .forEach(curso -> {
                    String tipoCurso = curso.getCursoActual().getTipo();

                    if ("C".equals(tipoCurso) || "T".equals(tipoCurso)) {
                        cursoCarrera.set(true);  // Cambiar el valor de la variable
                    }

                    if ("P".equals(tipoCurso) || "T".equals(tipoCurso)) {
                        cursoPrograma.set(true);  // Cambiar el valor de la variable
                    }
                });

        // Actualizar la sesión de trabajo si se han encontrado los tipos de curso correspondientes
        if (cursoCarrera.get()) {
            genericSession.getWorkSession(key).setCursoPregrado(true);
        }

        if (cursoPrograma.get()) {
            genericSession.getWorkSession(key).setCursoPrograma(true);
        }
    }

    /**
     * Method description
     *
     *
     * @param mencionInfoList
     *
     * @return
     */
    private static List<MencionInfoIntranetProfesorView> getDistinctInfoIntranet(
            List<MencionInfoIntranetProfesorView> mencionInfoList) {
        Set<MencionInfoIntranetProfesorView> mencionInfoSet
                = new LinkedHashSet<>(mencionInfoList);

        return new ArrayList<>(mencionInfoSet);
    }
}
