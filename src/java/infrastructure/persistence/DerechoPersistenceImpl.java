/*
 * @(#)DerechoPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.DerechoPersistence;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCar;
import domain.model.AluCarId;
import domain.model.Derecho;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.sqlRestriction;
import org.hibernate.type.StandardBasicTypes;
import infrastructure.support.DerechoCoordinadorSupport;
import java.util.stream.Collectors;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class DerechoPersistenceImpl extends CrudAbstractDAO<Derecho, Long> implements DerechoPersistence {

    @SuppressWarnings("unchecked")
    @Override
    public void generarDerechos(AluCar aluCar) {

        Query query = getSession().getNamedQuery("DerechoAlumnoFunction");
        AluCarId id = aluCar.getId();

        query.setParameter(0, id.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, id.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, id.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, id.getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, aluCar.getAcaCodMen(), StandardBasicTypes.INTEGER);
        query.setParameter(5, aluCar.getAcaCodPlan(), StandardBasicTypes.INTEGER);
        query.setParameter(6, aluCar.getParametroMencion().getPmenAgnoIns(), StandardBasicTypes.INTEGER);
        query.setParameter(7, aluCar.getParametroMencion().getPmenSemIns(), StandardBasicTypes.INTEGER);
        query.setParameter(8, aluCar.getPlan().getMencion().getCarrera().getTcarrera().getTcrCtip(), StandardBasicTypes.INTEGER);
        query.setParameter(9, aluCar.getAluCarFunction().getNivel(), StandardBasicTypes.INTEGER);
        query.setParameter(10, aluCar.getAaingreso().getAaiViaIng().getViiCod(), StandardBasicTypes.INTEGER);

        query.executeUpdate();

    }

    /**
     * Method description
     *
     * @param aluCar
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Derecho> findDerMalla(AluCar aluCar) {

        Criteria criteria = getSession().createCriteria(Derecho.class);

        String sqlFilter = " asi_tipo='P' AND EXISTS(SELECT * FROM flag_inscripcion WHERE param_car = der_cod_car AND param_men =der_men AND (puede_inscribir=1 OR puede_modificar=1))";
        criteria.createAlias("asignatura", "asignatura");
        criteria.add(eq("aluCar.id", aluCar.getId()));
        criteria.add(sqlRestriction(sqlFilter));
        criteria.addOrder(asc("derTipo"));
        criteria.addOrder(asc("asignatura.asiCod"));

        return criteria.list();

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Derecho> findDerFI(AluCar aluCar) {
        Criteria criteria = getSession().createCriteria(Derecho.class);

        String sqlFilter = " asi_tipo in ('A','C','I','D') AND EXISTS(SELECT * FROM flag_inscripcion WHERE param_car = der_cod_car AND param_men =der_men AND puede_inscribirfi=1)";
        criteria.createAlias("asignatura", "asignatura");
        criteria.add(eq("aluCar.id", aluCar.getId()));
        criteria.add(sqlRestriction(sqlFilter));
        criteria.addOrder(asc("derTipo"));
        criteria.addOrder(asc("asignatura.asiCod"));

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DerechoCoordinadorSupport> getDerechoCoordinador(AluCar aluCar, Integer rut, String userType) {
        
        Query query = getSession().getNamedQuery("DerechoCoordinadorFunction");
        AluCarId id = aluCar.getId();

        query.setParameter(0, id.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, id.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, id.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, id.getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, aluCar.getAcaCodMen(), StandardBasicTypes.INTEGER);
        query.setParameter(5, aluCar.getAcaCodPlan(), StandardBasicTypes.INTEGER);
        query.setParameter(6, aluCar.getPlan().getMencion().getCarrera().getTcarrera().getTcrCtip(), StandardBasicTypes.INTEGER);
        query.setParameter(7, rut, StandardBasicTypes.INTEGER);
        query.setParameter(8, userType, StandardBasicTypes.STRING);

        List<Object[]> results = query.list();

        return results.stream()
                .map(obj -> {
                    DerechoCoordinadorSupport derecho = new DerechoCoordinadorSupport();
                    derecho.setDerAsign(((Number) obj[0]).intValue());
                    derecho.setDerNom((String) obj[1]);
                    derecho.setDerNivel(((Number) obj[2]).intValue());
                    derecho.setDerCred(((Number) obj[3]).intValue());
                    derecho.setDerSct(((Number) obj[4]).intValue());
                    derecho.setDerReq(((Number) obj[5]).intValue());
                    return derecho;
                })
                .collect(Collectors.toList());

    }

    /**
     *
     * @param aluCar
     * @param rut
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<DerechoCoordinadorSupport> getDerechoCoordinadorLibre(AluCar aluCar, Integer rut) {

        Query query = getSession().getNamedQuery("DerechoCoordinadorLibreFunction");
        AluCarId id = aluCar.getId();

        query.setParameter(0, id.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, id.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, id.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, id.getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, rut, StandardBasicTypes.INTEGER);

        List<Object[]> results = query.list();

        return results.stream()
                .map(obj -> {
                    DerechoCoordinadorSupport derecho = new DerechoCoordinadorSupport();
                    derecho.setDerAsign(((Number) obj[0]).intValue());
                    derecho.setDerNom((String) obj[1]);
                    derecho.setDerNivel(((Number) obj[2]).intValue());
                    derecho.setDerCred(((Number) obj[3]).intValue());
                    derecho.setDerSct(((Number) obj[4]).intValue());
                    derecho.setDerReq(((Number) obj[5]).intValue());
                    return derecho;
                })
                .collect(Collectors.toList());
    }

    /**
     *
     * @param aluCar
     * @param rut
     * @param userType
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<DerechoCoordinadorSupport> getDerechoFI(AluCar aluCar, Integer rut, String userType) {
 
        Query query = getSession().getNamedQuery("DerechoFIFunction");
        AluCarId id = aluCar.getId();

        query.setParameter(0, id.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, id.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, id.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, id.getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, rut, StandardBasicTypes.INTEGER);
        query.setParameter(5, userType, StandardBasicTypes.STRING);

        List<Object[]> results = query.list();

        return results.stream()
                .map(obj -> {
                    DerechoCoordinadorSupport derecho = new DerechoCoordinadorSupport();
                    derecho.setDerAsign(((Number) obj[0]).intValue());
                    derecho.setDerNom((String) obj[1]);
                    derecho.setDerNivel(((Number) obj[2]).intValue());
                    derecho.setDerCred(((Number) obj[3]).intValue());
                    derecho.setDerSct(((Number) obj[4]).intValue());
                    derecho.setDerReq(((Number) obj[5]).intValue());
                    return derecho;
                })
                .collect(Collectors.toList());
    }

}
