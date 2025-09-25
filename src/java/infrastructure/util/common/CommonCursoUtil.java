package infrastructure.util.common;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import domain.model.Asignatura;
import domain.model.Profesor;
import domain.model.Curso;
import domain.model.Horario;
import domain.model.CursoAyudante;
import domain.model.CursoProfesor;
import domain.model.CursoId;
import domain.model.CursoEspejo;
import domain.model.CursoEspejoId;
import domain.model.CursoProfesorId;
import domain.model.comparator.CursoComparable;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.MiCarreraSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

import java.util.*;
import java.util.stream.Collectors;
import domain.repository.CursoRepository;
import domain.repository.CursoProfesorRepository;
import infrastructure.dto.CursoEspejoJsonDTO;
import infrastructure.dto.CursoInsJsonDTO;
import infrastructure.dto.CursoJsonDTO;
import infrastructure.dto.CursoProfesorJsonDTO;
import infrastructure.wrapper.CursoResponseWrapper;
import java.lang.reflect.Type;
import java.sql.Date;

/**
 * Clase que contiene métodos utilitarios relacionados con el manejo de cursos.
 */
public final class CommonCursoUtil {

    private CommonCursoUtil() {
    }

    /**
     * Obtiene una lista de cursos distinta y ordenada de manera ascendente.
     *
     * @param cursoList Lista de cursos a procesar.
     * @return Lista de cursos distinta y ordenada.
     */
    public static List<Curso> getDistinctAsc(List<Curso> cursoList) {
        return cursoList.stream()
                .distinct()
                .sorted(Comparator.comparing(Curso::getId, new CursoComparable()))
                .collect(Collectors.toList());
    }

    /**
     * Evita la carga perezosa (Lazy Loading) de los datos de los profesores de
     * los cursos.
     *
     * @param cursoList Lista de cursos con profesores.
     */
    public static void evitarLazyCursoProf(List<CursoProfesor> cursoList) {
        cursoList.forEach(cursoProfesor -> cursoProfesor.getProfesor().getNombre());
    }

    /**
     * Evita la carga perezosa (Lazy Loading) de los datos de los ayudantes de
     * los cursos.
     *
     * @param cursoList Lista de cursos con ayudantes.
     */
    public static void evitarLazyCursoAyudante(List<CursoAyudante> cursoList) {
        cursoList.forEach(cursoAyudante -> cursoAyudante.getAyudante().getNombre());
    }

    /**
     * Obtiene el curso padre (espejo) de un curso, dependiendo de su tipo.
     *
     * @param curso Curso a analizar.
     * @param espejos Lista de cursos espejo.
     * @return ID del curso padre.
     */
    public static CursoId getParent(Curso curso, List<CursoEspejo> espejos) {
        return "E".equals(curso.getCurTipo()) ? getEspejo(curso, espejos) : curso.getId();
    }

    /**
     * Obtiene una lista de cursos espejo a partir de una lista de cursos.
     *
     * @param lista Lista de cursos a procesar.
     * @return Lista de cursos espejo.
     */
    public static List<CursoEspejo> getEspejos(List<Curso> lista) {
        return lista.stream()
                .filter(curso -> "E".equals(curso.getCurTipo()))
                .map(curso -> {
                    
                    CursoEspejoId id = new CursoEspejoId();
                    id.setCesAsign(curso.getId().getCurAsign());
                    id.setCesElect(curso.getId().getCurElect());
                    id.setCesCoord(curso.getId().getCurCoord());
                    id.setCesSecc(curso.getId().getCurSecc());
                    id.setCesAgno(curso.getId().getCurAgno());
                    id.setCesSem(curso.getId().getCurSem());

                    CursoEspejo espejo = new CursoEspejo();
                    espejo.setId(id);

                    espejo.setCesAsignTr(curso.getEspejo().getCesAsignTr());
                    espejo.setCesElectTr(curso.getEspejo().getCesElectTr());
                    espejo.setCesCoordTr(curso.getEspejo().getCesCoordTr());
                    espejo.setCesSeccTr(curso.getEspejo().getCesSeccTr());
                    espejo.setCesAgnoTr(curso.getEspejo().getCesAgnoTr());
                    espejo.setCesSemTr(curso.getEspejo().getCesSemTr());
                    espejo.setCesCompTr(curso.getEspejo().getCesCompTr());

                    return espejo;
                })
                .collect(Collectors.toList());
    }

