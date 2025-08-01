package infrastructure.util.common;

import domain.model.Profesor;
import domain.model.Curso;
import domain.model.Horario;
import domain.model.CursoAyudante;
import domain.model.CursoProfesor;
import domain.model.CursoId;
import domain.model.CursoEspejo;
import domain.model.CursoEspejoId;
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
                ws.setCursoList(cursoRepository.find(tipoCarrera, especialidad, regimen, agno, sem, rut, userType, "CTE"));
                break;
            case "C":
                ws.setCursoList(cursoRepository.find(tipoCarrera, especialidad, regimen, agno, sem, rut, userType, "C"));
                break;
            case "T": // Transversales               
                ws.setCursoTransversalList(cursoRepository.find(tipoCarrera, especialidad, regimen, agno, sem, rut, userType, "CT"));                
                break;
            case "T*": // Todos los transversales
                
System.out.println("super");

                ws.setCursoTransversalFullList(cursoRepository.findTransversales(agno, sem));
                break;
            case "E":
                ws.setCursoEspejoList(ContextUtil.getDAO().getCursoEspejoRepository(user)
                        .find(tipoCarrera, especialidad, regimen, agno, sem, rut, userType));
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
                ws.setCursoProfesorList(cursoProfesorRepository.find(tipoCarrera, especialidad, regimen, agno, sem, rut, userType, "CTE"));
                break;
            case "C":
                ws.setCursoProfesorList(cursoProfesorRepository.find(tipoCarrera, especialidad, regimen, agno, sem, rut, userType, "C"));
                break;
            case "T":
                ws.setCursoProfesorList(cursoProfesorRepository.find(tipoCarrera, especialidad, regimen, agno, sem, rut, userType, "T"));
                break;
            case "T*": // Todos los transversales
                ws.setCursoTransversalList(ContextUtil.getDAO().getCursoRepository(user).findTransversales(agno, sem));
                break;
            case "E":
                ws.setCursoEspejoList(ContextUtil.getDAO().getCursoEspejoRepository(user)
                        .find(tipoCarrera, especialidad, regimen, agno, sem, rut, userType));
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
}
