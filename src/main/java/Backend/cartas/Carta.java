/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.cartas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Phoenix
 */
public class Carta extends JButton {

    protected String caracter;
    private Random random = new Random();
    protected int id;
    protected String IMAGEN;

    private int[] numeros = {1, 2, 3, 4, 5, 6, 7, 8};

    public Carta(String caracter, int id) {
        this.id = id;
        this.caracter = caracter;

        setBackground(Color.WHITE);
        //agregarFuncion();
    }

    /*
    private void agregarFuncion() {
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                caraLevantada = true;
                mostrarId();
            }
        });
    }*/
 /*private void almacenarCartas() {

        cartaPresionada = this;
        contador++;
    }*/
    public void mostrarImagen() {
        setIcon(new ImageIcon(getClass().getClassLoader().getResource(IMAGEN)));
    }

    public int elegirNumero() {
        int numAleatorio = random.nextInt(numeros.length);
        return numeros[numAleatorio];
    }

    public boolean esIgual() {
        for (int num : numeros) {
            if (id == num) {
                return true; // Si encuentra un número igual, devuelve true
            }
        }
        return false; // Si termina de recorrer el arreglo sin encontrar coincidencias, devuelve false
    }

    public void mostrarId() {
        this.setText(String.valueOf(id));
        mostrarImagen();
    }

    public void mostrarCaracter1() {
        setText(String.valueOf(caracter));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;

    }
}
