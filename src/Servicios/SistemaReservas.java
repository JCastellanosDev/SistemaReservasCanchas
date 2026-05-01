package Servicios;

import modelos.Cancha;
import modelos.Reserva;
import java.util.ArrayList;

public class SistemaReservas {
    private ArrayList<Cancha> canchas = new ArrayList<>();
    private ArrayList<Reserva> reservas = new ArrayList<>();
    private int siguienteIdReserva = 1;

    public void agregarCancha(Cancha cancha) {
        for (Cancha c : canchas) {
            if (c.getId() == cancha.getId()) {
                System.out.println("Cancha con mismo ID");
                return;
            }
        }

        canchas.add(cancha);
        System.out.println("Cancha agregada");
    }

    public void eliminarCancha(int id) {
        for (Reserva r : reservas) {
            if (r.getCancha().getId() == id) {
                System.out.println("No se puede eliminar la cancha, tiene reservas activas");
                return;
            }
        }

        for (Cancha c : canchas) {
            if (c.getId() == id) {
                canchas.remove(c);
                System.out.println("Cancha eliminada");
                return;
            }
        }

        System.out.println("Cancha no encontrada");
    }

    public boolean existeCancha(int id) {
        return buscarCancha(id) != null;
    }

    public Cancha buscarCancha(int id) {
        for (Cancha c : canchas) {
            if (c.getId() == id) {
                return c;
            }
        }

        return null;
    }

    public void reservarCancha(int idCancha, String cliente, String horario) {
        Cancha cancha = buscarCancha(idCancha);

        if (cancha == null) {
            System.out.println("Cancha no encontrada");
            return;
        }

        for (Reserva r : reservas) {
            if (r.getCancha().getId() == idCancha && r.getHorario().equals(horario)) {
                System.out.println("Esta cancha ya esta ocupada en este horario");
                return;
            }
        }

        Reserva nueva = new Reserva(siguienteIdReserva, cliente, horario, cancha);
        siguienteIdReserva++;
        reservas.add(nueva);
        cancha.setDisponible(false);
        System.out.println("Reserva agregada");
    }

    public void mostrarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas registradas");
            return;
        }

        for (Reserva r : reservas) {
            r.mostrarReserva();
        }
    }

	    public void mostrarCanchas() {
	        if (canchas.isEmpty()) {
	            System.out.println("No hay canchas registradas");
	            return;
	        }

	        for (Cancha c : canchas) {
	            c.mostrarInfo();
	            boolean tieneReservas = false;

	            System.out.println("Horarios ocupados:");
	            for (Reserva r : reservas) {
	                if (r.getCancha().getId() == c.getId()) {
	                    System.out.println("- " + r.getHorario() + " | Cliente: " + r.getCliente());
	                    tieneReservas = true;
	                }
	            }

	            if (!tieneReservas) {
	                System.out.println("- Sin horarios ocupados");
	            }

	            System.out.println();
	        }
	    }

    public void eliminarReserva(int idReserva){
        for (Reserva r : reservas) {
            if (r.getId() == idReserva){
                Cancha cancha = r.getCancha();
                reservas.remove(r);
                boolean canchaTieneReservas = false;

                for(Reserva otraReserva : reservas){
                    if(otraReserva.getCancha().getId() == cancha.getId()){
                        canchaTieneReservas = true;
                        break;
                    }
                }
                if(!canchaTieneReservas){
                    cancha.setDisponible(true);
                }
                IO.println("Reserva eliminada");
                return;
            }
    }
        IO.println("Reserva no encontrada");
    }
    public ArrayList<Reserva> getReservas() {return reservas;}

    public ArrayList<Cancha> getCanchas() {return canchas;}

    public void actualizarSiguienteIdReserva(int idReserva) {
        if (idReserva >= siguienteIdReserva) {
            siguienteIdReserva = idReserva + 1;
        }
    }

}
