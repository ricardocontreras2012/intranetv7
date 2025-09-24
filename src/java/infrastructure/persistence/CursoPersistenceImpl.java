/*
 * @(#)CursoPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence;

import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCar;
import domain.model.Curso;
import domain.model.CursoId;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import static org.hibernate.sql.JoinType.LEFT_OUTER_JOIN;
import static org.hibernate.criterion.Order.desc;
import org.hibernate.criterion.Restrictions;
import static org.hibernate.criterion.Restrictions.eq;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import infrastructure.support.CursoResumenSupport;
import java.util.Collections;
import java.util.stream.Collectors;
import domain.repository.CursoRepository;
import infrastructure.util.FormatUtil;
import java.sql.CallableStatement;
import java.sql.Clob;

import org.hibernate.Session;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CursoPersistenceImpl extends CrudAbstractDAO<Curso, Long> implements CursoRepository {

    @SuppressWarnings("unchecked")
    @Override
    public List<Curso> find(Integer asignatura) {
        Criteria criteria = getSession().createCriteria(Curso.class);

        criteria.setFetchMode("asignatura", JOIN);
        criteria.add(eq("id.curAsign", asignatura));
        criteria.addOrder(desc("id.curAgno"));
        criteria.addOrder(desc("id.curSem"));

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public String findJson(Integer rut, Integer carrera, Integer agnoIng, Integer semIng, Integer asign) {

        try {
            Session session = getSession();

            return session.doReturningWork(connection -> {
                String sql = "{ ? = call curso_pkg.get_cursos_inscripcion_json(?,?,?,?,?) }";

                try (CallableStatement stmt = connection.prepareCall(sql)) {
                    stmt.registerOutParameter(1, java.sql.Types.CLOB);
                    stmt.setInt(2, rut);
                    stmt.setInt(3, carrera);
                    stmt.setInt(4, agnoIng);
                    stmt.setInt(5, semIng);                    
                    stmt.setInt(6, asign);

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
    public List<Curso> findAll(Integer rut, String userType, Integer asignatura, Integer agno, Integer sem) {
        Criteria criteria = getSession().createCriteria(Curso.class);

        // Parámetros nombrados para evitar inyección sql
        String sqlFilter = "perfil_intranet_pkg.curso_propio(this_.cur_asign, :userType, :rut) > 0";

        criteria.setFetchMode("asignatura", JOIN);
        criteria.add(eq("id.curAsign", asignatura));
        criteria.add(eq("id.curAgno", agno));
        criteria.add(eq("id.curSem", sem));

        // Añadir el sqlRestriction usando parámetros
        criteria.add(Restrictions.sqlRestriction(sqlFilter,
                new Object[]{userType, rut},
                new Type[]{StringType.INSTANCE, IntegerType.INSTANCE}));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param curso
     * @return
     */
    @Override
    public Curso find(Curso curso) {
        Criteria criteria = getSession().createCriteria(Curso.class);

        criteria.add(eq("id", curso.getId()));

        return (Curso) criteria.uniqueResult();
    }

    /**
     * Method description
     *
     * @param cursoId
     * @return
     */
    @Override
    public Curso find(CursoId cursoId) {

        Criteria criteria = getSession().createCriteria(Curso.class);

        criteria.setFetchMode("asignatura", JOIN);
        criteria.createCriteria("electivo", LEFT_OUTER_JOIN);
        criteria.add(eq("id", cursoId));

        return (Curso) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Curso> find(Integer rut, Integer agno, Integer sem, String proyecto) {
        String sql = "select * from curso "
                + "join curso_profesor on cur_asign = cpro_asign and cur_elect = cpro_elect and cur_coord = cpro_coord and cur_secc = cpro_secc and cur_agno = cpro_agno and cur_sem = cpro_sem "
                + "join curso_car ccar on ccar_asign = cpro_asign and ccar_elect = cpro_elect and ccar_coord = cpro_coord "
                + "and ccar_secc = cpro_secc and ccar_agno = cpro_agno and ccar_sem = cpro_sem "
                + "join mencion men on men_cod_car = ccar_cod_car and men_cod_men = ccar_cod_men "
                + "join unidad uni on uni_cod = men_unidad "
                + "join proyecto proy on proy_uni = uni_cod "
                + "where proy_cod = ? and cpro_prof_rut = ? and cpro_agno = ? and cpro_sem = ?";

        SQLQuery query = getSession().createSQLQuery(sql).addEntity("Curso", Curso.class);

        // Establecemos los parámetros para la consulta
        query.setParameter(0, proyecto, StandardBasicTypes.STRING);
        query.setParameter(1, rut, StandardBasicTypes.INTEGER);
        query.setParameter(2, agno, StandardBasicTypes.INTEGER);
        query.setParameter(3, sem, StandardBasicTypes.INTEGER);

        // Ejecutamos la consulta y obtenemos los resultados
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Curso> findAyudantia(Integer rut, Integer agno, Integer sem, String proyecto) {

        String sql = "select * from Curso "
                + "join curso_ayudante on cur_asign = cayu_asign and cur_elect = cayu_elect and cur_coord = cayu_coord and cur_secc = cayu_secc and cur_agno = cayu_agno and cur_sem = cayu_sem "
                + "join curso_car ccar on ccar_asign = cayu_asign and ccar_elect = cayu_elect and ccar_coord = cayu_coord and ccar_secc = cayu_secc and ccar_agno = cayu_agno and ccar_sem = cayu_sem "
                + "join mencion men on men_cod_car = ccar_cod_car and men_cod_men = ccar_cod_men "
                + "join unidad uni on uni_cod = men_unidad "
                + "join proyecto proy on proy_uni = uni_cod "
                + "where proy_cod = ? and cayu_rut = ? and cayu_agno = ? and cayu_sem = ?";

        SQLQuery query = getSession().createSQLQuery(sql).addEntity("Curso", Curso.class);

        // Establecemos los parámetros para la consulta
        query.setParameter(0, proyecto, StandardBasicTypes.STRING);
        query.setParameter(1, rut, StandardBasicTypes.INTEGER);
        query.setParameter(2, agno, StandardBasicTypes.INTEGER);
        query.setParameter(3, sem, StandardBasicTypes.INTEGER);

        // Ejecutamos la consulta y obtenemos los resultados
        return query.list();
    }

    /**
     * Method description
     *
     * @param id
     * @return
     */
    @Override
    public int remove(CursoId id) {

        Query query = getSession().getNamedQuery("RemoveCursoFunction");

        query.setParameter(0, id.getCurAsign(), StandardBasicTypes.INTEGER);
        query.setParameter(1, id.getCurElect(), StandardBasicTypes.STRING);
        query.setParameter(2, id.getCurCoord(), StandardBasicTypes.STRING);
        query.setParameter(3, id.getCurSecc(), StandardBasicTypes.INTEGER);
        query.setParameter(4, id.getCurAgno(), StandardBasicTypes.INTEGER);
        query.setParameter(5, id.getCurSem(), StandardBasicTypes.INTEGER);
        query.setParameter(6, id.getCurComp(), StandardBasicTypes.STRING);

        return query.executeUpdate();
    }

    /**
     * Method description
     *
     * @param agno
     * @param sem
     * @param tipoCarrera
     * @param especialidad
     * @param jornada
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CursoResumenSupport> findResumenMateriales(Integer agno, Integer sem, Integer tipoCarrera,
            Integer especialidad, String jornada) {
        try {
            Query query = getSession().getNamedQuery("ResumenMaterialesFunction");

            query.setParameter(0, agno, StandardBasicTypes.INTEGER);
            query.setParameter(1, sem, StandardBasicTypes.INTEGER);
            query.setParameter(2, tipoCarrera, StandardBasicTypes.INTEGER);
            query.setParameter(3, especialidad, StandardBasicTypes.INTEGER);

            List<Object[]> results = query.list();

            // Usar Streams para transformar los resultados
            return results.stream()
                    .map(CursoResumenSupport::new) // Crear un objeto CursoResumenSupport para cada elemento
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // Manejo de excepciones: devolver lista vacía en lugar de lanzar error o devolver null
            return Collections.emptyList();
        }
    }

    /**
     * Method description
     *
     * @param agno
     * @param sem
     * @param tipoCarrera
     * @param especialidad
     * @param jornada
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CursoResumenSupport> findResumenReportes(Integer agno, Integer sem, Integer tipoCarrera,
            Integer especialidad, String jornada) {
        try {
            Query query = getSession().getNamedQuery("ResumenReportesFunction");

            query.setParameter(0, agno, StandardBasicTypes.INTEGER);
            query.setParameter(1, sem, StandardBasicTypes.INTEGER);
            query.setParameter(2, tipoCarrera, StandardBasicTypes.INTEGER);
            query.setParameter(3, especialidad, StandardBasicTypes.INTEGER);

            List<Object[]> results = query.list();

            // Usar Streams para transformar los resultados en objetos CursoResumenSupport
            return results.stream()
                    .map(CursoResumenSupport::new) // Crear un objeto CursoResumenSupport para cada resultado
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // Manejo de excepciones: devolver lista vacía en lugar de lanzar error o devolver null
            return Collections.emptyList();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CursoResumenSupport> findResumenReportes(Integer rut, Integer agno, Integer sem) {
        try {
            Query query = getSession().getNamedQuery("ResumenReportesJefeAreaFunction");

            query.setParameter(0, rut, StandardBasicTypes.INTEGER);
            query.setParameter(1, agno, StandardBasicTypes.INTEGER);
            query.setParameter(2, sem, StandardBasicTypes.INTEGER);

            List<Object[]> results = query.list();

            // Usar Streams para transformar los resultados en objetos CursoResumenSupport
            return results.stream()
                    .map(CursoResumenSupport::new) // Crear un objeto CursoResumenSupport para cada resultado
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // Manejo de excepciones: devolver lista vacía en lugar de lanzar error o devolver null
            return Collections.emptyList();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CursoResumenSupport> findResumenMateriales(Integer rut, Integer agno, Integer sem) {
        try {
            Query query = getSession().getNamedQuery("ResumenMaterialesJefeAreaFunction");

            query.setParameter(0, rut, StandardBasicTypes.INTEGER);
            query.setParameter(1, agno, StandardBasicTypes.INTEGER);
            query.setParameter(2, sem, StandardBasicTypes.INTEGER);

            List<Object[]> results = query.list();

            // Transformar los resultados en objetos CursoResumenSupport usando Streams
            return results.stream()
                    .map(CursoResumenSupport::new) // Crear un objeto CursoResumenSupport para cada resultado
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // Manejo de excepciones: devolver una lista vacía en lugar de lanzar una excepción
            return Collections.emptyList();
        }
    }

    /**
     *
     * @param id
     * @param modo
     */
    @Override
    public void setModoEvaluacion(CursoId id, String modo) {
        Query query = getSession().createSQLQuery("{ call SET_MODALIDAD_EVALUACION(?,?,?,?,?,?,?) }");

        query.setParameter(0, id.getCurAsign(), StandardBasicTypes.INTEGER);
        query.setParameter(1, id.getCurElect(), StandardBasicTypes.STRING);
        query.setParameter(2, id.getCurCoord(), StandardBasicTypes.STRING);
        query.setParameter(3, id.getCurSecc(), StandardBasicTypes.INTEGER);
        query.setParameter(4, id.getCurAgno(), StandardBasicTypes.INTEGER);
        query.setParameter(5, id.getCurSem(), StandardBasicTypes.INTEGER);
        query.setParameter(6, modo, StandardBasicTypes.STRING);
        query.executeUpdate();
    }

    /**
     *
     * @param tcarrera
     * @param especialidad
     * @param jornada
     * @param agno
     * @param sem
     * @param rut
     * @param perfil
     * @param tipo
     * @return
     */

    @SuppressWarnings("unchecked")
    @Override
    public String findJson(Integer tcarrera, Integer especialidad, String jornada, Integer agno, Integer sem, Integer rut, String perfil, String tipo) {

        try {
            Session session = getSession();

            // Usar doReturningWork para trabajar con la conexión real (Hikari lo maneja por detrás)
            String result;
            result = session.doReturningWork(connection -> {
                String json = null;

                try (CallableStatement stmt = connection.prepareCall("{ ? = call curso_pkg.get_cursos_json(?,?,?,?,?,?,?,?) }")) {
                    stmt.registerOutParameter(1, java.sql.Types.CLOB);
                    stmt.setInt(2, tcarrera);
                    stmt.setInt(3, especialidad);
                    stmt.setString(4, jornada);
                    stmt.setInt(5, agno);
                    stmt.setInt(6, sem);
                    stmt.setString(7, tipo);
                    stmt.setInt(8, rut);
                    stmt.setString(9, perfil);
                    stmt.execute();

                    Clob clob = stmt.getClob(1);
                    if (clob != null) {
                        json = clob.getSubString(1, (int) clob.length());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    json = "{\"error\": \"" + ex.getMessage() + "\"}";
                }

                return json;
            });

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ""; // Retorna una lista vacía en caso de excepción
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public String transversalesJson(Integer agno, Integer sem) {

        try {
            Session session = getSession();

            // Usar doReturningWork para trabajar con la conexión real (Hikari lo maneja por detrás)
            String result;
            result = session.doReturningWork(connection -> {
                String json = null;

                try (CallableStatement stmt = connection.prepareCall("{ ? = call curso_pkg.get_transversales_json(?,?) }")) {
                    stmt.registerOutParameter(1, java.sql.Types.CLOB);
                    stmt.setInt(2, agno);
                    stmt.setInt(3, sem);
                    stmt.execute();

                    Clob clob = stmt.getClob(1);
                    if (clob != null) {
                        json = clob.getSubString(1, (int) clob.length());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    json = "{\"error\": \"" + ex.getMessage() + "\"}";
                }

                return json;
            });

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ""; // Retorna una lista vacía en caso de excepción
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Curso> findxPerfilPeriodo(Integer agno, Integer sem, Integer rut, String perfil) {
        try {
            Query query = getSession().getNamedQuery("GetCursosxPerfilPeriodoFunction");

            query.setParameter(0, perfil, StandardBasicTypes.STRING);
            query.setParameter(1, rut, StandardBasicTypes.INTEGER);
            query.setParameter(2, agno, StandardBasicTypes.INTEGER);
            query.setParameter(3, sem, StandardBasicTypes.INTEGER);

            List<Object[]> results = query.list();

            // Usar Streams para transformar los resultados en objetos Curso
            return results.stream()
                    .map(obj -> {
                        Curso curso = new Curso();
                        CursoId id = new CursoId();

                        id.setCurAsign(((Number) obj[0]).intValue());
                        id.setCurElect((String) obj[1]);
                        id.setCurCoord((String) obj[2]);
                        id.setCurSecc(((Number) obj[3]).intValue());
                        id.setCurAgno(((Number) obj[4]).intValue());
                        id.setCurSem(((Number) obj[5]).intValue());
                        id.setCurComp("T");

                        curso.setCurNombre((String) obj[7]);
                        curso.setCurProfesores((String) obj[8]);
                        curso.setCurAyudantes((String) obj[9]);
                        curso.setCurHorario((String) obj[10]);
                        curso.setCurSalas((String) obj[11]);
                        curso.setCurCupoIni(((Number) obj[12]).intValue());
                        curso.setCurCupoDis(((Number) obj[13]).intValue());
                        curso.setCurFechaInicio((Date) obj[14]);
                        curso.setCurFechaTermino((Date) obj[15]);
                        curso.setCurJorDiurno((String) obj[16]);
                        curso.setCurJorVesp((String) obj[17]);
                        curso.setCurTipo((String) obj[18]);

                        curso.setId(id);
                        return curso;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); // Retorna lista vacía en caso de error
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Curso> findxUser(Integer rut, String tipo) {
        try {
            Query query = getSession().getNamedQuery("GetCursosUserFunction");
            query.setParameter(0, tipo, StandardBasicTypes.STRING);
            query.setParameter(1, rut, StandardBasicTypes.INTEGER);

            List<Object[]> results = query.list();

            // Usar Streams para transformar los resultados en objetos Curso
            return results.stream()
                    .map(obj -> {
                        Curso curso = new Curso();
                        CursoId id = new CursoId();

                        id.setCurAsign(((Number) obj[0]).intValue());
                        id.setCurElect((String) obj[1]);
                        id.setCurCoord((String) obj[2]);
                        id.setCurSecc(((Number) obj[3]).intValue());
                        id.setCurAgno(((Number) obj[4]).intValue());
                        id.setCurSem(((Number) obj[5]).intValue());
                        id.setCurComp((String) obj[6]);

                        curso.setCurNombre((String) obj[7]);
                        curso.setCurProfesores((String) obj[8]);
                        curso.setId(id);

                        return curso;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); // Retorna una lista vacía en caso de error
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Curso> getCursosSolicitudInscripcion(AluCar aluCar, Integer agno, Integer sem) {

        Integer mencion = aluCar.getAcaCodMen();
        Integer tipo = aluCar.getPlan().getMencion().getCarrera().getTcarrera().getTcrCtip();
        if (tipo == 16 && mencion == 0) {
            mencion = 1;
        }

        String sql = "select curso.* from curso, malla  where cur_agno=? and cur_sem=? and "
                + "ma_asign = cur_asign and "
                + "ma_cod_car = ? and "
                + "ma_cod_men = ? and "
                + "ma_cod_plan = ? and "
                + "not exists(select * from calificacion,asignatura where asi_cod = cal_asign and asi_elect='N' and "
                + "cal_rut = ? and "
                + "cal_cod_car= ? and "
                + "cal_agno_ing = ? and "
                + "cal_sem_ing = ? and "
                + "cal_asign = ma_asign and "
                + "cal_sit_alu ='A')";

        SQLQuery query = getSession().createSQLQuery(sql).addEntity("curso", Curso.class);

        query.setParameter(0, agno, StandardBasicTypes.INTEGER);
        query.setParameter(1, sem, StandardBasicTypes.INTEGER);
        query.setParameter(2, aluCar.getId().getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(3, mencion, StandardBasicTypes.INTEGER);
        query.setParameter(4, aluCar.getAcaCodPlan(), StandardBasicTypes.INTEGER);
        query.setParameter(5, aluCar.getId().getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(6, aluCar.getId().getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(7, aluCar.getId().getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(8, aluCar.getId().getAcaSemIng(), StandardBasicTypes.INTEGER);

        return query.list();
    }

    @Override
    public void addTransversal(
            Integer asign, String elect, String coord, Integer secc, Integer agno, Integer sem) {

        Query query = getSession().getNamedQuery("AddCursoTransversalFunction");

        query.setParameter(0, asign, StandardBasicTypes.INTEGER);
        query.setParameter(1, elect, StandardBasicTypes.STRING);
        query.setParameter(2, coord, StandardBasicTypes.STRING);
        query.setParameter(3, secc, StandardBasicTypes.INTEGER);
        query.setParameter(4, agno, StandardBasicTypes.INTEGER);
        query.setParameter(5, sem, StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }

    @Override
    public void removeTransversal(
            Integer asign, String elect, String coord, Integer secc, Integer agno, Integer sem) {

        Query query = getSession().getNamedQuery("RemoveCursoTransversalFunction");

        query.setParameter(0, asign, StandardBasicTypes.INTEGER);
        query.setParameter(1, elect, StandardBasicTypes.STRING);
        query.setParameter(2, coord, StandardBasicTypes.STRING);
        query.setParameter(3, secc, StandardBasicTypes.INTEGER);
        query.setParameter(4, agno, StandardBasicTypes.INTEGER);
        query.setParameter(5, sem, StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }
}