    /**
     * Obtiene el curso espejo correspondiente a un curso de la lista de
     * espejos.
     *
     * @param curso Curso a analizar.
     * @param lista Lista de cursos espejo.
     * @return ID del curso espejo.
     */
    public static CursoId getEspejo(Curso curso, List<CursoEspejo> lista) {

        if (lista == null) {
            return getIdTransversal(curso.getId());
        }

        return lista.stream()
                .filter(c -> curso.getId().getCurAsign().intValue() == c.getId().getCesAsign().intValue()
                && curso.getId().getCurElect().equals(c.getId().getCesElect())
                && curso.getId().getCurCoord().equals(c.getId().getCesCoord())
                && curso.getId().getCurSecc().intValue() == c.getId().getCesSecc().intValue()
                && curso.getId().getCurAgno().intValue() == c.getId().getCesAgno().intValue()
                && curso.getId().getCurSem().intValue() == c.getId().getCesSem().intValue())
                .findFirst()
                .map(c -> {
                    CursoId id = new CursoId();
                    id.setCurAsign(c.getCesAsignTr());
                    id.setCurElect(c.getCesElectTr());
                    id.setCurCoord(c.getCesCoordTr());
                    id.setCurSecc(c.getCesSeccTr());
                    id.setCurAgno(c.getCesAgnoTr());
                    id.setCurSem(c.getCesSemTr());
                    id.setCurComp(c.getCesCompTr());
                    return id;
                })
                .orElseGet(() -> getIdTransversal(curso.getId()));
    }

    /**
     * Obtiene el ID transversal de un curso a partir de su ID original.
     *
     * @param id ID del curso.
     * @return ID transversal.
     */
    public static CursoId getIdTransversal(CursoId id) {
        return Optional.ofNullable(ContextUtil.getDAO().getCursoEspejoRepository(ActionUtil.getDBUser()).getEspejo(id))
                .map(cursoEspejo -> {
                    CursoId idRet = new CursoId();
                    idRet.setCurAsign(cursoEspejo.getCesAsignTr());
                    idRet.setCurElect(cursoEspejo.getCesElectTr());
                    idRet.setCurCoord(cursoEspejo.getCesCoordTr());
                    idRet.setCurSecc(cursoEspejo.getCesSeccTr());
                    idRet.setCurAgno(cursoEspejo.getCesAgnoTr());
                    idRet.setCurSem(cursoEspejo.getCesSemTr());
                    idRet.setCurComp(cursoEspejo.getCesCompTr());
                    return idRet;
                })
                .orElse(id);
    }

    /**
     * Compara si dos cursos son iguales basándose en su ID.
     *
     * @param c1 Primer curso.
     * @param c2 Segundo curso.
     * @param espejos Lista de cursos espejo.
     * @return true si los cursos son iguales, false de lo contrario.
     */
    public static boolean iguales(Curso c1, Curso c2, List<CursoEspejo> espejos) {

System.out.println("espejos="+espejos);
System.out.println("largo="+espejos.size());
        
        return equals(getParent(c1, espejos), getParent(c2, espejos));
    }

    /**
     * Compara dos IDs de cursos para verificar si son iguales.
     *
     * @param id1 Primer ID de curso.
     * @param id2 Segundo ID de curso.
     * @return true si los IDs son iguales, false de lo contrario.
     */
    public static boolean equals(CursoId id1, CursoId id2) {
        return id1.getCurAsign().equals(id2.getCurAsign())
                && id1.getCurElect().equals(id2.getCurElect())
                && id1.getCurCoord().equals(id2.getCurCoord())
                && id1.getCurSecc().equals(id2.getCurSecc())
                && id1.getCurAgno().equals(id2.getCurAgno())
                && id1.getCurSem().equals(id2.getCurSem());
    }

