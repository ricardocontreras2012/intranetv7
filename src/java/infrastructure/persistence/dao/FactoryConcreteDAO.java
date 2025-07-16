/*
 * @(#)DAO.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence.dao;

//import org.apache.log4j.Logger;
import infrastructure.persistence.AluCarFunctionsPersistenceViewImpl;
import infrastructure.persistence.CertificacionPersistenceViewImpl;
import infrastructure.persistence.CartolaPersistenceViewImpl;
import infrastructure.persistence.ActaNominaPersistenceViewImpl;
import infrastructure.persistence.NominaActaPersistenceViewImpl;
import infrastructure.persistence.ActaPersistenceViewImpl;
import infrastructure.persistence.FlagInscripcionPersistenceViewImpl;
import domain.repository.FlagInscripcionPersistenceView;
import domain.repository.AluCarFunctionsPersistenceView;
import domain.repository.ActaNominaPersistenceView;
import domain.repository.CartolaPersistenceView;
import domain.repository.CertificacionPersistenceView;
import domain.repository.NominaActaPersistenceView;
import domain.repository.ActaPersistenceView;
import infrastructure.persistence.ExamenPersistenceImpl;
import infrastructure.persistence.MensajePersistenceImpl;
import infrastructure.persistence.AsignaturaPersistenceImpl;
import infrastructure.persistence.CursoEspejoPersistenceImpl;
import infrastructure.persistence.LogroPersistenceImpl;
import infrastructure.persistence.TevaluacionPersistenceImpl;
import infrastructure.persistence.ConvenioPersistenceImpl;
import infrastructure.persistence.AluCarPersistenceImpl;
import infrastructure.persistence.ReservaSalaPersistenceImpl;
import infrastructure.persistence.EncuestaDocentePersistenceImpl;
import infrastructure.persistence.ExamenComisionPersistenceImpl;
import infrastructure.persistence.ParametroPersistenceImpl;
import infrastructure.persistence.AlumnoPersistenceImpl;
import infrastructure.persistence.TramitePersistenceImpl;
import infrastructure.persistence.PregEnctaPersistenceImpl;
import infrastructure.persistence.InscripcionAdicionalLogroPersistenceImpl;
import infrastructure.persistence.ActaConvalidacionPersistenceImpl;
import infrastructure.persistence.DocenteHorarioPersistenceImpl;
import infrastructure.persistence.ConvalidacionSolicitudPersistenceImpl;
import infrastructure.persistence.ModuloHorarioPersistenceImpl;
import infrastructure.persistence.MatriculaHistoricoPersistenceImpl;
import infrastructure.persistence.ExternoPersistenceImpl;
import infrastructure.persistence.ComunaPersistenceImpl;
import infrastructure.persistence.ActionPersistenceImpl;
import infrastructure.persistence.TmensajeDestinoPersistenceImpl;
import infrastructure.persistence.ComentarioEncuestaAyudantePersistenceImpl;
import infrastructure.persistence.EstadoSolicitudPersistenceImpl;
import infrastructure.persistence.ActaConvalidacionAsignaturaPersistenceImpl;
import infrastructure.persistence.SolicitudInscripcionPersistenceImpl;
import infrastructure.persistence.EmpleadorPersistenceImpl;
import infrastructure.persistence.RespEnctaAyuCursoPersistenceImpl;
import infrastructure.persistence.DerechoPersistenceImpl;
import infrastructure.persistence.SolicitudJustificativoPersistenceImpl;
import infrastructure.persistence.FuncionarioPersistenceImpl;
import infrastructure.persistence.ExpedienteLogroPersistenceImpl;
import infrastructure.persistence.NominaCarreraPersistenceImpl;
import infrastructure.persistence.HorarioPersistenceImpl;
import infrastructure.persistence.ProfesorPersistenceImpl;
import infrastructure.persistence.LogCertificacionPersistenceImpl;
import infrastructure.persistence.SalaPersistenceImpl;
import infrastructure.persistence.TmensajeBarraDestinoPersistenceImpl;
import infrastructure.persistence.AsistenciaAlumnoPersistenceImpl;
import infrastructure.persistence.MaterialApoyoPersistenceImpl;
import infrastructure.persistence.LogSolicitudPersistenceImpl;
import infrastructure.persistence.AlumnoEmpleadorPersistenceImpl;
import infrastructure.persistence.AdministrativoPersistenceImpl;
import infrastructure.persistence.MensajeDestinatarioPersistenceImpl;
import infrastructure.persistence.MencionPersistenceImpl;
import infrastructure.persistence.TmotivoSolicitudInscripcionPersistenceImpl;
import infrastructure.persistence.SolicitudPersistenceImpl;
import infrastructure.persistence.AyudantePersistenceImpl;
import infrastructure.persistence.ElectivoPersistenceImpl;
import infrastructure.persistence.PersonaPersistenceImpl;
import infrastructure.persistence.CursoPersistenceImpl;
import infrastructure.persistence.CalificacionPersistenceImpl;
import infrastructure.persistence.PlanLogroPersistenceImpl;
import infrastructure.persistence.ConvalidacionComisionProfPersistenceImpl;
import infrastructure.persistence.InscripcionPersistenceImpl;
import infrastructure.persistence.RequisitoGradoTituloAdicPersistenceImpl;
import infrastructure.persistence.ReincorporacionPersistenceImpl;
import infrastructure.persistence.CarreraPersistenceImpl;
import infrastructure.persistence.PreguntaAutoEvaluacionPersistenceImpl;
import infrastructure.persistence.PasswordTicketPersistenceImpl;
import infrastructure.persistence.EvaluacionPersistenceImpl;
import infrastructure.persistence.ReporteClasePersistenceImpl;
import infrastructure.persistence.EmisionNominaPersistenceImpl;
import infrastructure.persistence.CalificacionLogroAdicionalPersistenceImpl;
import infrastructure.persistence.RespuestaAutoEvaluacionAcademicoPersistenceImpl;
import infrastructure.persistence.ActaCalificacionNominaPersistenceImpl;
import infrastructure.persistence.RegionPersistenceImpl;
import infrastructure.persistence.UserLoginActionStackPersistenceImpl;
import infrastructure.persistence.MensajeAttachPersistenceImpl;
import infrastructure.persistence.SolicitudAttachPersistenceImpl;
import infrastructure.persistence.LogActaPersistenceImpl;
import infrastructure.persistence.ExpedienteNominaPersistenceImpl;
import infrastructure.persistence.ParametroMencionPersistenceImpl;
import infrastructure.persistence.MallaPersistenceImpl;
import infrastructure.persistence.RespuestaEncuestaAyudantePersistenceImpl;
import infrastructure.persistence.EncuestaAyudantePersistenceImpl;
import infrastructure.persistence.UnidadPersistenceImpl;
import infrastructure.persistence.ProyectoPersistenceImpl;
import infrastructure.persistence.MencionInfoIntranetPersistenceImpl;
import infrastructure.persistence.FichaLaboralPersistenceImpl;
import infrastructure.persistence.MencionInfoIntranetProfesorPersistenceImpl;
import infrastructure.persistence.EvaluacionAlumnoPersistenceImpl;
import infrastructure.persistence.CursoTevaluacionPersistenceImpl;
import infrastructure.persistence.DiaPersistenceImpl;
import infrastructure.persistence.AsistenciaAlumnoNominaPersistenceImpl;
import infrastructure.persistence.CcalidadPersistenceImpl;
import infrastructure.persistence.CursoProfesorPersistenceImpl;
import infrastructure.persistence.RespuestaEncuestaDocentePersistenceImpl;
import infrastructure.persistence.CursoAyudantePersistenceImpl;
import infrastructure.persistence.LogInscripcionPersistenceImpl;
import infrastructure.persistence.ActaCalificacionPersistenceImpl;
import infrastructure.persistence.ConvalidacionSolicitudAsignPersistenceImpl;
import infrastructure.persistence.RespEnctaCursoPersistenceImpl;
import infrastructure.persistence.ParamArchivosWebPersistenceImpl;
import infrastructure.persistence.ConvalidacionComisionPersistenceImpl;
import infrastructure.persistence.TsolicitudPersistenceImpl;
import infrastructure.persistence.TmaterialPerfilPersistenceImpl;
import infrastructure.persistence.LaborRealizadaPersistenceImpl;
import infrastructure.persistence.AreaPersistenceImpl;
import infrastructure.persistence.SacarreraPersistenceImpl;
import infrastructure.persistence.PracticaPersistenceImpl;
import infrastructure.persistence.SolicitudCertificadoCarritoPersistenceImpl;
import infrastructure.persistence.EstadoCivilPersistenceImpl;
import infrastructure.persistence.ComentarioEncuestaDocentePersistenceImpl;
import infrastructure.persistence.DummyPersistenceImpl;
import infrastructure.persistence.FichaEstudioPersistenceImpl;
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
import domain.repository.RegionPersistence;
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
import infrastructure.persistence.EstadoDocExpPersistenceImpl;
import org.hibernate.Session;
import persistence.scalar.ScalarPersistence;
import persistence.scalar.ScalarPersistenceImpl;
import static infrastructure.util.AppStaticsUtil.APP_DB_USERS;
import infrastructure.util.HibernateUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class FactoryConcreteDAO extends FactoryGenericDAO {

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public ActaNominaPersistenceView getActaNominaPersistenceView(String userType) {
        return (ActaNominaPersistenceView) instantiateDAO(ActaNominaPersistenceViewImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public ActaCalificacionPersistence getActaCalificacionPersistence(String userType) {
        return (ActaCalificacionPersistence) instantiateDAO(ActaCalificacionPersistenceImpl.class, userType);
    }

    @Override
    public ActaConvalidacionPersistence getActaConvalidacionPersistence(String userType) {
        return (ActaConvalidacionPersistence) instantiateDAO(ActaConvalidacionPersistenceImpl.class, userType);
    }

    @Override
    public ActaConvalidacionAsignaturaPersistence getActaConvalidacionAsignaturaPersistence(String userType) {
        return (ActaConvalidacionAsignaturaPersistence) instantiateDAO(ActaConvalidacionAsignaturaPersistenceImpl.class, userType);
    }


    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public ActaPersistenceView getActaMencionPersistenceView(String userType) {
        return (ActaPersistenceView) instantiateDAO(ActaPersistenceViewImpl.class, userType);
    }


    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public AluCarPersistence getAluCarPersistence(String userType) {
        return (AluCarPersistence) instantiateDAO(AluCarPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public AluCarFunctionsPersistenceView getAluCarFunctionsPersistenceView(String userType) {
        return (AluCarFunctionsPersistenceView) instantiateDAO(AluCarFunctionsPersistenceViewImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public AlumnoPersistence getAlumnoPersistence(String userType) {
        return (AlumnoPersistence) instantiateDAO(AlumnoPersistenceImpl.class, userType);
    }
    
    @Override
    public AreaPersistence getAreaPersistence(String userType) {
        return (AreaPersistence) instantiateDAO(AreaPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public AsignaturaPersistence getAsignaturaPersistence(String userType) {
        return (AsignaturaPersistence) instantiateDAO(AsignaturaPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public AsistenciaAlumnoPersistence getAsistenciaAlumnoPersistence(String userType) {
        return (AsistenciaAlumnoPersistence) instantiateDAO(AsistenciaAlumnoPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public AsistenciaAlumnoNominaPersistence getAsistenciaAlumnoNominaPersistence(String userType) {
        return (AsistenciaAlumnoNominaPersistence) instantiateDAO(AsistenciaAlumnoNominaPersistenceImpl.class,
                userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public AyudantePersistence getAyudantePersistence(String userType) {
        return (AyudantePersistence) instantiateDAO(AyudantePersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public ActaCalificacionNominaPersistence getActaCalificacionNominaPersistence(String userType) {
        return (ActaCalificacionNominaPersistence) instantiateDAO(ActaCalificacionNominaPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public CalificacionLogroAdicionalPersistence getCalificacionAdicionalLogroPersistence(String userType) {
        return (CalificacionLogroAdicionalPersistence) instantiateDAO(
                CalificacionLogroAdicionalPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public CalificacionPersistence getCalificacionPersistence(String userType) {
        return (CalificacionPersistence) instantiateDAO(CalificacionPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public CartolaPersistenceView getCartolaPersistenceView(String userType) {
        return (CartolaPersistenceView) instantiateDAO(CartolaPersistenceViewImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public CcalidadPersistence getCcalidadPersistence(String userType) {
        return (CcalidadPersistence) instantiateDAO(CcalidadPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public ComentarioEncuestaAyudantePersistence getComentarioEncuestaAyudantePersistence(String userType) {
        return (ComentarioEncuestaAyudantePersistence) instantiateDAO(ComentarioEncuestaAyudantePersistenceImpl.class, userType);
    }

    /**
     *
     * @param userType
     * @return
     */
    @Override
    public ComentarioEncuestaDocentePersistence getComentarioEncuestaDocentePersistence(String userType) {
        return (ComentarioEncuestaDocentePersistence) instantiateDAO(ComentarioEncuestaDocentePersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public ComunaPersistence getComunaPersistence(String userType) {
        return (ComunaPersistence) instantiateDAO(ComunaPersistenceImpl.class, userType);
    }

    @Override
    public ConvalidacionComisionPersistence getConvalidacionComisionPersistence(String userType) {
        return (ConvalidacionComisionPersistence) instantiateDAO(ConvalidacionComisionPersistenceImpl.class, userType);
    }

    @Override
    public ConvalidacionComisionProfPersistence getConvalidacionComisionProfPersistence(String userType) {
        return (ConvalidacionComisionProfPersistence) instantiateDAO(ConvalidacionComisionProfPersistenceImpl.class, userType);
    }

    @Override
    public ConvalidacionSolicitudPersistence getConvalidacionSolicitudPersistence(String userType) {
        return (ConvalidacionSolicitudPersistence) instantiateDAO(ConvalidacionSolicitudPersistenceImpl.class, userType);
    }

    @Override
    public ConvalidacionSolicitudAsignPersistence getConvalidacionSolicitudAsignPersistence(String userType) {
        return (ConvalidacionSolicitudAsignPersistence) instantiateDAO(ConvalidacionSolicitudAsignPersistenceImpl.class, userType);
    }

    @Override
    public ConvenioPersistence getConvenioPersistence(String userType) {
        return (ConvenioPersistence) instantiateDAO(ConvenioPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public ScalarPersistence getScalarPersistence(String userType) {
        return (ScalarPersistence) instantiateDAO(ScalarPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public CursoPersistence getCursoPersistence(String userType) {
        return (CursoPersistence) instantiateDAO(CursoPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public CursoAyudantePersistence getCursoAyudantePersistence(String userType) {
        return (CursoAyudantePersistence) instantiateDAO(CursoAyudantePersistenceImpl.class, userType);
    }       

    /**
     *
     * @param userType
     * @return
     */
    @Override
    public CursoProfesorPersistence getCursoProfesorPersistence(String userType) {
        return (CursoProfesorPersistence) instantiateDAO(CursoProfesorPersistenceImpl.class, userType);
    }
    
    @Override
    public DocenteHorarioPersistence getDocenteHorarioPersistence(String userType) {
        return (DocenteHorarioPersistence) instantiateDAO(DocenteHorarioPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public CursoTevaluacionPersistence getCursoTevaluacionPersistence(String userType) {
        return (CursoTevaluacionPersistence) instantiateDAO(CursoTevaluacionPersistenceImpl.class, userType);
    }

    @Override
    public CursoEspejoPersistence getCursoEspejoPersistence(String userType) {
        return (CursoEspejoPersistence) instantiateDAO(CursoEspejoPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public DerechoPersistence getDerechoPersistence(String userType) {
        return (DerechoPersistence) instantiateDAO(DerechoPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public DiaPersistence getDiaPersistence(String userType) {
        return (DiaPersistence) instantiateDAO(DiaPersistenceImpl.class, userType);
    }    

    @Override
    public DummyPersistence getDummyPersistence(String userType) {
        return (DummyPersistence) instantiateDAO(DummyPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public ElectivoPersistence getElectivoPersistence(String userType) {
        return (ElectivoPersistence) instantiateDAO(ElectivoPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public EmisionNominaPersistence getEmisionNominaPersistence(String userType) {
        return (EmisionNominaPersistence) instantiateDAO(EmisionNominaPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public EmpleadorPersistence getEmpleadorPersistence(String userType) {
        return (EmpleadorPersistence) instantiateDAO(EmpleadorPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public EncuestaAyudantePersistence getEncuestaAyudantePersistence(String userType) {
        return (EncuestaAyudantePersistence) instantiateDAO(EncuestaAyudantePersistenceImpl.class, userType);
    }

    /**
     *
     * @param userType
     * @return
     */
    @Override
    public EncuestaDocentePersistence getEncuestaDocentePersistence(String userType) {
        return (EncuestaDocentePersistence) instantiateDAO(EncuestaDocentePersistenceImpl.class, userType);
    }   

    /**
     *
     * @param userType
     * @return
     */
    @Override
    public EstadoCivilPersistence getEstadoCivilPersistence(String userType) {
        return (EstadoCivilPersistence) instantiateDAO(EstadoCivilPersistenceImpl.class, userType);
    }

    /**
     *
     * @param userType
     * @return
     */
    @Override
    public EstadoSolicitudPersistence getEstadoSolicitudPersistence(String userType) {
        return (EstadoSolicitudPersistence) instantiateDAO(EstadoSolicitudPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public EvaluacionPersistence getEvaluacionPersistence(String userType) {
        return (EvaluacionPersistence) instantiateDAO(EvaluacionPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public EvaluacionAlumnoPersistence getEvaluacionAlumnoPersistence(String userType) {
        return (EvaluacionAlumnoPersistence) instantiateDAO(EvaluacionAlumnoPersistenceImpl.class, userType);
    }
    
    @Override
    public ExamenPersistence getExamenPersistence(String userType) {
        return (ExamenPersistence) instantiateDAO(ExamenPersistenceImpl.class, userType);
    }
    
    @Override
    public ExamenComisionPersistence getExamenComisionPersistence(String userType) {
        return (ExamenComisionPersistence) instantiateDAO(ExamenComisionPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public ExpedienteLogroPersistence getExpedienteLogroPersistence(String userType) {
        return (ExpedienteLogroPersistence) instantiateDAO(ExpedienteLogroPersistenceImpl.class, userType);
    }

    @Override
    public ExpedienteNominaPersistence getExpedienteNominaPersistence(String userType) {
        return (ExpedienteNominaPersistence) instantiateDAO(ExpedienteNominaPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public ExternoPersistence getExternoPersistence(String userType) {
        return (ExternoPersistence) instantiateDAO(ExternoPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public FlagInscripcionPersistenceView getFlagInscripcionPersistenceView(String userType) {
        return (FlagInscripcionPersistenceView) instantiateDAO(FlagInscripcionPersistenceViewImpl.class, userType);
    }

    @Override
    public FuncionarioPersistence getFuncionarioPersistence(String userType) {
        return (FuncionarioPersistence) instantiateDAO(FuncionarioPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public HorarioPersistence getHorarioPersistence(String userType) {
        return (HorarioPersistence) instantiateDAO(HorarioPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public InscripcionPersistence getInscripcionPersistence(String userType) {
        return (InscripcionPersistence) instantiateDAO(InscripcionPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public InscripcionAdicionalLogroPersistence getInscripcionAdicionalLogroPersistence(String userType) {
        return (InscripcionAdicionalLogroPersistence) instantiateDAO(InscripcionAdicionalLogroPersistenceImpl.class,
                userType);
    }


     /**
     *
     * @param userType
     * @return
     */
    @Override
    public LaborRealizadaPersistence getLaborRealizadaPersistence(String userType) {
        return (LaborRealizadaPersistence) instantiateDAO(LaborRealizadaPersistenceImpl.class, userType);
    }

    /**
     *
     * @param userType
     * @return
     */
    @Override
    public LogActaPersistence getLogActaPersistence(String userType) {
        return (LogActaPersistence) instantiateDAO(LogActaPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public LogCertificacionPersistence getLogCertificacionPersistence(String userType) {
        return (LogCertificacionPersistence) instantiateDAO(LogCertificacionPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public LogInscripcionPersistence getLogInscripcionPersistence(String userType) {
        return (LogInscripcionPersistence) instantiateDAO(LogInscripcionPersistenceImpl.class, userType);
    }

    @Override
    public LogSolicitudPersistence getLogSolicitudPersistence(String userType) {
        return (LogSolicitudPersistence) instantiateDAO(LogSolicitudPersistenceImpl.class, userType);
    }

    /**
     *
     * @param userType
     * @return
     */
    @Override
    public ActionPersistence getLogActionPersistence(String userType) {
        return (ActionPersistence) instantiateDAO(ActionPersistenceImpl.class, userType);
    }

    @Override
    public LogroPersistence getLogroPersistence(String userType) {
        return (LogroPersistence) instantiateDAO(LogroPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public MallaPersistence getMallaPersistence(String userType) {
        return (MallaPersistence) instantiateDAO(MallaPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public MaterialApoyoPersistence getMaterialApoyoPersistence(String userType) {
        return (MaterialApoyoPersistence) instantiateDAO(MaterialApoyoPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public MatriculaHistoricoPersistence getMatriculaHistoricoPersistence(String userType) {
        return (MatriculaHistoricoPersistence) instantiateDAO(MatriculaHistoricoPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public MencionPersistence getMencionPersistence(String userType) {
        return (MencionPersistence) instantiateDAO(MencionPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public MencionInfoIntranetPersistence getMencionInfoIntranetPersistence(String userType) {
        return (MencionInfoIntranetPersistence) instantiateDAO(MencionInfoIntranetPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public MencionInfoIntranetProfesorPersistence getMencionInfoIntranetProfesorPersistence(String userType) {
        return (MencionInfoIntranetProfesorPersistence) instantiateDAO(
                MencionInfoIntranetProfesorPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public MensajeDestinatarioPersistence getMensajeDestinatarioPersistence(String userType) {
        return (MensajeDestinatarioPersistence) instantiateDAO(MensajeDestinatarioPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public MensajeAttachPersistence getMensajeAttachPersistence(String userType) {
        return (MensajeAttachPersistence) instantiateDAO(MensajeAttachPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public MensajePersistence getMensajePersistence(String userType) {
        return (MensajePersistence) instantiateDAO(MensajePersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public ModuloHorarioPersistence getModuloHorarioPersistence(String userType) {
        return (ModuloHorarioPersistence) instantiateDAO(ModuloHorarioPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public PlanLogroPersistence getPlanLogroPersistence(String userType) {
        return (PlanLogroPersistence) instantiateDAO(PlanLogroPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public NominaActaPersistenceView getNominaActaPersistenceView(String userType) {
        return (NominaActaPersistenceView) instantiateDAO(NominaActaPersistenceViewImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public NominaCarreraPersistence getNominaCarreraPersistence(String userType) {
        return (NominaCarreraPersistence) instantiateDAO(NominaCarreraPersistenceImpl.class, userType);
    }


    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public ParametroPersistence getParametroPersistence(String userType) {
        return (ParametroPersistence) instantiateDAO(ParametroPersistenceImpl.class, userType);
    }
    
     @Override
    public ParametroMencionPersistence getParametroMencionPersistence(String userType) {
        return (ParametroMencionPersistence) instantiateDAO(ParametroMencionPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public ParamArchivosWebPersistence getParamArchivosWebPersistence(String userType) {
        return (ParamArchivosWebPersistence) instantiateDAO(ParamArchivosWebPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public PasswordTicketPersistence getPasswordTicketPersistence(String userType) {
        return (PasswordTicketPersistence) instantiateDAO(PasswordTicketPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public PracticaPersistence getPracticaPersistence(String userType) {
        return (PracticaPersistence) instantiateDAO(PracticaPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public PregEnctaPersistence getPregEnctaPersistence(String userType) {
        return (PregEnctaPersistence) instantiateDAO(PregEnctaPersistenceImpl.class, userType);
    }

    @Override
    public PersonaPersistence getPersonaPersistence(String userType) {
        return (PersonaPersistence) instantiateDAO(PersonaPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public ProfesorPersistence getProfesorPersistence(String userType) {
        return (ProfesorPersistence) instantiateDAO(ProfesorPersistenceImpl.class, userType);
    }

    @Override
    public ProyectoPersistence getProyectoPersistence(String userType) {
        return (ProyectoPersistence) instantiateDAO(ProyectoPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public ReporteClasePersistence getReporteClasePersistence(String userType) {
        return (ReporteClasePersistence) instantiateDAO(ReporteClasePersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public RegionPersistence getRegionPersistence(String userType) {
        return (RegionPersistence) instantiateDAO(RegionPersistenceImpl.class, userType);
    }

    /**
     *
     * @param userType
     * @return
     */
    @Override
    public ReincorporacionPersistence getReincorporacionPersistence(String userType) {
        return (ReincorporacionPersistence) instantiateDAO(ReincorporacionPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public ReservaSalaPersistence getReservaSalaPersistence(String userType) {
        return (ReservaSalaPersistence) instantiateDAO(ReservaSalaPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */


    @Override
    public RespEnctaCursoPersistence getRespEnctaCursoPersistence(String userType) {
        return (RespEnctaCursoPersistence) instantiateDAO(RespEnctaCursoPersistenceImpl.class, userType);
    }

    @Override
    public RespEnctaAyuCursoPersistence getRespEnctaAyuCursoPersistence(String userType) {
        return (RespEnctaAyuCursoPersistence) instantiateDAO(RespEnctaAyuCursoPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */

    @Override
    public PreguntaAutoEvaluacionPersistence getPreguntaAutoEvaluacionPersistence(String userType) {
        return (PreguntaAutoEvaluacionPersistence) instantiateDAO(
                PreguntaAutoEvaluacionPersistenceImpl.class, userType);
    }

    @Override
    public RespuestaAutoEvaluacionAcademicoPersistence getRespuestaAutoEvaluacionAcademicoPersistence(String userType) {
        return (RespuestaAutoEvaluacionAcademicoPersistence) instantiateDAO(
                RespuestaAutoEvaluacionAcademicoPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public RespuestaEncuestaAyudantePersistence getRespuestaEncuestaAyudantePersistence(String userType) {
        return (RespuestaEncuestaAyudantePersistence) instantiateDAO(RespuestaEncuestaAyudantePersistenceImpl.class,
                userType);
    }

    /**
     *
     * @param userType
     * @return
     */
    @Override
    public RespuestaEncuestaDocentePersistence getRespuestaEncuestaDocentePersistence(String userType) {
        return (RespuestaEncuestaDocentePersistence) instantiateDAO(RespuestaEncuestaDocentePersistenceImpl.class,
                userType);
    }    

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public SacarreraPersistence getSacarreraPersistence(String userType) {
        return (SacarreraPersistence) instantiateDAO(SacarreraPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public SalaPersistence getSalaPersistence(String userType) {
        return (SalaPersistence) instantiateDAO(SalaPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public SolicitudPersistence getSolicitudPersistence(String userType) {
        return (SolicitudPersistence) instantiateDAO(SolicitudPersistenceImpl.class, userType);
    }

    @Override
    public SolicitudInscripcionPersistence getSolicitudInscripcionPersistence(String userType) {
        return (SolicitudInscripcionPersistence) instantiateDAO(SolicitudInscripcionPersistenceImpl.class, userType);
    }
    
    @Override
    public SolicitudJustificativoPersistence getSolicitudJustificativoPersistence(String userType) {
        return (SolicitudJustificativoPersistence) instantiateDAO(SolicitudJustificativoPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public SolicitudAttachPersistence getSolicitudAttachPersistence(String userType) {
        return (SolicitudAttachPersistence) instantiateDAO(SolicitudAttachPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public AdministrativoPersistence getAdministrativoPersistence(String userType) {
        return (AdministrativoPersistence) instantiateDAO(AdministrativoPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public RequisitoGradoTituloAdicPersistence getRequisitoGradoTituloAdicPersistence(String userType) {
        return (RequisitoGradoTituloAdicPersistence) instantiateDAO(RequisitoGradoTituloAdicPersistenceImpl.class,
                userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public TevaluacionPersistence getTevaluacionPersistence(String userType) {
        return (TevaluacionPersistence) instantiateDAO(TevaluacionPersistenceImpl.class, userType);
    }  
    
    @Override
    public CertificacionPersistenceView getCertificacionPersistenceView(String userType) {
        return (CertificacionPersistenceView) instantiateDAO(CertificacionPersistenceViewImpl.class, userType);
    } 

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public TmaterialPerfilPersistence getTmaterialPerfilPersistence(String userType) {
        return (TmaterialPerfilPersistence) instantiateDAO(TmaterialPerfilPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public TmensajeDestinoPersistence getTmensajeDestinoPersistence(String userType) {
        return (TmensajeDestinoPersistence) instantiateDAO(TmensajeDestinoPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public TmensajeBarraDestinoPersistence getTmensajeBarraDestinoPersistence(String userType) {
        return (TmensajeBarraDestinoPersistence) instantiateDAO(TmensajeBarraDestinoPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public TramitePersistence getTramitePersistence(String userType) {
        return (TramitePersistence) instantiateDAO(TramitePersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public TsolicitudPersistence getTsolicitudPersistence(String userType) {
        return (TsolicitudPersistence) instantiateDAO(TsolicitudPersistenceImpl.class, userType);
    }

    @Override
    public TmotivoSolicitudInscripcionPersistence getTmotivoSolicitudInscripcionPersistence(String userType) {
        return (TmotivoSolicitudInscripcionPersistence) instantiateDAO(TmotivoSolicitudInscripcionPersistenceImpl.class, userType);
    }

    @Override
    public UnidadPersistence getUnidadPersistence(String userType) {
        return (UnidadPersistence) instantiateDAO(UnidadPersistenceImpl.class, userType);
    }

    @Override
    public UserLoginActionStackPersistence getUserLoginActionStackPersistence(String userType) {
        return (UserLoginActionStackPersistence) instantiateDAO(UserLoginActionStackPersistenceImpl.class, userType);
    }
    
    @Override
    public SolicitudCertificadoCarritoPersistence getSolicitudCertificadoCarritoPersistence(String userType) {
        return (SolicitudCertificadoCarritoPersistence) instantiateDAO(SolicitudCertificadoCarritoPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     * @param daoClass
     * @param userType
     * @return
     */
    @SuppressWarnings("rawtypes")
    private CrudAbstractDAO instantiateDAO(Class daoClass, String userType) {
        try {
            CrudAbstractDAO dao = (CrudAbstractDAO) daoClass.newInstance();

            dao.setSession(getSession(APP_DB_USERS.get(userType)));

            return dao;
        } catch (InstantiationException | IllegalAccessException ex) {

            ex.printStackTrace();

            throw new RuntimeException("Can not instantiate DAO: " + daoClass, ex);
        }
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    Session getSession(String userType) {
        return HibernateUtil.getSession(userType);
    }

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
    @Override
    public FichaEstudioPersistence getFichaEstudioPersistence(String userType) {
        return (FichaEstudioPersistence) instantiateDAO(FichaEstudioPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public FichaLaboralPersistence getFichaLaboralPersistence(String userType) {
        return (FichaLaboralPersistence) instantiateDAO(FichaLaboralPersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public AlumnoEmpleadorPersistence getAlumnoEmpleadorPersistence(String userType) {
        return (AlumnoEmpleadorPersistence) instantiateDAO(AlumnoEmpleadorPersistenceImpl.class, userType);
    }
        
    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    @Override
    public CarreraPersistence getCarreraPersistence(String userType) {
        return (CarreraPersistence) instantiateDAO(CarreraPersistenceImpl.class, userType);
    }
    
    @Override
    public EstadoDocExpPersistence getEstadoDocExpPersistence(String userType) {
        return (EstadoDocExpPersistence) instantiateDAO(EstadoDocExpPersistenceImpl.class, userType);
    }
}
