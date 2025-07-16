/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.secretariaproyectos;

import domain.model.CursoId;
import domain.model.Horario;
import domain.model.ModuloHorario;
import java.io.InputStream;
import static java.lang.Integer.parseInt;
import java.util.List;
import java.util.Map;
import session.GenericSession;
import infrastructure.support.ConvenioSupport;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.ActionUtil;
import infrastructure.util.AppStaticsUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.FormatUtil;
import infrastructure.util.LogUtil;

/**
 *
 * @author Ricardo
 */
public class SecretariaProyectosConvenioSaveConvenioService {

    public static ActionInputStreamUtil service(GenericSession genericSession, Map<String, String[]> parameters, String key) throws Exception {

        ActionInputStreamUtil ais = null;
        String name;
        InputStream input;

        String user = ActionUtil.getDBUser();

        Integer folio = ContextUtil.getDAO().getScalarPersistence(user).getSecuenciaConvenio();
        name = "Contrato_" + folio + ".pdf";
        input = getInput(genericSession, parameters, user, folio, name, key);
        ais = new ActionInputStreamUtil(name, AppStaticsUtil.PDF_MIME, input);
        LogUtil.setLog(genericSession.getRut(), folio);

        return ais;
    }

    private static InputStream getInput(GenericSession genericSession, Map<String, String[]> parameters, String user, Integer folio, String name, String key) {

        Integer rut = parseInt(parameters.get("rutAux")[0]);
        Integer rutFirma = parseInt(parameters.get("rutFirmaAux")[0]);
        String direcccion = FormatUtil.clean(parameters.get("direccionAux")[0]);
        String proyecto = parameters.get("proyectoAux")[0];
        String fecha = parameters.get("fechaAux")[0];
        String fechaInicio = parameters.get("fechaInicioAux")[0];
        String fechaTermino = parameters.get("fechaTerminoAux")[0];
        String tipoContrato = parameters.get("tipoContratoAux")[0];
        String funcion = FormatUtil.clean(parameters.get("funcionAux")[0]);
        Integer monto = parseInt(parameters.get("montoAux")[0]);
        String tipoMonto = parameters.get("tipoMontoAux")[0];
        String obsPago = parameters.get("obsPagoAux")[0];

        ContextUtil.getDAO().getFuncionarioPersistence(user).modify(rut, direcccion);
        ContextUtil.getDAO().getConvenioPersistence(user).insert(
                folio, rut, proyecto, rutFirma, fecha, fechaInicio, fechaTermino,
                tipoContrato, funcion, monto, tipoMonto, obsPago);

        switch (tipoContrato) {
            case "DOC":
                CursoId id = genericSession.getWorkSession(key).getCurso().getId();
                List<Horario> horarioList = ContextUtil.getDAO().getHorarioPersistence(user).getHorario(id);

                horarioList.stream()
                        .filter(horario -> "C".equals(String.valueOf(horario.getHorTipoClase())))
                        .forEach(horario -> {
                            String dia = horario.getId().getHorDia();
                            Integer modulo = horario.getId().getHorModulo();
                            ModuloHorario mh = ContextUtil.getDAO().getModuloHorarioPersistence(user).find(id.getCurAgno(), id.getCurSem(), modulo);
                            ContextUtil.getDAO().getConvenioPersistence(user).putHorario(folio, dia, mh.getModDesde(), mh.getModHasta());
                        });
                break;
            case "SER":
            case "SEC":
            case "DPG":
                parameters.entrySet().stream()
                        .filter(entry -> entry.getKey().startsWith("dia_"))
                        .forEach(entry -> {
                            String field = entry.getKey();
                            String dia = entry.getValue()[0];
                            int row = parseInt(field.substring(field.lastIndexOf('_') + 1));
                            String inicio = parameters.get("inicio_" + row)[0];
                            String termino = parameters.get("termino_" + row)[0];

                            ContextUtil.getDAO().getConvenioPersistence(user).putHorario(folio, dia, inicio, termino);
                        });

                break;
            default:
                break;

        }

        ConvenioSupport convenioSupport = new ConvenioSupport();

        return convenioSupport.print(folio, name);

    }
}
