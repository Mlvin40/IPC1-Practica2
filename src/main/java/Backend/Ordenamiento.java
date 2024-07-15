/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Backend.jugador.Jugador;

/**
 *
 * @author Phoenix
 */
public class Ordenamiento {
    
    /**
     * METODO BURBUJA
     * ordenamiento de jugadores
     * @param jugadores
     * @return 
     */
    public Jugador[] ordenarPorPuntajeDescendente(Jugador[] jugadores) {
        int n = jugadores.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (jugadores[j].getPunteo() < jugadores[j + 1].getPunteo()) {
                    // Intercambiar jugadores
                    Jugador temp = jugadores[j];
                    jugadores[j] = jugadores[j + 1];
                    jugadores[j + 1] = temp;
                }
            }
        }
        return jugadores;
    }
}
