/*
 * @(#)DerechoPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence;

import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCar;
import domain.model.AluCarId;
import domain.model.Derecho;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.type.StandardBasicTypes;
import infrastructure.support.DerechoCoordinadorSupport;
import java.util.stream.Collectors;
import domain.repository.DerechoRepository;
import java.sql.CallableStatement;
import java.sql.Clob;
import org.hibernate.Session;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class DerechoPersistenceImpl extends CrudAbstractDAO<Derecho, Long> implements DerechoRepository {    
    @SuppressWarnings("unchecked")
    @Override
    public String getDerechosJson(AluCarId id) {

        try {
            Session session = getSession();

            // Usar doReturningWork para trabajar con la conexión real (Hikari lo maneja por detrás)
            String result = null;
            result = session.doReturningWork(connection -> {
                String json = null;

                try (CallableStatement stmt = connection.prepareCall("{ ? = call derecho_inscripcion_pkg.get_derechos_json(?,?,?,?) }")) {
                    stmt.registerOutParameter(1, java.sql.Types.CLOB);

                    stmt.setInt(2, id.getAcaRut());
                    stmt.setInt(3, id.getAcaCodCar());
                    stmt.setInt(4, id.getAcaAgnoIng());
                    stmt.setInt(5, id.getAcaSemIng());
                    
                    stmt.execute();

                    Clob clob = stmt.getClob(1);
                    if (clob != null) {
                        json = clob.getSubString(1, (int) clob.length());
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    json = "{\"error\": \"" + ex.getMessage() + "\"}";
                }

                return json;
            });
            
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ""; // Retorna una lista vacía en caso de excepción
        }
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
