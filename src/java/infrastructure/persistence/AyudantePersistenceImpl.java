/*
 * @(#)AyudantePersistenceImpl.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence;

import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.Ayudante;
import domain.model.Curso;
import domain.model.CursoAyudante;
import domain.model.CursoId;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
import domain.model.AyudanteActivoView;
import domain.repository.AyudanteRepository;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AyudantePersistenceImpl extends CrudAbstractDAO<Ayudante, Long> implements AyudanteRepository {

    /**
     * Method description
     *
     * @param rut
     * @param password
     * @return
     */
    @Override
    public Ayudante find(Integer rut, String password) {
        Criteria criteria = getSession().createCriteria(Ayudante.class);
        
        criteria.add(idEq(rut));
        criteria.add(sqlRestriction("valid_user_ayudante(ayu_rut, (?))=1", password, new StringType()));

        return (Ayudante) criteria.uniqueResult();
    }

    /**
     *
     * @param rut
     * @return
     */
    @Override
    public Ayudante findFull(Integer rut) {
        Criteria criteria = getSession().createCriteria(Ayudante.class);

        criteria.setFetchMode("comuna", JOIN);
        criteria.createAlias("comuna.region", "region");
        criteria.add(idEq(rut));

        return (Ayudante) criteria.uniqueResult();
    }

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    @Override
    public Ayudante find(Integer rut) {
        Criteria criteria = getSession().createCriteria(Ayudante.class);

        criteria.add(idEq(rut));

        return (Ayudante) criteria.uniqueResult();
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
    public List<Ayudante> find(String paterno, String materno, String nombre) {
        String sql = "SELECT * FROM ayudante WHERE (normaliza_string(upper(ayu_paterno)) LIKE  normaliza_string(:paterno)||'%') AND "
                + "((ayu_materno IS NULL AND :materno IS NULL) OR  (normaliza_string(upper(ayu_materno)) LIKE normaliza_string(:materno)||'%')) AND "
                + "(normaliza_string(upper(ayu_nombre)) LIKE '%'||normaliza_string(:nombre)||'%') ORDER BY normaliza_string(upper(ayu_paterno)), normaliza_string(upper(ayu_materno)), ayu_nombre";

        SQLQuery query = getSession().createSQLQuery(sql).addEntity("ayudante", Ayudante.class);
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
    public List<Curso> findCarga(Integer rut) {
        Criteria criteria = getSession().createCriteria(Curso.class);

        String filter = "EXISTS (select * from curso_ayudante where "
                + "cayu_asign = this_.cur_asign and "
                + "cayu_elect = this_.cur_elect and "
                + "cayu_coord = this_.cur_coord and "
                + "cayu_secc = this_.cur_secc and "
                + "cayu_agno = this_.cur_agno and "
                + "cayu_sem = this_.cur_sem and "
                + "cayu_rut = :rut)";

        criteria.setFetchMode("asignatura", JOIN);
        criteria.createAlias("cursoActual", "cursoActual");
        criteria.createCriteria("electivo", LEFT_OUTER_JOIN);
        criteria.add(Restrictions.sqlRestriction(filter,
                new Object[]{rut},
                new Type[]{IntegerType.INSTANCE}));

        criteria.addOrder(asc("id.curAgno"));
        criteria.addOrder(asc("id.curSem"));
        criteria.addOrder(asc("id.curAsign"));
        criteria.addOrder(asc("id.curElect"));
        criteria.addOrder(asc("id.curCoord"));
        criteria.addOrder(asc("id.curSecc"));

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CursoAyudante> findCargaHistorica(Integer rut) {
        Criteria criteria = getSession().createCriteria(CursoAyudante.class);

        criteria.setFetchMode("curso", JOIN);
        criteria.setFetchMode("asignatura", JOIN);
        criteria.createCriteria("curso.electivo", LEFT_OUTER_JOIN);
        criteria.createCriteria("curso.horarios", LEFT_OUTER_JOIN);
        criteria.setFetchMode("profesor", JOIN);
        criteria.createAlias("curso.asignatura", "asignatura");
        criteria.add(eq("id.cayuRut", rut));
        criteria.addOrder(desc("id.cayuAgno"));
        criteria.addOrder(desc("id.cayuSem"));
        criteria.addOrder(desc("id.cayuAsign"));
        criteria.addOrder(desc("id.cayuElect"));
        criteria.addOrder(desc("id.cayuCoord"));
        criteria.addOrder(desc("id.cayuSecc"));

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
    public List<CursoAyudante> find(Curso curso) {

        Criteria criteria = getSession().createCriteria(CursoAyudante.class);

        criteria.setFetchMode("ayudante", JOIN);
        criteria.add(eq("cursoActual.id", curso.getId()));

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CursoAyudante> find(CursoId cursoId) {
        Criteria criteria = getSession().createCriteria(CursoAyudante.class);

        criteria.setFetchMode("ayudante", JOIN);
        criteria.setFetchMode("curso", JOIN);
        criteria.add(eq("curso.id", cursoId));

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
        String hql = "update Ayudante set ayu_password = get_hash(:rut, :password) where ayu_rut = :rut";
        Query query = getSession().createQuery(hql);

        query.setParameter("rut", rut.toString(), StandardBasicTypes.STRING);
        query.setParameter("password", password, StandardBasicTypes.STRING);
        query.executeUpdate();
    }

    /**
     * Method description
     *
     * @param rut
     * @param email
     */
    @Override
    public void setEmail(Integer rut, String email) {
        String hql = "update Ayudante set ayu_email = :email where ayu_rut = :rut";
        Query query = getSession().createQuery(hql);

        query.setParameter("rut", rut.toString(), StandardBasicTypes.STRING);
        query.setParameter("email", email, StandardBasicTypes.STRING);
        query.executeUpdate();
    }

    /**
     * Method description
     *
     * @param rut
     */
    @Override
    public void setLastLogin(Integer rut) {
        String hql = "update Ayudante set ayu_last_login = SYSDATE where ayu_rut = :rut";
        Query query = getSession().createQuery(hql);

        query.setParameter("rut", rut.toString(), StandardBasicTypes.STRING);
        query.executeUpdate();
    }

    /**
     * Method description
     *
     * @param facultad
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<AyudanteActivoView> findAyudantesActivosFacultad(Integer facultad) {
        Criteria criteria = getSession().createCriteria(AyudanteActivoView.class);
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
    public List<AyudanteActivoView> findAyudantesActivosDepartamento(Integer depto) {
        Criteria criteria = getSession().createCriteria(AyudanteActivoView.class);

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
    public List<AyudanteActivoView> findAyudantesActivosCarrera(Integer carrera, Integer mencion) {
        Criteria criteria = getSession().createCriteria(AyudanteActivoView.class);

        criteria.add(eq("carrera", carrera));
        criteria.add(eq("mencion", mencion));
        return criteria.list();
    }

    @Override
    public void creaAyudante(Integer rut) {
        Query query = getSession().getNamedQuery("CreaAyudanteFunction");

        query.setParameter(0, rut, StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Curso> getCursos(Integer rut) {
        String strQuery = "select * from curso where exists (select * from curso_ayudante where "
                + "cayu_asign = cur_asign and "
                + "cayu_elect = cur_elect and "
                + "cayu_coord = cur_coord and "
                + "cayu_secc = cur_secc and "
                + "cayu_agno = cur_agno and "
                + "cayu_sem = cur_sem and "
                + "cayu_comp = cur_comp and "
                + "cayu_rut = :rut)";

        Query query = getSession().createSQLQuery(strQuery).addEntity("curso", Curso.class);
        query.setParameter("rut", rut, StandardBasicTypes.INTEGER);

        return query.list();
    }
}
