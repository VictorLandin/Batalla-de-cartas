/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


/**
 *
 * @author a22victorlr
 */
public class Personajes {
    private int vida;

    /**
     *
     */
    private int energia;
    private int regeneracionEnergia;
    
    /**
     *
     * @param vida
     * @param energia
     * @param regeneracionEnergia
     */
    public Personajes(int vida, int energia, int regeneracionEnergia) {
        this.vida = vida;
        this.energia = energia;
        this.regeneracionEnergia = regeneracionEnergia;
    }
    
    // Métodos para manipular los atributos

    /**
     *
     * @param cantidad
     */
    public void reducirVida(int cantidad) {
        vida -= cantidad;
    }
    
    /**
     *
     * @param cantidad
     */
    public void aumentarVida(int cantidad) {
        vida += cantidad;
    }
    
    /**
     *
     * @param cantidad
     */
    public void aumentarEnergia(int cantidad) {
        energia += cantidad;
    }
    
    /**
     *
     * @param cantidad
     */
    public void reducirEnergia(int cantidad) {
        energia -= cantidad;
    }
    
    /**
     *
     */
    public void regenerarEnergia() {
        energia += regeneracionEnergia;
    }
}
