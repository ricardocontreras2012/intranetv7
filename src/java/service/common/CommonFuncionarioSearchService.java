/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import com.opensymphony.xwork2.Action;
import domain.model.Funcionario;
import java.util.ArrayList;
import java.util.List;
import domain.repository.FuncionarioRepository;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.FormatUtil.cleanName;
import infrastructure.util.LogUtil;

/**
 *
 * @author Ricardo
 */
public class CommonFuncionarioSearchService {

    public String service(GenericSession genericSession, Integer rut, String paterno, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        List<Funcionario> list = new ArrayList<>();

        FuncionarioRepository funcionarioRepo
                = ContextUtil.getDAO().getFuncionarioRepository(ActionUtil.getDBUser());

        if (rut != null) {
            Funcionario funcionario = funcionarioRepo.find(rut);
            if (funcionario == null) {
                funcionarioRepo.creaFuncionario(rut);
                funcionario = funcionarioRepo.find(rut);
            }
            
            list.add(funcionario);

        } else {
            list = funcionarioRepo.find(cleanName(paterno));
        }   
        
        ws.setFuncionarioList(list);
        
        LogUtil.setLog(genericSession.getRut(), "RUT=" + rut + " paterno=" + paterno);
        return Action.SUCCESS;
    }
}
