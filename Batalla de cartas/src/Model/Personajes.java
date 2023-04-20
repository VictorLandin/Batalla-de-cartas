/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author a22victorlr
 */
public class Personajes {
    private int vida;
    protected ArrayList<Cartas> mazo;
    private int energia;
    private int regeneracionEnergia;
    
    public Personajes(int vida, ArrayList<Cartas> mazo, int energia, int regeneracionEnergia) {
        this.vida = vida;
        this.mazo = mazo;
        this.energia = energia;
        this.regeneracionEnergia = regeneracionEnergia;
    }
    
    // Métodos para manipular los atributos
    public void reducirVida(int cantidad) {
        vida -= cantidad;
    }
    
    public void aumentarVida(int cantidad) {
        vida += cantidad;
    }
    
    public void aumentarEnergia(int cantidad) {
        energia += cantidad;
    }
    
    public void reducirEnergia(int cantidad) {
        energia -= cantidad;
    }
    
    public void regenerarEnergia() {
        energia += regeneracionEnergia;
    }
}
