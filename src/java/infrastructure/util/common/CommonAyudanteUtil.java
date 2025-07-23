/*
 * @(#)CommonAyudanteUtil.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.util.common;

import domain.model.Ayudante;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import static org.apache.struts2.ServletActionContext.getRequest;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.FormatUtil.cleanName;
import infrastructure.util.LogUtil;
import session.ProfesorSession;
import domain.repository.AyudanteRepository;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonAyudanteUtil {

    private CommonAyudanteUtil() {
    }

    public static boolean login(Integer rut, String passwd, String key, Map<String, Object> sesion) {
        AyudanteRepository ayudanteRepository = ContextUtil.getDAO().getAyudanteRepository(ActionUtil.getDBUser());
        Ayudante ayudante = ayudanteRepository.find(rut, passwd);

        if (ayudante != null) {
            ayudante.setCarga();  // Carga los cursos asociados al ayudante

            // Crear sesión genérica para el ayudante
            GenericSession genericSession = new GenericSession(ActionUtil.getDBUser(), rut, passwd, 0);
            WorkSession ws = new WorkSession(ActionUtil.getDBUser());

            // Configurar la sesión con los cursos y ayudante
            ws.setCursoList(ayudante.getCarga());
            ws.setAyudante(ayudante);

            // Configurar el mapa de la sesión y establecer el correo y último login
            genericSession.setSessionMap(new HashMap<>());
            genericSession.getSessionMap().put(key, ws);
            genericSession.setEmail(ayudante.getAyuEmail());
            genericSession.setLastLogin(ayudante.getAyuLastLogin());

            // Actualizar el último login del ayudante en la base de datos
            ayudanteRepository.setLastLogin(rut);

            // Configurar los detalles adicionales del ayudante en la sesión
            getComplemento(genericSession, ayudante, key);

            // Establecer el tiempo de inactividad para la sesión
            getRequest().getSession().setMaxInactiveInterval(1800);

            // Guardar la sesión generada en el mapa de la sesión
            sesion.put("genericSession", genericSession);
            
            ProfesorSession profesorSession = new ProfesorSession();
            sesion.put("profesorSession", profesorSession);

            // Registrar la acción de login
            LogUtil.setLog(rut);
            return true;

        }

        return false;
    }
    
     /**
     * Método que configura los detalles adicionales del ayudante dentro de la sesión,
     * incluyendo el nombre, apellido, tipo de material y otros datos necesarios.
     *
     * @param genericSession Sesión del trabajo que contiene los datos de sesión del usuario.
     * @param ayudante Objeto que representa al ayudante, con su información personal.
     * @param key Llave para acceder a la sesión.
     */
    private static void getComplemento(GenericSession genericSession, Ayudante ayudante, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        // Configurar información personal del ayudante en la sesión
        genericSession.setDv(ayudante.getAyuDv());
        genericSession.setPaterno(ayudante.getAyuPaterno());
        genericSession.setMaterno(ayudante.getAyuMaterno());
        genericSession.setNombres(ayudante.getAyuNombre());
        genericSession.setNombre(ayudante.getNombre());
        genericSession.setNombreMensaje(ayudante.getNombreMensaje());

        // Establecer opciones de tipo de material según el tipo de usuario
        ws.setTmaterialSelectOption(ContextUtil.getTipoMaterialMap().get(genericSession.getUserType()));

        // Configurar los tipos de cursos en la sesión
        setTipoCursos(genericSession, ayudante, key);

        // Establecer el nombre del ayudante en la sesión
        ws.setNombre(ayudante.getNombre());
    }

    /**
     * Método que establece los tipos de cursos (pregrado, programa) asociados al ayudante
     * en función de los cursos que tiene asignados.
     *
     * @param genericSession Sesión del trabajo.
     * @param ayudante Objeto que contiene los cursos del ayudante.
     * @param key Llave para acceder a los datos de la sesión.
     */
    private static void setTipoCursos(GenericSession genericSession, Ayudante ayudante, String key) {
        // Usar streams y lambda para verificar los tipos de curso
        boolean cursoCarrera = ayudante.getCarga().stream()
            .anyMatch(curso -> "C".equals(curso.getCursoActual().getTipo()) || "T".equals(curso.getCursoActual().getTipo()));

        boolean cursoPrograma = ayudante.getCarga().stream()
            .anyMatch(curso -> "P".equals(curso.getCursoActual().getTipo()) || "T".equals(curso.getCursoActual().getTipo()));

        WorkSession ws = genericSession.getWorkSession(key);
        ws.setCursoPregrado(cursoCarrera);
        ws.setCursoPrograma(cursoPrograma);
    }


    /**
     * Method description
     *
     * @param genericSession
     * @param rut
     * @param paterno
     * @param materno
     * @param nombre
     * @param key
     */
    public static void getNomina(GenericSession genericSession, Integer rut, String paterno, String materno,
            String nombre, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        ws.setAyudanteList(getAyudante(rut, paterno, materno, nombre));
    }

    /**
     * Method description
     *
     * @param rut
     * @param paterno
     * @param materno
     * @param nombre
     * @return
     */
    private static List<Ayudante> getAyudante(Integer rut, String paterno, String materno, String nombre) {
        List<Ayudante> lAyudante = null;
        AyudanteRepository ayudanteRepository
                = ContextUtil.getDAO().getAyudanteRepository(ActionUtil.getDBUser());

        if (rut != null) {
            Ayudante ayudante = ayudanteRepository.find(rut);

            lAyudante = new ArrayList<>();

            if (ayudante != null) {
                lAyudante.add(ayudante);
            }
        } else {
            if (!StringUtils.isEmpty(paterno) || !StringUtils.isEmpty(materno) || !StringUtils.isEmpty(nombre)) {
                lAyudante = ayudanteRepository.find(cleanName(paterno),
                        cleanName(materno), cleanName(nombre));
            }
        }

        return lAyudante;
    }

    public static List<Ayudante> getAyudantePersona(Integer rut, String paterno, String materno, String nombre) {
        List<Ayudante> lAyudante = null;
        AyudanteRepository ayudanteRepository
                = ContextUtil.getDAO().getAyudanteRepository(ActionUtil.getDBUser());

        if (rut != null) {
            Ayudante ayudante = ayudanteRepository.find(rut);

            lAyudante = new ArrayList<>();

            if (ayudante != null) {
                lAyudante.add(ayudante);
            } else {
                ayudanteRepository.creaAyudante(rut);
                ayudante = ayudanteRepository.find(rut);
                if (ayudante != null) {
                    lAyudante.add(ayudante);
                }
            }
        } else {
            if (!StringUtils.isEmpty(paterno) || !StringUtils.isEmpty(materno) || !StringUtils.isEmpty(nombre)) {
                lAyudante = ayudanteRepository.find(cleanName(paterno),
                        cleanName(materno), cleanName(nombre));
            }
        }

        return lAyudante;
    }
}
