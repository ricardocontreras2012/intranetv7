/*
 * @(#)FactoryGenericDAO.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence.dao;

import domain.repository.FlagInscripcionPersistenceView;
import domain.repository.AluCarFunctionsPersistenceView;
import domain.repository.ActaNominaPersistenceView;
import domain.repository.CartolaPersistenceView;
import domain.repository.CertificacionPersistenceView;
import domain.repository.NominaActaPersistenceView;
import domain.repository.ActaPersistenceView;
import domain.repository.SalaPersistence;
import domain.repository.EmpleadorPersistence;
import domain.repository.PreguntaAutoEvaluacionPersistence;
import domain.repository.AyudantePersistence;
import domain.repository.RespEnctaCursoPersistence;
import domain.repository.AluCarPersistence;
import domain.repository.DummyPersistence;
import domain.repository.ParamArchivosWebPersistence;
import domain.repository.EncuestaDocentePersistence;
import domain.repository.UnidadPersistence;
import domain.repository.LaborRealizadaPersistence;
import domain.repository.PlanLogroPersistence;
import domain.repository.LogActaPersistence;
import domain.repository.CursoAyudantePersistence;
import domain.repository.TsolicitudPersistence;
import domain.repository.RespuestaAutoEvaluacionAcademicoPersistence;
import domain.repository.InscripcionAdicionalLogroPersistence;
import domain.repository.CalificacionLogroAdicionalPersistence;
import domain.repository.RespuestaEncuestaDocentePersistence;
import domain.repository.ActionPersistence;
import domain.repository.ReporteClasePersistence;
import domain.repository.ConvalidacionSolicitudPersistence;
import domain.repository.AsistenciaAlumnoNominaPersistence;
import domain.repository.AsignaturaPersistence;
import domain.repository.TevaluacionPersistence;
import domain.repository.MallaPersistence;
import domain.repository.MencionInfoIntranetProfesorPersistence;
import domain.repository.CursoTevaluacionPersistence;
import domain.repository.ExpedienteNominaPersistence;
import domain.repository.SolicitudPersistence;
import domain.repository.AlumnoEmpleadorPersistence;
import domain.repository.ComunaPersistence;
import domain.repository.FichaEstudioPersistence;
import domain.repository.SolicitudJustificativoPersistence;
import domain.repository.DocenteHorarioPersistence;
import domain.repository.HorarioPersistence;
import domain.repository.FuncionarioPersistence;
import domain.repository.SacarreraPersistence;
import domain.repository.ModuloHorarioPersistence;
import domain.repository.EncuestaAyudantePersistence;
import domain.repository.UserLoginActionStackPersistence;
import domain.repository.InscripcionPersistence;
import domain.repository.CarreraPersistence;
import domain.repository.PregEnctaPersistence;
import domain.repository.MencionInfoIntranetPersistence;
import domain.repository.ElectivoPersistence;
import domain.repository.EmisionNominaPersistence;
import domain.repository.ActaCalificacionNominaPersistence;
import domain.repository.ProyectoPersistence;
import domain.repository.MatriculaHistoricoPersistence;
import domain.repository.LogInscripcionPersistence;
import domain.repository.CursoPersistence;
import domain.repository.AlumnoPersistence;
import domain.repository.ComentarioEncuestaAyudantePersistence;
import domain.repository.TmensajeDestinoPersistence;
import domain.repository.ActaConvalidacionPersistence;
import domain.repository.SolicitudInscripcionPersistence;
import domain.repository.RequisitoGradoTituloAdicPersistence;
import domain.repository.PersonaPersistence;
import domain.repository.CursoEspejoPersistence;
import domain.repository.DerechoPersistence;
import domain.repository.CalificacionPersistence;
import domain.repository.ProfesorPersistence;
import domain.repository.ConvalidacionComisionProfPersistence;
import domain.repository.LogCertificacionPersistence;
import domain.repository.EstadoCivilPersistence;
import domain.repository.ExpedienteLogroPersistence;
import domain.repository.ConvalidacionSolicitudAsignPersistence;
import domain.repository.AdministrativoPersistence;
import domain.repository.ActaCalificacionPersistence;
import domain.repository.MensajeDestinatarioPersistence;
import domain.repository.CcalidadPersistence;
import domain.repository.AreaPersistence;
import domain.repository.TramitePersistence;
import domain.repository.RespEnctaAyuCursoPersistence;
import domain.repository.ParametroPersistence;
import domain.repository.DiaPersistence;
import domain.repository.MensajePersistence;
import domain.repository.MensajeAttachPersistence;
import domain.repository.ExternoPersistence;
import domain.repository.TmaterialPerfilPersistence;
import domain.repository.LogroPersistence;
import domain.repository.ActaConvalidacionAsignaturaPersistence;
import domain.repository.ReincorporacionPersistence;
import domain.repository.ConvalidacionComisionPersistence;
import domain.repository.LogSolicitudPersistence;
import domain.repository.ParametroMencionPersistence;
import domain.repository.MencionPersistence;
import domain.repository.SolicitudAttachPersistence;
import domain.repository.CursoProfesorPersistence;
import domain.repository.PracticaPersistence;
import domain.repository.EstadoSolicitudPersistence;
import domain.repository.ReservaSalaPersistence;
import domain.repository.ExamenPersistence;
import domain.repository.EvaluacionPersistence;
import domain.repository.TmensajeBarraDestinoPersistence;
import domain.repository.ComentarioEncuestaDocentePersistence;
import domain.repository.FichaLaboralPersistence;
import domain.repository.PasswordTicketPersistence;
import domain.repository.ExamenComisionPersistence;
import domain.repository.AsistenciaAlumnoPersistence;
import domain.repository.RespuestaEncuestaAyudantePersistence;
import domain.repository.MaterialApoyoPersistence;
import domain.repository.SolicitudCertificadoCarritoPersistence;
import domain.repository.TmotivoSolicitudInscripcionPersistence;
import domain.repository.ConvenioPersistence;
import domain.repository.EstadoDocExpPersistence;
import domain.repository.EvaluacionAlumnoPersistence;
import domain.repository.NominaCarreraPersistence;
import persistence.scalar.ScalarPersistence;

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
    public abstract ActaNominaPersistenceView getActaNominaPersistenceView(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract ActaCalificacionPersistence getActaCalificacionPersistence(String userType);

    public abstract ActaConvalidacionPersistence getActaConvalidacionPersistence(String userType);

    public abstract ActaConvalidacionAsignaturaPersistence getActaConvalidacionAsignaturaPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract ActaPersistenceView getActaMencionPersistenceView(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract AdministrativoPersistence getAdministrativoPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract AluCarFunctionsPersistenceView getAluCarFunctionsPersistenceView(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract AluCarPersistence getAluCarPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract AlumnoPersistence getAlumnoPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    
    public abstract AreaPersistence getAreaPersistence(String userType);
    public abstract AsignaturaPersistence getAsignaturaPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract AsistenciaAlumnoPersistence getAsistenciaAlumnoPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract AsistenciaAlumnoNominaPersistence getAsistenciaAlumnoNominaPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract AyudantePersistence getAyudantePersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract ActaCalificacionNominaPersistence getActaCalificacionNominaPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract CalificacionLogroAdicionalPersistence getCalificacionAdicionalLogroPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract CalificacionPersistence getCalificacionPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract CartolaPersistenceView getCartolaPersistenceView(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract CcalidadPersistence getCcalidadPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract ComentarioEncuestaAyudantePersistence getComentarioEncuestaAyudantePersistence(String userType);

    public abstract ComentarioEncuestaDocentePersistence getComentarioEncuestaDocentePersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract ComunaPersistence getComunaPersistence(String userType);

    public abstract ConvalidacionComisionPersistence getConvalidacionComisionPersistence(String userType);

    public abstract ConvalidacionComisionProfPersistence getConvalidacionComisionProfPersistence(String userType);

    public abstract ConvalidacionSolicitudPersistence getConvalidacionSolicitudPersistence(String userType);

    public abstract ConvalidacionSolicitudAsignPersistence getConvalidacionSolicitudAsignPersistence(String userType);

    public abstract ConvenioPersistence getConvenioPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract ScalarPersistence getScalarPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract CursoPersistence getCursoPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract CursoAyudantePersistence getCursoAyudantePersistence(String userType);
    public abstract CursoProfesorPersistence getCursoProfesorPersistence(String userType);
    public abstract DocenteHorarioPersistence getDocenteHorarioPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract CursoTevaluacionPersistence getCursoTevaluacionPersistence(String userType);

    public abstract CursoEspejoPersistence getCursoEspejoPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract DerechoPersistence getDerechoPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract DiaPersistence getDiaPersistence(String userType);

    public abstract DummyPersistence getDummyPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract ElectivoPersistence getElectivoPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract EmisionNominaPersistence getEmisionNominaPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract EmpleadorPersistence getEmpleadorPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract EncuestaAyudantePersistence getEncuestaAyudantePersistence(String userType);

    public abstract EncuestaDocentePersistence getEncuestaDocentePersistence(String userType);

    public abstract EstadoCivilPersistence getEstadoCivilPersistence(String userType);

    public abstract EstadoSolicitudPersistence getEstadoSolicitudPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract EvaluacionAlumnoPersistence getEvaluacionAlumnoPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract EvaluacionPersistence getEvaluacionPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract ExamenPersistence getExamenPersistence(String userType);

    public abstract ExamenComisionPersistence getExamenComisionPersistence(String userType);

    public abstract ExpedienteLogroPersistence getExpedienteLogroPersistence(String userType);

    public abstract ExpedienteNominaPersistence getExpedienteNominaPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract ExternoPersistence getExternoPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract FlagInscripcionPersistenceView getFlagInscripcionPersistenceView(String userType);

    public abstract FuncionarioPersistence getFuncionarioPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract HorarioPersistence getHorarioPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract InscripcionPersistence getInscripcionPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract InscripcionAdicionalLogroPersistence getInscripcionAdicionalLogroPersistence(String userType);

    public abstract LaborRealizadaPersistence getLaborRealizadaPersistence(String userType);

    public abstract LogActaPersistence getLogActaPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract LogCertificacionPersistence getLogCertificacionPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract LogInscripcionPersistence getLogInscripcionPersistence(String userType);

    public abstract ActionPersistence getLogActionPersistence(String userType);

    public abstract LogroPersistence getLogroPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract LogSolicitudPersistence getLogSolicitudPersistence(String userType);

    public abstract MallaPersistence getMallaPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract MaterialApoyoPersistence getMaterialApoyoPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract MatriculaHistoricoPersistence getMatriculaHistoricoPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract MencionPersistence getMencionPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract MencionInfoIntranetPersistence getMencionInfoIntranetPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract MencionInfoIntranetProfesorPersistence getMencionInfoIntranetProfesorPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract MensajeDestinatarioPersistence getMensajeDestinatarioPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract MensajeAttachPersistence getMensajeAttachPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract MensajePersistence getMensajePersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract ModuloHorarioPersistence getModuloHorarioPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract NominaActaPersistenceView getNominaActaPersistenceView(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract PlanLogroPersistence getPlanLogroPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract NominaCarreraPersistence getNominaCarreraPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract ParamArchivosWebPersistence getParamArchivosWebPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract ParametroPersistence getParametroPersistence(String userType);
    public abstract ParametroMencionPersistence getParametroMencionPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract PasswordTicketPersistence getPasswordTicketPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract PracticaPersistence getPracticaPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract PregEnctaPersistence getPregEnctaPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract PersonaPersistence getPersonaPersistence(String userType);

    public abstract ProfesorPersistence getProfesorPersistence(String userType);

    public abstract ProyectoPersistence getProyectoPersistence(String userType);

    public abstract ReincorporacionPersistence getReincorporacionPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract ReporteClasePersistence getReporteClasePersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract ReservaSalaPersistence getReservaSalaPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract RespEnctaCursoPersistence getRespEnctaCursoPersistence(String userType);

    public abstract RespEnctaAyuCursoPersistence getRespEnctaAyuCursoPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract PreguntaAutoEvaluacionPersistence getPreguntaAutoEvaluacionPersistence(
            String userType);

    public abstract RespuestaAutoEvaluacionAcademicoPersistence getRespuestaAutoEvaluacionAcademicoPersistence(
            String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract RespuestaEncuestaAyudantePersistence getRespuestaEncuestaAyudantePersistence(String userType);

    public abstract RespuestaEncuestaDocentePersistence getRespuestaEncuestaDocentePersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract SacarreraPersistence getSacarreraPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract SalaPersistence getSalaPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract SolicitudPersistence getSolicitudPersistence(String userType);

    public abstract SolicitudInscripcionPersistence getSolicitudInscripcionPersistence(String userType);
    public abstract SolicitudJustificativoPersistence getSolicitudJustificativoPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract SolicitudAttachPersistence getSolicitudAttachPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract RequisitoGradoTituloAdicPersistence getRequisitoGradoTituloAdicPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract TevaluacionPersistence getTevaluacionPersistence(String userType);

    public abstract CertificacionPersistenceView getCertificacionPersistenceView(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract TmaterialPerfilPersistence getTmaterialPerfilPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract TmensajeBarraDestinoPersistence getTmensajeBarraDestinoPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract TmensajeDestinoPersistence getTmensajeDestinoPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract TramitePersistence getTramitePersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract TsolicitudPersistence getTsolicitudPersistence(String userType);

    public abstract TmotivoSolicitudInscripcionPersistence getTmotivoSolicitudInscripcionPersistence(String userType);

    public abstract UnidadPersistence getUnidadPersistence(String userType);

    public abstract UserLoginActionStackPersistence getUserLoginActionStackPersistence(String userType);

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
    public abstract FichaEstudioPersistence getFichaEstudioPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract FichaLaboralPersistence getFichaLaboralPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract AlumnoEmpleadorPersistence getAlumnoEmpleadorPersistence(String userType);

    public abstract SolicitudCertificadoCarritoPersistence getSolicitudCertificadoCarritoPersistence(String userType);

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public abstract CarreraPersistence getCarreraPersistence(String userType);
    
    public abstract EstadoDocExpPersistence getEstadoDocExpPersistence(String userType);

}
