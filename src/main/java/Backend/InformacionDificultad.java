/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

/**
 *
 * @author Phoenix
 */
public class InformacionDificultad {

    private int filas;
    private static final int columnas = 5;

    public int getColumnas() {
        return columnas;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public void facil() {
        this.filas = 2;
    }

    public void normal() {
        this.filas = 4;
    }

    public void dificil() {
        this.filas = 6;
    }
}
