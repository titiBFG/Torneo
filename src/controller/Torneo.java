package controller;

import entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Torneo {
    private Map<String, Equipo> equipos;
    private List<Partido> partidos;
    private Map<Equipo, Integer> puntos;

    public Torneo() {
        this.equipos = new HashMap<>();
        this.partidos = new ArrayList<>();
        this.puntos = new HashMap<>();
    }

    public List<Equipo> getEquipos() {
        return new ArrayList<>(equipos.values());
    }

    public void addEquipo(String equipo, int fans) {
        Equipo nuevoEquipo = new Equipo(equipo, fans);
        equipos.put(equipo, nuevoEquipo);
        puntos.put(nuevoEquipo, 0);
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void addPartido(String equipoLoc,
                           String equipoVis,
                           LocalDate fecha,
                           int ptsLoc,
                           int ptsVis) {

        // Verifico si los equipos estan registrados en el torneo
        Equipo nEquipoLoc = equipos.get(equipoLoc);
        Equipo nEquipoVis = equipos.get(equipoVis);

        if (nEquipoLoc == null || nEquipoVis == null) {
            System.out.println("Error: Uno o ambos equipos no están registrados.");
            return;
        }

        Partido nuevoPartido = new Partido(nEquipoLoc, nEquipoVis, fecha);
        nuevoPartido.setPtsLoc(ptsLoc);
        nuevoPartido.setPtsVis(ptsVis);
        partidos.add(nuevoPartido);
        actualizarPuntos(nuevoPartido);
    }

    private void actualizarPuntos(Partido partido) {
        int ptsLoc = partido.getPtsLoc();
        int ptsVis = partido.getPtsVis();
        Equipo equipoLoc = partido.getEquipoLoc();
        Equipo equipoVis = partido.getEquipoVis();

        if(ptsLoc > ptsVis) {
            puntos.put(equipoLoc, puntos.get(equipoLoc) + 3);
        } else if(ptsLoc < ptsVis) {
            puntos.put(equipoVis, puntos.get(equipoVis) + 3);
        } else if(ptsLoc == ptsVis) {
            puntos.put(equipoLoc, puntos.get(equipoLoc) + 1);
            puntos.put(equipoVis, puntos.get(equipoVis) + 1);
        }

    }

    public void mostrarPartidosFecha(LocalDate fecha) {
        partidos.stream()
                .filter(partido -> partido.getFecha().equals(fecha))
                .forEach(System.out::println);
    }

    public void tablaDePosiciones() {
        System.out.printf("%-20s %5s %6s %7s %7s %7s %8s %8s %8s%n",
                "Equipo", "PJ", "Pts", "PG", "PE", "PP", "GF", "GC", "Dif");
        System.out.println("--------------------------------------------------" +
                "----------------------------------");
        puntos.entrySet().stream()
                .sorted((e1, e2) -> {
                    int comp = e2.getValue().compareTo(e1.getValue());
                    if (comp == 0) {
                        // Si los puntos son iguales, compara por goles convertidos totales
                        int goles1 = getGolesConvertidosTotales(e1.getKey());
                        int goles2 = getGolesConvertidosTotales(e2.getKey());
                        return Integer.compare(goles2, goles1);
                    }
                    return comp;
                })
                .forEach(entrada -> {
                    Equipo equipo = entrada.getKey();
                    System.out.printf("%-20s %5d %6d %7d %7d %7d %8d %8d %8d%n",
                            equipo.getNombre(),
                            getPartidosJugados(equipo),
                            entrada.getValue(),
                            getPartidosGanados(equipo),
                            getPartidosEmpatados(equipo),
                            getPartidosPerdidos(equipo),
                            getGolesConvertidosTotales(equipo),
                            getGolesRecibidosTotales(equipo),
                            getDiferenciaDeGoles(equipo)
                    );
                });
    }

    private int getPartidosJugados(Equipo equipo) {
        return (int) partidos.stream()
                .filter(partido ->
                        partido.getEquipoLoc().equals(equipo) ||
                                partido.getEquipoVis().equals(equipo))
                .count();
    }

    private int getPartidosGanados(Equipo equipo) {
        return (int) partidos.stream()
                .filter(partido ->
                        (partido.getEquipoLoc().equals(equipo) &&
                                partido.getPtsLoc() > partido.getPtsVis()) ||
                                (partido.getEquipoVis().equals(equipo) &&
                                        partido.getPtsLoc() < partido.getPtsVis()))
                .count();
    }

    private int getPartidosPerdidos(Equipo equipo) {
        return (int) partidos.stream()
                .filter(partido ->
                        (partido.getEquipoLoc().equals(equipo) &&
                                partido.getPtsLoc() < partido.getPtsVis()) ||
                                (partido.getEquipoVis().equals(equipo) &&
                                        partido.getPtsLoc() > partido.getPtsVis()))
                .count();
    }

    private int getPartidosEmpatados(Equipo equipo) {
        return (int) partidos.stream()
                .filter(partido ->
                        ((partido.getEquipoLoc().equals(equipo) ||
                                partido.getEquipoVis().equals(equipo)) &&
                                partido.getPtsLoc() == partido.getPtsVis()))
                .count();
    }

    private int getGolesConvertidosDeLocal(Equipo equipo) {
        return partidos.stream()
                .filter(partido -> partido.getEquipoLoc().equals(equipo))
                .mapToInt(Partido::getPtsLoc)
                .sum();
    }

    private int getGolesConvertidosDeVisitante(Equipo equipo) {
        return partidos.stream()
                .filter(partido -> partido.getEquipoVis().equals(equipo))
                .mapToInt(Partido::getPtsVis)
                .sum();
    }

    private int getGolesConvertidosTotales(Equipo equipo) {
        return getGolesConvertidosDeLocal(equipo) +
                getGolesConvertidosDeVisitante(equipo);
    }

    private int getGolesRecibidosDeLocal(Equipo equipo) {
        return partidos.stream()
                .filter(partido -> partido.getEquipoLoc().equals(equipo))
                .mapToInt(Partido::getPtsVis)
                .sum();
    }

    private int getGolesRecibidosDeVisitante(Equipo equipo) {
        return partidos.stream()
                .filter(partido -> partido.getEquipoVis().equals(equipo))
                .mapToInt(Partido::getPtsLoc)
                .sum();
    }

    private int getGolesRecibidosTotales(Equipo equipo) {
        return getGolesRecibidosDeLocal(equipo) +
                getGolesRecibidosDeVisitante(equipo);
    }

    private int getDiferenciaDeGoles(Equipo equipo) {
        return Math.abs(getGolesConvertidosTotales(equipo) -
                getGolesRecibidosTotales(equipo));
    }
}
