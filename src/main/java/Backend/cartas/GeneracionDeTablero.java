/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.cartas;

import java.awt.GridLayout;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author Phoenix
 */
public class GeneracionDeTablero {

    Random random = new Random();

    private JPanel panelTablero;
    private Carta[][] botones;

    private int filas;
    private int columnas;

    /**
     * filas y columnas es igual a la dificultad elegida al momento de
     * instanciar la clase recibe esos valores
     *
     * @param panelTablero
     * @param filas
     * @param columnas
     */
    public GeneracionDeTablero(JPanel panelTablero, int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.panelTablero = panelTablero;
    }

    public void inicializarComponentes() {
        panelTablero.setLayout(new GridLayout(filas, columnas));
        botones = new Carta[filas][columnas];

        //llena el arreglo con cartas en donde sea null
        llenarCartas();

        //METODO PARA CREAR EL TABLERO  
        for (int x = 0; x < filas; x++) {
            for (int y = 0; y < columnas; y++) {
                panelTablero.add(botones[x][y]);
            }
        }
        panelTablero.revalidate();
        panelTablero.repaint();
    }

    /**
     * Este metodo obtiene el total de cartas que va a contener el tablero y las
     * divide en dos para que ese sea su recorrido y dentro de cada vuelta en el
     * for se multiplica una carta por lo que siempre tendra una pareja
     */
    private void llenarCartas() {

        int totalcasillas = filas * columnas;
        int recorrido = totalcasillas / 2;

        for (int i = 0; i < recorrido; i++) {

            int rndFila;
            int rndColumna;
            int contador = 0;

            //Carta temporal para obtener una pareja nueva
            Carta cartaElegida = cartaAleatoria();

            //Metodo para duplicar las cartas
            while (contador < 2) {

                rndFila = random.nextInt(filas);
                rndColumna = random.nextInt(columnas);

                if (botones[rndFila][rndColumna] == null) {
                    contador++;
                    botones[rndFila][rndColumna] = duplicarCarta(cartaElegida);
                }
            }
        }
    }

    /**
     * este metodo se utiliza para duplicar la carta elegida aleatoriamente por
     * el metodo cartaAleatoria
     *
     * @param carta
     * @return
     */
    private Carta duplicarCarta(Carta carta) {

        if (carta instanceof CardUno) {
            return new CardUno();
        } else if (carta instanceof CardDos) {
            return new CardDos();
        } else if (carta instanceof CardTres) {
            return new CardTres();
        } else if (carta instanceof CardCuatro) {
            return new CardCuatro();
        } else if (carta instanceof CardCinco) {
            return new CardCinco();
        } else if (carta instanceof CardSeis) {
            return new CardSeis();
        }
        return null;
    }

    /**
     * Elige una carta aleatoria de todas las existentes, esto con la finalidad
     * de que el tablero no tenga siempre una misma pareja
     *
     * @return
     */
    private Carta cartaAleatoria() {
        random = new Random();
        int rnd = random.nextInt(6) + 1;

        switch (rnd) {
            case 1:
                return new CardUno();

            case 2:
                return new CardDos();

            case 3:
                return new CardTres();

            case 4:
                return new CardCuatro();

            case 5:
                return new CardCinco();

            case 6:
                return new CardSeis();

            default:
        }
        return null;

    }

    public Carta[][] getBotones() {
        return botones;
    }

    public void setBotones(Carta[][] botones) {
        this.botones = botones;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }
}
