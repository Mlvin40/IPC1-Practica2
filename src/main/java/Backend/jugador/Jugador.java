/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.jugador;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.swing.JOptionPane;

/**
 *
 * @author Phoenix
 */
public class Jugador implements Serializable {

    private String nombre;
    private int punteo;
    private int parejasEncontradas;
    private final LocalDateTime horaDeJuego;

    private String nombrePerdedor;

    public Jugador(String nombre, int punteo, LocalDateTime horaDeJuego, String nombrePerdedor) {
        this.nombre = nombre;
        this.punteo = punteo;
        this.horaDeJuego = horaDeJuego;
        this.nombrePerdedor = nombrePerdedor;
    }

    public Jugador(String nombre) {
        this.nombre = nombre;
        //para obtener la hora de juego sin los milisegundos
        this.horaDeJuego = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        this.punteo = 0;
        this.parejasEncontradas = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPunteo() {
        return punteo;
    }

    public void setPunteo(int punteo) {
        this.punteo = punteo;
    }

    public int getParejasEncontradas() {
        return parejasEncontradas;
    }

    public void setParejasEncontradas(int parejasEncontradas) {
        this.parejasEncontradas = parejasEncontradas;
    }

    public String getNombrePerdedor() {
        return nombrePerdedor;
    }

    public void setNombrePerdedor(String nombrePerdedor) {
        this.nombrePerdedor = nombrePerdedor;
    }

    public LocalDateTime getHoraDeJuego() {
        return horaDeJuego;
    }

    //si encuentra una pareja tiene que sumarle 50 puntos al jugador
    public void sumarPuntos() {
        punteo += 50;
    }

    //al falla un intento se le restan puntos al jugador
    public void restarPuntos() {
        if (punteo > 0) {
            punteo -= 10;
        }
    }

    public void mostrarDatos() {
        JOptionPane.showMessageDialog(null, nombre + "\n" + "Punteo: " + punteo
                + "\n" + "HoraDeJuego: " + horaDeJuego + "\n" + "Perdedor: " + nombrePerdedor, "GANADOR", JOptionPane.PLAIN_MESSAGE);
    }
}
