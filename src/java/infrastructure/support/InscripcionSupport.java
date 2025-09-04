/*
 * @(#)InscripcionSupport.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.support;

import domain.model.Derecho;
import domain.model.Curso;
import domain.model.AluCar;
import domain.model.CursoId;
import domain.model.AluCarId;
import domain.model.Asignatura;
import domain.model.InscripcionId;
import domain.model.Inscripcion;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.List;
import java.util.Map;
import domain.repository.MallaRepository;
import session.GenericSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.DateUtil.getSysdate;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import static infrastructure.util.HibernateUtil.rollbackTransaction;
import infrastructure.util.LogUtil;
import static infrastructure.util.common.CommonCursoUtil.getDistinctAsc;
import domain.model.InscripcionCursoView;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Reglas de negocio para la inscripción de asignaturas.
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class InscripcionSupport {

    private ActionCommonSupport action;
    private final AluCar aluCar;
    private int errDelete;
    private final GenericSession genericSession;
    private final int rut;

    /**
     *
     * @param aluCar
     * @param genericSession
     */
    public InscripcionSupport(AluCar aluCar, GenericSession genericSession) {
        this.aluCar = aluCar;
        this.rut = aluCar.getId().getAcaRut();
        this.genericSession = genericSession;
    }

    /**
     *
     * @param aluCar
     * @param action
     * @param genericSession
     */
    public InscripcionSupport(AluCar aluCar, ActionCommonSupport action,
            GenericSession genericSession) {
        this.aluCar = aluCar;
        this.rut = aluCar.getId().getAcaRut();
        this.action = action;
        this.genericSession = genericSession;
    }

    /**
     * Obtiene los cursos definidos para la asignatura(derecho).
     *
     * @param derecho Asignatura que el alumno tiene derecho de inscribir.
     * @return Lista de cursos definidos para la asignatura.
     */
    public List<Curso> getCursos(Derecho derecho) {
        List<Curso> cursoLists = new ArrayList<>();

        if (this.aluCar.getDerechosInscripcion() != null) {
            cursoLists = ContextUtil.getDAO().getCursoRepository(ActionUtil.getDBUser()).find(
                    derecho.getId().getDerAsign(), this.aluCar.getParametros().getAgnoIns(),
                    this.aluCar.getParametros().getSemIns(), this.aluCar.getPlan().getMencion().getId().getMenCodCar(), this.aluCar.getPlan().getMencion().getId().getMenCodMen());
        }

        return cursoLists;
    }

    /**
     * Obtiene los cursos definidos para la asignatura(derecho).
     *
     * @param gs
     * @param derecho Asignatura que el alumno tiene derecho de inscribir.
     * @param agnoIns
     * @param semIns
     * @return Lista de cursos definidos para la asignatura.
     */
    public List<Curso> getCursos(GenericSession gs, DerechoCoordinadorSupport derecho, Integer agnoIns, Integer semIns) {
        return getDistinctAsc(ContextUtil.getDAO().getCursoRepository(ActionUtil.getDBUser()).findAll(
                gs.getRut(), gs.getUserType(), derecho.getDerAsign(), agnoIns, semIns));
    }

    /**
     *
     *
     * @param rut
     * @param userType
     * @return Lista de cursos inscritos.
     */
    public Integer isAlumnoPropio(Integer rut, String userType) {
        return ContextUtil.getDAO().getAluCarRepository(ActionUtil.getDBUser()).isAlumnoPropio(aluCar, rut, userType);
    }

    public List<Inscripcion> getInscripcionFull(Integer agno, Integer sem) {
        List<Inscripcion> insList = getInscripcion(agno, sem);
        getTotales(insList);
        return insList;
    }

    public List<Inscripcion> getInscripcion(Integer agno, Integer sem) {
        return ContextUtil.getDAO().getInscripcionRepository(ActionUtil.getDBUser()).getInscripcion(aluCar, agno,
                sem);
    }

    /**
     * Totaliza número de asignaturas y créditos inscritos.
     *
     * @param cursoList Lista de cursos inscritos.
     */
    private void getTotales(List<Inscripcion> insList) {
        Set<String> tipoAsignaturasValidas = new HashSet<>();
        tipoAsignaturasValidas.add("P");
        tipoAsignaturasValidas.add("M");

        // Inicialización de variables de acumulación
        final int[] nroAsignMallaInscritas = {0};
        final int[] nroCredMallaInscitos = {0};
        final int[] sctAcum = {0};

        // Recorremos la lista 
        insList.forEach(ins -> {
            Asignatura asignatura = ins.getCurso().getAsignatura();
            if (tipoAsignaturasValidas.contains(asignatura.getAsiTipo())) {
                nroAsignMallaInscritas[0]++;
                nroCredMallaInscitos[0] += asignatura.getAsiHcredTeo() + asignatura.getAsiHcredEje() + asignatura.getAsiHcredLab();
                sctAcum[0] += (asignatura.getAsiSct() == null) ? 0 : getSct(asignatura);
            }
        });

        // Asignamos los resultados a aluCar
        this.aluCar.setAsignaturasInscritas(nroAsignMallaInscritas[0]);
        this.aluCar.setCreditosInscritos(nroCredMallaInscitos[0]);
        this.aluCar.setSctInscritos(sctAcum[0]);
    }

    /**
     * Elimina inscripción por el alumno.
     *
     * @param parameters Check-box del formulario de inscripción.
     */
    public void removeInscripcionAlumno(Map<String, String[]> parameters) {
        if ("SI".equals(this.aluCar.getParametros().getPuedeEliminar())) {
            removeInscripciones(action, parameters, true);
        }
    }

    /**
     * Elimina inscripción por el Jefe de Carrera.
     *
     * @param userType
     * @param parameters Check-box del formulario de inscripción.
     */
    public void removeInscripcionCoordinador(String userType, Map<String, String[]> parameters) {
        //if (this.aluCar.getIsAlumnoPropio() || "CFI".equals(userType)) {
        removeInscripciones(action, parameters, false);
        //}
    }

    private void removeInscripciones(ActionCommonSupport action, Map<String, String[]> parameters, boolean alumno) {
        action.clearActionErrors();
        beginTransaction(ActionUtil.getDBUser());

        List<Curso> cursosSeleccionados = IntStream.range(0, this.aluCar.getInsList().size())
                .filter(i -> parameters.containsKey("ck_" + i))
                .mapToObj(i -> this.aluCar.getInsList().get(i).getCurso())
                .collect(Collectors.toList());

        boolean eliminacionExitosa = cursosSeleccionados.stream()
                .allMatch(curso -> {
                    CursoId idCurso = curso.getId();
                    String nombre = curso.getNombreFull();

                    int errDelete = ContextUtil.getDAO().getInscripcionRepository(ActionUtil.getDBUser()).deleteInscripcion(this.aluCar, idCurso, alumno ? "DEL_ALUMNO" : "DEL_COORD", genericSession.getRut(), genericSession.getUserType());

                    if (errDelete > 0) {
                        String errMsg;

                        if (errDelete == 20001) {
                            errMsg = action.getText("error.acta.generada") + " " + nombre;
                        } else {
                            errMsg = "NO elimina " + nombre + ". Dé aviso de esta situación a Registro Curricular";
                        }

                        action.addActionError(errMsg);
                        LogUtil.setLog(rut, errMsg);

                        return false; // Indica que no fue exitoso
                    }

                    return true; // Indica que fue exitoso
                });

        if (eliminacionExitosa) {
            LogUtil.setLog(rut, "Eliminación exitosa");
            commitTransaction();
        } else {
            rollbackTransaction();
        }
    }

    public String addInscripcionAlumno(Curso curso, Derecho derecho, Integer carrera, Integer mencion) {

        String retValor = puedeInscribirAlumno(action, curso);

        if (retValor.equals(SUCCESS)) {
            newInscripcion(curso, "INS_ALUMNO", derecho.getDerForce());
        } else {
            LogUtil.setLog(rut, "NO inscribe " + curso.getNombreFull() + " Causa "
                    + action.getActionErrors().iterator().next());
        }

        return retValor;
    }

    /**
     * Agregar inscripción si cumple todas las reglas de negocio de inscripción.
     *
     * @param userType
     * @param aluCar
     * @param curso
     * @param derecho
     * @return SUCCESS: OK, ERROR: No cumple alguna de las reglas.
     */
    public String addInscripcionCoordinador(String userType, AluCar aluCar, Curso curso, DerechoCoordinadorSupport derecho) {
        action.clearErrorsAndMessages();

        try {
            if (!aluCar.estaActivo()) {
                action.addActionError(action.getText("error.alumno.no.activo"));

                LogUtil.setLog(rut, "NO inscribe " + curso.getNombreFull() + " Causa "
                        + action.getText("error.alumno.no.activo"));

                return ERROR;
            }

            if (ContextUtil.getDAO().getActaCalificacionRepository(ActionUtil.getDBUser()).isExisteActaCoordinador(aluCar.getId(), curso.getId()) != 0) {
                action.addActionError(action.getText("error.acta.generada"));
                LogUtil.setLog(rut, "NO inscribe " + curso.getNombreFull() + " Causa "
                        + action.getText("error.acta.generada"));

                return ERROR;
            }

            if (!SUCCESS.equals(puedeInscribirCoordinador(action, curso))) {
                LogUtil.setLog(rut, "NO inscribe " + curso.getNombreFull() + " Causa No puede inscribir");

                return ERROR;
            }
            
            newInscripcion(curso, "INS_COORD", "");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;

    }

    /**
     * Agregar inscripción si cumple todas las reglas de negocio de inscripción.
     *
     * @param userType
     * @param aluCar
     * @param oldCurso
     * @param newCurso
     * @return SUCCESS: OK, ERROR: No cumple alguna de las reglas.
     */
    public String traspasoInscripcionCoordinador(String userType, AluCar aluCar, Curso oldCurso, Curso newCurso) {

        if (ContextUtil.getDAO().getActaCalificacionRepository(ActionUtil.getDBUser()).isExisteActaCoordinador(aluCar.getId(), newCurso.getId()) != 0) {
            action.clearErrors();

            action.addActionError(action.getText("error.acta.generada"));
            LogUtil.setLog(rut, "NO traspasa " + newCurso.getNombreFull() + " Causa "
                    + action.getText("error.acta.generada"));

            return ERROR;
        }

        if (!SUCCESS.equals(tieneTopeHorario(action, aluCar, oldCurso, newCurso))) {
            action.addActionError("RUT " + aluCar.getAlumno().getAluRut());
            LogUtil.setLog(rut, "NO traspasa " + newCurso.getNombreFull() + " Causa Tope de Horario");

            return ERROR;
        }

        traspasoInscripcion(aluCar, oldCurso, newCurso, "TRASPASO_CURSO");

        return SUCCESS;
    }

    /**
     * Method description
     *
     *
     * @param curso
     * @param realizador
     * @param force
     */
    public void newInscripcion(Curso curso, String realizador, String force) {

        beginTransaction(ActionUtil.getDBUser());
        ContextUtil.getDAO().getInscripcionRepository(ActionUtil.getDBUser()).makePersistent(
                inscripcionNueva(curso, realizador, genericSession.getRut(), force));

        commitTransaction();
        LogUtil.setLog(genericSession.getRut(), rut + "> " + curso.getNombreFull());
    }

    /**
     * Method description
     *
     *
     * @param aluCar
     * @param oldCurso
     * @param newCurso
     * @param realizador
     */
    public void traspasoInscripcion(AluCar aluCar, Curso oldCurso, Curso newCurso, String realizador) {

        ContextUtil.getDAO().getInscripcionRepository(ActionUtil.getDBUser()).traspaso(aluCar.getId(), oldCurso.getId(), newCurso.getId());
        LogUtil.setLog(genericSession.getRut(), rut + "> " + newCurso.getNombreFull());
    }

    /**
     * Reglas de negocio de inscripción de asignaturas. Estas se implementan
     * como una cascade validaciones.
     *
     * @param action Action que invoca para responder el mensaje de error
     * correspondiente mediante struts2( actionError).
     * @param curso Curso a inscribir.
     * @return SUCCESS: OK, ERROR: No cumple alguna de las reglas.
     */
    private String puedeInscribirAlumno(ActionCommonSupport action, Curso curso) {
        String retValue = ContextUtil.getDAO().getScalarRepository(ActionUtil.getDBUser()).validarInscripcionAlumno(
                this.aluCar.getId().getAcaRut(),
                this.aluCar.getId().getAcaCodCar(),
                this.aluCar.getId().getAcaAgnoIng(),
                this.aluCar.getId().getAcaSemIng(),
                this.aluCar.getAcaCodMen(),
                this.aluCar.getAcaCodPlan(),
                curso.getId().getCurAsign(),
                curso.getId().getCurElect(),
                curso.getId().getCurCoord(),
                curso.getId().getCurSecc(),
                curso.getId().getCurAgno(),
                curso.getId().getCurSem());

        if (!SUCCESS.equals(retValue)) {
            action.addActionError(retValue);
        }

        return retValue;
    }

    /**
     * Reglas de negocio de inscripción de asignaturas. Estas se implementan
     * como una cascade validaciones.
     *
     * @param action Action que invoca para responder el mensaje de error
     * correspondiente mediante struts2( actionError).
     * @param curso Curso a inscribir.
     * @return SUCCESS: OK, ERROR: No cumple alguna de las reglas.
     */
    private String puedeInscribirCoordinador(ActionCommonSupport action, Curso curso) {
        
        action.clearMessages();
        String retValue = ContextUtil.getDAO().getScalarRepository(ActionUtil.getDBUser()).validarInscripcionCoord(
                this.aluCar.getId().getAcaRut(),
                this.aluCar.getId().getAcaCodCar(),
                this.aluCar.getId().getAcaAgnoIng(),
                this.aluCar.getId().getAcaSemIng(),
                this.aluCar.getAcaCodMen(),
                this.aluCar.getAcaCodPlan(),
                curso.getId().getCurAsign(),
                curso.getId().getCurElect(),
                curso.getId().getCurCoord(),
                curso.getId().getCurSecc(),
                curso.getId().getCurAgno(),
                curso.getId().getCurSem());

        if (!SUCCESS.equals(retValue)) {            
            String[] textos = retValue.split("\\n");
            for (String texto : textos) {
                if (texto != null && !texto.trim().isEmpty()) {
                    action.addActionMessage(texto.trim());
                }
            }           
        }

        return retValue;
    }

    /**
     * Reglas de negocio de inscripción de asignaturas. Estas se implementan
     * como una cascade validaciones.
     *
     * @param action Action que invoca para responder el mensaje de error
     * correspondiente mediante struts2( actionError).
     * @param curso Curso a inscribir.
     * @return SUCCESS: OK, ERROR: No cumple alguna de las reglas.
     */
    private String tieneTopeHorario(ActionCommonSupport action, AluCar aluCar, Curso oldCurso, Curso newCurso) {

        String retValue = ERROR;

        String tope = topeHorarioCambioCurso(aluCar, oldCurso, newCurso);
        String actionMessage = "";

        if (tope != null) {
            actionMessage += " " + action.getText("error.tope.horario");
        }

        if (actionMessage.isEmpty()) {
            retValue = SUCCESS;
        } else {
            action.clearMessages();
            action.addActionMessage(actionMessage);
        }

        return retValue;

    }

    public void setSctNivel() {
        if ("SI".equals(aluCar.getParametros().getPuedeInscribirMalla())
                || "SI".equals(aluCar.getParametros().getPuedeModificar())
                || asList("JC", "SP").contains(genericSession.getUserType())) {

            MallaRepository mallaPersistence = ContextUtil.getDAO().getMallaRepository(ActionUtil.getDBUser());

            if (aluCar.getPlan().getMencion().getCarrera().getTcarrera().getTcrCtip() == 35 && aluCar.getAcaCodPlan() < 7) {
                aluCar.setCreditosNivel(mallaPersistence.getCreditosNivel(aluCar));
            }

            aluCar.setSctNivel(mallaPersistence.getSctNivel(aluCar));
        }
    }

    private String topeHorarioCambioCurso(AluCar aluCar, Curso cursoOri, Curso cursoDest) {
        AluCarId id = aluCar.getId();
        CursoId oriId = cursoOri.getId();
        CursoId destId = cursoDest.getId();

        return ContextUtil.getDAO().getScalarRepository(ActionUtil.getDBUser()).topeHorarioCambioCurso(
                id.getAcaRut(),
                id.getAcaCodCar(),
                id.getAcaAgnoIng(),
                id.getAcaSemIng(),
                oriId.getCurAsign(),
                oriId.getCurElect(),
                oriId.getCurCoord(),
                oriId.getCurSecc(),
                oriId.getCurAgno(),
                oriId.getCurSem(),
                destId.getCurAsign(),
                destId.getCurElect(),
                destId.getCurCoord(),
                destId.getCurSecc(),
                destId.getCurAgno(),
                destId.getCurSem()
        );

    }

    /**
     * Crea una nueva inscripción.
     *
     * @param curso Curso a inscribir.
     * @return La nueva inscripción.
     */
    private Inscripcion inscripcionNueva(Curso curso, String realizador, Integer rutRea, String force) {
        Inscripcion insNueva = new Inscripcion();
        InscripcionId insIdNueva = new InscripcionId();
        AluCarId aluCarId = this.aluCar.getId();

        insIdNueva.setInsRut(aluCarId.getAcaRut());
        insIdNueva.setInsCodCar(aluCarId.getAcaCodCar());
        insIdNueva.setInsAgnoIng(aluCarId.getAcaAgnoIng());
        insIdNueva.setInsSemIng(aluCarId.getAcaSemIng());

        CursoId cursoId = curso.getId();

        insIdNueva.setInsAsign(cursoId.getCurAsign());
        insIdNueva.setInsElect(cursoId.getCurElect());
        insIdNueva.setInsAgno(cursoId.getCurAgno());
        insIdNueva.setInsSem(cursoId.getCurSem());
        insIdNueva.setInsComp(cursoId.getCurComp());

        insNueva.setId(insIdNueva);
        insNueva.setInsCoord(cursoId.getCurCoord());
        insNueva.setInsSecc(cursoId.getCurSecc());
        insNueva.setInsFecha(getSysdate());
        insNueva.setInsProceso(realizador);
        insNueva.setInsRutReali(rutRea);
        insNueva.setInsForce(force);

        return insNueva;
    }

    public void swapInscripcion(AluCarId id, CursoId cOld, CursoId cNew) {
        beginTransaction(ActionUtil.getDBUser());
        ContextUtil.getDAO().getInscripcionRepository(ActionUtil.getDBUser()).swap(id, cOld, cNew);
        commitTransaction();
    }

    /**
     *
     * @return
     */
    public int getErrDelete() {
        return errDelete;
    }

    /**
     *
     * @param errDelete
     */
    public void setErrDelete(int errDelete) {
        this.errDelete = errDelete;
    }

    private int getSct(Asignatura asignatura) {
        int retValue = asignatura.getAsiSct();
        if (asList(351425, 351458).contains(asignatura.getAsiCod())) {
            retValue = 0;
        }

        return retValue;
    }

    public static List<AluCar> getNomina(List<InscripcionCursoView> nomina) {
        return nomina.stream()
                .map(InscripcionCursoView::getAluCar)
                .collect(Collectors.toList());
    }

    public static List<Curso> getCursoList(List<Inscripcion> insList) {
        return insList.stream()
                .map(Inscripcion::getCurso)
                .collect(Collectors.toList());
    }
}
