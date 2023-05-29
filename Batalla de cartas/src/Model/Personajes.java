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
    int vida;

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
    

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
}
