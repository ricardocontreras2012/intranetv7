/*
 * @(#)ActaCalificacionNominaPersistenceImpl.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.ActaCalificacionNominaPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import org.hibernate.type.StandardBasicTypes;
import domain.model.ActaCalificacionNomina;
import java.math.BigDecimal;
import org.hibernate.Query;
import domain.model.ActaNominaView;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ActaCalificacionNominaPersistenceImpl extends CrudAbstractDAO<ActaCalificacionNomina, Long>
        implements ActaCalificacionNominaPersistence {    

    /**
     * Method description
     *
     *
     * @param actaNominaView
     */
    @Override
    public void putCalificacionNumerica(ActaNominaView actaNominaView) {
        String hql = "update ActaCalificacionNomina set acan_final = :nota where " + "acan_folio = :folio AND "
                + "acan_agno = :agno AND " + "acan_sem = :sem AND " + "acan_rut = :rut";
        Query query = getSession().createQuery(hql);

        query.setParameter("nota", actaNominaView.getAcanFinal(), StandardBasicTypes.BIG_DECIMAL);
        query.setParameter("folio", actaNominaView.getAcalFolio(), StandardBasicTypes.INTEGER);
        query.setParameter("agno", actaNominaView.getAcalAgno(), StandardBasicTypes.INTEGER);
        query.setParameter("sem", actaNominaView.getAcalSem(), StandardBasicTypes.INTEGER);
        query.setParameter("rut", actaNominaView.getAluRut(), StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }

    /**
     * Method description
     *
     *
     * @param actaNominaView
     */
    @Override
    public void putCalificacionConcepto(ActaNominaView actaNominaView) {
        String hql = "update ActaCalificacionNomina set acan_concep = :concepto where " + "acan_folio = :folio AND "
                + "acan_agno = :agno AND " + "acan_sem = :sem AND " + "acan_rut = :rut";
        Query query = getSession().createQuery(hql);

        query.setParameter("concepto", actaNominaView.getAcanConcep(), StandardBasicTypes.STRING);
        query.setParameter("folio", actaNominaView.getAcalFolio(), StandardBasicTypes.INTEGER);
        query.setParameter("agno", actaNominaView.getAcalAgno(), StandardBasicTypes.INTEGER);
        query.setParameter("sem", actaNominaView.getAcalSem(), StandardBasicTypes.INTEGER);
        query.setParameter("rut", actaNominaView.getAluRut(), StandardBasicTypes.INTEGER);
        query.executeUpdate();
    }

    /**
     *
     * @param folio
     * @param agno
     * @param sem
     * @param rut
     * @param carrera
     * @param agnoIng
     * @param semIng
     * @param nota
     */
    @Override
    public void insertCalificacion(Integer folio, Integer agno, Integer sem, Integer rut, Integer carrera, Integer agnoIng, Integer semIng, BigDecimal nota) {
        String hql = "insert into Acta_Calificacion_Nomina (acan_folio,acan_agno,acan_sem,acan_rut,acan_cod_car,acan_agno_ing,acan_sem_ing,acan_final) VALUES "
                + "(:folio,:agno,:sem,:rut,:carrera,:agnoIng,:semIng,:nota)";
        Query query = getSession().createSQLQuery(hql);

        query.setParameter("folio", folio, StandardBasicTypes.INTEGER);
        query.setParameter("agno", agno, StandardBasicTypes.INTEGER);
        query.setParameter("sem", sem, StandardBasicTypes.INTEGER);
        query.setParameter("rut", rut, StandardBasicTypes.INTEGER);
        query.setParameter("carrera", carrera, StandardBasicTypes.INTEGER);
        query.setParameter("agnoIng", agnoIng, StandardBasicTypes.INTEGER);
        query.setParameter("semIng", semIng, StandardBasicTypes.INTEGER);
        query.setParameter("nota", nota, StandardBasicTypes.BIG_DECIMAL);
        
        query.executeUpdate();
    }
}
