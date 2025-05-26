package boundary;

import controller.Torneo;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Torneo torneo = new Torneo();
        // Carga de equipos
        torneo.addEquipo("Team A", 100);
        torneo.addEquipo("Team B", 200);
        torneo.addEquipo("Team C", 300);
        torneo.addEquipo("Team D", 400);
        torneo.addEquipo("Team E", 500);
        torneo.addEquipo("Team F", 600);
        torneo.addEquipo("Team G", 700);
        torneo.addEquipo("Team H", 800);

        // Fecha 1-4/11
        torneo.addPartido("Team A", "Team B", LocalDate.of(2023, 11, 4), 2, 0);
        torneo.addPartido("Team C", "Team D", LocalDate.of(2023, 11, 4), 2, 1);
        torneo.addPartido("Team E", "Team F", LocalDate.of(2023, 11, 4), 2, 2);
        torneo.addPartido("Team G", "Team H", LocalDate.of(2023, 11, 4), 2, 3);
        // Fecha 2-12/11
        torneo.addPartido("Team B", "Team A", LocalDate.of(2023, 11, 12), 1, 1);
        torneo.addPartido("Team C", "Team D", LocalDate.of(2023, 11, 12), 3, 0);
        torneo.addPartido("Team F", "Team E", LocalDate.of(2023, 11, 12), 1, 2);
        torneo.addPartido("Team H", "Team G", LocalDate.of(2023, 11, 12), 0, 0);
        // Punto b.4
        torneo.mostrarPartidosFecha(LocalDate.of(2023,11,12));
        // Punto b.5
        torneo.tablaDePosiciones();

    }
}