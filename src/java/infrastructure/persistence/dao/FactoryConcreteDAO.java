/*
 * @(#)DAO.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence.dao;

//import org.apache.log4j.Logger;
import infrastructure.persistence.AluCarFunctionsViewPersistenceImpl;
import infrastructure.persistence.CertificacionViewPersistenceImpl;
import infrastructure.persistence.CartolaViewPersistenceImpl;
import infrastructure.persistence.ActaNominaViewPersistenceImpl;
import infrastructure.persistence.NominaActaViewPersistenceImpl;
import infrastructure.persistence.ActaViewPersistenceImpl;
import infrastructure.persistence.FlagInscripcionViewPersistenceImpl;
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
import infrastructure.persistence.EstadoDocExpPersistenceImpl;
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
import domain.repository.RegionRepository;
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

import org.hibernate.Session;
import persistence.scalar.ScalarPersistenceImpl;
import static infrastructure.util.AppStaticsUtil.APP_DB_USERS;
import infrastructure.util.HibernateUtil;
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
    public ActaNominaViewRepository getActaNominaViewRepository(String userType) {
        return (ActaNominaViewRepository) instantiateDAO(ActaNominaViewPersistenceImpl.class, userType);
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
    public ActaCalificacionRepository getActaCalificacionRepository(String userType) {
        return (ActaCalificacionRepository) instantiateDAO(ActaCalificacionPersistenceImpl.class, userType);
    }

    @Override
    public ActaConvalidacionRepository getActaConvalidacionRepository(String userType) {
        return (ActaConvalidacionRepository) instantiateDAO(ActaConvalidacionPersistenceImpl.class, userType);
    }

    @Override
    public ActaConvalidacionAsignaturaRepository getActaConvalidacionAsignaturaRepository(String userType) {
        return (ActaConvalidacionAsignaturaRepository) instantiateDAO(ActaConvalidacionAsignaturaPersistenceImpl.class, userType);
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
    public ActaViewRepository getActaMencionViewRepository(String userType) {
        return (ActaViewRepository) instantiateDAO(ActaViewPersistenceImpl.class, userType);
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
    public AluCarRepository getAluCarRepository(String userType) {
        return (AluCarRepository) instantiateDAO(AluCarPersistenceImpl.class, userType);
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
    public AluCarFunctionsViewRepository getAluCarFunctionsViewRepository(String userType) {
        return (AluCarFunctionsViewRepository) instantiateDAO(AluCarFunctionsViewPersistenceImpl.class, userType);
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
    public AlumnoRepository getAlumnoRepository(String userType) {
        return (AlumnoRepository) instantiateDAO(AlumnoPersistenceImpl.class, userType);
    }
    
    @Override
    public AreaRepository getAreaRepository(String userType) {
        return (AreaRepository) instantiateDAO(AreaPersistenceImpl.class, userType);
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
    public AsignaturaRepositoy getAsignaturaRepository(String userType) {
        return (AsignaturaRepositoy) instantiateDAO(AsignaturaPersistenceImpl.class, userType);
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
    public AsistenciaAlumnoRepository getAsistenciaAlumnoRepository(String userType) {
        return (AsistenciaAlumnoRepository) instantiateDAO(AsistenciaAlumnoPersistenceImpl.class, userType);
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
    public AsistenciaAlumnoNominaRepository getAsistenciaAlumnoNominaRepository(String userType) {
        return (AsistenciaAlumnoNominaRepository) instantiateDAO(AsistenciaAlumnoNominaPersistenceImpl.class,
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
    public AyudanteRepository getAyudanteRepository(String userType) {
        return (AyudanteRepository) instantiateDAO(AyudantePersistenceImpl.class, userType);
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
    public ActaCalificacionNominaRepository getActaCalificacionNominaRepository(String userType) {
        return (ActaCalificacionNominaRepository) instantiateDAO(ActaCalificacionNominaPersistenceImpl.class, userType);
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
    public CalificacionLogroAdicionalRepository getCalificacionAdicionalLogroRepository(String userType) {
        return (CalificacionLogroAdicionalRepository) instantiateDAO(
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
    public CalificacionRepository getCalificacionRepository(String userType) {
        return (CalificacionRepository) instantiateDAO(CalificacionPersistenceImpl.class, userType);
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
    public CartolaViewRepository getCartolaViewRepository(String userType) {
        return (CartolaViewRepository) instantiateDAO(CartolaViewPersistenceImpl.class, userType);
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
    public CcalidadRepository getCcalidadRepository(String userType) {
        return (CcalidadRepository) instantiateDAO(CcalidadPersistenceImpl.class, userType);
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
    public ComentarioEncuestaAyudanteRepository getComentarioEncuestaAyudanteRepository(String userType) {
        return (ComentarioEncuestaAyudanteRepository) instantiateDAO(ComentarioEncuestaAyudantePersistenceImpl.class, userType);
    }

    /**
     *
     * @param userType
     * @return
     */
    @Override
    public ComentarioEncuestaDocenteRepository getComentarioEncuestaDocenteRepository(String userType) {
        return (ComentarioEncuestaDocenteRepository) instantiateDAO(ComentarioEncuestaDocentePersistenceImpl.class, userType);
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
    public ComunaRepository getComunaRepository(String userType) {
        return (ComunaRepository) instantiateDAO(ComunaPersistenceImpl.class, userType);
    }

    @Override
    public ConvalidacionComisionRepository getConvalidacionComisionRepository(String userType) {
        return (ConvalidacionComisionRepository) instantiateDAO(ConvalidacionComisionPersistenceImpl.class, userType);
    }

    @Override
    public ConvalidacionComisionProfRepository getConvalidacionComisionProfRepository(String userType) {
        return (ConvalidacionComisionProfRepository) instantiateDAO(ConvalidacionComisionProfPersistenceImpl.class, userType);
    }

    @Override
    public ConvalidacionSolicitudRepository getConvalidacionSolicitudRepository(String userType) {
        return (ConvalidacionSolicitudRepository) instantiateDAO(ConvalidacionSolicitudPersistenceImpl.class, userType);
    }

    @Override
    public ConvalidacionSolicitudAsignRepository getConvalidacionSolicitudAsignRepository(String userType) {
        return (ConvalidacionSolicitudAsignRepository) instantiateDAO(ConvalidacionSolicitudAsignPersistenceImpl.class, userType);
    }

    @Override
    public ConvenioRepository getConvenioRepository(String userType) {
        return (ConvenioRepository) instantiateDAO(ConvenioPersistenceImpl.class, userType);
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
    public ScalarRespository getScalarRepository(String userType) {
        return (ScalarRespository) instantiateDAO(ScalarPersistenceImpl.class, userType);
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
    public CursoRepository getCursoRepository(String userType) {
        return (CursoRepository) instantiateDAO(CursoPersistenceImpl.class, userType);
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
    public CursoAyudanteRepository getCursoAyudanteRepository(String userType) {
        return (CursoAyudanteRepository) instantiateDAO(CursoAyudantePersistenceImpl.class, userType);
    }       

    /**
     *
     * @param userType
     * @return
     */
    @Override
    public CursoProfesorRepository getCursoProfesorRepository(String userType) {
        return (CursoProfesorRepository) instantiateDAO(CursoProfesorPersistenceImpl.class, userType);
    }
    
    @Override
    public DocenteHorarioRepository getDocenteHorarioRepository(String userType) {
        return (DocenteHorarioRepository) instantiateDAO(DocenteHorarioPersistenceImpl.class, userType);
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
    public CursoTevaluacionRepository getCursoTevaluacionRepository(String userType) {
        return (CursoTevaluacionRepository) instantiateDAO(CursoTevaluacionPersistenceImpl.class, userType);
    }

    @Override
    public CursoEspejoRepository getCursoEspejoRepository(String userType) {
        return (CursoEspejoRepository) instantiateDAO(CursoEspejoPersistenceImpl.class, userType);
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
    public DerechoRepository getDerechoRepository(String userType) {
        return (DerechoRepository) instantiateDAO(DerechoPersistenceImpl.class, userType);
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
    public DiaRepository getDiaRepository(String userType) {
        return (DiaRepository) instantiateDAO(DiaPersistenceImpl.class, userType);
    }    

    @Override
    public DummyRepository getDummyRepository(String userType) {
        return (DummyRepository) instantiateDAO(DummyPersistenceImpl.class, userType);
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
    public ElectivoRepository getElectivoRepository(String userType) {
        return (ElectivoRepository) instantiateDAO(ElectivoPersistenceImpl.class, userType);
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
    public EmisionNominaRepository getEmisionNominaRepository(String userType) {
        return (EmisionNominaRepository) instantiateDAO(EmisionNominaPersistenceImpl.class, userType);
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
    public EmpleadorRepository getEmpleadorRepository(String userType) {
        return (EmpleadorRepository) instantiateDAO(EmpleadorPersistenceImpl.class, userType);
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
    public EncuestaAyudanteRepository getEncuestaAyudanteRepository(String userType) {
        return (EncuestaAyudanteRepository) instantiateDAO(EncuestaAyudantePersistenceImpl.class, userType);
    }

    /**
     *
     * @param userType
     * @return
     */
    @Override
    public EncuestaDocenteRepository getEncuestaDocenteRepository(String userType) {
        return (EncuestaDocenteRepository) instantiateDAO(EncuestaDocentePersistenceImpl.class, userType);
    }   

    /**
     *
     * @param userType
     * @return
     */
    @Override
    public EstadoCivilRepository getEstadoCivilRepository(String userType) {
        return (EstadoCivilRepository) instantiateDAO(EstadoCivilPersistenceImpl.class, userType);
    }

    /**
     *
     * @param userType
     * @return
     */
    @Override
    public EstadoSolicitudRepository getEstadoSolicitudRepository(String userType) {
        return (EstadoSolicitudRepository) instantiateDAO(EstadoSolicitudPersistenceImpl.class, userType);
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
    public EvaluacionRepository getEvaluacionRepository(String userType) {
        return (EvaluacionRepository) instantiateDAO(EvaluacionPersistenceImpl.class, userType);
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
    public EvaluacionAlumnoRepository getEvaluacionAlumnoRepository(String userType) {
        return (EvaluacionAlumnoRepository) instantiateDAO(EvaluacionAlumnoPersistenceImpl.class, userType);
    }
    
    @Override
    public ExamenRepository getExamenRepository(String userType) {
        return (ExamenRepository) instantiateDAO(ExamenPersistenceImpl.class, userType);
    }
    
    @Override
    public ExamenComisionRepository getExamenComisionRepository(String userType) {
        return (ExamenComisionRepository) instantiateDAO(ExamenComisionPersistenceImpl.class, userType);
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
    public ExpedienteLogroRepository getExpedienteLogroRepository(String userType) {
        return (ExpedienteLogroRepository) instantiateDAO(ExpedienteLogroPersistenceImpl.class, userType);
    }

    @Override
    public ExpedienteNominaRepository getExpedienteNominaRepository(String userType) {
        return (ExpedienteNominaRepository) instantiateDAO(ExpedienteNominaPersistenceImpl.class, userType);
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
    public ExternoRepository getExternoRepository(String userType) {
        return (ExternoRepository) instantiateDAO(ExternoPersistenceImpl.class, userType);
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
    public FlagInscripcionViewRepository getFlagInscripcionViewRepository(String userType) {
        return (FlagInscripcionViewRepository) instantiateDAO(FlagInscripcionViewPersistenceImpl.class, userType);
    }

    @Override
    public FuncionarioRepository getFuncionarioRepository(String userType) {
        return (FuncionarioRepository) instantiateDAO(FuncionarioPersistenceImpl.class, userType);
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
    public HorarioRepository getHorarioRepository(String userType) {
        return (HorarioRepository) instantiateDAO(HorarioPersistenceImpl.class, userType);
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
    public InscripcionRepository getInscripcionRepository(String userType) {
        return (InscripcionRepository) instantiateDAO(InscripcionPersistenceImpl.class, userType);
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
    public InscripcionAdicionalLogroRepository getInscripcionAdicionalLogroRepository(String userType) {
        return (InscripcionAdicionalLogroRepository) instantiateDAO(InscripcionAdicionalLogroPersistenceImpl.class,
                userType);
    }


     /**
     *
     * @param userType
     * @return
     */
    @Override
    public LaborRealizadaRepository getLaborRealizadaRepository(String userType) {
        return (LaborRealizadaRepository) instantiateDAO(LaborRealizadaPersistenceImpl.class, userType);
    }

    /**
     *
     * @param userType
     * @return
     */
    @Override
    public LogActaRepository getLogActaRepository(String userType) {
        return (LogActaRepository) instantiateDAO(LogActaPersistenceImpl.class, userType);
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
    public LogCertificacionRepository getLogCertificacionRepository(String userType) {
        return (LogCertificacionRepository) instantiateDAO(LogCertificacionPersistenceImpl.class, userType);
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
    public LogInscripcionRepository getLogInscripcionRepository(String userType) {
        return (LogInscripcionRepository) instantiateDAO(LogInscripcionPersistenceImpl.class, userType);
    }

    @Override
    public LogSolicitudRepository getLogSolicitudRepository(String userType) {
        return (LogSolicitudRepository) instantiateDAO(LogSolicitudPersistenceImpl.class, userType);
    }

    /**
     *
     * @param userType
     * @return
     */
    @Override
    public ActionRepository getLogActionRepository(String userType) {
        return (ActionRepository) instantiateDAO(ActionPersistenceImpl.class, userType);
    }

    @Override
    public LogroRepository getLogroRepository(String userType) {
        return (LogroRepository) instantiateDAO(LogroPersistenceImpl.class, userType);
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
    public MallaRepository getMallaRepository(String userType) {
        return (MallaRepository) instantiateDAO(MallaPersistenceImpl.class, userType);
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
    public MaterialApoyoRepository getMaterialApoyoRepository(String userType) {
        return (MaterialApoyoRepository) instantiateDAO(MaterialApoyoPersistenceImpl.class, userType);
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
    public MatriculaHistoricoRepository getMatriculaHistoricoRepository(String userType) {
        return (MatriculaHistoricoRepository) instantiateDAO(MatriculaHistoricoPersistenceImpl.class, userType);
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
    public MencionRepository getMencionRepository(String userType) {
        return (MencionRepository) instantiateDAO(MencionPersistenceImpl.class, userType);
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
    public MencionInfoIntranetRepository getMencionInfoIntranetRepository(String userType) {
        return (MencionInfoIntranetRepository) instantiateDAO(MencionInfoIntranetPersistenceImpl.class, userType);
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
    public MencionInfoIntranetProfesorRepository getMencionInfoIntranetProfesorRepository(String userType) {
        return (MencionInfoIntranetProfesorRepository) instantiateDAO(
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
    public MensajeDestinatarioRepository getMensajeDestinatarioRepository(String userType) {
        return (MensajeDestinatarioRepository) instantiateDAO(MensajeDestinatarioPersistenceImpl.class, userType);
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
    public MensajeAttachRepository getMensajeAttachRepository(String userType) {
        return (MensajeAttachRepository) instantiateDAO(MensajeAttachPersistenceImpl.class, userType);
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
    public MensajeRepository getMensajeRepository(String userType) {
        return (MensajeRepository) instantiateDAO(MensajePersistenceImpl.class, userType);
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
    public ModuloHorarioRepository getModuloHorarioRepository(String userType) {
        return (ModuloHorarioRepository) instantiateDAO(ModuloHorarioPersistenceImpl.class, userType);
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
    public PlanLogroRepository getPlanLogroRepository(String userType) {
        return (PlanLogroRepository) instantiateDAO(PlanLogroPersistenceImpl.class, userType);
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
    public NominaActaViewRepository getNominaActaViewRepository(String userType) {
        return (NominaActaViewRepository) instantiateDAO(NominaActaViewPersistenceImpl.class, userType);
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
    public NominaCarreraRepository getNominaCarreraRepository(String userType) {
        return (NominaCarreraRepository) instantiateDAO(NominaCarreraPersistenceImpl.class, userType);
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
    public ParametroRepository getParametroRepository(String userType) {
        return (ParametroRepository) instantiateDAO(ParametroPersistenceImpl.class, userType);
    }
    
     @Override
    public ParametroMencionRepository getParametroMencionRepository(String userType) {
        return (ParametroMencionRepository) instantiateDAO(ParametroMencionPersistenceImpl.class, userType);
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
    public ParamArchivosWebRepository getParamArchivosWebRepository(String userType) {
        return (ParamArchivosWebRepository) instantiateDAO(ParamArchivosWebPersistenceImpl.class, userType);
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
    public PasswordTicketRepository getPasswordTicketRepository(String userType) {
        return (PasswordTicketRepository) instantiateDAO(PasswordTicketPersistenceImpl.class, userType);
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
    public PracticaRepository getPracticaRepository(String userType) {
        return (PracticaRepository) instantiateDAO(PracticaPersistenceImpl.class, userType);
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
    public PregEnctaRepository getPregEnctaRepository(String userType) {
        return (PregEnctaRepository) instantiateDAO(PregEnctaPersistenceImpl.class, userType);
    }

    @Override
    public PersonaRepository getPersonaRepository(String userType) {
        return (PersonaRepository) instantiateDAO(PersonaPersistenceImpl.class, userType);
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
    public ProfesorRepository getProfesorRepository(String userType) {
        return (ProfesorRepository) instantiateDAO(ProfesorPersistenceImpl.class, userType);
    }

    @Override
    public ProyectoRepository getProyectoRepository(String userType) {
        return (ProyectoRepository) instantiateDAO(ProyectoPersistenceImpl.class, userType);
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
    public ReporteClaseRepository getReporteClaseRepository(String userType) {
        return (ReporteClaseRepository) instantiateDAO(ReporteClasePersistenceImpl.class, userType);
    }

    /**
     * Method description
     *
     *
     * @param userType
     *
     * @return
     */
    public RegionRepository getRegionRepository(String userType) {
        return (RegionRepository) instantiateDAO(RegionPersistenceImpl.class, userType);
    }

    /**
     *
     * @param userType
     * @return
     */
    @Override
    public ReincorporacionRepository getReincorporacionRepository(String userType) {
        return (ReincorporacionRepository) instantiateDAO(ReincorporacionPersistenceImpl.class, userType);
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
    public ReservaSalaRepository getReservaSalaRepository(String userType) {
        return (ReservaSalaRepository) instantiateDAO(ReservaSalaPersistenceImpl.class, userType);
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
    public RespEnctaCursoRepository getRespEnctaCursoRepository(String userType) {
        return (RespEnctaCursoRepository) instantiateDAO(RespEnctaCursoPersistenceImpl.class, userType);
    }

    @Override
    public RespEnctaAyuCursoRepository getRespEnctaAyuCursoRepository(String userType) {
        return (RespEnctaAyuCursoRepository) instantiateDAO(RespEnctaAyuCursoPersistenceImpl.class, userType);
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
    public PreguntaAutoEvaluacionRepository getPreguntaAutoEvaluacionRepository(String userType) {
        return (PreguntaAutoEvaluacionRepository) instantiateDAO(
                PreguntaAutoEvaluacionPersistenceImpl.class, userType);
    }

    @Override
    public RespuestaAutoEvaluacionAcademicoRepository getRespuestaAutoEvaluacionAcademicoRepository(String userType) {
        return (RespuestaAutoEvaluacionAcademicoRepository) instantiateDAO(
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
    public RespuestaEncuestaAyudanteRepository getRespuestaEncuestaAyudanteRepository(String userType) {
        return (RespuestaEncuestaAyudanteRepository) instantiateDAO(RespuestaEncuestaAyudantePersistenceImpl.class,
                userType);
    }

    /**
     *
     * @param userType
     * @return
     */
    @Override
    public RespuestaEncuestaDocenteRepository getRespuestaEncuestaDocenteRepository(String userType) {
        return (RespuestaEncuestaDocenteRepository) instantiateDAO(RespuestaEncuestaDocentePersistenceImpl.class,
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
    public SacarreraRepository getSacarreraRepository(String userType) {
        return (SacarreraRepository) instantiateDAO(SacarreraPersistenceImpl.class, userType);
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
    public SalaRepository getSalaRepository(String userType) {
        return (SalaRepository) instantiateDAO(SalaPersistenceImpl.class, userType);
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
    public SolicitudRepository getSolicitudRepository(String userType) {
        return (SolicitudRepository) instantiateDAO(SolicitudPersistenceImpl.class, userType);
    }

    @Override
    public SolicitudInscripcionRepository getSolicitudInscripcionRepository(String userType) {
        return (SolicitudInscripcionRepository) instantiateDAO(SolicitudInscripcionPersistenceImpl.class, userType);
    }
    
    @Override
    public SolicitudJustificativoRepository getSolicitudJustificativoRepository(String userType) {
        return (SolicitudJustificativoRepository) instantiateDAO(SolicitudJustificativoPersistenceImpl.class, userType);
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
    public SolicitudAttachRepository getSolicitudAttachRepository(String userType) {
        return (SolicitudAttachRepository) instantiateDAO(SolicitudAttachPersistenceImpl.class, userType);
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
    public AdministrativoRepository getAdministrativoRepository(String userType) {
        return (AdministrativoRepository) instantiateDAO(AdministrativoPersistenceImpl.class, userType);
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
    public RequisitoGradoTituloAdicRepository getRequisitoGradoTituloAdicRepository(String userType) {
        return (RequisitoGradoTituloAdicRepository) instantiateDAO(RequisitoGradoTituloAdicPersistenceImpl.class,
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
    public TevaluacionRepository getTevaluacionRepository(String userType) {
        return (TevaluacionRepository) instantiateDAO(TevaluacionPersistenceImpl.class, userType);
    }  
    
    @Override
    public CertificacionViewRepository getCertificacionViewRepository(String userType) {
        return (CertificacionViewRepository) instantiateDAO(CertificacionViewPersistenceImpl.class, userType);
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
    public TmaterialPerfilRepository getTmaterialPerfilRepository(String userType) {
        return (TmaterialPerfilRepository) instantiateDAO(TmaterialPerfilPersistenceImpl.class, userType);
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
    public TmensajeDestinoRepository getTmensajeDestinoRepository(String userType) {
        return (TmensajeDestinoRepository) instantiateDAO(TmensajeDestinoPersistenceImpl.class, userType);
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
    public TmensajeBarraDestinoRepository getTmensajeBarraDestinoRepository(String userType) {
        return (TmensajeBarraDestinoRepository) instantiateDAO(TmensajeBarraDestinoPersistenceImpl.class, userType);
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
    public TramiteRepository getTramiteRepository(String userType) {
        return (TramiteRepository) instantiateDAO(TramitePersistenceImpl.class, userType);
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
    public TsolicitudRepository getTsolicitudRepository(String userType) {
        return (TsolicitudRepository) instantiateDAO(TsolicitudPersistenceImpl.class, userType);
    }

    @Override
    public TmotivoSolicitudInscripcionRepository getTmotivoSolicitudInscripcionRepository(String userType) {
        return (TmotivoSolicitudInscripcionRepository) instantiateDAO(TmotivoSolicitudInscripcionPersistenceImpl.class, userType);
    }

    @Override
    public UnidadRepository getUnidadRepository(String userType) {
        return (UnidadRepository) instantiateDAO(UnidadPersistenceImpl.class, userType);
    }

    @Override
    public UserLoginActionStackRepository getUserLoginActionStackRepository(String userType) {
        return (UserLoginActionStackRepository) instantiateDAO(UserLoginActionStackPersistenceImpl.class, userType);
    }
    
    @Override
    public SolicitudCertificadoCarritoRepository getSolicitudCertificadoCarritoRepository(String userType) {
        return (SolicitudCertificadoCarritoRepository) instantiateDAO(SolicitudCertificadoCarritoPersistenceImpl.class, userType);
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
    public FichaEstudioRepository getFichaEstudioRepository(String userType) {
        return (FichaEstudioRepository) instantiateDAO(FichaEstudioPersistenceImpl.class, userType);
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
    public FichaLaboralRepository getFichaLaboralRepository(String userType) {
        return (FichaLaboralRepository) instantiateDAO(FichaLaboralPersistenceImpl.class, userType);
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
    public AlumnoEmpleadorRepository getAlumnoEmpleadorRepository(String userType) {
        return (AlumnoEmpleadorRepository) instantiateDAO(AlumnoEmpleadorPersistenceImpl.class, userType);
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
    public CarreraRepository getCarreraRepository(String userType) {
        return (CarreraRepository) instantiateDAO(CarreraPersistenceImpl.class, userType);
    }
    
    @Override
    public EstadoDocExpRepository getEstadoDocExpRepository(String userType) {
        return (EstadoDocExpRepository) instantiateDAO(EstadoDocExpPersistenceImpl.class, userType);
    }
}
