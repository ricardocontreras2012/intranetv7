/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.repository;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.Convenio;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public interface ConvenioRepository extends CrudGenericDAO<Convenio, Long> {
    Convenio find(Integer id);
    List<Convenio> getMisConvenios(Integer rut);
    void delConvenio(Integer convenio);
    void insert(Integer folio, Integer rut, String proyecto,Integer rutFirma, String fecha,
            String fechaInicio, String fechaTermino, String tipo, 
            String funcion, Integer monto, String tipoMonto, String obsPago);
    void putHorario(Integer folio, String dia, String inicio, String termino);
}
