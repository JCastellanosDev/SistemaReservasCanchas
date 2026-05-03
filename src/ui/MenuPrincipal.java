package ui;
import Servicios.SistemaReservas;
import Servicios.ValidarHorario;
import Servicios.HorarioParque;

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

                    String rango = ValidarHorario.rangoDisponible();
                    if (rango == null) {
                        IO.println("Lo sentimos, ya no hay horarios disponibles por hoy.");
                        IO.println("El parque cierra a las " + HorarioParque.CIERRE.getTexto() + ".");
                        Espera.pausar();
                        Espera.limpiar();
                        break;
                    }

                    var id = LectorConsola.leerEntero("ID de la cancha: ");
                    var nombreCliente = IO.readln("Nombre del cliente: ");

                    IO.println("Horarios disponibles por hoy:" + rango);
                    IO.println("Formato de 24 Horas: HH:MM");



                    String horaInicio, horaFi, errorHorario;
                    do{
                        horaInicio = IO.readln("Horario de inicio: ");
                        horaFi = IO.readln("Horario de fin: ");
                        errorHorario = ValidarHorario.validar(horaInicio, horaFi);

                        if(errorHorario != null){
                            IO.println("Error: " + errorHorario);
                            IO.println("Intenta de nuevo");
                        }
                    }while(errorHorario != null);

                    String horario = ValidarHorario.formatear(horaInicio, horaFi);
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
