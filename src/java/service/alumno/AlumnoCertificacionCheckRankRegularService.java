/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import domain.model.AluCarId;
import session.AlumnoSession;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Ricardo
 */
public class AlumnoCertificacionCheckRankRegularService {
    public String service(GenericSession genericSession, AlumnoSession as,
            String key) {

        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aluCar = ws.getAluCar();
        AluCarId id = ws.getAluCar().getId();

        String prom = String.format("%.1f", ContextUtil.getDAO().getAluCarPersistence(ActionUtil.getDBUser()).getPromedioRanking(id, aluCar.getAcaCodMen(), aluCar.getAcaCodPlan()));
        String rankTmp = ContextUtil.getDAO().getDummyPersistence(ActionUtil.getDBUser()).getRankingRegular(id.getAcaRut(), id.getAcaCodCar(), id.getAcaAgnoIng(), id.getAcaSemIng());               
        String rank = "Ordenados los alumnos que ingresaron en su promoción, según su promedio de notas " + prom + " ocupó el lugar N° " + rankTmp.substring(0, rankTmp.indexOf("->")) + " alumnos.";
        as.setRank(rank);
       
        rankTmp = ContextUtil.getDAO().getDummyPersistence(ActionUtil.getDBUser()).getRankingRegularMencion(id.getAcaRut(), id.getAcaCodCar(), id.getAcaAgnoIng(), id.getAcaSemIng(), aluCar.getAcaCodMen());        
        String rankMencion = "Ordenados los alumnos que ingresaron en su promoción, según su promedio de notas " + prom + " ocupó el lugar N° " + rankTmp.substring(0, rankTmp.indexOf("->")) + " alumnos.";
        as.setRankMencion(rankMencion);

        return SUCCESS;
    }
}
