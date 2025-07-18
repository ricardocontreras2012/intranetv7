/*
 * @(#)CommonRequisitoAdicionalLogroEmitirActaService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.Map;
import session.GenericSession;

/**
 *
 * @author Ricardo Contreras S.
 */
public class CommonRequisitoAdicionalLogroEmitirActaService {

    /**
     * Method description
     *
     *
     * @param genericSession
     * @param map
     * @param key
     *
     * @return
     */
    public String service(GenericSession genericSession, Map<String, String[]> map, String key) {

        /*
        try {
            WorkSession ws = genericSession.getWorkSession(key);

            if (!ws.getInscripcionAdicionalLogroList().isEmpty()) {
                ActaRequisitoAdicionalLogro actaRequisitoAdicionalLogro;
                Integer folio;

                beginTransaction(ActionUtil.getDBUser());
                folio = ContextUtil.getDAO().getScalarPersistence(ActionUtil.getDBUser()).getSecuenciaActa();
                actaRequisitoAdicionalLogro = new ActaRequisitoAdicionalLogro();

                EstadoActa estadoActa = new EstadoActa();

                estadoActa.setEacCod("E");
                actaRequisitoAdicionalLogro.setAratAgno(
                        ws.getInscripcionAdicionalLogroList().get(0).getId().getIalAgno());
                actaRequisitoAdicionalLogro.setAratSem(
                        ws.getInscripcionAdicionalLogroList().get(0).getId().getIalSem());
                actaRequisitoAdicionalLogro.setAratTipo("N");
                actaRequisitoAdicionalLogro.setAratFolio(folio);
                actaRequisitoAdicionalLogro.setEstadoActa(estadoActa);
                actaRequisitoAdicionalLogro.setTrequisitoLogroAdicional(ws.getTrequisitoLogroAdicional());
                getDAO().getActaRequisitoAdicionalLogroPersistence(ActionUtil.getDBUser()).makePersistent(actaRequisitoAdicionalLogro);

                for (InscripcionAdicionalLogro inscripcionRequisitoAdicionalLogro
                        : ws.getInscripcionAdicionalLogroList()) {
                    String[] tmpNota = map.get("nota_" + inscripcionRequisitoAdicionalLogro.getId().getIalRut());
                    String[] tmpFecha = map.get("fecha_" + inscripcionRequisitoAdicionalLogro.getId().getIalRut());

                    if (isNotNull(tmpNota) && isNotNull(tmpFecha)) {
                        ActaCalificacionAdicionalLogro actaCalifRequisitoAdicionalLogro = new ActaCalificacionAdicionalLogro();
                        ActaCalificacionAdicionalLogroId actaCalifRequisitoAdicionalLogroId = new ActaCalificacionAdicionalLogroId();
                        AluCarId aluCarId
                                = inscripcionRequisitoAdicionalLogro.getAluCar().getId();

                        actaCalifRequisitoAdicionalLogroId.setAcratRut(aluCarId.getAcaRut());
                        actaCalifRequisitoAdicionalLogroId.setAcratCodCar(aluCarId.getAcaCodCar());
                        actaCalifRequisitoAdicionalLogroId.setAcratAgnoIng(aluCarId.getAcaAgnoIng());
                        actaCalifRequisitoAdicionalLogroId.setAcratSemIng(aluCarId.getAcaSemIng());
                        actaCalifRequisitoAdicionalLogroId.setAcratFolio(folio);
                        actaCalifRequisitoAdicionalLogro.setId(actaCalifRequisitoAdicionalLogroId);
                        actaCalifRequisitoAdicionalLogro.setActaRequisitoAdicionalLogro(actaRequisitoAdicionalLogro);
                        actaCalifRequisitoAdicionalLogro.setAcratNota(new BigDecimal(tmpNota[0]));
                        actaCalifRequisitoAdicionalLogro.setAcratFechaEvaluacion(stringToDate(tmpFecha[0]));
                        getDAO().getActaCalificacionAdicionalLogroPersistence(ActionUtil.getDBUser()).makePersistent(actaCalifRequisitoAdicionalLogro);
                    }
                }

                commitTransaction();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
        return SUCCESS;
    }
}
