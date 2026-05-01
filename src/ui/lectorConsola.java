package ui;

public class LectorConsola {
    public static int leerEntero(String mensaje){
        while (true){
            try{
                return Integer.parseInt(IO.readln(mensaje));
            }catch(NumberFormatException e){
                IO.println("Ingresa un numero valido");
            }
        }
    }
}
