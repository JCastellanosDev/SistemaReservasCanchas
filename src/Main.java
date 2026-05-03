import Datos.ArchivosReservas;
import Servicios.SistemaReservas;
import modelos.Cancha;

import ui.MenuPrincipal;

void main() {
    SistemaReservas sistema = new SistemaReservas();
    ArchivosReservas archivo = new ArchivosReservas();

    if (!archivo.cargar(sistema)) {
        sistema.agregarCancha("Futbol");
        sistema.agregarCancha("Tenis");

    }
    MenuPrincipal menu = new MenuPrincipal(sistema);
    menu.mostrar();
	archivo.guardar(sistema);

}
