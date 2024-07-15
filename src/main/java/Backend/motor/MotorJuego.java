/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.motor;

import Backend.Archivos;
import Backend.cartas.Carta;
import Backend.cartas.GeneracionDeTablero;
import Backend.jugador.Jugador;
import Frontend.MenuPrincipal;
import Frontend.PedirNombre;
import Frontend.VentanaJuego;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Phoenix
 */
public class MotorJuego {

    Archivos archivos = new Archivos();

    private int contador = 0;
    private boolean parejaEncontrada;
    private Carta cartaPresionada1;
    private Carta cartaPresionada2;
    private int cantidadParejasGeneradas;

    GeneracionDeTablero tableroEnJuego;
    VentanaJuego framePrincipal;

    Jugador jugador1;
    Jugador jugador2;
    Jugador jugadorEnTurno;

    private Carta[][] botones;
    private int filas;
    private int columnas;

    public MotorJuego(VentanaJuego framePrincipal, GeneracionDeTablero tableroGenerado) {
        //esta parte del constructor obtiene todo lo que necesita para iniciar con la partida
        this.framePrincipal = framePrincipal;
        this.tableroEnJuego = tableroGenerado;
        this.botones = tableroGenerado.getBotones();
        this.filas = tableroGenerado.getFilas();
        this.columnas = tableroGenerado.getColumnas();
        this.cantidadParejasGeneradas = (columnas * filas) / 2;

        JOptionPane.showMessageDialog(null, "Se ha empezado una nueva partida");
        crearJugadores();

        elegirQuienEmpieza();
        actualizarEstadisticas();
        controladorPartida();
    }

    private void elegirQuienEmpieza() {
        Random random = new Random();
        int numrandom = random.nextInt(2);

        switch (numrandom) {
            case 0:
                jugadorEnTurno = jugador1;
                break;

            case 1:
                jugadorEnTurno = jugador2;
                break;

            default:
                //no deberia de llegar a este punto, pero por si al caso
                JOptionPane.showMessageDialog(null, "ha ocurrido un error inesperado");
        }
    }

    //Metodo que es utilizado para crear a los jugadores de la partida actual
    public void crearJugadores() {

        PedirNombre pedirName = new PedirNombre(framePrincipal, true);
        pedirName.setVisible(true);
        jugador1 = new Jugador(pedirName.getNombreCapturado());

        PedirNombre pedirName2 = new PedirNombre(framePrincipal, true);
        pedirName2.setVisible(true);
        jugador2 = new Jugador(pedirName2.getNombreCapturado());

    }

    //metodo que se utiliza para mantener actializado el jframe con todos sus contenedores
    private void actualizarEstadisticas() {

        framePrincipal.getLblJugador1().setText(jugador1.getNombre());
        framePrincipal.getLblJugador2().setText(jugador2.getNombre());
        /**
         *
         */
        framePrincipal.getLblPunteoJugador1().setText(String.valueOf(jugador1.getPunteo()));
        framePrincipal.getLblPunteoJugador2().setText(String.valueOf(jugador2.getPunteo()));
        /**
         *
         */
        framePrincipal.getLblJugador1().setText(jugador1.getNombre());
        framePrincipal.getLblJugador2().setText(jugador2.getNombre());

        framePrincipal.getLblJugadorEnTurno().setText(jugadorEnTurno.getNombre());

    }

    private void controladorPartida() {
        funcionBotones();
        //    while (cantidadParejasGeneradas != 0) {
        //  }
    }

    private void funcionBotones() {
        for (int x = 0; x < filas; x++) {
            for (int y = 0; y < columnas; y++) {
                final int filaActual = x;
                final int columnaActual = y;

                botones[x][y].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //Agregar Funciones a la hora de Presionar un boton
                        botones[filaActual][columnaActual].mostrarId();

                        controlarPresiones(botones[filaActual][columnaActual]);

                        if (finalPartida()) {
                            //MostrarGanador
                            definirGanador();
                            framePrincipal.dispose();
                            MenuPrincipal menuPrincipal = new MenuPrincipal();
                            menuPrincipal.setVisible(true);

                        }
                    }
                });
            }
        }
    }

    private void cambiarTurno() {

        if (jugadorEnTurno == jugador1) {
            jugadorEnTurno = jugador2;
        } else if (jugadorEnTurno == jugador2) {
            jugadorEnTurno = jugador1;
        }
        actualizarEstadisticas();
    }

    public void controlarPresiones(Carta cardPress) {
        cardPress.setEnabled(false);

        if (contador == 0) {
            cartaPresionada1 = cardPress;
            contador++;
        } else if (contador == 1) {
            cartaPresionada2 = cardPress;
            contador++;
        }

        /**
         * cuando un jugador haya presionado dos botones se llamara al metodo
         * encargado de ver si se encontro una pareja
         */
        if (contador > 1) {
            verificarPareja();
            contador = 0;
        }
    }

    /**
     * este metodo es el que verifica si un jugador encontro una pareja y le
     * suma 50 puntos de lo contrario cambiara de turno y al jugado que fallo le
     * restara 10 puntos
     */
    private void verificarPareja() {

        cartaPresionada2.mostrarId();

        if (cartaPresionada1.getId() == cartaPresionada2.getId()) {
            /**
             * si el jugador acerto un par de cartas este sigue en turno y se
             * realizan las respectivas acciones por conseguir una pareja
             */

            cartaPresionada1.setEnabled(false);
            cartaPresionada2.setEnabled(false);
            cartaPresionada1.setBackground(Color.CYAN);
            cartaPresionada2.setBackground(Color.CYAN);

            parejaEncontrada = true;
            cantidadParejasGeneradas--;

            //Como el jugador en turno encontro una pareja se le suman los puntos
            jugadorEnTurno.sumarPuntos();
            actualizarEstadisticas();

            if (finalPartida()) {
                JOptionPane.showMessageDialog(null, "Ya se han encontrado todas las parejas");
            }

        } else {
            /**
             * Si no se encuentra una pareja se restablecen las cartas que no
             * coincidieron y realiza las respectivas acciones al jugador por
             * fallar
             */

            //Mostrar las cartas por 1 segundo
            JOptionPane.showMessageDialog(null, "Pareja no encontrada");

            jugadorEnTurno.restarPuntos();
            actualizarEstadisticas();
            cambiarTurno();
            cartaPresionada1.setText("");
            cartaPresionada2.setText("");

            cartaPresionada1.setIcon(null);
            cartaPresionada2.setIcon(null);

            cartaPresionada1.setEnabled(true);
            cartaPresionada2.setEnabled(true);
            parejaEncontrada = false;
        }
    }

    private boolean finalPartida() {
        return cantidadParejasGeneradas == 0;
    }

    private void definirGanador() {
        Jugador ganador;
        Jugador perdedor;
        if (jugador1.getPunteo() > jugador2.getPunteo()) {
            ganador = jugador1;
            perdedor = jugador2;
        } else {
            ganador = jugador2;
            perdedor = jugador1;
        }
        ganador.setNombrePerdedor(perdedor.getNombre());
        ganador.mostrarDatos();
        archivos.guardarPartidaGanada(ganador);
    }
}