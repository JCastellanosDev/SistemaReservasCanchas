package Servicios;

import modelos.Cancha;
import modelos.Reserva;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SistemaReservas {
    private ArrayList<Cancha> canchas = new ArrayList<>();
    private ArrayList<Reserva> reservas = new ArrayList<>();

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

        Reserva nueva = new Reserva(reservas.size() + 1, cliente, horario, cancha);
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
        }
    }

    public void guardarInfo() {
        File archivoInfo = new File("Parque Colinas.txt");

        try (FileWriter archivo = new FileWriter(archivoInfo)) {
            archivo.write("Canchas\n");
            for (Cancha c : canchas) {
                archivo.write("CANCHA;" + c.getId() + ";" + c.getTipo() + ";" + c.isDisponible() + "\n");
            }

            archivo.write("\nReservas\n");
            for (Reserva r : reservas) {
                archivo.write("RESERVA;" + r.getId() + ";" + r.getCliente() + ";" + r.getHorario()
                        + ";" + r.getCancha().getId() + "\n");
            }

            System.out.println("Informacion guardada en: " + archivoInfo.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error guardando informacion");
        }
    }

    public boolean cargarInfo() {
        File archivoInfo = new File("Parque Colinas.txt");

        if (!archivoInfo.exists()) {
            return false;
        }

        try (BufferedReader lector = new BufferedReader(new FileReader(archivoInfo))) {
            String linea;

            while ((linea = lector.readLine()) != null) {
                if (linea.isBlank() || linea.equals("Canchas") || linea.equals("Reservas")) {
                    continue;
                }

                String[] partes = linea.split(";");

                if (partes[0].equals("CANCHA")) {
                    int id = Integer.parseInt(partes[1]);
                    String tipo = partes[2];
                    boolean disponible = Boolean.parseBoolean(partes[3]);

                    Cancha cancha = new Cancha(id, tipo);
                    cancha.setDisponible(disponible);
                    canchas.add(cancha);
                }

                if (partes[0].equals("RESERVA")) {
                    int idReserva = Integer.parseInt(partes[1]);
                    String cliente = partes[2];
                    String horario = partes[3];
                    int idCancha = Integer.parseInt(partes[4]);

                    Cancha cancha = buscarCancha(idCancha);

                    if (cancha != null) {
                        Reserva reserva = new Reserva(idReserva, cliente, horario, cancha);
                        reservas.add(reserva);
                    }
                }
            }

            System.out.println("Informacion cargada correctamente");
            return true;
        } catch (IOException e) {
            System.out.println("Error cargando informacion");
            return false;
        }
    }
}