    /**
     * Obtiene el código corto de un curso a partir de su identificador.
     *
     * @param cursoStr Identificador del curso en formato de cadena.
     * @return Código corto del curso.
     */
    public static String getCodigo(String cursoStr) {
        Curso curso = new Curso(cursoStr);
        return curso.getId().getCodigoCorto(" ");
    }

    /**
     * Obtiene los cursos de un usuario según su perfil, periodo y tipo de
     * carrera.
     *
     * @param genericSession Sesión genérica.
     * @param tipo Tipo de curso.
     * @param key Clave de la sesión de trabajo.
     */
    public static void getCursos(GenericSession genericSession, String tipo, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        int agno = ws.getAgnoAct();
        int sem = ws.getSemAct();

        String userType = genericSession.getUserType();
        String user = ActionUtil.getDBUser();
        Integer rut = genericSession.getRut();
        CursoRepository cursoRepository = ContextUtil.getDAO().getCursoRepository(user);

        MiCarreraSupport miCarreraSupport = ws.getMiCarreraSupport();
        Integer tipoCarrera = miCarreraSupport.getTcrCtip();
        Integer especialidad = miCarreraSupport.getEspCod();
        String regimen = miCarreraSupport.getRegimen();

        switch (tipo) {
            case "*":
                ws.setCursoList(getListFromJson(cursoRepository.findJson(tipoCarrera, especialidad, regimen, agno, sem, rut, userType, "CTE")));
                break;
            case "T": // Transversales propios               
                ws.setCursoTransversalList(getListFromJson(cursoRepository.findJson(tipoCarrera, especialidad, regimen, agno, sem, rut, userType, "CT")));
                break;
            case "T*": // Todos los transversales que se ofertan               
                ws.setCursoTransversalFullList(getListFromJson(cursoRepository.transversalesJson(agno, sem)));
                break;
            case "E":
                ws.setCursoEspejoList(getListEspejoFromJson((ContextUtil.getDAO().getCursoEspejoRepository(user)
                        .espejosJson(tipoCarrera, especialidad, regimen, agno, sem, rut, userType))));
                break;
        }
    }

    /**
     * Obtiene los cursos del profesor en base a su perfil, periodo y tipo de
     * carrera.
     *
     * @param genericSession Sesión genérica.
     * @param tipo Tipo de curso.
     * @param key Clave de la sesión de trabajo.
     */
    public static void getCursosProf(GenericSession genericSession, String tipo, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        int agno = ws.getAgnoAct();
        int sem = ws.getSemAct();
        MiCarreraSupport miCarreraSupport = ws.getMiCarreraSupport();
        Integer tipoCarrera = miCarreraSupport.getTcrCtip();
        Integer especialidad = miCarreraSupport.getEspCod();
        String regimen = miCarreraSupport.getRegimen();
        String userType = genericSession.getUserType();
        String user = ActionUtil.getDBUser();
        Integer rut = genericSession.getRut();
        CursoProfesorRepository cursoProfesorRepository = ContextUtil.getDAO().getCursoProfesorRepository(user);

        switch (tipo) {
            case "*":
                ws.setCursoProfesorList(getListCursoProfFromJson(cursoProfesorRepository.findJson(tipoCarrera, especialidad, regimen, agno, sem, rut, userType, "CTE")));
                break;
        }
    }

    /**
     * Obtiene el nombre de archivo para un curso, reemplazando los espacios por
     * guiones bajos.
     *
     * @param curso Curso a procesar.
     * @return Nombre de archivo del curso.
     */
    public static String getNombreFile(Curso curso) {
        return curso.getNombreFull().replace(" ", "_");
    }

    /**
     * Obtiene la lista de profesores asociados a un curso.
     *
     * @param curso Curso al cual obtener los profesores.
     * @return Lista de profesores.
     */
    public static List<Profesor> getProfesores(Curso curso) {
        return ContextUtil.getDAO().getProfesorRepository(ActionUtil.getDBUser()).getProfesores(curso.getId());
    }

