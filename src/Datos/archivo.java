package Datos;
import java.io.*;

public class Archivo {
    public static void guardar(String texto, String ruta){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))){
            writer.write(texto);
        }catch(IOException e){
            System.out.println("Error al guardar el archivo");
        }
    }
}
