package ui;
import Servicios.SistemaReservas;
import modelos.Cancha;

public class MenuAdmin {
    private SistemaReservas sistema;

    public MenuAdmin(SistemaReservas sistema) {
        this.sistema = sistema;
    }
    public void mostrar(){
        IO.println("Acesso super usuario concedido");
        Espera.esperar(2000);
        Espera.limpiar();

        int opcAdmin;
        do {
            IO.println("------Bienvenido Tuyin-------");
            IO.println("¿Que deseas hacer?");
            IO.println("1.- Agregar nueva cancha");
            IO.println("2.- Eliminar cancha");
            IO.println("3.- Guardar informacion");
            IO.println("0.- Salir de super usuario");
            opcAdmin = LectorConsola.leerEntero("Opcion: ");

            switch (opcAdmin){
                case 1:
                    var idM = LectorConsola.leerEntero("ID de la cancha nueva : ");
                    if(sistema.existeCancha(idM)){
                        IO.println("Ya existe una cancha con ese ID");
                        Espera.pausar();
                        Espera.limpiar();
                        break;
                    }
                    var tipoCancha = IO.readln("Tipo de cancha nueva : ");
                    sistema.agregarCancha(new Cancha(idM, tipoCancha));
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
                    sistema.guardarInfo();
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
