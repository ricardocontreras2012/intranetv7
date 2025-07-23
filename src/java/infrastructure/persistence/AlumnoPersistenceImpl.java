/*
 * @(#)AlumnoPersistenceImpl.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence;

import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.Alumno;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.idEq;
import static org.hibernate.criterion.Restrictions.sqlRestriction;
import org.hibernate.type.StringType;
import org.hibernate.type.StandardBasicTypes;
import infrastructure.util.ContextUtil;
import domain.model.AlumnoActivoView;
import domain.repository.AlumnoRepository;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AlumnoPersistenceImpl extends CrudAbstractDAO<Alumno, Long> implements AlumnoRepository {

    /**
     * Method description
     *
     * @param rut
     * @param password
     * @return
     */
    @Override
    public Alumno find(Integer rut, String password) {
        Criteria criteria = getSession().createCriteria(Alumno.class);

        criteria.add(idEq(rut));
        criteria.add(sqlRestriction("valid_user_alumno(alu_rut, (?))=1", password, new StringType()));

        return (Alumno) criteria.uniqueResult();
    }

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    @Override
    public Alumno find(Integer rut) {
        Criteria criteria = getSession().createCriteria(Alumno.class);

        criteria.add(idEq(rut));

        return (Alumno) criteria.uniqueResult();
    }

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    @Override
    public Alumno findFull(Integer rut) {
        Criteria criteria = getSession().createCriteria(Alumno.class);

        criteria.setFetchMode("comunaAlu", JOIN);
        criteria.createAlias("comunaAlu.region", "region");
        criteria.add(idEq(rut));

        return (Alumno) criteria.uniqueResult();
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
    public List<Alumno> find(String paterno, String materno, String nombre) {
        String sql = "SELECT * FROM alumno "
                + "WHERE normaliza_string(upper(alu_paterno)) LIKE normaliza_string(:paterno) || '%' "
                + "AND ((alu_materno IS NULL AND :materno IS NULL) OR "
                + "     normaliza_string(upper(alu_materno)) LIKE normaliza_string(:materno) || '%') "
                + "AND normaliza_string(upper(CASE WHEN alu_nombre_social IS NOT NULL THEN alu_nombre_social ELSE alu_nombre END)) LIKE '%' || normaliza_string(:nombre) || '%' "
                + "ORDER BY normaliza_string(upper(alu_paterno)), "
                + "         normaliza_string(upper(alu_materno)), "
                + "         normaliza_string(upper(CASE WHEN alu_nombre_social IS NOT NULL THEN alu_nombre_social ELSE alu_nombre END))";

        SQLQuery query = getSession().createSQLQuery(sql).addEntity("alumno", Alumno.class);
        query.setParameter("paterno", paterno.toUpperCase(ContextUtil.getLocale()), StandardBasicTypes.STRING);
        query.setParameter("materno", materno.toUpperCase(ContextUtil.getLocale()), StandardBasicTypes.STRING);
        query.setParameter("nombre", nombre.toUpperCase(ContextUtil.getLocale()), StandardBasicTypes.STRING);

        return query.list();
    }

    /**
     * Method description
     *
     * @param rut
     * @param password
     */
    @Override
    public void setPassword(Integer rut, String password) {
        String hql = "update Alumno set alu_password = get_hash(:rut, :password) where alu_rut = :rut";
        Query query = getSession().createQuery(hql);

        query.setParameter("rut", rut.toString(), StandardBasicTypes.STRING);
        query.setParameter("password", password, StandardBasicTypes.STRING);
        query.executeUpdate();
    }

    @Override
    public void setEmail(Integer rut, String mail) {
        String hql = "update Alumno set alu_email = :mail where alu_rut = :rut";
        Query query = getSession().createQuery(hql);

        query.setParameter("rut", rut.toString(), StandardBasicTypes.STRING);
        query.setParameter("mail", mail, StandardBasicTypes.STRING);
        query.executeUpdate();
    }

    /**
     * Method description
     *
     * @param rut
     */
    @Override
    public void setLastLogin(Integer rut) {
        String hql = "update Alumno set alu_last_login = SYSDATE where alu_rut = :rut";
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
    public List<AlumnoActivoView> findAlumnosActivosFacultad(Integer facultad) {
        Criteria criteria = getSession().createCriteria(AlumnoActivoView.class);
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
    public List<AlumnoActivoView> findAlumnosActivosDepartamento(Integer depto) {
        Criteria criteria = getSession().createCriteria(AlumnoActivoView.class);

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
    public List<AlumnoActivoView> findAlumnosActivosCarrera(Integer carrera, Integer mencion) {
        Criteria criteria = getSession().createCriteria(AlumnoActivoView.class);

        criteria.add(eq("carrera", carrera));
        criteria.add(eq("mencion", mencion));

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AlumnoActivoView> findAlumnosActivosCarrera(Integer uniCod) {
        Criteria criteria = getSession().createCriteria(AlumnoActivoView.class);
        criteria.add(eq("unidad", uniCod));
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AlumnoActivoView> findAlumnosActivosNivelCarrera(Integer carrera, Integer mencion, Integer nivel) {
        Criteria criteria = getSession().createCriteria(AlumnoActivoView.class);

        criteria.add(eq("carrera", carrera));
        criteria.add(eq("mencion", mencion));
        criteria.add(eq("nivel", nivel));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    @Override
    public Alumno getMisDatos(Integer rut) {
        Criteria criteria = getSession().createCriteria(Alumno.class);

        criteria.setFetchMode("comunaAlu", JOIN);
        criteria.add(idEq(rut));

        return (Alumno) criteria.uniqueResult();
    }

    /**
     * Method description
     *
     * @param rut
     * @param email
     * @param emailLaboral
     * @param direccion
     * @param comuna
     * @param fono
     * @param estadoCivil
     */
    @Override
    public void setMisDatos(Integer rut, String email, String emailLaboral, String direccion, Integer comuna, String fono, Integer estadoCivil) {
        String hql = "update Alumno set alu_email = :email, alu_email_laboral = :emailLaboral, alu_direc_alu = :direccion,"
                + "alu_comuna_alu = :comuna, alu_fono_alu= :fono, alu_est_civ= :estadoCivil where alu_rut = :rut";

        Query query = getSession().createQuery(hql);

        query.setParameter("rut", rut.toString(), StandardBasicTypes.STRING);
        query.setParameter("email", email, StandardBasicTypes.STRING);
        query.setParameter("emailLaboral", emailLaboral, StandardBasicTypes.STRING);
        query.setParameter("direccion", direccion, StandardBasicTypes.STRING);
        query.setParameter("comuna", comuna, StandardBasicTypes.INTEGER);
        query.setParameter("fono", fono, StandardBasicTypes.STRING);
        query.setParameter("estadoCivil", estadoCivil, StandardBasicTypes.INTEGER);
        query.executeUpdate();
    }
}
