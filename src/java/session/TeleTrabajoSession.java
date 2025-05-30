/*
 * @(#)TeletrabajoSession.java
 *
 * Copyright (c) 2023 FAE-USACH
 */
package session;

import domain.model.TareaActividadTeletrabajo;
import domain.model.ActividadTeletrabajo;
import domain.model.FuncionarioTeletrabajo;
import domain.model.EvidenciaTareaActTeletrabajo;
import java.io.Serializable;
import java.util.List;
import infrastructure.support.TeleTrabajoSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 * Class description
 *
 * @author Felipe
 * @version 1.0, 21/09/2023
 */
public final class TeleTrabajoSession implements Serializable {

    private static final long serialVersionUID = 1L;
    private FuncionarioTeletrabajo funcionarioTeletrabajo;
    private ActividadTeletrabajo actividadTeletrabajo;
    private List<TareaActividadTeletrabajo> tareaList;
    private TeleTrabajoSupport[] horarioFuncionario;
    private List<FuncionarioTeletrabajo> funcionarioList;
    private List<ActividadTeletrabajo> actividadList;
    private TareaActividadTeletrabajo tarea;
    private List<EvidenciaTareaActTeletrabajo> evidenciaList;
    
    /**
     *
     */
    public TeleTrabajoSession() {
    }

    public FuncionarioTeletrabajo getFuncionarioTeletrabajo() {
        return funcionarioTeletrabajo;
    }

    public void setFuncionarioTeletrabajo(FuncionarioTeletrabajo funcionarioTeletrabajo) {
        this.funcionarioTeletrabajo = funcionarioTeletrabajo;
    }

    public ActividadTeletrabajo getActividadTeletrabajo() {
        return actividadTeletrabajo;
    }

    public void setActividadTeletrabajo(ActividadTeletrabajo actividadTeletrabajo) {
        this.actividadTeletrabajo = actividadTeletrabajo;
    }

    public List<TareaActividadTeletrabajo> getTareaList() {
        return tareaList;
    }

    public void setTareaList(List<TareaActividadTeletrabajo> tareaList) {
        this.tareaList = tareaList;
    }

    public TeleTrabajoSupport[] getHorarioFuncionario() {
        return horarioFuncionario;
    }

    public void setHorarioFuncionario(TeleTrabajoSupport[] horarioFuncionario) {
        this.horarioFuncionario = horarioFuncionario;
    }

    public List<FuncionarioTeletrabajo> getFuncionarioList() {
        return funcionarioList;
    }

    public void setFuncionarioList(List<FuncionarioTeletrabajo> funcionarioList) {
        this.funcionarioList = funcionarioList;
    }

    public List<ActividadTeletrabajo> getActividadList() {
        return actividadList;
    }

    public void setActividadList(List<ActividadTeletrabajo> actividadList) {
        this.actividadList = actividadList;
    }

    public TareaActividadTeletrabajo getTarea() {
        return tarea;
    }

    public void setTarea(TareaActividadTeletrabajo tarea) {
        this.tarea = tarea;
    }
    
    public boolean isTeletrabajoJefe(Integer rut)
    {        
        return (ContextUtil.getDAO().getFuncionarioPersistence(ActionUtil.getDBUser()).findTeleTrabajoJefe(rut) != null);
    }
    
    public boolean isTeletrabajoFuncionario(Integer rut)
    {        
        return (ContextUtil.getDAO().getFuncionarioPersistence(ActionUtil.getDBUser()).findTeleTrabajo(rut) != null);
    }

    public List<EvidenciaTareaActTeletrabajo> getEvidenciaList() {
        return evidenciaList;
    }

    public void setEvidenciaList(List<EvidenciaTareaActTeletrabajo> evidenciaList) {
        this.evidenciaList = evidenciaList;
    }
    
    public boolean isSuperTeletrabajo(Integer rut)
    {        
        return (ContextUtil.getDAO().getFuncionarioPersistence(ActionUtil.getDBUser()).findSuperTeleTrabajo(rut) != null);
    }
    
}
