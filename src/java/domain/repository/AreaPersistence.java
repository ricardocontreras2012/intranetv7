/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.Area;
import java.util.List;

/**
 *
 * @author Usach
 */
public interface  AreaPersistence extends CrudGenericDAO<Area, Long> {
     List<Area> getAreas(Integer rut, String type);    
}
