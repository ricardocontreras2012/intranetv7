/*
 * @(#)CommonAlumnoUtil.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.util.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.comparator.CalificacionLogroAdicionalComparable;
import domain.model.comparator.RequisitoAdicionalTitPLanComparable;
import domain.model.AluCar;
import domain.model.Alumno;
import domain.model.CalificacionLogroAdicional;
import domain.model.Curso;
import domain.model.CursoAyudante;
import domain.model.CursoProfesor;
import domain.model.EncuestaAyudante;
import domain.model.EncuestaDocente;
import domain.model.RequisitoLogroAdicional;
import static java.lang.Integer.valueOf;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import org.apache.struts2.ServletActionContext;
import domain.repository.ParametroRepository;
import session.AlumnoSession;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.InscripcionSupport;
import infrastructure.support.ParametroSesionSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.FormatUtil.cleanName;
import infrastructure.util.LogUtil;
import infrastructure.util.SystemParametersUtil;
import static infrastructure.util.common.CommonCursoUtil.evitarLazyCursoAyudante;
import static infrastructure.util.common.CommonCursoUtil.evitarLazyCursoProf;
import domain.model.AluCarFunctionsView;
import domain.model.FlagInscripcionView;
import domain.model.Inscripcion;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;
import domain.repository.AlumnoRepository;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonAlumnoUtil {

    private CommonAlumnoUtil() {
    }

    public static Alumno find(Integer rut) {
        return ContextUtil.getDAO().getAlumnoRepository(ActionUtil.getDBUser()).find(rut);
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
        genericSession.getWorkSession(key).setAlumnoList(getAlumno(rut, paterno, materno, nombre));
    }

    /*
 * Obtiene una lista de alumnos basados en los criterios de búsqueda proporcionados.
 * Si se proporciona un número de rut, se busca el alumno directamente por rut.
 * Si no se proporciona rut, se busca a los alumnos por los parámetros de nombre (paterno, materno y nombre).
 * 
 * @param rut El número de identificación del alumno (rut). Si no se proporciona, se realiza la búsqueda por nombre.
 * @param paterno El apellido paterno del alumno para realizar la búsqueda. Puede ser nulo o vacío.
 * @param materno El apellido materno del alumno para realizar la búsqueda. Puede ser nulo o vacío.
 * @param nombre El nombre del alumno para realizar la búsqueda. Puede ser nulo o vacío.
 * @return Una lista de alumnos que coinciden con los criterios de búsqueda. Si no se encuentran alumnos, se devuelve una lista vacía.
     */
    private static List<Alumno> getAlumno(Integer rut, String paterno, String materno, String nombre) {
        // Obtener la instancia de la persistencia de alumnos
        AlumnoRepository alumnoRepository = ContextUtil.getDAO().getAlumnoRepository(ActionUtil.getDBUser());

        // Lista para almacenar los alumnos encontrados
        List<Alumno> lAlumno = new ArrayList<>();

        // Si el rut no es nulo, buscar el alumno por su rut
        if (rut != null) {
            Alumno alumno = alumnoRepository.findFull(rut);
            if (alumno != null) {
                // Si se encuentra el alumno, agregarlo a la lista
                lAlumno.add(alumno);
            }
        } else {
            // Si el rut es nulo, buscar por los parámetros de nombre (paterno, materno, nombre)
            if (!isEmpty(paterno) || !isEmpty(materno) || !isEmpty(nombre)) {
                // Preparar los parámetros de búsqueda, llamando a getNombreBusqueda para cada uno
                String nombreBusquedaPaterno = cleanName(paterno);
                String nombreBusquedaMaterno = cleanName(materno);
                String nombreBusquedaNombre = cleanName(nombre);

                // Buscar los alumnos que coinciden con los criterios de búsqueda
                lAlumno = alumnoRepository.find(nombreBusquedaPaterno, nombreBusquedaMaterno, nombreBusquedaNombre);
            }
        }

        // Devolver la lista de alumnos encontrados (puede estar vacía si no se encuentran coincidencias)
        return lAlumno;
    }

    public static List<Curso> getCarga(AluCar aluCar, GenericSession gs) {
        Integer agno = aluCar.getParametros().getAgnoAct();
        Integer sem = aluCar.getParametros().getSemAct();

        String next = ContextUtil.getDAO().getScalarRepository(ActionUtil.getDBUser()).getSemestrePrevio(agno, sem, aluCar.getId().getAcaCodCar(), aluCar.getAcaCodMen(), aluCar.getAcaCodPlan());
        Integer agnoPrev = Integer.parseInt(next.substring(0, 4));
        Integer semPrev = Integer.parseInt(next.substring(4));

        List<Inscripcion> insList = ContextUtil.getDAO().getInscripcionRepository(ActionUtil.getDBUser()).getInscripcion(aluCar, agno, sem);
        List<Inscripcion> insPracticaList = ContextUtil.getDAO().getInscripcionRepository(ActionUtil.getDBUser()).getInscripcionPractica(aluCar.getId(), agnoPrev, semPrev);

        if (insPracticaList != null && !insPracticaList.isEmpty()) {
            insList.addAll(insPracticaList);
        }

        return InscripcionSupport.getCursoList(insList);
    }

    public static String getNombreSocial(Alumno alumno) {
        String nombreSocial = alumno.getAluNombreSocial();
        return (nombreSocial != null && !nombreSocial.trim().isEmpty())
                ? nombreSocial
                : alumno.getAluNombre();
    }

    /**
     *
     * @param aluCar
     * @return
     */
    public static AluCarFunctionsView getAluCarFunction(AluCar aluCar) {
        return ContextUtil.getDAO().getAluCarFunctionsViewRepository(ActionUtil.getDBUser()).find(
                aluCar);
    }

    /**
     * Method description
     *
     * @param aluCar
     * @return
     */
    public static ParametroSesionSupport getParametros(AluCar aluCar) {
        //Integer facultad = aluCar.getUnidadFacultad().getUniCod();
        ParametroSesionSupport parametro = new ParametroSesionSupport();

        parametro.setAgnoAct(aluCar.getParametroMencion().getPmenAgnoAct());
        parametro.setSemAct(aluCar.getParametroMencion().getPmenSemAct());
        parametro.setAgnoIns(aluCar.getParametroMencion().getPmenAgnoIns());
        parametro.setSemIns(aluCar.getParametroMencion().getPmenSemIns());
        parametro.setAgnoCal(aluCar.getParametroMencion().getPmenAgnoCal());
        parametro.setSemCal(aluCar.getParametroMencion().getPmenSemCal());
        parametro.setAgnoEnc(aluCar.getParametroMencion().getPmenAgnoEnc());
        parametro.setSemEnc(aluCar.getParametroMencion().getPmenSemEnc());
        parametro.setMaxAsignInscritas(aluCar.getParametroMencion().getPmenInsMaxAsign());
        parametro.setMaxNivelAdelanto(aluCar.getParametroMencion().getPmenInsMaxNivelAdelanto());

        Date terminoFechaCorte = aluCar.getParametroMencion().getPmenInsAdmTermino();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(terminoFechaCorte);
        calendar.add(Calendar.SECOND, 1); // Agregar un segundo
        parametro.setTerminoFechaCorte(calendar.getTime());

        parametro.setPuedeInscribirMalla("NO");
        parametro.setPuedeInscribirFormacionIntegral("NO");
        parametro.setPuedeEliminar("NO");
        parametro.setPuedeModificar("NO");
        parametro.setBloqueada("NO");

        FlagInscripcionView flags = ContextUtil.getDAO().getFlagInscripcionViewRepository(ActionUtil.getDBUser()).find(
                aluCar.getId().getAcaCodCar(),
                aluCar.getAcaCodMen());

        if ("1".equals(flags.getPuedeInscribirMalla())) {
            parametro.setPuedeInscribirMalla("SI");
        }

        if ("1".equals(flags.getPuedeInscribirFormacionIntegral())) {
            parametro.setPuedeInscribirFormacionIntegral("SI");
        }

        if ("1".equals(flags.getPuedeEliminar())) {
            parametro.setPuedeEliminar("SI");
        }

        if ("1".equals(flags.getPuedeModificar())) {
            parametro.setPuedeModificar("SI");
        }

        if ("1".equals(flags.getBloqueada())) {
            parametro.setBloqueada("SI");
        }

        ParametroRepository parametroRepository
                = ContextUtil.getDAO().getParametroRepository(ActionUtil.getDBUser());

        String parAgno = "agno_mat";
        String parSem = "sem_mat";

        if (aluCar.getAluCarFunction().getFlagNuevo() == 1) {
            parAgno = "agno_mat_nvos";
            parSem = "sem_mat_nvos";
        }

        parametro.setAgnoMat(valueOf(parametroRepository.find(parAgno).getParValor()));
        parametro.setSemMat(valueOf(parametroRepository.find(parSem).getParValor()));

        return parametro;
    }

    /**
     *
     * @param genericSession
     * @param alumnoSession
     * @param aluCar
     * @param key
     */
    public static void login(GenericSession genericSession, AlumnoSession alumnoSession, AluCar aluCar, String key) {
        WorkSession ws = genericSession.getWorkSession(key);     
        
        loadAluCar(genericSession, ws, aluCar);
        alumnoSession.setAluCar(aluCar);
        alumnoSession.setBienvenida(true);

        Alumno alumno = alumnoSession.getAlumno();
        aluCar.setAlumno(alumno);
        ws.setNombre(getNombreSocial(alumno));

        ws.setTmaterialSelectOption(ContextUtil.getTipoMaterialMap().get("AL"));
        setTipoCursos(ws);
        ws.setMencionInfoIntranet(ContextUtil.getDAO().getMencionInfoIntranetRepository("AL").find(
                aluCar.getPlan()));

        InscripcionSupport insSup = new InscripcionSupport(aluCar, genericSession);
        insSup.setSctNivel();              
        
        ContextUtil.getDAO().getAluCarRepository(ActionUtil.getDBUser()).generaLogros(aluCar.getId(), aluCar.getAcaCodMen(), aluCar.getAcaCodPlan());      
    }

    public static boolean login(Integer rut, String passwd, String key, Map<String, Object> sesion, Integer flag) {
        
        AlumnoRepository alumnoRepository
                = ContextUtil.getDAO().getAlumnoRepository("AL");
        Alumno alumno = Objects.equals(flag, SystemParametersUtil.INGRESO_REGULAR) ? alumnoRepository.find(rut, passwd) : alumnoRepository.find(rut);              

        if (alumno != null) {
            GenericSession genericSession = new GenericSession("AL", rut, passwd, flag);
            genericSession.setSessionMap(new HashMap<>());
            genericSession.getSessionMap().put(key, new WorkSession("AL"));

            AlumnoSession alumnoSession = new AlumnoSession();
            alumnoSession.setAlumno(alumno);

            alumnoRepository.setLastLogin(rut);

            genericSession.setDv(alumno.getAluDv());
            genericSession.setPaterno(alumno.getAluPaterno());
            genericSession.setMaterno(alumno.getAluMaterno());
            genericSession.setNombres(alumno.getAluNombre());
            genericSession.setNombre(alumno.getNombreSocialStd());
            genericSession.setEmail(alumno.getAluEmailUsach());
            genericSession.setNombreMensaje(alumno.getNombreMensaje());

            ServletActionContext.getRequest().getSession().setMaxInactiveInterval(1800);

            // Usuario validado y asignacion de su contexto de trabajo.
            sesion.put("genericSession", genericSession);
            sesion.put("alumnoSession", alumnoSession);
            LogUtil.setLog(rut);
                                              
            return true;
        }
        return false;
    }

    public static void loadAluCar(GenericSession gs, WorkSession ws, AluCar aluCar) {
        aluCar.setInitValues();
        aluCar.setCarga(gs);
        ws.setAluCar(aluCar);
        ws.setCursoList(aluCar.getCarga());
        ws.setCargaEspejo(aluCar.getCargaEspejo());               
    }

    /**
     * Establece si el alumno cursa pregrado o bien un programa.
     *
     * @param ws
     */
    private static void setTipoCursos(WorkSession ws) {
        if ("S".equals(ws.getAluCar().getPlan().getMencion().getCarrera().getTprograma()
                .getTprFlagCarrera())) {
            ws.setCursoPregrado(true);

        } else {
            ws.setCursoPrograma(true);
        }
    }

    public static void resetWorkSession(GenericSession genericSession, AlumnoSession alumnoSession, String key, String user) {
        WorkSession wsOld = genericSession.getWorkSession(key);
        AluCar aluCar = ContextUtil.getDAO().getAluCarRepository(user).find(wsOld.getAluCar().getId());
        WorkSession wsNew = new WorkSession(user);
        genericSession.setSessionMap(new HashMap<>());
        genericSession.getSessionMap().put(key, wsNew);
        login(genericSession, alumnoSession, aluCar, key);
        wsNew.setAluCar(aluCar);
    }

    public static String searchEncuestaDocente(GenericSession genericSession, String key, String tipo) {  
        WorkSession ws = genericSession.getWorkSession(key);
        String retVal = SUCCESS;
        AluCar aluCar = ws.getAluCar();

        Integer agnoEnc = aluCar.getParametros().getAgnoEnc();
        Integer semEnc = aluCar.getParametros().getSemEnc();
        
        EncuestaDocente encuesta = ContextUtil.getDAO().getEncuestaDocenteRepository(ActionUtil.getDBUser()).find(agnoEnc, semEnc, aluCar.getPlan().getMencion().getId().getMenCodCar(), aluCar.getAcaCodMen(), tipo);
        
        if (encuesta != null) {            
            ws.setEncuestaDocente(encuesta);  
                       
            List<CursoProfesor> cursoProfesorList = ContextUtil.getDAO().getCursoProfesorRepository(ActionUtil.getDBUser()).getCursosEncuesta(aluCar.getId(), agnoEnc, semEnc);           
            
            evitarLazyCursoProf(cursoProfesorList);
            ws.setCursoProfesorList(cursoProfesorList);
        } else {
            retVal = "stack";
        }         
        
        return retVal;        
    }

    public static String searchEncuestaAyudante(GenericSession genericSession, String key, String tipo) {
        WorkSession ws = genericSession.getWorkSession(key);
        String retVal = SUCCESS;
        AluCar aluCar = ws.getAluCar();

        Integer agnoEnc = aluCar.getParametros().getAgnoEnc();
        Integer semEnc = aluCar.getParametros().getSemEnc();

        EncuestaAyudante encuesta = ContextUtil.getDAO().getEncuestaAyudanteRepository(ActionUtil.getDBUser()).find(
                agnoEnc, semEnc, aluCar.getPlan().getMencion().getId().getMenCodCar(),
                aluCar.getAcaCodMen(), tipo);

        if (encuesta != null) {
            ws.setEncuestaAyudante(encuesta);

            List<CursoAyudante> cursoAyudanteList = ContextUtil.getDAO().getCursoAyudanteRepository(ActionUtil.getDBUser()).getCursosEncuesta(
                    aluCar.getId(), agnoEnc, semEnc);

            evitarLazyCursoAyudante(cursoAyudanteList);
            ws.setCursosEncuestaAyudante(cursoAyudanteList);

        } else {
            retVal = "stack";
        }
        return retVal;
    }

    public static boolean inscribioExamenAP(AluCar aluCar) {
        return aluCar.getPlan().getMencion().getCarrera().getTcarrera().getTcrCtip() == 33
                && aluCar.getCarga().stream()
                        .map(curso -> curso.getId().getCurAsign())
                        .anyMatch(asign -> Arrays.asList(371447, 371468).contains(asign));
    }

    public static List<RequisitoLogroAdicional> getRequisitoLogroAdicionalList(AluCar aluCar) {
        // Obtener las listas de persistencia
        List<RequisitoLogroAdicional> requisitoLogroAdicionalList
                = ContextUtil.getDAO().getRequisitoGradoTituloAdicRepository(ActionUtil.getDBUser()).find(aluCar);

        List<CalificacionLogroAdicional> lCalificacion
                = ContextUtil.getDAO().getCalificacionAdicionalLogroRepository(ActionUtil.getDBUser()).find(aluCar);

        // Ordenar las calificaciones
        lCalificacion.sort(new CalificacionLogroAdicionalComparable());

        // Agrupar calificaciones por claReq
        Map<Integer, Set<CalificacionLogroAdicional>> calificacionesMap = lCalificacion.stream()
                .filter(calificacion -> "A".equals(calificacion.getClaSitAlu())) // Filtrar solo "A"
                .collect(Collectors.groupingBy(
                        calificacion -> calificacion.getId().getClaReq(),
                        Collectors.toSet()
                ));

        requisitoLogroAdicionalList.forEach(requisito -> {
            Integer requisitoCod = requisito.getTrequisitoLogroAdicional().getTrlaCod(); // Tipo ajustado
            Set<CalificacionLogroAdicional> calificacionRequisitoAdicionalLogroSet
                    = calificacionesMap.getOrDefault(requisitoCod, Collections.emptySet());
            requisito.getTrequisitoLogroAdicional().setCalificacionLogroAdicionals(calificacionRequisitoAdicionalLogroSet);
        });

        // Ordenar la lista final de requisitos
        requisitoLogroAdicionalList.sort(new RequisitoAdicionalTitPLanComparable());

        return requisitoLogroAdicionalList;
    }
}
