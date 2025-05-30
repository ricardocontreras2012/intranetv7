/*
 * @(#)FichaEstudioPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.FichaEstudioPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.FichaEstudio;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import org.hibernate.Query;
import static org.hibernate.criterion.Order.desc;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.idEq;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author Alvaro Romero C.
 */
public final class FichaEstudioPersistenceImpl extends CrudAbstractDAO<FichaEstudio, Long>
        implements FichaEstudioPersistence {

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<FichaEstudio> find(Integer rut) {
        Criteria criteria = getSession().createCriteria(FichaEstudio.class);

        criteria.add(eq("alumno.aluRut", rut));
        criteria.setFetchMode("pais", JOIN);
        criteria.setFetchMode("institucionEducacional", JOIN);
        criteria.setFetchMode("testudio", JOIN);
        criteria.addOrder(desc("festDesdeAgno"));
        criteria.addOrder(desc("festDesdeMes"));

        return criteria.list();
    }

    /**
     * Method description
     *
     *
     * @param rut
     * @param correl
     *
     * @return
     */
    @Override
    public FichaEstudio find(Integer rut, Integer correl) {
        Criteria criteria = getSession().createCriteria(FichaEstudio.class);

        criteria.add(idEq(correl));
        criteria.add(eq("alumno.aluRut", rut));
        criteria.setFetchMode("pais", JOIN);
        criteria.setFetchMode("institucionEducacional", JOIN);
        criteria.setFetchMode("testudio", JOIN);

        return (FichaEstudio) criteria.uniqueResult();
    }

    /**
     * Method description
     *
     * @param correl
     * @param pais
     * @param institucionEducacional
     * @param otraInstitucion
     * @param tipoEstudio
     * @param nombreEstudio
     * @param desdeAgno
     * @param desdeMes
     * @param hastaAgno
     * @param hastaMes
     * @param estadoEstudio
     * @param areaEstudio
     */
    @Override
    public void updateEstudio(Integer correl, Integer pais, Integer institucionEducacional, String otraInstitucion,
            Integer tipoEstudio, String nombreEstudio, Integer desdeAgno, Integer desdeMes,
            Integer hastaAgno, Integer hastaMes, Integer estadoEstudio, Integer areaEstudio) {

        String hql = "update FichaEstudio set fest_pais = :pais, "
                + "fest_institucion = DECODE(:institucionEducacional,'null',NULL,:institucionEducacional), "
                + "fest_otra_institucion = :otraInstitucion, "
                + "fest_tipo_estudio = DECODE(:tipoEstudio,'',NULL,'null',NULL,:tipoEstudio), "
                + "fest_nombre_estudio = :nombreEstudio, "
                + "fest_desde_agno = :desdeAgno, " + "fest_desde_mes = :desdeMes, "
                + "fest_hasta_agno = DECODE(:hastaAgno,'null',NULL,:hastaAgno), "
                + "fest_hasta_mes = DECODE(:hastaMes,'null',NULL,:hastaMes), "
                + "fest_estado_estudio = :estadoEstudio, "
                + "fest_area_estudio = :areaEstudio " + "where fest_correl = :correl";

        Query query = getSession().createQuery(hql);

        query.setParameter("pais", pais, StandardBasicTypes.INTEGER);
        query.setParameter("institucionEducacional", institucionEducacional);
        query.setParameter("otraInstitucion", otraInstitucion, StandardBasicTypes.STRING);
        query.setParameter("tipoEstudio", tipoEstudio);
        query.setParameter("nombreEstudio", nombreEstudio, StandardBasicTypes.STRING);
        query.setParameter("desdeMes", desdeMes, StandardBasicTypes.INTEGER);
        query.setParameter("desdeAgno", desdeAgno, StandardBasicTypes.INTEGER);
        query.setParameter("hastaMes", hastaMes);
        query.setParameter("hastaAgno", hastaAgno);
        query.setParameter("estadoEstudio", estadoEstudio, StandardBasicTypes.INTEGER);
        query.setParameter("areaEstudio", areaEstudio, StandardBasicTypes.INTEGER);
        query.setParameter("correl", correl, StandardBasicTypes.INTEGER);
        query.executeUpdate();
    }

    /**
     * Method description
     *
     * @param correl
     * @param alumno
     * @param pais
     * @param institucionEducacional
     * @param otraInstitucion
     * @param tipoEstudio
     * @param nombreEstudio
     * @param desdeAgno
     * @param desdeMes
     * @param hastaAgno
     * @param hastaMes
     * @param estadoEstudio
     * @param areaEstudio
     */
    @Override
    public void createEstudio(Integer correl, Integer alumno, Integer pais, Integer institucionEducacional,
            String otraInstitucion, Integer tipoEstudio, String nombreEstudio, Integer desdeAgno,
            Integer desdeMes, Integer hastaAgno, Integer hastaMes, Integer estadoEstudio,
            Integer areaEstudio) {

        String sql;

        sql = "insert into Ficha_Estudio "
                + "(fest_correl, fest_rut_alumno, fest_pais, fest_institucion, fest_otra_institucion, fest_tipo_estudio, fest_nombre_estudio, fest_desde_mes, fest_desde_agno, fest_hasta_mes, fest_hasta_agno, fest_estado_estudio, fest_area_estudio)" + "values (" + correl + ", " + alumno + ", " + pais + ", " + institucionEducacional + ", '" + otraInstitucion + "', " + tipoEstudio + ", '" + nombreEstudio + "', " + desdeMes + ", " + desdeAgno + ", " + hastaMes + ", " + hastaAgno + ", " + estadoEstudio + ", " + areaEstudio + ")";

        Query query = getSession().createSQLQuery(sql);

        query.executeUpdate();
    }

    /**
     * Method description
     *
     *
     * @param correl
     * @param alumno
     */
    @Override
    public void deleteEstudio(Integer correl, Integer alumno) {
        String hql = "delete from FichaEstudio where fest_correl = :correl and fest_rut_alumno = :alumno";

        Query query = getSession().createQuery(hql);

        query.setParameter("correl", correl, StandardBasicTypes.INTEGER);
        query.setParameter("alumno", alumno, StandardBasicTypes.INTEGER);
        query.executeUpdate();
    }
}