    /**
     * Obtiene el horario de un curso.
     *
     * @param curso Curso al cual obtener el horario.
     * @return Lista de horarios.
     */
    public static List<Horario> getHorario(Curso curso) {
        return ContextUtil.getDAO().getHorarioRepository(ActionUtil.getDBUser()).getHorario(curso.getId());
    }

    /**
     * Obtiene la carga histórica de los cursos de un ayudante.
     *
     * @param rut RUT del ayudante.
     * @return Lista de cursos históricos.
     */
    public static List<Curso> getCargaHistoricaAyudante(Integer rut) {
        return ContextUtil.getDAO().getAyudanteRepository(ActionUtil.getDBUser()).getCursos(rut);
    }

    /**
     * Obtiene el ID de un curso a partir de su cadena de texto.
     *
     * @param paramCurso Cadena que representa el curso.
     * @return ID del curso.
     */
    public static CursoId getId(String paramCurso) {
        String[] parts = paramCurso.split("_");
        CursoId id = new CursoId();
        id.setCurAsign(Integer.valueOf(parts[0]));
        id.setCurElect(parts[1]);
        id.setCurCoord(parts[2]);
        id.setCurSecc(Integer.valueOf(parts[3]));
        id.setCurAgno(Integer.valueOf(parts[4]));
        id.setCurSem(Integer.valueOf(parts[5]));
        id.setCurComp("T");

        return id;
    }

    public static List<Curso> getListFromJson(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<CursoJsonDTO>>() {
        }.getType();
        List<CursoJsonDTO> cursoJsonList = gson.fromJson(json, listType);
        List<Curso> cursoList;

        cursoList = cursoJsonList.stream().map(dto -> {
            Curso curso = new Curso();

            // ID compuesto
            CursoId id = new CursoId();
            id.setCurAsign(dto.ASIGN);
            id.setCurElect(dto.ELECT);
            id.setCurCoord(dto.COORD);
            id.setCurSecc(dto.SECC);
            id.setCurAgno(dto.AGNO);
            id.setCurSem(dto.SEM);
            id.setCurComp(dto.COMP);
            curso.setId(id);

            // Datos base
            curso.setCurCupoIni(dto.CUPO_INI);
            curso.setCurCupoDis(dto.CUPO_DIS);
            curso.setCurJorDiurno(dto.JOR_DIURNO);
            curso.setCurJorVesp(dto.JOR_VESP);
            curso.setCurTipo(dto.TIPO);
            curso.setCurEnableProf(dto.ENABLE_PROFESOR);
            curso.setCurEnableAyu(dto.ENABLE_AYUDANTE);
            curso.setCurEnableLab(dto.ENABLE_LABORATORIO);

            // Virtuales
            curso.setCurNombre(dto.NOMBRE);
            curso.setCurProfesores(dto.NOMBRE_PROFESORES);
            curso.setCurAyudantes(dto.NOMBRE_AYUDANTES);
            curso.setCurHorario(dto.HORARIO);
            curso.setCurSalas(dto.SALAS);

            // Asignatura
            Asignatura asignatura = new Asignatura();
            asignatura.setAsiCod(dto.ASIGN);
            asignatura.setAsiHcredTeo(dto.HCRED_TEO);
            asignatura.setAsiHcredEje(dto.HCRED_EJE);
            asignatura.setAsiHcredLab(dto.HCRED_LAB);
            asignatura.setAsiTipoControlTel(dto.TIPO_CONTROL_TEL);
            curso.setAsignatura(asignatura);

            // Fechas (solo si no están vacías)
            if (dto.FECHA_INICIO != null && !dto.FECHA_INICIO.isEmpty()) {
                curso.setCurFechaInicio(Date.valueOf(dto.FECHA_INICIO));
            }

            if (dto.FECHA_TERMINO != null && !dto.FECHA_TERMINO.isEmpty()) {
                curso.setCurFechaTermino(Date.valueOf(dto.FECHA_TERMINO));
            }

            return curso;
        }).collect(Collectors.toList());

        return cursoList;
    }

