/*
 * @(#)CalificacionRequisitoAdicionalLogroPersistenceImpl.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence;

import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCar;
import domain.model.AluCarId;
import domain.model.CalificacionId;
import domain.model.CalificacionLogroAdicional;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import org.hibernate.Query;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;
import org.hibernate.type.StandardBasicTypes;
import infrastructure.support.CalificacionCertificacionSupport;
import java.util.stream.Collectors;
import domain.repository.CalificacionLogroAdicionalRepository;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CalificacionLogroAdicionalPersistenceImpl
        extends CrudAbstractDAO<CalificacionLogroAdicional, Long> implements CalificacionLogroAdicionalRepository {

    /**
     * Method description
     *
     * @param aluCar
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CalificacionLogroAdicional> find(AluCar aluCar) {
        Criteria criteria = getSession().createCriteria(CalificacionLogroAdicional.class);

        criteria.setFetchMode("trequisitoLogroAdicional", JOIN);
        criteria.add(eq("aluCar.id", aluCar.getId()));
        criteria.addOrder(asc("id.claAgno"));
        criteria.addOrder(asc("id.claSem"));
        criteria.addOrder(asc("id.claReq"));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param aluCar
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CalificacionCertificacionSupport> findAprobadas(AluCar aluCar) {
        Query query = getSession().getNamedQuery("I4CalificacionesAdicionalesFunction");
        AluCarId acaId = aluCar.getId();

        query.setParameter(0, acaId.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, acaId.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, acaId.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, acaId.getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, aluCar.getAcaCodMen(), StandardBasicTypes.INTEGER);
        query.setParameter(5, aluCar.getAcaCodPlan(), StandardBasicTypes.INTEGER);

        List<Object[]> results = query.list();

        return results.stream().map(obj -> {
            CalificacionCertificacionSupport cal = new CalificacionCertificacionSupport();
            CalificacionId id = new CalificacionId();

            id.setCalRut(((Number) obj[0]).intValue());
            id.setCalCodCar(((Number) obj[1]).intValue());
            id.setCalAgnoIng(((Number) obj[2]).intValue());
            id.setCalSemIng(((Number) obj[3]).intValue());
            cal.setCalNombre((String) obj[4]);
            cal.setCalNota((String) obj[5]);
            id.setCalAgno(((Number) obj[6]).intValue());
            id.setCalSem(((Number) obj[7]).intValue());
            cal.setCalSitAlu((String) obj[8]);
            cal.setCalProc((String) obj[9]);
            cal.setId(id);

            return cal;
        }).collect(Collectors.toList());
    }

    /**
     * Method description
     *
     * @param aluCar
     * @param trequisito
     * @return
     */
    @Override
    public boolean puedeInscribirAdicional(AluCar aluCar, Integer trequisito) {
        AluCarId id = aluCar.getId();
        String ret = (String) getSession().createSQLQuery("SELECT tiene_prereq_adicionales("
                + id.getAcaRut() + ',' + id.getAcaCodCar() + ','
                + id.getAcaAgnoIng() + ',' + id.getAcaSemIng() + ','
                + aluCar.getAcaCodMen() + ',' + aluCar.getAcaCodPlan() + ','
                + aluCar.getAluCarFunction().getNivel() + ',' + trequisito
                + ") from dual").uniqueResult();
        boolean retValue = true;

        if ("N".equals(ret)) {
            retValue = false;
        }

        return retValue;
    }
}
