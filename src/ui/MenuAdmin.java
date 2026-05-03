package ui;
import Datos.ArchivosReservas;
import Servicios.SistemaReservas;
import modelos.Cancha;


public class MenuAdmin {
    private SistemaReservas sistema;
    private ArchivosReservas archivo = new ArchivosReservas();

    public MenuAdmin(SistemaReservas sistema) {
        this.sistema = sistema;
    }
    public void mostrar(){
        IO.println("Aceso super usuario concedido");
        Espera.esperar(1000);
        Espera.limpiar();

        int opcAdmin;
        do {
            IO.println("------Bienvenido Tuyin-------");
            IO.println("¿Que deseas hacer?");
            IO.println("1.- Agregar nueva cancha");
            IO.println("2.- Eliminar cancha");
            IO.println("3.- Eliminar reserva");
            IO.println("4.- Eliminar todas las reservas");
            IO.println("0.- Salir de super usuario");
            opcAdmin = LectorConsola.leerEntero("Opcion: ");

            switch (opcAdmin){
                case 1:
                    var tipoCancha = IO.readln("Tipo de cancha nueva: ");
                    sistema.agregarCancha(tipoCancha);
                    Espera.pausar();
                    Espera.limpiar();
                    break;


                case 2:
                    var idE = LectorConsola.leerEntero("ID de la cancha a eliminar : ");
                    Cancha canchaEliminar = sistema.buscarCancha(idE);
                    if (canchaEliminar == null) {
                        IO.println("Cancha no encontrada");
                        Espera.pausar();
                        Espera.limpiar();
                        break;
                    }

                    IO.println("Cancha seleccionada:");
                    canchaEliminar.mostrarInfo();
                    String decision = IO.readln("Estas seguro de eliminar esta cancha? (s/n) : ");
                    if (!decision.equalsIgnoreCase("s")){
                        IO.println("Operacion cancelada");
                        Espera.pausar();
                        Espera.limpiar();
                        break;
                    }
                    sistema.eliminarCancha(idE);
                    Espera.pausar();
                    Espera.limpiar();
                    break;
	                case 3:
	                    IO.println("Reservas registradas:");
	                    sistema.mostrarReservas();
	                    int idReserva = LectorConsola.leerEntero("ID de la reserva a eliminar : ");
	                    sistema.eliminarReserva(idReserva);
	                    Espera.pausar();
                    Espera.limpiar();
                    break;
                case 4:
                    IO.println("===ADVERTENCIA====");
                    IO.println("Esta accion elimara todas las reservas registradas.");
                    sistema.mostrarReservas();
                    String borrar = IO.readln("Escribe confirmar para continuar");
                    if (borrar.equalsIgnoreCase("confirmar")){
                        sistema.eliminarTodasReservas();
                    }else {
                        IO.println("Operacion cancelada");
                    }
                    Espera.pausar();
                    Espera.limpiar();
                    break;

                case 0:
                    IO.println("Saliendo de super usuario");
                    Espera.pausar();
                    Espera.limpiar();
                    break;
                default:
                    IO.println("Opcion no valida");
                    Espera.pausar();
                    Espera.limpiar();
            }
        } while (opcAdmin != 0);
    }
}
