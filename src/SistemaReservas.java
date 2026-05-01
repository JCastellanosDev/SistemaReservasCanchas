import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
public class SistemaReservas {

    ArrayList<Cancha> canchas = new ArrayList<>();
    ArrayList<Reserva> reservas = new ArrayList<>();

    public void agregarCancha(Cancha cancha){
        for (Cancha c : canchas){
            if (c.id == cancha.id){
                IO.println("Cancha con mismo ID");
                return;
            }
        }
        canchas.add(cancha);
        IO.println("Cancha agregada");
    }

    public void eliminarCancha(int id){
        for (Reserva r : reservas){
            if (r.cancha.id == id){
                IO.println("No se puede eliminar la cancha, tiene reservas activas");
                return;
            }
        }
        for (Cancha c : canchas){
            if (c.id == id){
                canchas.remove(c);
                IO.println("Cancha eliminada");
                return;
            }
        }
        IO.println("Cancha no encotrada");
    }

    public boolean existeCancha(int id){
        for (Cancha c : canchas){
            if (c.id == id){
                return true;
            }
        }
        return false;
    }

    public Cancha buscarCancha(int id){
        for (Cancha c : canchas){
            if (c.id == id){
                return c;
            }
        }
        return null;
    }

    public void reservarCancha(int idCancha, String cliente, String horario){
        for (Cancha c : canchas) {
            if (c.id == idCancha){
                for (Reserva r : reservas){
                    if(r.cancha.id == idCancha && r.horario.equals(horario)){
                        IO.println("Esta cancha ya esta ocupada en este horario");
                        return;
                    }
                }

                Reserva nueva = new Reserva(reservas.size() + 1, cliente, horario, c);
                reservas.add(nueva);
                c.disponible = false;
                IO.println("Reserva agregada");
                return;
            }
        }

        IO.println("Cancha no encontrada");
    }

    public void mostrarReservas(){
        if (reservas.isEmpty()){
            IO.println("No hay reservas registradas");
            return;
        }

        for (Reserva r : reservas){
            r.mostrarReserva();
        }
    }

   public void mostrarCanchas(){
        for ( Cancha c : canchas){
            c.mostrarInfo();
        }
   }

   public void guardarInfo(){
        try{
            File archivoInfo = new File("Parque Colinas.txt");
            IO.println("Se guardara en: " + archivoInfo.getAbsolutePath());

            FileWriter archivo = new FileWriter(archivoInfo);


	            archivo.write("Canchas\n");
	            for (Cancha c : canchas){
	            archivo.write("CANCHA;" + c.id + ";" + c.tipo + ";" + c.disponible + "\n");
	            }

	            archivo.write("\nReservas\n");
	            for (Reserva r : reservas){
	                archivo.write("RESERVA;" + r.id + ";" + r.cliente + ";" + r.horario + ";" + r.cancha.id + "\n");
	            }

            archivo.close();
            IO.println("Informacion guardada en: " + archivo.toString());

        }catch(IOException e){
            IO.println("Error guardando informacion");
        }
   }

   public boolean cargarInfo(){
        File archivoInfo = new File("Parque Colinas.txt");
        if (!archivoInfo.exists()){
            return false;
        }
        try{
            BufferedReader lector = new BufferedReader(new FileReader(archivoInfo));

	            String linea;

	            while ((linea = lector.readLine()) != null){
	                if (linea.isBlank() || linea.equals("Canchas") || linea.equals("Reservas")){
	                    continue;
	                }

	                String[] partes = linea.split(";");

                if (partes[0].equals("CANCHA")){
                    int id = Integer.parseInt(partes[1]);
                    String tipo = partes[2];
                    boolean disponible = Boolean.parseBoolean(partes[3]);

	                    Cancha cancha = new Cancha(id, tipo);
	                    cancha.disponible = disponible;
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
            lector.close();
            IO.println("Infomracion cargada correctamente");
            return true;
   }catch (IOException e){
        IO.println("Error cargando informacion");
        return false;
   }
   }
}
