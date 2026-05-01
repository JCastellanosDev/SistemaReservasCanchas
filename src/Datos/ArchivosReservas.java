package Datos;
import Servicios.SistemaReservas;
import modelos.Cancha;
import modelos.Reserva;

import java.io.*;


public class ArchivosReservas {
    public boolean cargar(SistemaReservas sistema){
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
                    sistema.getCanchas().add(cancha);
                }

                if (partes[0].equals("RESERVA")) {
                    int idReserva = Integer.parseInt(partes[1]);
                    String cliente = partes[2];
                    String horario = partes[3];
                    int idCancha = Integer.parseInt(partes[4]);

                    Cancha cancha = sistema.buscarCancha(idCancha);

                    if (cancha != null) {
                        Reserva reserva = new Reserva(idReserva, cliente, horario, cancha);
                        sistema.getReservas().add(reserva);
                        sistema.actualizarSiguienteIdReserva(idReserva);
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
    public void guardar(SistemaReservas sistema){
        File archivoInfo = new File("Parque Colinas.txt");

        try (FileWriter archivo = new FileWriter(archivoInfo)) {
            archivo.write("Canchas\n");
            for (Cancha c : sistema.getCanchas()) {
                archivo.write("CANCHA;" + c.getId() + ";" + c.getTipo() + ";" + c.isDisponible() + "\n");
            }

            archivo.write("\nReservas\n");
            for (Reserva r : sistema.getReservas()) {
                archivo.write("RESERVA;" + r.getId() + ";" + r.getCliente() + ";" + r.getHorario()
                        + ";" + r.getCancha().getId() + "\n");
            }

            System.out.println("Informacion guardada en: " + archivoInfo.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error guardando informacion");
        }
    }
}
