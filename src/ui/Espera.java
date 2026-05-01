package ui;

public class Espera {
    static void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void pausar() {
        IO.readln("Presione Enter para continuar...");
    }

    public static void limpiar() {
        System.out.print("\033[H\033[2J\033[3J");
        System.out.flush();

        for (int i = 0; i < 5; i++) {
            IO.println();
        }
        }}
