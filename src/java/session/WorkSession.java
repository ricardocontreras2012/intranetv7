/*
 * @(#)WorkSession.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package session;

import infrastructure.support.CursoResumenSupport;
import infrastructure.support.ReservaSalaSupport;
import infrastructure.support.MensajeSupport;
import infrastructure.support.ActionResultSupport;
import infrastructure.support.ActaConsultaSupport;
import infrastructure.support.CalificacionAdicionalLogroxInscribirSupport;
import infrastructure.support.MiCarreraSupport;
import infrastructure.support.DerechoCoordinadorSupport;
import domain.model.Mensaje;
import domain.model.Empleador;
import domain.model.CursoAyudante;
import domain.model.Ayudante;
import domain.model.AsistenciaAlumno;
import domain.model.InscripcionAdicionalLogro;
import domain.model.EvaluacionAlumno;
import domain.model.Calificacion;
import domain.model.ReporteClase;
import domain.model.LogCertificacion;
import domain.model.PregEncta;
import domain.model.Tsolicitud;
import domain.model.Profesor;
import domain.model.Tevaluacion;
import domain.model.SolicitudInscripcion;
import domain.model.Alumno;
import domain.model.Comuna;
import domain.model.ComentarioEncuestaAyudante;
import domain.model.MensajeDestinatario;
import domain.model.Mencion;
import domain.model.ComentarioEncuestaDocente;
import domain.model.MatriculaHistorico;
import domain.model.Ccalidad;
import domain.model.CursoProfesor;
import domain.model.Sala;
import domain.model.Practica;
import domain.model.Reincorporacion;
import domain.model.CursoEspejo;
import domain.model.Solicitud;
import domain.model.LogSolicitud;
import domain.model.LogInscripcion;
import domain.model.MaterialApoyo;
import domain.model.ExpedienteLogro;
import domain.model.TdocumentoSolicitud;
import domain.model.Unidad;
import domain.model.Persona;
import domain.model.ActaCalificacion;
import domain.model.Tmaterial;
import domain.model.ModuloHorario;
import domain.model.Derecho;
import domain.model.TmotivoSolicitudInscripcion;
import domain.model.DocenteHorario;
import domain.model.Evaluacion;
import domain.model.TrequisitoLogroAdicional;
import domain.model.Asignatura;
import domain.model.Tramite;
import domain.model.Malla;
import domain.model.Curso;
import domain.model.SolicitudJustificativo;
import domain.model.Horario;
import domain.model.PreguntaAutoEvaluacion;
import domain.model.CursoTevaluacion;
import domain.model.AluCar;
import domain.model.MencionInfoIntranet;
import domain.model.EncuestaDocente;
import domain.model.AsistenciaAlumnoNomina;
import domain.model.Funcionario;
import domain.model.EncuestaAyudante;
import domain.model.Electivo;
import domain.model.ReservaSala;
import static java.util.Arrays.copyOf;
import java.util.List;
import infrastructure.util.ContextUtil;
import static infrastructure.util.common.CommonAsistenciaUtil.asiste;
import domain.model.ActaNominaView;
import domain.model.RespEnctaCursoView;
import domain.model.RespEnctaAyuCursoView;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class WorkSession {

    private List<ActaCalificacion> actas;
    private String actionCall;
    private String actionNested;
    private ActionResultSupport actionSupport;
    private Integer agnoAct;
    private Integer semAct;
    private AluCar aluCar;
    private List<AluCar> aluCarList;
    private Alumno alumno;
    private List<Alumno> alumnoList;
    private List<Asignatura> asignaturaList;
    private AsistenciaAlumno asistenciaAlumno;
    private List<AsistenciaAlumno> asistenciaAlumnoList;
    private List<AsistenciaAlumnoNomina> asistenciaAlumnoNominaList;
    private Ayudante ayudante;
    private List<Ayudante> ayudanteList;
    private boolean blinkMensajes;
    private List<CalificacionAdicionalLogroxInscribirSupport> calificacionRequisitoAdicionalLogroxInscribirList;
    private List<Calificacion> calificaciones;
    private Ccalidad ccalidad;
    private List<ComentarioEncuestaDocente> comentarioEncuestaDocenteList;
    private List<ComentarioEncuestaAyudante> comentarioEncuestaAyudanteList;
    private List<ActaConsultaSupport> actaConsultaSupportList;
    private MiCarreraSupport miCarreraSupport;
    private List<MiCarreraSupport> miCarreraSupportList;
    private List<Comuna> comunaList;
    private Mensaje currentMsg;
    private Curso curso;
    private List<Curso> cursoList;
    private List<Curso> cursoSolicitudList;
    private List<Curso> cursoTransversalList;
    private boolean cursoPregrado;
    private CursoProfesor cursoProfesor;
    private CursoAyudante cursoAyudante;
    private boolean cursoPrograma;
    private List<CursoTevaluacion> cursoTevaluacion;
    private List<CursoProfesor> cursoProfesorList;
    private List<CursoProfesor> cursosAutoEvaluacion;
    private List<CursoAyudante> cursosEncuestaAyudante;
    private List<CursoAyudante> cursoAyudanteList;
    private Derecho derecho;
    private DerechoCoordinadorSupport derechoCoordinador;
    private Empleador empleador;
    private List<Empleador> empleadorList;
    private EncuestaDocente encuestaDocente;
    private EncuestaAyudante encuestaAyudante;
    private Evaluacion evaluacion;
    private List<EvaluacionAlumno> evaluacionAlumno;
    private List<Evaluacion> evaluacionList;
    private ExpedienteLogro expedienteLogro;
    private List<ExpedienteLogro> expedienteLogroList;
    private String fechaActual;
    private Horario[][] horario;
    private List<Horario> horarioList;
    private List<ModuloHorario> moduloHorarioList;
    private List<InscripcionAdicionalLogro> inscripcionRequisitoAdicionalLogroList;
    private InscripcionAdicionalLogro inscripcionRequisitoAdicionalLogro;
    private String keyParent;
    private LogCertificacion logCertificacion;
    private List<Tramite> tramites;
    private List<LogInscripcion> logInscripcionList;
    private MaterialApoyo material;
    private MencionInfoIntranet mencionInfoIntranet;
    private List<Mencion> mencionList;
    private Mensaje mensajeFwd;
    private MensajeSupport mensajeSupport;
    private List<String> modulos;
    private String nombre;
    private String nombreCarrera;
    private List<ActaNominaView> nominaActa;
    private List<AluCar> nominaCurso;
    private Long nuevosMensajes;
    private List<Tmaterial> otrosTmaterial;
    private Practica practica;
    private List<Practica> practicaList;
    private List<PregEncta> preguntasEncuesta;
    private Profesor profesor;
    private List<Profesor> profesorList;
    private boolean puedeEmitir;
    private List<MensajeDestinatario> receivedMsgs;
    private int cantMsgsReceived;
    private int cantMsgsReceivedFiltered;
    private ReporteClase reporte;
    private List<ReporteClase> reportes;
    private TrequisitoLogroAdicional trequisitoLogroAdicional;
    private List<TrequisitoLogroAdicional> trequisitoLogroAdicionalList;
    private List<RespEnctaCursoView> respEncta;
    private List<RespEnctaAyuCursoView> respEnctaAyu;
    private List<CursoResumenSupport> resumenCurso;
    private Sala sala;
    private List<Sala> salaList;
    private List<ReservaSala> reservaList;
    private List<MatriculaHistorico> matriculaList;

    private List<Mensaje> sentMsgs;
    private int cantMsgsSended;
    private int cantMsgsSendedFiltered;
    private Solicitud solicitud;
    private List<Solicitud> solicitudList;
    private List<Electivo> electivoList;
    private List<SolicitudInscripcion> solicitudInscripcionList;
    private List<TdocumentoSolicitud> tdocumentoSolicitudList;
    private List<Tevaluacion> tevaluacion;
    private String tipoCalificacion;
    private String tipoMaterial;
    private List<Tmaterial> tmaterial;
    private List<Tmaterial> tmaterialSelectOption;
    private List<Tsolicitud> tsolicitudList;
    private List<PreguntaAutoEvaluacion> preguntasAutoevaluacionList;
    private String type;
    private String typeSearch;
    private Integer pos;
    private String fechaInicio;
    private String fechaTermino;

    //private List<LogActa> logActaList;
    private List<LogSolicitud> logSolicitudList;
    private Persona persona;
    private String status;
    private List<CursoEspejo> cargaEspejo;
    private List<CursoEspejo> cursoEspejoList;
    private ReservaSalaSupport[][] horarioSala;
    private ReservaSala reserva;
    private List<Malla> mallaList;
    private String pdfTempFile;
    private List<Reincorporacion> reincorporacionList;
    private String flag;
    private String idHorario;

    private Funcionario funcionario;
    private List<Funcionario> funcionarioList;
    private List<Calificacion> actaRectificatoriaList;
    private List<DocenteHorario> docenteHorarioList;
    
    private List<Unidad> unidadList;
    private List<SolicitudJustificativo> justificativoList;

    /**
     *
     * @param type
     */
    public WorkSession(String type) {
        this.type = type;
        this.nuevosMensajes = 0L;
    }

    /**
     *
     * @return
     */
    public List<ActaCalificacion> getActas() {
        return actas;
    }

    /**
     *
     * @param actas
     */
    public void setActas(List<ActaCalificacion> actas) {
        this.actas = actas;
    }

    /**
     *
     * @return
     */
    public String getActionCall() {
        return actionCall;
    }

    /**
     *
     * @param actionCall
     */
    public void setActionCall(String actionCall) {
        this.actionCall = actionCall;
    }

    /**
     *
     * @return
     */
    public String getActionNested() {
        return actionNested;
    }

    /**
     *
     * @param actionNested
     */
    public void setActionNested(String actionNested) {
        this.actionNested = actionNested;
    }

    /**
     *
     * @return
     */
    public Integer getAgnoAct() {
        return agnoAct;
    }

    /**
     *
     * @param agnoAct
     */
    public void setAgnoAct(Integer agnoAct) {
        this.agnoAct = agnoAct;
    }

    /**
     *
     * @return
     */
    public AluCar getAluCar() {
        return aluCar;
    }

    /**
     *
     * @param aluCar
     */
    public void setAluCar(AluCar aluCar) {
        this.aluCar = aluCar;
    }

    /**
     *
     * @return
     */
    public List<AluCar> getAluCarList() {
        return aluCarList;
    }

    /**
     *
     * @param aluCarList
     */
    public void setAluCarList(List<AluCar> aluCarList) {
        this.aluCarList = aluCarList;
    }

    /**
     *
     * @return
     */
    public Alumno getAlumno() {
        return alumno;
    }

    /**
     *
     * @param alumno
     */
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    /**
     *
     * @return
     */
    public List<Alumno> getAlumnoList() {
        return alumnoList;
    }

    /**
     *
     * @param alumnoList
     */
    public void setAlumnoList(List<Alumno> alumnoList) {
        this.alumnoList = alumnoList;
    }

    /**
     *
     * @return
     */
    public List<Asignatura> getAsignaturaList() {
        return asignaturaList;
    }

    /**
     *
     * @param asignaturaList
     */
    public void setAsignaturaList(List<Asignatura> asignaturaList) {
        this.asignaturaList = asignaturaList;
    }

    /**
     *
     * @return
     */
    public AsistenciaAlumno getAsistenciaAlumno() {
        return asistenciaAlumno;
    }

    /**
     *
     * @param asistenciaAlumno
     */
    public void setAsistenciaAlumno(AsistenciaAlumno asistenciaAlumno) {
        this.asistenciaAlumno = asistenciaAlumno;
    }

    /**
     *
     * @return
     */
    public List<AsistenciaAlumno> getAsistenciaAlumnoList() {
        return asistenciaAlumnoList;
    }

    /**
     *
     * @param asistenciaAlumnoList
     */
    public void setAsistenciaAlumnoList(List<AsistenciaAlumno> asistenciaAlumnoList) {
        this.asistenciaAlumnoList = asistenciaAlumnoList;
    }

    /**
     *
     * @return
     */
    public List<AsistenciaAlumnoNomina> getAsistenciaAlumnoNominaList() {
        return asistenciaAlumnoNominaList;
    }

    /**
     *
     * @param asistenciaAlumnoNominaList
     */
    public void setAsistenciaAlumnoNominaList(List<AsistenciaAlumnoNomina> asistenciaAlumnoNominaList) {
        this.asistenciaAlumnoNominaList = asistenciaAlumnoNominaList;
    }

    /**
     *
     * @return
     */
    public Ayudante getAyudante() {
        return ayudante;
    }

    /**
     *
     * @param ayudante
     */
    public void setAyudante(Ayudante ayudante) {
        this.ayudante = ayudante;
    }

    /**
     *
     * @return
     */
    public List<Ayudante> getAyudanteList() {
        return ayudanteList;
    }

    /**
     *
     * @param ayudanteList
     */
    public void setAyudanteList(List<Ayudante> ayudanteList) {
        this.ayudanteList = ayudanteList;
    }

    /**
     *
     * @return
     */
    public boolean isBlinkMensajes() {
        return blinkMensajes;
    }

    /**
     *
     * @param blinkMensajes
     */
    public void setBlinkMensajes(boolean blinkMensajes) {
        this.blinkMensajes = blinkMensajes;
    }

    /**
     *
     * @return
     */
    public List<CalificacionAdicionalLogroxInscribirSupport> getCalificacionRequisitoAdicionalLogroxInscribirList() {
        return calificacionRequisitoAdicionalLogroxInscribirList;
    }

    /**
     *
     * @param calificacionRequisitoAdicionalLogroxInscribirList
     */
    public void setCalificacionRequisitoAdicionalLogroxInscribirList(List<CalificacionAdicionalLogroxInscribirSupport> calificacionRequisitoAdicionalLogroxInscribirList) {
        this.calificacionRequisitoAdicionalLogroxInscribirList = calificacionRequisitoAdicionalLogroxInscribirList;
    }

    /**
     *
     * @return
     */
    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    /**
     *
     * @param calificaciones
     */
    public void setCalificaciones(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    /**
     *
     * @return
     */
    public Ccalidad getCcalidad() {
        return ccalidad;
    }

    /**
     *
     * @param ccalidad
     */
    public void setCcalidad(Ccalidad ccalidad) {
        this.ccalidad = ccalidad;
    }

    /**
     *
     * @return
     */
    public List<ComentarioEncuestaDocente> getComentarioEncuestaDocenteList() {
        return comentarioEncuestaDocenteList;
    }

    /**
     *
     * @param comentarioEncuestaDocenteList
     */
    public void setComentarioEncuestaDocenteList(List<ComentarioEncuestaDocente> comentarioEncuestaDocenteList) {
        this.comentarioEncuestaDocenteList = comentarioEncuestaDocenteList;
    }

    /**
     *
     * @return
     */
    public List<ComentarioEncuestaAyudante> getComentarioEncuestaAyudanteList() {
        return comentarioEncuestaAyudanteList;
    }

    /**
     *
     * @param comentarioEncuestaAyudanteList
     */
    public void setComentarioEncuestaAyudanteList(List<ComentarioEncuestaAyudante> comentarioEncuestaAyudanteList) {
        this.comentarioEncuestaAyudanteList = comentarioEncuestaAyudanteList;
    }

    /**
     *
     * @return
     */
    public List<ActaConsultaSupport> getActaConsultaSupportList() {
        return actaConsultaSupportList;
    }

    /**
     *
     * @param actaConsultaSupportList
     */
    public void setActaConsultaSupportList(List<ActaConsultaSupport> actaConsultaSupportList) {
        this.actaConsultaSupportList = actaConsultaSupportList;
    }

    /**
     *
     * @return
     */
    public MiCarreraSupport getMiCarreraSupport() {
        return miCarreraSupport;
    }

    /**
     *
     * @param miCarreraSupport
     */
    public void setMiCarreraSupport(MiCarreraSupport miCarreraSupport) {
        this.miCarreraSupport = miCarreraSupport;
    }

    /**
     *
     * @return
     */
    public List<MiCarreraSupport> getMiCarreraSupportList() {
        return miCarreraSupportList;
    }

    /**
     *
     * @param miCarreraSupportList
     */
    public void setMiCarreraSupportList(List<MiCarreraSupport> miCarreraSupportList) {
        this.miCarreraSupportList = miCarreraSupportList;
    }

    /**
     *
     * @return
     */
    public List<Comuna> getComunaList() {
        return comunaList;
    }

    /**
     *
     * @param comunaList
     */
    public void setComunaList(List<Comuna> comunaList) {
        this.comunaList = comunaList;
    }

    /**
     *
     * @return
     */
    public Mensaje getCurrentMsg() {
        return currentMsg;
    }

    /**
     *
     * @param currentMsg
     */
    public void setCurrentMsg(Mensaje currentMsg) {
        this.currentMsg = currentMsg;
    }

    /**
     *
     * @return
     */
    public Curso getCurso() {
        return curso;
    }

    /**
     *
     * @param curso
     */
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    /**
     *
     * @return
     */
    public List<Curso> getCursoList() {
        return cursoList;
    }

    /**
     *
     * @param cursoList
     */
    public void setCursoList(List<Curso> cursoList) {
        this.cursoList = cursoList;
    }

    /**
     *
     * @return
     */
    public boolean isCursoPregrado() {
        return cursoPregrado;
    }

    /**
     *
     * @param cursoPregrado
     */
    public void setCursoPregrado(boolean cursoPregrado) {
        this.cursoPregrado = cursoPregrado;
    }

    /**
     *
     * @return
     */
    public CursoAyudante getCursoAyudante() {
        return cursoAyudante;
    }

    /**
     *
     * @param cursoAyudante
     */
    public void setCursoAyudante(CursoAyudante cursoAyudante) {
        this.cursoAyudante = cursoAyudante;
    }

    /**
     *
     * @return
     */
    public CursoProfesor getCursoProfesor() {
        return cursoProfesor;
    }

    /**
     *
     * @param cursoProfesor
     */
    public void setCursoProfesor(CursoProfesor cursoProfesor) {
        this.cursoProfesor = cursoProfesor;
    }

    /**
     *
     * @return
     */
    public boolean isCursoPrograma() {
        return cursoPrograma;
    }

    /**
     *
     * @param cursoPrograma
     */
    public void setCursoPrograma(boolean cursoPrograma) {
        this.cursoPrograma = cursoPrograma;
    }

    /**
     *
     * @return
     */
    public List<CursoTevaluacion> getCursoTevaluacion() {
        return cursoTevaluacion;
    }

    /**
     *
     * @param cursoTevaluacion
     */
    public void setCursoTevaluacion(List<CursoTevaluacion> cursoTevaluacion) {
        this.cursoTevaluacion = cursoTevaluacion;
    }

    /**
     *
     * @return
     */
    public List<CursoAyudante> getCursosEncuestaAyudante() {
        return cursosEncuestaAyudante;
    }

    /**
     *
     * @param cursosEncuestaAyudante
     */
    public void setCursosEncuestaAyudante(List<CursoAyudante> cursosEncuestaAyudante) {
        this.cursosEncuestaAyudante = cursosEncuestaAyudante;
    }

    /**
     *
     * @return
     */
    public Derecho getDerecho() {
        return derecho;
    }

    /**
     *
     * @param derecho
     */
    public void setDerecho(Derecho derecho) {
        this.derecho = derecho;
    }

    /**
     *
     * @return
     */
    public DerechoCoordinadorSupport getDerechoCoordinador() {
        return derechoCoordinador;
    }

    /**
     *
     * @param derechoCoordinador
     */
    public void setDerechoCoordinador(DerechoCoordinadorSupport derechoCoordinador) {
        this.derechoCoordinador = derechoCoordinador;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    /**
     *
     * @return
     */
    public Empleador getEmpleador() {
        return empleador;
    }

    /**
     *
     * @param empleador
     */
    public void setEmpleador(Empleador empleador) {
        this.empleador = empleador;
    }

    /**
     *
     * @return
     */
    public List<Empleador> getEmpleadorList() {
        return empleadorList;
    }

    /**
     *
     * @param empleadorList
     */
    public void setEmpleadorList(List<Empleador> empleadorList) {
        this.empleadorList = empleadorList;
    }

    /**
     *
     * @return
     */
    public EncuestaDocente getEncuestaDocente() {
        return encuestaDocente;
    }

    /**
     *
     * @param encuestaDocente
     */
    public void setEncuestaDocente(EncuestaDocente encuestaDocente) {
        this.encuestaDocente = encuestaDocente;
    }

    /**
     *
     * @return
     */
    public EncuestaAyudante getEncuestaAyudante() {
        return encuestaAyudante;
    }

    /**
     *
     * @param encuestaAyudante
     */
    public void setEncuestaAyudante(EncuestaAyudante encuestaAyudante) {
        this.encuestaAyudante = encuestaAyudante;
    }

    /**
     *
     * @return
     */
    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    /**
     *
     * @param evaluacion
     */
    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    /**
     *
     * @return
     */
    public List<EvaluacionAlumno> getEvaluacionAlumno() {
        return evaluacionAlumno;
    }

    /**
     *
     * @param evaluacionAlumno
     */
    public void setEvaluacionAlumno(List<EvaluacionAlumno> evaluacionAlumno) {
        this.evaluacionAlumno = evaluacionAlumno;
    }

    /**
     *
     * @return
     */
    public List<Evaluacion> getEvaluacionList() {
        return evaluacionList;
    }

    /**
     *
     * @param evaluacionList
     */
    public void setEvaluacionList(List<Evaluacion> evaluacionList) {
        this.evaluacionList = evaluacionList;
    }

    public ExpedienteLogro getExpedienteLogro() {
        return expedienteLogro;
    }

    public void setExpedienteLogro(ExpedienteLogro expedienteLogro) {
        this.expedienteLogro = expedienteLogro;
    }

    /**
     *
     * @return
     */
    public List<ExpedienteLogro> getExpedienteLogroList() {
        return expedienteLogroList;
    }

    /**
     *
     * @param expedienteLogroList
     */
    public void setExpedienteLogroList(List<ExpedienteLogro> expedienteLogroList) {
        this.expedienteLogroList = expedienteLogroList;
    }

    /**
     *
     * @return
     */
    public String getFechaActual() {
        return fechaActual;
    }

    /**
     *
     * @param fechaActual
     */
    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }

    public List<ModuloHorario> getModuloHorarioList() {
        return moduloHorarioList;
    }

    public void setModuloHorarioList(List<ModuloHorario> moduloHorarioList) {
        this.moduloHorarioList = moduloHorarioList;
    }

    /**
     * Method description
     *
     * @return a defensive copy of the field. The caller may change the state of
     * the returned object in any way, without affecting the internals of this
     * class.
     */
    public Horario[][] getHorario() {
        return copyOf(horario, horario.length);
    }

    /**
     * Method description
     *
     * @param horario
     */
    public void setHorario(Horario[][] horario) {
        this.horario = copyOf(horario, horario.length);
    }

    /**
     *
     * @return
     */
    public List<InscripcionAdicionalLogro> getInscripcionAdicionalLogroList() {
        return inscripcionRequisitoAdicionalLogroList;
    }

    /**
     *
     * @param inscripcionRequisitoAdicionalLogroList
     */
    public void setInscripcionAdicionalLogroList(List<InscripcionAdicionalLogro> inscripcionRequisitoAdicionalLogroList) {
        this.inscripcionRequisitoAdicionalLogroList = inscripcionRequisitoAdicionalLogroList;
    }

    /**
     *
     * @return
     */
    public InscripcionAdicionalLogro getInscripcionAdicionalLogro() {
        return inscripcionRequisitoAdicionalLogro;
    }

    /**
     *
     * @param inscripcionRequisitoAdicionalLogro
     */
    public void setInscripcionAdicionalLogro(InscripcionAdicionalLogro inscripcionRequisitoAdicionalLogro) {
        this.inscripcionRequisitoAdicionalLogro = inscripcionRequisitoAdicionalLogro;
    }

    /**
     *
     * @return
     */
    public String getKeyParent() {
        return keyParent;
    }

    /**
     *
     * @param keyParent
     */
    public void setKeyParent(String keyParent) {
        this.keyParent = keyParent;
    }

    /**
     *
     * @return
     */
    public LogCertificacion getLogCertificacion() {
        return logCertificacion;
    }

    /**
     *
     * @param logCertificacion
     */
    public void setLogCertificacion(LogCertificacion logCertificacion) {
        this.logCertificacion = logCertificacion;
    }

    /**
     *
     * @return
     */
    public List<LogInscripcion> getLogInscripcionList() {
        return logInscripcionList;
    }

    /**
     *
     * @param logInscripcionList
     */
    public void setLogInscripcionList(List<LogInscripcion> logInscripcionList) {
        this.logInscripcionList = logInscripcionList;
    }

    /**
     *
     * @return
     */
    public MaterialApoyo getMaterial() {
        return material;
    }

    /**
     *
     * @param material
     */
    public void setMaterial(MaterialApoyo material) {
        this.material = material;
    }

    /**
     *
     * @return
     */
    public MencionInfoIntranet getMencionInfoIntranet() {
        return mencionInfoIntranet;
    }

    /**
     *
     * @param mencionInfoIntranet
     */
    public void setMencionInfoIntranet(MencionInfoIntranet mencionInfoIntranet) {
        this.mencionInfoIntranet = mencionInfoIntranet;
    }    

    /**
     *
     * @return
     */
    public List<Mencion> getMencionList() {
        return mencionList;
    }

    /**
     *
     * @param mencionList
     */
    public void setMencionList(List<Mencion> mencionList) {
        this.mencionList = mencionList;
    }

    /**
     *
     * @return
     */
    public MensajeSupport getMensajeSupport() {
        return mensajeSupport;
    }

    /**
     *
     * @param mensajeSupport
     */
    public void setMensajeSupport(MensajeSupport mensajeSupport) {
        this.mensajeSupport = mensajeSupport;
    }

    /**
     *
     * @return
     */
    public Mensaje getMensajeFwd() {
        return mensajeFwd;
    }

    /**
     *
     * @param mensajeFwd
     */
    public void setMensajeFwd(Mensaje mensajeFwd) {
        this.mensajeFwd = mensajeFwd;
    }

    /**
     *
     * @return
     */
    public List<String> getModulos() {
        return modulos;
    }

    /**
     *
     * @param modulos
     */
    public void setModulos(List<String> modulos) {
        this.modulos = modulos;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public String getNombreCarrera() {
        return nombreCarrera;
    }

    /**
     *
     * @param nombreCarrera
     */
    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    /**
     *
     * @return
     */
    public List<ActaNominaView> getNominaActa() {
        return nominaActa;
    }

    /**
     *
     * @param nominaActa
     */
    public void setNominaActa(List<ActaNominaView> nominaActa) {
        this.nominaActa = nominaActa;
    }

    public List<AluCar> getNominaCurso() {
        return nominaCurso;
    }

    public void setNominaCurso(List<AluCar> nominaCurso) {
        this.nominaCurso = nominaCurso;
    }

    /**
     *
     * @return
     */
    public Long getNuevosMensajes() {
        return nuevosMensajes;
    }

    /**
     *
     * @param nuevosMensajes
     */
    public void setNuevosMensajes(Long nuevosMensajes) {
        this.nuevosMensajes = nuevosMensajes;
    }

    /**
     *
     * @return
     */
    public List<Tmaterial> getOtrosTmaterial() {
        return otrosTmaterial;
    }

    /**
     *
     * @param otrosTmaterial
     */
    public void setOtrosTmaterial(List<Tmaterial> otrosTmaterial) {
        this.otrosTmaterial = otrosTmaterial;
    }

    /**
     *
     * @return
     */
    public Practica getPractica() {
        return practica;
    }

    /**
     *
     * @param practica
     */
    public void setPractica(Practica practica) {
        this.practica = practica;
    }

    /**
     *
     * @return
     */
    public List<Practica> getPracticaList() {
        return practicaList;
    }

    /**
     *
     * @param practicaList
     */
    public void setPracticaList(List<Practica> practicaList) {
        this.practicaList = practicaList;
    }

    /**
     *
     * @return
     */
    public List<PregEncta> getPreguntasEncuesta() {
        return preguntasEncuesta;
    }

    /**
     *
     * @param preguntasEncuesta
     */
    public void setPreguntasEncuesta(List<PregEncta> preguntasEncuesta) {
        this.preguntasEncuesta = preguntasEncuesta;
    }

    /**
     *
     * @return
     */
    public Profesor getProfesor() {
        return profesor;
    }

    /**
     *
     * @param profesor
     */
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    /**
     *
     * @return
     */
    public List<Profesor> getProfesorList() {
        return profesorList;
    }

    /**
     *
     * @param profesorList
     */
    public void setProfesorList(List<Profesor> profesorList) {
        this.profesorList = profesorList;
    }

    /**
     *
     * @return
     */
    public boolean isPuedeEmitir() {
        return puedeEmitir;
    }

    /**
     *
     * @param puedeEmitir
     */
    public void setPuedeEmitir(boolean puedeEmitir) {
        this.puedeEmitir = puedeEmitir;
    }

    /**
     *
     * @return
     */
    public List<MensajeDestinatario> getReceivedMsgs() {
        return receivedMsgs;
    }

    /**
     *
     * @param receivedMsgs
     */
    public void setReceivedMsgs(List<MensajeDestinatario> receivedMsgs) {
        this.receivedMsgs = receivedMsgs;
    }

    /**
     *
     * @return
     */
    public ReporteClase getReporte() {
        return reporte;
    }

    /**
     *
     * @param reporte
     */
    public void setReporte(ReporteClase reporte) {
        this.reporte = reporte;
    }

    /**
     *
     * @return
     */
    public List<ReporteClase> getReportes() {
        return reportes;
    }

    /**
     *
     * @param reportes
     */
    public void setReportes(List<ReporteClase> reportes) {
        this.reportes = reportes;
    }

    public TrequisitoLogroAdicional getTrequisitoLogroAdicional() {
        return trequisitoLogroAdicional;
    }

    public void setTrequisitoLogroAdicional(TrequisitoLogroAdicional trequisitoLogroAdicional) {
        this.trequisitoLogroAdicional = trequisitoLogroAdicional;
    }

    public List<TrequisitoLogroAdicional> getTrequisitoLogroAdicionalList() {
        return trequisitoLogroAdicionalList;
    }

    public void setTrequisitoLogroAdicionalList(List<TrequisitoLogroAdicional> trequisitoLogroAdicionalList) {
        this.trequisitoLogroAdicionalList = trequisitoLogroAdicionalList;
    }

    /**
     *
     * @return
     */
    public List<RespEnctaCursoView> getRespEncta() {
        return respEncta;
    }

    /**
     *
     * @param respEncta
     */
    public void setRespEncta(List<RespEnctaCursoView> respEncta) {
        this.respEncta = respEncta;
    }

    /**
     *
     * @return
     */
    public List<CursoResumenSupport> getResumenCurso() {
        return resumenCurso;
    }

    /**
     *
     * @param resumenCurso
     */
    public void setResumenCurso(List<CursoResumenSupport> resumenCurso) {
        this.resumenCurso = resumenCurso;
    }

    /**
     *
     * @return
     */
    public Sala getSala() {
        return sala;
    }

    /**
     *
     * @param sala
     */
    public void setSala(Sala sala) {
        this.sala = sala;
    }

    /**
     *
     * @return
     */
    public List<Sala> getSalaList() {
        return salaList;
    }

    /**
     *
     * @param salaList
     */
    public void setSalaList(List<Sala> salaList) {
        this.salaList = salaList;
    }

    /**
     *
     * @return
     */
    public Integer getSemAct() {
        return semAct;
    }

    /**
     *
     * @param semAct
     */
    public void setSemAct(Integer semAct) {
        this.semAct = semAct;
    }

    /**
     *
     * @return
     */
    public List<Mensaje> getSentMsgs() {
        return sentMsgs;
    }

    /**
     *
     * @param sentMsgs
     */
    public void setSentMsgs(List<Mensaje> sentMsgs) {
        this.sentMsgs = sentMsgs;
    }

    /**
     *
     * @return
     */
    public Solicitud getSolicitud() {
        return solicitud;
    }

    /**
     *
     * @param solicitud
     */
    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    /**
     *
     * @return
     */
    public List<Solicitud> getSolicitudList() {
        return solicitudList;
    }

    /**
     *
     * @param solicitudList
     */
    public void setSolicitudList(List<Solicitud> solicitudList) {
        this.solicitudList = solicitudList;
    }

    public List<SolicitudInscripcion> getSolicitudInscripcionList() {
        return solicitudInscripcionList;
    }

    public void setSolicitudInscripcionList(List<SolicitudInscripcion> solicitudInscripcionList) {
        this.solicitudInscripcionList = solicitudInscripcionList;
    }

    /**
     *
     * @return
     */
    public List<TdocumentoSolicitud> getTdocumentoSolicitudList() {
        return tdocumentoSolicitudList;
    }

    /**
     *
     * @param tdocumentoSolicitudList
     */
    public void setTdocumentoSolicitudList(List<TdocumentoSolicitud> tdocumentoSolicitudList) {
        this.tdocumentoSolicitudList = tdocumentoSolicitudList;
    }

    /**
     *
     * @return
     */
    public List<Tevaluacion> getTevaluacion() {
        return tevaluacion;
    }

    /**
     *
     * @param tevaluacion
     */
    public void setTevaluacion(List<Tevaluacion> tevaluacion) {
        this.tevaluacion = tevaluacion;
    }

    /**
     *
     * @return
     */
    public String getTipoCalificacion() {
        return tipoCalificacion;
    }

    /**
     *
     * @param tipoCalificacion
     */
    public void setTipoCalificacion(String tipoCalificacion) {
        this.tipoCalificacion = tipoCalificacion;
    }

    /**
     *
     * @return
     */
    public String getTipoMaterial() {
        return tipoMaterial;
    }

    /**
     *
     * @param tipoMaterial
     */
    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    /**
     *
     * @return
     */
    public List<Tmaterial> getTmaterial() {
        return tmaterial;
    }

    /**
     *
     * @param tmaterial
     */
    public void setTmaterial(List<Tmaterial> tmaterial) {
        this.tmaterial = tmaterial;
    }

    /**
     *
     * @return
     */
    public List<Tmaterial> getTmaterialSelectOption() {
        return tmaterialSelectOption;
    }

    /**
     *
     * @param tmaterialSelectOption
     */
    public void setTmaterialSelectOption(List<Tmaterial> tmaterialSelectOption) {
        this.tmaterialSelectOption = tmaterialSelectOption;
    }

    /**
     *
     * @return
     */
    public List<Tsolicitud> getTsolicitudList() {
        return tsolicitudList;
    }

    /**
     *
     * @param tsolicitudList
     */
    public void setTsolicitudList(List<Tsolicitud> tsolicitudList) {
        this.tsolicitudList = tsolicitudList;
    }

    /**
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     */
    public String getTypeSearch() {
        return typeSearch;
    }

    /**
     *
     * @param typeSearch
     */
    public void setTypeSearch(String typeSearch) {
        this.typeSearch = typeSearch;
    }

    /*
     *
     *
     */
    /**
     *
     * @return
     */
    public List<Curso> getCarga() {
        return this.cursoList;
    }

    /**
     *
     * @param correl
     * @param rut
     * @return
     */
    public boolean asisteClases(Integer correl, Integer rut) {
        return asiste(this, correl, rut);
    }

    /**
     *
     * @return
     */
    public List<LogSolicitud> getLogSolicitudList() {
        return logSolicitudList;
    }

    /**
     *
     * @param logSolicitudList
     */
    public void setLogSolicitudList(List<LogSolicitud> logSolicitudList) {
        this.logSolicitudList = logSolicitudList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CursoEspejo> getCargaEspejo() {
        return cargaEspejo;
    }

    public void setCargaEspejo(List<CursoEspejo> cargaEspejo) {
        this.cargaEspejo = cargaEspejo;
    }

    public ReservaSalaSupport[][] getHorarioSala() {
        return horarioSala;
    }

    public void setHorarioSala(ReservaSalaSupport[][] horarioSala) {
        this.horarioSala = horarioSala;
    }

    public ReservaSala getReserva() {
        return reserva;
    }

    public void setReserva(ReservaSala reserva) {
        this.reserva = reserva;
    }

    public List<Malla> getMallaList() {
        return mallaList;
    }

    public void setMallaList(List<Malla> mallaList) {
        this.mallaList = mallaList;
    }

    public List<RespEnctaAyuCursoView> getRespEnctaAyu() {
        return respEnctaAyu;
    }

    public void setRespEnctaAyu(List<RespEnctaAyuCursoView> respEnctaAyu) {
        this.respEnctaAyu = respEnctaAyu;
    }

   /* public Justificativo getJustificativo() {
        return justificativo;
    }

    public void setJustificativo(Justificativo justificativo) {
        this.justificativo = justificativo;
    }

    public List<Justificativo> getJustificativoList() {
        return justificativoList;
    }

    public void setJustificativoList(List<Justificativo> justificativoList) {
        this.justificativoList = justificativoList;
    }*/

    public String getPdfTempFile() {
        return pdfTempFile;
    }

    public void setPdfTempFile(String pdfTempFile) {
        this.pdfTempFile = pdfTempFile;
    }

    public List<Reincorporacion> getReincorporacionList() {
        return reincorporacionList;
    }

    public void setReincorporacionList(List<Reincorporacion> reincorporacionList) {
        this.reincorporacionList = reincorporacionList;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<PreguntaAutoEvaluacion> getPreguntasAutoevaluacionList() {
        return preguntasAutoevaluacionList;
    }

    public void setPreguntasAutoevaluacionList(List<PreguntaAutoEvaluacion> preguntasAutoevaluacionList) {
        this.preguntasAutoevaluacionList = preguntasAutoevaluacionList;
    }

    public List<Horario> getHorarioList() {
        return horarioList;
    }

    public void setHorarioList(List<Horario> horarioList) {
        this.horarioList = horarioList;
    }

    public List<CursoProfesor> getCursoProfesorList() {
        return cursoProfesorList;
    }

    public void setCursoProfesorList(List<CursoProfesor> cursoProfesorList) {
        this.cursoProfesorList = cursoProfesorList;
    }

    public List<CursoAyudante> getCursoAyudanteList() {
        return cursoAyudanteList;
    }

    public void setCursoAyudanteList(List<CursoAyudante> cursoAyudanteList) {
        this.cursoAyudanteList = cursoAyudanteList;
    }

    public List<CursoProfesor> getCursosAutoEvaluacion() {
        return cursosAutoEvaluacion;
    }

    public void setCursosAutoEvaluacion(List<CursoProfesor> cursosAutoEvaluacion) {
        this.cursosAutoEvaluacion = cursosAutoEvaluacion;
    }

    public ActionResultSupport getActionSupport() {
        return actionSupport;
    }

    public void setActionSupport(ActionResultSupport actionSupport) {
        this.actionSupport = actionSupport;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public List<CursoEspejo> getCursoEspejoList() {
        return cursoEspejoList;
    }

    public void setCursoEspejoList(List<CursoEspejo> cursoEspejoList) {
        this.cursoEspejoList = cursoEspejoList;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Funcionario> getFuncionarioList() {
        return funcionarioList;
    }

    public void setFuncionarioList(List<Funcionario> funcionarioList) {
        this.funcionarioList = funcionarioList;
    }

    public List<Curso> getCursoSolicitudList() {
        return cursoSolicitudList;
    }

    public void setCursoSolicitudList(List<Curso> cursoSolicitudList) {
        this.cursoSolicitudList = cursoSolicitudList;
    }

    public List<Curso> getCursoTransversalList() {
        return cursoTransversalList;
    }

    public void setCursoTransversalList(List<Curso> cursoTransversalList) {
        this.cursoTransversalList = cursoTransversalList;
    }

    public List<Calificacion> getActaRectificatoriaList() {
        return actaRectificatoriaList;
    }

    public void setActaRectificatoriaList(List<Calificacion> actaRectificatoriaList) {
        this.actaRectificatoriaList = actaRectificatoriaList;
    }

    public List<TmotivoSolicitudInscripcion> getTmotivoSolicitudInscripcionList() {
        return ContextUtil.getTmotivoSolicitudInscripcion();
    }

    public List<Electivo> getElectivoList() {
        return electivoList;
    }

    public void setElectivoList(List<Electivo> electivoList) {
        this.electivoList = electivoList;
    }

    public List<MatriculaHistorico> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<MatriculaHistorico> matriculaList) {
        this.matriculaList = matriculaList;
    }    

    public List<Tramite> getTramites() {
        return tramites;
    }

    public void setTramites(List<Tramite> tramites) {
        this.tramites = tramites;
    }

    public List<ReservaSala> getReservaList() {
        return reservaList;
    }

    public void setReservaList(List<ReservaSala> reservaList) {
        this.reservaList = reservaList;
    }

    public int getCantMsgsReceived() {
        return cantMsgsReceived;
    }

    public void setCantMsgsReceived(int cantMsgsReceived) {
        this.cantMsgsReceived = cantMsgsReceived;
    }

    public int getCantMsgsReceivedFiltered() {
        return cantMsgsReceivedFiltered;
    }

    public void setCantMsgsReceivedFiltered(int cantMsgsReceivedFiltered) {
        this.cantMsgsReceivedFiltered = cantMsgsReceivedFiltered;
    }

    public int getCantMsgsSended() {
        return cantMsgsSended;
    }

    public void setCantMsgsSended(int cantMsgsSended) {
        this.cantMsgsSended = cantMsgsSended;
    }

    public int getCantMsgsSendedFiltered() {
        return cantMsgsSendedFiltered;
    }

    public void setCantMsgsSendedFiltered(int cantMsgsSendedFiltered) {
        this.cantMsgsSendedFiltered = cantMsgsSendedFiltered;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(String fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public List<DocenteHorario> getDocenteHorarioList() {
        return docenteHorarioList;
    }

    public void setDocenteHorarioList(List<DocenteHorario> docenteHorarioList) {
        this.docenteHorarioList = docenteHorarioList;
    }

    public String getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(String idHorario) {
        this.idHorario = idHorario;
    }

    public List<Unidad> getUnidadList() {
        return unidadList;
    }

    public void setUnidadList(List<Unidad> unidadList) {
        this.unidadList = unidadList;
    }

    public List<SolicitudJustificativo> getJustificativoList() {
        return justificativoList;
    }

    public void setJustificativoList(List<SolicitudJustificativo> justificativoList) {
        this.justificativoList = justificativoList;
    }
}