    /*public static List<Curso> getListInsFromJson(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<CursoInsJsonDTO>>() {
        }.getType();
        List<CursoInsJsonDTO> cursoJsonList = gson.fromJson(json, listType);
        List<Curso> cursoList;       
        
        cursoList = cursoJsonList.stream().map(dto -> {
            Curso curso = new Curso();

            // ID compuesto
            CursoId id = new CursoId();
            id.setCurAsign(dto.ASIGN);
            id.setCurElect(dto.ELECT);
            id.setCurCoord(dto.COORD);
            id.setCurSecc(dto.SECC);
            id.setCurAgno(dto.AGNO);
            id.setCurSem(dto.SEM);
            id.setCurComp(dto.COMP);
            curso.setId(id);

            // Virtuales
            curso.setCurNombre(dto.NOMBRE);
            curso.setCurProfesores(dto.NOMBRE_PROFESORES);
            curso.setCurHorario(dto.HORARIO);

            // Asignatura
            Asignatura asignatura = new Asignatura();
            asignatura.setAsiCod(dto.ASIGN);
            
            curso.setAsignatura(asignatura);

            

            return curso;
        }).collect(Collectors.toList());

        return cursoList;
    }*/
    public static List<Curso> getListInsFromJson(String json) {
        Gson gson = new Gson();

        // Deserializar el objeto raíz
        CursoResponseWrapper response = gson.fromJson(json, CursoResponseWrapper.class);

        // Validar status
        if (!"OK".equalsIgnoreCase(response.getStatus())
                && !"CAMBIO MENCION".equalsIgnoreCase(response.getStatus())) {
            throw new RuntimeException("Error en JSON: status = " + response.getStatus());
        }

        // Convertir data a lista de Curso
        List<CursoInsJsonDTO> cursoJsonList = response.getData();
        List<Curso> cursoList;

        cursoList = cursoJsonList.stream().map(dto -> {
            Curso curso = new Curso();

            // ID compuesto
            CursoId id = new CursoId();
            id.setCurAsign(dto.ASIGN);
            id.setCurElect(dto.ELECT);
            id.setCurCoord(dto.COORD);
            id.setCurSecc(dto.SECC);
            id.setCurAgno(dto.AGNO);
            id.setCurSem(dto.SEM);
            id.setCurComp(dto.COMP);
            curso.setId(id);

            // Virtuales
            curso.setCurNombre(dto.NOMBRE);
            curso.setCurProfesores(dto.NOMBRE_PROFESORES);
            curso.setCurHorario(dto.HORARIO);

            // Asignatura
            Asignatura asignatura = new Asignatura();
            asignatura.setAsiCod(dto.ASIGN);
            curso.setAsignatura(asignatura);

            return curso;
        }).collect(Collectors.toList());

        return cursoList;
    }

