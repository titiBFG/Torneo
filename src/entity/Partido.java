package entity;

import java.time.LocalDate;

public class Partido {
    private LocalDate fecha;
    private Equipo equipoLoc;
    private Equipo equipoVis;
    private int ptsLoc;
    private int ptsVis;

    public Partido(Equipo equipoLoc,
                   Equipo equipoVis,
                   LocalDate fecha) {
        this.fecha = fecha;
        this.equipoLoc = equipoLoc;
        this.equipoVis = equipoVis;
        this.ptsLoc = 0;
        this.ptsVis = 0;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Equipo getEquipoLoc() {
        return this.equipoLoc;
    }

    public void setEquipoLoc(Equipo equipo) {
        this.equipoLoc = equipo;
    }

    public Equipo getEquipoVis() {
        return this.equipoVis;
    }

    public void setEquipoVis(Equipo equipo) {
        this.equipoVis = equipo;
    }

    public int getPtsLoc() {
        return this.ptsLoc;
    }

    public void setPtsLoc(int pts) {
        this.ptsLoc = pts;
    }

    public int getPtsVis() {
        return this.ptsVis;
    }

    public void setPtsVis(int pts) {
        this.ptsVis = pts;
    }

    @Override
    public String toString() {
        return "Fecha: " + fecha +
                ", " + equipoLoc.getNombre() + " (" + ptsLoc + ")" +
                ", " + equipoVis.getNombre() + " (" + ptsVis + ")";
    }
}
