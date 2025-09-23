/*
 * @(#)InscripcionPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.InscripcionRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCar;
import domain.model.AluCarId;
import domain.model.Curso;
import domain.model.CursoId;
import domain.model.Inscripcion;
import domain.model.InscripcionId;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;
import org.hibernate.type.StandardBasicTypes;
import domain.model.InscripcionCursoView;
import infrastructure.util.FormatUtil;
import java.sql.CallableStatement;
import java.sql.Clob;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.Type;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class InscripcionPersistenceImpl extends CrudAbstractDAO<Inscripcion, Long>
        implements InscripcionRepository {
    
    @SuppressWarnings("unchecked")
    @Override
    public String getInscripcionJson(AluCarId id) {

        try {
            Session session = getSession();

            return session.doReturningWork(connection -> {
                String sql = "{ ? = call inscripcion_pkg.get_inscripcion_json(?,?,?,?) }";

                try (CallableStatement stmt = connection.prepareCall(sql)) {
                    stmt.registerOutParameter(1, java.sql.Types.CLOB);
                    stmt.setInt(2, id.getAcaRut());
                    stmt.setInt(3, id.getAcaCodCar());
                    stmt.setInt(4, id.getAcaAgnoIng());
                    stmt.setInt(5, id.getAcaSemIng());

                    stmt.execute();

                    Clob clob = stmt.getClob(1);
                    if (clob != null) {
                        return clob.getSubString(1, (int) clob.length());
                    } else {
                        return "{}";
                    }

                } catch (Exception ex) {
                    return String.format("{\"error\": \"%s\"}", FormatUtil.sanitizeMgsJson(ex.getMessage()));
                }
            });

        } catch (Exception e) {
            return "{\"error\": \"Error interno en el servidor\"}";
        }
    }  
    
    @SuppressWarnings("unchecked")
    @Override
    public String postInscripcionJson(AluCarId id, CursoId cursoId) {

        try {
            Session session = getSession();

            return session.doReturningWork(connection -> {
                String sql = "{ ? = call inscripcion_pkg.post_inscripcion_json(?,?,?,?, ?,?,?,?,?,?,?) }";

                try (CallableStatement stmt = connection.prepareCall(sql)) {
                    stmt.registerOutParameter(1, java.sql.Types.CLOB);
                    stmt.setInt(2, id.getAcaRut());
                    stmt.setInt(3, id.getAcaCodCar());
                    stmt.setInt(4, id.getAcaAgnoIng());
                    stmt.setInt(5, id.getAcaSemIng());
                    
                    stmt.setInt(6, cursoId.getCurAsign());
                    stmt.setString(7, cursoId.getCurElect());
                    stmt.setString(8, cursoId.getCurCoord());
                    stmt.setInt(9, cursoId.getCurSecc());
                    stmt.setInt(10, cursoId.getCurAgno());
                    stmt.setInt(11, cursoId.getCurSem());
                    stmt.setString(12, cursoId.getCurComp());

                    stmt.execute();

                    Clob clob = stmt.getClob(1);
                    if (clob != null) {
                        return clob.getSubString(1, (int) clob.length());
                    } else {
                        return "{}";
                    }

                } catch (Exception ex) {
                    return String.format("{\"error\": \"%s\"}", FormatUtil.sanitizeMgsJson(ex.getMessage()));
                }
            });

        } catch (Exception e) {
            return "{\"error\": \"Error interno en el servidor\"}";
        }
    }    
    
    @SuppressWarnings("unchecked")
    @Override
    public String getCargaJson(AluCarId id) {

        try {
            Session session = getSession();

            return session.doReturningWork(connection -> {
                String sql = "{ ? = call inscripcion_pkg.get_carga_json(?,?,?,?) }";

                try (CallableStatement stmt = connection.prepareCall(sql)) {
                    stmt.registerOutParameter(1, java.sql.Types.CLOB);
                    stmt.setInt(2, id.getAcaRut());
                    stmt.setInt(3, id.getAcaCodCar());
                    stmt.setInt(4, id.getAcaAgnoIng());
                    stmt.setInt(5, id.getAcaSemIng());

                    stmt.execute();

                    Clob clob = stmt.getClob(1);
                    if (clob != null) {
                        return clob.getSubString(1, (int) clob.length());
                    } else {
                        return "{}";
                    }

                } catch (Exception ex) {
                    return String.format("{\"error\": \"%s\"}", FormatUtil.sanitizeMgsJson(ex.getMessage()));
                }
            });

        } catch (Exception e) {
            return "{\"error\": \"Error interno en el servidor\"}";
        }
    } 
    
    @SuppressWarnings("unchecked")
    @Override
    public String getInscripcionSimpleJson(AluCarId id, Integer agno, Integer sem) {

        try {
            Session session = getSession();

            return session.doReturningWork(connection -> {
                String sql = "{ ? = call inscripcion_pkg.get_inscripcion_simple_json(?,?,?,?,?,?) }";

                try (CallableStatement stmt = connection.prepareCall(sql)) {
                    stmt.registerOutParameter(1, java.sql.Types.CLOB);
                    stmt.setInt(2, id.getAcaRut());
                    stmt.setInt(3, id.getAcaCodCar());
                    stmt.setInt(4, id.getAcaAgnoIng());
                    stmt.setInt(5, id.getAcaSemIng());
                    stmt.setInt(6, agno);
                    stmt.setInt(7, sem);

                    stmt.execute();

                    Clob clob = stmt.getClob(1);
                    if (clob != null) {
                        return clob.getSubString(1, (int) clob.length());
                    } else {
                        return "{}";
                    }

                } catch (Exception ex) {
                    return String.format("{\"error\": \"%s\"}", FormatUtil.sanitizeMgsJson(ex.getMessage()));
                }
            });

        } catch (Exception e) {
            return "{\"error\": \"Error interno en el servidor\"}";
        }
    }   

    @SuppressWarnings("unchecked")
    @Override
    public List<Inscripcion> getInscripcionPractica(AluCarId id, Integer agnoIns, Integer semIns) {
        Criteria criteria = getSession().createCriteria(Inscripcion.class);

        String filter = " NOT EXISTS (SELECT 1 FROM calificacion WHERE cal_rut = ? and cal_cod_car = ? and "
                + " cal_agno_ing = ? and cal_sem_ing = ? and cal_asign = ins_asign and cal_agno = ? and cal_sem = ?)";

        criteria.setFetchMode("aluCar", JOIN);
        criteria.setFetchMode("curso", JOIN);
        criteria.createAlias("curso.asignatura", "asignatura");
        criteria.add(eq("id.insAgno", agnoIns));
        criteria.add(eq("id.insSem", semIns));
        criteria.add(eq("asignatura.asiFlagPractica", "S"));
        criteria.add(eq("aluCar.id", id));
        criteria.add(Restrictions.sqlRestriction(filter,
                new Object[]{id.getAcaRut(), id.getAcaCodCar(), id.getAcaAgnoIng(), id.getAcaSemIng(), agnoIns, semIns},
                new Type[]{StandardBasicTypes.INTEGER, StandardBasicTypes.INTEGER, StandardBasicTypes.INTEGER,
                    StandardBasicTypes.INTEGER, StandardBasicTypes.INTEGER, StandardBasicTypes.INTEGER}));
        criteria.addOrder(asc("id.insAsign"));
        criteria.addOrder(asc("id.insElect"));
        criteria.addOrder(asc("insCoord"));
        criteria.addOrder(asc("insSecc"));

        return criteria.list();
    }

    @Override
    public int deleteInscripcion(AluCar aluCar, CursoId id, String proceso, Integer rutRea, String user) {

        Query query = getSession().getNamedQuery("InscripcionRemoveFunction");

        query.setParameter(0, aluCar.getId().getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, aluCar.getId().getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, aluCar.getId().getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, aluCar.getId().getAcaSemIng(), StandardBasicTypes.INTEGER);

        query.setParameter(4, id.getCurAsign(), StandardBasicTypes.INTEGER);
        query.setParameter(5, id.getCurElect(), StandardBasicTypes.STRING);
        query.setParameter(6, id.getCurCoord(), StandardBasicTypes.STRING);
        query.setParameter(7, id.getCurSecc(), StandardBasicTypes.INTEGER);
        query.setParameter(8, id.getCurAgno(), StandardBasicTypes.INTEGER);
        query.setParameter(9, id.getCurSem(), StandardBasicTypes.INTEGER);
        query.setParameter(10, id.getCurComp(), StandardBasicTypes.STRING);

        query.setParameter(11, user, StandardBasicTypes.STRING);
        query.setParameter(12, proceso, StandardBasicTypes.STRING);
        query.setParameter(13, rutRea, StandardBasicTypes.INTEGER);

        query.executeUpdate();

        return 0;
    }

    /**
     * Method description
     *
     * @param curso
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<InscripcionCursoView> findNomina(Curso curso) {
        Criteria criteria = getSession().createCriteria(InscripcionCursoView.class);

        criteria.setFetchMode("aluCar", JOIN);
        criteria.setFetchMode("aluCar.alumno", JOIN);
        criteria.add(eq("curso", curso));
        criteria.createAlias("aluCar.alumno", "alumno");
        criteria.addOrder(asc("alumno.aluPaterno"));
        criteria.addOrder(asc("alumno.aluMaterno"));
        criteria.addOrder(asc("alumno.aluNombre"));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param curso
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<InscripcionCursoView> findNominaRanking(Curso curso) {
        Criteria criteria = getSession().createCriteria(InscripcionCursoView.class);

        criteria.add(eq("curso", curso));
        criteria.createAlias("aluCar.alumno", "alumno");
        criteria.createAlias("aluCar", "aluCar");
        criteria.addOrder(asc("aluCar.acaRanking"));
        criteria.addOrder(asc("alumno.aluPaterno"));
        criteria.addOrder(asc("alumno.aluMaterno"));
        criteria.addOrder(asc("alumno.aluNombre"));

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Integer> getRutInscritos(CursoId id) {
        // Consulta SQL con parámetros
        String sql = "SELECT ins_rut rut FROM nomina_curso WHERE "
                + "ins_asign = :curAsign AND "
                + "ins_elect = :curElect AND "
                + "ins_coord = :curCoord AND "
                + "ins_secc = :curSecc AND "
                + "ins_agno = :curAgno AND "
                + "ins_sem = :curSem AND "
                + "ins_comp = :curComp";

        // Crear la consulta con Hibernate
        SQLQuery query = getSession().createSQLQuery(sql);

        // Asignar los valores de manera segura usando setParameter
        query.setParameter("curAsign", id.getCurAsign(), StandardBasicTypes.INTEGER);
        query.setParameter("curElect", id.getCurElect(), StandardBasicTypes.STRING);
        query.setParameter("curCoord", id.getCurCoord(), StandardBasicTypes.STRING);
        query.setParameter("curSecc", id.getCurSecc(), StandardBasicTypes.INTEGER);
        query.setParameter("curAgno", id.getCurAgno(), StandardBasicTypes.INTEGER);
        query.setParameter("curSem", id.getCurSem(), StandardBasicTypes.INTEGER);
        query.setParameter("curComp", id.getCurComp(), StandardBasicTypes.STRING);

        // Ejecutar la consulta y devolver los resultados
        return query.addScalar("rut", StandardBasicTypes.INTEGER).list();
    }

    @Override
    public void cambioMencion(AluCar aluCar, Integer mencion, Integer agnoIns, Integer semIns) {
        Query query = getSession().createSQLQuery("{ call CAMBIAR_MENCION(?,?,?,?,?,?,?) }");

        query.setParameter(0, aluCar.getId().getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, aluCar.getId().getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, aluCar.getId().getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, aluCar.getId().getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, mencion, StandardBasicTypes.INTEGER);
        query.setParameter(5, agnoIns, StandardBasicTypes.INTEGER);
        query.setParameter(6, semIns, StandardBasicTypes.INTEGER);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Curso> getCursosSwap(AluCar aluCar, CursoId id) {
        AluCarId aluCarId = aluCar.getId();

        String strQuery = "SELECT * "
                + "FROM ( "
                + "    SELECT curso.* "
                + "    FROM derecho, curso, asignatura "
                + "    WHERE der_rut = :rut "
                + "      AND der_cod_car = :carrera "
                + "      AND der_agno_ing = :agnoIng "
                + "      AND der_sem_ing = :semIng "
                + "      AND der_men = :mencion "
                + "      AND cur_asign = der_asign "
                + "      AND der_asign = asi_cod "
                + "      AND asi_tipo = 'P' "
                + "      AND der_tipo = 1 "
                + "      AND cur_agno = :agno "
                + "      AND cur_sem = :sem "
                + ") filtro "
                + "WHERE inscripcion_pkg.puede_hacer_swap( "
                + "        :rut, :carrera, :agnoIng, :semIng, :mencion, :codPlan, :asign, :elect, :coord, :secc, :agno, :sem, "
                + "        cur_asign, cur_elect, cur_coord, cur_secc, cur_agno, cur_sem, :puede_modificar "
                + "    ) = 'SI' "
                + "ORDER BY cur_asign, cur_elect, cur_coord, cur_secc";

        // Crear la consulta SQL con parámetros
        SQLQuery query = getSession().createSQLQuery(strQuery).addEntity("curso", Curso.class);

        // Asignar los parámetros de manera segura
        query.setParameter("rut", aluCarId.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter("carrera", aluCarId.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter("agnoIng", aluCarId.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter("semIng", aluCarId.getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter("mencion", aluCar.getAcaCodMen(), StandardBasicTypes.INTEGER);
        query.setParameter("codPlan", aluCar.getAcaCodPlan(), StandardBasicTypes.INTEGER);
        query.setParameter("asign", id.getCurAsign(), StandardBasicTypes.INTEGER);
        query.setParameter("elect", id.getCurElect(), StandardBasicTypes.STRING);
        query.setParameter("coord", id.getCurCoord(), StandardBasicTypes.STRING);
        query.setParameter("secc", id.getCurSecc(), StandardBasicTypes.INTEGER);
        query.setParameter("agno", id.getCurAgno(), StandardBasicTypes.INTEGER);
        query.setParameter("sem", id.getCurSem(), StandardBasicTypes.INTEGER);
        
        /// OOOOJJJJOOOO
        ///query.setParameter("puede_modificar", aluCar.getParametros().getPuedeModificar(), StandardBasicTypes.STRING);

        // Ejecutar la consulta y devolver los resultados
        return query.list();
    }

    @Override
    public void swap(AluCarId id, CursoId oldId, CursoId newId) {

        Query query = getSession().getNamedQuery("IscripcionSwapFunction");
        query.setParameter(0, id.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, id.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, id.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, id.getAcaSemIng(), StandardBasicTypes.INTEGER);

        query.setParameter(4, oldId.getCurAsign(), StandardBasicTypes.INTEGER);
        query.setParameter(5, oldId.getCurElect(), StandardBasicTypes.STRING);
        query.setParameter(6, oldId.getCurCoord(), StandardBasicTypes.STRING);
        query.setParameter(7, oldId.getCurSecc(), StandardBasicTypes.INTEGER);
        query.setParameter(8, oldId.getCurAgno(), StandardBasicTypes.INTEGER);
        query.setParameter(9, oldId.getCurSem(), StandardBasicTypes.INTEGER);

        query.setParameter(10, newId.getCurAsign(), StandardBasicTypes.INTEGER);
        query.setParameter(11, newId.getCurElect(), StandardBasicTypes.STRING);
        query.setParameter(12, newId.getCurCoord(), StandardBasicTypes.STRING);
        query.setParameter(13, newId.getCurSecc(), StandardBasicTypes.INTEGER);
        query.setParameter(14, newId.getCurAgno(), StandardBasicTypes.INTEGER);
        query.setParameter(15, newId.getCurSem(), StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }

    @Override
    public void traspaso(AluCarId id, CursoId oldId, CursoId newId) {
        Query query = getSession().getNamedQuery("IscripcionTraspasoFunction");
        query.setParameter(0, id.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, id.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, id.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, id.getAcaSemIng(), StandardBasicTypes.INTEGER);

        query.setParameter(4, oldId.getCurAsign(), StandardBasicTypes.INTEGER);
        query.setParameter(5, oldId.getCurElect(), StandardBasicTypes.STRING);
        query.setParameter(6, oldId.getCurCoord(), StandardBasicTypes.STRING);
        query.setParameter(7, oldId.getCurSecc(), StandardBasicTypes.INTEGER);
        query.setParameter(8, oldId.getCurAgno(), StandardBasicTypes.INTEGER);
        query.setParameter(9, oldId.getCurSem(), StandardBasicTypes.INTEGER);
        query.setParameter(10, oldId.getCurComp(), StandardBasicTypes.STRING);

        query.setParameter(11, newId.getCurAsign(), StandardBasicTypes.INTEGER);
        query.setParameter(12, newId.getCurElect(), StandardBasicTypes.STRING);
        query.setParameter(13, newId.getCurCoord(), StandardBasicTypes.STRING);
        query.setParameter(14, newId.getCurSecc(), StandardBasicTypes.INTEGER);
        query.setParameter(15, newId.getCurAgno(), StandardBasicTypes.INTEGER);
        query.setParameter(16, newId.getCurSem(), StandardBasicTypes.INTEGER);
        query.setParameter(17, newId.getCurComp(), StandardBasicTypes.STRING);

        query.executeUpdate();
    }

    @Override
    public void setForce(AluCarId id, InscripcionId idIns, String force) {
        Query query = getSession().getNamedQuery("IscripcionForceFunction");
        query.setParameter(0, id.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, id.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, id.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, id.getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, idIns.getInsAsign(), StandardBasicTypes.INTEGER);
        query.setParameter(5, idIns.getInsElect(), StandardBasicTypes.STRING);
        query.setParameter(6, idIns.getInsAgno(), StandardBasicTypes.INTEGER);
        query.setParameter(7, idIns.getInsSem(), StandardBasicTypes.INTEGER);
        query.setParameter(8, idIns.getInsComp(), StandardBasicTypes.STRING);
        query.setParameter(9, force, StandardBasicTypes.STRING);

        query.executeUpdate();
    }

    @Override
    public String getResumen(Integer tcarrera, Integer especialidad, String jornada, Integer agno, Integer sem, Integer rut, String perfil) {

        String sql = "SELECT inscripcion_pkg.get_resumen_inscripcion(:tcarrera,:especialidad,:jornada,:rut,:perfil,:agno,:sem) from dual";

        Query query = getSession().createSQLQuery(sql);

        query.setParameter("tcarrera", tcarrera, StandardBasicTypes.INTEGER);
        query.setParameter("especialidad", especialidad, StandardBasicTypes.INTEGER);
        query.setParameter("jornada", jornada, StandardBasicTypes.STRING);
        query.setParameter("rut", rut, StandardBasicTypes.INTEGER);
        query.setParameter("perfil", perfil, StandardBasicTypes.STRING);
        query.setParameter("agno", agno, StandardBasicTypes.INTEGER);
        query.setParameter("sem", sem, StandardBasicTypes.INTEGER);

        try {
            Clob resultClob = (Clob) query.uniqueResult();
            String resultString = resultClob.getSubString(1, (int) resultClob.length());

            return resultString.replaceAll("\\s+", "");

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
