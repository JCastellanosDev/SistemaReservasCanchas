package ui;
import Servicios.SistemaReservas;

public class MenuPrincipal {
    private SistemaReservas sistema;
    public MenuPrincipal(SistemaReservas sistema) {
        this.sistema = sistema;
    }

    public void mostrar(){
        int opc;
        do {
            IO.println("Sistema de Reservas de Canchas Deportivas");
            IO.println("\n1. Ver canchas");
            IO.println("2. Reservar cancha");
            IO.println("3. Ver reservas");
            IO.println("0. Salir");

            opc = LectorConsola.leerEntero("Ingrese una opcion: ");

            switch (opc) {
                case 1:
                    sistema.mostrarCanchas();
                    Espera.pausar();
                    Espera.limpiar();
                    break;
                case 2:
                    sistema.mostrarCanchas();
                    var id = LectorConsola.leerEntero("ID de la cancha: ");
                    var nombreCliente = IO.readln("Nombre del cliente: ");
                    var horaInicio = LectorConsola.leerEntero("Hora de inicio:  (7 - 20)");
                    var horaFinal = LectorConsola.leerEntero("Hora fin  (8-21): ");
                    String horario = horaInicio + ":00 - " + horaFinal + ":00";

                    sistema.reservarCancha(id, nombreCliente, horario);
                    Espera.pausar();
                    Espera.limpiar();
                    break;
                case 3:
                    sistema.mostrarReservas();
                    Espera.pausar();
                    Espera.limpiar();
                    break;
                case 99:
                    var contra = LectorConsola.leerEntero("Ingrese su contraseña: ");
                    if (contra == 35789){
                        MenuAdmin menuAdmin = new MenuAdmin(sistema);
                        menuAdmin.mostrar();
                    }else{
                        IO.println("---Acceso Denegado---");
                    }

                    break;

                case 0:
                    IO.println("Saliendo...");
                    break;
                default:
                    IO.println("Opcion no valida");
                    Espera.pausar();
                    Espera.limpiar();
            }

        } while (opc != 0);
    }
}
