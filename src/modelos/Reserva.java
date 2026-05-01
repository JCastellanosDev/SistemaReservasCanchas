package modelos;

public class Reserva {
    private int id;
    private String cliente;
    private String horario;
    private Cancha cancha;

    public Reserva(int id, String cliente, String horario, Cancha cancha){
        this.id = id;
        this.cliente = cliente;
        this.horario = horario;
        this.cancha = cancha;
    }

    public int getId() {return id;}
    public String getCliente() {return cliente;}
    public String getHorario() {return horario;}
    public Cancha getCancha() {return cancha;}

    public void mostrarReserva() {
        System.out.println("ID: " + id
                + " | Cliente: " + cliente
                + " | Horario: " + horario
                + " | Cancha: " + cancha.getTipo());
    }

    @Override
    public String toString() {
        return "ID: " + id
                + " | Cliente: " + cliente
                + " | Horario: " + horario
                + " | Cancha: " + cancha.getTipo();
    }
}
