public class Cancha {
    int id;
    String tipo;
    boolean disponible;

    Cancha(int id, String tipo){
        this.id = id;
        this.tipo = tipo;
        this.disponible = true;
    }


    public void mostrarInfo(){
        String estado = disponible ? "Si" : "No";
        IO.println("ID: " + id + " | Tipo: " + tipo + " | Disponible: " + estado);
    }


}
