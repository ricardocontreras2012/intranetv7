/*
 * @(#)ActaNominaPersistenceViewImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.ActaNominaPersistenceView;
import infrastructure.persistence.dao.CrudAbstractDAO;
import org.hibernate.type.StandardBasicTypes;
import domain.model.CursoId;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import domain.model.ActaNominaView;
import java.util.stream.Collectors;

/**
 *
 * @author Ricardo Contreras S.
 */
public class ActaNominaPersistenceViewImpl extends CrudAbstractDAO<ActaNominaView, Long>
        implements ActaNominaPersistenceView {

    /**
     * Method description
     *
     *
     * @param id
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ActaNominaView> find(CursoId id) {
        Query query = getSession().getNamedQuery("GetNominaxCursoFunction");

        query.setParameter(0, id.getCurAsign(), StandardBasicTypes.INTEGER);
        query.setString(1, id.getCurElect());
        query.setString(2, id.getCurCoord());
        query.setParameter(3, id.getCurSecc(), StandardBasicTypes.INTEGER);
        query.setParameter(4, id.getCurAgno(), StandardBasicTypes.INTEGER);
        query.setParameter(5, id.getCurSem(), StandardBasicTypes.INTEGER);
        query.setString(6, id.getCurComp());

        List<Object[]> results = query.list();

        return results.stream().map(obj -> {
            ActaNominaView acta = new ActaNominaView();
            acta.setAluRut(((Number) obj[0]).intValue());
            acta.setAluDv((String) obj[1]);
            acta.setAluPaterno((String) obj[2]);
            acta.setAluMaterno((String) obj[3]);
            acta.setAluNombre((String) obj[4]);
            acta.setAcalAsign(((Number) obj[5]).intValue());
            acta.setAcalElect((String) obj[6]);
            acta.setAcalCoord((String) obj[7]);
            acta.setAcalSecc(((Number) obj[8]).intValue());
            acta.setAcalAgno(((Number) obj[9]).intValue());
            acta.setAcalSem(((Number) obj[10]).intValue());
            acta.setAcanFinal(obj[11] != null ? (BigDecimal) obj[11] : null);
            acta.setAcanConcep((String) obj[12]);
            acta.setAcalEst((String) obj[13]);
            acta.setAcalFolio(((Number) obj[14]).intValue());
            acta.setAcaCodCar(((Number) obj[15]).intValue());
            acta.setAcaCodMen(((Number) obj[16]).intValue());
            return acta;
        }).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ActaNominaView> find(Integer folio, Integer agno, Integer sem) {
        Query query = getSession().getNamedQuery("GetNominaxFolioFunction");

        query.setParameter(0, folio, StandardBasicTypes.INTEGER);
        query.setParameter(1, agno, StandardBasicTypes.INTEGER);
        query.setParameter(2, sem, StandardBasicTypes.INTEGER);

        List<Object[]> results = query.list();

        return results.stream().map(obj -> {
            ActaNominaView acta = new ActaNominaView();
            acta.setAluRut(((Number) obj[0]).intValue());
            acta.setAluDv((String) obj[1]);
            acta.setAluPaterno((String) obj[2]);
            acta.setAluMaterno((String) obj[3]);
            acta.setAluNombre((String) obj[4]);
            acta.setAcalAsign(((Number) obj[5]).intValue());
            acta.setAcalElect((String) obj[6]);
            acta.setAcalCoord((String) obj[7]);
            acta.setAcalSecc(((Number) obj[8]).intValue());
            acta.setAcalAgno(((Number) obj[9]).intValue());
            acta.setAcalSem(((Number) obj[10]).intValue());
            acta.setAcanFinal(obj[11] != null ? (BigDecimal) obj[11] : null);
            acta.setAcanConcep((String) obj[12]);
            acta.setAcalEst((String) obj[13]);
            acta.setAcalFolio(((Number) obj[14]).intValue());
            acta.setAcaCodCar(((Number) obj[15]).intValue());
            acta.setAcaCodMen(((Number) obj[16]).intValue());
            return acta;
        }).collect(Collectors.toList());
    }
}
