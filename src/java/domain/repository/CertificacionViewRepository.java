/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AluCarId;
import java.util.List;
import domain.model.CertificacionView;

/**
 *
 * @author Ricardo
 */
public interface CertificacionViewRepository extends CrudGenericDAO<CertificacionView, Long> {

    List<CertificacionView> find(AluCarId id);
}
