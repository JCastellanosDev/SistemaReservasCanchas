package modelos;

public class Cancha {
    private int id;
    private String tipo;
    private boolean disponible;

    public Cancha(int id, String tipo){
        this.id = id;
        this.tipo = tipo;
        this.disponible = true;
    }

    public String getTipo() {
        return tipo;
    }

    public int getId() {
        return id;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void mostrarInfo() {
        String estado = disponible ? "Disponible" : "Ocupado";
        System.out.println("ID: " + id + " | Tipo: " + tipo + " | Estado: " + estado);
    }

    @Override
    public String toString() {
        String estado = disponible ? "Disponible" : "Ocupado";
        return "ID: " + id + " | Tipo: " + tipo + " | Estado: " + estado;
    }


}
