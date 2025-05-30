/*
 * @(#)ProfesorPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.ProfesorPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.Curso;
import domain.model.CursoId;
import domain.model.CursoProfesor;
import domain.model.Profesor;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Order.desc;
import org.hibernate.criterion.Restrictions;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.idEq;
import static org.hibernate.criterion.Restrictions.sqlRestriction;
import static org.hibernate.sql.JoinType.LEFT_OUTER_JOIN;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import infrastructure.util.ContextUtil;
import domain.model.ProfesorActivoView;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ProfesorPersistenceImpl extends CrudAbstractDAO<Profesor, Long> implements ProfesorPersistence {

    /**
     * Method description
     *
     * @param rut
     * @param password
     * @return
     */
    @Override
    public Profesor find(Integer rut, String password) {
        Criteria criteria = getSession().createCriteria(Profesor.class);

        criteria.add(idEq(rut));
        criteria.add(sqlRestriction("valid_user_profesor(prof_rut, (?))=1", password, new StringType()));

        return (Profesor) criteria.uniqueResult();
    }

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    @Override
    public Profesor find(Integer rut) {
        Criteria criteria = getSession().createCriteria(Profesor.class);

        criteria.add(idEq(rut));

        return (Profesor) criteria.uniqueResult();
    }

    /**
     * Method description
     *
     * @param paterno
     * @param materno
     * @param nombre
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Profesor> find(String paterno, String materno, String nombre) {

        String sql = "SELECT * FROM profesor WHERE (normaliza_string(upper(prof_pat)) LIKE  normaliza_string(:paterno)||'%') AND "
                + "((prof_mat IS NULL AND :materno IS NULL) OR  (normaliza_string(upper(prof_mat)) LIKE normaliza_string(:materno)||'%')) AND "
                + "(normaliza_string(upper(prof_nom)) LIKE '%'||normaliza_string(:nombre)||'%') ORDER BY normaliza_string(upper(prof_pat)), normaliza_string(upper(prof_mat)), prof_nom";

        SQLQuery query = getSession().createSQLQuery(sql).addEntity("profesor", Profesor.class);
        query.setParameter("paterno", paterno.toUpperCase(ContextUtil.getLocale()), StandardBasicTypes.STRING);
        query.setParameter("materno", materno.toUpperCase(ContextUtil.getLocale()), StandardBasicTypes.STRING);
        query.setParameter("nombre", nombre.toUpperCase(ContextUtil.getLocale()), StandardBasicTypes.STRING);

        return query.list();
    }

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Curso> findCargaAcademica(Integer rut) {
        Criteria criteria = getSession().createCriteria(Curso.class);

        String filter = "EXISTS (select * from curso_profesor where "
                + "cpro_asign = this_.cur_asign and "
                + "cpro_elect = this_.cur_elect and "
                + "cpro_coord = this_.cur_coord and "
                + "cpro_secc = this_.cur_secc and "
                + "cpro_agno = this_.cur_agno and "
                + "cpro_sem = this_.cur_sem and "
                + "cpro_prof_rut = :rut)";

        criteria.setFetchMode("asignatura", JOIN);
        criteria.setFetchMode("cursoActual", JOIN);

        criteria.add(Restrictions.sqlRestriction(filter,
                new Object[]{rut},
                new Type[]{IntegerType.INSTANCE}));

        criteria.add(sqlRestriction("cur_tipo in ('C','T')"));
        criteria.addOrder(asc("id.curAgno"));
        criteria.addOrder(asc("id.curSem"));
        criteria.addOrder(asc("id.curAsign"));
        criteria.addOrder(asc("id.curElect"));
        criteria.addOrder(asc("id.curCoord"));
        criteria.addOrder(asc("id.curSecc"));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Curso> findCursosActaRectificatoria(Integer rut) {
        // Creamos el Criteria para la clase Curso
        Criteria criteria = getSession().createCriteria(Curso.class);

        // FetchMode optimizado
        criteria.setFetchMode("asignatura", JOIN);

        // Usamos una subconsulta SQL con parámetros
        String filter = "EXISTS (select * from curso_profesor, acta_calificacion, parametro_mencion, curso_car, plan where "
                + "cpro_asign = this_.cur_asign and "
                + "cpro_elect = this_.cur_elect and "
                + "cpro_coord = this_.cur_coord and "
                + "cpro_secc = this_.cur_secc and "
                + "cpro_agno = this_.cur_agno and "
                + "cpro_sem = this_.cur_sem and "
                + "cpro_asign = acal_asign and "
                + "cpro_elect = acal_elect and "
                + "cpro_coord = acal_coord and "
                + "cpro_secc = acal_secc and "
                + "cpro_agno = acal_agno and "
                + "cpro_sem = acal_sem and "
                + "acal_est='F' and "
                + "cpro_asign = ccar_asign and "
                + "cpro_elect = ccar_elect and "
                + "cpro_coord = ccar_coord and "
                + "cpro_secc = ccar_secc and "
                + "cpro_agno = ccar_agno and "
                + "cpro_sem = ccar_sem and "
                + "pmen_cod_car = ccar_cod_car and "
                + "pmen_cod_men = ccar_cod_men and "
                + "pla_cod_car = ccar_cod_car and "
                + "pla_cod_men = ccar_cod_men and "
                + "pla_vigente='S' and "
                + "(ccar_agno+1)*10+ccar_sem >= get_semestre_previo(pmen_agno_act, pmen_sem_act, pmen_cod_car, pmen_cod_men, pla_cod) and "
                + "cpro_prof_rut = :rut)";

        // Añadimos la restricción SQL con parámetros
        criteria.add(Restrictions.sqlRestriction(filter,
                new Object[]{rut},
                new Type[]{IntegerType.INSTANCE}));

        // Añadimos más restricciones de acuerdo a la lógica de negocio
        criteria.add(Restrictions.sqlRestriction("cur_tipo in ('C','T')"));

        // Ordenamos los resultados por los campos correspondientes
        criteria.addOrder(Order.desc("id.curAgno"));
        criteria.addOrder(Order.desc("id.curSem"));
        criteria.addOrder(Order.desc("id.curAsign"));
        criteria.addOrder(Order.desc("id.curElect"));
        criteria.addOrder(Order.desc("id.curCoord"));
        criteria.addOrder(Order.desc("id.curSecc"));

        // Ejecutamos la consulta y devolvemos los resultados
        return criteria.list();
    }

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Curso> findCargaHistorica(Integer rut) {
        Criteria criteria = getSession().createCriteria(Curso.class);
        criteria.setFetchMode("asignatura", JOIN);

        // La consulta SQL con los parámetros
        String filter = "EXISTS (select * from curso_profesor where "
                + "cpro_asign = cur_asign and "
                + "cpro_elect = cur_elect and "
                + "cpro_coord = cur_coord and "
                + "cpro_secc = cur_secc and "
                + "cpro_agno = cur_agno and "
                + "cpro_sem = cur_sem and "
                + "cpro_prof_rut = :rut)";

        // Agregar sqlRestriction con parámetros
        criteria.add(Restrictions.sqlRestriction(filter,
                new Object[]{rut},
                new Type[]{IntegerType.INSTANCE}));

        // Asegurarse de que no haya otra llamada innecesaria a sqlRestriction
        criteria.add(Restrictions.sqlRestriction("cur_tipo in ('C','T')"));

        // Ordenamiento
        criteria.addOrder(Order.desc("id.curAgno"));
        criteria.addOrder(Order.desc("id.curSem"));
        criteria.addOrder(Order.desc("id.curAsign"));
        criteria.addOrder(Order.desc("id.curElect"));
        criteria.addOrder(Order.desc("id.curCoord"));
        criteria.addOrder(Order.desc("id.curSecc"));

        // Ejecutar la consulta y devolver los resultados
        return criteria.list();
    }

    /**
     * Method description
     *
     * @param rut
     * @param agnoInicio
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Curso> findCursosMaterialHistorico(Integer rut, Integer agnoInicio) {
        Criteria criteria = getSession().createCriteria(Curso.class);

        String filter = "exists ("
                + "select * from curso_profesor where "
                + "this_.cur_asign = cpro_asign and "
                + "this_.cur_elect = cpro_elect and "
                + "this_.cur_coord = cpro_coord and "
                + "this_.cur_secc = cpro_secc and "
                + "this_.cur_agno = cpro_agno and "
                + "this_.cur_sem = cpro_sem and "
                + "cpro_prof_rut= :rut and "
                + "cpro_agno >= :gnoInicio)";

        // Agregar sqlRestriction con parámetros
        criteria.add(Restrictions.sqlRestriction(filter,
                new Object[]{rut, agnoInicio},
                new Type[]{IntegerType.INSTANCE, IntegerType.INSTANCE}));

        criteria.setFetchMode("asignatura", JOIN);
        criteria.createCriteria("electivo", LEFT_OUTER_JOIN);
        criteria.addOrder(desc("id.curAgno"));
        criteria.addOrder(desc("id.curSem"));
        criteria.addOrder(asc("id.curAsign"));
        criteria.addOrder(asc("id.curElect"));
        criteria.addOrder(asc("id.curCoord"));
        criteria.addOrder(asc("id.curSecc"));

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
    public List<CursoProfesor> findProfesor(Curso curso) {
        Criteria criteria = getSession().createCriteria(CursoProfesor.class);

        criteria.setFetchMode("profesor", JOIN);
        criteria.add(eq("cursoActual.id", curso.getId()));

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Profesor> getProfesores(CursoId id) {
        String strQuery = "select * from profesor where exists (select * from curso_profesor where "
                + "cpro_asign = :asign and "
                + "cpro_elect = :elect and "
                + "cpro_coord = :coord and "
                + "cpro_secc = :secc and "
                + "cpro_agno = :agno and "
                + "cpro_sem = :sem and "
                + "cpro_prof_rut = prof_rut)";

        SQLQuery query = getSession().createSQLQuery(strQuery).addEntity("profesor", Profesor.class);
        // Establecemos los parámetros para la consulta
        query.setParameter("asign", id.getCurAsign(), StandardBasicTypes.INTEGER);
        query.setParameter("elect", id.getCurElect(), StandardBasicTypes.STRING);
        query.setParameter("coord", id.getCurCoord(), StandardBasicTypes.STRING);
        query.setParameter("secc", id.getCurSecc(), StandardBasicTypes.INTEGER);
        query.setParameter("agno", id.getCurAgno(), StandardBasicTypes.INTEGER);
        query.setParameter("sem", id.getCurSem(), StandardBasicTypes.INTEGER);

        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CursoProfesor> findProfesor(CursoId cursoId) {
        Criteria criteria = getSession().createCriteria(CursoProfesor.class);

        criteria.setFetchMode("profesor", JOIN);
        criteria.setFetchMode("curso", JOIN);
        criteria.add(eq("curso.id", cursoId));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CursoProfesor> findCursosActas(Integer rut) {
        Criteria criteria = getSession().createCriteria(CursoProfesor.class);

        criteria.setFetchMode("cargaActa", JOIN);
        criteria.setFetchMode("cargaActa.asignatura", JOIN);
        criteria.setFetchMode("cargaActa.electivo", JOIN);
        criteria.add(eq("id.cproRut", rut));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param rut
     * @param password
     */
    @Override
    public void setPassword(Integer rut, String password) {
        String hql = "update Profesor set prof_password = get_hash(:rut, :password) where prof_rut = :rut";
        Query query = getSession().createQuery(hql);

        query.setParameter("rut", rut.toString(), StandardBasicTypes.STRING);
        query.setParameter("password", password, StandardBasicTypes.STRING);
        query.executeUpdate();
    }

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    @Override
    public Profesor getMisDatos(Integer rut) {
        Criteria criteria = getSession().createCriteria(Profesor.class);

        criteria.setFetchMode("categoria", JOIN);
        criteria.setFetchMode("jerarquia", JOIN);
        criteria.setFetchMode("jornada", JOIN);
        criteria.setFetchMode("comuna", JOIN);
        criteria.add(idEq(rut));

        return (Profesor) criteria.uniqueResult();
    }

    /**
     * Method description
     *
     * @param rut
     * @param email
     * @param fono
     */
    @Override
    public void setMisDatos(Integer rut, String email,
            //Date fechaNac, String direccion, Integer comuna, 
            String fono) {

        String hql = "update Profesor set prof_email = :email, prof_fono= :fono, prof_updated=sysdate "
                // + "prof_fecha_nac = :fechaNac,"
                // + "prof_direccion = :direccion, prof_comuna =:comuna"
                // + ", prof_categoria =:categoria," + "prof_jerarqui = :jerarquia," + "prof_jornada = :jornada"
                + " where prof_rut = :rut";
        Query query = getSession().createQuery(hql);
        query.setParameter("rut", rut.toString(), StandardBasicTypes.STRING);
        query.setParameter("email", email, StandardBasicTypes.STRING);
        //query.setParameter("fechaNac", fechaNac, StandardBasicTypes.DATE);
        //query.setParameter("direccion", direccion, StandardBasicTypes.STRING);
        //query.setParameter("comuna", comuna, StandardBasicTypes.INTEGER);
        query.setParameter("fono", fono, StandardBasicTypes.STRING);

        query.executeUpdate();
    }

    /**
     * Method description
     *
     * @param rut
     */
    @Override
    public void setLastLogin(Integer rut) {
        String hql = "update Profesor set prof_last_login = SYSDATE where prof_rut = :rut";
        Query query = getSession().createQuery(hql);

        query.setParameter("rut", rut.toString(), StandardBasicTypes.STRING);
        query.executeUpdate();
    }

    /**
     * Method description
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ProfesorActivoView> findProfesoresActivosFacultad(Integer facultad) {
        Criteria criteria = getSession().createCriteria(ProfesorActivoView.class);
        criteria.add(eq("facultad", facultad));
        return criteria.list();
    }

    /**
     * Method description
     *
     * @param depto
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ProfesorActivoView> findProfesoresActivosDepartamento(Integer depto) {
        Criteria criteria = getSession().createCriteria(ProfesorActivoView.class);

        criteria.add(eq("depto", depto));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param carrera
     * @param mencion
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ProfesorActivoView> findProfesoresActivosCarrera(Integer carrera, Integer mencion) {
        Criteria criteria = getSession().createCriteria(ProfesorActivoView.class);

        criteria.add(eq("carrera", carrera));
        criteria.add(eq("mencion", mencion));
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ProfesorActivoView> findProfesoresActivosCarrera(Integer uniCod) {
        Criteria criteria = getSession().createCriteria(ProfesorActivoView.class);
        String filter = "EXISTS (SELECT * FROM mencion where men_unidad= :uniCod AND ccar_cod_car= men_cod_car AND  ccar_cod_men= men_cod_men)";

        // Agregar sqlRestriction con parámetros
        criteria.add(Restrictions.sqlRestriction(filter,
                new Object[]{uniCod},
                new Type[]{IntegerType.INSTANCE}));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param profesor
     * @param alumno
     * @return
     */
    @Override
    public String esProfesorDe(Integer profesor, Integer alumno) {
        // Utilizando parámetros de consulta para prevenir inyecciones SQL
        return (String) getSession()
                .createSQLQuery("select es_profesor_de(:profesor, :alumno) from dual")
                .setParameter("profesor", profesor, StandardBasicTypes.INTEGER)
                .setParameter("alumno", alumno, StandardBasicTypes.INTEGER)
                .uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Profesor> findxUser(Integer rut, String tipo) {
        try {
            Query query = getSession().getNamedQuery("GetProfesoresUserFunction");

            query.setParameter(0, tipo, StandardBasicTypes.STRING);
            query.setParameter(1, rut, StandardBasicTypes.INTEGER);

            List<Object[]> results = query.list();

            // Usar Streams para mapear los resultados a objetos Profesor
            return results.stream()
                    .map(obj -> {
                        Profesor prof = new Profesor();
                        prof.setProfRut(((Number) obj[0]).intValue());
                        prof.setProfPat((String) obj[1]);
                        prof.setProfMat((String) obj[2]);
                        prof.setProfNom((String) obj[3]);
                        return prof;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // Devuelve lista vacía en lugar de null para evitar problemas en código consumidor
            return Collections.emptyList();
        }
    }

    @Override
    public void creaProfesor(Integer rut) {
        Query query = getSession().getNamedQuery("CreaProfesorFunction");

        query.setParameter(0, rut, StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }

    @Override
    public Profesor trabaja(Integer rut, String user) {
        String sql = "select * from Profesor where exists (SELECT *"
                + " FROM labor_realizada, labor"
                + " WHERE lrea_rut = prof_rut and"
                + " lrea_labor = lab_cod and"
                + " lab_user= ?) and"
                + " prof_rut = ?";

        SQLQuery query = getSession().createSQLQuery(sql).addEntity("Profesor", Profesor.class);

        // Establecemos los parámetros para la consulta
        query.setParameter(0, user, StandardBasicTypes.STRING);
        query.setParameter(1, rut, StandardBasicTypes.INTEGER);

        // Ejecutamos la consulta y obtenemos los resultados
        return (Profesor) query.uniqueResult();
    }
}
