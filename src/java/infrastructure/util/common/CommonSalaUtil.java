/*
 * @(#)CommonSalaUtil.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.util.common;

import domain.model.ModuloHorario;
import domain.model.Sala;
import infrastructure.support.ReservaSalaSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.FormatUtil;
import java.awt.*;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Utilidades relacionadas con las salas, incluyendo manejo de horarios y
 * colores.
 *
 * @version 7, 24/05/2012
 */
public final class CommonSalaUtil {

    private CommonSalaUtil() {
    }

    /**
     * Devuelve el color asociado a una sala, en formato para PDF.
     *
     * @param sala La sala para la que se necesita el color.
     * @return El color asociado a la sala.
     */
    public static Color getColorPDF(Sala sala) {
        return FormatUtil.getColor(getColorHexPorSala(sala.getSalaNum()));
    }

    /**
     * Obtiene el horario de una sala entre un inicio y término, utilizando los
     * módulos de horario proporcionados.
     *
     * @param sala La sala de la cual obtener el horario.
     * @param inicio Hora de inicio del horario.
     * @param termino Hora de término del horario.
     * @param moduloHorarioList Lista de módulos de horario disponibles.
     * @return Matriz de horarios de la sala, organizada por módulo y día.
     */
    public static ReservaSalaSupport[][] getHorario(Sala sala, String inicio, String termino, List<ModuloHorario> moduloHorarioList) {
        List<ReservaSalaSupport> reservaList = ContextUtil.getDAO().getSalaRepository(ActionUtil.getDBUser()).getHorario(sala, inicio, termino);

        int modulos = moduloHorarioList.size();
        int dias = reservaList.size() / modulos;

        ReservaSalaSupport[][] horarioReturn = new ReservaSalaSupport[modulos][dias];

        // Utiliza un índice de contador en un único bucle
        IntStream.range(0, reservaList.size()).forEach(count -> {
            int nDia = count / modulos;
            int nMod = count % modulos;
            horarioReturn[nMod][nDia] = reservaList.get(count);
        });

        return horarioReturn;
    }

    /**
     * Obtiene la posición de un módulo en una lista de módulos de horario.
     *
     * @param modList Lista de módulos de horario.
     * @param modulo El número de módulo.
     * @return La posición del módulo en la lista.
     */
    public static int getPos(List<ModuloHorario> modList, int modulo) {
        return modList.get(modulo).getId().getModCod() - 1;
    }

    /**
     * Obtiene el color hexadecimal asociado a una sala.
     *
     * @param sala El identificador de la sala.
     * @return El color hexadecimal asociado a la sala.
     */
    public static String getColorHexPorSala(String sala) {
        return ContextUtil.getDAO().getScalarRepository(ActionUtil.getDBUser()).getColorHexPorSala(sala);
    }
}
