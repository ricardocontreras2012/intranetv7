/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.repository;

import domain.model.AluCarId;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public interface DummyPersistence {
    void setPasswordUser(Integer rut, String user, String password);
    String getRandomPassword(String user);
    String getEmail(Integer rut);
    Integer createSolicitudCertificado(AluCarId id, Integer monto, String jsonList);
    void createPago(Integer correl, Integer idTrans);
    Object[] getCertificado(Integer solicitud);
    Object[] getSolicitudCertificado(Integer idTrans);
    void setEstadoCarrito(Integer correl, Integer ord, String estado);
    void setEstadoPago(Integer correl, String estado);
    String getRankingEgresado(Integer rut, Integer carrera, Integer agnoIng, Integer semIng);
    String getRankingEgresadoMencion(Integer rut, Integer carrera, Integer agnoIng, Integer semIng, Integer mencion);
    String getRankingRegular(Integer rut, Integer carrera, Integer agnoIng, Integer semIng);
    String getRankingRegularMencion(Integer rut, Integer carrera, Integer agnoIng, Integer semIng, Integer mencion);
    void setGlosa(Integer correl, Integer ord, String glosa);
    List<Integer> getMinors(Integer rut, String type);
}
