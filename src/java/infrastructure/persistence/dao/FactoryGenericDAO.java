/*
 * @(#)FactoryGenericDAO.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence.dao;

import domain.repository.SalaRepository;
import domain.repository.EmpleadorRepository;
import domain.repository.PreguntaAutoEvaluacionRepository;
import domain.repository.RespEnctaCursoRepository;
import domain.repository.ParamArchivosWebRepository;
import domain.repository.EncuestaDocenteRepository;
import domain.repository.UnidadRepository;
import domain.repository.LaborRealizadaRepository;
import domain.repository.PlanLogroRepository;
import domain.repository.LogActaRepository;
import domain.repository.TsolicitudRepository;
import domain.repository.RespuestaAutoEvaluacionAcademicoRepository;
import domain.repository.InscripcionAdicionalLogroRepository;
import domain.repository.RespuestaEncuestaDocenteRepository;
import domain.repository.ReporteClaseRepository;
import domain.repository.TevaluacionRepository;
import domain.repository.MallaRepository;
import domain.repository.MencionInfoIntranetProfesorRepository;
import domain.repository.ExpedienteNominaRepository;
import domain.repository.SolicitudRepository;
import domain.repository.FichaEstudioRepository;
import domain.repository.SolicitudJustificativoRepository;
import domain.repository.HorarioRepository;
import domain.repository.FuncionarioRepository;
import domain.repository.SacarreraRepository;
import domain.repository.ModuloHorarioRepository;
import domain.repository.EncuestaAyudanteRepository;
import domain.repository.UserLoginActionStackRepository;
import domain.repository.InscripcionRepository;
import domain.repository.PregEnctaRepository;
import domain.repository.MencionInfoIntranetRepository;
import domain.repository.ElectivoRepository;
import domain.repository.EmisionNominaRepository;
import domain.repository.ProyectoRepository;
import domain.repository.MatriculaHistoricoRepository;
import domain.repository.LogInscripcionRepository;
import domain.repository.TmensajeDestinoRepository;
import domain.repository.SolicitudInscripcionRepository;
import domain.repository.RequisitoGradoTituloAdicRepository;
import domain.repository.PersonaRepository;
import domain.repository.ProfesorRepository;
import domain.repository.LogCertificacionRepository;
import domain.repository.EstadoCivilRepository;
import domain.repository.ExpedienteLogroRepository;
import domain.repository.MensajeDestinatarioRepository;
import domain.repository.TramiteRepository;
import domain.repository.RespEnctaAyuCursoRepository;
import domain.repository.ParametroRepository;
import domain.repository.MensajeRepository;
import domain.repository.MensajeAttachRepository;
import domain.repository.ExternoRepository;
import domain.repository.TmaterialPerfilRepository;
import domain.repository.LogroRepository;
import domain.repository.ReincorporacionRepository;
import domain.repository.LogSolicitudRepository;
import domain.repository.ParametroMencionRepository;
import domain.repository.MencionRepository;
import domain.repository.SolicitudAttachRepository;
import domain.repository.PracticaRepository;
import domain.repository.EstadoSolicitudRepository;
import domain.repository.ReservaSalaRepository;
import domain.repository.ExamenRepository;
import domain.repository.EvaluacionRepository;
import domain.repository.TmensajeBarraDestinoRepository;
import domain.repository.FichaLaboralRepository;
import domain.repository.PasswordTicketRepository;
import domain.repository.ExamenComisionRepository;
import domain.repository.RespuestaEncuestaAyudanteRepository;
import domain.repository.MaterialApoyoRepository;
import domain.repository.SolicitudCertificadoCarritoRepository;
import domain.repository.TmotivoSolicitudInscripcionRepository;
import domain.repository.EstadoDocExpRepository;
import domain.repository.EvaluacionAlumnoRepository;
import domain.repository.NominaCarreraRepository;
import domain.repository.ActaCalificacionNominaRepository;
import domain.repository.ActaCalificacionRepository;
import domain.repository.ActaConvalidacionAsignaturaRepository;
import domain.repository.ActaConvalidacionRepository;
import domain.repository.ActaNominaViewRepository;
import domain.repository.ActaViewRepository;
import domain.repository.ActionRepository;
import domain.repository.AdministrativoRepository;
import domain.repository.AluCarFunctionsViewRepository;
import domain.repository.AluCarRepository;
import domain.repository.AlumnoEmpleadorRepository;
import domain.repository.AlumnoRepository;
import domain.repository.AreaRepository;
import domain.repository.AsignaturaRepositoy;
import domain.repository.AsistenciaAlumnoNominaRepository;
import domain.repository.AsistenciaAlumnoRepository;
import domain.repository.AyudanteRepository;
import domain.repository.CalificacionLogroAdicionalRepository;
import domain.repository.CalificacionRepository;
import domain.repository.CarreraRepository;
import domain.repository.CartolaViewRepository;
import domain.repository.CcalidadRepository;
import domain.repository.CertificacionViewRepository;
import domain.repository.ComentarioEncuestaAyudanteRepository;
import domain.repository.ComentarioEncuestaDocenteRepository;
import domain.repository.ComunaRepository;
import domain.repository.ConvalidacionComisionRepository;
import domain.repository.ConvalidacionComisionProfRepository;
import domain.repository.ConvalidacionSolicitudAsignRepository;
import domain.repository.ConvalidacionSolicitudRepository;
import domain.repository.ConvenioRepository;
import domain.repository.CursoAyudanteRepository;
import domain.repository.CursoEspejoRepository;
import domain.repository.CursoRepository;
import domain.repository.CursoProfesorRepository;
import domain.repository.CursoTevaluacionRepository;
import domain.repository.DerechoRepository;
import domain.repository.DiaRepository;
import domain.repository.DocenteHorarioRepository;
import domain.repository.DummyRepository;
import domain.repository.NominaActaViewRepository;
import domain.repository.FlagInscripcionViewRepository;
import persistence.scalar.ScalarRespository;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
abstract class FactoryGenericDAO {

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract ActaNominaViewRepository getActaNominaViewRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract ActaCalificacionRepository getActaCalificacionRepository(String userType);

    public abstract ActaConvalidacionRepository getActaConvalidacionRepository(String userType);

    public abstract ActaConvalidacionAsignaturaRepository getActaConvalidacionAsignaturaRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract ActaViewRepository getActaMencionViewRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract AdministrativoRepository getAdministrativoRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract AluCarFunctionsViewRepository getAluCarFunctionsViewRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract AluCarRepository getAluCarRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract AlumnoRepository getAlumnoRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    
    public abstract AreaRepository getAreaRepository(String userType);
    public abstract AsignaturaRepositoy getAsignaturaRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract AsistenciaAlumnoRepository getAsistenciaAlumnoRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract AsistenciaAlumnoNominaRepository getAsistenciaAlumnoNominaRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract AyudanteRepository getAyudanteRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract ActaCalificacionNominaRepository getActaCalificacionNominaRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract CalificacionLogroAdicionalRepository getCalificacionAdicionalLogroRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract CalificacionRepository getCalificacionRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract CartolaViewRepository getCartolaViewRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract CcalidadRepository getCcalidadRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract ComentarioEncuestaAyudanteRepository getComentarioEncuestaAyudanteRepository(String userType);

    public abstract ComentarioEncuestaDocenteRepository getComentarioEncuestaDocenteRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract ComunaRepository getComunaRepository(String userType);

    public abstract ConvalidacionComisionRepository getConvalidacionComisionRepository(String userType);

    public abstract ConvalidacionComisionProfRepository getConvalidacionComisionProfRepository(String userType);

    public abstract ConvalidacionSolicitudRepository getConvalidacionSolicitudRepository(String userType);

    public abstract ConvalidacionSolicitudAsignRepository getConvalidacionSolicitudAsignRepository(String userType);

    public abstract ConvenioRepository getConvenioRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract ScalarRespository getScalarRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract CursoRepository getCursoRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract CursoAyudanteRepository getCursoAyudanteRepository(String userType);
    public abstract CursoProfesorRepository getCursoProfesorRepository(String userType);
    public abstract DocenteHorarioRepository getDocenteHorarioRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract CursoTevaluacionRepository getCursoTevaluacionRepository(String userType);

    public abstract CursoEspejoRepository getCursoEspejoRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract DerechoRepository getDerechoRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract DiaRepository getDiaRepository(String userType);

    public abstract DummyRepository getDummyRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract ElectivoRepository getElectivoRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract EmisionNominaRepository getEmisionNominaRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract EmpleadorRepository getEmpleadorRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract EncuestaAyudanteRepository getEncuestaAyudanteRepository(String userType);

    public abstract EncuestaDocenteRepository getEncuestaDocenteRepository(String userType);

    public abstract EstadoCivilRepository getEstadoCivilRepository(String userType);

    public abstract EstadoSolicitudRepository getEstadoSolicitudRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract EvaluacionAlumnoRepository getEvaluacionAlumnoRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract EvaluacionRepository getEvaluacionRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract ExamenRepository getExamenRepository(String userType);

    public abstract ExamenComisionRepository getExamenComisionRepository(String userType);

    public abstract ExpedienteLogroRepository getExpedienteLogroRepository(String userType);

    public abstract ExpedienteNominaRepository getExpedienteNominaRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract ExternoRepository getExternoRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract FlagInscripcionViewRepository getFlagInscripcionViewRepository(String userType);

    public abstract FuncionarioRepository getFuncionarioRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract HorarioRepository getHorarioRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract InscripcionRepository getInscripcionRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract InscripcionAdicionalLogroRepository getInscripcionAdicionalLogroRepository(String userType);

    public abstract LaborRealizadaRepository getLaborRealizadaRepository(String userType);

    public abstract LogActaRepository getLogActaRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract LogCertificacionRepository getLogCertificacionRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract LogInscripcionRepository getLogInscripcionRepository(String userType);

    public abstract ActionRepository getLogActionRepository(String userType);

    public abstract LogroRepository getLogroRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract LogSolicitudRepository getLogSolicitudRepository(String userType);

    public abstract MallaRepository getMallaRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract MaterialApoyoRepository getMaterialApoyoRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract MatriculaHistoricoRepository getMatriculaHistoricoRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract MencionRepository getMencionRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract MencionInfoIntranetRepository getMencionInfoIntranetRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract MencionInfoIntranetProfesorRepository getMencionInfoIntranetProfesorRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract MensajeDestinatarioRepository getMensajeDestinatarioRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract MensajeAttachRepository getMensajeAttachRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract MensajeRepository getMensajeRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract ModuloHorarioRepository getModuloHorarioRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract NominaActaViewRepository getNominaActaViewRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract PlanLogroRepository getPlanLogroRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract NominaCarreraRepository getNominaCarreraRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract ParamArchivosWebRepository getParamArchivosWebRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract ParametroRepository getParametroRepository(String userType);
    public abstract ParametroMencionRepository getParametroMencionRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract PasswordTicketRepository getPasswordTicketRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract PracticaRepository getPracticaRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract PregEnctaRepository getPregEnctaRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract PersonaRepository getPersonaRepository(String userType);

    public abstract ProfesorRepository getProfesorRepository(String userType);

    public abstract ProyectoRepository getProyectoRepository(String userType);

    public abstract ReincorporacionRepository getReincorporacionRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract ReporteClaseRepository getReporteClaseRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract ReservaSalaRepository getReservaSalaRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract RespEnctaCursoRepository getRespEnctaCursoRepository(String userType);

    public abstract RespEnctaAyuCursoRepository getRespEnctaAyuCursoRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract PreguntaAutoEvaluacionRepository getPreguntaAutoEvaluacionRepository(
            String userType);

    public abstract RespuestaAutoEvaluacionAcademicoRepository getRespuestaAutoEvaluacionAcademicoRepository(
            String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract RespuestaEncuestaAyudanteRepository getRespuestaEncuestaAyudanteRepository(String userType);

    public abstract RespuestaEncuestaDocenteRepository getRespuestaEncuestaDocenteRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract SacarreraRepository getSacarreraRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract SalaRepository getSalaRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract SolicitudRepository getSolicitudRepository(String userType);

    public abstract SolicitudInscripcionRepository getSolicitudInscripcionRepository(String userType);
    public abstract SolicitudJustificativoRepository getSolicitudJustificativoRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract SolicitudAttachRepository getSolicitudAttachRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract RequisitoGradoTituloAdicRepository getRequisitoGradoTituloAdicRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract TevaluacionRepository getTevaluacionRepository(String userType);

    public abstract CertificacionViewRepository getCertificacionViewRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract TmaterialPerfilRepository getTmaterialPerfilRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract TmensajeBarraDestinoRepository getTmensajeBarraDestinoRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract TmensajeDestinoRepository getTmensajeDestinoRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract TramiteRepository getTramiteRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract TsolicitudRepository getTsolicitudRepository(String userType);

    public abstract TmotivoSolicitudInscripcionRepository getTmotivoSolicitudInscripcionRepository(String userType);

    public abstract UnidadRepository getUnidadRepository(String userType);

    public abstract UserLoginActionStackRepository getUserLoginActionStackRepository(String userType);

    /**
     * Alvaro *
     */
    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract FichaEstudioRepository getFichaEstudioRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract FichaLaboralRepository getFichaLaboralRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract AlumnoEmpleadorRepository getAlumnoEmpleadorRepository(String userType);

    public abstract SolicitudCertificadoCarritoRepository getSolicitudCertificadoCarritoRepository(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract CarreraRepository getCarreraRepository(String userType);
    
    public abstract EstadoDocExpRepository getEstadoDocExpRepository(String userType);

}
