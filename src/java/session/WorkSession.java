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
import domain.model.EstadoDocExp;

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

    private EstadoDocExp estadoDocExp;
    private List<EstadoDocExp> estadoDocExpList;
    /**
     *
     * @param type
     */
    public WorkSession(String type) {
        this.type = type;
        this.nuevosMensajes = 0L;
    }

    
    public List<ActaCalificacion> getActas() {
        return actas;
    }


    public void setActas(List<ActaCalificacion> actas) {
        this.actas = actas;
    }

    public String getActionCall() {
        return actionCall;
    }


    public void setActionCall(String actionCall) {
        this.actionCall = actionCall;
    }


    public String getActionNested() {
        return actionNested;
    }


    public void setActionNested(String actionNested) {
        this.actionNested = actionNested;
    }

 
    public Integer getAgnoAct() {
        return agnoAct;
    }

    public void setAgnoAct(Integer agnoAct) {
        this.agnoAct = agnoAct;
    }

    
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

    
    public List<AluCar> getAluCarList() {
        return aluCarList;
    }

    public void setAluCarList(List<AluCar> aluCarList) {
        this.aluCarList = aluCarList;
    }

    
    public Alumno getAlumno() {
        return alumno;
    }


    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
    
    public List<Alumno> getAlumnoList() {
        return alumnoList;
    }

    public void setAlumnoList(List<Alumno> alumnoList) {
        this.alumnoList = alumnoList;
    }

    
    public List<Asignatura> getAsignaturaList() {
        return asignaturaList;
    }

    public void setAsignaturaList(List<Asignatura> asignaturaList) {
        this.asignaturaList = asignaturaList;
    }

    public AsistenciaAlumno getAsistenciaAlumno() {
        return asistenciaAlumno;
    }

    public void setAsistenciaAlumno(AsistenciaAlumno asistenciaAlumno) {
        this.asistenciaAlumno = asistenciaAlumno;
    }

    public List<AsistenciaAlumno> getAsistenciaAlumnoList() {
        return asistenciaAlumnoList;
    }

    public void setAsistenciaAlumnoList(List<AsistenciaAlumno> asistenciaAlumnoList) {
        this.asistenciaAlumnoList = asistenciaAlumnoList;
    }
    
    public List<AsistenciaAlumnoNomina> getAsistenciaAlumnoNominaList() {
        return asistenciaAlumnoNominaList;
    }

    public void setAsistenciaAlumnoNominaList(List<AsistenciaAlumnoNomina> asistenciaAlumnoNominaList) {
        this.asistenciaAlumnoNominaList = asistenciaAlumnoNominaList;
    }
    
    public Ayudante getAyudante() {
        return ayudante;
    }

    public void setAyudante(Ayudante ayudante) {
        this.ayudante = ayudante;
    }
    
    public List<Ayudante> getAyudanteList() {
        return ayudanteList;
    }

    public void setAyudanteList(List<Ayudante> ayudanteList) {
        this.ayudanteList = ayudanteList;
    }
    
    public boolean isBlinkMensajes() {
        return blinkMensajes;
    }

    public void setBlinkMensajes(boolean blinkMensajes) {
        this.blinkMensajes = blinkMensajes;
    }
    
    public List<CalificacionAdicionalLogroxInscribirSupport> getCalificacionRequisitoAdicionalLogroxInscribirList() {
        return calificacionRequisitoAdicionalLogroxInscribirList;
    }

    public void setCalificacionRequisitoAdicionalLogroxInscribirList(List<CalificacionAdicionalLogroxInscribirSupport> calificacionRequisitoAdicionalLogroxInscribirList) {
        this.calificacionRequisitoAdicionalLogroxInscribirList = calificacionRequisitoAdicionalLogroxInscribirList;
    }
    
    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }
    
    public Ccalidad getCcalidad() {
        return ccalidad;
    }

    public void setCcalidad(Ccalidad ccalidad) {
        this.ccalidad = ccalidad;
    }
    
    public List<ComentarioEncuestaDocente> getComentarioEncuestaDocenteList() {
        return comentarioEncuestaDocenteList;
    }

    public void setComentarioEncuestaDocenteList(List<ComentarioEncuestaDocente> comentarioEncuestaDocenteList) {
        this.comentarioEncuestaDocenteList = comentarioEncuestaDocenteList;
    }
    
    public List<ComentarioEncuestaAyudante> getComentarioEncuestaAyudanteList() {
        return comentarioEncuestaAyudanteList;
    }

    public void setComentarioEncuestaAyudanteList(List<ComentarioEncuestaAyudante> comentarioEncuestaAyudanteList) {
        this.comentarioEncuestaAyudanteList = comentarioEncuestaAyudanteList;
    }
    
    public List<ActaConsultaSupport> getActaConsultaSupportList() {
        return actaConsultaSupportList;
    }

    public void setActaConsultaSupportList(List<ActaConsultaSupport> actaConsultaSupportList) {
        this.actaConsultaSupportList = actaConsultaSupportList;
    }
    
    public MiCarreraSupport getMiCarreraSupport() {
        return miCarreraSupport;
    }

    public void setMiCarreraSupport(MiCarreraSupport miCarreraSupport) {
        this.miCarreraSupport = miCarreraSupport;
    }
    
    public List<MiCarreraSupport> getMiCarreraSupportList() {
        return miCarreraSupportList;
    }

    public void setMiCarreraSupportList(List<MiCarreraSupport> miCarreraSupportList) {
        this.miCarreraSupportList = miCarreraSupportList;
    }
    
    public List<Comuna> getComunaList() {
        return comunaList;
    }

    public void setComunaList(List<Comuna> comunaList) {
        this.comunaList = comunaList;
    }
    
    public Mensaje getCurrentMsg() {
        return currentMsg;
    }

    public void setCurrentMsg(Mensaje currentMsg) {
        this.currentMsg = currentMsg;
    }
    
    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    public List<Curso> getCursoList() {
        return cursoList;
    }

    public void setCursoList(List<Curso> cursoList) {
        this.cursoList = cursoList;
    }
    
    public boolean isCursoPregrado() {
        return cursoPregrado;
    }

    public void setCursoPregrado(boolean cursoPregrado) {
        this.cursoPregrado = cursoPregrado;
    }
    
    public CursoAyudante getCursoAyudante() {
        return cursoAyudante;
    }

    public void setCursoAyudante(CursoAyudante cursoAyudante) {
        this.cursoAyudante = cursoAyudante;
    }
    
    public CursoProfesor getCursoProfesor() {
        return cursoProfesor;
    }

    public void setCursoProfesor(CursoProfesor cursoProfesor) {
        this.cursoProfesor = cursoProfesor;
    }
    
    public boolean isCursoPrograma() {
        return cursoPrograma;
    }

    public void setCursoPrograma(boolean cursoPrograma) {
        this.cursoPrograma = cursoPrograma;
    }
    
    public List<CursoTevaluacion> getCursoTevaluacion() {
        return cursoTevaluacion;
    }

    public void setCursoTevaluacion(List<CursoTevaluacion> cursoTevaluacion) {
        this.cursoTevaluacion = cursoTevaluacion;
    }
    
    public List<CursoAyudante> getCursosEncuestaAyudante() {
        return cursosEncuestaAyudante;
    }

    public void setCursosEncuestaAyudante(List<CursoAyudante> cursosEncuestaAyudante) {
        this.cursosEncuestaAyudante = cursosEncuestaAyudante;
    }
    
    public Derecho getDerecho() {
        return derecho;
    }

    public void setDerecho(Derecho derecho) {
        this.derecho = derecho;
    }
    
    public DerechoCoordinadorSupport getDerechoCoordinador() {
        return derechoCoordinador;
    }

    public void setDerechoCoordinador(DerechoCoordinadorSupport derechoCoordinador) {
        this.derechoCoordinador = derechoCoordinador;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    
    public Empleador getEmpleador() {
        return empleador;
    }

    public void setEmpleador(Empleador empleador) {
        this.empleador = empleador;
    }

    
    public List<Empleador> getEmpleadorList() {
        return empleadorList;
    }

    public void setEmpleadorList(List<Empleador> empleadorList) {
        this.empleadorList = empleadorList;
    }
    
    public EncuestaDocente getEncuestaDocente() {
        return encuestaDocente;
    }

    public void setEncuestaDocente(EncuestaDocente encuestaDocente) {
        this.encuestaDocente = encuestaDocente;
    }

    
    public EncuestaAyudante getEncuestaAyudante() {
        return encuestaAyudante;
    }

    public void setEncuestaAyudante(EncuestaAyudante encuestaAyudante) {
        this.encuestaAyudante = encuestaAyudante;
    }
    
    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }
    
    public List<EvaluacionAlumno> getEvaluacionAlumno() {
        return evaluacionAlumno;
    }

    public void setEvaluacionAlumno(List<EvaluacionAlumno> evaluacionAlumno) {
        this.evaluacionAlumno = evaluacionAlumno;
    }
    
    public List<Evaluacion> getEvaluacionList() {
        return evaluacionList;
    }

    public void setEvaluacionList(List<Evaluacion> evaluacionList) {
        this.evaluacionList = evaluacionList;
    }

    public ExpedienteLogro getExpedienteLogro() {
        return expedienteLogro;
    }

    public void setExpedienteLogro(ExpedienteLogro expedienteLogro) {
        this.expedienteLogro = expedienteLogro;
    }
    
    public List<ExpedienteLogro> getExpedienteLogroList() {
        return expedienteLogroList;
    }

    public void setExpedienteLogroList(List<ExpedienteLogro> expedienteLogroList) {
        this.expedienteLogroList = expedienteLogroList;
    }
    
    public String getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }

    public List<ModuloHorario> getModuloHorarioList() {
        return moduloHorarioList;
    }

    public void setModuloHorarioList(List<ModuloHorario> moduloHorarioList) {
        this.moduloHorarioList = moduloHorarioList;
    }

    public Horario[][] getHorario() {
        return copyOf(horario, horario.length);
    }

    public void setHorario(Horario[][] horario) {
        this.horario = copyOf(horario, horario.length);
    }
    
    public List<InscripcionAdicionalLogro> getInscripcionAdicionalLogroList() {
        return inscripcionRequisitoAdicionalLogroList;
    }

    public void setInscripcionAdicionalLogroList(List<InscripcionAdicionalLogro> inscripcionRequisitoAdicionalLogroList) {
        this.inscripcionRequisitoAdicionalLogroList = inscripcionRequisitoAdicionalLogroList;
    }
    
    public InscripcionAdicionalLogro getInscripcionAdicionalLogro() {
        return inscripcionRequisitoAdicionalLogro;
    }

    public void setInscripcionAdicionalLogro(InscripcionAdicionalLogro inscripcionRequisitoAdicionalLogro) {
        this.inscripcionRequisitoAdicionalLogro = inscripcionRequisitoAdicionalLogro;
    }
    
    public String getKeyParent() {
        return keyParent;
    }

    public void setKeyParent(String keyParent) {
        this.keyParent = keyParent;
    }
    
    public LogCertificacion getLogCertificacion() {
        return logCertificacion;
    }

    public void setLogCertificacion(LogCertificacion logCertificacion) {
        this.logCertificacion = logCertificacion;
    }
    
    public List<LogInscripcion> getLogInscripcionList() {
        return logInscripcionList;
    }

    public void setLogInscripcionList(List<LogInscripcion> logInscripcionList) {
        this.logInscripcionList = logInscripcionList;
    }
    
    public MaterialApoyo getMaterial() {
        return material;
    }

    public void setMaterial(MaterialApoyo material) {
        this.material = material;
    }
    
    public MencionInfoIntranet getMencionInfoIntranet() {
        return mencionInfoIntranet;
    }

    public void setMencionInfoIntranet(MencionInfoIntranet mencionInfoIntranet) {
        this.mencionInfoIntranet = mencionInfoIntranet;
    }    
    
    public List<Mencion> getMencionList() {
        return mencionList;
    }

    public void setMencionList(List<Mencion> mencionList) {
        this.mencionList = mencionList;
    }
    
    public MensajeSupport getMensajeSupport() {
        return mensajeSupport;
    }

    public void setMensajeSupport(MensajeSupport mensajeSupport) {
        this.mensajeSupport = mensajeSupport;
    }
    
    public Mensaje getMensajeFwd() {
        return mensajeFwd;
    }

    public void setMensajeFwd(Mensaje mensajeFwd) {
        this.mensajeFwd = mensajeFwd;
    }
    
    public List<String> getModulos() {
        return modulos;
    }

    public void setModulos(List<String> modulos) {
        this.modulos = modulos;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }
    
    public List<ActaNominaView> getNominaActa() {
        return nominaActa;
    }

    public void setNominaActa(List<ActaNominaView> nominaActa) {
        this.nominaActa = nominaActa;
    }

    public List<AluCar> getNominaCurso() {
        return nominaCurso;
    }

    public void setNominaCurso(List<AluCar> nominaCurso) {
        this.nominaCurso = nominaCurso;
    }
    
    public Long getNuevosMensajes() {
        return nuevosMensajes;
    }

    public void setNuevosMensajes(Long nuevosMensajes) {
        this.nuevosMensajes = nuevosMensajes;
    }
    
    public List<Tmaterial> getOtrosTmaterial() {
        return otrosTmaterial;
    }

    public void setOtrosTmaterial(List<Tmaterial> otrosTmaterial) {
        this.otrosTmaterial = otrosTmaterial;
    }
    
    public Practica getPractica() {
        return practica;
    }

    public void setPractica(Practica practica) {
        this.practica = practica;
    }
    
    public List<Practica> getPracticaList() {
        return practicaList;
    }

    public void setPracticaList(List<Practica> practicaList) {
        this.practicaList = practicaList;
    }
    
    public List<PregEncta> getPreguntasEncuesta() {
        return preguntasEncuesta;
    }

    public void setPreguntasEncuesta(List<PregEncta> preguntasEncuesta) {
        this.preguntasEncuesta = preguntasEncuesta;
    }
    
    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
    
    public List<Profesor> getProfesorList() {
        return profesorList;
    }

    public void setProfesorList(List<Profesor> profesorList) {
        this.profesorList = profesorList;
    }
    
    public boolean isPuedeEmitir() {
        return puedeEmitir;
    }

    public void setPuedeEmitir(boolean puedeEmitir) {
        this.puedeEmitir = puedeEmitir;
    }
    
    public List<MensajeDestinatario> getReceivedMsgs() {
        return receivedMsgs;
    }

    public void setReceivedMsgs(List<MensajeDestinatario> receivedMsgs) {
        this.receivedMsgs = receivedMsgs;
    }
    
    public ReporteClase getReporte() {
        return reporte;
    }

    public void setReporte(ReporteClase reporte) {
        this.reporte = reporte;
    }
    
    public List<ReporteClase> getReportes() {
        return reportes;
    }

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
    
    public List<RespEnctaCursoView> getRespEncta() {
        return respEncta;
    }

    public void setRespEncta(List<RespEnctaCursoView> respEncta) {
        this.respEncta = respEncta;
    }
 
    public List<CursoResumenSupport> getResumenCurso() {
        return resumenCurso;
    }
    
    public void setResumenCurso(List<CursoResumenSupport> resumenCurso) {
        this.resumenCurso = resumenCurso;
    }
    
    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
    
    public List<Sala> getSalaList() {
        return salaList;
    }

    public void setSalaList(List<Sala> salaList) {
        this.salaList = salaList;
    }
    
    public Integer getSemAct() {
        return semAct;
    }

    public void setSemAct(Integer semAct) {
        this.semAct = semAct;
    }
    
    public List<Mensaje> getSentMsgs() {
        return sentMsgs;
    }

    public void setSentMsgs(List<Mensaje> sentMsgs) {
        this.sentMsgs = sentMsgs;
    }
    
    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }
    
    public List<Solicitud> getSolicitudList() {
        return solicitudList;
    }

    public void setSolicitudList(List<Solicitud> solicitudList) {
        this.solicitudList = solicitudList;
    }

    public List<SolicitudInscripcion> getSolicitudInscripcionList() {
        return solicitudInscripcionList;
    }

    public void setSolicitudInscripcionList(List<SolicitudInscripcion> solicitudInscripcionList) {
        this.solicitudInscripcionList = solicitudInscripcionList;
    }

    
    public List<TdocumentoSolicitud> getTdocumentoSolicitudList() {
        return tdocumentoSolicitudList;
    }

    public void setTdocumentoSolicitudList(List<TdocumentoSolicitud> tdocumentoSolicitudList) {
        this.tdocumentoSolicitudList = tdocumentoSolicitudList;
    }
    
    public List<Tevaluacion> getTevaluacion() {
        return tevaluacion;
    }

    public void setTevaluacion(List<Tevaluacion> tevaluacion) {
        this.tevaluacion = tevaluacion;
    }
    
    public String getTipoCalificacion() {
        return tipoCalificacion;
    }

    public void setTipoCalificacion(String tipoCalificacion) {
        this.tipoCalificacion = tipoCalificacion;
    }
    
    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public List<Tmaterial> getTmaterial() {
        return tmaterial;
    }

    public void setTmaterial(List<Tmaterial> tmaterial) {
        this.tmaterial = tmaterial;
    }

    public List<Tmaterial> getTmaterialSelectOption() {
        return tmaterialSelectOption;
    }

    public void setTmaterialSelectOption(List<Tmaterial> tmaterialSelectOption) {
        this.tmaterialSelectOption = tmaterialSelectOption;
    }

    public List<Tsolicitud> getTsolicitudList() {
        return tsolicitudList;
    }

    public void setTsolicitudList(List<Tsolicitud> tsolicitudList) {
        this.tsolicitudList = tsolicitudList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeSearch() {
        return typeSearch;
    }

    public void setTypeSearch(String typeSearch) {
        this.typeSearch = typeSearch;
    }

    public List<Curso> getCarga() {
        return this.cursoList;
    }

    public boolean asisteClases(Integer correl, Integer rut) {
        return asiste(this, correl, rut);
    }


    public List<LogSolicitud> getLogSolicitudList() {
        return logSolicitudList;
    }


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

    public EstadoDocExp getEstadoDocExp() {
        return estadoDocExp;
    }

    public void setEstadoDocExp(EstadoDocExp estadoDocExp) {
        this.estadoDocExp = estadoDocExp;
    }

    public List<EstadoDocExp> getEstadoDocExpList() {
        return estadoDocExpList;
    }

    public void setEstadoDocExpList(List<EstadoDocExp> estadoDocExpList) {
        this.estadoDocExpList = estadoDocExpList;
    }
}
