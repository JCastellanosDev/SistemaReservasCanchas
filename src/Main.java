import Servicios.SistemaReservas;
import modelos.Cancha;

import ui.MenuPrincipal;

void main() {
    SistemaReservas sistema = new SistemaReservas();
    if (!sistema.cargarInfo()) {
        sistema.agregarCancha(new Cancha(1, "Futbol"));
        sistema.agregarCancha(new Cancha(2, "Tenis"));

    }
MenuPrincipal menu = new MenuPrincipal(sistema);
menu.mostrar();

}
