public class Reserva {
    int id;
    String cliente;
    String horario;
    Cancha cancha;

    public Reserva(int id, String cliente, String horario, Cancha cancha){
        this.id = id;
        this.cliente = cliente;
        this.horario = horario;
        this.cancha = cancha;
    }

    public void mostrarReserva(){
        IO.println("| Reserva ID: " + id +
                "\n| Cliente : " + cliente +
                "\n| Horario: " + horario +
                "\n| Cancha: " + cancha.tipo);

    }
}
