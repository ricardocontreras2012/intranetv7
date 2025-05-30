/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.teletrabajo;

import session.GenericSession;
import java.io.File;
import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.EvidenciaTareaActTeletrabajo;
import domain.model.TareaActividadTeletrabajo;
import java.io.IOException;
import domain.repository.EvidenciaTareaActTeletrabajoPersistence;
import session.TeleTrabajoSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import static infrastructure.util.common.CommonArchivoUtil.doUpload;
import static infrastructure.util.common.CommonArchivoUtil.getMaterialFileName;
import infrastructure.util.common.CommonSequenceUtil;

/**
 *
 * @author Javier
 */
public class TeleTrabajoUpLoadEvidenciaService {

    /**
     * Metodo servicio que realiza la 
     * subida/actulaizacion de la evidencia.
     *
     * @param action
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @param teleSession
     * @param uploadedFile
     * @param uploadedFileFileName
     * @param uploadedFileContentType
     * @param posT
     * @param correl
     * @param descripcionEvidencia
     * @return Action status.
     * @throws java.io.IOException
     */
    public static String service(ActionCommonSupport action, GenericSession genericSession, String key, TeleTrabajoSession teleSession, File[] uploadedFile, String[] uploadedFileFileName, String[] uploadedFileContentType, Integer posT, Integer correl, String descripcionEvidencia) throws IOException {
        //Creacion de variables a utilizar
        EvidenciaTareaActTeletrabajoPersistence evidenciaItemActTeletrabajoPersistence = ContextUtil.getDAO().getEvidenciaTarActTeletrabajoPersistence("TT");
        TareaActividadTeletrabajo tareaActividadTeletrabajo = teleSession.getTareaList().get(posT);
        EvidenciaTareaActTeletrabajo evidencia = evidenciaItemActTeletrabajoPersistence.find(tareaActividadTeletrabajo.getId().getTatelRut(), tareaActividadTeletrabajo.getId().getTatelFecha(), tareaActividadTeletrabajo.getId().getTatelTarea(), correl);

        int i = 0;
        
        //Se crea o se actualiza la evidencia segun corresponda.
        try {
            if (evidencia == null) {
                //La evidencia no existe.
                if (uploadedFile == null) {
                    //No se ha ingresado un archivo
                    beginTransaction("TT");
                    evidenciaItemActTeletrabajoPersistence.insertEvidencia(tareaActividadTeletrabajo.getId().getTatelRut(), tareaActividadTeletrabajo.getId().getTatelFecha(), tareaActividadTeletrabajo.getId().getTatelTarea(), descripcionEvidencia, "");
                    commitTransaction();
                } else {
                    //Se ha ingresado un archivo
                    for (String name : uploadedFileFileName) {

                        Integer folio = CommonSequenceUtil.getDocumentSeq();
                        String nombre = getMaterialFileName(name, folio);
                        doUpload(action, uploadedFile[i], nombre, "tele");

                        beginTransaction("TT");
                        evidenciaItemActTeletrabajoPersistence.insertEvidencia(tareaActividadTeletrabajo.getId().getTatelRut(), tareaActividadTeletrabajo.getId().getTatelFecha(), tareaActividadTeletrabajo.getId().getTatelTarea(), descripcionEvidencia, nombre);
                        commitTransaction();

                        i++;
                    }
                }

            } else {
                //La evidencia ya existe
                if (uploadedFile == null) {
                    //No ha que actualizar el archivo
                    evidencia.setEtatelDes(descripcionEvidencia);

                    beginTransaction("TT");
                    evidenciaItemActTeletrabajoPersistence.update(evidencia);
                    commitTransaction();
                } else {
                    //Hay que actualizar el archivo
                    for (String name : uploadedFileFileName) {
                        Integer folio = CommonSequenceUtil.getDocumentSeq();
                        String nombre = getMaterialFileName(name, folio);
                        doUpload(action, uploadedFile[i], nombre, "tele");

                        evidencia.setEtatelDes(descripcionEvidencia);
                        evidencia.setEtatelFile(nombre);

                        beginTransaction("TT");
                        evidenciaItemActTeletrabajoPersistence.update(evidencia);
                        commitTransaction();

                        correl++;
                        i++;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }

}
