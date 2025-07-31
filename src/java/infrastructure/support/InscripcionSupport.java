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
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

        String retValor = puedeInscribirAlumno(action, curso, derecho, carrera, mencion);

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
        if (!aluCar.estaActivo()) {

            action.clearErrorsAndMessages();
            action.addActionError(action.getText("error.alumno.no.activo"));

            LogUtil.setLog(rut, "NO inscribe " + curso.getNombreFull() + " Causa "
                    + action.getText("error.alumno.no.activo"));

            return ERROR;
        }

        if (asignaturaInscrita(curso)) {

            action.addActionError(action.getText("error.asignatura.ya.inscrita"));
            LogUtil.setLog(rut, "NO inscribe " + curso.getNombreFull() + " Causa "
                    + action.getText("error.asignatura.ya.inscrita"));

            return ERROR;
        }

        if (ContextUtil.getDAO().getActaCalificacionRepository(ActionUtil.getDBUser()).isExisteActaCoordinador(aluCar.getId(), curso.getId()) != 0) {
            action.addActionError(action.getText("error.acta.generada"));
            LogUtil.setLog(rut, "NO inscribe " + curso.getNombreFull() + " Causa "
                    + action.getText("error.acta.generada"));

            return ERROR;
        }

        if (!SUCCESS.equals(puedeInscribirCoordinador(userType, action, curso, derecho, aluCar.getId().getAcaCodCar(), aluCar.getAcaCodMen()))) {
            LogUtil.setLog(rut, "NO inscribe " + curso.getNombreFull() + " Causa No puede inscribir");

            return ERROR;
        }

        newInscripcion(curso, "INS_COORD", "");
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
    private String puedeInscribirAlumno(ActionCommonSupport action, Curso curso, Derecho derecho, Integer carrera, Integer mencion) {
        ParametroSesionSupport param = aluCar.getParametros();

        return Stream.<Supplier<Optional<String>>>of(
                () -> puedeInscribir(derecho, param) ? Optional.empty() : Optional.of("error.inscripcion.noPuedeInscribir"),
                () -> asignaturaInscrita(curso) ? Optional.of("error.asignatura.ya.inscrita") : Optional.empty(),
                () -> sobrepasaMaxSct(derecho.getDerTipo(), getSct(derecho.getAsignatura())) ? Optional.of("error.excede.maximo.sct") : Optional.empty(),
                () -> sobrepasaMaxAsign(derecho.getDerTipo()) ? Optional.of("error.excede.maximo.inscripciones") : Optional.empty(),
                () -> sobrepasaMaxNivel(derecho.getDerTipo(), derecho.getDerNiv()) ? Optional.of("error.excede.maximo.nivel") : Optional.empty(),
                () -> sobrepasaCreditosNivel(curso, derecho.getDerTipo()) ? Optional.of("error.excede.maximo.creditos") : Optional.empty(),
                () -> noTieneRequisitosElectivo(curso) ? Optional.of("error.sin.requisitos") : Optional.empty(),
                () -> electivoYaAprobado(curso) ? Optional.of("error.electivo.aprobado") : Optional.empty(),
                () -> {
                    String tope = topeHorario(curso);
                    return tope.isEmpty() ? Optional.empty() : Optional.of(action.getText("error.tope.horario") + " " + tope);
                },
                () -> noHayCupo(curso, carrera, mencion) ? Optional.of("error.sin.cupos") : Optional.empty()
        )
                .map(Supplier::get)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .map(error -> addError(action, error))
                .orElse(SUCCESS);
    }

    private boolean puedeInscribir(Derecho derecho, ParametroSesionSupport param) {
        return (derecho.getDerTipo() == 1 && ("SI".equals(param.getPuedeInscribirMalla()) || "SI".equals(param.getPuedeModificar())))
                || ((derecho.getDerTipo() == 2 || derecho.getDerTipo() == 3) && "SI".equals(param.getPuedeInscribirFormacionIntegral()));
    }

    private String addError(ActionCommonSupport action, String errorKey) {
        action.addActionError(action.getText(errorKey));
        return ERROR;
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
    private String puedeInscribirCoordinador(String userType, ActionCommonSupport action, Curso curso,
            DerechoCoordinadorSupport derecho, Integer carrera, Integer mencion) {

        String tope = topeHorario(curso);
        StringBuilder actionMessage = new StringBuilder();
        Integer tipoDerecho = 0;

        switch (userType) {
            case "JC":
            case "SP":
                tipoDerecho = 1;
                break;
            case "CFI":
                tipoDerecho = 3;
        }

        if (sobrepasaMaxSct(tipoDerecho, derecho.getDerSct())) {
            actionMessage.append(" ").append(action.getText("error.excede.maximo.sct"));
        }

        if (sobrepasaMaxAsign(tipoDerecho)) {
            actionMessage.append(" ").append(action.getText("error.excede.maximo.inscripciones"));
        }

        if (sobrepasaMaxNivel(tipoDerecho, derecho.getDerNivel())) {
            actionMessage.append(" ").append(action.getText("error.excede.maximo.nivel"));
        }

        if (sobrepasaCreditosNivel(curso, tipoDerecho)) {
            actionMessage.append(" ").append(action.getText("error.excede.maximo.creditos"));
        }

        if (noTieneRequisitosElectivo(curso)) {
            actionMessage.append(" ").append(action.getText("error.sin.requisitos"));
        }

        if (tope != null && !tope.isEmpty()) {
            actionMessage.append(" ").append(action.getText("error.tope.horario")).append(" ").append(tope);
        }

        if (noHayCupo(curso, carrera, mencion)) {
            actionMessage.append(" ").append(action.getText("error.sin.cupos"));
        }

        if (actionMessage.length() == 0) {
            return SUCCESS;
        } else {
            action.addActionMessage(actionMessage.toString().trim());
            return ERROR;
        }
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

    /**
     * Verifica si se pasa del máximo de asignaturas a inscribir permitido.
     *
     * @return True: sobrepasa el máximo, false de lo contrario.
     */
    private boolean sobrepasaMaxAsign(Integer tipoDerecho) {
        boolean retValue = false;

        if (tipoDerecho == 1 && this.aluCar.getPlan().getMencion().getCarrera().getTcarrera().getTcrCtip() == 33 && this.aluCar.getAcaCodPlan() < 3) {
            retValue = this.aluCar.getAsignaturasInscritas() + 1
                    > this.aluCar.getParametros().getMaxAsignInscritas();
        }

        return retValue;
    }

    /**
     * Method description
     *
     *
     * @param tipoDerecho
     * @param sct
     *
     * @return
     */
    private boolean sobrepasaMaxSct(Integer tipoDerecho, Integer sct) {

        Integer sctExtra = 4;
        

        /*if (aluCar.getPlan().getMencion().getCarrera().getTcarrera().getTcrCtip() == 16) {
            sctExtra = 4;
        }*/
        return (tipoDerecho == 1 || tipoDerecho == 4) &&  this.aluCar.getSctInscritos() + sct > 34 ;
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

    /**
     * Verifica si el nivel del curso a inscribir sobrepasa al permitido.
     *
     * @return True: sobrepasa el máximo nivel, false de lo contrario.
     */
    private boolean sobrepasaMaxNivel(Integer tipoDerecho, Integer nivel) {
        boolean retValue = false;

        if (tipoDerecho == 1 && this.aluCar.getPlan().getMencion().getCarrera().getTcarrera().getTcrCtip() == 35) {
            retValue = this.aluCar.getAluCarFunction().getNivel() + this.aluCar.getParametros().getMaxNivelAdelanto()
                    < nivel;
        }

        return retValue;
    }

    /**
     * Verifica si se pasa del máximo de créditos que un alumno puede inscribir,
     * dependiendo del nivel en que se encuentra el alumno.
     *
     * @param curso Curso a inscribir.
     * @return True: Sobrepasa el número de créditos, false de lo contrario.
     */
    private boolean sobrepasaCreditosNivel(Curso curso, Integer tipoDerecho) {
        boolean retValue = false;

        if (tipoDerecho == 1 && this.aluCar.getPlan().getMencion().getCarrera().getTcarrera().getTcrCtip() == 35 && this.aluCar.getAcaCodPlan() < 7) {
            int creditos = curso.getAsignatura().getAsiHcredTeo() + curso.getAsignatura().getAsiHcredEje()
                    + curso.getAsignatura().getAsiHcredLab();

            retValue = this.aluCar.getCreditosInscritos() + creditos > this.aluCar.getCreditosNivel() + 4;
        }

        return retValue;
    }

    /**
     * Verifica si no hay cupos disponibles.
     *
     * @param curso Curso a inscribir.
     * @return True: No hay cupos disponible, false de lo contrario.
     */
    private boolean noHayCupo(Curso curso, Integer carrera, Integer mencion) {
        boolean retValue = false;
        CursoId id = curso.getId();

        if (!"P".equals(curso.getAsignatura().getAsiTipo())) {
            retValue = "NO".equals(ContextUtil.getDAO().getScalarRepository(ActionUtil.getDBUser()).hayCupoCarrera(
                    id.getCurAsign(),
                    id.getCurElect(),
                    id.getCurCoord(),
                    id.getCurSecc(),
                    id.getCurAgno(),
                    id.getCurSem(),
                    carrera, mencion));
        } else {
            if ("SI".equals(this.aluCar.getParametros().getPuedeModificar())) {
                retValue = "NO".equals(ContextUtil.getDAO().getScalarRepository(ActionUtil.getDBUser()).hayCupo(
                        id.getCurAsign(),
                        id.getCurElect(),
                        id.getCurCoord(),
                        id.getCurSecc(),
                        id.getCurAgno(),
                        id.getCurSem()));
            }
        }

        return retValue;
    }

    /**
     * Verifica si no tiene prerequisitos para inscribir curso en el caso de ser
     * electivo.
     *
     * @param curso Curso a verificar si es electivo y cumple los prerequisitos.
     * @return True: Si no cumple, false de lo contrario.
     */
    private boolean noTieneRequisitosElectivo(Curso curso) {
        boolean retValue = false;

        if ("S".equals(curso.getAsignatura().getAsiElect())) {
            CursoId cursoId = curso.getId();
            AluCarId aluCarId = this.aluCar.getId();
            int flag = ContextUtil.getDAO().getScalarRepository(ActionUtil.getDBUser()).tienePreReqElectivo(
                    aluCarId.getAcaRut(),
                    aluCarId.getAcaCodCar(),
                    aluCarId.getAcaAgnoIng(),
                    aluCarId.getAcaSemIng(),
                    this.aluCar.getAcaCodMen(),
                    this.aluCar.getAcaCodPlan(),
                    cursoId.getCurAsign(),
                    cursoId.getCurElect(),
                    cursoId.getCurAgno(),
                    cursoId.getCurSem()
            );

            retValue = (flag == 0);
        }

        return retValue;
    }

    private boolean electivoYaAprobado(Curso curso) {
        boolean retValue = false;

        if ("S".equals(curso.getAsignatura().getAsiElect())) {
            CursoId cursoId = curso.getId();
            AluCarId aluCarId = this.aluCar.getId();

            int flag = ContextUtil.getDAO().getScalarRepository(ActionUtil.getDBUser()).electivoYaAprobado(
                    aluCarId.getAcaRut(),
                    aluCarId.getAcaCodCar(),
                    aluCarId.getAcaAgnoIng(),
                    aluCarId.getAcaSemIng(),
                    cursoId.getCurAsign(),
                    cursoId.getCurElect(),
                    cursoId.getCurAgno(),
                    cursoId.getCurSem()
            );

            retValue = (flag == 1);
        }

        return retValue;
    }

    /**
     * Verifica tope de horario del curso con los cursos ya inscritos.
     *
     * @param curso Curso a inscribir.
     * @return String con el tope (día-módulo), null si no hay tope.
     */
    private String topeHorario(Curso curso) {
        AluCarId id = this.aluCar.getId();
        CursoId cursoId = curso.getId();

        return ContextUtil.getDAO().getScalarRepository(ActionUtil.getDBUser()).topeHorario(
                id.getAcaRut(),
                id.getAcaCodCar(),
                id.getAcaAgnoIng(),
                id.getAcaSemIng(),
                cursoId.getCurAsign(),
                cursoId.getCurElect(),
                cursoId.getCurCoord(),
                cursoId.getCurSecc(),
                cursoId.getCurAgno(),
                cursoId.getCurSem()
        );

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
     * Verifica si la asignatura ya fue inscrita.
     *
     * @param curso Curso a inscribir.
     * @return True: Ya está inscrita, false de lo contrario.
     */
    private boolean asignaturaInscrita(Curso curso) {
        return this.aluCar.getInsList().stream()
                .anyMatch(insAux -> asList(16, 20, 33, 35).contains(aluCar.getPlan().getMencion().getCarrera().getTcarrera().getTcrCtip())
                ? insAux.getId().getInsAsign().equals(curso.getId().getCurAsign())
                && insAux.getId().getInsElect().trim().equals(curso.getId().getCurElect().trim())
                : insAux.getId().getInsAsign().equals(curso.getId().getCurAsign()));
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
