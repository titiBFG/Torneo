package entity;

public class Equipo {
    private final String nombre;
    private int fans;

    public Equipo(String nombre, int fans) {
        this.nombre = nombre;
        this.fans = fans;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getFans() {
        return this.fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }
}