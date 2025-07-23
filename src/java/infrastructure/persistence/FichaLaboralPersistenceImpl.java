/*
 * @(#)FichaLaboralPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.FichaLaboralRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.FichaLaboral;
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
public final class FichaLaboralPersistenceImpl extends CrudAbstractDAO<FichaLaboral, Long>
        implements FichaLaboralRepository {

    /**
     * Method description
     *
     * @param rut
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<FichaLaboral> find(Integer rut) {
        Criteria criteria = getSession().createCriteria(FichaLaboral.class);

        criteria.createAlias("alumnoEmpleador", "alumnoEmpleador");
        criteria.setFetchMode("alumnoEmpleador", JOIN);
        criteria.add(eq("alumnoEmpleador.alumno.aluRut", rut));
        criteria.addOrder(desc("flabDesdeAgno"));
        criteria.addOrder(desc("flabDesdeMes"));
        //criteria.setFetchMode("region", FetchMode.JOIN);
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
    public FichaLaboral find(Integer rut, Integer correl) {
        Criteria criteria = getSession().createCriteria(FichaLaboral.class);

        criteria.add(idEq(correl));
        criteria.createAlias("alumnoEmpleador", "alumnoEmpleador");
        criteria.createAlias("alumnoEmpleador.alumno", "alumno");
        criteria.add(eq("alumno.aluRut", rut));
        criteria.setFetchMode("empleador", JOIN);
        criteria.setFetchMode("actividadEconomica", JOIN);
        criteria.setFetchMode("areaTrabajo", JOIN);
        // criteria.setFetchMode("region", FetchMode.JOIN);
        // criteria.setFetchMode("comuna", FetchMode.JOIN);
        criteria.setFetchMode("tipoTrabajo", JOIN);

        return (FichaLaboral) criteria.uniqueResult();
    }

    /**
     * Method description
     *
     *
     * @param correl
     * @param alumnoEmpleador
     * @param areaTrabajo
     * @param region
     * @param comuna
     * @param otroLugar
     * @param cargo
     * @param tipoTrabajo
     * @param sueldo
     * @param desdeAgno
     * @param desdeMes
     * @param hastaAgno
     * @param hastaMes
     * @param descripcion
     */
    @Override
    public void updateLaboral(Integer correl, Integer alumnoEmpleador, Integer areaTrabajo,
            Integer region, Integer comuna, String otroLugar, String cargo, Integer tipoTrabajo,
            Integer sueldo, Integer desdeAgno, Integer desdeMes, Integer hastaAgno, Integer hastaMes,
            String descripcion) {

        String hql
                = "update FichaLaboral set "
                + "flab_area_trabajo = :areaTrabajo, "
                + "flab_region = DECODE(:region,'null',NULL,:region), "
                + "flab_comuna = DECODE(:comuna,'null',NULL,:comuna), "
                + "flab_otro_lugar = DECODE(:otroLugar,'null',NULL,:otroLugar), "
                + "flab_cargo = :cargo, "
                + "flab_tipo_trabajo = :tipoTrabajo, "
                + "flab_sueldo = :sueldo, "
                + "flab_desde_mes = DECODE(:desdeMes,'null',NULL,:desdeMes), "
                + "flab_desde_agno = DECODE(:desdeAgno,'null',NULL,:desdeAgno), "
                + "flab_hasta_mes = DECODE(:hastaMes,'null',NULL,:hastaMes), "
                + "flab_hasta_agno = DECODE(:hastaAgno,'null',NULL,:hastaAgno), "
                + "flab_descripcion = :descripcion "
                + "where flab_correl_ficha = :correl and flab_correl_alu_emp = :alumnoEmpleador";

        Query query = getSession().createQuery(hql);

        query.setParameter("areaTrabajo", areaTrabajo, StandardBasicTypes.INTEGER);
        query.setParameter("region", region);
        query.setParameter("comuna", comuna);
        query.setParameter("otroLugar", otroLugar);
        query.setParameter("cargo", cargo, StandardBasicTypes.STRING);
        query.setParameter("tipoTrabajo", tipoTrabajo, StandardBasicTypes.INTEGER);
        query.setParameter("sueldo", sueldo, StandardBasicTypes.INTEGER);
        query.setParameter("desdeAgno", desdeAgno);
        query.setParameter("desdeMes", desdeMes);
        query.setParameter("hastaAgno", hastaAgno);
        query.setParameter("hastaMes", hastaMes);
        query.setParameter("descripcion", descripcion, StandardBasicTypes.STRING);
        query.setParameter("correl", correl, StandardBasicTypes.INTEGER);
        query.setParameter("alumnoEmpleador", alumnoEmpleador, StandardBasicTypes.INTEGER);
        query.executeUpdate();
    }

    /**
     * Method description
     *
     *
     * @param correl
     * @param alumnoEmpleador
     * @param areaTrabajo
     * @param region
     * @param comuna
     * @param otroLugar
     * @param cargo
     * @param tipoTrabajo
     * @param sueldo
     * @param desdeAgno
     * @param desdeMes
     * @param hastaAgno
     * @param hastaMes
     * @param descripcion
     */
    @Override
    public void createLaboral(Integer correl, Integer alumnoEmpleador, Integer areaTrabajo,
            Integer region, Integer comuna, String otroLugar, String cargo, Integer tipoTrabajo,
            Integer sueldo, Integer desdeAgno, Integer desdeMes, Integer hastaAgno, Integer hastaMes,
            String descripcion) {

        String sql
                = "insert into Ficha_Laboral "
                + "(flab_correl_ficha, flab_correl_alu_emp, flab_area_trabajo, flab_region, flab_comuna, flab_otro_lugar, flab_cargo, flab_tipo_trabajo, flab_sueldo, flab_desde_mes, flab_desde_agno, flab_hasta_mes, flab_hasta_agno, flab_descripcion) "
                + "values (" + correl + ", " + alumnoEmpleador + ", " + areaTrabajo + ", " + region + ", " + comuna + ", '" + otroLugar + "', '" + cargo + "', " + tipoTrabajo + ", " + sueldo + ", " + desdeMes + ", " + desdeAgno + ", " + hastaMes + ", " + hastaAgno + ", '" + descripcion + "')";
        Query query = getSession().createSQLQuery(sql);

        query.executeUpdate();
    }

    /**
     * Method description
     *
     *
     * @param correl
     */
    @Override
    public void deleteLaboral(Integer correl) {
        String hql = "delete from FichaLaboral where flab_correl_ficha = " + correl;
        Query query = getSession().createQuery(hql);

        query.executeUpdate();
    }
}