    public static List<CursoEspejo> getListEspejoFromJson(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<CursoEspejoJsonDTO>>() {
        }.getType();
        List<CursoEspejoJsonDTO> cursoJsonList = gson.fromJson(json, listType);
        List<CursoEspejo> cursoList;

        cursoList = cursoJsonList.stream().map(dto -> {
            CursoEspejo cursoEspejo = new CursoEspejo();
            Curso espejo = new Curso();
            Curso transversal = new Curso();

            CursoEspejoId idCursoEspejo = new CursoEspejoId();
            CursoId idEspejo = new CursoId();
            CursoId idTransversal = new CursoId();

            idCursoEspejo.setCesAsign(dto.ASIGN);
            idCursoEspejo.setCesElect(dto.ELECT);
            idCursoEspejo.setCesCoord(dto.COORD);
            idCursoEspejo.setCesSecc(dto.SECC);
            idCursoEspejo.setCesAgno(dto.AGNO);
            idCursoEspejo.setCesSem(dto.SEM);
            idCursoEspejo.setCesComp(dto.COMP);
            cursoEspejo.setId(idCursoEspejo);

            idEspejo.setCurAsign(dto.ASIGN);
            idEspejo.setCurElect(dto.ELECT);
            idEspejo.setCurCoord(dto.COORD);
            idEspejo.setCurSecc(dto.SECC);
            idEspejo.setCurAgno(dto.AGNO);
            idEspejo.setCurSem(dto.SEM);
            idEspejo.setCurComp(dto.COMP);
            espejo.setId(idEspejo);
            espejo.setCurNombre(dto.NOMBRE);

            idTransversal.setCurAsign(dto.ASIGN_TR);
            idTransversal.setCurElect(dto.ELECT_TR);
            idTransversal.setCurCoord(dto.COORD_TR);
            idTransversal.setCurSecc(dto.SECC_TR);
            idTransversal.setCurAgno(dto.AGNO_TR);
            idTransversal.setCurSem(dto.SEM_TR);
            idTransversal.setCurComp(dto.COMP_TR);
            transversal.setId(idTransversal);

            // Datos base
            transversal.setCurCupoIni(dto.CUPO_INI_TR);
            transversal.setCurJorDiurno(dto.JOR_DIURNO_TR);
            transversal.setCurJorVesp(dto.JOR_VESP_TR);

            // Virtuales
            transversal.setCurNombre(dto.NOMBRE_TR);
            transversal.setCurProfesores(dto.NOMBRE_PROFESORES_TR);
            transversal.setCurAyudantes(dto.NOMBRE_AYUDANTES_TR);
            transversal.setCurHorario(dto.HORARIO_TR);
            transversal.setCurSalas(dto.SALAS_TR);

            // Fechas (solo si no están vacías)
            if (dto.FECHA_INICIO_TR != null && !dto.FECHA_INICIO_TR.isEmpty()) {
                transversal.setCurFechaInicio(Date.valueOf(dto.FECHA_INICIO_TR));
            }

            if (dto.FECHA_TERMINO_TR != null && !dto.FECHA_TERMINO_TR.isEmpty()) {
                transversal.setCurFechaTermino(Date.valueOf(dto.FECHA_TERMINO_TR));
            }

            cursoEspejo.setEspejo(espejo);
            cursoEspejo.setTransversal(transversal);

            return cursoEspejo;
        }).collect(Collectors.toList());

        return cursoList;
    }

    public static List<CursoProfesor> getListCursoProfFromJson(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<CursoProfesorJsonDTO>>() {
        }.getType();
        List<CursoProfesorJsonDTO> cursoJsonList = gson.fromJson(json, listType);
        List<CursoProfesor> cursoList;

        cursoList = cursoJsonList.stream().map(dto -> {
            CursoProfesor cursoProf = new CursoProfesor();
            Curso curso = new Curso();
            Profesor prof = new Profesor();

            // ID compuesto
            CursoProfesorId id = new CursoProfesorId();
            CursoId cursoId = new CursoId();

            cursoId.setCurAsign(dto.ASIGN);
            cursoId.setCurElect(dto.ELECT);
            cursoId.setCurCoord(dto.COORD);
            cursoId.setCurSecc(dto.SECC);
            cursoId.setCurAgno(dto.AGNO);
            cursoId.setCurSem(dto.SEM);
            curso.setCurNombre(dto.NOMBRE);
            curso.setId(cursoId);

            id.setCproAsign(dto.ASIGN);
            id.setCproElect(dto.ELECT);
            id.setCproCoord(dto.COORD);
            id.setCproSecc(dto.SECC);
            id.setCproAgno(dto.AGNO);
            id.setCproSem(dto.SEM);
            id.setCproRut(dto.PROF_RUT);
            cursoProf.setId(id);

            prof.setProfRut(dto.PROF_RUT);
            prof.setProfPat(dto.PROF_PAT);
            prof.setProfMat(dto.PROF_MAT);
            prof.setProfNom(dto.PROF_NOM);
            cursoProf.setProfesor(prof);
            cursoProf.setCurso(curso);

            return cursoProf;
        }).collect(Collectors.toList());

        return cursoList;
    }

}
