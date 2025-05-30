/*
 * @(#)RespuestaEncuestaAyudantePersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.RespuestaEncuestaAyudantePersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCar;
import domain.model.CursoAyudante;
import domain.model.EncuestaAyudante;
import domain.model.RespuestaEncuestaAyudante;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Projections.rowCount;
import static org.hibernate.criterion.Restrictions.eq;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class RespuestaEncuestaAyudantePersistenceImpl extends CrudAbstractDAO<RespuestaEncuestaAyudante, Long>
        implements RespuestaEncuestaAyudantePersistence {

    /**
     * Method description
     *
     * @param aluCar
     * @param encuesta
     * @param cursoAyudante
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<RespuestaEncuestaAyudante> find(AluCar aluCar, EncuestaAyudante encuesta, CursoAyudante cursoAyudante) {
        Criteria criteria = getSession().createCriteria(RespuestaEncuestaAyudante.class);

        criteria.add(eq("aluCar.id", aluCar.getId()));
        criteria.add(eq("id.reaEnc", encuesta.getEnaNro()));
        criteria.add(eq("cursoAyudante.id", cursoAyudante.getId()));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param aluCar
     * @param encuesta
     * @return
     */
    @Override
    public Long find(AluCar aluCar, EncuestaAyudante encuesta) {
        Criteria criteria = getSession().createCriteria(RespuestaEncuestaAyudante.class);

        criteria.add(eq("aluCar.id", aluCar.getId()));
        criteria.add(eq("id.reaEnc",
                encuesta.getEnaNro())).setProjection(rowCount()).uniqueResult();

        return (Long) criteria.uniqueResult();
    }

    /**
     * Method description
     *
     * @param aluCar
     * @param cursoAyudante
     * @param preg
     * @param resp
     * @param correl
     */
    @Override
    public void doSave(AluCar aluCar, CursoAyudante cursoAyudante, Integer preg, Integer resp,
            Integer correl) {
        String hqlUpdate
                = "update RespuestaEncuestaAyudante set id.reaRut = NULL, id.reaResp=:newResp, reaCorrel =:correl where"
                + " id.reaRut = :oldRut AND" + " id.reaCodCar = :oldCodCar AND" + " id.reaAgnoIng = :oldAgnoIng AND"
                + " id.reaSemIng = :oldSemIng AND" + " id.reaAsign = :oldAsign AND" + " id.reaElect = :oldElect AND"
                + " id.reaCoord = :oldCoord AND" + " id.reaSecc = :oldSecc AND" + " id.reaAgno = :oldAgno AND"
                + " id.reaSem = :oldSem AND" + " id.reaRutAyu =:oldRutAyu AND"
                + " id.reaPreg = :oldPreg";

            getSession().createQuery(hqlUpdate).setInteger("newResp", resp).setInteger(
                    "oldRut", aluCar.getId().getAcaRut()).setInteger("oldCodCar", aluCar.getId().getAcaCodCar()).setInteger(
                            "oldAgnoIng", aluCar.getId().getAcaAgnoIng()).setInteger(
                            "oldSemIng", aluCar.getId().getAcaSemIng()).setInteger(
                            "oldAsign", cursoAyudante.getId().getCayuAsign()).setString(
                            "oldElect", cursoAyudante.getId().getCayuElect()).setString(
                            "oldCoord", cursoAyudante.getId().getCayuCoord()).setInteger(
                            "oldSecc", cursoAyudante.getId().getCayuSecc()).setInteger(
                            "oldAgno", cursoAyudante.getId().getCayuAgno()).setInteger(
                            "oldSem", cursoAyudante.getId().getCayuSem()).setInteger(
                            "oldRutAyu", cursoAyudante.getId().getCayuRut()).setInteger(
                            "oldPreg", preg).setInteger("correl", correl).executeUpdate();
    }

}
