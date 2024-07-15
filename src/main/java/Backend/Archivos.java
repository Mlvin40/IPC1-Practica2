/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Backend.jugador.Jugador;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

/**
 *
 * @author Phoenix
 */
public class Archivos {

    /**
     * PARA EL HISTORIAL DE JUGADORES
     */
    private static final String PATH_ARCHIVO = "estadisticas.txt";
    //private File archivo;
    

    /**
     * Cada vez que un jugador gana una partida guarda utilizando este metodo
     * @param jugador 
     */
    public void guardarPartidaGanada(Jugador jugador) {
        File archivo = new File(PATH_ARCHIVO);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            if (!archivo.exists()) {
                archivo.createNewFile();  // Crea el archivo si no existe
            }

            writer.write(jugador.getNombre() + "," + jugador.getPunteo() + ","
                    + jugador.getHoraDeJuego() + "," + jugador.getNombrePerdedor() + "\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar la partida ganada");
        }
    }

    public Jugador[] leerArchivo() {
        Jugador[] players = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_ARCHIVO))) {
            String linea;
            int contLine = 0;
            while ((linea = reader.readLine()) != null) {
                contLine++;
            }

            // Crear un nuevo BufferedReader para leer desde el principio del archivo
            try (BufferedReader newReader = new BufferedReader(new FileReader(PATH_ARCHIVO))) {
                players = new Jugador[contLine];
                int contadorJugadores = 0;
                while ((linea = newReader.readLine()) != null) {
                    String[] data = linea.split(",");
                    //SE HACE EL CASTEO DE CADA JUGADOR Y SE LE PASA A UN CONSTRUCTOR ESPEFICICO
                    //QUE SE ENCARGA DE RESTABLECER ESOS DATOS
                    LocalDateTime hora = LocalDateTime.parse(data[2]);
                    Jugador nuevo = new Jugador(data[0], Integer.valueOf(data[1]), hora, data[3]);
                    players[contadorJugadores] = nuevo;
                    contadorJugadores++;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No hay un historial de jugadores");
        }

        return players;
    }
}
