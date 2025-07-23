/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.CursoId;
import java.util.List;
import domain.model.RespEnctaAyuCursoView;

/**
 *
 * @author Ricardo
 */
public interface RespEnctaAyuCursoRepository extends CrudGenericDAO<RespEnctaAyuCursoView, Long> {

    /**
     * Method description
     *
     * @param cursoId
     * @return
     */
    List<RespEnctaAyuCursoView> find(CursoId cursoId);
}
