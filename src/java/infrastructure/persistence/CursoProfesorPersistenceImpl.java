/*
 * @(#)CursoProfesorPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence;

import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCarId;
import domain.model.Curso;
import domain.model.CursoProfesor;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.sql.JoinType.LEFT_OUTER_JOIN;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import domain.repository.CursoProfesorRepository;
import java.sql.CallableStatement;
import java.sql.Clob;
import org.hibernate.Session;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CursoProfesorPersistenceImpl extends CrudAbstractDAO<CursoProfesor, Long>
        implements CursoProfesorRepository {

    /**
     * Method description
     *
     * @param curso
     * @param rut
     * @return
     */
    @Override
    public boolean exists(Curso curso, Integer rut) {
        Criteria criteria = getSession().createCriteria(CursoProfesor.class);

        criteria.add(eq("curso.id", curso.getId()));
        criteria.add(eq("id.cproRut", rut));

        return criteria.uniqueResult() != null;
    }

    /**
     * Method description
     *
     *
     * @param id
     * @param agno
     * @param sem
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CursoProfesor> getCursosEncuesta(AluCarId id, Integer agno, Integer sem) {

        // Creamos el Criteria para la clase CursoProfesor
        Criteria criteria = getSession().createCriteria(CursoProfesor.class);

        // Construcción de la subconsulta de manera segura utilizando parámetros
        String filter = "exists (select 1 from inscripcion ins, alu_car ac "
                + "where ins.ins_rut = ac.aca_rut and ins.ins_cod_car = ac.aca_cod_car and "
                + "ins.ins_agno_ing = ac.aca_agno_ing and ins.ins_sem_ing = ac.aca_sem_ing and "
                + "ins.ins_asign = this_.cpro_asign and ins.ins_elect = this_.cpro_elect and "
                + "ins.ins_coord = this_.cpro_coord and ins.ins_secc = this_.cpro_secc and "
                + "ins.ins_agno = this_.cpro_agno and ins.ins_sem = this_.cpro_sem and "
                + "ins.ins_rut = ? and ins.ins_cod_car = ? and ins.ins_agno_ing = ? and "
                + "ins.ins_sem_ing = ? and ins.ins_agno = ? and ins.ins_sem = ?)";

        // Agregar sqlRestriction con parámetros para evitar inyecciones SQL
        criteria.add(Restrictions.sqlRestriction(filter,
                new Object[]{id.getAcaRut(), id.getAcaCodCar(), id.getAcaAgnoIng(), id.getAcaSemIng(), agno, sem},
                new Type[]{StandardBasicTypes.INTEGER, StandardBasicTypes.INTEGER, StandardBasicTypes.INTEGER,
                    StandardBasicTypes.INTEGER, StandardBasicTypes.INTEGER, StandardBasicTypes.INTEGER}));

        // Crear alias y relaciones entre las entidades
        criteria.createAlias("curso", "curso");
        criteria.createAlias("curso.asignatura", "asignatura");
        criteria.createCriteria("curso.electivo", LEFT_OUTER_JOIN);
        //criteria.createCriteria("curso.electivo", Criteria.LEFT_JOIN); // Corrección del LEFT_OUTER_JOIN

        // Restricciones adicionales para agno y sem
        criteria.add(Restrictions.eq("curso.id.curAgno", agno));
        criteria.add(Restrictions.eq("curso.id.curSem", sem));

        // Ejecutar la consulta y devolver los resultados
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CursoProfesor> getCursosEncuesta(Integer rut) {
        Criteria criteria = getSession().createCriteria(CursoProfesor.class);

        criteria.createAlias("curso", "curso");
        criteria.createAlias("profesor", "profesor");
        criteria.add(eq("profesor.profRut", rut));

        return criteria.list();
    }

    /**
     *
     * @param rut
     * @param agno
     * @param sem
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CursoProfesor> findCursosMallaJefeArea(Integer rut, Integer agno, Integer sem) {
        // Creamos el Criteria para la clase CursoProfesor
        Criteria criteria = getSession().createCriteria(CursoProfesor.class, "cursoProfesor");

        // Alias para las relaciones entre CursoProfesor, Profesor y Curso
        criteria.createAlias("cursoProfesor.profesor", "profesor");
        criteria.createAlias("cursoProfesor.curso", "curso");

        // Subconsulta utilizando sqlRestriction, ya que la tabla 'curso_actual_jefe_area' no es una entidad
        String subquery = "exists (select null from curso_actual_jefe_area ca "
                + "where ca.ca_asign = cpro_asign "
                + "and ca.ca_elect = cpro_elect "
                + "and ca.ca_coord = cpro_coord "
                + "and ca.ca_secc = cpro_secc "
                + "and ca.ca_agno = cpro_agno "
                + "and ca.ca_sem = cpro_sem "
                + "and ca.ca_agno = ? "
                + "and ca.ca_sem = ? "
                + "and ca.lrea_rut = ?)";

        // Agregamos el sqlRestriction con los parámetros para la subconsulta
        criteria.add(Restrictions.sqlRestriction(subquery, new Object[]{agno, sem, rut},
                new Type[]{StandardBasicTypes.INTEGER, StandardBasicTypes.INTEGER, StandardBasicTypes.INTEGER}));

        // Restricciones adicionales sobre los campos de la clase CursoProfesor
        criteria.add(Restrictions.eq("cursoProfesor.id.cproAgno", agno));
        criteria.add(Restrictions.eq("cursoProfesor.id.cproSem", sem));

        // Ordenación de los resultados
        criteria.addOrder(Order.asc("cursoProfesor.id.cproAsign"));
        criteria.addOrder(Order.asc("cursoProfesor.id.cproElect"));
        criteria.addOrder(Order.asc("cursoProfesor.id.cproCoord"));
        criteria.addOrder(Order.asc("cursoProfesor.id.cproSecc"));

        // Ejecutar la consulta y retornar los resultados
        return criteria.list();
    }

    /*@SuppressWarnings("unchecked")
    @Override
    public List<CursoProfesor> find(Integer tcarrera, Integer especialidad, String jornada, Integer agno, Integer sem, Integer rut, String perfil, String tipo) {
        try {
            Query query = getSession().getNamedQuery("GetCursosProfFunction");
            query.setParameter(0, tcarrera, StandardBasicTypes.INTEGER);
            query.setParameter(1, especialidad, StandardBasicTypes.INTEGER);
            query.setParameter(2, jornada, StandardBasicTypes.STRING);
            query.setParameter(3, rut, StandardBasicTypes.INTEGER);
            query.setParameter(4, perfil, StandardBasicTypes.STRING);
            query.setParameter(5, agno, StandardBasicTypes.INTEGER);
            query.setParameter(6, sem, StandardBasicTypes.INTEGER);

            List<Object[]> results = query.list();

            // Usamos Streams para transformar los resultados
            return results.stream()
                    .map(obj -> {
                        Curso curso = new Curso();
                        CursoProfesor cursoProf = new CursoProfesor();
                        CursoId id = new CursoId();
                        Profesor prof = new Profesor();

                        // Asignar valores a id y curso
                        id.setCurAsign(((Number) obj[0]).intValue());
                        id.setCurElect((String) obj[1]);
                        id.setCurCoord((String) obj[2]);
                        id.setCurSecc(((Number) obj[3]).intValue());
                        id.setCurAgno(((Number) obj[4]).intValue());
                        id.setCurSem(((Number) obj[5]).intValue());
                        id.setCurComp("T");

                        curso.setCurNombre((String) obj[6]);

                        // Asignar valores a profesor
                        prof.setProfRut(((Number) obj[7]).intValue());
                        prof.setProfPat((String) obj[8]);
                        prof.setProfMat((String) obj[9]);
                        prof.setProfNom((String) obj[10]);

                        // Asignar datos a cursoProf
                        curso.setId(id);
                        cursoProf.setCurso(curso);
                        cursoProf.setProfesor(prof);

                        return cursoProf;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }*/
    
    @SuppressWarnings("unchecked")
    @Override
    public String findJson(Integer tcarrera, Integer especialidad, String jornada, Integer agno, Integer sem, Integer rut, String perfil, String tipo) {

        try {
            Session session = getSession();

            // Usar doReturningWork para trabajar con la conexión real (Hikari lo maneja por detrás)
            String result = null;
            result = session.doReturningWork(connection -> {
                String json = null;

                try (CallableStatement stmt = connection.prepareCall("{ ? = call curso_pkg.get_cursos_prof_json(?,?,?,?,?,?,?) }")) {
                    stmt.registerOutParameter(1, java.sql.Types.CLOB);

                    stmt.setInt(2, tcarrera);
                    stmt.setInt(3, especialidad);
                    stmt.setString(4, jornada);
                    stmt.setInt(5, agno);
                    stmt.setInt(6, sem);
                    stmt.setInt(7, rut);
                    stmt.setString(8, perfil);

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
}
