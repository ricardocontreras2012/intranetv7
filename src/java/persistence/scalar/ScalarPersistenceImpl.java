/*
 * @(#)ScalarPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package persistence.scalar;

import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCarId;
import java.io.Serializable;
import java.util.Date;
import java.util.stream.IntStream;
import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import static org.hibernate.type.StandardBasicTypes.INTEGER;
import static org.hibernate.type.StandardBasicTypes.STRING;
import static org.hibernate.type.StandardBasicTypes.TIMESTAMP;
import org.hibernate.type.Type;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ScalarPersistenceImpl extends CrudAbstractDAO<Object, Serializable>
        implements ScalarRespository {

    private Integer getSecuencia(String secuencia) {
        return (Integer) getSession().createSQLQuery("SELECT " + secuencia + ".NEXTVAL seq FROM DUAL").addScalar("seq", INTEGER).uniqueResult();
    }

    private String getString(String sqlText) {
        return (String) getSession().createSQLQuery("SELECT " + sqlText + " val FROM DUAL").addScalar("val", STRING).uniqueResult();
    }

    private String getString(String sqlText, Object[] params, String[] paramNames, Type[] types) {
        SQLQuery query = getSession().createSQLQuery("SELECT " + sqlText + " val FROM dual");

        // Asignamos cada parámetro con su nombre y tipo correspondiente
        IntStream.range(0, params.length)
                .forEach(i -> query.setParameter(paramNames[i], params[i], types[i]));

        Object result = (String) query.addScalar("val", StandardBasicTypes.STRING).uniqueResult();
        return (result != null) ? (String) result : "";
    }

    /**
     * Method description
     *
     * @return
     */
    @Override
    public Integer getSecuenciaAsistencia() {
        return getSecuencia("seq_asistencia_alumno");
    }

    /**
     * Method description
     *
     * @return
     */
    @Override
    public Integer getSecuenciaMensaje() {
        return getSecuencia("seq_msg");
    }

    /**
     * Method description
     *
     * @return
     */
    @Override
    public Integer getSecuenciaNomina() {
        return getSecuencia("seq_nomina_carrera");
    }

    /**
     * Method description
     *
     * @return
     */
    @Override
    public Integer getSecuenciaCertificado() {
        return getSecuencia("seq_cert_random");
    }

    // Método para obtener el verificador de certificado
    @Override
    public String getVerificadorCertificado(Integer folio) {
        return getString("get_rand_certificados(:folio)",
                new Object[]{folio},
                new String[]{"folio"},
                new Type[]{StandardBasicTypes.INTEGER});
    }

    /**
     * Method description
     *
     * @return
     */
    @Override
    public Integer getSecuenciaMaterial() {
        return getSecuencia("seq_documento");
    }

    /**
     * Method description
     *
     * @return
     */
    @Override
    public Integer getSecuenciaActa() {
        return getSecuencia("seq_acta");
    }

    /**
     * Method description
     *
     * @return
     */
    @Override
    public Integer getSecuenciaEncuesta() {
        return getSecuencia("seq_encuesta");
    }

    /**
     *
     * @return
     */
    @Override
    public Integer getSecuenciaLogging() {
        return getSecuencia("seq_logging");
    }

    /**
     * Method description
     *
     *
     * @return
     */
    @Override
    public Integer getSecuenciaDocumentoSolicitud() {
        return getSecuencia("seq_solicitud_documento");
    }

    /**
     * Method description
     *
     *
     * @return
     */
    @Override
    public Integer getSecuenciaSolicitud() {
        return getSecuencia("seq_solicitud");
    }

    /**
     * Method description
     *
     *
     * @return
     */
    @Override
    public Integer getSecuenciaFichaEgresado() {
        return getSecuencia("seq_ficha_egresado");
    }

    @Override
    public Integer getSecuenciaComision() {
        return getSecuencia("seq_comision");
    }

    @Override
    public Integer getSecuenciaConvalidacion() {
        return getSecuencia("seq_convalidacion");
    }

    @Override
    public Integer getSecuenciaExpedienteNomina() {
        return getSecuencia("seq_nomina_expediente");
    }

    @Override
    public Integer getSecuenciaConvenio() {
        return getSecuencia("seq_convenio");
    }

    /**
     * Method description
     *
     * @return
     */
    @Override
    public Date getSysdate() {
        return (Date) getSession().createSQLQuery("select sysdate from dual").addScalar("sysdate", TIMESTAMP).uniqueResult();
    }

    // Método para obtener la fecha en palabras
    @Override
    public String getFechaEnPalabras(String fecha) {
        return getString("get_fecha_en_palabras(:fecha)",
                new Object[]{fecha},
                new String[]{"fecha"},
                new Type[]{StandardBasicTypes.STRING});
    }

    @Override
    // Método para obtener el número en palabras
    public String getNumeroEnPalabras(Integer numero) {
        return getString("util_pkg.get_numeroletras(:numero)",
                new Object[]{numero},
                new String[]{"numero"},
                new Type[]{StandardBasicTypes.INTEGER});
    }

    @Override
    public String getUltimaMatricula(AluCarId id) {
        return getString(
                "alu_car_pkg.get_ultima_matricula(:rut, :codCar, :agnoIng, :semIng)",
                new Object[]{
                    id.getAcaRut(),
                    id.getAcaCodCar(),
                    id.getAcaAgnoIng(),
                    id.getAcaSemIng()
                },
                new String[]{"rut", "codCar", "agnoIng", "semIng"},
                new Type[]{
                    StandardBasicTypes.INTEGER,
                    StandardBasicTypes.INTEGER,
                    StandardBasicTypes.INTEGER,
                    StandardBasicTypes.INTEGER
                }
        );
    }

    @Override
    public String getExamenAP(AluCarId id) {
        return getString("get_asign_examen_ap(:rut, :codCar, :agnoIng, :semIng)",
                new Object[]{id.getAcaRut(), id.getAcaCodCar(), id.getAcaAgnoIng(), id.getAcaSemIng()},
                new String[]{"rut", "codCar", "agnoIng", "semIng"},
                new Type[]{
                    StandardBasicTypes.INTEGER,
                    StandardBasicTypes.INTEGER,
                    StandardBasicTypes.INTEGER,
                    StandardBasicTypes.INTEGER
                });
    }

    @Override
    public String getNombreFacultadxAsign(Integer asign) {
        return getString("facultad_pkg.get_nombre_x_asignatura(:asign)", new Object[]{asign}, new String[]{"asign"}, new Type[]{StandardBasicTypes.INTEGER});
    }

    @Override
    public String getNombreFacultadxTcarrera(Integer tcarrera, Integer especialidad) {
        return getString("facultad_pkg.get_nombre_x_tcarrera_esp(:tcarrera, :especialidad)", new Object[]{tcarrera, especialidad}, new String[]{"tcarrera", "especialidad"}, new Type[]{StandardBasicTypes.INTEGER, StandardBasicTypes.INTEGER});
    }

    /**
     *
     * @param rut
     * @return
     */
    @Override
    public String getNombreFacultadxAyu(Integer rut) {
        return getString("facultad_pkg.get_nombre_x_ayudante(" + rut + ")");
    }

    /**
     *
     * @param rut
     * @return
     */
    @Override
    public Integer getUnidadFacultadxProf(Integer rut) {
        return (Integer) getSession().createSQLQuery("select facultad_pkg.get_unidad_x_profesor(" + rut + ") facultad from dual").addScalar("facultad", INTEGER).uniqueResult();
    }

    @Override
    public String getNombreDepartamento(Integer carrera, Integer mencion) {
        return getString("departamento_pkg.get_nombre_x_carrera_mencion(:carrera, :mencion)", new Object[]{carrera, mencion}, new String[]{"carrera", "mencion"}, new Type[]{StandardBasicTypes.INTEGER, StandardBasicTypes.INTEGER});
    }

    @Override
    public Integer getComision(Integer carrera, Integer mencion) {
        return (Integer) getSession().createSQLQuery("select get_comision_convalidacion(" + carrera + ',' + mencion + ") comision from dual").addScalar("comision", INTEGER).uniqueResult();
    }

    @Override
    public String getReserva(String sala, String dia, Integer modulo, String inicio, String termino) {
        return getString("sala_pkg.get_tope_curso_reserva('" + sala + "','" + dia + "'," + modulo + ",'" + inicio + "','" + termino + "')");
    }

    @Override
    public String getDistincion(Integer rut, Integer carrera, Integer agnoIng, Integer semIng, Integer mencion, Integer plan, Integer correl) {
        return getString("get_distincion_alu_en_palabras(:rut, :carrera, :agnoIng, :semIng, :mencion, :plan, :correl)",
                new Object[]{rut, carrera, agnoIng, semIng, mencion, plan, correl},
                new String[]{"rut", "carrera", "agnoIng", "semIng", "mencion", "plan", "correl"},
                new Type[]{StandardBasicTypes.INTEGER, StandardBasicTypes.INTEGER, StandardBasicTypes.INTEGER,
                    StandardBasicTypes.INTEGER, StandardBasicTypes.INTEGER, StandardBasicTypes.INTEGER,
                    StandardBasicTypes.INTEGER});
    }

    @Override
    public String getRol(Integer rut) {
        return (String) getSession().createSQLQuery("select max(expl_rol) rol from expediente_logro where expl_rut=" + rut).addScalar("rol", STRING).uniqueResult();
    }

    @Override
    public String saveHorarioSala(Integer asign, String elect, String coord, Integer secc, Integer agno, Integer sem, String horarioStr) {
        return getString("curso_pkg.save_horario_json(:asign, :elect, :coord, :secc, :agno, :sem, 'T', :horarioStr)",
                new Object[]{asign, elect, coord, secc, agno, sem, horarioStr},
                new String[]{"asign", "elect", "coord", "secc", "agno", "sem", "horarioStr"},
                new Type[]{StandardBasicTypes.INTEGER, StandardBasicTypes.STRING, StandardBasicTypes.STRING, StandardBasicTypes.INTEGER, StandardBasicTypes.INTEGER, StandardBasicTypes.INTEGER, StandardBasicTypes.STRING});
    }

    @Override
    public String addCurso(Integer asign, String elect, String coord, Integer secc, Integer agno, Integer sem, String electivo, Integer cupo, String inicio, String termino, Integer rut, String jornada) {
        return getString("curso_pkg.new_curso(:asign, :elect, :coord, :secc, :agno, :sem, 'T', :electivo, :cupo, :inicio, :termino, :rut, :jornada)",
                new Object[]{asign, elect, coord, secc, agno, sem, electivo, cupo, inicio, termino, rut, jornada},
                new String[]{"asign", "elect", "coord", "secc", "agno", "sem", "electivo", "cupo", "inicio", "termino", "rut", "jornada"},
                new Type[]{StandardBasicTypes.INTEGER, StandardBasicTypes.STRING, StandardBasicTypes.STRING,
                    StandardBasicTypes.INTEGER, StandardBasicTypes.INTEGER, StandardBasicTypes.INTEGER,
                    StandardBasicTypes.STRING, StandardBasicTypes.INTEGER, StandardBasicTypes.STRING,
                    StandardBasicTypes.STRING, StandardBasicTypes.INTEGER, StandardBasicTypes.STRING});
    }

    @Override
    public String modifyCurso(Integer asign, String elect, String coord, Integer secc, Integer agno, Integer sem, Integer cupo, String inicio, String termino, Integer rut, String jornada) {
        return getString("curso_pkg.update_curso(:asign, :elect, :coord, :secc, :agno, :sem, 'T', :cupo, :inicio, :termino, :rut, :jornada)",
                new Object[]{asign, elect, coord, secc, agno, sem, cupo, inicio, termino, rut, jornada},
                new String[]{"asign", "elect", "coord", "secc", "agno", "sem", "cupo", "inicio", "termino", "rut", "jornada"},
                new Type[]{StandardBasicTypes.INTEGER, StandardBasicTypes.STRING, StandardBasicTypes.STRING, StandardBasicTypes.INTEGER,
                    StandardBasicTypes.INTEGER, StandardBasicTypes.INTEGER, StandardBasicTypes.INTEGER,
                    StandardBasicTypes.STRING, StandardBasicTypes.STRING, StandardBasicTypes.INTEGER, StandardBasicTypes.STRING});
    }

    @Override
    public String getPuedePonerNotas(Integer asign, String elect, String coord, Integer secc, Integer agno, Integer sem) {
        return getString("puede_poner_notas(:asign, :elect, :coord, :secc, :agno, :sem)",
                new Object[]{asign, elect, coord, secc, agno, sem},
                new String[]{"asign", "elect", "coord", "secc", "agno", "sem"},
                new Type[]{StandardBasicTypes.INTEGER, StandardBasicTypes.STRING, StandardBasicTypes.STRING, StandardBasicTypes.INTEGER, StandardBasicTypes.INTEGER, StandardBasicTypes.INTEGER});

    }

    @Override
    public String getSiguienteSemestre(Integer agno, Integer sem, Integer carrera, Integer mencion, Integer plan) {
        return getString("get_siguiente_semestre(" + agno + "," + sem + "," + carrera + "," + mencion + "," + plan + ")");
    }

    @Override
    public String getSemestrePrevio(Integer agno, Integer sem, Integer carrera, Integer mencion, Integer plan) {
        return getString("get_semestre_previo(" + agno + "," + sem + "," + carrera + "," + mencion + "," + plan + ")");
    }

    @Override
    public int puedeEliminar(Integer carrera, Integer mencion, Integer asign, Integer rut, String userType) {
        String sql = "SELECT jefe_carrera_pkg.puede_eliminar_inscripcion(:carrera, :mencion, :asign, :userType, :rut) as val FROM dual";

        return ((Number) getSession()
                .createSQLQuery(sql)
                .setParameter("carrera", carrera, StandardBasicTypes.INTEGER)
                .setParameter("mencion", mencion, StandardBasicTypes.INTEGER)
                .setParameter("asign", asign, StandardBasicTypes.INTEGER)
                .setParameter("userType", userType, StandardBasicTypes.STRING)
                .setParameter("rut", rut, StandardBasicTypes.INTEGER)
                .uniqueResult()).intValue();
    }

    @Override
    public String getMsgPlusFlag(String user) {
        return (String) getSession().createSQLQuery("select wu_mensaje_plus_flag flag from  web_logging.web_user where wu_user='" + user + "'").addScalar("flag", STRING).uniqueResult();
    }

    @Override
    public String getIniciales(Integer rut, String trabajo) {
        return (String) getSession().createSQLQuery("select lrea_iniciales_resp iniciales FROM labor_realizada, labor WHERE lrea_labor = lab_cod and lab_des_masc='" + trabajo + "' and lrea_rut=" + rut).addScalar("iniciales", STRING).uniqueResult();
    }

    @Override
    public String getTopeHorarioConvenioCurso(Integer rut, String inicio, String termino, Integer asign, String elect, String coord, Integer secc, Integer agno, Integer sem) {
        return getString("get_tope_hor_convenio_curso(" + rut + ",to_date('" + inicio + "','dd-mm-yyyy'), to_date('" + termino + "','dd-mm-yyyy')," + asign + ",'" + elect + "','" + coord + "'," + secc + "," + agno + "," + sem + ")");
    }

    @Override
    public String getTopeHorarioConvenio(Integer rut, String dia, String fechaInicio, String fechaTermino, String horaInicio, String horaTermino) {
        return getString("get_tope_hor_convenio(" + rut + ",'" + dia + "', to_date('" + fechaInicio + "','dd-mm-yyyy'), to_date('" + fechaTermino + "','dd-mm-yyyy'),'" + horaInicio + "','" + horaTermino + "')");
    }

    @Override
    public String getColorHexPorAsignatura(Integer asignatura) {
        return getString("color_pkg.get_color_hex_x_asignatura(:asignatura)", new Object[]{asignatura}, new String[]{"asignatura"}, new Type[]{StandardBasicTypes.INTEGER});
    }

    @Override
    public String getColorHexPorSala(String sala) {
        return getString("color_pkg.get_color_hex_x_sala(:sala)", new Object[]{sala}, new String[]{"sala"}, new Type[]{StandardBasicTypes.STRING});
    }

    @Override
    public String validarInscripcionAlumno(Integer rut, Integer carrera, Integer agnoIng, Integer semIng, Integer mencion, Integer plan, Integer asign, String elect, String coord, Integer secc, Integer agno, Integer sem) {
        String sql = "select inscripcion_pkg.validar_inscripcion_x_alumno(:rut, :carrera, :agnoIng, :semIng, :mencion, :plan, :asign, :elect, :coord, :secc, :agno, :sem, 'T') from dual";
        return ((String) getSession().createSQLQuery(sql)
                .setParameter("rut", rut, StandardBasicTypes.INTEGER)
                .setParameter("carrera", carrera, StandardBasicTypes.INTEGER)
                .setParameter("agnoIng", agnoIng, StandardBasicTypes.INTEGER)
                .setParameter("semIng", semIng, StandardBasicTypes.INTEGER)
                .setParameter("mencion", mencion, StandardBasicTypes.INTEGER)
                .setParameter("plan", plan, StandardBasicTypes.INTEGER)
                .setParameter("asign", asign, StandardBasicTypes.INTEGER)
                .setParameter("elect", elect, StandardBasicTypes.STRING)
                .setParameter("coord", coord, StandardBasicTypes.STRING)
                .setParameter("secc", secc, StandardBasicTypes.INTEGER)
                .setParameter("agno", agno, StandardBasicTypes.INTEGER)
                .setParameter("sem", sem, StandardBasicTypes.INTEGER)
                .uniqueResult());
    }
    
    @Override
    public String validarInscripcionCoord(Integer rut, Integer carrera, Integer agnoIng, Integer semIng, Integer mencion, Integer plan, Integer asign, String elect, String coord, Integer secc, Integer agno, Integer sem) {
        String sql = "select inscripcion_pkg.validar_inscripcion_x_coord(:rut, :carrera, :agnoIng, :semIng, :mencion, :plan, :asign, :elect, :coord, :secc, :agno, :sem, 'T') from dual";
        return ((String) getSession().createSQLQuery(sql)
                .setParameter("rut", rut, StandardBasicTypes.INTEGER)
                .setParameter("carrera", carrera, StandardBasicTypes.INTEGER)
                .setParameter("agnoIng", agnoIng, StandardBasicTypes.INTEGER)
                .setParameter("semIng", semIng, StandardBasicTypes.INTEGER)
                .setParameter("mencion", mencion, StandardBasicTypes.INTEGER)
                .setParameter("plan", plan, StandardBasicTypes.INTEGER)
                .setParameter("asign", asign, StandardBasicTypes.INTEGER)
                .setParameter("elect", elect, StandardBasicTypes.STRING)
                .setParameter("coord", coord, StandardBasicTypes.STRING)
                .setParameter("secc", secc, StandardBasicTypes.INTEGER)
                .setParameter("agno", agno, StandardBasicTypes.INTEGER)
                .setParameter("sem", sem, StandardBasicTypes.INTEGER)
                .uniqueResult());
    }

    @Override
    public String topeHorarioCambioCurso(Integer rut, Integer carrera, Integer agnoIng, Integer semIng, Integer asignOri, String electOri, String coordOri, Integer seccOri, Integer agnoOri, Integer semOri, Integer asignDest, String electDest, String coordDest, Integer seccDest, Integer agnoDest, Integer semDest) {
        return (String) getSession().createSQLQuery("select get_topes_horario_cambio_curso(" + rut + "," + carrera + "," + agnoIng + "," + semIng + "," + asignOri + ",'" + electOri + "','" + coordOri + "'," + seccOri + "," + agnoOri + "," + semOri + "," + asignDest + ",'" + electDest + "','" + coordDest + "'," + seccDest + "," + agnoDest + "," + semDest + "  ) flag from dual").addScalar("flag", STRING).uniqueResult();
    }   

    @Override
    public int getHorasCromoMalla(Integer carrera, Integer mencion, Integer plan) {
        return (Integer) getSession().createSQLQuery("select certificacion_pkg.get_horas_crono_malla(" + carrera + "," + mencion + "," + plan + " ) flag from dual").addScalar("flag", INTEGER).uniqueResult();
    }

    @Override
    public String getNombreNormalizado(String str) {
        return getString("util_pkg.normaliza_nombre(:str)", new Object[]{str}, new String[]{"str"}, new Type[]{StandardBasicTypes.STRING});
    }

    @Override
    public String getCert(Integer cod) {
        return (String) getSession().createSQLQuery("select tce_des_corta des from tcertificado where tce_cod=" + cod).addScalar("des", STRING).uniqueResult();
    }

    @Override
    public String getTramite(Integer cod) {
        return (String) getSession().createSQLQuery("select tra_descripcion from tramite where tra_tramite = :cod")
                .setParameter("cod", cod, StandardBasicTypes.INTEGER)
                .uniqueResult();
    }

    @Override
    public int getFlagEmail(Integer rut, Integer carrera, Integer agnoIng, Integer semIng) {
        return (Integer) getSession().createSQLQuery("select get_flag_ask_email(" + rut + "," + carrera + "," + agnoIng + "," + semIng + ") flag from dual").addScalar("flag", INTEGER).uniqueResult();
    }

    @Override
    public String getEmailUsach(String email) {
        return getString("get_email_usach(:email)", new Object[]{email}, new String[]{"email"}, new Type[]{StandardBasicTypes.STRING});
    }

    @Override
    public int getArancelLogro(int logro, int tprograma) {
        String sql = "select val_valor from valor_arancel_logro where val_logro= :logro AND val_tprog=:tprog";
        return ((Number) getSession().createSQLQuery(sql)
                .setParameter("logro", logro, StandardBasicTypes.INTEGER)
                .setParameter("tprog", tprograma, StandardBasicTypes.INTEGER)
                .uniqueResult()).intValue();
    }
}
