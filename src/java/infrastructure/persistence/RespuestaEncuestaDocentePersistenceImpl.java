/*
 * @(#)RespuestaEncuestaDocentePersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.RespuestaEncuestaDocentePersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCar;
import domain.model.CursoProfesor;
import domain.model.EncuestaDocente;
import domain.model.RespuestaEncuestaDocente;
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
public final class RespuestaEncuestaDocentePersistenceImpl extends CrudAbstractDAO<RespuestaEncuestaDocente, Long> implements RespuestaEncuestaDocentePersistence {
    /**
     * Method description
     *
     * @param aluCar
     * @param encuesta
     * @param cursoProfesor
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<RespuestaEncuestaDocente> find(AluCar aluCar, EncuestaDocente encuesta, CursoProfesor cursoProfesor) {
        Criteria criteria = getSession().createCriteria(RespuestaEncuestaDocente.class);
        
        criteria.add(eq("aluCar.id", aluCar.getId()));
        criteria.add(eq("id.redEnc", encuesta.getEdoNro()));
        criteria.add(eq("cursoProfesor.id", cursoProfesor.getId()));

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
    public Long find(AluCar aluCar, EncuestaDocente encuesta) {
        Criteria criteria = getSession().createCriteria(RespuestaEncuestaDocente.class);

        criteria.add(eq("aluCar.id", aluCar.getId()));
        criteria.add(eq("id.redEnc", encuesta.getEdoNro())).setProjection(rowCount()).uniqueResult();

        return (Long) criteria.uniqueResult();
    }

    /**
     * Method description
     *
     * @param aluCar
     * @param cursoProfesor
     * @param preg
     * @param resp
     */
    @Override
    public void doSave(AluCar aluCar, CursoProfesor cursoProfesor, Integer apartado, Integer preg, Integer resp, Integer correl) {
        String hqlUpdate
                = "update RespuestaEncuestaDocente set id.redRut = NULL, id.redResp=:newResp, redCorrel =:correl where"
                + " id.redRut = :oldRut AND" + " id.redCodCar = :oldCodCar AND" + " id.redAgnoIng = :oldAgnoIng AND"
                + " id.redSemIng = :oldSemIng AND" + " id.redAsign = :oldAsign AND" + " id.redElect = :oldElect AND"
                + " id.redCoord = :oldCoord AND" + " id.redSecc = :oldSecc AND" + " id.redAgno = :oldAgno AND"
                + " id.redSem = :oldSem AND" + " id.redRutProf =:oldRutProf AND"
                + " id.redPreg = :oldPreg AND id.redApartado =:oldApartado";

        getSession().createQuery(hqlUpdate).setInteger("newResp", resp).setInteger(
                "oldRut", aluCar.getId().getAcaRut()).setInteger("oldCodCar", aluCar.getId().getAcaCodCar()).setInteger(
                "oldAgnoIng", aluCar.getId().getAcaAgnoIng()).setInteger(
                "oldSemIng", aluCar.getId().getAcaSemIng()).setInteger(
                "oldAsign", cursoProfesor.getId().getCproAsign()).setString(
                "oldElect", cursoProfesor.getId().getCproElect()).setString(
                "oldCoord", cursoProfesor.getId().getCproCoord()).setInteger(
                "oldSecc", cursoProfesor.getId().getCproSecc()).setInteger(
                "oldAgno", cursoProfesor.getId().getCproAgno()).setInteger(
                "oldSem", cursoProfesor.getId().getCproSem()).setInteger(
                "oldRutProf", cursoProfesor.getId().getCproRut()).setInteger("oldApartado", apartado).setInteger(
                "oldPreg", preg).setInteger("correl", correl).executeUpdate();
    }
    
    /**
     * Method description
     *
     * @param aluCar
     * @param cursoProfesor
     * @param preg
     * @param resp
     */
    @Override
    public void doSaveEncuestaIntermedia(AluCar aluCar, CursoProfesor cursoProfesor, Integer preg, Integer resp, Integer correl) {
        String hqlUpdate
                = "update RespuestaEncuestaDocente set id.redRut = NULL, id.redResp=:newResp, redCorrel =:correl where"
                + " id.redRut = :oldRut AND" + " id.redCodCar = :oldCodCar AND" + " id.redAgnoIng = :oldAgnoIng AND"
                + " id.redSemIng = :oldSemIng AND" + " id.redAsign = :oldAsign AND" + " id.redElect = :oldElect AND"
                + " id.redCoord = :oldCoord AND" + " id.redSecc = :oldSecc AND" + " id.redAgno = :oldAgno AND"
                + " id.redSem = :oldSem AND" + " id.redRutProf =:oldRutProf AND"
                + " id.redPreg = :oldPreg";

        getSession().createQuery(hqlUpdate).setInteger("newResp", resp).setInteger(
                "oldRut", aluCar.getId().getAcaRut()).setInteger("oldCodCar", aluCar.getId().getAcaCodCar()).setInteger(
                "oldAgnoIng", aluCar.getId().getAcaAgnoIng()).setInteger(
                "oldSemIng", aluCar.getId().getAcaSemIng()).setInteger(
                "oldAsign", cursoProfesor.getId().getCproAsign()).setString(
                "oldElect", cursoProfesor.getId().getCproElect()).setString(
                "oldCoord", cursoProfesor.getId().getCproCoord()).setInteger(
                "oldSecc", cursoProfesor.getId().getCproSecc()).setInteger(
                "oldAgno", cursoProfesor.getId().getCproAgno()).setInteger(
                "oldSem", cursoProfesor.getId().getCproSem()).setInteger(
                "oldRutProf", cursoProfesor.getId().getCproRut()).setInteger(
                "oldPreg", preg).setInteger("correl", correl).executeUpdate();
    }

}
