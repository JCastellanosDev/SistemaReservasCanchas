package Servicios;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class ValidarHorario {

    public static LocalTime parsearHora(String texto) {
        try {
            return LocalTime.parse(texto.trim(), HorarioParque.FORMATO);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Formato invalido \"" + texto + "\". Usa HH:MM en 24 horas (ej: 07:00, 14:30)."
            );
        }
    }

    public static String validar(String inicioStr, String finStr) {
        LocalTime inicio, fin;

        try {
            inicio = parsearHora(inicioStr);
        } catch (IllegalArgumentException e) {
            return "Hora de inicio — " + e.getMessage();
        }

        try {
            fin = parsearHora(finStr);
        } catch (IllegalArgumentException e) {
            return "Hora de fin — " + e.getMessage();
        }

        if (!fin.isAfter(inicio))
            return "La hora de fin (" + finStr + ") debe ser posterior a la de inicio (" + inicioStr + ").";

        if (inicio.isBefore(HorarioParque.APERTURA.getHora()))
            return "Solo se puede reservar a partir de las " + HorarioParque.APERTURA.getTexto() + ".";

        if (inicio.isAfter(HorarioParque.ULTIMA_RESERVACION.getHora()))
            return "La ultima reserva inicia a las " + HorarioParque.ULTIMA_RESERVACION.getTexto()
                    + ", cerramos a las " + HorarioParque.CIERRE.getTexto() + ".";

        if (fin.isAfter(HorarioParque.CIERRE.getHora()))
            return "La reserva no puede terminar despues de las " + HorarioParque.CIERRE.getTexto() + ".";

        LocalTime ahora = LocalTime.now();
        if (!inicio.isAfter(ahora))
            return "No puedes reservar a las " + inicioStr + ". La hora actual es "
                    + ahora.format(HorarioParque.FORMATO) + ".";

        return null;
    }

    public static String rangoDisponible() {
        LocalTime ahora = LocalTime.now();
        LocalTime apertura = LocalTime.now();
        LocalTime ultimaReserva = LocalTime.now();

        LocalTime desde = ahora.isBefore(apertura) ? apertura : ahora.plusMinutes(1);

        if (desde.isAfter(ultimaReserva)) return null;

        return desde.format(HorarioParque.FORMATO)
                + " - "
                + ultimaReserva.format(HorarioParque.FORMATO)
                + "  (fin maximo: " + HorarioParque.CIERRE.getTexto() + ")";
    }

    public static String formatear(String inicio, String fin) {
        return inicio.trim() + " - " + fin.trim();
    }
}