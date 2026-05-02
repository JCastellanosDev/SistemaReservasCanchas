package Servicios;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public enum HorarioParque {
    APERTURA(7, 0),
    ULTIMA_RESERVACION(20, 0),
    CIERRE(21, 0);

    private final LocalTime hora;
    public static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("HH:mm");

    HorarioParque(int hora, int minuto){
        this.hora = LocalTime.of(hora, minuto);
    }

    public LocalTime getHora() {
        return hora;
    }
    public String getTexto(){
        return hora.format(FORMATO);
    }
}
